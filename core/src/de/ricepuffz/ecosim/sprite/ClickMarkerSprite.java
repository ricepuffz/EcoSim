package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import de.ricepuffz.ecosim.registry.TextureRegistry;

public class ClickMarkerSprite extends RiceSprite {
    private static Texture texture = TextureRegistry.getTexture("pixel");

    public ClickMarkerSprite(String name) {
        super(name, texture);

        this.scale(3F);
        this.setColor(Color.RED);
        this.setAlpha(0F);
    }

    @Override
    public void onTick() {

    }

    public void click(Vector3 position) {
        this.setMiddleX(position.x);
        this.setMiddleY(position.y);
        this.setAlpha(1F);
    }
}
