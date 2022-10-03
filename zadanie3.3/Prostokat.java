public class Prostokat extends Czworokat {

    @Override
    public double obliczPole() {
        return Figury.ObliczPole(Figury.DwaParametry.PROSTOKAT, getBoki()[0], getBoki()[1]);
    }

    @Override
    public double obliczObwod() {
        return Figury.ObliczObwod(Figury.DwaParametry.PROSTOKAT, getBoki()[0], getBoki()[1]);
    }
    
    public Prostokat(double b1, double b2) {
        super(b1, b1, b2, b2);
    }
}