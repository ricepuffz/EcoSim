package de.ricepuffz.ecosim.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {
    private Map<String, SceneLayer> layers;
    private List<SceneLayer> layersSorted;


    public Scene() {
        layers = new HashMap<>();
        layersSorted = new ArrayList<>();
    }


    public void draw(SpriteBatch batch) {
        for (SceneLayer layer : layersSorted) {
            layer.draw(batch);
        }
    }

    public void tick() {
        for (SceneLayer layer : layers.values()) {
            layer.tick();
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
