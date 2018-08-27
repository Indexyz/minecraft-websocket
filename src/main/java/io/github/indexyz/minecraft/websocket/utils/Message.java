package io.github.indexyz.minecraft.websocket.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import org.json.JSONObject;

public class Message {
    public static String playerMessage(EntityPlayerMP player, String message) {
        JSONObject object = new JSONObject();
        object.put("sender", player);
        object.put("message", message);
        object.put("type", "chat");
        return object.toString();
    }

    public static String systemMessage(String message) {
        JSONObject object = new JSONObject();
        object.put("sender", "system");
        object.put("message", message);
        object.put("type", "system");
        return object.toString();
    }
}
