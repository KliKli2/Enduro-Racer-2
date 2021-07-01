package de.thdeg.enduroracer.assets.ui;

import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;

/**
 * Class to manage the Endscreen
 */
public class EndScreen extends GameObject {

    /**
     * The default constructor for the Endscreen
     * @param gameView , the gameView object
     */
    public EndScreen(GameView gameView) {
        super(gameView);
    }

    /**
     * Method to display the endscreen
     * @param message , message to display on the endscreen
     */
    public void showEndScreen(String message) {
        this.gameView.showEndScreen(message);
    }

    @Override
    protected void updateStatus() {

    }

    @Override
    public void addToCanvas() {

    }
}
