import java.util.Random;

import java.awt.Color;

/** Pojedyncza komórka na panelu, będaca wątkiem. */
public class Cell extends Thread {

    public int r;
    public int g;
    public int b;
    private long time;
    private double p;

    private Cell[] neighbours = new Cell[4];
    public boolean canRead = true;
    static Neighbourhood neighbourhood;
    static Random rand;
    public boolean isStopped = false;

    /** Losuje czas, z jakim komórka będzie zmieniać kolor. Tworzy jeden obiekt klasy Neighbourhood i daje mu dostęp
     * do generatora pseudolosowego.
     * 
     * @param k Liczba milisekund, z której będzie losowany czas.
     * @param p Prawdopodobieństwo zmiany koloru na losowy.
     * @param rand Generator pseudolosowy.
     */
    Cell (int k, double p, Random rand) {
        if (Cell.neighbourhood == null) {
            Cell.neighbourhood = new Neighbourhood(rand); // Stworz jeden obiekt klasy Neighbourhood współdzielony przez wszystkie wątki
                                                          // i daj mu dostęp do generatora pseudolosowego.
        }
        if (Cell.rand == null) {
            Cell.rand = rand;
        }
        this.randomColor(); // Nadaj komorce losowy kolor.
        this.p = p;
        int min = (int) (0.5 * k);
        int max = (int) (1.5 * k);
        this.time = min + rand.nextInt(max - min + 1); // Czas, co jaki komorka zmienia kolor.
    }

    /** Dodaje sąsiadów komówki do tablicy neighbours.
     * 
     * @param n0 Pierwszy sąsiad.
     * @param n1 Drugi sąsiad.
     * @param n2 Trzeci sąsiad.
     * @param n3 Czwarty sąsiad.
     */
    public void addNeighbours (Cell n0, Cell n1, Cell n2, Cell n3) {
        this.neighbours[0] = n0;
        this.neighbours[1] = n1;
        this.neighbours[2] = n2;
        this.neighbours[3] = n3;
    }

    /** Generuje losowy, jasny kolor. */
    public void randomColor () {
        float h = Cell.rand.nextFloat();
        float s = 1f;
        float MIN_BRIGHTNESS = 1f;
        float b = MIN_BRIGHTNESS + ((1f - MIN_BRIGHTNESS) * rand.nextFloat());
        Color c = Color.getHSBColor(h, s, b);
        this.r = c.getRed();
        this.g = c.getGreen();
        this.b = c.getBlue();
    }

    /** Uruchamia wątek. Zmienia kolor co odpowiednią wartość time, w zależności od kolorów sąsiadów. */
    @Override
    public void run () {
        try {
            while (true) {
                Thread.sleep(time); 
                neighbourhood.changeColor(p, this, neighbours[0], neighbours[1], neighbours[2], neighbours[3]); // Zmien kolor w zaleznosci od sasiadow
                while (this.isStopped) { // Jesli isStopped = true, zatrzymaj watek.
                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/** Klasa współdzielona pomiędzy wątkami, służąca do ich synchronizacji przy zmianie koloru. */
class Neighbourhood {
    static Random rand;

    /** Daje dostęp do generatora pseudolosowego.
     * 
     * @param rand Generator pseudolosowy.
     */
    Neighbourhood (Random rand) {
        if (Neighbourhood.rand == null) {
            Neighbourhood.rand = rand;
        }
    }

    /** Zmienia kolor komórki. Z prawdopodobieństwem p zmienia kolor na losowy, z prawdopodobieństwem 1 - p zmienia kolor
     * w zależności od niezatrzymanych sąsiadów.
     * 
     * @param p Prawdopodobieństwo zmiany koloru na losowy.
     * @param me Komórka, która zmienia kolor.
     * @param s0 Pierwszy sąsiad komórki.
     * @param s1 Drugi sąsiad komórki.
     * @param s2 Trzeci sąsiad komórki.
     * @param s3 Czwarty sąsiad komórki.
     */
    synchronized void changeColor (double p, Cell me, Cell s0, Cell s1, Cell s2, Cell s3) {
        float v = rand.nextFloat(); // Wylosuj liczbe pomocnicza dla prawdopodobienstwa.

        if (v > p) { 
            while ((!s0.canRead && !s0.isStopped) || (!s1.canRead && !s1.isStopped) || (!s2.canRead && !s2.isStopped) || (!s3.canRead && !s3.isStopped)) { 
                // Jesli nie mozna czytac koloru sasiada i nie jest on zastopowany, to czekaj.
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println("error");
                }
            }
            me.canRead = false; // Odbierz mozliwosc czytania koloru zanim go zmienisz.

            if ((!s0.canRead && !s0.isStopped) || (!s1.canRead && !s1.isStopped) || (!s2.canRead && !s2.isStopped) || (!s3.canRead && !s3.isStopped)) {
                System.err.println("Sasiad zmienia kolor");
            } // Wyswietl blad w razie proby przeczytania koloru sasiada, ktory go zmienia.

            Cell[] nb = {s0, s1, s2, s3};
            int rT = 0;
            int gT = 0;
            int bT = 0;
            int unstoppedCounter = 0;

            //Liczenie sredniej z koloru sasiadow, nie uwzgledniajac tych zastopowanych.
            for (int i = 0; i < 4; i++) { 
                if (!nb[i].isStopped) {
                    rT += nb[i].r;
                    gT += nb[i].g;
                    bT += nb[i].b;
                    unstoppedCounter++;
                }
            } 

            if (unstoppedCounter > 0) {
                me.r = rT / unstoppedCounter;
                me.g = gT / unstoppedCounter;
                me.b = bT / unstoppedCounter;
            }
            me.canRead = true; // Po zmianie koloru daj mozliwosc czytania.
        }
        else { // Z prawdopodobienstwem p zmien kolor na losowy.
            me.canRead = false; // Przed zmiana koloru spraw, zeby sasiedzi nie mogli czytac twojego koloru.
            me.randomColor();
            me.canRead = true; // Po zmianie koloru jest on mozliwy do odczytania.
        }
        notifyAll();
    }
}
//nie zsynchronizuje sie, moga 2 wyjsc z while -  -, tak samo ustawianie isStopped na false i true nie jest zsynchronizowane 
