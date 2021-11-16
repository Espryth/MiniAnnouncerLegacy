package me.jonakls.minimessagelegacy.module;

import me.jonakls.minimessagelegacy.MiniMessageLegacy;
import me.jonakls.minimessagelegacy.configuration.Configuration;
import me.yushust.inject.AbstractModule;

public class BinderModule extends AbstractModule {

    private MiniMessageLegacy plugin;

    public BinderModule(MiniMessageLegacy plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Configuration.class).named("config").toInstance(new Configuration(plugin, "config.yml"));
    }
}
