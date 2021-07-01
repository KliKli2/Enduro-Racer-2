package de.thdeg.enduroracer.assets.ui;

import de.thdeg.enduroracer.logic.GameObject;
import de.thdeg.enduroracer.logic.GameView;
import de.thdeg.enduroracer.logic.Position;

import java.awt.*;
import java.util.Arrays;

/**
 * Class to manage the Overlay during the game
 */
public class Overlay extends GameObject {
    private String message;

    /**
     * The default constructor for the Overlay
     * @param gameView , the gameView object
     */
    public Overlay(GameView gameView) {
        super(gameView);
        this.size = 36;
    }

    /**
     * Shows a message on the overlay, for 3 seconds. The message can consist of several lines.
     *
     * @param message Message to show.
     */
    public void showMessage(String message) {
        this.message = message;
        String[] lines = message.split("\\R");
        int longestLine = Arrays.stream(lines).mapToInt(String::length).max().orElse(1);
        double textHeight = lines.length * size;
        double textWidth = longestLine * size;
        this.position = new Position((GameView.WIDTH - textWidth) / 2d, (GameView.HEIGHT - textHeight) / 2d);
        gameView.setTimer("ShowOverLay", "ShowOverLay", 3000);
    }

    @Override
    public void updateStatus() {
    }

    @Override
    public void addToCanvas() {
        if (!gameView.timerExpired("ShowOverLay", "ShowOverLay")) {
            gameView.addTextToCanvas(message, position.getX(), position.getY(), size, Color.WHITE, rotation);
        }
    }
}