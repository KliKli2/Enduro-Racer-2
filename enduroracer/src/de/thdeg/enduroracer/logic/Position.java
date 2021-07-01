package de.thdeg.enduroracer.logic;

/**
 * Class to determine the position of each object on the screen
 */
public class Position implements Cloneable, Comparable<Position> {
    private double x;
    private double y;

    /**
     * Return the y value of the position
     *
     * @return the y value
     */
    public double getY() {
        return y;
    }

    /**
     * Set a new y value for the position
     *
     * @param y the new y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the x value of the position
     *
     * @return the x value
     */
    public double getX() {
        return x;
    }

    /**
     * Set a new x value of the position
     *
     * @param x the new x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method to calculate the distance between two Positions
     *
     * @param other the other Position
     * @return the distance between both Positions
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Constructor with specified x and y value
     *
     * @param x the x value
     * @param y the y value
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Position clone() {
        try {
            return (Position) super.clone();
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
        return "Position (" + (int) Math.round(this.x) + ", " + (int) Math.round(this.y) + ")";
    }

    @Override
    public int compareTo(Position o) {
        return (int) Math.signum(Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)) - Math.sqrt(Math.pow(o.getX(), 2) + Math.pow(o.getY(), 2)) + distance(this));
    }
}
