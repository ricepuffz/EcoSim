package de.ricepuffz.ecosim.engine.scene.object.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.ricepuffz.ecosim.engine.scene.IActor;

public class SpriteActor extends Sprite implements IActor {
    String name;

    public SpriteActor(String name, Texture texture) {
        super(texture);

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
