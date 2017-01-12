package Model;

import View.DrawableObject;
import View.DrawingPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Zeichnet einen Kreis mit Radius 20 mit einer Zahl und einem Buchstaben darin.
 * Dient zur Visualisierung von Datenstrukturen und der Wirkung von Such-
 * und Sortieralgorithmen.
 *
 * @author M. Kneblewski
 *
 */
public class Ball implements DrawableObject {

    private Color black, red, white;

    private boolean isMarked;
    private double x,y;
    private int number;
    private char character;

    private Ellipse2D.Double circle;
    private Font font;

    /**
     * Erzeugt einen Kreis.
     * @param pX x-Position der oberen, linken Ecke.
     * @param pY y-Position der oberen, linken Ecke.
     * @param n Zahl, die im Kreis stehen soll.
     * @param c Buchstabe, der im Kreis stehen soll.
     */
    public Ball(int pX, int pY, int n, char c) {
        this.x = pX;
        this.y = pY;
        black = new Color(0,0,0);
        red = new Color(255,0,0);
        white = new Color(255,255,255);
        number = n;
        character = c;
        isMarked = false;
        circle = new Ellipse2D.Double(x,y,20,20);
        font = new Font("Sans",Font.PLAIN,9);
    }

    /**
     * Zeichnet den Kreis. Wird von DrawingZone aufgerufen, falls der Kreis
     * dort hinzugefügt wurde.
     */
    public void draw(DrawingPanel dp, Graphics2D g2d){
        g2d.setColor(white);
        if(isMarked){
            g2d.setColor(red);
        }
        g2d.fill(circle);
        g2d.setColor(black);
        g2d.draw(circle);
        g2d.setFont(font);
        g2d.drawString(""+number+character, (int)x+3, (int)y+14);
    }

    public void update(double dt){
        circle.setFrame(x,y,20,20);
    }

    /**
     * Erzeugt einen exakten Klon des Kreises und gibt ihn zurück.
     * @return der Objekt-Klon.
     */
    public Ball getCopy(){
        Ball copy = new Ball((int)x,(int)y,number,character);
        return copy;
    }

    /**
     * Gibt Auskunft darüber, ob der Kreis gerade farbig markiert ist.
     * @return Ist der Kreis markiert?
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Setzt die Markierung des Kreises
     * @param isMarked Soll der Kreis markiert sein?
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public int getX() {
        return (int)x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Liefert die Nummer im Kreis.
     * @return Die Nummer.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Verändert die Nummer des Kreises.
     * @param number Die neue Nummer.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Liefert den Buchstaben im Kreis.
     * @return Der Buchstabe.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Verändert den Buchstaben im Kreis.
     * @param character Der neue Buchstabe.
     */
    public void setCharacter(char character) {
        this.character = character;
    }

}
