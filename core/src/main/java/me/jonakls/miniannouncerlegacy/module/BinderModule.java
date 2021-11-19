package me.jonakls.miniannouncerlegacy.module;

import me.jonakls.miniannouncerlegacy.MiniAnnouncerLegacy;
import me.jonakls.miniannouncerlegacy.configuration.Configuration;
import me.yushust.inject.AbstractModule;

public class BinderModule extends AbstractModule {

    private MiniAnnouncerLegacy plugin;

    public BinderModule(MiniAnnouncerLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Configuration.class).named("config").toInstance(new Configuration(plugin, "config.yml"));
    }
}
