package com.skylarkingstudios.nightknight.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;
import com.skylarkingstudios.nightknight.game.WorldController;
import com.skylarkingstudios.nightknight.objects.Night;

public class InputHandler implements InputProcessor {

    private Night night;
    private WorldController world;

    public InputHandler(Night night, WorldController world) {
       this.night = night;
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        // LEFT is pressed on keyboard
        if(keycode == Input.Keys.LEFT)
            if(night.isCooledDown())
                night.attackLeft();
            if(world.getGameState() == WorldController.GameState.WAITING) {
                world.setGameState(WorldController.GameState.STARTING);
                if (night.getState() == Night.NightState.REST)
                    night.setState(Night.NightState.READY);
            }
            if(world.getGameState() == WorldController.GameState.GAME_OVER) {
                world.restartGame();
            }

        // RIGHT is pressed on keyboard
        else if(keycode == Input.Keys.RIGHT)
            if(night.isCooledDown())
                night.attackRight();
            if(world.getGameState() == WorldController.GameState.WAITING) {
                world.setGameState(WorldController.GameState.STARTING);
                if(night.getState() == Night.NightState.REST)
                    night.setState(Night.NightState.READY);
            }

            if(world.getGameState() == WorldController.GameState.GAME_OVER) {
                world.restartGame();
            }

        // P is pressed on keyboard
        else if(keycode == Input.Keys.P) {
            if (world.getGameState() == WorldController.GameState.PLAYING)
                world.setGameState(WorldController.GameState.PAUSED);
            else if (world.getGameState() == WorldController.GameState.PAUSED)
                world.setGameState(WorldController.GameState.PLAYING);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keschwabycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        if(world.getGameState() == WorldController.GameState.WAITING) {
            world.setGameState(WorldController.GameState.STARTING);
            if(night.getState() == Night.NightState.REST)
                night.setState(Night.NightState.READY);
        }

        if(world.getGameState() == WorldController.GameState.GAME_OVER) {
            world.restartGame();
        }


        if(night.isCooledDown()) {
            if (screenX <= Gdx.graphics.getWidth() / 2)
                night.attackLeft();
            else
                night.attackRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
