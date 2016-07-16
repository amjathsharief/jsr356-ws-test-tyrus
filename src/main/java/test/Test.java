package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class Test {
	public static void main(String[] args) {
		try {
			final ClientEndpointConfig cec = ClientEndpointConfig.Builder
					.create().build();
			ClientManager client = ClientManager.createClient();
			client.connectToServer(
					new Endpoint() {
						@Override
						public void onOpen(Session session,
								EndpointConfig config) {
							System.out.println("Session Id : "
									+ session.getId());
							session.addMessageHandler(new MessageHandler.Whole<String>() {
								public void onMessage(String message) {
									System.out.print(message);
								}
							});
						}
					},
					cec,
					new URI(
							"ws://stream.meetup.com/2/rsvps?key=b3b453b15e7d4d2f405e546f20725e"));

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Please press a key to stop the program.");
			reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
