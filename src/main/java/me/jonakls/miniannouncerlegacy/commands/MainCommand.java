package me.jonakls.miniannouncerlegacy.commands;

import me.jonakls.miniannouncerlegacy.MiniAnnouncerLegacy;
import me.jonakls.miniannouncerlegacy.configuration.Configuration;
import me.jonakls.miniannouncerlegacy.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;
import javax.inject.Named;

public class MainCommand implements CommandExecutor {

    @Inject
    @Named("config")
    private Configuration config;

    @Override
    public boolean onCommand(CommandSender sender,Command command ,String label ,String[] args ) {

        if (!sender.hasPermission("miniannouncer.commands")) {
            sender.sendMessage(ChatUtil.toLegacyColors("&cNo permissions!"));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatUtil.toLegacyColors("&cUnknown Command"));
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "enable":
                config.set("announcer.enabled", true);
                config.reloadFile();
                MiniAnnouncerLegacy.getInstance().getAnnouncerManager().initTask();
                sender.sendMessage(ChatUtil.toLegacyColors("&aStart all announcements"));
                break;
            case "disable":
                config.set("announcer.enabled", false);
                config.reloadFile();
                MiniAnnouncerLegacy.getInstance().getAnnouncerManager().stopTask();
                sender.sendMessage(ChatUtil.toLegacyColors("&aStop all announcements"));
                break;
            case "reload":
                MiniAnnouncerLegacy.getInstance().getAnnouncerManager().stopTask();
                config.reloadFile();
                sender.sendMessage(ChatUtil.toLegacyColors("&aPlugin has been reloaded"));
                MiniAnnouncerLegacy.getInstance().getAnnouncerManager().initTask();
                break;
            case "info":
                ChatUtil.toArraySender(
                        sender,
                        "&6MiniAnnouncer &8- &cv" + MiniAnnouncerLegacy.getInstance().getDescription().getVersion(),
                        "&r",
                        "&eMade by: &c" + MiniAnnouncerLegacy.getInstance().getDescription().getAuthors()
                );
                break;
            default:
                ChatUtil.toArraySender(
                        sender,
                        "&6MiniAnnouncer &8- &cv" + MiniAnnouncerLegacy.getInstance().getDescription().getVersion(),
                        "&r",
                        "&eMade by: &c" + MiniAnnouncerLegacy.getInstance().getDescription().getAuthors(),
                        "&e/ma - /minia - /announcer - /miniannouncer",
                        "&r",
                        "&a/ma reload &8| &eReload plugin",
                        "&a/ma info &8| &eShow plugin info",
                        "&a/ma help &8| &eShow this message"
                );
                break;
        }
        return true;
    }
}
