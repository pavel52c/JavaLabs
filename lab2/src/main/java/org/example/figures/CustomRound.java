package org.example.figures;


import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomRound extends FigureAbstract {

    public CustomRound(int size) {
        super(size,size);
    }

    @Override
    public Shape getShape() {
        return new Ellipse2D.Double(WINDOW_CENTER_POS_X-sizeX/2, WINDOW_CENTER_POS_Y-sizeY/2, sizeX, sizeY);
    }
}
