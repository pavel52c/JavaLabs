package org.example.core;

import org.example.BouncingBallSimple;
import org.example.DisplayWorker;
import org.example.dto.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MovingCircles extends JPanel implements Runnable {

    private static final DisplayWorker displayWorker = new DisplayWorker();
    private static final int WINDOW_HEIGHT = displayWorker.getHeight();
    private static final int WINDOW_WIDTH = displayWorker.getWidth();
    private static final int WINDOW_POSITION_X = displayWorker.getXPos();
    private static final int WINDOW_POSITION_Y = displayWorker.getYPos();

    private final ArrayList<Circle> circles;

    public MovingCircles() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setLocation(WINDOW_POSITION_X,WINDOW_POSITION_Y);
        circles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1; i++) {
            int x = random.nextInt(WINDOW_WIDTH);
            int y = random.nextInt(WINDOW_HEIGHT);
            int radius = random.nextInt(30) + 20;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Circle circle = new Circle(x, y, radius, color, 3);
            circles.add(circle);
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                int counter = 0;
                while (true) {
                    for (Circle circle : circles) {
                        circle.move(WINDOW_WIDTH, WINDOW_HEIGHT);
                        if (counter % 10 == 0) {
                            circle.reColor();
                            counter *= -1;
                        }
                        circle.reSize();
                    }

                    repaint();

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                }
            }
        };

        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Circle circle : circles) {
            circle.draw(g);
        }

    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            for (Circle circle : circles) {
                circle.move(WINDOW_WIDTH, WINDOW_HEIGHT);
                if (counter % 10 == 0) {
                    circle.reColor();
                    counter *= -1;
                }
                circle.reSize();
            }

            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Задаем основное окно программы
                JFrame frame = new JFrame("Moving Circles");
                MovingCircles movingCircles = new MovingCircles();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(movingCircles);
                frame.pack();
                frame.setVisible(true);
            }
        });

//        frame.add(movingCircles);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(movingCircles);
//        frame.pack();
//        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
//        frame.setLocation(WINDOW_POSITION_X,WINDOW_POSITION_Y);
//        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
////        frame.setResizable(false);
//        frame.setVisible(true);
//
//        Thread thread = new Thread(movingCircles);
//        thread.start();
    }
}
