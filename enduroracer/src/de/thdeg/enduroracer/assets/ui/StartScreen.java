package de.thdeg.enduroracer.assets.ui;

import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;

/**
 * Class to manage the Startscreen
 */
public class StartScreen extends GameObject {

    private boolean difficultyIsSetToEasy;

    /**
     * Default constructor for the startscreen
     * @param gameView , the gameView object
     */
    public StartScreen(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void updateStatus() {

    }

    @Override
    public void addToCanvas() {

    }

    /**
     * Method to display the startscreen
     */
    public void showStartScreen() {
        this.difficultyIsSetToEasy = this.gameView.showSimpleStartScreen("Enduro Racer II",
                "Vom Spiel wurden insgesamt 2 Level implementiert.\n\n" +
                        "Die Steuerung erfolgt mit den Pfeiltasten\n" +
                        "und der Leertaste, hierbei gilt zu beachten,\n" +
                        "dass die Streckenberechnung außerhalb der\n" +
                        "Begrenzungen zu kleineren Bugs führen kann.");

    }

    /**
     * Get the chosen difficulty
     * @return , the chosen difficulty
     */
    public boolean isDifficultySetToEasy() {
        return this.difficultyIsSetToEasy;
    }
}
