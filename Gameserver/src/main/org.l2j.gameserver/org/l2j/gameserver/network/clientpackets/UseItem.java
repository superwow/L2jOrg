package org.l2j.gameserver.network.clientpackets;

import org.l2j.commons.threading.ThreadPool;
import org.l2j.gameserver.Config;
import org.l2j.gameserver.ai.CtrlEvent;
import org.l2j.gameserver.ai.CtrlIntention;
import org.l2j.gameserver.ai.NextAction;
import org.l2j.gameserver.enums.ItemSkillType;
import org.l2j.gameserver.enums.PrivateStoreType;
import org.l2j.gameserver.handler.AdminCommandHandler;
import org.l2j.gameserver.handler.IItemHandler;
import org.l2j.gameserver.handler.ItemHandler;
import org.l2j.gameserver.instancemanager.FortSiegeManager;
import org.l2j.gameserver.model.WorldObject;
import org.l2j.gameserver.world.World;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.effects.EffectType;
import org.l2j.gameserver.model.holders.ItemSkillHolder;
import org.l2j.gameserver.model.items.ItemTemplate;
import org.l2j.gameserver.model.items.EtcItem;
import org.l2j.gameserver.model.items.instance.Item;
import org.l2j.gameserver.model.items.type.ActionType;
import org.l2j.gameserver.network.SystemMessageId;
import org.l2j.gameserver.network.serverpackets.ActionFailed;
import org.l2j.gameserver.network.serverpackets.ExUseSharedGroupItem;
import org.l2j.gameserver.network.serverpackets.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;
import static org.l2j.gameserver.util.GameUtils.isItem;

public final class UseItem extends ClientPacket {

    private static final Logger LOGGER = LoggerFactory.getLogger(UseItem.class);
    private int _objectId;
    private boolean _ctrlPressed;
    private int _itemId;

    @Override
    public void readImpl() {
        _objectId = readInt();
        _ctrlPressed = readInt() != 0;
    }

    @Override
    public void runImpl() {
        final Player player = client.getPlayer();
        if (player == null) {
            return;
        }

        // Flood protect UseItem
        if (!client.getFloodProtectors().getUseItem().tryPerformAction("use item")) {
            return;
        }

        if (player.getActiveTradeList() != null) {
            player.cancelActiveTrade();
        }

        if (player.getPrivateStoreType() != PrivateStoreType.NONE) {
            player.sendPacket(SystemMessageId.WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM);
            player.sendPacket(ActionFailed.STATIC_PACKET);
            return;
        }

        final Item item = player.getInventory().getItemByObjectId(_objectId);
        if (item == null) {
            // gm can use other player item
            if (player.isGM()) {
                final WorldObject obj = World.getInstance().findObject(_objectId);
                if (isItem(obj)) {
                    AdminCommandHandler.getInstance().useAdminCommand(player, "admin_use_item " + _objectId, true);
                }
            }
            return;
        }

        if (item.isQuestItem() && (item.getItem().getDefaultAction() != ActionType.NONE)) {
            player.sendPacket(SystemMessageId.YOU_CANNOT_USE_QUEST_ITEMS);
            return;
        }

        // No UseItem is allowed while the player is in special conditions
        if (player.hasBlockActions() || player.isControlBlocked() || player.isAlikeDead()) {
            return;
        }

        // Char cannot use item when dead
        if (player.isDead() || !player.getInventory().canManipulateWithItemId(item.getId())) {
            final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS);
            sm.addItemName(item);
            player.sendPacket(sm);
            return;
        }

        if (!item.isEquipped() && !item.getItem().checkCondition(player, player, true)) {
            return;
        }

