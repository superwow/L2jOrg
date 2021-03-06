package org.l2j.authserver.network.client.packet.client2auth;

import org.l2j.authserver.controller.AuthController;
import org.l2j.commons.network.SessionKey;
import org.l2j.authserver.network.client.packet.AuthClientPacket;
import org.l2j.authserver.network.client.packet.auth2client.PlayOk;

import static org.l2j.authserver.network.client.packet.auth2client.LoginFail.LoginFailReason.REASON_ACCESS_FAILED;
import static org.l2j.authserver.network.client.packet.auth2client.PlayFail.PlayFailReason.REASON_TOO_MANY_PLAYERS;

/**
 * Fromat is ddc d: first part of session id d: second part of session id c: server ID
 */
public class RequestServerLogin extends AuthClientPacket {

	private int accountId;
	private int authKey;
	private int _serverId;

	@Override
	public boolean readImpl() {
		if (available() >= 9) {
			accountId = readInt();
			authKey = readInt();
			_serverId = Byte.toUnsignedInt(readByte());
			return true;
		}
		return false;
	}
	
	@Override
	public void run() {
		SessionKey sk = client.getSessionKey();

		if (sk.checkLoginPair(accountId, authKey)) {
			if (AuthController.getInstance().isLoginPossible(getClient(), _serverId)) {
			    client.joinGameserver();
				client.sendPacket(new PlayOk(_serverId));
			} else  {
				client.close(REASON_TOO_MANY_PLAYERS);
			}
		} else {
			client.close(REASON_ACCESS_FAILED);
		}
	}
}
