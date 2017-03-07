package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class MainFrame extends JFrame {

    // Attribute
    private DrawingPanel drawingPanel;

    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;


    /**
     * Konstruktor
     * @param name Der Titel des Fensters
     */
    public MainFrame(String name) {
        this.drawingPanel = new DrawingPanel();
        this.drawingPanel.setOpaque(true);
        this.drawingPanel.setBackground(new Color(157, 83, 138));

        this.getContentPane().add(drawingPanel);

        addKeyListener(drawingPanel);

        this.setLocation(0,0);
        this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        this.setVisible(true);
    }

    /**
     * Liefert das obere DrawinPanel zurück.
     * @return Das obere DrawingPanel
     */
    public DrawingPanel getDrawingPanel(){
        return drawingPanel;
    }
}
