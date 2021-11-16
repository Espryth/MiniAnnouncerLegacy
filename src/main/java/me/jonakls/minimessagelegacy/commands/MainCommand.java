package me.jonakls.minimessagelegacy.commands;

import me.jonakls.minimessagelegacy.MiniMessageLegacy;
import me.jonakls.minimessagelegacy.configuration.Configuration;
import me.jonakls.minimessagelegacy.utils.ChatUtil;
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
                MiniMessageLegacy.getInstance().getAnnouncerManager().initTask();
                sender.sendMessage(ChatUtil.toLegacyColors("&aStart all announcements"));
                break;
            case "disable":
                config.set("announcer.enabled", false);
                config.reloadFile();
                MiniMessageLegacy.getInstance().getAnnouncerManager().stopTask();
                sender.sendMessage(ChatUtil.toLegacyColors("&aStop all announcements"));
                break;
            case "reload":
                MiniMessageLegacy.getInstance().getAnnouncerManager().stopTask();
                config.reloadFile();
                sender.sendMessage(ChatUtil.toLegacyColors("&aPlugin has been reloaded"));
                MiniMessageLegacy.getInstance().getAnnouncerManager().initTask();
                break;
            case "info":
                ChatUtil.toArraySender(
                        sender,
                        "&6MiniAnnouncer &8- &cv" + MiniMessageLegacy.getInstance().getDescription().getVersion(),
                        "&r",
                        "&eMade by: &c" + MiniMessageLegacy.getInstance().getDescription().getAuthors()
                );
                break;
            default:
                ChatUtil.toArraySender(
                        sender,
                        "&6MiniAnnouncer &8- &cv" + MiniMessageLegacy.getInstance().getDescription().getVersion(),
                        "&r",
                        "&eMade by: &c" + MiniMessageLegacy.getInstance().getDescription().getAuthors(),
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
