package io.github.indexyz.minecraft.websocket.utils;

import com.google.common.reflect.TypeToken;
import io.github.indexyz.minecraft.websocket.Main;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.gson.GsonConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configure {
    public static ConfigModel loadConfig() throws ObjectMappingException, IOException {
        Main mod = Main.getInstance();
        Logger logger = mod.getLogger();
        Path configDir = mod.getConfigDir();

        if (!Files.exists(configDir)) {
            Files.createDirectories(configDir);
        }

        Path configFile = Paths.get(configDir + "/bridge.json");

        GsonConfigurationLoader configurationLoader = GsonConfigurationLoader.builder()
                .setPath(configFile)
                .build();

        ConfigurationNode configurationNode = configurationLoader.load();

        ConfigModel config = configurationNode.getValue(
                TypeToken.of(ConfigModel.class), new ConfigModel());

        if (!Files.exists(configFile)) {
            Files.createFile(configFile);
            logger.info("config file created");
            configurationNode.setValue(TypeToken.of(ConfigModel.class), config);
            configurationLoader.save(configurationNode);
        }
        return config;
    }
}
