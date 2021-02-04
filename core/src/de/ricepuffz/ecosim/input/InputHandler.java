package de.ricepuffz.ecosim.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import de.ricepuffz.ecosim.EcoSim;
import de.ricepuffz.ecosim.sprite.ClickMarkerSprite;

public class InputHandler implements InputProcessor {
    private EcoSim main;

    private float scrolledTotal = 0F;
    public boolean leftMousePressed = false;


    public InputHandler(EcoSim main) {
        this.main = main;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                main.movingUp = true;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                main.movingDown = true;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                main.movingRight = true;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                main.movingLeft = true;
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                main.movingUp = false;
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                main.movingDown = false;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                main.movingRight = false;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                main.movingLeft = false;
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

            main.lastClickLocation = new Vector2(screenX, screenY);
            main.updateLastClickPositionWorld();

            ClickMarkerSprite sprite = (ClickMarkerSprite) (main.scene.getLayer("debug").getSprite("clickMarker"));
            sprite.click(main.lastClickPositionWorld);
        } else if (button == Input.Buttons.MIDDLE) {
            scrolledTotal = 0F;
            ((OrthographicCamera) main.camera).zoom = 1F;
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
            main.lastClickLocation = new Vector2(screenX, screenY);
            main.updateLastClickPositionWorld();

            ClickMarkerSprite sprite = (ClickMarkerSprite) (main.scene.getLayer("debug").getSprite("clickMarker"));
            sprite.click(main.lastClickPositionWorld);
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



            OrthographicCamera camera = (OrthographicCamera) main.camera;
            camera.zoom += actualAmount / 10F;

            camera.update();


            if (leftMousePressed) {
                main.updateLastClickPositionWorld();

                ClickMarkerSprite sprite = (ClickMarkerSprite) (main.scene.getLayer("debug").getSprite("clickMarker"));
                sprite.click(main.lastClickPositionWorld);
            }
        }

        return true;
    }
}
