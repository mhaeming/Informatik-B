package minesweeper.main;

import minesweeper.model.Board;
import minesweeper.view.PlayingField;

import javax.swing.*;

/**
 * Starts the Minesweeper game
 */
public class Game {

    public static void main(String[] args){
        JFrame frame = new JFrame("MineSweeper");
        Board model = new Board(5,4,4);
        PlayingField view = new PlayingField(model);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}