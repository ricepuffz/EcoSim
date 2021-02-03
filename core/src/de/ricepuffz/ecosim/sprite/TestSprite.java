package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import de.ricepuffz.ecosim.ITickable;
import de.ricepuffz.ecosim.registry.TextureRegistry;

public class TestSprite extends RiceSprite implements ITickable {
    private static Texture texture = TextureRegistry.getTexture("hecc");

    private boolean rising = true;

    public TestSprite(String name) {
        super(name, texture);

        this.setX(80F);
        this.setY(40F);
    }

    @Override
    public void tick() {
        if (rising) {
            this.setX(this.getX() + 0.3F);
            this.setY(this.getY() + 0.1F);
            if (this.getX() >= 180)
                rising = false;
        } else {
            this.setX(this.getX() - 0.3F);
            this.setY(this.getY() - 0.1F);
            if (this.getX() <= 80)
                rising = true;
        }

        this.rotate(0.1F);
    }
}
