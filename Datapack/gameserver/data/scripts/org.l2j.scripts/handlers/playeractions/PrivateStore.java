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
package handlers.playeractions;


import org.l2j.gameserver.enums.PrivateStoreType;
import org.l2j.gameserver.handler.IPlayerActionHandler;
import org.l2j.gameserver.data.xml.model.ActionData;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.world.zone.ZoneType;
import org.l2j.gameserver.network.SystemMessageId;
import org.l2j.gameserver.network.serverpackets.ActionFailed;
import org.l2j.gameserver.network.serverpackets.PrivateStoreManageListBuy;
import org.l2j.gameserver.network.serverpackets.PrivateStoreManageListSell;
import org.l2j.gameserver.network.serverpackets.RecipeShopManageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Open/Close private store player action handler.
 * @author Nik
 */
public final class PrivateStore implements IPlayerActionHandler
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateStore.class);
	
	@Override
	public void useAction(Player activeChar, ActionData data, boolean ctrlPressed, boolean shiftPressed)
	{
		final PrivateStoreType type = PrivateStoreType.findById(data.getOptionId());
		if (type == null)
		{
			LOGGER.warn("Incorrect private store type: " + data.getOptionId());
			return;
		}
		
		// Player shouldn't be able to set stores if he/she is alike dead (dead or fake death)
		if (!activeChar.canOpenPrivateStore())
		{
			if (activeChar.isInsideZone(ZoneType.NO_STORE))
			{
				activeChar.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE);
			}
			
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		switch (type)
		{
			case SELL:
			case SELL_MANAGE:
			case PACKAGE_SELL:
			{
				if ((activeChar.getPrivateStoreType() == PrivateStoreType.SELL) || (activeChar.getPrivateStoreType() == PrivateStoreType.SELL_MANAGE) || (activeChar.getPrivateStoreType() == PrivateStoreType.PACKAGE_SELL))
				{
					activeChar.setPrivateStoreType(PrivateStoreType.NONE);
				}
				break;
			}
			case BUY:
			case BUY_MANAGE:
			{
				if ((activeChar.getPrivateStoreType() == PrivateStoreType.BUY) || (activeChar.getPrivateStoreType() == PrivateStoreType.BUY_MANAGE))
				{
					activeChar.setPrivateStoreType(PrivateStoreType.NONE);
				}
				break;
			}
			case MANUFACTURE:
			{
				activeChar.setPrivateStoreType(PrivateStoreType.NONE);
				activeChar.broadcastUserInfo();
			}
		}
		
		if (activeChar.getPrivateStoreType() == PrivateStoreType.NONE)
		{
			if (activeChar.isSitting())
			{
				activeChar.standUp();
			}
			
			switch (type)
			{
				case SELL:
				case SELL_MANAGE:
				case PACKAGE_SELL:
				{
					activeChar.setPrivateStoreType(PrivateStoreType.SELL_MANAGE);
					activeChar.sendPacket(new PrivateStoreManageListSell(1, activeChar, type == PrivateStoreType.PACKAGE_SELL));
					activeChar.sendPacket(new PrivateStoreManageListSell(2, activeChar, type == PrivateStoreType.PACKAGE_SELL));
					break;
				}
				case BUY:
				case BUY_MANAGE:
				{
					activeChar.setPrivateStoreType(PrivateStoreType.BUY_MANAGE);
					activeChar.sendPacket(new PrivateStoreManageListBuy(1, activeChar));
					activeChar.sendPacket(new PrivateStoreManageListBuy(2, activeChar));
					break;
				}
				case MANUFACTURE:
				{
					activeChar.sendPacket(new RecipeShopManageList(activeChar, true));
				}
			}
		}
	}
}
