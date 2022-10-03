import javax.swing.JFrame;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/** Klasa tworząca okno. */
public class Frame extends JFrame implements ComponentListener {

    JFrame frame;
    Panel panel;

    /** Tworzy okno, dodaje do niego panel.
     * 
     * @param n Liczba kolumn na panelu.
     * @param m Liczba wierszy na panelu.
     * @param k Liczba milisekund, z której wątki będą losowały czas.
     * @param p Prawdopodobieństwo zmiany koloru.
     */
    public Frame(int n, int m, int k, double p) {
        int WIDTH = 1000; // Szerokosc panelu.
        int HEIGHT = 600; // Wysokosc panelu.
        frame = new JFrame();
        panel = new Panel(n, m, k, p, WIDTH, HEIGHT);
        frame.setTitle("Symulacja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(WIDTH, HEIGHT + 40); // Wysokosc okna wieksza o 40 pikseli od wysokosci panelu, zeby nie ucinalo prostokatow.
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.add(panel);
        frame.addComponentListener(this); // Dodaj ComponentListener, zeby moc odswiezac panel.
    }

    /** Umożliwia resize panelu i jego komórek.
     * 
     * @param e Event tworzony przez zmianę rozmiaru okna.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        panel.setSize(frame.getWidth(), frame.getHeight() - 40);  // Odswiezanie rozmiaru panelu.
        panel.repaint(); // Namaluj nowy panel.
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda componentResized działała. */
    @Override
    public void componentHidden(ComponentEvent e) {
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda componentResized działała. */
    @Override
    public void componentMoved(ComponentEvent e) {
    }

    /** Metoda nieużywana w projekcie, ale potrzebna, aby metoda componentResized działała. */
    @Override
    public void componentShown(ComponentEvent e) {
    }

}