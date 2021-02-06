package de.ricepuffz.ecosim.engine.scene.object.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.engine.scene.IDrawable;
import de.ricepuffz.ecosim.engine.scene.ITickable;

public abstract class RiceSprite extends SpriteActor implements ITickable, IDrawable {
    public RiceSprite(String name, Texture texture) {
        super(name, texture);
    }

    @Override
    public void tick() {
        onTick();
    }

    @Override
    public abstract void onTick();

    public void draw(SpriteBatch batch) {
        ((Sprite) this).draw(batch);
    }

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
