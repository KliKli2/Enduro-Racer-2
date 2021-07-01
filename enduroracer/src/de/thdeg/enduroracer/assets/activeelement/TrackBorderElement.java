package de.thdeg.enduroracer.assets.activeelement;

import de.thdeg.enduroracer.assets.passiveelement.Perspective;
import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for the unique border elements of teh track
 */
public class TrackBorderElement extends Perspective {

    /**
     * Default constructor for the Track elements
     * @param gameView , the gameView object
     * @param x , the X-Values
     * @param y , the Y-Values
     * @param colour , the hitbox colour
     * @param objectsToCollideWith , the list with collisionobjects
     */
    public TrackBorderElement(GameView gameView, double[] x, double[] y, Color colour, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, x, y, colour, objectsToCollideWith);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return Arrays.toString(this.x) + " : " + Arrays.toString(this.y);
    }
}
