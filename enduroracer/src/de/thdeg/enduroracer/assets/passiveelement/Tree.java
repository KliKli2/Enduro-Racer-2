package de.thdeg.enduroracer.assets.passiveelement;

import de.thdeg.enduroracer.logic.BlockImages;
import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.util.ArrayList;

/**
 * The class for the Tree obstacle
 */
class Tree extends Obstacle {

    /**
     * Default constructor for the Trees
     * @param gameView , the gameView object
     * @param x , the X-Values
     * @param y , the Y-Values
     * @param colour , the hitbox colour
     * @param objectsToCollideWith , the list with collisionobjects
     */
    public Tree(GameView gameView, double[] x, double[] y, Color colour, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, x, y, colour, objectsToCollideWith);
        this.image = "tree.png";
        this.size = 81;
    }
}
