package de.ricepuffz.ecosim.scene.testscene.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class LastClickPositionWorldText extends Text implements ITickable {
    private TestScene scene;

    private String lastClickPositionWorldString;


    public LastClickPositionWorldText(TestScene scene) {
        super("lastClickPositionWorldText", "Click World: x=0 | y=0", FontManager.getFont("arial"), Font.Size.SIZE16);
        this.scene = scene;
    }


    private void updatePosition() {
        LastClickPositionScreenText lastClickPositionScreenText = (LastClickPositionScreenText) scene.getLayer("hud").getActor("lastClickPositionScreenText");
        GlyphLayout lastClickPositionScreenTextGlyphLayout = lastClickPositionScreenText.getGlyphLayout();

        this.setPosition(Gdx.graphics.getWidth() - getGlyphLayout().width - 5,
                lastClickPositionScreenText.getY() - lastClickPositionScreenTextGlyphLayout.height - 5);
    }


    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {
        lastClickPositionWorldString = "Click World: x=" + (int) scene.lastClickPositionWorld.x + " | y=" + (int) scene.lastClickPositionWorld.y;
        this.setContent(lastClickPositionWorldString);

        updatePosition();
    }
}