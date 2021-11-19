package me.jonakls.miniannouncerlegacy.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatSerialize {

    public static void sendComponent(Player player, String message) {
        String label = StringUtils.substringBetween(message, "<", ">");

        if (label == null) {
            player.sendMessage(ChatUtil.toLegacyColors(message));
            return;
        }

        String[] types = label.split(":");
        for(String line : types) {
            Bukkit.getLogger().info("Types: " + line);
        }

        switch (types[0].toUpperCase()) {
            case "HOVER":
                player.spigot().sendMessage(ChatUtil.serializeHover(types[1], types[2], types[3]));
                break;
            case "CLICK":
                player.spigot().sendMessage(ChatUtil.serializeClick(types[1], types[2], types[3]));
            default:
                player.sendMessage(ChatUtil.toLegacyColors(message));

        }
    }

}
