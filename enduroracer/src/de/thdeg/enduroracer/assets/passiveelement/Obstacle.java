package de.thdeg.enduroracer.assets.passiveelement;

import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class to create obstacles and manage them
 */
public class Obstacle extends Perspective {
    protected String image;

    /**
     * Default Constructor for the Obstacle
     */
    protected Obstacle(GameView gameView, double[] x, double[] y, Color colour, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, x, y, colour, objectsToCollideWith);
        this.speedInPixel = 0;
        this.rotation = 0;
        this.height = (int) (this.size);
        this.width = (int) (this.size);
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas(this.image, this.scaledX[2], this.scaledY[2], (Math.abs(this.scaledX[1] - this.scaledX[2])) / this.size, this.rotation);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if (this.gameView.timerExpired("PlayerHit", "Obstacle")) {
            this.gameView.setTimer("PlayerHit", "Obstacle", 150);
            this.gamePlayManager.getGameObjectManager().getPlayer().gotHit();
        }
    }
}
