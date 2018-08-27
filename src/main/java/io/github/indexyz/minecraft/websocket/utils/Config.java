package io.github.indexyz.minecraft.websocket.utils;

import io.github.indexyz.minecraft.websocket.Main;
import net.minecraftforge.common.config.Property;

public class Config {
    public static int port = 5000;
    public static String token = "Indexyz";

    private final static String WEBSOCKET_CATEGORY = "websocket";

    public static void init() {
        try {
            Main.configuration.load();
            Main.configuration.getCategory(Config.WEBSOCKET_CATEGORY);
            Property tokenProp = Main.configuration.get(Config.WEBSOCKET_CATEGORY, "token",
                    "Indexyz", "Websocket need this to verify client");

            Property portProp = Main.configuration.get(Config.WEBSOCKET_CATEGORY, "port",
                    5000, "Websocket Callback server start at this port");


            Config.port = portProp.getInt();
            Config.token = tokenProp.getString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Main.configuration.hasChanged()) {
                Main.configuration.save();
            }
        }
    }
}
