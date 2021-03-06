package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.data.xml.impl.HennaData;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.items.Henna;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

import java.util.List;

/**
 * @author Zoey76
 */
public class HennaEquipList extends ServerPacket {
    private final Player _player;
    private final List<Henna> _hennaEquipList;

    public HennaEquipList(Player player) {
        _player = player;
        _hennaEquipList = HennaData.getInstance().getHennaList(player.getClassId());
    }

    public HennaEquipList(Player player, List<Henna> list) {
        _player = player;
        _hennaEquipList = list;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.HENNA_EQUIP_LIST);
        writeLong(_player.getAdena()); // activeChar current amount of Adena
        writeInt(3); // available equip slot
        writeInt(_hennaEquipList.size());

        for (Henna henna : _hennaEquipList) {
            // Player must have at least one dye in inventory
            // to be able to see the Henna that can be applied with it.
            if ((_player.getInventory().getItemByItemId(henna.getDyeItemId())) != null) {
                writeInt(henna.getDyeId()); // dye Id
                writeInt(henna.getDyeItemId()); // item Id of the dye
                writeLong(henna.getWearCount()); // amount of dyes required
                writeLong(henna.getWearFee()); // amount of Adena required
                writeInt(henna.isAllowedClass(_player.getClassId()) ? 0x01 : 0x00); // meet the requirement or not
                // writeInt(0x00); // Does not exist in Classic.
            }
        }
    }

}
