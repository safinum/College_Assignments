import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Prostokat {
    private int row, col, number; 
    public Prostokat (int row, int col, int number) {
      this.row = row;
      this.col = col;
      this.number = number;
    }
  
    public void draw(Graphics g, int width, int height, int WIDTH, int HEIGHT) {
        //offsetX - mnoznik, ktory pozwala ustalic odpowiednie przesuniecie
        //prostokata w lewo lub w prawo
        int offsetX = -this.col + 2 * this.row; 

        //x, y - wspolrzedne gornego lewego wierzcholka prostokata
        int x = WIDTH / 2 + offsetX * width / 2 - width / 2;
        int y = height * this.col;
        Graphics g2D = (Graphics2D) g;
        g2D.setColor(new Color(228, 250, 253));
        g2D.fillRect(x, y, width, height);
        g2D.setColor(new Color(7, 30, 36));
        g2D.drawRect(x, y, width, height);
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial Black", Font.PLAIN, 20));
        g2D.drawString(Integer.toString(this.number), x + 20, y + height/2);
  
    }
}