package de.thdeg.enduroracer.assets.passiveelement;

import de.thdeg.enduroracer.assets.activeelement.Enemy;
import de.thdeg.enduroracer.assets.activeelement.TrackBorderElement;
import de.thdeg.enduroracer.assets.passiveelement.*;
import de.thdeg.enduroracer.logic.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to create Tracks
 */
public class Track extends CollidableGameObject implements MovingGameObject {

    /**
     * Constant to define the length of the track
     */
    public static final double MAXLENGTH = 13000;
    //{230, 280, 280, 230}, new double[]{-1, -1, -2795, -2745}
    // {200, 200, 150, 150}, new double[]{-400, -400, -450, -450}
    private static final double[][] POSSIBLE_PLACEMENTS_FOR_ENEMY_X = new double[][]{
            new double[]{200, 200, 150, 150},
            new double[]{-200, -200, -150, -150}
    };

    private static final double[][] POSSIBLE_PLACEMENTS_FOR_ENEMY_Y = new double[][]{
            new double[]{-40, -40, -55, -55},
            new double[]{-40, -40, -55, -55}
    };

    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<TrackBorderElement[][]> tr;
    private int numberOfObstacles;
    private int numberOfEnemies;

    /**
     * Constructor with values for the Track
     */
    public Track(GameView gameView, int numberOfObstacles, int numberOfEnemies) {
        super(gameView);
        this.numberOfEnemies = numberOfEnemies;
        this.numberOfObstacles = numberOfObstacles;
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.rotation = 0;
        this.size = 3;
        this.height = (int) (this.size);
        this.width = (int) (this.size);
        this.position = new Position(0, 0);
        this.rotation = 0;
        this.tr = new ArrayList<>();
        initializeObjects();
    }

    /**
     * Method to reset the track after Level is finished
     */
    public void reset() {
        this.gamePlayManager.getGameObjectManager().moveWorld(0, -this.gamePlayManager.getGameObjectManager().getPlayer().getScore());
        this.tr.clear();
        this.obstacles.clear();
        this.enemies.clear();
        initializeObjects();
    }

    /**
     * Method to set the maximum amount of obstacles
     * @param numberOfObstacles, number of obstacles
     */
    public void setNumberOfObstacles(int numberOfObstacles) {
        this.numberOfObstacles = numberOfObstacles;
    }

    /**
     * Method to set the maximum number of enemies
     * @param numberOfEnemies, number of enemies
     */
    public void setNumberOfEnemies(int numberOfEnemies) {
        this.numberOfEnemies = numberOfEnemies;
    }

    private void spawnEnemies() {
        ArrayList<CollidableGameObject> noInitializer = new ArrayList<>();
        for (int i = 0; i < POSSIBLE_PLACEMENTS_FOR_ENEMY_X.length; i++) {
            Enemy enemy = new Enemy(this.gameView, POSSIBLE_PLACEMENTS_FOR_ENEMY_X[i], POSSIBLE_PLACEMENTS_FOR_ENEMY_Y[i], noInitializer);
            this.enemies.add(enemy);
        }
    }

    private void plantTrees(){
        ArrayList<CollidableGameObject> noInitializer = new ArrayList<>();
        for (int i = 0; i < this.tr.get(0).length; i++) {
            for (TrackBorderElement t : this.tr.get(0)[i]) {
                if(new Random().nextBoolean() && t.getY()[0] < -400 && t.getY()[1] < -400 && t.getY()[2] < -400 && t.getY()[3] < -400) {
                    Tree tree = new Tree(this.gameView, t.getX(), t.getY(), Color.MAGENTA, noInitializer);
                    this.obstacles.add(tree);
                }
            }
        }
    }

