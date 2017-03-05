import Controller.MainController;
import View.MainFrame;

import java.awt.*;

/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainProgram {

    public static void main (String[] args){
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        MainProgram.setup();
                    }
                });
    }

    private static void setup(){
        MainFrame mainFrame = new MainFrame("A dice game");
        MainController mainController = new MainController(mainFrame);
    }

}
