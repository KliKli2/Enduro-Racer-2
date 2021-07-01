package de.thdeg.enduroracer.assets.activeelement;

import de.thdeg.enduroracer.assets.passiveelement.Perspective;
import de.thdeg.enduroracer.logic.BlockImages;
import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class to create and manipulate enemies
 */
public class Enemy extends Perspective implements Cloneable {

    private double accelerator;

    /**
     * The constructor to create an Enemy
     */
    public Enemy(GameView gameView, double[] x, double[] y, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, x, y, Color.MAGENTA, objectsToCollideWith);
        this.rotation = 0;
        this.size = 82*10;
        /*this.height = (int) (21 * this.size);
        this.width = (int) (13 * this.size);*/
        this.accelerator = 1.3;
        this.speedInPixel = 3;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addImageToCanvas("enemy1.png", this.scaledX[2], this.scaledY[2], 0.5,0);
    }

    @Override
    public Enemy clone() {
        return (Enemy) super.clone();
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

    @Override
    public void update() {
        System.out.println("asdf");
        if (this.gameView.timerExpired("DefaultSpeeding", "Enemy")) {
            this.gameView.setTimer("DefaultSpeeding", "Enemy", 50);
            this.accelerator = Math.max(this.accelerator - 0.02, 0);
            this.speedInPixel = Math.max(this.speedInPixel - 0.01, 0);
            for(int i = 0; i < x.length; i ++){
                y[i] -= this.speedInPixel;
            }
            scaleElement();
        }
    }

    @Override
    public void updateStatus() {

    }
}
