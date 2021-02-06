package de.ricepuffz.ecosim.engine;

import com.badlogic.gdx.Gdx;

public class Util {
    private Util() { }

    public static float deltaTime() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        return Math.min(deltaTime, 1F);
    }
}
