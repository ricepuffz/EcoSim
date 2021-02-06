package de.ricepuffz.ecosim.scene.testscene.text;

import com.badlogic.gdx.Gdx;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.IResizeAffected;
import de.ricepuffz.ecosim.engine.scene.object.Text;

public class FPSCounterText extends Text implements ITickable, IResizeAffected {
    private int fpsCounter = 0;
    private long lastFpsPrint = System.currentTimeMillis();
    private String fpsCounterValue = "FPS: 0";

    public FPSCounterText() {
        super("fpsCounterText", "", FontManager.getFont("arial"), Font.Size.SIZE16);
        reapplyPosition();
    }

    private void reapplyPosition() {
        this.setPosition(5, Gdx.graphics.getHeight() - 5);
    }

    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        fpsCounter++;

        if (lastFpsPrint + 1000 <= System.currentTimeMillis()) {
            fpsCounterValue = "FPS: " + fpsCounter;
            fpsCounter = 0;
            lastFpsPrint = System.currentTimeMillis();
        }

        this.setContent(fpsCounterValue);
    }

    @Override
    public void onResize() {
        reapplyPosition();
    }
}
