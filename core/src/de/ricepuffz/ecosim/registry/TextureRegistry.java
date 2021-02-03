package de.ricepuffz.ecosim.registry;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureRegistry {
    private static Map<String, Texture> textures = new HashMap<>();


    private TextureRegistry() { }


    public static void registerTexture(Texture texture, String name) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textures.put(name, texture);
    }


    public static Texture getTexture(String name) {
        return textures.get(name);
    }

    public static void dispose() {
        for (String name : (String[]) textures.keySet().toArray()) {
            textures.get(name).dispose();
            textures.remove(name);
        }
    }


    public static void registerStandardTextures() {
        registerTexture(new Texture("textures/hecc.png"), "hecc");
        registerTexture(new Texture("textures/pixel.png"), "pixel");
    }
}
