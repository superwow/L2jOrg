/*
 * This file is part of the L2J Mobius project.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.model.entity.Message;
import org.l2j.gameserver.model.itemcontainer.ItemContainer;
import org.l2j.gameserver.model.items.instance.Item;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author Migi, DS
 */
public class ExReplySentPost extends AbstractItemPacket {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExReplySentPost.class);

    private final Message _msg;
    private Collection<Item> _items = null;

    public ExReplySentPost(Message msg) {
        _msg = msg;
        if (msg.hasAttachments()) {
            final ItemContainer attachments = msg.getAttachments();
            if ((attachments != null) && (attachments.getSize() > 0)) {
                _items = attachments.getItems();
            } else {
                LOGGER.warn("Message " + msg.getId() + " has attachments but itemcontainer is empty.");
            }
        }
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.EX_REPLY_SENT_POST);

        writeInt(0x00); // GOD
        writeInt(_msg.getId());
        writeInt(_msg.isLocked());
        writeString(_msg.getReceiverName());
        writeString(_msg.getSubject());
        writeString(_msg.getContent());

        if ((_items != null) && !_items.isEmpty()) {
            writeInt(_items.size());
            for (Item item : _items) {
                writeItem(item);
                writeInt(item.getObjectId());
            }
        } else {
            writeInt(0x00);
        }
        writeLong(_msg.getReqAdena());
        writeInt(_msg.hasAttachments());
        writeInt(_msg.isReturned());
    }
}
