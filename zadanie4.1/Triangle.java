
import javax.swing.JFrame;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Triangle extends JFrame implements ComponentListener {

    JFrame frame;
    Panel panel;

    public Triangle(int n) {
        int WIDTH = 1000;
        int HEIGHT = 600;
        frame = new JFrame();
        panel = new Panel(n, WIDTH, HEIGHT);
        frame.setTitle("Trojkat Pascala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        // ucina prostokaty na dole, panel musi byc mniejszy
        frame.setSize(WIDTH, HEIGHT + 40);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.add(panel);
        frame.addComponentListener(this);

    }

    @Override
    public void componentResized(ComponentEvent e) {
        panel.odswiez(frame.getWidth(), frame.getHeight() - 40);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

}
