public class Kwadrat extends Czworokat {

    @Override
    public double obliczPole() {
        return Figury.ObliczPole(Figury.JedenParametr.KWADRAT, getBoki()[0]);
        //return Math.pow(getBoki()[0], 2);
    }

    @Override
    public double obliczObwod() {
        return Figury.ObliczObwod(Figury.JedenParametr.KWADRAT, getBoki()[0]);
    }

    public Kwadrat(double b) {
        super(b, b, b, b);
    }
    
}