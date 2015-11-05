package com.skylarkingstudios.nightknight.game;


import com.badlogic.gdx.utils.Timer;
import com.skylarkingstudios.nightknight.helpers.Assets;
import com.skylarkingstudios.nightknight.objects.Night;
import com.skylarkingstudios.nightknight.objects.Skeleton;
import com.skylarkingstudios.nightknight.objects.SkeletonFactory;
import com.skylarkingstudios.nightknight.util.Constants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class WorldController {

    private int testing = 0;
    private boolean debugToggle;
    private Set<Skeleton> skeletons;
    private Night night;
    private float resetter;
    private float startTime;
    private float spawnTime;
    private Random random;
    private Iterator<Skeleton> iterator;
    private int moonBeamY;
    private float timer;
    public enum GameState {
        PLAYING, PAUSED, GAME_OVER, WAITING, STARTING
    }
    private GameState state;
    private int score;

    public WorldController() {
        night = new Night();
        initializeGame();
    }

    private void initializeGame() {
        state = GameState.WAITING;
        timer = 0; // second
        moonBeamY = Constants.SCREEN_HEIGHT;
        night.init();
        debugToggle = false;
        resetter = 0;
        score = 0;
        startTime = 0;
        skeletons = new HashSet<Skeleton>();
        spawnTime = .5f;
        random = new Random();
    }

    public void update(float delta) {

        // Improve handling of Game States. Make them more self contained.
        switch (state) {
            // GAME OVER logic
            case GAME_OVER:
                if (moonBeamY < Constants.SCREEN_HEIGHT) {
                    moonBeamY += 10;
                }
            break;

            // PAUSED logic
            case PAUSED:
            break;

            // WAITING logic
            case WAITING:
                night.update(delta);
            break;

            // STARTING logic
            case STARTING:
                if (night.getState()!= Night.NightState.READY)
                    setGameState(GameState.PLAYING);
            // PLAYING logic
            case PLAYING:
                night.update(delta);
                if (startTime < 2.0f)
                    startTime += delta;
                else
                    resetter += delta;

                if (resetter > spawnTime) {
                    skeletons.add(SkeletonFactory.SF.makeSkeleton());
                    resetter = 0;
                    spawnTime = random.nextFloat() * (.7f - .4f) + .4f;         // Random float between .5f and .7f
                }

                for (iterator = skeletons.iterator(); iterator.hasNext(); ) {
                    Skeleton skeleton = iterator.next();
                    skeleton.update(delta);

            // Working on better skeleton logic. Still buggy
//                    if (skeleton.getDirection().equals("Left")) {
//                        skeleton.goLeft();
//                        if (skeleton.getBoundX() < Night.x + Night.getWidth() + 60 && night.getFatalRight()) {
//                            skeleton.setState(Skeleton.SkeletonState.DYING_LEFT);
//                        }
//                        if (skeleton.getBoundX() == Night.x + Night.getWidth() + 8) {       // The Point you hit Night Knight
//                            if (skeleton.getState() == Skeleton.SkeletonState.DYING_LEFT) {
//                                iterator.remove();
//                                score++;
//                            }
//                            else {
//                                // Dispose Game Objects somehow?
//                                if (skeleton.getState() == Skeleton.SkeletonState.WALKING_LEFT) {
//                                    skeleton.setState(Skeleton.SkeletonState.ATTACKING_LEFT);
//                                    night.setState(Night.NightState.DYING_LEFT);
//                                }
//                                else if (skeleton.getSkeletonAnimation().isAnimationFinished(skeleton.getFrame())) {
//                                    state = GameState.GAME_OVER;
//                                }
//                                skeleton.goRight();
//                            }
//                        }
//                    }

            // Skeletons Walking Left Logic
                    if (skeleton.getDirection().equals("Left")) {
                        skeleton.goLeft();
                        if (skeleton.getState() == Skeleton.SkeletonState.DYING_LEFT) {                                 // Skeleton is dying
                            if (skeleton.getBoundX() == Night.x + Night.getWidth() + 8) {
                                iterator.remove();
                            }
                            else {
                                // if he's not dead yet (maybe do something here if I don't add generic animation
                            }
                        }
                        else if (skeleton.getBoundX() < Night.x + Night.getWidth() + 60 && night.getFatalRight()) {     // You hit Skeleton
                            score++;
                            skeleton.setState(Skeleton.SkeletonState.DYING_LEFT);
                        }
                        else if (skeleton.getBoundX() == Night.x + Night.getWidth() + 8) {                              // Skeleton hits You
                            if (skeleton.getState() == Skeleton.SkeletonState.WALKING_LEFT) {
                                skeleton.setState(Skeleton.SkeletonState.ATTACKING_LEFT);
                                night.setState(Night.NightState.DYING_LEFT);
                            }
                            else if (skeleton.getSkeletonAnimation().isAnimationFinished(skeleton.getFrame())) {
                                state = GameState.GAME_OVER;
                            }
                            skeleton.goRight();
                        }

                    }

            // Skeletons Walking Right Logic
                    else {
                        skeleton.goRight();
                        if (skeleton.getBoundRightX() > Night.x - 60 && night.getFatalLeft()) {
                            skeleton.setState(Skeleton.SkeletonState.DYING_RIGHT);
                        }
                        if ((skeleton.getBoundRightX()) == Night.x - 8) {
                            if (skeleton.getState() == Skeleton.SkeletonState.DYING_RIGHT) {
                                iterator.remove();
                                score++;
                            }
                            else {
                                // Dispose Game Objects somehow?
                                if (skeleton.getState() == Skeleton.SkeletonState.WALKING_RIGHT) {
                                    skeleton.setState(Skeleton.SkeletonState.ATTACKING_RIGHT);
                                    night.setState(Night.NightState.DYING_RIGHT);
                                }
                                else if (skeleton.getSkeletonAnimation().isAnimationFinished(skeleton.getFrame())) {
                                    state = GameState.GAME_OVER;
                                }
                                skeleton.goLeft();
                            }
                        }
                    }
                }
                if (moonBeamY > 120) {
                    moonBeamY -= 10;
                }
            break;

        }
    }

    public Set<Skeleton> getSkeletons() {
        return skeletons;
    }

    public Night getNight() {
        return night;
    }

    public GameState getGameState() {
        return state;
    }

    public void setGameState(GameState newState) {
        state = newState;
    }

    public boolean getDebug() {
        return debugToggle;
    }

    public int getMoonBeamY() {
        return moonBeamY;
    }

    public void restartGame() {
        initializeGame();
    }

    public int getScore() {
        return score;
    }




}
