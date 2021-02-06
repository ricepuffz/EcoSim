package de.ricepuffz.ecosim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.engine.registry.FontManager;
import de.ricepuffz.ecosim.engine.registry.SceneManager;
import de.ricepuffz.ecosim.engine.registry.TextureManager;
import de.ricepuffz.ecosim.engine.scene.Scene;
import de.ricepuffz.ecosim.engine.ui.UIManager;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class EcoSim extends ApplicationAdapter {
	private SpriteBatch batch;
	private SpriteBatch hudBatch;

	private Scene currentScene = null;


	@Override
	public void create () {
		TextureManager.registerStandardTextures();
		FontManager.registerStandardFonts();

		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();

		currentScene = new TestScene(this);
		SceneManager.registerScene(currentScene, "test");

		currentScene.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Gdx.input.setInputProcessor(currentScene.getInputProcessor());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3F, 0.4F, 0.5F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

		currentScene.onRender();

		batch.setProjectionMatrix(currentScene.camera.combined);

		currentScene.tick();
		currentScene.draw();

		UIManager currentUIManager = currentScene.uiManager;
		if (currentUIManager != null) {
			currentUIManager.tick();
			currentUIManager.draw();
		}
	}

	@Override
	public void resize(int width, int height) {
		currentScene.camera.viewportWidth = width;
		currentScene.camera.viewportHeight = height;
		currentScene.camera.update();

		hudBatch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		SceneManager.onResize();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		hudBatch.dispose();
		TextureManager.dispose();
		FontManager.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	public SpriteBatch getHudBatch() {
		return hudBatch;
	}
}
