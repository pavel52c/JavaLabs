package org.example.core;

import org.example.panels.FigurePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {

    private static final DisplayWorker displayWorker = new DisplayWorker();
    private static final int WINDOW_HEIGHT = displayWorker.getHeight();
    private static final int WINDOW_WIDTH = displayWorker.getWidth();
    private static final int WINDOW_POSITION_X = displayWorker.getXPos();
    private static final int WINDOW_POSITION_Y = displayWorker.getYPos();


    public MainWindow() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSITION_X, WINDOW_POSITION_Y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Lab 2");
        JPanel grid = new JPanel(new GridLayout(1, 2));
        JPanel panel = new FigurePanel();
        add(panel);
        add(grid, BorderLayout.SOUTH);
        setVisible(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right") || KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) {
                    FigurePanel.managedRectangle.manageSizeUp();
                }
                if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left") || KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) {
                    int widthRectangle = FigurePanel.rectangle.getShape().getBounds().width;
                    int widthManaged = FigurePanel.managedRectangle.getShape().getBounds().width;
                    double sideOfRectangle = Math.sqrt((2 * widthManaged * widthManaged) / 4);
                    if (widthRectangle < sideOfRectangle - 5) {
                        FigurePanel.managedRectangle.manageSizeDown();
                    }
                }
            }
        });
    }
}