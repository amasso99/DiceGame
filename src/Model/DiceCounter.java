package Model;

import View.DrawableObject;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;

/**
 * Created by Oussama on 07.03.2017.
 */
public class DiceCounter implements DrawableObject {

    private int counter, goal;

    public DiceCounter(int goal){
        counter = 0;
        this.goal = goal;
    }

    public void addValue(int value){
        counter = counter + value;
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        int dim = (int) (MainFrame.SCREEN_WIDTH*0.1);
        g2d.setColor(new Color(43, 87, 78));
        g2d.fillOval(5,5,dim,dim);

        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(counter),dim/2,dim/2);
    }

    @Override
    public void update(double dt) {
        if(counter > goal){
            System.exit(0);
        }
    }
}
