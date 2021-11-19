package me.jonakls.miniannouncerlegacy.api;

import me.jonakls.api.NMS;
import me.jonakls.version.Version1_17_R1;
import me.jonakls.version.Version1_8_R3;
import org.bukkit.Bukkit;

public class ServerVersion {

    public NMS version() {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        if (version.contains("1_8")) {
            return new Version1_8_R3();
        }
        return new Version1_17_R1();
    }

}
