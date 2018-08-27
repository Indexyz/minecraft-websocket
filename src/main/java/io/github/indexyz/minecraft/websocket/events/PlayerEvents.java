package io.github.indexyz.minecraft.websocket.events;

import io.github.indexyz.minecraft.websocket.Main;
import io.github.indexyz.minecraft.websocket.utils.Message;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Main.server.broadcast(
            Message.systemMessage(event.player.getDisplayNameString() + " joined the server")
        );
    }

    @SubscribeEvent
    public static void playerLeft(PlayerEvent.PlayerLoggedOutEvent event) {
        Main.server.broadcast(
            Message.systemMessage(event.player.getDisplayNameString() + " left the server")
        );
    }

    @SubscribeEvent
    public static void playerDeadEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            String message = event.getSource().getDeathMessage(event.getEntityLiving()).getUnformattedText();
            Main.server.broadcast(
                Message.systemMessage(
                    String.format(message, ((EntityPlayer) event.getEntity()).getDisplayNameString())
                )
            );
        }
    }
}