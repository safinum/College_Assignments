public class Romb extends Czworokat {

    private final double kat;

    @Override
    public double obliczPole() {
        return Figury.ObliczPole(Figury.DwaParametry.ROMB, getBoki()[0], kat);
    }

    @Override
    public double obliczObwod() {
        return Figury.ObliczObwod(Figury.DwaParametry.ROMB, getBoki()[0], kat);
    }

    public Romb(double b, double k) {
        super(b, b, b, b);
        this.kat = k;

        if (k >= 180 || k <= 0) {
            Wyjatki.niepoprawnyArgument();
        }

    }
    
}