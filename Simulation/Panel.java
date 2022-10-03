import java.awt.Graphics;
import java.util.Date;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Klasa umieszczająca na panelu komórki będące wątkami, wypełniająca je odpowiednim kolorem i zatrzymująca lub wznawiająca
 * wątki po kliknięciu.
 */
public class Panel extends JPanel implements MouseListener {

    int n;
    int m;
    int k;
    double p;
    Cell[][] cells;
    int corX;
    int corY;

    static Random rand = new Random(new Date().getTime()); // Pseudolosowy generator. getTime() sluzy temu, aby z kazdym kolejnym
                                                           // uruchomieniem kolory byly inne.

    int width;
    int height;
    int w;
    int h;

    /** Tworzy odpowiednią liczbę obiektów klasy Cell będących wątkami, przypisuje im sąsiadów, uruchamia wątki każdej komórki
     * oraz jeden wątek odświeżający planszę.
     * 
     * @param n Liczba kolumn.
     * @param m Liczba wierszy.
     * @param k Liczba milisekund, z której komórki będą losować czas zmiany koloru.
     * @param p Prawdopodobieństwo zmiany koloru.
     * @param w Szerokość panelu.
     * @param h Wysokość panelu.
     */
    public Panel (int n, int m, int k, double p, int w, int h) {
        this.cells = new Cell[n][m];
        this.n = n;
        this.m = m;
        this.k = k;
        this.p = p;
        this.setSize(w, h);
        this.addMouseListener(this);

        for (int i = 0; i < n; i++) { // Dodaj do tablicy cells[][] n x m komorek.
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell(k, p, rand);
            }
        }

        for (int i = 0; i < n; i++) { // Dodaj kazdej komorce sasiadow.
            for (int j = 0; j < m; j++) {
                cells[i][j].addNeighbours(
                cells[(i + 1) % n][j], 
                cells[(i - 1 + n) % n][j], 
                cells[i][(j + 1) % m], 
                cells[i][(j - 1 + m) % m]);
            }
        }

        for (int i = 0; i < n; i++) { // Uruchom watki.
            for (int j = 0; j < m; j++) {
                cells[i][j].start();
            }
        }

        new Repaint(this).start(); // Uruchom watek, ktory bedzie odswiezal cala plansze.
    }

    /** Wypełnia komórki odpowiednim kolorem (losowym lub zależnym od sąsiadów).
     * Jeśli wątek jest zatrzymany, wypełnia komórkę na biało.
     * 
     * @param gr Obiekt klasy Graphics, potrzebny do ustawienia koloru i wypełniania.
     */
    public void paintComponent (Graphics gr) {
        super.paintComponent(gr);

        width = this.getWidth(); // Szerokosc panelu.
        height = this.getHeight(); // Wysokosc panelu.
        h = height / this.m; // Wysokosc pojedynczej komorki.
        w = width / this.n; // Szerokosc pojedynczej komorki.

        Graphics g2D = (Graphics2D) gr;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                int r = this.cells[i][j].r; // Pobierz wartosci r, g, b z komorki.
                int g = this.cells[i][j].g;
                int b = this.cells[i][j].b;

                if (this.cells[i][j].isStopped == false) { // Jezeli watek nie jest zatrzymany, to wypelnij go odpowiednim kolorem.
                    g2D.setColor(new Color(r, g, b)); 
                    g2D.fillRect(i * w, j * h, w, h); 
                }
                else { // Jezeli watek jest zatrzymany, wypelnij go na bialo.
                    g2D.setColor(Color.WHITE);
                    g2D.fillRect(i * w, j * h, w, h); 
                }
            }
        }
    }

    /** Ustawia klikniętą komórkę jako zatrzymaną lub wznowioną.
     * 
     * @param e Event utworzony przez kliknięcie w komórkę.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        width = this.getWidth(); // Szerokosc panelu.
        height = this.getHeight(); // Wysokosc panelu.
        h = height / this.m; // Wysokosc pojedynczej komorki.
        w = width / this.n; // Szerokosc pojedynczej komorki.

        corX = (e.getX()/w); // Wspolrzedne x i y kliknietej komorki.
        corY = (e.getY()/h);

        if (cells[corX][corY].isStopped == false) { // Jesli klikniety watek nie jest zatrzymany, zatrzymaj go.
            cells[corX][corY].isStopped = true;
        }
        else { // Jesli jest zatrzymany, to go wznow.
            cells[corX][corY].isStopped = false;
        }
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda mouseClicked działała. */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda mouseClicked działała. */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda mouseClicked działała. */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda mouseClicked działała. */
    @Override
    public void mouseReleased(MouseEvent e) {
    } 
}
