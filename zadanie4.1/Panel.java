import java.awt.Graphics;

import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JPanel;

public class Panel extends JPanel {

    int n;

    public Panel (int n, int w, int h) {
        this.n = n;
        this.setSize(w, h);
        this.setBackground(new Color(158, 199, 204));
    }

    public void odswiez(int w, int h){
        this.setSize(w, h);
        this.setBackground(new Color(158, 199, 204));
        super.repaint();
    }
    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth(); //szerokosc panelu
        int height = this.getHeight(); //wysokosc panelu


        int wiersze = this.n; //liczba wierszy

  
        int prostokat_height = height / wiersze; //wysokosc pojedynczego prostokata
        int prostokat_width = width / wiersze; //szerokosc pojedynczego prostokata

        System.out.println(n);

        for (int col = 0; col < wiersze; col++) {
            //wiesz trojkata Pascala odpowiadajacy wartosci col
            ArrayList<Integer> wiersz = new WierszTrojkataPascala(col).getWiersz();

            //petla tworzaca pojedyncze prostokaty w wierszu
            for (int row = 0; row < col+1; row++) {
                Prostokat p = new Prostokat(row, col, wiersz.get(row));
                p.draw(g, prostokat_width, prostokat_height, width, height);
            }
        }
 
    }
    
}
