package de.ricepuffz.ecosim.engine.ui.element;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.ricepuffz.ecosim.engine.Util;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.scene.IDrawable;
import de.ricepuffz.ecosim.engine.scene.ITickable;
import de.ricepuffz.ecosim.engine.scene.object.Text;

public class UIButton implements IUIElement, IDrawable, ITickable {
    private String name;
    private Vector2 position;

    private String textString;
    private Text text;

    protected Font font;
    protected Font.Size fontSize;


    public UIButton(String name, String textString, Vector2 position) {
        this.name = name;
        this.position = position;

        font = FontManager.getFont("arial");
        fontSize = Font.Size.SIZE16;

        this.setText(textString);
    }

    public UIButton(String name, String textString) {
        this(name, textString, new Vector2(0, 0));
    }


    public void setText(String value) {
        textString = value;
        generateText();
    }

    public void generateText() {
        text = new Text(null, textString, font, fontSize);
        text.setPosition(getX(), getY());
    }

    private void updateTextPosition() {
        text.setPosition(getX(), getY());
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void draw(SpriteBatch batch) {
        text.draw(batch);
    }

    @Override
    public void tick() {
        onTick();
    }

    @Override
    public void onTick() {

    }


    public float getX() {
        return position.x;
    }
    public float getY() {
        return position.y;
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setX(float value) {
        position.x = value;
        updateTextPosition();
    }
    public void setY(float value) {
        position.y = value;
        updateTextPosition();
    }
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
        updateTextPosition();
    }
    public void setPosition(Vector2 position) {
        this.position = position;
        updateTextPosition();
    }
    public void translate(float x, float y) {
        setX(getX() + x);
        setY(getY() + y);
        updateTextPosition();
    }
    public void translate(Vector2 value) {
        translate(value.x, value.y);
        updateTextPosition();
    }
}
