package de.ricepuffz.ecosim.scene.testscene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ricepuffz.ecosim.EcoSim;
import de.ricepuffz.ecosim.scene.testscene.text.*;
import de.ricepuffz.ecosim.engine.Util;
import de.ricepuffz.ecosim.input.TestInputHandler;
import de.ricepuffz.ecosim.engine.scene.Scene;
import de.ricepuffz.ecosim.engine.scene.SceneLayer;
import de.ricepuffz.ecosim.engine.scene.object.sprite.ClickMarkerSprite;
import de.ricepuffz.ecosim.engine.scene.object.sprite.TestSprite;

public class TestScene extends Scene {
    private EcoSim main;

    private TestInputHandler testInputHandler;


    private float cameraSpeed = 300F;
    private float zoomingCameraSpeedModifier = 10000F;
    public boolean movingUp, movingDown, movingLeft, movingRight;

    public Vector2 lastClickLocation = new Vector2(0F, 0F);
    public Vector3 lastClickPositionWorld = new Vector3(0F, 0F, 0F);


    public TestScene(EcoSim main) {
        this.main = main;

        testInputHandler = new TestInputHandler(this);

        initScene();
    }


    private void initScene() {
        SceneLayer layer = new SceneLayer(main, "test", 1);
        this.addLayer(layer);
        layer.addActor(new TestSprite("hecc"));
        layer.addActor(new FuckText(this));


        SceneLayer debugLayer = new SceneLayer(main, "debug", 100000);
        this.addLayer(debugLayer);
        debugLayer.addActor(new ClickMarkerSprite("clickMarker"));


        SceneLayer hudLayer = new SceneLayer(main, "hud", 1000000);
        hudLayer.setIsHudLayer(true);
        this.addLayer(hudLayer);
        hudLayer.addActor(new ControlsHintText());
        hudLayer.addActor(new FPSCounterText());
        hudLayer.addActor(new CameraPositionText(this));
        hudLayer.addActor(new LastClickPositionScreenText(this));
        hudLayer.addActor(new LastClickPositionWorldText(this));
        hudLayer.addActor(new ZoomAmountText(this));
    }


    @Override
    public void onRender() {
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


        if (testInputHandler.leftMousePressed && (movingUp || movingDown || movingRight || movingLeft)) {
            updateLastClickPositionWorld();

            ClickMarkerSprite sprite = (ClickMarkerSprite) (getLayer("debug").getActor("clickMarker"));
            sprite.click(lastClickPositionWorld);
        }

        GridRenderer.renderGrid(this);
    }


    public void updateLastClickPositionWorld() {
        lastClickPositionWorld = camera.unproject(new Vector3(lastClickLocation.x, lastClickLocation.y, 0F));
    }


    @Override
    public InputProcessor getInputProcessor() {
        return testInputHandler;
    }
}
