package de.ricepuffz.ecosim.engine.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {
    public enum Size {
        SIZE16,
        SIZE32,
        SIZE100
    }

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

    public BitmapFont get(Size size) {
        switch (size) {
            case SIZE16:
                return get16();
            case SIZE32:
                return get32();
            case SIZE100:
                return get100();
            default:
                return null;
        }
    }
}
