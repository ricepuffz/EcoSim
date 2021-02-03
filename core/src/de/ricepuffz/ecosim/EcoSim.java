package de.ricepuffz.ecosim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ricepuffz.ecosim.registry.TextureRegistry;
import de.ricepuffz.ecosim.scene.Scene;
import de.ricepuffz.ecosim.sprite.RiceSprite;
import de.ricepuffz.ecosim.sprite.TestSprite;

public class EcoSim extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Scene scene = null;
	
	@Override
	public void create () {
		TextureRegistry.registerStandardTextures();

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3);
		scene = new Scene();

		scene.addSprite(new TestSprite("hecc"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3F, 0.4F, 0.5F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		scene.tick();

		batch.begin();
		scene.draw(batch);
		font.draw(batch, "fuck you", scene.getSprite("hecc").getX() + scene.getSprite("hecc").getWidth() / 2, scene.getSprite("hecc").getY() + 250);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		TextureRegistry.dispose();
	}
}
