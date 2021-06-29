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

    private JPanel field;

    private JLabel bombsToFind;

    public PlayingField(GameRules model) {
        // Fit the window size to the number of rows and cols
        this.setPreferredSize(new Dimension(model.getWidth()*80, model.getHeight()*80));

        // Add the model
        this.model = model;
        model.addObserver(this);

        // Bombs to find, will be text on top
        bombsToFind = new JLabel("Bombs to find: " + this.model.getBombsToFind());
        bombsToFind.setPreferredSize(new Dimension(model.getWidth()*80, 30));

        // The actual field is a grid
        field = new JPanel();
        field.setLayout(new GridLayout(0, model.getWidth(), 10, 10));

        // This is a BorderLayout, so the grid can be added in the center and the bombs to find text on top
        this.setLayout(new BorderLayout());

        // Add all the buttons / small fields to the grid
        for (int i = 1; i <= model.getHeight() * model.getWidth(); i++) {
            Integer j = i;
            String text = j.toString();
            JButton button = new JButton(text);
            button.setPreferredSize(new Dimension(10,10));
            field.add(button);

            // This is how to set the button so that you just see a number, but no edges
            if (i == 4 || i == 6 || i == 18 || i == 23 || i == 36 || i == 60){
                button.setBorderPainted(false);
            }

            // With this, buttons can be hidden
            if (i == 3 || i == 5 || i == 16 || i == 21 || i == 35 || i == 56){
                button.setVisible(false);
            }
        }

        // Combine the grid and the text on top
        this.add(this.bombsToFind, BorderLayout.NORTH);
        this.add(field, BorderLayout.CENTER);

    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
