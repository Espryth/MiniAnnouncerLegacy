package me.jonakls.minimessagelegacy.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static String toLegacyColors(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void toArraySender(CommandSender sender, String... texts) {
        for (String line : texts) {
            sender.sendMessage(toLegacyColors(line));
        }
    }

    public static Component toMiniMessage(String text) {
        return MiniMessage.get().parse(text);
    }

}
