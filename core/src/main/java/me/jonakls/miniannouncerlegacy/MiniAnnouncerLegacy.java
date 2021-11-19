package me.jonakls.miniannouncerlegacy;

import me.jonakls.api.NMS;
import me.jonakls.miniannouncerlegacy.api.ServerVersion;
import me.jonakls.miniannouncerlegacy.commands.MainCommand;
import me.jonakls.miniannouncerlegacy.managers.ActionsManager;
import me.jonakls.miniannouncerlegacy.managers.AnnouncerManager;
import me.jonakls.miniannouncerlegacy.module.BinderModule;
import me.yushust.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import javax.inject.Inject;

public final class MiniAnnouncerLegacy extends JavaPlugin {

    private static MiniAnnouncerLegacy instance;
    private NMS component;

    @Inject
    private ActionsManager actionsManager;

    @Inject
    private AnnouncerManager announcerManager;

    @Inject
    private MainCommand mainCommand;

    @Override
    public void onEnable() {
        instance = this;

        ServerVersion version = new ServerVersion();
        component = version.version();

        setupInjector();
        getCommand("miniannouncer").setExecutor(mainCommand);
        announcerManager.initTask();

    }

    @Override
    public void onDisable() {
        announcerManager.stopTask();
    }

    private void setupInjector() {
        Injector injector = Injector.create(new BinderModule(this));
        injector.injectMembers(this);
    }

    public static MiniAnnouncerLegacy getInstance() {
        return instance;
    }

    public ActionsManager getActionsManager() {
        return actionsManager;
    }

    public AnnouncerManager getAnnouncerManager() {
        return announcerManager;
    }

    public NMS getComponent() {
        return component;
    }
}
