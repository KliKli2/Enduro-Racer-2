package de.thdeg.enduroracer.logic.managers;

import de.thdeg.enduroracer.assets.activeelement.Motorcycle;
import de.thdeg.enduroracer.logic.GameView;

import java.awt.event.KeyEvent;

/**
 * Class to take care of the user input
 */
public class InputManager {
    private final GameView gameView;
    private final Motorcycle motorcycle;

    /**
     * Constructor to set up the InputManager
     *
     * @param gameView   the used canvas
     * @param motorcycle the Object, that the player steers
     */
    public InputManager(GameView gameView, Motorcycle motorcycle) {
        this.gameView = gameView;
        this.motorcycle = motorcycle;
    }

    /**
     * Receives the user input and executes the right method for the main Motorcycle
     */
    public void updateUserInputs() {
        Integer[] gedruekteTasten = gameView.getKeyCodesOfCurrentlyPressedKeys();
        for (int keyCode : gedruekteTasten) {
            if (keyCode == KeyEvent.VK_UP) {
                this.motorcycle.updatePosition(2);
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                this.motorcycle.updatePosition(3);
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                this.motorcycle.updatePosition(1);
            }
            if (keyCode == KeyEvent.VK_LEFT) {
                this.motorcycle.updatePosition(0);
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                this.motorcycle.updatePosition(-1);
            }
        }
    }
}
