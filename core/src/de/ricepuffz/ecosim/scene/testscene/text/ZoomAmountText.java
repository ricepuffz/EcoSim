package de.ricepuffz.ecosim.scene.testscene.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

import java.math.BigDecimal;

public class ZoomAmountText extends Text implements ITickable {
    private TestScene scene;

    private String zoomAmountString;


    public ZoomAmountText(TestScene scene) {
        super("zoomAmountText", "Zoom: 0.0", FontManager.getFont("arial"), Font.Size.SIZE16);
        this.scene = scene;
    }


    private void updatePosition() {
        LastClickPositionWorldText lastClickPositionWorldText = (LastClickPositionWorldText) scene.getLayer("hud").getActor("lastClickPositionWorldText");
        GlyphLayout lastClickPositionWorldTextGlyphLayout = lastClickPositionWorldText.getGlyphLayout();

        this.setPosition(Gdx.graphics.getWidth() - getGlyphLayout().width - 5,
                lastClickPositionWorldText.getY() - lastClickPositionWorldTextGlyphLayout.height - 10);
    }


    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        BigDecimal bd = new BigDecimal(Float.toString(1F - (((OrthographicCamera) scene.camera).zoom)));
		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		zoomAmountString = "Zoom: " + bd.floatValue();

        this.setContent(zoomAmountString);

        updatePosition();
    }
}
