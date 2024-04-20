package org.example.core;

import java.awt.*;
import java.util.HashMap;

public class DisplayWorker {
    private final Dimension dimension = Toolkit.getDefaultToolkit ().getScreenSize ();
    private final int y = dimension.height;
    private final int x = dimension.width;
    private final double height = 0.8;
    private final double width = 0.8;
    private final int fontSize = 17;

    public DisplayWorker() {
    }

    public int getHeight(){
        return (int)(y * height);
    }

    public int getWidth(){
        return (int)(x * width);
    }

    public int getXPos(){
        return (x - getWidth())/2;
    }

    public int getYPos(){
        return (y - getHeight())/2;
    }

//    public int getFontSize() {
//        return fontSize;
//    }

    public HashMap<String, Integer> getWindowCenter(){
        HashMap<String, Integer> map = new HashMap<>();
        int x = getWidth()/2;
        int y = getHeight()/2;
        map.put("POS_X", x);
        map.put("POS_Y", y);
        return map;
    }
}
