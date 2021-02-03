package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import de.ricepuffz.ecosim.ITickable;
import de.ricepuffz.ecosim.registry.TextureRegistry;

public class TestSprite extends RiceSprite implements ITickable {
    private static Texture texture = TextureRegistry.getTexture("hecc");

    private boolean rising = true;

    public TestSprite(String name) {
        super(name, texture);
    }

    @Override
    public void onTick() {
        if (rising) {
            this.translate(0.3F, 0.1F);
            if (this.getX() >= 10)
                rising = false;
        } else {
            this.translate(-0.3F, -0.1F);
            if (this.getX() <= -10)
                rising = true;
        }

        this.rotate(0.1F);
    }
}
