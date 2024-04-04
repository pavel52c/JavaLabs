package org.example.dto;

import java.awt.*;

public abstract class Figure implements IFigure {
    protected int x;
    protected int y;
    protected Color color;
    protected int speed;

    public Figure(int x, int y, Color color, int speed) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = speed;
    }

    public void reColor() {
        int r = (int) (Math.random() * 220);
        int g = (int) (Math.random() * 220);
        int b = (int) (Math.random() * 220);
        this.color = new Color(r,g,b);
    }

}
