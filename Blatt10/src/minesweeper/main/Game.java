package minesweeper.main;

import minesweeper.model.Board;
import minesweeper.view.PlayingField;

import javax.swing.*;

/**
 * Starts the Minesweeper game
 */
public class Game {

    public static void main(String[] args) {
        // if (args.length != 3) {
        //     System.err.println(args.length);
        //     throw new IllegalArgumentException("Args length must be 3: Width, height, bombs");
        // }

        // int arguments[] = new int[3];

        // for (int i = 0; i < 3; i++) {
        //     try {
        //         arguments[i] = Integer.parseInt(args[i]);
        //     } catch (NumberFormatException e) {
        //         throw new IllegalArgumentException("Args must be Numeric!");
        //     }
        // }
        
        int[] arguments = new int[]{6,4,2};

        JFrame frame = new JFrame("MineSweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board model = new Board(arguments[0], arguments[1], arguments[2]);
        PlayingField view = new PlayingField(model);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}