package de.thdeg.enduroracer.assets.passiveelement;

/**
 * Class to manage different levels and their features
 */
public class Level {
    private final String name;
    private final int enemies;
    private final int background;
    private final int obstacles;
    private final String music;

    /**
     * Get the name of the level
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the number of the total enemies
     *
     * @return the number
     */
    public int getEnemies() {
        return enemies;
    }

    /**
     * Get the name of the background image
     *
     * @return the name
     */
    public int getBackground() {
        return background;
    }

    /**
     * Get the name of the music
     *
     * @return the name
     */
    public String getMusic() {
        return music;
    }

    /**
     * Gets the number of obstacles on the track
     * @return , the number of obstacles
     */
    public int getObstacles() {
        return obstacles;
    }

    /**
     * Constructor for the level object
     *
     * @param name       name of the level
     * @param enemies    number of enemies in the level
     * @param obstacles  number of obstacles in teh level
     * @param background index of the background image
     * @param music      name of the backgroundmusic
     */
    public Level(String name, int enemies, int obstacles, int background, String music) {
        this.enemies = enemies;
        this.name = name;
        this.background = background;
        this.music = music;
        this.obstacles = obstacles;
    }
}
