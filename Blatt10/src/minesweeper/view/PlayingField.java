package minesweeper.view;

import javax.swing.*;

import minesweeper.model.Board;

import java.awt.*;
import java.util.Observable;
import java.util.Observer; @SuppressWarnings("deprecation")

/**
 * The gui / playing field of the minesweeper game
 */
public class PlayingField extends JPanel implements Observer {

    private Board model;

    private JLabel bombsToFind;

    public PlayingField(Board model){
        this.model = model;
        this.model.addObserver(this);

        this.setLayout(new GridLayout());

        this.bombsToFind = new JLabel("Bombs to find: " + this.model.getTotalBombs());

        this.add(this.bombsToFind);
    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
