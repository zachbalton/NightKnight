package com.skylarkingstudios.nightknight.objects;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.skylarkingstudios.nightknight.helpers.Assets;
import com.skylarkingstudios.nightknight.util.Constants;

public class Skeleton {

    public enum SkeletonState {
        WALKING_LEFT,
        WALKING_RIGHT,
        ATTACKING_LEFT,
        ATTACKING_RIGHT,
        DYING_LEFT,
        DYING_RIGHT,
        VICTORY_RIGHT,
        VICTORY_LEFT
    }

    private SkeletonState state;
    private Animation skeletonAnimation;

    private float frame;
    private boolean isLooping;

    private boolean walkingLeftInit, walkingRightInit, attackingLeftInit, attackingRightInit,
            dyingLeftInit, dyingRightInit;

    private String direction;
    private int x = 0;
    private int y = 120;
    private int walkSpeed = 4;
    private int boundX;
    private int boundY = 192;
    private int boundRightX = 0;
    private float timer = 0F;

    private static Animation walkLeftAnimation = Assets.skeletonWalkLeft;
    private static Animation walkRightAnimation = Assets.skeletonWalkRight;
    private Animation walkAnimation;
    private int width = Constants.SKELE_WIDTH - 108;
    private int height = Constants.SKELE_HEIGHT;



    public Skeleton(String direction) {

        if (direction.equals("Left")) {
            this.direction = direction;
            walkAnimation = walkLeftAnimation;
            x = Constants.SCREEN_WIDTH;
            boundX = x + 54;
            boundRightX = boundX + width;
            state = SkeletonState.WALKING_LEFT;
        } else {
            this.direction = "Right";
            walkAnimation = walkRightAnimation;
            x = 0 - Constants.SKELE_WIDTH;
            boundX = x + 54;
            boundRightX = boundX + width;
            state = SkeletonState.WALKING_RIGHT;
        }
        init();
    }

    public void init() {
        walkingLeftInit = false;
        walkingRightInit = false;
        attackingLeftInit = false;
        attackingRightInit = false;
        dyingLeftInit = false;
        dyingRightInit = false;
        frame = 0;
        isLooping = true;
    }

    public void update(float runTime) {

        switch (state) {
            case WALKING_LEFT:
                frame+=runTime;
                if (!walkingLeftInit) {
                    isLooping = true;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonWalkLeft);
                    walkingLeftInit = true;
                }
            break;

            case WALKING_RIGHT:
                frame+=runTime;
                if (!walkingRightInit) {
                    isLooping = true;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonWalkRight);
                    walkingRightInit = true;
                }
            break;

            case DYING_LEFT:
                frame+=runTime;
                if (!dyingLeftInit) {
                    isLooping = false;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonDieLeft);
                    dyingLeftInit = true;
                }
            break;

            case DYING_RIGHT:
                frame+=runTime;
                if (!dyingRightInit) {
                    isLooping = false;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonDieRight);
                    dyingRightInit = true;
                }
            break;

            case ATTACKING_LEFT:
                frame+=runTime;
                if (!attackingLeftInit) {
                    isLooping = false;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonSwordSwingLeft);
                    attackingLeftInit = true;
                }

                if (Assets.skeletonSwordSwingLeft.isAnimationFinished(frame)) {
                    state = SkeletonState.VICTORY_LEFT;
                }

            break;

            case ATTACKING_RIGHT:
                frame+=runTime;
                if (!attackingRightInit) {
                    isLooping = false;
                    frame = 0;
                    setSkeletonAnimation(Assets.skeletonSwordSwingRight);
                    attackingRightInit = true;
                }

                if (Assets.skeletonSwordSwingRight.isAnimationFinished(frame)) {
                    state = SkeletonState.VICTORY_RIGHT;
                }

            break;
        }


    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void goLeft() {
        x-=walkSpeed;
        boundX-=walkSpeed;
        boundRightX-=walkSpeed;
    }
    public void goRight() {
        x+=walkSpeed;
        boundX+=walkSpeed;
        boundRightX+=walkSpeed;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Rectangle boundingBox() {
        return new Rectangle(x, y, width, height);
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public String getDirection() {
        return direction;
    }

    public int getBoundX() {
        return boundX;
    }
    public int getBoundY() {
        return boundY;
    }

    public int getBoundRightX() {
        return boundRightX;
    }

    public void setAnimation(Animation animation) {
        this.walkAnimation = animation;
    }

    public void setSkeletonAnimation(Animation skeletonAnimation) {
        this.skeletonAnimation = skeletonAnimation;
    }

    public Animation getSkeletonAnimation() {
        return skeletonAnimation;
    }

    public float getFrame() {
        return frame;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public void setState(SkeletonState state){
        this.state = state;
    }

    public SkeletonState getState() {
        return state;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

}
