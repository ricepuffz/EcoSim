package de.ricepuffz.ecosim.scene.testscene.text;

import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;
import de.ricepuffz.ecosim.engine.scene.object.sprite.TestSprite;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class FuckText extends Text implements ITickable {
    private TestScene scene;

    public FuckText(TestScene scene) {
        super("fuckText", "fuck", FontManager.getFont("arial"), Font.Size.SIZE32);

        this.scene = scene;
    }

    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        TestSprite testSprite = (TestSprite) scene.getLayer("test").getActor("hecc");
        this.setPosition(testSprite.getX() + testSprite.getWidth() / 2, testSprite.getY() + 250);
    }
}
