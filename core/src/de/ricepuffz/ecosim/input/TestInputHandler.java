package de.ricepuffz.ecosim.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import de.ricepuffz.ecosim.engine.scene.object.sprite.ClickMarkerSprite;
import de.ricepuffz.ecosim.scene.testscene.TestScene;

public class TestInputHandler implements InputProcessor {
    private TestScene scene;

    private float scrolledTotal = 0F;
    public boolean leftMousePressed = false;


    public TestInputHandler(TestScene scene) {
        this.scene = scene;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                scene.movingUp = true;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                scene.movingDown = true;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                scene.movingRight = true;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                scene.movingLeft = true;
                break;
            case Input.Keys.R:
                scrolledTotal = 0F;
                ((OrthographicCamera) scene.camera).zoom = 1F;
                break;
            case Input.Keys.E:
                scene.camera.position.x = 0;
                scene.camera.position.y = 0;
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                scene.movingUp = false;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                scene.movingDown = false;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                scene.movingRight = false;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                scene.movingLeft = false;
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            leftMousePressed = true;

            scene.lastClickLocation = new Vector2(screenX, screenY);
            scene.updateLastClickPositionWorld();

            ClickMarkerSprite sprite = (ClickMarkerSprite) (scene.getLayer("debug").getActor("clickMarker"));
            sprite.click(scene.lastClickPositionWorld);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == 0)
            leftMousePressed = false;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (leftMousePressed) {
            scene.lastClickLocation = new Vector2(screenX, screenY);
            scene.updateLastClickPositionWorld();

            ClickMarkerSprite sprite = (ClickMarkerSprite) (scene.getLayer("debug").getActor("clickMarker"));
            sprite.click(scene.lastClickPositionWorld);
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        float actualAmount = amount;

        if (scrolledTotal + actualAmount >= -10F && scrolledTotal + actualAmount <= 26F) {
            if (scrolledTotal >= 3F) {
                if (scrolledTotal >= 9F) {
                    if (scrolledTotal >= 18F)
                        actualAmount *= 4F;
                    else
                        actualAmount *= 3F;
                } else
                    actualAmount *= 2F;
            }
            if (scrolledTotal <= -3F) {
                if (scrolledTotal <= -5F) {
                    if (scrolledTotal <= -7F)
                        actualAmount *= 0.175F;
                    else
                        actualAmount *= 0.25F;
                } else
                    actualAmount *= 0.5F;
            }

            if (scrolledTotal + actualAmount > -0.9F && scrolledTotal + actualAmount < 0.9F) {
                actualAmount = actualAmount - (scrolledTotal + actualAmount);
            }
            scrolledTotal += actualAmount;

            float neededChange = 0F;
            if (scrolledTotal < -10F) {
                neededChange = -10F - scrolledTotal;
            } else if (scrolledTotal > 26F) {
                neededChange = 26F - scrolledTotal;
            }

            scrolledTotal += neededChange;
            actualAmount += neededChange;



            OrthographicCamera camera = (OrthographicCamera) scene.camera;
            camera.zoom += actualAmount / 10F;

            camera.update();


            if (leftMousePressed) {
                scene.updateLastClickPositionWorld();

                ClickMarkerSprite sprite = (ClickMarkerSprite) (scene.getLayer("debug").getActor("clickMarker"));
                sprite.click(scene.lastClickPositionWorld);
            }
        }

        return true;
    }
}
