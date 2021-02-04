package de.ricepuffz.ecosim.sprite;

import com.badlogic.gdx.graphics.Texture;
import de.ricepuffz.ecosim.ITickable;
import de.ricepuffz.ecosim.Util;
import de.ricepuffz.ecosim.registry.TextureRegistry;

public class TestSprite extends RiceSprite implements ITickable {
    private static Texture texture = TextureRegistry.getTexture("hecc");

    private boolean rising = true;

    public TestSprite(String name) {
        super(name, texture);

        this.setMiddleX(0);
        this.setMiddleY(0);
    }

    @Override
    public void onTick() {
        float deltaTime = Util.deltaTime();

        float horizontalMovement = 60F * deltaTime;
        float verticalMovement = 20F * deltaTime;

        if (rising) {
            this.translate(horizontalMovement, verticalMovement);
            if (this.getMiddleX() >= 100)
                rising = false;
        } else {
            this.translate(-horizontalMovement, -verticalMovement);
            if (this.getMiddleX() <= -100)
                rising = true;
        }

        this.rotate(100F * deltaTime);
    }
}
