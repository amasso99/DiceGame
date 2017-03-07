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
    private int actualValue;
    private int dim;
    private int x;
    private int y;

    public Dice(){
        diceValues = new int[]{1, 2, 3, 4, 5, 6};
        dim = (int) (MainFrame.SCREEN_WIDTH*0.3);
        y = MainFrame.SCREEN_HEIGHT/2 - dim/2;
        x = MainFrame.SCREEN_WIDTH/2 - dim/2;

        rollIt();
    }

    public int getActualValue(){
        return diceValues[actualValue];
    }

    public void rollIt(){
        actualValue = (int) (diceValues.length*Math.random());
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        g2d.fillRect(x , y,dim ,dim);
        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(diceValues[actualValue]),MainFrame.SCREEN_WIDTH/2, MainFrame.SCREEN_HEIGHT/2);
    }

    @Override
    public void update(double dt) {

    }

    public int[] getNotAccessibleIndices(){
        return new int[]{actualValue, diceValues.length-actualValue};
    }

    public int[] getDiceValues(){
        return diceValues;
    }

    public int getX() {
        return x;
    }

    public int getDim() {
        return dim;
    }

    public int getY() {
        return y;
    }

}
