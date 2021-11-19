package me.jonakls.miniannouncerlegacy.builders;

import me.jonakls.api.Title;

public class TitleBuilder {

    private String title;
    private String subTitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TitleBuilder setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public TitleBuilder setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public TitleBuilder setStay(int stay) {
        this.stay = stay;
        return this;
    }

    public TitleBuilder setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    public Title build() {
        return new Title(
          title, subTitle, fadeIn, stay, fadeOut
        );
    }
}
