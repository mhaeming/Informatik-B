package minesweeper.model;

import java.util.Observable;

/**
 * Implements the actual logic and model behind the Minesweeper games
 */
// Needs to be filled, currently just there for being able to add this model to the view-constructor
public class GameRules extends Observable {

    private int bombsToFind = 7;

    public int getBombsToFind(){
        return this.bombsToFind;
    }
}
