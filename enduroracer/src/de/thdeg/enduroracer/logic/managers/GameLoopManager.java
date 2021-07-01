package de.thdeg.enduroracer.logic.managers;

import de.thdeg.enduroracer.logic.GameView;

import java.awt.*;
import java.io.IOException;

/**
 * The class to manage the visual gamestate
 */
public class GameLoopManager {
    private final GameView gameView;
    private final InputManager inputManager;
    private final GameObjectManager gameObjectManager;
    private final GamePlayManager gamePlayManager;

    /**
     * Basic constructor for GameLoopManager, assigning the default values for the Game
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Enduro Racer II");
        this.gameView.setStatusText("Maximilian von Hohenbühel - Java Programming SS 2021");
        this.gameView.setWindowIcon("logo.png");
        this.gameView.playSound("06-Ending.wav", true);
        this.gameObjectManager = new GameObjectManager(this.gameView);
        this.gamePlayManager = new GamePlayManager(this.gameView, this.gameObjectManager);
        this.inputManager = new InputManager(this.gameView, this.gameObjectManager.getMotorcycle());
    }

    /**
     * Method to start the visual aspect of the game and updating the screen
     */
    public void startGame() {
        this.gameView.setColorForBlockImage('l', new Color(98, 57, 11));
        this.gameView.setColorForBlockImage('h', new Color(0, 235, 242));
        while (true) {
            this.gamePlayManager.updateGamePlay();
            this.inputManager.updateUserInputs();
            this.gameObjectManager.updateGameObjects();
            this.gameView.printCanvas();
        }
    }

}