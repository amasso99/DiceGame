package Control;

import Model.Ball;
import Model.List;
import View.DrawingPanel;

import java.util.Random;

/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainController {

    private Random rng;

    private int maxWidth;
    private int moduloValue;

    private long time;
    private long loops;
    private long switches;

    private Ball lastFound;
    private Ball[] originalArray;
    private Ball[] moddedArray;
    private List<Ball>[] hashArray; //Hier wird mit einer Verkettung gearbeitet

    public MainController(){
        rng = new Random();
        rng.setSeed(System.currentTimeMillis());

        moduloValue = 10;
    }

    /**
     * Erzeugt ein Array aus zufällig gefüllten Kreises mit sinnvollen Koordinaten.
     * @param amount Anzahl der Bälle
     * @param originalPanel Panel zur Darstellung des Urpsrungsarrays.
     * @param moddedPanel Panel zur Darstellung des abgeändereten (sortierten?) Arrays.
     */
    public void generateArray(int amount, DrawingPanel originalPanel, DrawingPanel moddedPanel){
        originalArray = new Ball[amount];
        moddedArray = new Ball[amount];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int x = 10;
        int y = 10;
        int maxRandom = Integer.max(100,amount);
        this.maxWidth = originalPanel.getWidth();
        for(int i = 0; i < amount; i++){
            Ball newBall = new Ball(x, y, rng.nextInt(maxRandom), alphabet.charAt(rng.nextInt(alphabet.length())));
            originalArray[i] = newBall;
            originalPanel.addObject(originalArray[i]);

            Ball copyBall = newBall.getCopy();
            moddedArray[i] = copyBall;
            moddedPanel.addObject(moddedArray[i]);

            x += 20;
            if ( x > maxWidth - 10 ){
                x = 10;
                y += 20;
            }
        }
    }

    /**
     * Erzeugt eine frische, unsortierte Kopie des Original-Arrays.
     */
    public void recopy(DrawingPanel moddedPanel){
        moddedPanel.removeAllObjects();
        for(int i = 0; i < originalArray.length; i++){
            Ball copyBall = originalArray[i].getCopy();
            moddedArray[i] = copyBall;
            moddedPanel.addObject(moddedArray[i]);
        }
    }

    /**
     * Setzt für alle Kugeln im sortierten Array neue Koordinaten gemäß der Reihenfolge im Array.
     * Muss nach dem Sortieren aufgerufen werden, damit die Sortierung sichtbar wird.
     */
    private void updateCoordinates(){
        int x = 10;
        int y = 10;
        for(int i = 0; i < moddedArray.length; i++){
            moddedArray[i].setX(x);
            moddedArray[i].setY(y);

            x += 20;
            if ( x > maxWidth - 10 ){
                x = 10;
                y += 20;
            }
        }
    }

    /**
     * Führt eine lineare Suche auf dem modded-Array durch.
     * @param key Die gesuchte Zahl.
     */
    public void linSearchArray(int key) {
        if(lastFound != null){
            lastFound.setMarked(false);
        }
        time = System.nanoTime();
        loops = 0;

        // Lineare Suche Start
        lastFound = null;
        int i = 0;
        while ( lastFound == null && i < moddedArray.length){
            loops++;
            if (moddedArray[i].getNumber() == key){
                lastFound = moddedArray[i];
            }
            i++;
        }
        // Lineare Suche Ende
        time = (System.nanoTime() - time)/1000;
        if(lastFound != null){
            lastFound.setMarked(true);
        }
    }

    /**
     * Führt eine binäre Suche auf dem modded-Array durch.
     * @param key Die gesuchte Zahl.
     */
    public void binSearchArray(int key) {
        if(lastFound != null){
            lastFound.setMarked(false);
        }
        time = System.nanoTime();
        loops = 0;
        // Binäre Suche Start
        //TODO 01: Orientiere dich für die Messung der Schleifendurchgänge an der Linearen Suche und implementiere die Binäre Suche iterativ.
        lastFound = null;

        int left = 0;
        int right = moddedArray.length-1;
        int mid = right/2;
        while(!(left == right && left == mid)){
            loops++;
            if(moddedArray[mid].getNumber() < key){
                left = mid +1;
            }else if(moddedArray[mid].getNumber() > key){
                right = mid - 1;
            }else if( moddedArray[mid].getNumber() == key){
                lastFound = moddedArray[mid];
                break;
            }
            if(moddedArray[left] == moddedArray[right] && moddedArray[left].getNumber()==key ){
                lastFound = moddedArray[left];
                break;
            }
            mid = (left+right)/2;
        }
        // Binäre Suche Ende
        time = (System.nanoTime() - time)/1000;
        if(lastFound != null){
            lastFound.setMarked(true);
        }
    }

    /**
     *
     * Sortiert das modded-Array gemäß dem Bubble-Sort-Algorithmus.
     */
    public void bubbleSortArray() {
        time = System.nanoTime();
        loops = 0;
        switches = 0;
        // Bubblesort Start
        for(int i = 0; i < moddedArray.length; i++){
            loops++;
            for (int j = 0; j < moddedArray.length-1-i; j++){
                loops++;
                if(moddedArray[j].getNumber() > moddedArray[j+1].getNumber()){
                    switchBalls(j, j+1);
                }
            }
        }
        // Bubble Sort Ende
        time = (System.nanoTime() - time)/1000;
        updateCoordinates();
    }

    /**
     * Sortiert das modded-Array gemäß dem Selection-Sort-Algorithmus.
     */
    public void selectionSortArray() {
        time = System.nanoTime();
        loops = 0;
        switches = 0;
        // Selectionsort Start
        //TODO 02: Orientiere dich für die Messung der Schleifendurchgänge und der tatsächlichen Vertauschungen an Bubblesort und implementiere Selectionsort inplace.
        for(int i = 0; i < moddedArray.length-1; i++){
            int min = i;
            for (int j = min + 1; j < moddedArray.length; j++) {
                loops++;
                if(moddedArray[min].getNumber() > moddedArray[j].getNumber()){
                    min = j;
                }
            }
            switchBalls(min,i);
        }
        // Selection Sort Ende
        time = (System.nanoTime() - time)/1000;
        updateCoordinates();
    }

    /**
     * Sortiert das modded-Array gemäß dem Insertion-Sort-Algorithmus.
     */
    public void insertionSortArray() {
        time = System.nanoTime();
        loops = 0;
        switches = 0;
        // Insertionsort Start
        //TODO 03: Orientiere dich für die Messung der Schleifendurchgänge und der tatsächlichen Vertauschungen an Bubblesort und implementiere Insertionssort inplace.
        int j;
        for (int i = 0; i < moddedArray.length; i++) {
            int tempBall = i;
            j = i;
            while(j > 0 && moddedArray[j-1].getNumber() > moddedArray[tempBall].getNumber()){
                loops++;
                switchBalls(j,tempBall);
            }
            j = tempBall;
        }
        // Insertion Sort Ende
        time = (System.nanoTime() - time)/1000;
        updateCoordinates();
    }

    /**
     * Sortiert das modded-Array gemäß dem Quick-Sort-Algorithmus.
     */
    public void quickSortArray() {
        time = System.nanoTime();
        loops = 0;
        switches = 0;
        // Quick Sort Start
        quickSortRekursiv(0,moddedArray.length-1);
        // Quick Sort Ende
        time = (System.nanoTime() - time)/1000;
        updateCoordinates();
    }

    /**
     * Die eigentliche rekursive Quicksort-Methode.
     */
    private void quickSortRekursiv(int start, int end){
        loops++;
        int i = start;
        int j = end;
        int middle =  (i + j) / 2;
        int pivot = moddedArray[middle].getNumber();

        //Beginn des Zaubers
            //TODO 05: Programmiere den rekursiven Quicksortalgorithmus. Halte dich an den hier vorgegeben Rahmen.
        //Ende des Zaubers
    }

    /**
     * Die Bälle werden gemäß der Hashfunktion in der Hashtabelle gepspeichert.
     * Dazu werden alle Bälle zunächst kopiert und dann in die passenden Listen von hashArray übertragen.
     * Anschließend müsst ihr noch für die zeichnerische Darstellung der Bälle die jeweilige x- und y-Koordinate aktualisieren.
     * @param hashPanel
     */
    public void hashIt(DrawingPanel hashPanel){
        hashPanel.removeAllObjects();
        hashArray = new List[moduloValue]; //Die Länge des Arrays wird durch die Anzahl prinzipiell möglicher Funktionswerte der Hash-Funktion festgelegt.
        for (int i = 0; i < hashArray.length; i++) {
            hashArray[i] = new List<Ball>();
        }
        //TODO 04a: Implementiere eine Hashfunktion samt Sortierung der Bälle ins Hasharray. Vergiss nicht, dann auch die Hashsuche zu implementieren!
        for (int i = 0; i < originalArray.length; i++) {
            int temp = hashFunction(originalArray[i].getNumber());
            hashArray[temp].append(originalArray[i].getCopy());
        }

        for (int i = 0; i < hashArray.length; i++) {
            int x = 10+(i*(hashPanel.getWidth()/hashArray.length));
            int y = 10;
            hashArray[i].toFirst();
            while(hashArray[i].hasAccess()){
                Ball ball = hashArray[i].getContent();
                ball.setX(x);
                ball.setY(y);
                hashPanel.addObject(ball);
                y = y + 20;
                hashArray[i].next();
            }
        }

            //TODO 04b: Überarbeitung der Koordinaten der Bälle in der Hashtabelle für die Darstellung in der View.
    }

    /**
     * Die Hashfunktion für die Methode hashIt(...)
     * @param argument Das übergebene Funktionsargument
     * @return Funktionswert
     */
    private int hashFunction(int argument){
            //TODO 4c: Implementiere eine vernünftige Hashfunktion.
        return argument%moduloValue;
    }

    /**
     * Führt eine Hash-Suche auf dem Hash-Arrays durch.
     * @param key
     */
    public void hashSearch(int key){
            //TODO 4d: Implementiere die Suche auf der Hashtabelle.
        if(lastFound != null){
            lastFound.setMarked(false);
        }
        time = System.nanoTime();
        loops = 0;
        // Hash Suche Start
        //TODO 01: Orientiere dich für die Messung der Schleifendurchgänge an der Linearen Suche und implementiere die Binäre Suche iterativ.
        lastFound = null;
        int tempKey = hashFunction(key);
        List<Ball> list = hashArray[tempKey];
        list.toFirst();
        while(list.hasAccess()){
            if(list.getContent().getNumber() == key){
                lastFound = list.getContent();
                list.toLast();
            }
            list.next();
            loops++;
        }
        // Hash Suche Ende
        time = (System.nanoTime() - time)/1000;
        if(lastFound != null){
            lastFound.setMarked(true);
        }


    }

    /**
     * Vertausch zwei Bälle innerhalb des Arrays, das verändert wird.
     * Bei jedem Aufruf dieser Methode wird das Attribut switches hochgezählt.
     * @param a Indexposition des einen Balls
     * @param b Indexposition des anderen Balls
     */
    private void switchBalls(int a, int b){
        Ball temp = moddedArray[a];
        moddedArray[a] = moddedArray[b];
        moddedArray[b] = temp;

        switches++;
    }

    public long getTime() {
        return time;
    }

    public long getLoops() {
        return loops;
    }

    public long getSwitches() {
        return switches;
    }
}
