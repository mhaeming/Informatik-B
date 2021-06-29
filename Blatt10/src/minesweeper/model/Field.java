package minesweeper.model;

import java.util.ArrayList;
import java.util.Observable; @SuppressWarnings("deprecation")

/**
 * Field
 */
public class Field extends Observable{

    private boolean hasBomb;
    private boolean flagged;
    private boolean revealed;
    private Board board;

    private int x;
    private int y;

    /**
     * A array to store neighboring fields. The index gives the position relative to the selected field as follows:
     * 
     *  1 | 2 | 3 
     *  4 | x | 5
     *  6 | 7 | 8
     */
    private ArrayList<Field> neighbors = new ArrayList<Field>();
    private int nearBombs;

    public Field(int x, int y, boolean hasBomb, Board board) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        this.board = board;

        this.flagged = false;
        this.revealed = false;
    }


    public boolean hasBomb() {
        return this.hasBomb;
    }

    public boolean isFlagged() {
        return this.flagged;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public ArrayList<Field> getNeighbors() {
        return this.neighbors;
    }

    public int getNearBombs() {
        return this.nearBombs;
    }

    /**
     * reveal a field and end the game if it has a bomb
     */
    public void reveal() {
        if (!this.revealed) {
            
            if (flagged) {
                return;
            }

            this.revealed = true;
            // reveal all surrounding fields if there is no bomb
            if (!this.hasBomb && nearBombs == 0) {
                for (Field field : neighbors) {
                    field.reveal();
                }
            }
            this.board.revealedField(this);
            this.setChanged();
            this.notifyObservers();

        }
    }

    /**
     * Switch the flagged statement
     */
    public void toggleFlag() {
        this.flagged = !this.flagged;
        this.board.switchedFlag(this);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Loop over neighbors and count bombs
     * 
     * @return number of bombs in neighboring fields
     */
    public void countNeighborBombs() {
        int bombs = 0;

        for (Field field : neighbors) {
            if (field.hasBomb) {
                bombs++;
            }
        }

        this.nearBombs = bombs;
    }

    public void searchNeighbors() {
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (i == x && j == y) {
                    continue; // Skip if at the center
                }
                Field f = board.getField(i,j);
                if (f != null) {
                    this.neighbors.add(f);
                }
            }
        }
        this.countNeighborBombs();
    }
}