package org.example.figures;

import org.example.core.DisplayWorker;

import java.awt.*;

public interface Figure {

    DisplayWorker displayWorker = new DisplayWorker();
    int WINDOW_CENTER_POS_X = displayWorker.getWindowCenter().get("POS_X");
    int WINDOW_CENTER_POS_Y = displayWorker.getWindowCenter().get("POS_Y");

    void reSize(double step);

    void reColor();

    Color getColor();

    void setFlag(int flag);

    int getFlag();

    Shape getShape();

    void manageSizeUp();

    void manageSizeDown();
}
