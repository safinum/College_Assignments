
import javax.swing.JFrame;

public class Line {

    JFrame frame;
    Panel panel;

    public Line (String[] parametry) {

        frame = new JFrame();
        panel = new Panel(parametry);

        frame.setTitle("Wiersz trojkata Pascala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.add(panel);

    }
    
}
