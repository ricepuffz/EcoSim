package de.ricepuffz.ecosim.engine.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIManager {
    private Map<String, UILayer> layers;
    private List<UILayer> layersSorted;


    public UIManager() {
        layers = new HashMap<>();
        layersSorted = new ArrayList<>();
    }


    public void draw() {
        for (UILayer layer : layersSorted) {
            layer.draw();
        }
    }

    public void tick() {
        for (UILayer layer : layers.values()) {
            layer.tick();
        }
    }

    public void onResize() {
        for (UILayer layer : layers.values()) {
            layer.onResize();
        }
    }


    public void addLayer(UILayer layer) {
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

    public UILayer getLayer(String name) {
        return layers.get(name);
    }
}
