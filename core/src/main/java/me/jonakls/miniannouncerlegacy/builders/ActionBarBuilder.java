package me.jonakls.miniannouncerlegacy.builders;

import me.jonakls.api.ActionBar;

public class ActionBarBuilder {

    private String text;

    public ActionBarBuilder setActionBar(String text) {
        this.text = text;
        return this;
    }

    public ActionBar build() {
        return new ActionBar(text);
    }

}
