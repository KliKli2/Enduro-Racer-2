package de.thdeg.enduroracer.assets.passiveelement;

import de.thdeg.enduroracer.logic.CollidableGameObject;
import de.thdeg.enduroracer.logic.CollidingGameObject;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.MovingGameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class to deal with the perspective transformation
 */
public class Perspective extends CollidingGameObject implements MovingGameObject {

    protected double[] x;
    protected double[] y;
    protected double[] z;

    protected double[] scaledX;
    protected double[] scaledY;
    protected double focal;
    protected Color colour;

    protected Perspective(GameView gameView, double[] x, double[] y, Color colour, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.z = new double[]{-500, -500, -500, -500};
        this.scaledX = new double[x.length];
        this.scaledY = new double[y.length];
        double theta = 60;
        this.focal = -Math.cos(0.5 * theta) / Math.sin(0.5 * theta);
        setHitBox(calculateHitBox());
        scaleElement();
    }

    /**
     * Method to get the X-Values
     * @return , the X-values
     */
    public double[] getX() {
        return x;
    }

    /**
     * Method to get the Y-Values
     * @return , the Y-values
     */
    public double[] getY() {
        return y;
    }

    /**
     * Method to transform the normal xyz-coordinates into the used xy-coordinates for the visible plane
     */
    public void scaleElement() {
        for (int i = 0; i < this.x.length; i++) {
            if (this.y[i] < 0 && this.y[i] > -3000 && Math.abs(this.x[i]) < 3000) {
                this.scaledX[i] = (((this.focal * this.x[i] / this.y[i]) + 1) * (GameView.WIDTH / 2.0) + (GameView.WIDTH / 32.0));
                this.scaledY[i] = (((this.focal * this.z[i] / this.y[i]) + 1) * (GameView.HEIGHT / 4.0) + (GameView.HEIGHT / 4.0));
            }
        }
        updateHitBoxPosition();
    }

    /**
     * Method to rotate the xyz-coordinates with the rotation matrix
     * @param rot, the angle for the rotation
     */
    public void rotateElement(double rot) {
        rot = rot * 180 / Math.PI;
        double[][][] rotMatrix = new double[][][]{
                new double[][]{
                        new double[]{1, 0, 0},
                        new double[]{0, Math.cos(rot), -Math.sin(rot)},
                        new double[]{0, Math.sin(rot), Math.cos(rot)}
                },
                new double[][]{
                        new double[]{Math.cos(rot), 0, Math.sin(rot)},
                        new double[]{0, 1, 0},
                        new double[]{-Math.sin(rot), 0, Math.cos(rot)}
                },
                new double[][]{
                        new double[]{Math.cos(rot), -Math.sin(rot), 0},
                        new double[]{Math.sin(rot), Math.cos(rot), 0},
                        new double[]{0, 0, 1}
                }
        };

        int rotPoint = 1;

        for (int k = 0; k < this.x.length; k++) {
            double d1 = rotMatrix[rotPoint][0][0] * this.x[k] + rotMatrix[rotPoint][0][1] * this.z[k] + rotMatrix[rotPoint][0][2] * this.y[k];
            double d3 = rotMatrix[rotPoint][1][0] * this.x[k] + rotMatrix[rotPoint][1][1] * this.z[k] + rotMatrix[rotPoint][1][2] * this.y[k];
            double d2 = rotMatrix[rotPoint][2][0] * this.x[k] + rotMatrix[rotPoint][2][1] * this.z[k] + rotMatrix[rotPoint][2][2] * this.y[k];
            this.x[k] = d1;
            this.y[k] = d2;
            this.z[k] = d3;
        }
        scaleElement();
    }

    private Polygon calculateHitBox() {
        return new Polygon(doubleToInt(this.x), doubleToInt(this.y), this.x.length);
    }

    /**
     * Method to cast a double-Array into the corresponding int-Array
     * @param doubles, the double-Array
     * @return , the casted int-Array
     */
    public int[] doubleToInt(double[] doubles) {
        if (doubles == null) {
            return null;
        }
        int[] ret = new int[doubles.length];
        for (int i = 0; i < doubles.length; i++) {
            ret[i] = (int) doubles[i];
        }
        return ret;
    }

    @Override
    public Perspective clone() {
        return (Perspective) super.clone();
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
    public void adaptPosition(double adaptX, double adaptY) {
        for (int i = 0; i < this.x.length; i++) {
            this.x[i] += adaptX;
            this.y[i] += adaptY;
        }
        scaleElement();
    }

    @Override
    protected void updateHitBoxPosition() {
        setHitBox(doubleToInt(this.x), doubleToInt(this.y));
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    @Override
    protected void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        this.gameView.addPolygonToCanvas(this.scaledX, this.scaledY, 5, true, this.colour);
    }

    @Override
    public void updatePosition() {

    }
}
