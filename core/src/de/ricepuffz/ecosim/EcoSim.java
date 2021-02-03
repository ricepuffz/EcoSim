package de.ricepuffz.ecosim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.registry.TextureRegistry;
import de.ricepuffz.ecosim.scene.Scene;
import de.ricepuffz.ecosim.scene.SceneLayer;
import de.ricepuffz.ecosim.sprite.TestSprite;

public class EcoSim extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Scene scene = null;
	Camera camera = null;
	
	@Override
	public void create () {
		TextureRegistry.registerStandardTextures();

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/arial_100.fnt"));
		font.getData().setScale(0.4F);
		scene = new Scene();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		SceneLayer layer = new SceneLayer("test", 1);
		layer.addSprite(new TestSprite("hecc"));
		scene.addLayer(layer);

		camera.translate(scene.getLayer("test").getSprite("hecc").getWidth() / 2,
				scene.getLayer("test").getSprite("hecc").getHeight() / 2, 0F);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3F, 0.4F, 0.5F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		scene.tick();

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		scene.draw(batch);
		font.draw(batch, "fuck you", scene.getLayer("test").getSprite("hecc").getX() + scene.getLayer("test").getSprite("hecc").getWidth() / 2,
				scene.getLayer("test").getSprite("hecc").getY() + 250);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		TextureRegistry.dispose();
	}
}
