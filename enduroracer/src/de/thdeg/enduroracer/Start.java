package de.thdeg.enduroracer;

import de.thdeg.enduroracer.logic.managers.GameLoopManager;

/**
 * Class to start the game and manage overall values and gamestates
 */
public class Start {
    /**
     * Main method to start the game
     * @param args , standard arguments
     */
    public static void main(String[] args) {
        System.out.println("Spiel");
        var manager = new GameLoopManager();
        manager.startGame();
    }
}
