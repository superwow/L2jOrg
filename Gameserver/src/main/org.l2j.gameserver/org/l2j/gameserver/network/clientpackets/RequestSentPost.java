package org.l2j.gameserver.network.clientpackets;

import org.l2j.gameserver.Config;
import org.l2j.gameserver.instancemanager.MailManager;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.entity.Message;
import org.l2j.gameserver.world.zone.ZoneType;
import org.l2j.gameserver.network.SystemMessageId;
import org.l2j.gameserver.network.serverpackets.ExReplySentPost;
import org.l2j.gameserver.util.GameUtils;

/**
 * @author Migi, DS
 */
public final class RequestSentPost extends ClientPacket {
    private int _msgId;

    @Override
    public void readImpl() {
        _msgId = readInt();
    }

    @Override
    public void runImpl() {
        final Player activeChar = client.getPlayer();
        if ((activeChar == null) || !Config.ALLOW_MAIL) {
            return;
        }

        final Message msg = MailManager.getInstance().getMessage(_msgId);
        if (msg == null) {
            return;
        }

        if (!activeChar.isInsideZone(ZoneType.PEACE) && msg.hasAttachments()) {
            client.sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS);
            return;
        }

        if (msg.getSenderId() != activeChar.getObjectId()) {
            GameUtils.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to read not own post!", Config.DEFAULT_PUNISH);
            return;
        }

        if (msg.isDeletedBySender()) {
            return;
        }

        client.sendPacket(new ExReplySentPost(msg));
    }
}
