package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.items.instance.Item;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

import java.util.ArrayList;
import java.util.List;

public final class WareHouseDepositList extends AbstractItemPacket {
    public static final int PRIVATE = 1;
    public static final int CLAN = 2;
    public static final int CASTLE = 3;
    public static final int FREIGHT = 1;
    private final int _sendType;
    private final long _playerAdena;
    private final List<Item> _items = new ArrayList<>();
    private final List<Integer> _itemsStackable = new ArrayList<>();
    /**
     * <ul>
     * <li>0x01-Private Warehouse</li>
     * <li>0x02-Clan Warehouse</li>
     * <li>0x03-Castle Warehouse</li>
     * <li>0x04-Warehouse</li>
     * </ul>
     */
    private final int _whType;

    public WareHouseDepositList(int sendType, Player player, int type) {
        _sendType = sendType;
        _whType = type;
        _playerAdena = player.getAdena();

        final boolean isPrivate = _whType == PRIVATE;
        for (Item temp : player.getInventory().getAvailableItems(true, isPrivate, false)) {
            if ((temp != null) && temp.isDepositable(isPrivate)) {
                _items.add(temp);
            }
            if ((temp != null) && temp.isDepositable(isPrivate) && temp.isStackable()) {
                _itemsStackable.add(temp.getDisplayId());
            }
        }
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.WAREHOUSE_DEPOSIT_LIST);
        writeByte((byte) _sendType);
        if (_sendType == 2) {
            writeInt(_whType);
            writeInt(_items.size());
            for (Item item : _items) {
                writeItem(item);
                writeInt(item.getObjectId());
            }
        } else {
            writeShort((short) _whType);
            writeLong(_playerAdena);
            writeInt(_itemsStackable.size());
            writeInt(_items.size());
        }
    }

}
