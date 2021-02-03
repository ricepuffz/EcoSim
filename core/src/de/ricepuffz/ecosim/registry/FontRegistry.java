package de.ricepuffz.ecosim.registry;

import com.badlogic.gdx.graphics.Texture;
import de.ricepuffz.ecosim.font.Font;

import java.util.HashMap;
import java.util.Map;

public class FontRegistry {
    private static Map<String, Font> fonts = new HashMap<>();


    private FontRegistry() { }


    public static void registerFont(Font font) {
        fonts.put(font.getName(), font);
    }


    public static Font getFont(String name) {
        return fonts.get(name);
    }


    public static void registerStandardFonts() {
        registerFont(new Font("arial"));
    }
}
