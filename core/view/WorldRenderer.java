package com.skylarkingstudios.nightknight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.skylarkingstudios.nightknight.helpers.Assets;
import com.skylarkingstudios.nightknight.objects.Night;
import com.skylarkingstudios.nightknight.objects.Skeleton;
import com.skylarkingstudios.nightknight.util.Constants;

public class WorldRenderer implements Disposable {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private ShapeRenderer sR;
    private BitmapFont font, font2;
    private WorldController world;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;

    // Game Objects
    private Night night;

    // Game Assets
    private Animation moon;


    public WorldRenderer(WorldController world) {
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);
        sR = new ShapeRenderer();
        sR.setProjectionMatrix(camera.combined);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("images/I-pixel-u.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 32;
        parameter.color = Color.LIGHT_GRAY;
        font2 = generator.generateFont(parameter);
        parameter.size = 64;
        font = generator.generateFont(parameter);

        initObjects();
        initAssets();
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.enableBlending();
        spriteBatch.begin();
        sR.begin(ShapeRenderer.ShapeType.Line);

        // Draw Backdrop
        spriteBatch.draw(Assets.backdrop, -80, -80, 960, 800);

        // Draw Moon
        spriteBatch.draw(moon.getKeyFrame(runTime, true), camera.viewportWidth/2F-64,
                camera.viewportHeight/3F*2, 128, 128);

        // Draw Mountains
        spriteBatch.draw(Assets.mountains, -80, -80, 960, 484);

        // Draw Arena
        spriteBatch.draw(Assets.arena, 0, -40, 800, 480);

        // Draw Knight (Fix this with a state machine {IDLERIGHT,ATTACKINGRIGHT,IDLELEFT,ATTACKINGLEFT,DYING,GETTINGREADY}
        spriteBatch.draw(night.getNightAnimation().getKeyFrame(night.getFrame(), night.isLooping()), Night.getDrawX(), Night.y, Constants.CHAR_WIDTH, Constants.CHAR_HEIGHT);

        // Draw Skeletons
        for (Skeleton skeleton : world.getSkeletons()) {
           // spriteBatch.draw(skeleton.getWalkAnimation().getKeyFrame(runTime, true), skeleton.getX(), skeleton.getY(), Constants.SKELE_WIDTH, Constants.SKELE_HEIGHT);
            spriteBatch.draw(skeleton.getSkeletonAnimation().getKeyFrame(skeleton.getFrame(), skeleton.isLooping()), skeleton.getX(), skeleton.getY(), Constants.SKELE_WIDTH, Constants.SKELE_HEIGHT);
        }

        // Draw Moonbeam
        if (night.getNightAnimation() != Assets.nightKnightRest) {
            spriteBatch.draw(Assets.moonBeam.getKeyFrame(runTime, true), Night.getDrawX(), world.getMoonBeamY(), Constants.CHAR_WIDTH, Constants.SCREEN_HEIGHT);
        }

        // Draw Score
        String score = "Score: " + world.getScore();
        font2.draw(spriteBatch, score, 20, camera.viewportHeight - (font2.getBounds(score).height));

        // Draw Paused Menu
        if (world.getGameState() == WorldController.GameState.PAUSED) {
            String s = "Paused";
            font.draw(spriteBatch, s, camera.viewportWidth/2F-font.getBounds(s).width/2F, Constants.SCREEN_HEIGHT/3*2+75);
        }

        // Draw Game Over Menu
        if (world.getGameState() == WorldController.GameState.GAME_OVER) {
            String s = "Game Over";
            font.draw(spriteBatch, s, camera.viewportWidth/2F-font.getBounds(s).width/2F, Constants.SCREEN_HEIGHT/3*2+75);
        }

        // Draw Waiting Text
        if (world.getGameState() == WorldController.GameState.WAITING) {
            String s = "Ready?";
            font.draw(spriteBatch, s, camera.viewportWidth /2F-font.getBounds(s).width/2F, Constants.SCREEN_HEIGHT/3*2+75);
        }

        // Draw Starting Text
        if (world.getGameState() == WorldController.GameState.STARTING) {
            String s = "Go!";
            font.draw(spriteBatch, s, camera.viewportWidth/2F-font.getBounds(s).width/2F, Constants.SCREEN_HEIGHT/3*2+75);
        }


//        // Draw AOE effect
//        spriteBatch.draw(Assets.areaOfEffect.getKeyFrame(runTime, true), Night.getDrawX(), Night.y, Constants.CHAR_WIDTH, Constants.CHAR_HEIGHT/2);

        // Draw Debug (if on)
        if (world.getDebug()) {
            // Knight bound box
            sR.rect(Night.x, Night.y, Night.getWidth(), Night.getHeight());
            sR.rect(Night.x-54, Night.y, Constants.CHAR_WIDTH, Night.getHeight());

            // Skele bound box
            for (Skeleton skeleton : world.getSkeletons()) {
                sR.rect(skeleton.getBoundX(), skeleton.getY(), skeleton.getWidth(), Constants.SKELE_HEIGHT);
            }
        }

        spriteBatch.end();
        sR.end();

    }

    private void initObjects() {
        night = world.getNight();
    }

    private void initAssets() {
        moon = Assets.moon;
    }

    public void dispose() {
        sR.dispose();
        spriteBatch.dispose();
        font.dispose();
        generator.dispose();
    }
}