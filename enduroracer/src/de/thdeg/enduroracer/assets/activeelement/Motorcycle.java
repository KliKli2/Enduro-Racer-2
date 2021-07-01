package de.thdeg.enduroracer.assets.activeelement;

import de.thdeg.enduroracer.logic.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class to manage the general motorcycles, from enemies and the player itself
 */
public class Motorcycle extends CollidingGameObject implements Cloneable {

    protected int maxRot;
    protected int rotSpeed;

    private boolean movingToRight;
    private boolean movingToLeft;

    private enum Status {
        STANDARD,
        JUMP
    }

    private enum JumpState {
        STANDARD,
        HALFH,
        HALFD,
        HIGH
    }

    private Status state;
    private JumpState jumpState;
    private double accelerator;

    /**
     * The constructor to create an Enemy
     *
     * @param gameView             GameView to show the player on
     * @param objectsToCollideWith List with objects to collide with
     */
    public Motorcycle(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.speedInPixel = 2;
        this.rotSpeed = 2;
        this.maxRot = 70;
        this.state = Status.STANDARD;
        this.jumpState = JumpState.STANDARD;
        this.speedInPixel = 1;
        this.rotation = 0;
        this.size = 4;
        this.accelerator = 1.01;
        this.height = (int) (21 * this.size);
        this.width = (int) (13 * this.size);
        this.position = new Position(GameView.WIDTH / 2.0, GameView.HEIGHT - this.height - 25);
        this.hitBox = new Polygon(
                new int[]{
                        -15,
                        -15,
                        15,
                        15
                },
                new int[]{
                        -40,
                        -20,
                        -20,
                        -40
                },
                4);
    }

    @Override
    public void addToCanvas() {
        switch (this.jumpState) {
            case STANDARD:
                if (this.movingToLeft) {
                    this.gameView.addImageToCanvas("player_curver.png", this.position.getX(), this.position.getY(), 0.5, 0);
                } else if (this.movingToRight) {
                    this.gameView.addImageToCanvas("player_curvel.png", this.position.getX(), this.position.getY(), 0.5, 0);
                } else {
                    this.gameView.addImageToCanvas("player.png", this.position.getX(), this.position.getY(), 0.5, 0);
                }

                break;
            case HALFH:
            case HALFD:
                this.gameView.addImageToCanvas("player.png", this.position.getX(), this.position.getY() - this.height, 0.5, 0);
                break;
            case HIGH:
                this.gameView.addImageToCanvas("player_high.png", this.position.getX(), this.position.getY() - this.height*1.5, 0.5, 0);
                break;
        }
    }

    /**
     * Moves the player into a certain direction
     *
     * @param direction the integer that tells which direction o move to
     *                  0 - left
     *                  1 - right
     *                  2 - up
     *                  3 - down
     */
    public void updatePosition(int direction) {
        switch (direction) {
            case -1:
                jump();
                break;
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    @Override
    public void update() {
        if (this.gameView.timerExpired("DefaultSpeeding", "Main")) {
            this.gameView.setTimer("DefaultSpeeding", "Main", 50);
            this.gamePlayManager.getGameObjectManager().getPlayer().setSpeed(this.speedInPixel);
            if (this.gameView.timerExpired("Collision", "Main")) {
                this.gameView.setTimer("Collision", "Main", 150);
                super.update();
            }
            this.accelerator = Math.max(this.accelerator - 0.05, 0);
            this.speedInPixel = Math.max(this.speedInPixel - 0.1, 0);
            if (this.gamePlayManager != null) {
                this.gamePlayManager.getGameObjectManager().moveWorld(0, this.speedInPixel);
            }
        }
    }

    private void jumpAnimation() {
        switch (this.jumpState) {
            case STANDARD:
                this.jumpState = JumpState.HALFH;
                this.canCollide = false;
                break;
            case HALFH:
                this.jumpState = JumpState.HIGH;
                this.canCollide = false;
                break;
            case HIGH:
                this.jumpState = JumpState.HALFD;
                this.canCollide = false;
                break;
            case HALFD:
                this.jumpState = JumpState.STANDARD;
                this.canCollide = true;
                break;
        }
    }

    @Override
    public void updateStatus() {
        if (this.state == Status.JUMP) {
            if (this.gameView.timerExpired("Jump", "Main")) {
                this.gameView.setTimer("Jump", "Main", 500);
                jumpAnimation();
                if (this.jumpState == JumpState.STANDARD) {
                    this.state = Status.STANDARD;
                }
            }
        }
    }

    private void jump() {
        this.state = Status.JUMP;
    }

    /**
     * Reacts to userinput and moves the character to the left.
     */
    private void right() {
        this.speedInPixel = Math.max(this.speedInPixel - 0.1, 0);
        this.movingToLeft = true;
        this.gamePlayManager.getGameObjectManager().turnWorld(Math.max(-0.001, -(this.speedInPixel / 15000)));
    }

    /**
     * Reacts to userinput and moves the character to the left.
     */
    private void left() {
        this.speedInPixel = Math.max(this.speedInPixel - 0.1, 0);
        this.movingToRight = true;
        this.gamePlayManager.getGameObjectManager().turnWorld(Math.min(0.001, (this.speedInPixel / 15000)));
    }

    /**
     * Reacts to userinput and moves the character to the left.
     */
    private void up() {
        if (this.gameView.timerExpired("Speedup", "MainMotorcycle")) {
            this.gameView.setTimer("Speedup", "MainMotorcycle", 100);
            this.movingToRight = false;
            this.movingToLeft = false;
            this.accelerator = Math.min(this.accelerator + 0.15, 3);
            this.speedInPixel = Math.min(this.speedInPixel + this.accelerator, 15);
        }
    }

    /**
     * Reacts to userinput and moves the character to the left.
     */
    private void down() {
        this.speedInPixel /= 12;
    }

    /**
     * return the list Collisiondetections
     */
    public ArrayList<CollidableGameObject> getObjectsToCollideWith() {
        return this.objectsToCollideWith;
    }

    @Override
    protected void updateHitBoxPosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
    }

    @Override
    public Motorcycle clone() {
        return (Motorcycle) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (object.getClass() != this.getClass()) {
            return false;
        } else if (this == null) {
            return false;
        } else return this == object;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