        _itemId = item.getId();
        if (player.isFishing() && ((_itemId < 6535) || (_itemId > 6540))) {
            // You cannot do anything else while fishing
            player.sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_FISHING_SCREEN);
            return;
        }

        if (!Config.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && (player.getReputation() < 0)) {
            final List<ItemSkillHolder> skills = item.getItem().getSkills(ItemSkillType.NORMAL);
            if ((skills != null) && skills.stream().anyMatch(holder -> holder.getSkill().hasEffectType(EffectType.TELEPORT))) {
                return;
            }
        }

        // If the item has reuse time and it has not passed.
        // Message from reuse delay must come from item.
        final int reuseDelay = item.getReuseDelay();
        final int sharedReuseGroup = item.getSharedReuseGroup();
        if (reuseDelay > 0) {
            final long reuse = player.getItemRemainingReuseTime(item.getObjectId());
            if (reuse > 0) {
                reuseData(player, item, reuse);
                sendSharedGroupUpdate(player, sharedReuseGroup, reuse, reuseDelay);
                return;
            }

            final long reuseOnGroup = player.getReuseDelayOnGroup(sharedReuseGroup);
            if (reuseOnGroup > 0) {
                reuseData(player, item, reuseOnGroup);
                sendSharedGroupUpdate(player, sharedReuseGroup, reuseOnGroup, reuseDelay);
                return;
            }
        }

        if (item.isEquipable()) {
            // Don't allow to put formal wear while a cursed weapon is equipped.
            if (player.isCursedWeaponEquipped() && (_itemId == 6408)) {
                return;
            }

            // Equip or unEquip
            if (FortSiegeManager.getInstance().isCombat(_itemId)) {
                return; // no message
            }

            if (player.isCombatFlagEquipped()) {
                return;
            }

            if (player.getInventory().isItemSlotBlocked(item.getItem().getBodyPart())) {
                player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
                return;
            }
            // Prevent players to equip weapon while wearing combat flag
            // Don't allow weapon/shield equipment if a cursed weapon is equipped.
            if ((item.getItem().getBodyPart() == ItemTemplate.SLOT_LR_HAND) || (item.getItem().getBodyPart() == ItemTemplate.SLOT_L_HAND) || (item.getItem().getBodyPart() == ItemTemplate.SLOT_R_HAND)) {
                if ((player.getActiveWeaponItem() != null) && (player.getActiveWeaponItem().getId() == 9819)) {
                    player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
                    return;
                }
                if (player.isMounted() || player.isDisarmed()) {
                    player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
                    return;
                }
                if (player.isCursedWeaponEquipped()) {
                    return;
                }
            } else if (item.getItem().getBodyPart() == ItemTemplate.SLOT_TALISMAN) {
                if (!item.isEquipped() && (player.getInventory().getTalismanSlots() == 0)) {
                    player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
                    return;
                }
            } else if (item.getItem().getBodyPart() == ItemTemplate.SLOT_BROOCH_JEWEL) {
                if (!item.isEquipped() && (player.getInventory().getBroochJewelSlots() == 0)) {
                    final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_CANNOT_EQUIP_S1_WITHOUT_EQUIPPING_A_BROOCH);
                    sm.addItemName(item);
                    player.sendPacket(sm);
                    return;
                }
            } else if (item.getItem().getBodyPart() == ItemTemplate.SLOT_AGATHION) {
                if (!item.isEquipped() && (player.getInventory().getAgathionSlots() == 0)) {
                    player.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
                    return;
                }
            } else if (item.getItem().getBodyPart() == ItemTemplate.SLOT_ARTIFACT) {
                if (!item.isEquipped() && (player.getInventory().getArtifactSlots() == 0)) {
                    final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.NO_ARTIFACT_BOOK_EQUIPPED_YOU_CANNOT_EQUIP_S1);
                    sm.addItemName(item);
                    player.sendPacket(sm);
                    return;
                }
            }
            if (player.isCastingNow()) {
                // Create and Bind the next action to the AI
                player.getAI().setNextAction(new NextAction(CtrlEvent.EVT_FINISH_CASTING, CtrlIntention.AI_INTENTION_CAST, () -> player.useEquippableItem(item, true)));
            } else if (player.isAttackingNow()) {
                ThreadPool.schedule(() -> {
                    var usedItem = player.getInventory().getItemByObjectId(_objectId);

                    if(isNull(usedItem)) {
                        return;
                    }
                    // Equip or unEquip
                    player.useEquippableItem(usedItem, false);
                }, player.getAttackEndTime() - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()));
            } else {
                player.useEquippableItem(item, true);
            }
        } else {
            final EtcItem etcItem = item.getEtcItem();
            final IItemHandler handler = ItemHandler.getInstance().getHandler(etcItem);
            if (handler == null) {
                if ((etcItem != null) && (etcItem.getHandlerName() != null)) {
                    LOGGER.warn("Unmanaged Item handler: " + etcItem.getHandlerName() + " for Item Id: " + _itemId + "!");
                }
            } else if (handler.useItem(player, item, _ctrlPressed)) {
                // Item reuse time should be added if the item is successfully used.
                // Skill reuse delay is done at handlers.itemhandlers.ItemSkillsTemplate;
                if (reuseDelay > 0) {
                    player.addTimeStampItem(item, reuseDelay);
                    sendSharedGroupUpdate(player, sharedReuseGroup, reuseDelay, reuseDelay);
                }
            }
        }
    }

    private void reuseData(Player activeChar, Item item, long remainingTime) {
        final int hours = (int) (remainingTime / 3600000);
        final int minutes = (int) (remainingTime % 3600000) / 60000;
        final int seconds = (int) ((remainingTime / 1000) % 60);
        final SystemMessage sm;
        if (hours > 0) {
            sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_HOUR_S_S3_MINUTE_S_AND_S4_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
            sm.addItemName(item);
            sm.addInt(hours);
            sm.addInt(minutes);
        } else if (minutes > 0) {
            sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_MINUTE_S_S3_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
            sm.addItemName(item);
            sm.addInt(minutes);
        } else {
            sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
            sm.addItemName(item);
        }
        sm.addInt(seconds);
        activeChar.sendPacket(sm);
    }

    private void sendSharedGroupUpdate(Player activeChar, int group, long remaining, int reuse) {
        if (group > 0) {
            activeChar.sendPacket(new ExUseSharedGroupItem(_itemId, group, remaining, reuse));
        }
    }
}
