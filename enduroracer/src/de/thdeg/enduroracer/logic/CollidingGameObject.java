package de.thdeg.enduroracer.logic;

import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * An object that can actively collide with other objects, e.g. a shot.
 */
public abstract class CollidingGameObject extends CollidableGameObject implements Cloneable {
    protected final ArrayList<CollidableGameObject> objectsToCollideWith;
    protected boolean canCollide;

    protected CollidingGameObject(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView);
        this.objectsToCollideWith = objectsToCollideWith;
        this.canCollide = true;
    }

    @Override
    public void update() {
        super.update();
        checkCollisions();
    }

    @Override
    public CollidingGameObject clone() {
        return (CollidingGameObject) super.clone();
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
     * Used to check possible collisions that are actively caused by this game object.
     * Both parties are notified about the collision.
     */
    private void checkCollisions() {
        if (this.canCollide) {
            for (CollidableGameObject collidableGameObject : objectsToCollideWith) {
                if (collidesWith(collidableGameObject)) {
                    reactToCollision(collidableGameObject);
                    collidableGameObject.reactToCollision(this);
                }
            }
        }
    }

    /**
     * Determines if this game object is collided with the other game object.
     *
     * @param other The other game object.
     * @return <code>true</code> if the there was a collision.
     */
    protected final boolean collidesWith(CollidableGameObject other) {
        if (other.hitBox != null && this.hitBox != null) {
            Area area = new Area(this.hitBox);
            area.intersect(new Area(other.hitBox));
            return !area.isEmpty();
        } else {
            return false;
        }
    }
}