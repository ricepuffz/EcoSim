package de.ricepuffz.ecosim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EcoSim extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;

	float x, y;
	boolean rising = true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("hecc.png");
		img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		sprite = new Sprite(img);

		x = 80F;
		y = 40F;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);

		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		batch.begin();
		//batch.draw(img, x, y);
		sprite.setX(x);
		sprite.setY(y);
		sprite.rotate(0.1F);
		sprite.draw(batch);
		batch.end();

		if (rising) {
			x += 0.3F;
			y += 0.1F;
			if (x >= 180)
				rising = false;
		} else {
			x -= 0.3F;
			y -= 0.1F;
			if (x <= 80)
				rising = true;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
