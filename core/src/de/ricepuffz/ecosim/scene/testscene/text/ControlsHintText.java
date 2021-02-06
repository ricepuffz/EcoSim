package de.ricepuffz.ecosim.scene.testscene.text;

import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.object.Text;

public class ControlsHintText extends Text {
    private static final String controlsHintString =
            "WASD or Arrows: Move camera\n" +
            "Shift (hold): Faster camera movement\n" +
            "Left Click: Move/Drag test box\n" +
            "Scroll: Zoom in/out\n" +
            "R: Reset zoom\n" +
            "E: Reset camera position";

    public ControlsHintText() {
        super("controlsHintText", controlsHintString, FontManager.getFont("arial"), Font.Size.SIZE16);
        this.setPosition(5, this.getGlyphLayout().height + 5);
    }
}
