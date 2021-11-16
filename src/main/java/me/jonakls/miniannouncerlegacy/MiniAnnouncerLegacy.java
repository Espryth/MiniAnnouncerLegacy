package me.jonakls.miniannouncerlegacy;

import me.jonakls.miniannouncerlegacy.commands.MainCommand;
import me.jonakls.miniannouncerlegacy.managers.ActionsManager;
import me.jonakls.miniannouncerlegacy.managers.AnnouncerManager;
import me.jonakls.miniannouncerlegacy.module.BinderModule;
import me.yushust.inject.Injector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public final class MiniAnnouncerLegacy extends JavaPlugin {

    private static MiniAnnouncerLegacy instance;
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

    public static MiniAnnouncerLegacy getInstance() {
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
