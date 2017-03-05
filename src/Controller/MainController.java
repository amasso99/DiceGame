package Controller;

import View.MainFrame;

/**
 * Created by Oussama on 04.03.2017.
 */
public class MainController {

    private MainFrame mainFrame;
    private DiceController diceController;

    public MainController(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        diceController = new DiceController(this);
    }

    public MainFrame getMainFrame(){
        return  mainFrame;
    }
}
