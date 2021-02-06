package de.ricepuffz.ecosim.scene.testscene.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class LastClickPositionScreenText extends Text implements ITickable {
    private TestScene scene;

    private String lastClickPositionScreenString;


    public LastClickPositionScreenText(TestScene scene) {
        super("lastClickPositionScreenText", "Click Screen: x=0 | y=0", FontManager.getFont("arial"), Font.Size.SIZE16);
        this.scene = scene;
    }


    private void updatePosition() {
        CameraPositionText cameraPositionText = (CameraPositionText) scene.getLayer("hud").getActor("cameraPositionText");
        GlyphLayout cameraPositionTextGlyphLayout = cameraPositionText.getGlyphLayout();

        this.setPosition(Gdx.graphics.getWidth() - getGlyphLayout().width - 5,
                cameraPositionText.getY() - cameraPositionTextGlyphLayout.height - 10);
    }


    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        lastClickPositionScreenString = "Click Screen: x=" + (int) scene.lastClickLocation.x + " | y=" + (int) scene.lastClickLocation.y;
        this.setContent(lastClickPositionScreenString);

        updatePosition();
    }
}
