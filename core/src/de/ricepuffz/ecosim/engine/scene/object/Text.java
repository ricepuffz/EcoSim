package de.ricepuffz.ecosim.engine.scene.object;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.ricepuffz.ecosim.engine.font.Font;
import de.ricepuffz.ecosim.engine.scene.IActor;
import de.ricepuffz.ecosim.engine.scene.IDrawable;

public class Text implements IActor, IDrawable {
    private String name;
    private String content;
    private Font font;
    private Font.Size fontSize;

    private GlyphLayout glyphLayout;
    private Vector2 position;

    public Text(String name, String content, Font font, Font.Size fontSize, Vector2 position) {
        this.name = name;
        this.content = content;
        this.font = font;
        this.fontSize = fontSize;
        this.position = position;

        updateGlyphLayout();
    }

    public Text(String name, String content, Font font, Font.Size fontSize) {
        this(name, content, font, fontSize, new Vector2(0, 0));
    }


    private void updateGlyphLayout() {
        glyphLayout = new GlyphLayout(font.get(fontSize), content);
    }

    @Override
    public void draw(SpriteBatch batch) {
        font.get(fontSize).draw(batch, content, position.x, position.y);
    }

    @Override
    public String getName() {
        return name;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
        updateGlyphLayout();
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
    }
    public void setY(float value) {
        position.y = value;
    }
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
