package org.example.dto;

import org.example.dto.*;

import java.awt.*;

public class Circle extends Figure implements ICircle {
    private int radius;
    private int speedY;
    private int speedX;
    private int speedRadius = 1;
    private final int maxRadius;
    private final int minRadius;

    public Circle(int x, int y, int radius, Color color, int speed) {
        super(x, y, color, speed);
        this.radius = radius;
        this.speedX = speed;
        this.speedY = speed;
        this.maxRadius = 2 * radius;
        this.minRadius = radius;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void reSize() {
        if (radius >= maxRadius || radius < minRadius)
            speedRadius *= -1;
        radius += speedRadius;
    }

    public void bounceSpeed() {
        this.speedX *= -1;
        this.speedY += -1;
    }

    @Override
    public void move(int xSize, int ySize) {
        x += speedX;
        y += speedY;

        if (x - radius < 0) {
            speedX = -speedX; // Инвертация вектора движения (обычное отражение)
            x = radius; // Релокация шарика относительно границы
        } else if (x + radius > xSize) {
            speedX = -speedX;
            x = xSize - radius;
        }
        // Проверим так же для двух других 
        if (y - radius < 0) {
            speedY = -speedY;
            y = radius;
        } else if (y + radius > ySize) {
            speedY = -speedY;
            y = ySize - radius;
        }
    }
}