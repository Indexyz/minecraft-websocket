package io.github.indexyz.minecraft.websocket.utils;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class ConfigModel {
    @Setting(value = "listenPort", comment = "The Port that you wan't to listen")
    public int listenPort = 5000;

    @Setting(value = "chatKey", comment = "Key for join websocket")
    public String chatKey = "Indexyz";
}
