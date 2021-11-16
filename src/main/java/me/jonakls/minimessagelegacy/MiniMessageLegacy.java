package me.jonakls.minimessagelegacy;

import me.jonakls.minimessagelegacy.commands.MainCommand;
import me.jonakls.minimessagelegacy.managers.ActionsManager;
import me.jonakls.minimessagelegacy.managers.AnnouncerManager;
import me.jonakls.minimessagelegacy.module.BinderModule;
import me.yushust.inject.Injector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public final class MiniMessageLegacy extends JavaPlugin {

    private static MiniMessageLegacy instance;
    private BukkitAudiences adventure;

    @Inject
    private ActionsManager actionsManager;

    @Inject
    private AnnouncerManager announcerManager;

    @Inject
    private MainCommand mainCommand;

    @Override
    public void onLoad() {
        instance = this;

    }

    @Override
    public void onEnable() {
        adventure = BukkitAudiences.create(this);
        setupInjector();
        getCommand("miniannouncer").setExecutor(mainCommand);
        announcerManager.initTask();

    }

    @Override
    public void onDisable() {
        announcerManager.stopTask();
        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }

    private void setupInjector() {
        Injector injector = Injector.create(new BinderModule(this));
        injector.injectMembers(this);
    }

    public static MiniMessageLegacy getInstance() {
        return instance;
    }

    public BukkitAudiences getAdventure() {
        return adventure;
    }

    public ActionsManager getActionsManager() {
        return actionsManager;
    }

    public AnnouncerManager getAnnouncerManager() {
        return announcerManager;
    }
}
