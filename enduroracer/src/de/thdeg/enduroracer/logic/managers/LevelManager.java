package de.thdeg.enduroracer.logic.managers;

import de.thdeg.enduroracer.assets.passiveelement.Level;
import de.thdeg.enduroracer.logic.NoMoreLevelsException;

import java.util.ArrayList;

/**
 * Class to manage the levels
 */
public class LevelManager {

    private final ArrayList<Level> levels;
    private Level activeLevel;

    /**
     * Set the actual level
     * @param index , the active level
     */
    public void setActiveLevel(int index) {
        if (index < this.levels.size()) {
            this.activeLevel = this.levels.get(index);
        }
    }

    /**
     * The default constructor for the Levelmanager
     * @param difficultyIsSetToEasy , the difficulty
     */
    public LevelManager(boolean difficultyIsSetToEasy) {
        this.levels = new ArrayList<>();
        setUpLevels(difficultyIsSetToEasy);
    }

    /**
     * Method to set the different levels
     * @param difficultyIsSetToEasy , teh chosen difficulty
     */
    public void setUpLevels(boolean difficultyIsSetToEasy){
        if (difficultyIsSetToEasy) {
            this.levels.add(new Level("Stage 1", 10, 10, 0, "02-BGM1.wav"));
            this.levels.add(new Level("Stage 2", 10, 10, 1, "05-BGM2.wav"));
        } else {
            this.levels.add(new Level("Stage 1", 5, 5, 1, "05-BGM2.wav"));
            this.levels.add(new Level("Stage 2", 5, 5, 0, "02-BGM1.wav"));
        }
    }

    /**
     * Method to reset the level
     */
    public void reset() {
        this.activeLevel = null;
        this.levels.clear();
    }

    /**
     * Method to select the next level
     * @throws NoMoreLevelsException, in case there is no next level
     */
    public void getNextLevel() throws NoMoreLevelsException {
        if (hasNextLevel()) {
            this.activeLevel = this.levels.get(this.levels.indexOf(this.activeLevel) + 1);
        } else {
            throw new NoMoreLevelsException();
        }
    }

    /**
     * Method to see if there is a next level
     * @return , true if a next level exists
     */
    public boolean hasNextLevel() {
        return this.levels.indexOf(this.activeLevel) < this.levels.size() - 1;
    }

    /**
     * Get teh active level
     * @return , the active level
     */
    public Level getActiveLevel() {
        return this.activeLevel;
    }
}
