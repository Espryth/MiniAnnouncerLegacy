package me.jonakls.miniannouncerlegacy.managers;

import me.jonakls.miniannouncerlegacy.MiniAnnouncerLegacy;
import me.jonakls.miniannouncerlegacy.configuration.Configuration;
import me.jonakls.miniannouncerlegacy.utils.PlaceholderUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Random;

public class AnnouncerManager {

    @Inject
    @Named("config")
    private Configuration config;

    private int i = 0;
    private BukkitTask task;

    public void announce() {
        ActionsManager actions = MiniAnnouncerLegacy.getInstance().getActionsManager();
        Random random = new Random();

        // If announcer is disabled from config, announcer will not be sent.
        if (!config.getBoolean("announcer.enabled")) return;

        List<String> announcements = config.getStringList("interval-announcement");

        if (config.getBoolean("announcer.random")) {
            List<String> announcement = config.getStringList(
                    "announcements." + announcements.get(random.nextInt(announcements.size()))
            );
            for (String line : announcement) {
                Bukkit.getOnlinePlayers()
                        .forEach(player -> actions.execute(player, PlaceholderUtil.placeholder(player, line)));
            }
            return;
        }

        if (i != announcements.size()) {
            for (String line : config.getStringList("announcements." + announcements.get(i))) {
                Bukkit.getOnlinePlayers()
                        .forEach(player -> actions.execute(player, PlaceholderUtil.placeholder(player, line)));
            }
            i++;
            return;
        }
        i = 0;
    }

    public void initTask() {
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(
                MiniAnnouncerLegacy.getInstance(),
                this::announce,
                0L,
                20L * config.getInt("announcer.interval")
        );
    }

    public void stopTask() {
        if (task != null) {
            this.task.cancel();
        }
    }


}
