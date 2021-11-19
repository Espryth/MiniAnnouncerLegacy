package me.jonakls.miniannouncerlegacy.utils;

import me.jonakls.api.ActionBar;
import me.jonakls.api.Title;
import me.jonakls.miniannouncerlegacy.builders.ActionBarBuilder;
import me.jonakls.miniannouncerlegacy.builders.ClickActionBuilder;
import me.jonakls.miniannouncerlegacy.builders.HoverBuilder;
import me.jonakls.miniannouncerlegacy.builders.TitleBuilder;
import net.md_5.bungee.api.chat.TextComponent;
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

    public static Title serializeTitle(String... strings) {
        return new TitleBuilder()
                .setTitle(toLegacyColors(strings[0]))
                .setSubTitle(toLegacyColors(strings[1]))
                .setFadeIn(Integer.parseInt(strings[2]))
                .setStay(Integer.parseInt(strings[3]))
                .setFadeOut(Integer.parseInt(strings[4]))
                .build();
    }

    public static ActionBar serializeActionBar(String text) {
        return new ActionBarBuilder().setActionBar(toLegacyColors(text)).build();
    }

    public static TextComponent serializeHover(String... strings) {
        return new HoverBuilder()
                .setHover(strings[1], strings[0])
                .setText(toLegacyColors(strings[2]))
                .build();
    }

    public static TextComponent serializeClick(String... strings) {
        return new ClickActionBuilder()
                .setClick(strings[0], strings[1])
                .setText(toLegacyColors(strings[2]))
                .build();
    }
}
