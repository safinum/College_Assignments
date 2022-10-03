public class Pieciokat extends Figura {

    private final double bok;

    @Override
    public double obliczObwod() {
        return Figury.ObliczObwod(Figury.JedenParametr.PIECIOKAT, bok);
    }

    @Override
    public double obliczPole() {
        return Figury.ObliczPole(Figury.JedenParametr.PIECIOKAT, bok);
    }

    public Pieciokat(double b) {
        if (b <= 0) {
            Wyjatki.niepoprawnyArgument();
        }
            this.bok = b;
    }
}