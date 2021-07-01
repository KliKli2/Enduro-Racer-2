package de.thdeg.enduroracer.logic;

import de.thdeg.enduroracer.logic.managers.GamePlayManager;

/**
 * The parent class for all gameassets
 */
public abstract class GameObject implements Cloneable {
    protected Position position;
    protected double speedInPixel;
    protected final GameView gameView;
    protected double size;
    protected double rotation;
    protected int width;
    protected int height;
    protected GamePlayManager gamePlayManager;

    /**
     * Method to assign a GamePlayManager for each object
     *
     * @param gamePlayManager the gameplaymanager to manage gameplay
     */
    public void setGamePlayManager(GamePlayManager gamePlayManager) {
        this.gamePlayManager = gamePlayManager;
    }

    /**
     * The default constructor for the GameObject class
     *
     * @param gameView the GameView canvas to be used
     */
    protected GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0, 0);
    }

    /**
     * Updates the position of the object and the status
     */
    public void update() {
        if (this instanceof MovingGameObject) {
            ((MovingGameObject) this).updatePosition();
        }
        this.updateStatus();
    }

    /**
     * Method to move an object whenever the gameworld moves
     *
     * @param adaptX the x-value to be moved by
     * @param adaptY the y-value to be moved by
     */
    public void adaptPosition(double adaptX, double adaptY) {
        this.position.setX(this.position.getX() + adaptX);
        this.position.setY(this.position.getY() + adaptY);
    }

    /**
     * Method to update the status of the object
     */
    protected abstract void updateStatus();

    /**
     * Method to show the object on the canvas
     */
    public abstract void addToCanvas();

    /**
     * Getting the position of the object
     *
     * @return the position of the object
     */
    public Position getPosition() {
        return this.position;
    }


    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
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
    public String toString() {
        String position = this.position.toString();
        return this.getClass().getSimpleName() + ": " + position;
    }
}