    /**
     * Get the list of placed enemies
     * @return , the ArrayList with all enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Method to set all parameters for the used objects
     */
    public void initializeObjects() {
        ArrayList<CollidableGameObject> noInitializer = new ArrayList<>();
        Log log = new Log(this.gameView, new double[]{250, -250, 250, 250}, new double[]{-250, -250, -275, -275}, Color.CYAN, noInitializer);
        this.obstacles.add(log);
        Stone stone = new Stone(this.gameView, new double[]{200, 200, 150, 150}, new double[]{-400, -400, -450, -450}, Color.BLACK, noInitializer);
        this.obstacles.add(stone);

        TrackBorderElement[][] t = new TrackBorderElement[][]{
                new TrackBorderElement[]{
                        new TrackBorderElement(this.gameView, new double[]{230, 280, 280, 230}, new double[]{-1, -1, -2795, -2745}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{230, 280, -220, -270}, new double[]{-2745, -2795, -3295, -3245}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, -220, -270}, new double[]{-3245, -3295, -3545, -3495}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, 30, -20}, new double[]{-3495, -3545, -3795, -3745}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-20, 30, 530, 480}, new double[]{-3745, -3795, -4045, -3995}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{480, 530, 1030, 980}, new double[]{-3995, -4045, -4295, -4245}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{980, 1030, 1530, 1480}, new double[]{-4245, -4295, -4795, -4745}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{1480, 1530, 1530, 1480}, new double[]{-4745, -4795, -5295, -5245}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{1480, 1530, 1280, 1230}, new double[]{-5245, -5295, -5795, -5745}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{1230, 1280, -220, -270}, new double[]{-5745, -5795, -8295, -8245}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, -220, -270}, new double[]{-8245, -8295, -9295, -9245}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, 530, 480}, new double[]{-9245, -9295, -9795, -9745}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{480, 530, 780, 730}, new double[]{-9745, -9795, -10295, -10245}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{730, 780, 280, 230}, new double[]{-10245, -10295, -11045, -10995}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{230, 280, 280, 230}, new double[]{-10995, -11045, -12795, -12745}, Color.CYAN, noInitializer)
                },
                new TrackBorderElement[]{
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, -220, -270}, new double[]{-1, -1, -2545, -2495}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, -470, -520}, new double[]{-2495, -2545, -2795, -2745}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-520, -470, -970, -1020}, new double[]{-2745, -2795, -3045, -2995}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-1020, -970, -1220, -1270}, new double[]{-2995, -3045, -3545, -3495}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-1270, -1220, -1220, -1270}, new double[]{-3495, -3545, -3795, -3745}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-1270, -1220, -720, -770}, new double[]{-3745, -3795, -4295, -4245}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-770, -720, 530, 480}, new double[]{-4245, -4295, -4545, -4495}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{480, 530, 780, 730}, new double[]{-4495, -4545, -5045, -4995}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{730, 780, 780, 730}, new double[]{-4995, -5045, -5545, -5495}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{730, 780, -720, -770}, new double[]{-5495, -5545, -8045, -7995}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-770, -720, -720, -770}, new double[]{-7995, -8045, -9545, -9495}, Color.CYAN, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-770, -720, -220, -270}, new double[]{-9495, -9545, -10045, -9995}, Color.BLACK, noInitializer),
                        new TrackBorderElement(this.gameView, new double[]{-270, -220, -220, -270}, new double[]{-9995, -10045, -12795, -12745}, Color.CYAN, noInitializer)
                }
        };
        this.tr.add(t);
        //plantTrees();
        //spawnEnemies();
    }

    /**
     * Get the List of all TrackBorderElements
     * @return , all TrackBorderElements from the first Track as double[][]
     */
    public TrackBorderElement[][] getTrackElements(){
        return this.tr.get(0);
    }

    /**
     * Add obstacles to the track
     * @param obstacle , the new obstacle
     */
    public void addObstacle(Obstacle obstacle){
        this.obstacles.add(obstacle);
    }

    @Override
    public void updateStatus() {
        this.rotation += 10;
    }

    @Override
    public void addToCanvas() {
        for (int i = 0; i < this.tr.get(0).length; i++) {
            for (TrackBorderElement t : this.tr.get(0)[i]) {
                t.addToCanvas();
            }
        }
        for (Obstacle o : this.obstacles) {
            o.addToCanvas();
        }
        for (Enemy e : this.enemies){
            e.addToCanvas();
        }
    }

    @Override
    protected void updateHitBoxPosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.exit(0);
    }

    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        super.adaptPosition(adaptX, adaptY);
        this.gamePlayManager.getGameObjectManager().getPlayer().setScore(adaptY);
        for (int i = 0; i < this.tr.get(0).length; i++) {
            for (TrackBorderElement t : this.tr.get(0)[i]) {
                t.adaptPosition(adaptX, adaptY);
            }
        }
        ArrayList<Perspective> toRemove = new ArrayList<>();
        for (Obstacle o : this.obstacles) {
            if (o.getY()[0] - adaptY > -500) {
                toRemove.add(o);
            } else {
                o.adaptPosition(adaptX, adaptY);
            }
        }
        this.obstacles.removeAll(toRemove);
        toRemove.clear();
        for (Enemy e : this.enemies) {
            if (e.getY()[0] - adaptY > -20) {
                toRemove.add(e);
            } else {
                e.adaptPosition(adaptX, adaptY);
            }
        }
        this.enemies.removeAll(toRemove);
    }

    /**
     * Rotates the track, while driving
     *
     * @param rot value to be rotated by
     */
    public void turn(double rot) {
        if (this.gameView.timerExpired("Rotation", "Track")) {
            this.gameView.setTimer("Rotation", "Track", 50);
            for (int i = 0; i < this.tr.get(0).length; i++) {
                for (int j = 0; j < this.tr.get(0)[i].length; j++) {
                    this.tr.get(0)[i][j].rotateElement(rot);
                }
            }
        }
        for (Obstacle o : this.obstacles) {
            o.rotateElement(rot);
        }
        for (Enemy e : this.enemies){
            e.rotateElement(rot);
        }
    }

    /**
     * Returns an array with all obstacles
     *
     * @return the obstacle array
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public void updatePosition() {

    }
}
