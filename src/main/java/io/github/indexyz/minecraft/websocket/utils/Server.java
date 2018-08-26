package io.github.indexyz.minecraft.websocket.utils;

import io.github.indexyz.minecraft.websocket.Main;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) { }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) { }

    @Override
    public void onMessage(WebSocket conn, String message) {
        JSONObject obj = new JSONObject(message);
        String result = obj.getString("sender") + ": " + obj.getString("message");

        Sponge.getServer().getBroadcastChannel().send(Text.of(result));
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Main.getInstance().getLogger().error(ex.getMessage());
    }

    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer
            (WebSocket conn, Draft draft, ClientHandshake request)
            throws InvalidDataException {
        ServerHandshakeBuilder builder = super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
        if (! request.getResourceDescriptor().equals("/" + Main.getInstance().getConfig().chatKey)) {
            throw new InvalidDataException(CloseFrame.POLICY_VALIDATION, "Not accepted!");
        }
        return builder;
    }

    @Override
    public void onStart() {
        Main.getInstance().getLogger().info("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
