package de.ricepuffz.ecosim.engine.registry;

import de.ricepuffz.ecosim.engine.font.Font;

import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static Map<String, Font> fonts = new HashMap<>();


    private FontManager() { }


    public static void registerFont(Font font) {
        fonts.put(font.getName(), font);
    }


    public static Font getFont(String name) {
        return fonts.get(name);
    }

    public static void dispose() {
        for (Object key : fonts.keySet().toArray()) {
            Font font = fonts.get(key);
            for (Font.Size size : Font.Size.values())
                font.get(size).dispose();
            fonts.remove(key);
        }
    }


    public static void registerStandardFonts() {
        registerFont(new Font("arial"));
    }
}
