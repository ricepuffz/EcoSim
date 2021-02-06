package de.ricepuffz.ecosim.scene.testscene.text;

import com.badlogic.gdx.Gdx;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class CameraPositionText extends Text implements ITickable {
    private TestScene scene;

    private String cameraPositionString;


    public CameraPositionText(TestScene scene) {
        super("cameraPositionText", "Camera: x=0 | y=0", FontManager.getFont("arial"), Font.Size.SIZE16);
        this.scene = scene;
    }


    private void updatePosition() {
        this.setPosition(Gdx.graphics.getWidth() - this.getGlyphLayout().width - 5, Gdx.graphics.getHeight() - 5);
    }


    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        cameraPositionString = "Camera: x=" + (int) scene.camera.position.x + " | y=" + (int) scene.camera.position.y;
        this.setContent(cameraPositionString);

        updatePosition();
    }
}
