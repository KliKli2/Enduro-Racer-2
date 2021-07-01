package de.thdeg.enduroracer.assets.ui;

import de.thdeg.enduroracer.logic.BlockImages;
import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.Position;

import java.awt.*;

/**
 * Class to manage the general background
 */
public class Background extends GameObject {

    private int backGroundNumber;

    /**
     * The default constructor for the GameObject class
     *
     * @param gameView the GameView canvas to be used
     */
    public Background(GameView gameView) {
        super(gameView);
        this.position = new Position(0, 0);
        this.size = 240 * 4;
    }

    /**
     * Set a new background index
     * @param backGroundNumber , the new index
     */
    public void setBackGroundNumber(int backGroundNumber) {
        this.backGroundNumber = backGroundNumber;
    }

    @Override
    protected void updateStatus() {
        this.backGroundNumber = this.gamePlayManager.getLevelManager().getActiveLevel().getBackground();
    }

    /**
     * Method to move the background while rotating the world
     *
     * @param rot value to move the background direction
     */
    public void turn(double rot) {
        if (rot >= 0) {
            if (this.position.getX() - 5 * Math.abs(rot) <= -this.size * 0.5) {
                this.position.setX(0);
            } else {
                this.position.setX(this.position.getX() - 5 * Math.abs(rot));
            }
        } else {
            if (this.position.getX() + 5 * Math.abs(rot) >= this.size * 0.5) {
                this.position.setX(0);
            } else {
                this.position.setX(this.position.getX() + 5 * Math.abs(rot));
            }
        }
    }

    ;

    @Override
    public void addToCanvas() {
        if (this.backGroundNumber == 0) {
            this.gameView.setBackgroundColor(new Color(50, 235, 100));
            this.gameView.addRectangleToCanvas(0, 0, GameView.WIDTH, GameView.HEIGHT / 4.0, 2, true, new Color(147, 195, 214));
            this.gameView.addImageToCanvas("background1.png", this.position.getX() - this.size * 0.7, this.position.getY() + GameView.HEIGHT / 4.0, 0.7, 0);
            this.gameView.addImageToCanvas("background1.png", this.position.getX(), this.position.getY() + GameView.HEIGHT / 4.0, 0.7, 0);
            this.gameView.addImageToCanvas("background1.png", this.position.getX() + this.size * 0.7, this.position.getY() + GameView.HEIGHT / 4.0, 0.7, 0);
        } else {
            this.gameView.setBackgroundColor(new Color(189,189,189));
            this.gameView.addRectangleToCanvas(0, 0, GameView.WIDTH, GameView.HEIGHT / 4.0, 2, true, new Color(147, 195, 214));
            this.gameView.addImageToCanvas("background2.png", this.position.getX() - this.size * 0.8, this.position.getY() + GameView.HEIGHT / 4.0, 0.8, 0);
            this.gameView.addImageToCanvas("background2.png", this.position.getX(), this.position.getY() + GameView.HEIGHT / 4.0, 0.8, 0);
            this.gameView.addImageToCanvas("background2.png", this.position.getX() + this.size * 0.8, this.position.getY() + GameView.HEIGHT / 4.0, 0.8, 0);
        }
    }
}
