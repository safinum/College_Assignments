public class Kwadrat extends Czworokat {

    @Override
    public double obliczPole() {
        return Math.pow(getBoki()[0], 2);
    }

    public Kwadrat(double b) {
        super(b, b, b, b);
    }
    
}