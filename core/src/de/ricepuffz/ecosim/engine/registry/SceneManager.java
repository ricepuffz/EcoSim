package de.ricepuffz.ecosim.engine.registry;

import de.ricepuffz.ecosim.engine.scene.Scene;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static Map<String, Scene> scenes = new HashMap<>();


    private SceneManager() { }


    public static void registerScene(Scene scene, String name) {
        scenes.put(name, scene);
    }


    public static Scene getScene(String name) {
        return scenes.get(name);
    }

    public static void onResize() {
        for (Object key : scenes.keySet().toArray())
            scenes.get(key).onResize();
    }
}

