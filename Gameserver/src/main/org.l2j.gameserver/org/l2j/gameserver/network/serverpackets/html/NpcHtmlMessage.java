package org.l2j.gameserver.network.serverpackets.html;

import org.l2j.gameserver.enums.HtmlActionScope;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

/**
 * NpcHtmlMessage server packet implementation.
 *
 * @author HorridoJoho
 */
public final class NpcHtmlMessage extends AbstractHtmlPacket {
    private final int _itemId;

    public NpcHtmlMessage() {
        _itemId = 0;
    }

    public NpcHtmlMessage(int npcObjId) {
        super(npcObjId);
        _itemId = 0;
    }

    public NpcHtmlMessage(String html) {
        super(html);
        _itemId = 0;
    }

    public NpcHtmlMessage(int npcObjId, String html) {
        super(npcObjId, html);
        _itemId = 0;
    }

    public NpcHtmlMessage(int npcObjId, int itemId) {
        super(npcObjId);

        if (itemId < 0) {
            throw new IllegalArgumentException();
        }

        _itemId = itemId;
    }

    public NpcHtmlMessage(int npcObjId, int itemId, String html) {
        super(npcObjId, html);

        if (itemId < 0) {
            throw new IllegalArgumentException();
        }

        _itemId = itemId;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.NPC_HTML_MESSAGE);

        writeInt(getNpcObjId());
        writeString(getHtml());
        writeInt(_itemId);
        writeInt(0x00); // TODO: Find me!
    }

    @Override
    public HtmlActionScope getScope() {
        return _itemId == 0 ? HtmlActionScope.NPC_HTML : HtmlActionScope.NPC_ITEM_HTML;
    }

}
