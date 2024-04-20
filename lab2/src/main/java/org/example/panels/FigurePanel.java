package org.example.panels;


import org.example.core.DisplayWorker;
import org.example.figures.CustomRectangle;
import org.example.figures.CustomRound;
import org.example.figures.Figure;
import org.example.threads.FigureRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FigurePanel extends JPanel implements ActionListener {
    private final DisplayWorker displayWorker = new DisplayWorker();
    private final int WINDOW_CENTER_POS_X = displayWorker.getWindowCenter().get("POS_X");
    private final int WINDOW_CENTER_POS_Y = displayWorker.getWindowCenter().get("POS_Y");

    private final int x = 300;
    private final int y = 300;
    public static Figure rectangle;
    public static Figure managedRectangle;
    public static Figure round;

    public FigurePanel() {
        Timer timer = new Timer(25, this);
        timer.start();
        rectangle = new CustomRectangle(x, y);
        managedRectangle = new CustomRectangle(500, 500);
        round = new CustomRound((int) (x * 0.75));
        Runnable rectangleRun = new FigureRunnable(rectangle, this, 1);
        Runnable roundRun = new FigureRunnable(round, this, 2);
        Thread rectangleThread = new Thread(rectangleRun);
        Thread roundThread = new Thread(roundRun);
        rectangleThread.start();
        roundThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5.0f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(managedRectangle.getColor());
        g2d.draw(managedRectangle.getShape());
        g2d.rotate(Math.toRadians(45), WINDOW_CENTER_POS_X, WINDOW_CENTER_POS_Y);
        g2d.setPaint(round.getColor());
        g2d.draw(round.getShape());
        g2d.setPaint(rectangle.getColor());
        g2d.draw(rectangle.getShape());
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}