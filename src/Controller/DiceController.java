package Controller;

import Model.Dice;
import Model.DiceCounter;
import View.DrawingPanel;
import View.InteractableObject;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Oussama on 04.03.2017.
 */
public class DiceController implements InteractableObject{

    private DiceCounter diceCounter;
    private MainController mainController;
    private MainFrame frame;

    private Dice dice;
    private Shape[]diceSide;
    private int[]diceValues;

    private double timer;

    public DiceController(MainController mainController){
        this.mainController = mainController;
        frame = mainController.getMainFrame();

        dice = new Dice();
        frame.getDrawingPanel().addObject(dice);


        String goal = JOptionPane.showInputDialog(frame, "What is your goal?");
        diceCounter = new DiceCounter(Integer.valueOf(goal));
        frame.getDrawingPanel().addObject(diceCounter);
        diceCounter.addValue(dice.getActualValue());

        diceSide = new Shape[4];
        initDiceSide();

        frame.getDrawingPanel().addObject(this);

        timer = 0;
    }

    private void initDiceSide() {
        int x = dice.getX();
        int y = dice.getY();
        int dim = dice.getDim();
        diceSide[0] = new Rectangle2D.Double(x + dim * 0.25, y - dim * 0.5, dim * 0.5, dim * 0.5);

        diceSide[1] = new Rectangle2D.Double(x + dim * 0.25, y + dim, dim * 0.5, dim * 0.5);

        diceSide[2] = new Rectangle2D.Double(x - dim * 0.5, y + dim * 0.25, dim * 0.5, dim * 0.5);

        diceSide[3] = new Rectangle2D.Double(x + dim, y + dim * 0.25, dim * 0.5, dim * 0.5);
    }

    @Override
    public void draw(DrawingPanel dp, Graphics2D g2d) {
        diceValues = dice.getAccessibleIndices();
        for (int i = 0; i < diceSide.length; i++) {
            g2d.setColor(Color.white);
            g2d.fill(diceSide[i]);
            g2d.setColor(Color.black);
        }

        for (int i = 0; i < diceSide.length; i++) {
            g2d.setColor(Color.black);
            g2d.setFont(FontLoader.DICE_FONT.deriveFont(60f));
            g2d.drawString(String.valueOf(Dice.interpretDicePoint(diceValues[i])),(int)(diceSide[i].getBounds().getX()+diceSide[i].getBounds().getWidth()/4),(int)(diceSide[i].getBounds().getY()+diceSide[i].getBounds().getHeight()/1.4));
        }


    }

    @Override
    public void update(double dt) {
        timer = timer +dt;
    }


    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mPoint = MouseInfo.getPointerInfo().getLocation();
        for (int i = 0; i < diceSide.length; i++) {
            if(diceSide[i].contains(mPoint) && timer > 2){
                dice.setCurrentValue(diceValues[i]);
                diceCounter.addValue(dice.getActualValue());

                timer = 0;
            }
        }
    }
}
