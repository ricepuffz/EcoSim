package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
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


    public float getMiddleX() {
        return this.getX() + this.getWidth() / 2;
    }

    public float getMiddleY() {
        return this.getY() + this.getHeight() / 2;
    }

    public void setMiddleX(float value) {
        this.setX(value - this.getWidth() / 2);
    }

    public void setMiddleY(float value) {
        this.setY(value - this.getHeight() / 2);
    }
}
