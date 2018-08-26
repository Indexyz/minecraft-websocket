package io.github.indexyz.minecraft.websocket;

import com.google.inject.Inject;
import io.github.indexyz.minecraft.websocket.utils.ConfigModel;
import io.github.indexyz.minecraft.websocket.utils.Configure;
import io.github.indexyz.minecraft.websocket.listners.PlayerChat;
import io.github.indexyz.minecraft.websocket.utils.Server;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(id = "bridge", name = "Bridge Plugin", version = "0.0.0")
public class Main {
    private static Main instance;
    @Inject
    private Logger logger;

    public static Main getInstance() {
        return instance;
    }

    private ConfigModel config;

    @Inject
    @ConfigDir(sharedRoot = true)
    private Path configDir;

    private Server server;

    public ConfigModel getConfig() {
        return config;
    }

    public Logger getLogger() {
        return logger;
    }

    public Path getConfigDir() {
        return configDir;
    }

    public Server getServer() {
        return server;
    }

    @Listener
    public void onInitialization(GameInitializationEvent event) throws IOException, ObjectMappingException {
        instance = this;
        config = Configure.loadConfig();
        Sponge.getEventManager().registerListeners(this, new PlayerChat());
        server = new Server(config.listenPort);

        server.start();
    }
}
