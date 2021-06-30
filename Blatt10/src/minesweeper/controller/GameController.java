package minesweeper.controller;

import minesweeper.model.Field;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Links the input options of the playing field with the model
 */
public class GameController extends MouseAdapter {

    public Field model;

    public GameController(Field model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch(e.getButton()) {
            case MouseEvent.BUTTON1: // left mouse button
                model.reveal();
                break;
            case MouseEvent.BUTTON3: // right mouse button
                model.toggleFlag();
                break;

        }
    }
}
