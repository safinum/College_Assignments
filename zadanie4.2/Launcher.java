import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Launcher implements ActionListener {

    JFrame frame;
    JLabel label;
    JTextField text1;
    JTextField text2;
    JButton button;

    public Launcher () {

        frame = new JFrame();
        label = new JLabel("GUI wyswietlajace wiersz trojkata Pascala");
        text1 = new JTextField();
        text2 = new JTextField();
        button = new JButton();

        frame.setTitle("Trojkat Pascala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(158, 199, 204));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        label.setBounds(30, 25, 350, 50);
        label.setForeground(new Color(243, 250, 251));
        label.setFont(new Font("Arial Black", Font.BOLD, 12)); 

        text1.setBounds(95, 100, 200, 50);
        text1.setFont(new Font("Arial Black", Font.PLAIN, 15));
        text1.setForeground(new Color(36, 77, 82));
        text1.setBackground(new Color(169, 235, 247));
        text1.setCaretColor(new Color(36, 77, 82));
        
        text2.setBounds(95, 175, 200, 50);
        text2.setFont(new Font("Arial Black", Font.PLAIN, 15));
        text2.setForeground(new Color(36, 77, 82));
        text2.setBackground(new Color(169, 235, 247));
        text2.setCaretColor(new Color(36, 77, 82));

        button.addActionListener(this);
        button.setBounds(95, 250, 200, 50);
        button.setText("Wyswietl wiersz");
        button.setFocusable(false);

        frame.add(label);
        frame.add(text1);
        frame.add(text2);
        frame.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final int n;
        
        String[] parts;
        String[] parametry;

        try {
            n  = Integer.parseInt(text1.getText());
        }
        catch (NumberFormatException d) {
            JOptionPane.showMessageDialog(frame, "Nr wiersza nie jest liczba naturalna", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (n <= 0 || n >= 33) {
            JOptionPane.showMessageDialog(frame, "Nieprawidlowy nr wiersza", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String parametr = text2.getText();
            parts = parametr.split(" ");
            parametry = new String[parts.length + 1];
            parametry[0] = Integer.toString(n);

            for (int i = 0; i < parts.length; i++) {
                int x = Integer.parseInt(parts[i]);
                if (x > n + 1 || x < 0) {
                    JOptionPane.showMessageDialog(frame, "Nieprawidlowy zakres parametrow", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    parametry[i + 1] = parts[i];
                }    
            }
        }
        catch (NumberFormatException f) {
            JOptionPane.showMessageDialog(frame, "Parametr nie jest liczba naturalna", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

            frame.dispose();
            new Line(parametry);
        
    }
}