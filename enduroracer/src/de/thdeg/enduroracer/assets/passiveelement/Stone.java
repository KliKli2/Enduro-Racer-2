package de.thdeg.enduroracer.assets.passiveelement;

import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.util.ArrayList;

/**
 * The class for the Stone obstacle
 */
class Stone extends Obstacle {

    /**
     * Default constructor for the Stones
     * @param gameView , the gameView object
     * @param x , the X-Values
     * @param y , the Y-Values
     * @param colour , the hitbox colour
     * @param objectsToCollideWith , the list with collisionobjects
     */
    public Stone(GameView gameView, double[] x, double[] y, Color colour, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, x, y, colour, objectsToCollideWith);
        this.size = 90 * 2.0;
        this.image = "stone.png";
    }
}
