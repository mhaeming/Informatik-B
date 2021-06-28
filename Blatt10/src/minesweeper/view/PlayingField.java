package minesweeper.view;

import minesweeper.model.GameRules;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The gui / playing field of the minesweeper game
 */
public class PlayingField extends JPanel implements Observer {

    private GameRules model;

    private JLabel bombsToFind;

    public PlayingField(GameRules model){
        this.model = model;
        this.model.addObserver(this);

        this.setLayout(new GridLayout());

        this.bombsToFind = new JLabel("Bombs to find: " + this.model.getBombsToFind());

        this.add(this.bombsToFind);
    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
