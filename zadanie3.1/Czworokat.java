public abstract class Czworokat extends Figura {

    private final double[] boki;

    public Czworokat(double b1, double b2, double b3, double b4) {
		if (b1 <= 0 || b2 <= 0 || b3 <= 0 || b4 <= 0) {
            Wyjatki.niepoprawnyArgument();
        }

        this.boki = new double[]{ b1, b2, b3, b4 };
	}
    
    @Override
    public double obliczObwod() {
        double obwod = 0;
		
		for(double d : boki) obwod += d;
		
		return obwod;
    }

    public double[] getBoki() { return boki; }
}