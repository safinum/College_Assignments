public class Szesciokat extends Figura {

    private final double bok;

    @Override
    public double obliczObwod() {
        return 6 * bok;
    }

    @Override
    public double obliczPole() {
        return (3 * Math.sqrt(3))/2 * Math.pow(bok, 2);
    }

    public Szesciokat(double b) {
        if (b <= 0) {
            Wyjatki.niepoprawnyArgument();
        }
        this.bok = b;
    }
    
}