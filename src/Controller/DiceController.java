package Controller;

import Model.Dice;
import View.MainFrame;

/**
 * Created by Oussama on 04.03.2017.
 */
public class DiceController {

    private MainController mainController;
    private MainFrame frame;
    private Dice[]dices;

    private final int DICE_AMOUNT = 2;

    public DiceController(MainController mainController){
        this.mainController = mainController;
        frame = mainController.getMainFrame();

        initDices();

    }

    private void initDices() {
        dices = new Dice[DICE_AMOUNT];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice(50+(i*150));
            frame.getTopDrawingPanel().addObject(dices[i]);
        }
    }

}
