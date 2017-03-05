package Model;

import View.DrawableObject;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;

/**
 * Created by Oussama on 04.03.2017.
 */
public class Dice implements DrawableObject {

    private int[]diceValues;
    private int actualValue, x;

    public Dice(int x){
        diceValues = new int[]{1, 2, 3, 4, 5, 6};
        this.x = x;
        rollIt();
    }

    public int getActualValue(){
        return diceValues[actualValue];
    }

    public int[] getNotAccessibleIndices(){
        return new int[]{actualValue, diceValues.length-actualValue};
    }

    public void rollIt(){
        actualValue = (int) (diceValues.length*Math.random());
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.fillRect(x, (int) ((int) (MainFrame.SCREEN_HEIGHT*0.5)-(MainFrame.SCREEN_WIDTH*0.15)), (int) (MainFrame.SCREEN_WIDTH*0.3),(int) (MainFrame.SCREEN_WIDTH*0.3));
        //1# side

    }

    @Override
    public void update(double dt) {

    }
}
