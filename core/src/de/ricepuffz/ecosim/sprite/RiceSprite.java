package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.ricepuffz.ecosim.ITickable;

public abstract class RiceSprite extends Sprite implements ITickable {
    private String name = null;

    public RiceSprite(String name, Texture texture) {
        super(texture);

        this.name = name;
    }

    @Override
    public abstract void tick();

    public String getName() {
        return name;
    }
}
