package de.ricepuffz.ecosim.scene.testscene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GridRenderer {
    private GridRenderer() { }

    public static void renderGrid(TestScene scene) {
        ShapeRenderer sr = new ShapeRenderer();
        sr.setProjectionMatrix(scene.camera.combined);
        Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
        sr.begin(ShapeRenderer.ShapeType.Line);

        if (((OrthographicCamera) scene.camera).zoom <= 0.5F) {
            Gdx.gl.glLineWidth(1F);
            sr.setColor(1F, 1F, 1F, 0.05F);

            for (int i = -5000; i < 5001; i++) {
                sr.line(new Vector2(i * 20F, -100000F), new Vector2(i * 20F, 100000));
                sr.line(new Vector2(-100000F, i * 20F), new Vector2(100000, i * 20F));
            }
        }

        Gdx.gl.glLineWidth(1F);
        sr.setColor(1F, 1F, 1F, 0.1F);

        for (int i = -1000; i < 1001; i++) {
            if (i % 3 == 0)
                sr.setColor(1F, 1F, 1F, 0.3F);

            sr.line(new Vector2(i * 100F, -100000F), new Vector2(i * 100F, 100000));
            sr.line(new Vector2(-100000F, i * 100F), new Vector2(100000, i * 100F));

            if (i % 3 == 0)
                sr.setColor(1F, 1F, 1F, 0.1F);
        }


        Gdx.gl.glLineWidth(2F);
        sr.setColor(1F, 1F, 1F, 0.3F);
        sr.line(new Vector2(0F, -100000F), new Vector2(0F, 100000F));
        sr.line(new Vector2(-100000F, 0F), new Vector2(100000F, 0F));
        sr.end();
        Gdx.gl.glDisable(Gdx.gl.GL_BLEND);
    }
}
