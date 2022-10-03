public class Kolo extends Figura {

    private final double promien;

    @Override
    public double obliczObwod() { 
        return Figury.ObliczObwod(Figury.JedenParametr.KOLO, promien); 
    }

    @Override
    public double obliczPole() { 
        return Figury.ObliczPole(Figury.JedenParametr.KOLO, promien); 
    }

    
    public Kolo(double r) {
        if(r <= 0) Wyjatki.niepoprawnyArgument();

        this.promien = r;
    }
}