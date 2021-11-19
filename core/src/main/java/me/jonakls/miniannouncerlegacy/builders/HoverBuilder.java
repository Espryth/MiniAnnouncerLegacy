package me.jonakls.miniannouncerlegacy.builders;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class HoverBuilder {

    private final TextComponent component = new TextComponent();

    public HoverBuilder setText(String text) {
        component.setText(text);
        return this;
    }

    public HoverBuilder setHover(String text, String type) {
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.valueOf(type.toUpperCase()), new ComponentBuilder(text).create()));
        return this;
    }

    public TextComponent build() {
        return component;
    }

}
