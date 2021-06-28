package minesweeper.main;

import minesweeper.model.GameRules;
import minesweeper.view.PlayingField;

import javax.swing.*;

/**
 * Starts the Minesweeper game
 */
public class Game {

    public static void main(String[] args){
        JFrame frame = new JFrame("MineSweeper");
        GameRules model = new GameRules();
        PlayingField view = new PlayingField(model);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}