public class Romb extends Czworokat {

    private final double kat;

    @Override
    public double obliczPole() {
        return Math.pow(getBoki()[0], 2) * Math.sin(Math.toRadians(kat));
    }

    public Romb(double b, double k) {
        super(b, b, b, b);
        this.kat = k;

        if (k >= 180 || k <= 0) {
            Wyjatki.niepoprawnyArgument();
        }

    }
    
}