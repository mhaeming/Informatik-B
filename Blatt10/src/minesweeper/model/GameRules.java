package minesweeper.model;

import java.util.Observable;

/**
 * Implements the actual logic and model behind the Minesweeper games
 */
// Needs to be filled, currently just there for being able to add this model to the view-constructor
public class GameRules extends Observable {

    private int bombsToFind = 7;

    // Should be: command line arguments
    private int height = 5;
    private int width = 15;

    public int getBombsToFind(){
        return this.bombsToFind;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }
}
