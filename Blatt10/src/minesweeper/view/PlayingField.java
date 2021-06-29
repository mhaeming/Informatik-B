package minesweeper.view;

import minesweeper.controller.GameController;
import minesweeper.model.Board;
import minesweeper.model.Field;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer; @SuppressWarnings("deprecation")

/**
 * The gui / playing field of the minesweeper game
 */
public class PlayingField extends JPanel implements Observer {

    private Board model;

    private JPanel field;

    private JLabel bombsToFind;

    private JButton[][] buttons;

    /**
     * Constructor
     * @param model
     */
    public PlayingField(Board model) {
        // Initialize the button-array
        buttons = new JButton[model.getHeight()][model.getWidth()];

        // Fit the window size to the number of rows and cols
        this.setPreferredSize(new Dimension(model.getWidth()*80, model.getHeight()*80));

        // Add the model
        this.model = model;
        model.addObserver(this);

        // Bombs to find, will be text on top
        bombsToFind = new JLabel("Bombs to find: " + this.model.getTotalBombs());
        bombsToFind.setPreferredSize(new Dimension(model.getWidth()*80, 30));

        // The actual field is a grid
        field = new JPanel();
        field.setLayout(new GridLayout(0, model.getWidth(), 10, 10));

        // This is a BorderLayout, so the grid can be added in the center and the bombs to find text on top
        this.setLayout(new BorderLayout());

        // Add all the buttons / small fields to the grid
        for (int x = 0; x < model.getHeight(); x++){
            for (int y = 0; y < model.getWidth(); y++) {

                JButton button = new JButton("");
                button.setPreferredSize(new Dimension(10, 10));
                button.setVisible(true);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(Color.lightGray);
                button.addMouseListener(new GameController(model.getField(x, y)));
                field.add(button);
                buttons[x][y] = button;
            }
        }

        // Combine the grid and the text on top
        this.add(this.bombsToFind, BorderLayout.NORTH);
        this.add(field, BorderLayout.CENTER);

    }



    @Override
    public void update(Observable o, Object arg) {

        // Check for all fields if they are revealed or flagged
        for (int x = 0; x < model.getHeight(); x++) {
            for (int y = 0; y < model.getWidth(); y++) {
                System.out.format("Checking: %d / %d \n", x, y);
                Field fieldToCheck = model.getField(x, y);
                if (fieldToCheck.isRevealed()) {
                    this.reveal(x, y);
                    for (Field neighbor: fieldToCheck.getNeighbors()){
                        Integer nearBombs = neighbor.getNearBombs();
                        buttons[neighbor.getX()][neighbor.getY()].setText(nearBombs.toString());
                    }
                }
                if (fieldToCheck.isFlagged()) {
                    this.flag(x, y);
                } else{
                    this.unflag(x, y);
                }
            }
        }
    }

    /**
     * Sets a single field to revealed-look
     * @param x,y the coordinates of the field
     */
    private void reveal(int x, int y){
        buttons[x][y].setBackground(Color.gray);
    }

    /**
     * Sets a single field to the flag-look
     * @param x,y the coordinates of the field
     */
    private void flag(int x, int y){
        buttons[x][y].setText("!");
    }

    /**
     * Sets a single field to the unflag-look
     * @param x,y the coordinates of the field
     */
    private void unflag(int x, int y){
        buttons[x][y].setText("");
    }



    /**
     * Sets a sngle field to the look to show the number of bombs in the neighborhood
     * @param x,y the coordinates of the field
     */
    private void setShowNeighborhood(int x, int y){
        buttons[x][y].setBorderPainted(false);
    }
}
