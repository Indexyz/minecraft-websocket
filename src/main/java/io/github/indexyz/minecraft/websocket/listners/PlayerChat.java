package io.github.indexyz.minecraft.websocket.listners;

import io.github.indexyz.minecraft.websocket.Main;
import org.json.JSONObject;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.message.MessageChannelEvent;

import java.util.Optional;


public class PlayerChat {
    @Listener(order = Order.LATE)
    public void onChat(MessageChannelEvent.Chat event) {
        if (event.isCancelled() || event.isMessageCancelled()) {
            return;
        }

        String text = event.getRawMessage()
                .toPlain().trim();
        Optional<Player> player = event.getCause().first(Player.class);

        if (!player.isPresent()) {
            return;
        }

        JSONObject object = new JSONObject();
        object.put("sender", player.get().getName());
        object.put("message", text);

        Main.getInstance().getServer().broadcast(object.toString());
    }
}
