import Control.MainController;
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
        MainController mainController = new MainController();
        MainFrame mainFrame = new MainFrame(mainController, "Laufzeitanalyse per Programm",50,50,1100,900);
    }

}
