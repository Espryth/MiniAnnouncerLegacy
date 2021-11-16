package me.jonakls.miniannouncerlegacy.managers;

import me.jonakls.miniannouncerlegacy.utils.AdventureUtil;
import me.jonakls.miniannouncerlegacy.utils.ChatUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ActionsManager {

    public void execute(Player player, String line) {
        String actionType = StringUtils.substringBetween(line, "[", "]").toUpperCase();

        switch (actionType) {
            default:
            case "MESSAGE":
                AdventureUtil.sendMessage(player, ChatUtil.toMiniMessage(line.substring(9)));
                break;
            case "ACTIONBAR":
                AdventureUtil.sendActionBar(player, ChatUtil.toMiniMessage(line.substring(11)));
                break;
            case "TITLE":
                String[] title = line.substring(7).split(";");
                AdventureUtil.showTitle(player, title);
                break;
            case "SOUND":
                String[] sound = line.substring(7).trim().split(";");
                player.playSound(
                        player.getLocation(),
                        Sound.valueOf(sound[0].trim()),
                        Float.parseFloat(sound[1]),
                        Float.parseFloat(sound[2])
                );
                break;
        }
    }



}
