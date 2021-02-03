package de.ricepuffz.ecosim.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.sprite.RiceSprite;

import java.util.ArrayList;
import java.util.List;

public class SceneLayer {
    private String name;
    private int position;

    private List<RiceSprite> sprites;


    public SceneLayer(String name, int position) {
        this.name = name;
        this.position = position;

        sprites = new ArrayList<>();
    }


    public void draw(SpriteBatch batch) {
        for (RiceSprite sprite : sprites) {
            sprite.draw(batch);
        }
    }

    public void tick() {
        for (RiceSprite sprite : sprites) {
            sprite.tick();
        }
    }


    public void addSprite(RiceSprite sprite) {
        sprites.add(sprite);
    }

    public RiceSprite getSprite(String name) {
        for (RiceSprite sprite : sprites) {
            if (sprite.getName().equals(name))
                return sprite;
        }

        return null;
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
