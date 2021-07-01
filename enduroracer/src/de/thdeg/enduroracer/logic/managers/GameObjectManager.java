package de.thdeg.enduroracer.logic.managers;

import de.thdeg.enduroracer.assets.activeelement.Enemy;
import de.thdeg.enduroracer.assets.activeelement.Motorcycle;
import de.thdeg.enduroracer.assets.passiveelement.Track;
import de.thdeg.enduroracer.assets.passiveelement.Obstacle;
import de.thdeg.enduroracer.assets.passiveelement.Player;
import de.thdeg.enduroracer.assets.ui.Background;
import de.thdeg.enduroracer.assets.ui.Overlay;
import de.thdeg.enduroracer.assets.ui.Scoreboard;
import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.MovingGameObject;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class to manage all existing GameObjects
 */
public class GameObjectManager {
    private final Motorcycle motorcycle;
    private final LinkedList<Enemy> enemies;
    private final LinkedList<Obstacle> obstacles;
    private final Track track;
    private final LinkedList<GameObject> gameObjects;
    private final Background background;
    private final Scoreboard scoreboard;
    private Player player;
    private final Overlay overlay;

    /**
     * Constructor to initialize all GameObjects
     *
     * @param gameView the gameView canvas used for the game
     */
    public GameObjectManager(GameView gameView) {
        this.motorcycle = new Motorcycle(gameView, new ArrayList<>());
        this.background = new Background(gameView);
        this.enemies = new LinkedList<>();
        this.obstacles = new LinkedList<>();
        this.track = new Track(gameView, 10, 5);
        this.gameObjects = new LinkedList<>();
        this.scoreboard = new Scoreboard(gameView);
        this.overlay = new Overlay(gameView);
    }

    /**
     * Method to set and reset the Player
     * @param name , name of the new Player
     */
    public void setPlayer(String name){
        this.player = new Player(name);
    }

    /**
     * Get the overlay object
     * @return , the overlay object
     */
    public Overlay getOverlay() {
        return overlay;
    }

    /**
     * Get get Background Object
     *
     * @return the background object
     */
    public Background getBackground() {
        return this.background;
    }

    /**
     * Get the Player Object
     * @return , the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get get player Object
     *
     * @return the player object
     */
    public Motorcycle getMotorcycle() {
        return this.motorcycle;
    }

    /**
     * Get a list of all existing enemies
     *
     * @return the enemy list
     */
    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get a list of all existing obstacles
     *
     * @return the obstacle list
     */
    public LinkedList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Get a list of all existing tracks
     *
     * @return the track
     */
    public Track getTrack() {
        return track;
    }

    /**
     * A method to move and update all existing GameObjects
     */
    public void updateGameObjects() {
        this.gameObjects.clear();
        this.gameObjects.add(this.track);
        this.gameObjects.add(this.background);
        this.gameObjects.add(this.scoreboard);
        this.gameObjects.addAll(this.track.getEnemies());
        this.gameObjects.addAll(this.track.getObstacles());
        this.gameObjects.add(this.motorcycle);
        this.gameObjects.add(this.overlay);
        this.gameObjects.forEach((gameObject) -> {
            gameObject.update();
            gameObject.addToCanvas();
        });
        this.motorcycle.getObjectsToCollideWith().addAll(this.enemies);
        for (Obstacle o : this.getTrack().getObstacles()) {
            this.motorcycle.getObjectsToCollideWith().add(o);
        }
    }

    /**
     * Move the worldmap and with that all the necessary objects
     *
     * @param adaptX the x-value to be moved by
     * @param adaptY the y-value to be moved by
     */
    public void moveWorld(double adaptX, double adaptY) {
        this.gameObjects.forEach((gameObject) -> {
            if (gameObject instanceof MovingGameObject) {
                gameObject.adaptPosition(adaptX, adaptY);
            }
        });
    }

    /**
     * Turn the worldmap and with that all the necessary objects
     *
     * @param rot value everything is rotated by
     */
    public void turnWorld(double rot) {
        this.background.turn(rot);
        this.track.turn(rot);
    }

    /**
     * Get the Scoreboard object
     * @return , the scoreboard object
     */
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }
}
