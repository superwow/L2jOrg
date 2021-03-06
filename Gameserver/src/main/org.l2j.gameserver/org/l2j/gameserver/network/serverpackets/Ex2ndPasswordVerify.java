package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

/**
 * @author mrTJO
 */
public class Ex2ndPasswordVerify extends ServerPacket {
    // TODO: Enum
    public static final int PASSWORD_OK = 0x00;
    public static final int PASSWORD_WRONG = 0x01;
    public static final int PASSWORD_BAN = 0x02;

    private final int _wrongTentatives;
    private final int _mode;

    public Ex2ndPasswordVerify(int mode, int wrongTentatives) {
        _mode = mode;
        _wrongTentatives = wrongTentatives;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.EX_2ND_PASSWORD_VERIFY);

        writeInt(_mode);
        writeInt(_wrongTentatives);
    }

}
