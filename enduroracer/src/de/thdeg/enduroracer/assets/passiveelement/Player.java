package de.thdeg.enduroracer.assets.passiveelement;

/**
 * Class to keep track of the player's stats
 */
public class Player {
    private double score;
    private double speed;
    private int levelCompleted;
    private double time;
    private int hits;
    private String name;

    /**
     * Default constructor for the player information
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.speed = 0;
        this.levelCompleted = 0;
        this.time = 0;
        this.hits = 0;
    }

    /**
     * Counts the hits during the race
     */
    public void gotHit() {
        this.hits += 1;
    }

    /**
     * Defines how many levels the player has finished
     */
    public void levelFinished() {
        this.levelCompleted += 1;
    }

    /**
     * Get the score for the scoreboard
     * @return , the player score
     */
    public double getScore() {
        return score;
    }

    /**
     * Get the current speed of the player
     * @return , the player speed
     */
    public double getSpeed() {
        return Math.round(speed * 100) / 100.0;
    }

    /**
     * Get the driven time from the player
     * @return , the driven time
     */
    public double getTime() {
        return time;
    }

    /**
     * Set a new speed for the player
     * @param speedInPixel , the new speed
     */
    public void setSpeed(double speedInPixel) {
        this.speed = speedInPixel;
    }

    /**
     * Set a new score for the player
     * @param adaptY , the new score
     */
    public void setScore(double adaptY) {
        this.score += adaptY;
    }

    /**
     * Get the name of the player
     * @param level , the level, that is driven on
     * @return , the String with the player name
     */
    public String getName(String level) {
        return this.name + " (" + level + ")";
    }
}
