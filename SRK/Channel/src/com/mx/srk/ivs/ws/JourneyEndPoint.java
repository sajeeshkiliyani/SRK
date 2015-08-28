package com.mx.srk.ivs.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author sajeesh
 */
@ServerEndpoint("/journeyendpoint")
public class JourneyEndPoint {

	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session peer) {
		peers.add(peer);
	}

	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, EncodeException {
		for (Session otherSession : peers) {
			if (!otherSession.equals(session)) {
				otherSession.getBasicRemote().sendText(message);
			}
		}
	}

	public static void updateAll(String message) throws IOException {
		for (Session otherSession : peers) {
			otherSession.getBasicRemote().sendText(message);
		}
	}

}
