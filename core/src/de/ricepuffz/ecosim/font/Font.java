package de.ricepuffz.ecosim.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {
    private String name;

    private BitmapFont size16;
    private BitmapFont size32;
    private BitmapFont size100;

    public Font(String name) {
        this.name = name;

        size16 = new BitmapFont(Gdx.files.internal("fonts/" + name + "/" + name + "_16.fnt"));
        size32 = new BitmapFont(Gdx.files.internal("fonts/" + name + "/" + name + "_32.fnt"));
        size100 = new BitmapFont(Gdx.files.internal("fonts/" + name + "/" + name + "_100.fnt"));
    }

    public String getName() {
        return name;
    }

    public BitmapFont get16() {
        return size16;
    }

    public BitmapFont get32() {
        return size32;
    }

    public BitmapFont get100() {
        return size100;
    }
}
