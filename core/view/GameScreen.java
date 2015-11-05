package com.skylarkingstudios.nightknight.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.skylarkingstudios.nightknight.game.WorldController;
import com.skylarkingstudios.nightknight.game.WorldRenderer;
import com.skylarkingstudios.nightknight.helpers.InputHandler;

public class GameScreen implements Screen {

    private WorldController world;
    private WorldRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        world = new WorldController();
        renderer = new WorldRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world.getNight(), world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);            // World updates by change in time
        renderer.render(runTime);       // Rendering concerned with which frame to use
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
