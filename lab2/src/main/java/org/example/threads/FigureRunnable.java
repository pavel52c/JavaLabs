package org.example.threads;


import org.example.figures.CustomRectangle;
import org.example.figures.Figure;
import org.example.panels.FigurePanel;

import java.awt.*;

public class FigureRunnable implements Runnable{

    private final Figure figure;
    private final Component component;
    private int step = 0;

    public FigureRunnable(Figure figure, Component component,int step) {
        this.figure = figure;
        this.component = component;
        this.step = step;
    }

    @Override
    public void run()
    {
        int flag = 0;
        System.out.println("Thread with "+figure.getClass().toString()+" is started");
        try
        {
            for (int i = 0; true; ) {
                int aR = FigurePanel.managedRectangle.getShape().getBounds().width;
                double bR = Math.sqrt((2*aR*aR)/4);
                if(figure instanceof CustomRectangle){
                    if ((figure.getShape().getBounds().width <= FigurePanel.round.getShape().getBounds().width)
                            ||(figure.getShape().getBounds().width) >= bR) {
                        flag=(figure.getFlag() == 1)?0:1;
                        figure.setFlag(flag);
                        figure.reColor();
                        if ((figure.getShape().getBounds().width) >= bR){
                            FigurePanel.managedRectangle.reColor();
                        }
                    }
                }else{
                    if (figure.getShape().getBounds().width >= FigurePanel.rectangle.getShape().getBounds().width) {
                        flag=(figure.getFlag() == 1)?0:1;
                        figure.setFlag(flag);
                        figure.reColor();
                    }
                }
                figure.reSize(step);
                component.repaint();
                int DELAY = 10;
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException ignored)
        {
        }
    }
}
