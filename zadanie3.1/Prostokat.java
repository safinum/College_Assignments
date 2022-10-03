public class Prostokat extends Czworokat {

    @Override
    public double obliczPole() {
        return getBoki()[0] * getBoki()[3];
    }
    
    public Prostokat(double b1, double b2) {
        super(b1, b1, b2, b2);
    }
}