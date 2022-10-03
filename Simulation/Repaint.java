/** Wątek służacy odświeżaniu panelu. */
public class Repaint extends Thread{ 
    Panel p;
    
    /** Przypisuje panel do zmiennej p.
     * 
     * @param p Panel, który będzie odświeżany.
     */
    Repaint (Panel p) {
        this.p = p;
    }

    /** Uruchamia wątek, który odświeża panel co 60 milisekund. */
    @Override
    public void run () {
        try {
            while (true) { 
                Thread.sleep(60);
                p.repaint();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
