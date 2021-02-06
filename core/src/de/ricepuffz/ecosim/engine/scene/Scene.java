package de.ricepuffz.ecosim.engine.scene;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Scene {
    private Map<String, SceneLayer> layers;
    private List<SceneLayer> layersSorted;

    public Camera camera = null;


    public Scene() {
        layers = new HashMap<>();
        layersSorted = new ArrayList<>();
    }


    public abstract void onRender();

    public abstract InputProcessor getInputProcessor();


    public void draw() {
        for (SceneLayer layer : layersSorted) {
            layer.draw();
        }
    }

    public void tick() {
        for (SceneLayer layer : layers.values()) {
            layer.tick();
        }
    }

    public void onResize() {
        for (SceneLayer layer : layers.values()) {
            layer.onResize();
        }
    }


    public void addLayer(SceneLayer layer) {
        layers.put(layer.getName(), layer);

        int i = 0;
        boolean layerAdded = false;
        while (i < layersSorted.size()) {
            if (layer.getPosition() < layersSorted.get(i).getPosition()) {
                layersSorted.add(i, layer);
                layerAdded = true;
                break;
            }

            i++;
        }

        if (!layerAdded)
            layersSorted.add(layer);
    }

    public SceneLayer getLayer(String name) {
        return layers.get(name);
    }
}
