package com.skylarkingstudios.nightknight;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.skylarkingstudios.nightknight.helpers.Assets;
import com.skylarkingstudios.nightknight.screens.GameScreen;

//public class NightKnight extends ApplicationAdapter {
public class NightKnight extends Game {


	@Override
	public void create () {
        Gdx.app.log("NightKnight", "Created");
        Assets.load();
        setScreen(new GameScreen());
	}

	@Override
	public void render () {
        super.render(); // DON'T FORGET!
	}

    public void resize(int w, int h) {

    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

}
