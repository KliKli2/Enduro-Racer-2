package de.thdeg.enduroracer.assets.ui;

import de.thdeg.enduroracer.logic.BlockImages;
import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.util.HashMap;

/**
 * Class for the score management and the creation of the scoreboard
 */
public class Scoreboard extends GameObject {

    private final double timeX;
    private final double timeY;
    private final double statX;
    private final double statY;
    private final double levelX;
    private final double levelY;
    private int time;
    private int score;
    private double speed;
    private final HashMap<String, Double> globalScores;
    private String highscore;

    /**
     * The default constructor for the GameObject class
     *
     * @param gameView the GameView canvas to be used
     */
    public Scoreboard(GameView gameView) {
        super(gameView);
        this.timeX = 20;
        this.timeY = 20;
        this.statX = GameView.WIDTH / 2.0 + 80;
        this.statY = 20;
        this.levelX = GameView.WIDTH - 200;
        this.levelY = GameView.HEIGHT - 50;
        this.time = 65;
        this.speed = 0;
        this.score = 0;
        this.globalScores = new HashMap<>();
    }

    /**
     * Get the highscore player
     * @return , the name of the player
     */
    public String getHighscore() {
        return highscore;
    }

    @Override
    public void updateStatus() {
        updateTime();
        if (this.gameView.timerExpired("Speed", "Scoreboard")) {
            this.gameView.setTimer("Speed", "Scoreboard", 783);
            this.speed = this.gamePlayManager.getGameObjectManager().getPlayer().getSpeed();
            this.score = (int) this.gamePlayManager.getGameObjectManager().getPlayer().getScore();
        }
    }

    private void updateTime() {
        if (this.gameView.timerExpired("Time", "Scoreboard")) {
            this.gameView.setTimer("Time", "Scoreboard", 1000);
            this.time = (this.time - 1 >= 0) ? this.time - 1 : this.time;
        }
    }

    /**
     * Method to reset the timer after each lap
     */
    public void reset() {
        this.time = 65;
    }

    /**
     * Get the remaining time for the scoreboard
     * @return , the remaining time
     */
    public int getTime() {
        return time;
    }

    @Override
    public void addToCanvas() {
        this.gameView.addBlockImageToCanvas((this.time > 9) ? BlockImages.NUMBERS[this.time / 10] : BlockImages.NUMBERS[0], this.timeX, this.timeY, 3, 0);
        this.gameView.addBlockImageToCanvas((this.time > 9) ? BlockImages.NUMBERS[this.time % 10] : BlockImages.NUMBERS[this.time], this.timeX + 28, this.timeY, 3, 0);
        this.gameView.addTextToCanvas("score", this.statX, this.statY, 25, Color.WHITE, 0);
        this.gameView.addTextToCanvas("speed", this.statX, this.statY + 40, 25, Color.WHITE, 0);
        this.gameView.addTextToCanvas(String.valueOf(this.score), this.statX + 150, this.statY, 25, Color.WHITE, 0);
        this.gameView.addTextToCanvas(String.valueOf(this.speed), this.statX + 150, this.statY + 40, 25, Color.WHITE, 0);
        this.gameView.addTextToCanvas("km\\h", this.statX + 275, this.statY + 40, 25, Color.WHITE, 0);
        this.gameView.addTextToCanvas(this.gamePlayManager.getLevelManager().getActiveLevel().getName(), this.levelX, this.levelY,25, Color.WHITE, 0);
    }

    /**
     * Get the final standings
     * @return , A hashmap with the corresponding scores
     */
    public HashMap<String, Double> getGlobalScores() {
        return globalScores;
    }

    /**
     * Add a lap to the score hashmap
     * @param player , the scored player
     * @param score , the score he achieved
     */
    public void finishedLap(String player, double score) {
        if(this.highscore == null){
            this.highscore = player;
        }else {
            if (this.globalScores.get(this.highscore) < score) {
                this.highscore = player;
            }
        }
        this.globalScores.put(player, score);
    }
}
