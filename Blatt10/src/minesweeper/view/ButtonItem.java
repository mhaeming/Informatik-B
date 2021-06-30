package minesweeper.view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

import minesweeper.controller.GameController;
import minesweeper.model.Field;  @SuppressWarnings("deprecation")

public class ButtonItem extends JPanel implements Observer{

    private JButton button;
    private JLabel label;

    private Field field;


    public ButtonItem(Field field) {
        this.button = new JButton();
        this.label = new JLabel();

        this.add(button, "button");
        this.add(label, "label");

        // Button styling
        this.button.setPreferredSize(new Dimension(30, 30));
        this.button.setVisible(true);
        this.button.setOpaque(true);
        this.button.setBorderPainted(false);
        this.button.setBackground(Color.lightGray);


        // Set Layout
        this.setLayout(new CardLayout());

        this.field = field;
        this.field.addObserver(this);
        GameController controller = new GameController(field);
        this.button.addMouseListener(controller);
    }


    @Override
    public void update(Observable o, Object arg) {
        CardLayout layout = (CardLayout) this.getLayout();
        if (this.field.isRevealed()) {
            
            Integer nearBombs = this.field.getNearBombs();

            if (this.button.isShowing()) {
                layout.show(this, "label");
            }

            if (this.field.hasBomb()) {
                label.setText("X");
            } else {
                label.setText(nearBombs.toString());
            }

        } else {
            if (label.isShowing()) {
                layout.show(this, "button");
            }
            if (this.field.isFlagged()) {
                this.button.setText("!");
            } else{
                this.button.setText("");
            }
        }



    }
}
