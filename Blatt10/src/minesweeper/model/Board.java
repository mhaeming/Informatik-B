package minesweeper.model;

import java.util.Random;

import java.util.Observable; @SuppressWarnings("deprecation")

/**
 * Board
 */
public class Board extends Observable{
    private Field[][] fields;

    private int bombs;
    private int flags;
    private int revealed;

    public Board(int width, int height, int bombs) {

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Please choose valid board dimensions!");
        }
        if (bombs >= width * height) {
            throw new IllegalArgumentException("Whoa calm down Satan! Not all fields can be bombs.");
        }

        fields = new Field[height][width];
        this.bombs = bombs;
        this.flags = 0;
        this.revealed = 0;



        /**
         * Place the bombs
         * Must be in while loop in case it tries to place the bomb on another bomb
         */
        int bombsToPlace = bombs;
        int row;
        int column;
        Random rnd = new Random();
        while (bombsToPlace > 0) {
            row = rnd.nextInt(width);
            column = rnd.nextInt(height);

            if (fields[column][row] == null) {
                fields[column][row] = new Field(column, row, true, this);
                bombsToPlace--;
            }
        }
        
        /**
         * Setup the remaining fields
         */
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (fields[i][j] == null) {
                    fields[i][j] = new Field(i, j, false, this); 
                }
            }
        }

        /**
         * Populate the neighbor information
         */
        for (Field[] f : fields) {
            for (Field field : f) {
                field.searchNeighbors();
            }
        }
    }

    public Field getField(int x, int y) {
        if (y < 0 || y >= this.fields.length || x < 0 || x >= this.fields[0].length) {
            return null;
        }
        return this.fields[y][x];
    }

    public void print() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if (fields[i][j].hasBomb()) {
                    System.out.print("[x]");  
                } else {
                    System.out.print("[ ]");  
                }
            }
            System.out.println();
        }
    }

    public int getTotalBombs() {
        return this.bombs;
    }

    public int getWidth() {
        return this.fields[0].length;
        
    }

    public int getHeight() {
        return this.fields.length;
    }
    
}
