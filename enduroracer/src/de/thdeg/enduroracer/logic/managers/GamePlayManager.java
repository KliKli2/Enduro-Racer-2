package de.thdeg.enduroracer.logic.managers;

import de.thdeg.enduroracer.assets.passiveelement.Track;
import de.thdeg.enduroracer.assets.activeelement.TrackBorderElement;
import de.thdeg.enduroracer.assets.ui.EndScreen;
import de.thdeg.enduroracer.assets.ui.StartScreen;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.NoMoreLevelsException;

import java.awt.*;

/**
 * Class to manage the actual gameplay
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private LevelManager levelManager;
    private boolean victory;

    /**
     * Get the gameObjectManager
     * @return , the gameObjectManager object
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    /**
     * Get the Levelmanager
     * @return , the levelManager object
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Constructor initializing the gameplay
     *
     * @param gameView          specifies the gameview canvas
     * @param gameObjectManager Object containing all existing GameObjects
     */
    public GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.victory = false;
        this.gameObjectManager = gameObjectManager;
        this.gameObjectManager.getMotorcycle().setGamePlayManager(this);
        this.gameObjectManager.getTrack().setGamePlayManager(this);
        this.gameObjectManager.getScoreboard().setGamePlayManager(this);
        this.gameObjectManager.getBackground().setGamePlayManager(this);
        this.gameObjectManager.getTrack().getObstacles().forEach(o -> o.setGamePlayManager(this));
        this.gameObjectManager.getTrack().getEnemies().forEach(o -> o.setGamePlayManager(this));
        for (int i = 0; i < this.gameObjectManager.getTrack().getTrackElements().length; i++){
            for(TrackBorderElement t : this.gameObjectManager.getTrack().getTrackElements()[i]) {
                t.setGamePlayManager(this);
            }
        }
        initializeLevel();
    }

    /**
     * Method to ask for the next game or the end of the game
     */
    public void nextGame() {
        StringBuilder message = new StringBuilder(this.victory ? "Congratulations!!\n" : "GameOver!!");
        if(victory){
            int i = 1;
            message.append(i).append(". ").append(this.gameObjectManager.getScoreboard().getHighscore()).append(": ").append(this.gameObjectManager.getScoreboard().getGlobalScores().get(this.gameObjectManager.getScoreboard().getHighscore())).append("\n");
            for(String key : this.gameObjectManager.getScoreboard().getGlobalScores().keySet()) {
                if (!key.equals(this.gameObjectManager.getScoreboard().getHighscore())) {
                    message.append(i).append(". ").append(key).append(": ").append(this.gameObjectManager.getScoreboard().getGlobalScores().get(key)).append("\n");
                }
            }
        }
        this.gameView.setBackgroundColor(Color.BLACK);
        EndScreen end = new EndScreen(this.gameView);
        end.showEndScreen(message.toString());
        this.levelManager.reset();
        this.victory = false;
        this.gameObjectManager.getScoreboard().reset();
        this.gameObjectManager.getTrack().reset();
        initializeLevel();
    }

    /**
     * Method to look for the next level
     */
    public void nextLevel() {
        this.gameObjectManager.getOverlay().showMessage("LAPTIME 0'" + (65 - this.gameObjectManager.getScoreboard().getTime()) + "'00");
        this.gameObjectManager.getScoreboard().finishedLap(this.gameObjectManager.getPlayer().getName(this.levelManager.getActiveLevel().getName()), this.gameObjectManager.getPlayer().getScore()*this.gameObjectManager.getScoreboard().getTime());
        this.gameObjectManager.getPlayer().levelFinished();
        this.gameObjectManager.getScoreboard().reset();
        this.gameObjectManager.getTrack().reset();
        initializeLevel();
    }

    /**
     * Method to start a level
     */
    public void initializeLevel() {
        if (this.levelManager == null || this.levelManager.getActiveLevel() == null) {
            StartScreen startScreen = new StartScreen(this.gameView);
            startScreen.showStartScreen();
            if(this.levelManager == null) {
                this.levelManager = new LevelManager(startScreen.isDifficultySetToEasy());
            }else {
                this.levelManager.setUpLevels(startScreen.isDifficultySetToEasy());
            }
            this.gameObjectManager.setPlayer("Player " + (this.gameObjectManager.getScoreboard().getGlobalScores().size() + 1));
            this.levelManager.setActiveLevel(0);
            this.gameObjectManager.getTrack().setNumberOfEnemies(this.levelManager.getActiveLevel().getEnemies());
            this.gameObjectManager.getTrack().setNumberOfObstacles(this.levelManager.getActiveLevel().getObstacles());
            this.gameObjectManager.getBackground().setBackGroundNumber(this.levelManager.getActiveLevel().getBackground());
            this.gameView.stopAllSounds();
            this.gameView.playSound(this.levelManager.getActiveLevel().getMusic(), true);
        } else if (this.levelManager.hasNextLevel()) {
            try {
                this.gameView.stopAllSounds();
                this.levelManager.getNextLevel();
                this.gameView.playSound(this.levelManager.getActiveLevel().getMusic(), true);
                this.gameObjectManager.getBackground().setBackGroundNumber(this.levelManager.getActiveLevel().getBackground());
            } catch (NoMoreLevelsException e) {
                e.printStackTrace();
            }
        } else {
            this.gameView.stopAllSounds();
            this.victory = true;
            this.gameView.playSound("06-Ending.wav", true);
            nextGame();
        }
    }

    /**
     * Method to update and simulate the gameplay
     */
    public void updateGamePlay() {
        if (this.getGameObjectManager().getPlayer().getScore() > Track.MAXLENGTH) {
            nextLevel();
        }
        if (this.gameObjectManager.getScoreboard().getTime() <= 0) {
            nextGame();
        }
        if (this.gameView.timerExpired("Kill Object", "Enemy")) {
            this.gameView.setTimer("Kill Object", "Enemy", 5000);
            if (this.gameObjectManager.getEnemies().size() > 0) {
                this.gameObjectManager.getEnemies().remove((int) (Math.random() * this.gameObjectManager.getEnemies().size()));
            }
        }

    }
}
