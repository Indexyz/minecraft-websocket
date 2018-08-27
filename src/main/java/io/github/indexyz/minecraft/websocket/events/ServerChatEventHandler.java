package io.github.indexyz.minecraft.websocket.events;

import io.github.indexyz.minecraft.websocket.Main;
import io.github.indexyz.minecraft.websocket.utils.Message;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ServerChatEventHandler {
    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event) {
        EntityPlayerMP sender = event.getPlayer();
        Main.server.broadcast(Message.playerMessage(sender, event.getMessage()));
    }
}