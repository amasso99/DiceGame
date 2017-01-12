package View;

import Control.MainController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class MainFrame extends JFrame {

    // Attribute

    // Referenzen
    private MainController mainController;

    private JPanel interactionPanel;
    private DrawingPanel hashPanel, originalPanel, moddedPanel;

    /**
     * Konstruktor
     * @param name Der Titel des Fensters
     * @param x Die obere linke x-Koordinate des Fensters bzgl. des Bildschirms
     * @param y Die obere linke y-Koordinaite des Fensters bzgl. des Bildschirms
     * @param width Die Breite des Fensters
     * @param height Die Höhe des Fensters
     */
    public MainFrame(MainController mainController, String name, int x, int y, int width, int height) {
        this.mainController = mainController;

        this.hashPanel = new DrawingPanel();
        this.hashPanel.setOpaque(true);
        this.hashPanel.setBackground(new Color(0, 113, 255));

        this.originalPanel = new DrawingPanel();
        this.originalPanel.setOpaque(true);
        this.originalPanel.setBackground(new Color(157, 152, 3));

        this.moddedPanel = new DrawingPanel();
        this.moddedPanel.setOpaque(true);
        this.moddedPanel.setBackground(new Color(3, 199, 207));

        this.interactionPanel = new InteractionPanelHandler(mainController, hashPanel, originalPanel, moddedPanel).getPanel();

        this.getContentPane().setLayout(new GridLayout(2,2,10,10));

        this.getContentPane().add(interactionPanel);
        this.getContentPane().add(originalPanel);
        this.getContentPane().add(hashPanel);
        this.getContentPane().add(moddedPanel);

        addKeyListener(originalPanel);
        addKeyListener(hashPanel);
        addKeyListener(moddedPanel);

        this.setLocation(x,y);
        this.setSize(width,height);
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    /**
     * Liefert das obere DrawinPanel zurück.
     * @return Das obere DrawingPanel
     */
    public DrawingPanel getTopDrawingPanel(){
        return originalPanel;
    }

    /**
     * Liefert das untere DrawinPanel zurück.
     * @return Das untere DrawingPanel
     */
    public DrawingPanel getBottomDrawingPanel() {
        return moddedPanel;
    }
}
