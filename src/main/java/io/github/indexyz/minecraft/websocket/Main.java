package io.github.indexyz.minecraft.websocket;

import io.github.indexyz.minecraft.websocket.utils.Config;
import io.github.indexyz.minecraft.websocket.utils.Server;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MOD_ID, name = Main.NAME, version = Main.VERSION,
        acceptedMinecraftVersions = "1.10.2", serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main {
    static final String MOD_ID = "minecraft-websocket";
    static final String NAME = "Minecraft Websocket";
    static final String VERSION = "@version@";

    public static Configuration configuration;
    public static Logger logger;

    public static Server server;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Main.configuration = new Configuration(event.getSuggestedConfigurationFile());
        Config.init();
    }

    @Mod.EventHandler
    public void onStarted(FMLServerStartedEvent event) {
        server = new Server(Config.port);
        server.start();
    }
}
