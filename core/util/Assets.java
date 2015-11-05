package com.skylarkingstudios.nightknight.helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Sound missSound, strikeSound;

    // Public Skeleton Assets
    public static Animation skeletonWalkLeft, skeletonWalkRight, skeletonDieLeft, skeletonDieRight,
            skeletonSwordSwingLeft, skeletonSwordSwingRight;

    // Public Knight Assets
    public static Animation nightKnightRest, nightKnightReady, nightKnightIdleRight,
            nightKnightAttackRight, nightKnightIdleLeft, nightKnightAttackLeft;

    // Public Level Assets
    public static Animation moon, moonBeam;
    public static TextureRegion backdrop, arena, mountains;

    // NightKnight Animation Texture Regions
    private static TextureRegion[] nightKnightIdleLeftFrames = new TextureRegion[8];
    private static TextureRegion[] nightKnightAttackLeftFrames = new TextureRegion[4];
    private static TextureRegion[] nightKnightRestFrames = new TextureRegion[8];
    private static TextureRegion[] nightKnightReadyFrames = new TextureRegion[18];
    private static TextureRegion[] nightKnightIdleRightFrames = new TextureRegion[8];
    private static TextureRegion[] nightKnightAttackRightFrames = new TextureRegion[4];

    // Skeleton Animation Texture Regions
    private static TextureRegion[] skeletonWalkLeftFrames = new TextureRegion[8];
    private static TextureRegion[] skeletonWalkRightFrames = new TextureRegion[8];
    private static TextureRegion[] skeletonDieLeftFrames = new TextureRegion[8];
    private static TextureRegion[] skeletonDieRightFrames = new TextureRegion[8];
    public static TextureRegion[] skeletonSwordSwingLeftFrames = new TextureRegion[2];
    public static TextureRegion[] skeletonSwordSwingRightFrames = new TextureRegion[2];

    // Level Animation Texture Regions
    private static TextureRegion[] moonFrames = new TextureRegion[2];
    private static TextureRegion[] moonBeamFrames = new TextureRegion[4];

    public static final TextureAtlas skeletonAtlas =
            new TextureAtlas(Gdx.files.internal("spritesheets/skeleton.atlas"));

    public static final TextureAtlas nightKnightAtlas =
            new TextureAtlas(Gdx.files.internal("spritesheets/nightknight.atlas"));

    public static final TextureAtlas levelAtlas =
            new TextureAtlas(Gdx.files.internal("spritesheets/level.atlas"));

    public static void load() {

        // Loading Sounds
        missSound = Gdx.audio.newSound(Gdx.files.internal("sounds/miss.wav"));
        strikeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/strike.mp3"));

        // Loading Animations/Textures

        // Skeleton Assets
        for (int i = 0; i < skeletonWalkLeftFrames.length; i++) {
            skeletonWalkLeftFrames[i] = skeletonAtlas.findRegion("Skele_Walk_Left"+i);
        }
        skeletonWalkLeft = new Animation(0.1f, skeletonWalkLeftFrames);


        for (int i = 0; i < skeletonWalkRightFrames.length; i++) {
            skeletonWalkRightFrames[i] = skeletonAtlas.findRegion("Skele_Walk_Right"+i);
        }
        skeletonWalkRight = new Animation(0.1f, skeletonWalkRightFrames);

        for (int i = 0; i < skeletonDieLeftFrames.length; i++) {
            skeletonDieLeftFrames[i] = skeletonAtlas.findRegion("Skele_Die_Left"+i);
        }
        skeletonDieLeft = new Animation(0.1f, skeletonDieLeftFrames);


        for (int i = 0; i < skeletonDieRightFrames.length; i++) {
            skeletonDieRightFrames[i] = skeletonAtlas.findRegion("Skele_Die_Right"+i);
        }
        skeletonDieRight = new Animation(0.1f, skeletonDieRightFrames);

        for (int i = 0; i < skeletonSwordSwingLeftFrames.length; i++) {
            skeletonSwordSwingLeftFrames[i] = skeletonAtlas.findRegion("Skele_Sword_Swing_Left"+i);
        }
        skeletonSwordSwingLeft = new Animation(0.05f, skeletonSwordSwingLeftFrames);


        for (int i = 0; i < skeletonSwordSwingRightFrames.length; i++) {
            skeletonSwordSwingRightFrames[i] = skeletonAtlas.findRegion("Skele_Sword_Swing_Right"+i);
        }
        skeletonSwordSwingRight = new Animation(0.05f, skeletonSwordSwingRightFrames);


        // -----------------------------------------------------------------------------------------

        // Night Knight Assets
        for (int i = 0; i < nightKnightIdleLeftFrames.length; i++) {
            nightKnightIdleLeftFrames[i] = nightKnightAtlas.findRegion("NK_IdleLeft"+i);
        }
        nightKnightIdleLeft = new Animation(0.1f, nightKnightIdleLeftFrames);


        for (int i = 0; i < nightKnightAttackLeftFrames.length; i++) {
            nightKnightAttackLeftFrames[i] = nightKnightAtlas.findRegion("NK_SwingLeft"+i);
        }
        nightKnightAttackLeft = new Animation(0.075f, nightKnightAttackLeftFrames);


        for (int i = 0; i < nightKnightRestFrames.length; i++) {
            nightKnightRestFrames[i] = nightKnightAtlas.findRegion("NK_GlowRest"+i);
        }
        nightKnightRest = new Animation(0.15f, nightKnightRestFrames);


        for (int i = 0; i < nightKnightReadyFrames.length; i++) {
            nightKnightReadyFrames[i] = nightKnightAtlas.findRegion("NK_GetReady"+i);
        }
        nightKnightReady = new Animation(0.1f, nightKnightReadyFrames);


        for (int i = 0; i < nightKnightIdleRightFrames.length; i++) {
            nightKnightIdleRightFrames[i] = nightKnightAtlas.findRegion("NK_IdleRight"+i);
        }
        nightKnightIdleRight = new Animation(0.1f, nightKnightIdleRightFrames);


        for (int i = 0; i < nightKnightAttackRightFrames.length; i++) {
            nightKnightAttackRightFrames[i] = nightKnightAtlas.findRegion("NK_SwingRight"+i);
        }
        nightKnightAttackRight = new Animation(0.075f, nightKnightAttackRightFrames);
        nightKnightAttackRight.setPlayMode(Animation.PlayMode.NORMAL);


        // -----------------------------------------------------------------------------------------

        // Level Assets
        for (int i = 0; i < moonFrames.length; i++) {
            moonFrames[i] = levelAtlas.findRegion("moon"+i);
        }
        moon = new Animation(3f, moonFrames);

        for (int i = 0; i < moonBeamFrames.length; i++) {
            moonBeamFrames[i] = levelAtlas.findRegion("moon_beam"+i);
        }
        moonBeam = new Animation(0.5f, moonBeamFrames);

        backdrop = new TextureRegion(levelAtlas.findRegion("backdrop"));

        arena = new TextureRegion(levelAtlas.findRegion("arena"));

        mountains = new TextureRegion(levelAtlas.findRegion("mountains"));

    }

    public static void dispose() {
        skeletonAtlas.dispose();
        nightKnightAtlas.dispose();
        levelAtlas.dispose();
    }

}
