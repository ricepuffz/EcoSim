package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.ricepuffz.ecosim.ITickable;

public abstract class RiceSprite extends Sprite implements ITickable {
    private String name;

    public RiceSprite(String name, Texture texture) {
        super(texture);

        this.name = name;
    }

    @Override
    public void tick() {
        onTick();
    }

    @Override
    public abstract void onTick();

    public String getName() {
        return name;
    }
}
