package de.ricepuffz.ecosim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ricepuffz.ecosim.font.Font;
import de.ricepuffz.ecosim.input.InputHandler;
import de.ricepuffz.ecosim.registry.FontRegistry;
import de.ricepuffz.ecosim.registry.TextureRegistry;
import de.ricepuffz.ecosim.scene.Scene;
import de.ricepuffz.ecosim.scene.SceneLayer;
import de.ricepuffz.ecosim.sprite.ClickMarkerSprite;
import de.ricepuffz.ecosim.sprite.TestSprite;

import java.math.BigDecimal;

public class EcoSim extends ApplicationAdapter {
	private long lastTickTime;

	private SpriteBatch batch;
	private SpriteBatch hud;
	private Font arial;
	public Scene scene = null;
	public Camera camera = null;
	private InputHandler inputHandler = null;

	private int fpsCounter = 0;
	private long lastFpsPrint = System.currentTimeMillis();
	private String fpsCounterValue = "";

	private float cameraSpeed = 300F;
	private float zoomingCameraSpeedModifier = 10000F;
	public boolean movingUp, movingDown, movingLeft, movingRight;
	public Vector2 lastClickLocation = new Vector2(0F, 0F);
	public Vector3 lastClickPositionWorld = new Vector3(0F, 0F, 0F);

	@Override
	public void create () {
		lastTickTime = System.currentTimeMillis();

		TextureRegistry.registerStandardTextures();
		FontRegistry.registerStandardFonts();

		batch = new SpriteBatch();
		hud = new SpriteBatch();

		arial = new Font("arial");
		scene = new Scene();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		SceneLayer layer = new SceneLayer("test", 1);
		layer.addSprite(new TestSprite("hecc"));
		scene.addLayer(layer);

		SceneLayer debugLayer = new SceneLayer("debug", 1000000);
		debugLayer.addSprite(new ClickMarkerSprite("clickMarker"));
		scene.addLayer(debugLayer);

		inputHandler = new InputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3F, 0.4F, 0.5F, 1F);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
				(Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

		scene.tick();

		float horizontalCameraMovement = 0F;
		float verticalCameraMovement = 0F;

		boolean shifting = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

		cameraSpeed *= ((OrthographicCamera) camera).zoom;
		if (shifting)
			cameraSpeed *= 2F;

		if (movingUp)
			verticalCameraMovement += cameraSpeed * Util.deltaTime();
		if (movingDown)
			verticalCameraMovement -= cameraSpeed * Util.deltaTime();
		if (movingRight)
			horizontalCameraMovement += cameraSpeed * Util.deltaTime();
		if (movingLeft)
			horizontalCameraMovement -= cameraSpeed * Util.deltaTime();

		if (shifting)
			cameraSpeed /= 2F;
		cameraSpeed /= ((OrthographicCamera) camera).zoom;

		camera.translate(horizontalCameraMovement, verticalCameraMovement, 0F);
		camera.update();


		if (inputHandler.leftMousePressed && (movingUp || movingDown || movingRight || movingLeft)) {
			updateLastClickPositionWorld();

			ClickMarkerSprite sprite = (ClickMarkerSprite) (scene.getLayer("debug").getSprite("clickMarker"));
			sprite.click(lastClickPositionWorld);
		}


		ShapeRenderer sr = new ShapeRenderer();
		Gdx.gl.glLineWidth(1F);
		sr.setProjectionMatrix(camera.combined);
		Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
		sr.begin(ShapeRenderer.ShapeType.Line);

		sr.setColor(1F, 1F, 1F, 0.1F);

		for (int i = -1000; i < 1001; i++)
			sr.line(new Vector2(i * 100F, -100000F), new Vector2(i * 100F, 100000));
		for (int i = -1000; i < 1001; i++)
			sr.line(new Vector2(-100000F, i * 100F), new Vector2(100000, i * 100F));


		sr.setColor(1F, 1F, 1F, 0.3F);
		sr.line(new Vector2(0F, -100000F), new Vector2(0F, 100000F));
		sr.line(new Vector2(-100000F, 0F), new Vector2(100000F, 0F));
		sr.end();
		Gdx.gl.glDisable(Gdx.gl.GL_BLEND);


		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		scene.draw(batch);

		arial.get32().draw(batch, "fuck you", scene.getLayer("test").getSprite("hecc").getX() + scene.getLayer("test").getSprite("hecc").getWidth() / 2,
				scene.getLayer("test").getSprite("hecc").getY() + 250);

		batch.end();


		hud.begin();

		fpsCounter++;

		if (lastFpsPrint + 1000 <= System.currentTimeMillis()) {
			fpsCounterValue = "FPS: " + fpsCounter;
			fpsCounter = 0;
			lastFpsPrint = System.currentTimeMillis();
		}


		String controlsHintString = "WASD or Arrows: Move camera\n" +
				"Shift (hold): Faster camera movement\n" +
				"Left Click: Move/Drag test box\n" +
				"Scroll: Zoom in/out\n" +
				"Scrollwheel press: Reset zoom";

		GlyphLayout gl = new GlyphLayout(arial.get16(), controlsHintString);
		arial.get16().draw(hud, controlsHintString, 5, gl.height + 5);


		arial.get16().draw(hud, fpsCounterValue, 5, Gdx.graphics.getHeight() - 5);

		String cameraPositionString = "Camera: x=" + (int) camera.position.x + " | y=" + (int) camera.position.y;
		GlyphLayout glyph = new GlyphLayout(arial.get16(), cameraPositionString);
		arial.get16().draw(hud, cameraPositionString, Gdx.graphics.getWidth() - glyph.width - 5,
				Gdx.graphics.getHeight() - 5);

		String lastClickPositionScreenString = "Click Screen: x=" + (int) lastClickLocation.x + " | y=" + (int) lastClickLocation.y;
		GlyphLayout glyph2 = new GlyphLayout(arial.get16(), lastClickPositionScreenString);
		arial.get16().draw(hud, lastClickPositionScreenString, Gdx.graphics.getWidth() - glyph2.width - 5,
				Gdx.graphics.getHeight() - glyph.height - 15);

		String lastClickPositionWorldString = "Click World: x=" + (int) lastClickPositionWorld.x + " | y=" + (int) lastClickPositionWorld.y;
		GlyphLayout glyph3 = new GlyphLayout(arial.get16(), lastClickPositionWorldString);
		arial.get16().draw(hud, lastClickPositionWorldString, Gdx.graphics.getWidth() - glyph3.width - 5,
				Gdx.graphics.getHeight() - glyph.height - glyph2.height - 20);

		/*
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		*/

		String zoomAmountString = "Zoom: ";

		BigDecimal bd = new BigDecimal(Float.toString(1F - (((OrthographicCamera) camera).zoom)));
		bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
		zoomAmountString += bd.floatValue();

		GlyphLayout glyph4 = new GlyphLayout(arial.get16(), zoomAmountString);
		arial.get16().draw(hud, zoomAmountString, Gdx.graphics.getWidth() - glyph4.width - 5,
				Gdx.graphics.getHeight() - glyph.height - glyph2.height - glyph3.height - 30);

		hud.end();
	}

	public void updateLastClickPositionWorld() {
		lastClickPositionWorld = camera.unproject(new Vector3(lastClickLocation.x, lastClickLocation.y, 0F));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		TextureRegistry.dispose();
	}
}
