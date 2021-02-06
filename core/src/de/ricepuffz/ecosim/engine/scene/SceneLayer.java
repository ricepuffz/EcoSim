package de.ricepuffz.ecosim.engine.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.EcoSim;
import de.ricepuffz.ecosim.engine.scene.object.IResizeAffected;

import java.util.ArrayList;
import java.util.List;

public class SceneLayer {
    private EcoSim main;

    private String name;
    private int position;
    private boolean hudLayer;

    private List<IActor> actors;


    public SceneLayer(EcoSim main, String name, int position) {
        this.main = main;

        this.name = name;
        this.position = position;

        actors = new ArrayList<>();
    }


    public void draw() {
        SpriteBatch batch = hudLayer ? main.getHudBatch() : main.getBatch();

        batch.begin();

        for (IActor actor : actors) {
            if (actor instanceof IDrawable)
                ((IDrawable) actor).draw(batch);
        }

        batch.end();
    }

    public void tick() {
        for (IActor actor : actors) {
            if (actor instanceof ITickable)
                ((ITickable) actor).tick();
        }
    }

    public void onResize() {
        for (IActor actor : actors) {
            if (actor instanceof IResizeAffected)
                ((IResizeAffected) actor).onResize();
        }
    }


    public IActor getActor(String name) {
        for (IActor actor : actors) {
            if (actor.getName().equals(name))
                return actor;
        }
        return null;
    }
    public void addActor(IActor actor) {
        actors.add(actor);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setIsHudLayer(boolean value) {
        hudLayer = value;
    }
}
