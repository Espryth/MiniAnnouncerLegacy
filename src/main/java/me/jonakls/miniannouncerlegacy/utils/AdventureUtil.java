package me.jonakls.miniannouncerlegacy.utils;

import me.jonakls.miniannouncerlegacy.MiniAnnouncerLegacy;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;

public class AdventureUtil {

    private static BukkitAudiences adventure = MiniAnnouncerLegacy.getInstance().getAdventure();

    public static void showTitle(Player player, String... title) {
        adventure.player(player).showTitle(
                Title.title(
                        ChatUtil.toMiniMessage(title[0]),
                        ChatUtil.toMiniMessage(title[1]),
                        Title.Times.of(
                                Duration.ofSeconds(Long.parseLong(title[2])),
                                Duration.ofSeconds(Long.parseLong(title[3])),
                                Duration.ofSeconds(Long.parseLong(title[4]))
                        )
                )

        );
    }

    public static void sendMessage(Player player, Component component) {
        adventure.player(player).sendMessage(component);
    }

    public static void sendActionBar(Player player, Component component) {
        adventure.player(player).sendActionBar(component);
    }
}
