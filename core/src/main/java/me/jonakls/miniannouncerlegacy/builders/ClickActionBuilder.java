package me.jonakls.miniannouncerlegacy.builders;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ClickActionBuilder {

    private final TextComponent component = new TextComponent();

    public ClickActionBuilder setText(String text) {
        component.setText(text);
        return this;
    }

    public ClickActionBuilder setText(BaseComponent component) {
        component.addExtra(component);
        return this;
    }

    public ClickActionBuilder setClick(String type, String text) {
        component.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(type.toUpperCase()), text));
        return this;
    }

    public TextComponent build() {
        return component;
    }
}
