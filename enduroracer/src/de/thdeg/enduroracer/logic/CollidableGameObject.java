package de.thdeg.enduroracer.logic;

import java.awt.*;

/**
 * Represents all game objects that are able to collide with something.
 */
public abstract class CollidableGameObject extends GameObject implements Cloneable {
    protected Polygon hitBox;

    protected CollidableGameObject(GameView gameView) {
        super(gameView);
    }

    /**
     * Allows to set a custom hitbox
     *
     * @param x, the x-values
     * @param y, the y-values
     */
    public void setHitBox(int[] x, int[] y) {
        this.hitBox = new Polygon(x, y, x.length);
    }

    /**
     * Allows to set a custom hitbox
     *
     * @param p, the polygon
     */
    public void setHitBox(Polygon p) {
        this.hitBox = p;
    }

    @Override
    public void update() {
        super.update();
        updateHitBoxPosition();
    }

    @Override
    public CollidableGameObject clone() {
        return (CollidableGameObject) super.clone();
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

    /**
     * Used to update the hitBox position of the game object.
     */
    protected abstract void updateHitBoxPosition();

    /**
     * If a GameObject has collided with something, it is able to react to the collision.
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject otherObject);

    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        super.adaptPosition(adaptX, adaptY);
        updateHitBoxPosition();
    }
}
