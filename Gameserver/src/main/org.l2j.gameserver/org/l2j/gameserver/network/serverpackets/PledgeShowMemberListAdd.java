package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.ClanMember;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

public final class PledgeShowMemberListAdd extends ServerPacket {
    private final String _name;
    private final int _lvl;
    private final int _classId;
    private final int _isOnline;
    private final int _pledgeType;

    public PledgeShowMemberListAdd(Player player) {
        _name = player.getName();
        _lvl = player.getLevel();
        _classId = player.getClassId().getId();
        _isOnline = (player.isOnline() ? player.getObjectId() : 0);
        _pledgeType = player.getPledgeType();
    }

    public PledgeShowMemberListAdd(ClanMember cm) {
        _name = cm.getName();
        _lvl = cm.getLevel();
        _classId = cm.getClassId();
        _isOnline = (cm.isOnline() ? cm.getObjectId() : 0);
        _pledgeType = cm.getPledgeType();
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.PLEDGE_SHOW_MEMBER_LIST_ADD);

        writeString(_name);
        writeInt(_lvl);
        writeInt(_classId);
        writeInt(0x00);
        writeInt(0x01);
        writeInt(_isOnline); // 1 = online 0 = offline
        writeInt(_pledgeType);
    }

}
