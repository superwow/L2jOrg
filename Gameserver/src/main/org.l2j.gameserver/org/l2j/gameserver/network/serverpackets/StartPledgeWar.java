package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

public class StartPledgeWar extends ServerPacket {
    private final String _pledgeName;
    private final String _playerName;

    public StartPledgeWar(String pledge, String charName) {
        _pledgeName = pledge;
        _playerName = charName;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.START_PLEDGE_WAR);

        writeString(_playerName);
        writeString(_pledgeName);
    }

}