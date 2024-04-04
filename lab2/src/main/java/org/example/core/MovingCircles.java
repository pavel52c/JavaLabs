package org.example.core;

import org.example.dto.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MovingCircles extends JPanel implements Runnable {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final ArrayList<Circle> circles;

    public MovingCircles() {
        circles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int radius = random.nextInt(30) + 20;
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Circle circle = new Circle(x, y, radius, color, 3);
//            Circle circle = new Circle(radius, 600, radius, color, 3);
            circles.add(circle);
        }
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
                circle.move(WIDTH, HEIGHT);
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
        JFrame frame = new JFrame("Moving Circles");
        MovingCircles movingCircles = new MovingCircles();

        frame.add(movingCircles);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread thread = new Thread(movingCircles);
        thread.start();
    }
}
