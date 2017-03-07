package Controller;

import Model.Dice;
import View.DrawingPanel;
import View.InteractableObject;
import View.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Oussama on 04.03.2017.
 */
public class DiceController implements InteractableObject{

    private MainController mainController;
    private MainFrame frame;

    private Dice dice;
    private Shape[]diceSide;

    public DiceController(MainController mainController){
        this.mainController = mainController;
        frame = mainController.getMainFrame();

        dice = new Dice();
        frame.getDrawingPanel().addObject(dice);

        diceSide = new Shape[4];
        initDiceSide();

        frame.getDrawingPanel().addObject(this);
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
        for (int i = 0; i < diceSide.length; i++) {
            g2d.setColor(Color.white);
            g2d.fill(diceSide[i]);
            g2d.setColor(Color.black);
        }
        for (int i = 0; i < diceSide.length; i++) {

        }
    }

    @Override
    public void update(double dt) {

    }


    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
