package org.l2j.gameserver.network.serverpackets.compound;

import io.github.joealisson.mmocore.StaticPacket;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;
import org.l2j.gameserver.network.serverpackets.ServerPacket;

/**
 * @author UnAfraid
 */
@StaticPacket
public class ExEnchantOneFail extends ServerPacket {
    public static final ExEnchantOneFail STATIC_PACKET = new ExEnchantOneFail();

    private ExEnchantOneFail() {
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.EX_ENCHANT_ONE_FAIL);
    }

}
