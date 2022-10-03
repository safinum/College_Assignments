public class Szesciokat extends Figura {

    private final double bok;

    @Override
    public double obliczObwod() {
        return Figury.ObliczObwod(Figury.JedenParametr.SZESCIOKAT, bok);
    }

    @Override
    public double obliczPole() {
        return Figury.ObliczPole(Figury.JedenParametr.SZESCIOKAT, bok);
    }

    public Szesciokat(double b) {
        if (b <= 0) {
            Wyjatki.niepoprawnyArgument();
        }
        this.bok = b;
    }
    
}