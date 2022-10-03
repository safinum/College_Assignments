public class Kolo extends Figura {

    private final double promien;

    @Override
    public double obliczObwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    public double obliczPole() {
        return Math.PI * Math.pow(promien, 2);
    }

    public Kolo(double r) {
        if (r <= 0) {
            Wyjatki.niepoprawnyArgument();
        }
        this.promien = r;
    }

}