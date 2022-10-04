
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Panel extends JPanel {

    JTextArea textArea;
    JScrollPane scroll;


    public Panel (String[] parametry) {

        textArea = new JTextArea(20, 25);
        textArea.setBorder(BorderFactory.createLineBorder(new Color(36, 77, 82), 1));
        Pro wiersz = new Pro();
        String str = wiersz.out(parametry);
        textArea.append(str);

        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        this.setSize(400, 400);
        this.setBackground(new Color(158, 199, 204));

        this.add(scroll);
    }
    
}