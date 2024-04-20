package org.example.figures;

import java.awt.*;


public class CustomRectangle extends FigureAbstract{

    public CustomRectangle(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    @Override
    public Shape getShape() {
        return new Rectangle((int) (WINDOW_CENTER_POS_X-sizeX/2), (int) (WINDOW_CENTER_POS_Y-sizeY/2), (int)sizeX, (int)sizeY);
    }
}
