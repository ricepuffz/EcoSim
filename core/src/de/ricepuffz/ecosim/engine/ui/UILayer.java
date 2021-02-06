package de.ricepuffz.ecosim.engine.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.EcoSim;
import de.ricepuffz.ecosim.engine.scene.IDrawable;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.IResizeAffected;
import de.ricepuffz.ecosim.engine.ui.element.IUIElement;

import java.util.ArrayList;
import java.util.List;

public class UILayer {

    private EcoSim main;

    private String name;
    private int position;

    private List<IUIElement> elements;


    public UILayer(EcoSim main, String name, int position) {
        this.main = main;

        this.name = name;
        this.position = position;

        elements = new ArrayList<>();
    }


    public void draw() {
        SpriteBatch batch = main.getHudBatch();

        batch.begin();

        for (IUIElement element : elements) {
            if (element instanceof IDrawable)
                ((IDrawable) element).draw(batch);
        }

        batch.end();
    }

    public void tick() {
        for (IUIElement element : elements) {
            if (element instanceof ITickable)
                ((ITickable) element).tick();
        }
    }

    public void onResize() {
        for (IUIElement element : elements) {
            if (element instanceof IResizeAffected)
                ((IResizeAffected) element).onResize();
        }
    }


    public IUIElement getUIElement(String name) {
        for (IUIElement element : elements) {
            if (element.getName().equals(name))
                return element;
        }
        return null;
    }
    public void addUIElement(IUIElement element) {
        elements.add(element);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
