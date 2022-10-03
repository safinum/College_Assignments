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
    JTextField text;
    JButton button;

    public Launcher () {

        frame = new JFrame();
        label = new JLabel("GUI wyswietlajace trojkat Pascala");
        text = new JTextField();
        button = new JButton();

        frame.setTitle("Trojkat Pascala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(158, 199, 204));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        label.setBounds(70, 80, 600, 50);
        label.setForeground(new Color(243, 250, 251));
        label.setFont(new Font("Arial Black", Font.BOLD, 30)); 

        text = new JTextField();
        text.setBounds(225, 200, 250, 125);
        text.setFont(new Font("Arial Black", Font.PLAIN, 40));
        text.setForeground(new Color(36, 77, 82));
        text.setBackground(new Color(169, 235, 247));
        text.setCaretColor(new Color(36, 77, 82)); 

        button.addActionListener(this);
        button.setBounds(170, 370, 350, 100);
        button.setText("Wyswietl trojkat");
        button.setFont(new Font("Arial Black", Font.PLAIN, 30));
        button.setFocusable(false);

        frame.add(label);
        frame.add(text);
        frame.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final int n;
        

        try {
            n  = Integer.parseInt(text.getText());
        }
        catch (NumberFormatException d) {
            JOptionPane.showMessageDialog(frame, "Niepoprawny parametr", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

            if (n <= 0 || n >= 20) {
                JOptionPane.showMessageDialog(frame, "Nieprawidlowy zakres", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                frame.dispose();
                new Triangle(n);   
            }
        
    }
}
