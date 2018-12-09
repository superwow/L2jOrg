package org.l2j.gameserver.network.l2.c2s;

import java.util.List;

import org.l2j.commons.lang.ArrayUtils;
import org.l2j.gameserver.dao.MailDAO;
import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.mail.Mail;
import org.l2j.gameserver.network.l2.s2c.ExShowReceivedPostList;

/**
 * Запрос на удаление полученных сообщений. Удалить можно только письмо без вложения. Отсылается при нажатии на "delete" в списке полученных писем.
 * @see ExShowReceivedPostList
 * @see RequestExDeleteSentPost
 */
public class RequestExDeleteReceivedPost extends L2GameClientPacket
{
	private int _count;
	private int[] _list;

	/**
	 * format: dx[d]
	 */
	@Override
	protected void readImpl()
	{
		_count = readInt();
		if(_count * 4 > availableData() || _count > Short.MAX_VALUE || _count < 1)
		{
			_count = 0;
			return;
		}
		_list = new int[_count]; // количество элементов для удаления
		for(int i = 0; i < _count; i++)
			_list[i] = readInt(); // уникальный номер письма
	}

	@Override
	protected void runImpl()
	{
		Player activeChar = getClient().getActiveChar();
		if(activeChar == null || _count == 0)
			return;

		List<Mail> mails = MailDAO.getInstance().getReceivedMailByOwnerId(activeChar.getObjectId());
		if(!mails.isEmpty())
		{
			for(Mail mail : mails)
				if(ArrayUtils.contains(_list, mail.getMessageId()))
					if(mail.getAttachments().isEmpty())
					{
						MailDAO.getInstance().deleteReceivedMailByMailId(activeChar.getObjectId(), mail.getMessageId());
					}
		}

		activeChar.sendPacket(new ExShowReceivedPostList(activeChar));
	}
}