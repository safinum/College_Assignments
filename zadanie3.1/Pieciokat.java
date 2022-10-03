public class Pieciokat extends Figura {

    private final double bok;

    @Override
    public double obliczObwod() {
        return 5 * bok;
    }

    @Override
    public double obliczPole() {
        return (Math.sqrt(25 + 10 * Math.sqrt(5)))/4 * Math.pow(bok, 2);
    }

    public Pieciokat(double b) {
        if (b <= 0) {
            Wyjatki.niepoprawnyArgument();
        }
            this.bok = b;
    }
}