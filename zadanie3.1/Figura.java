public abstract class Figura implements Obliczenia {

    public void pokazInfo() {
        System.out.printf("%s: obwod = %.2f; pole = %.2f\n", this.getClass().getName(), obliczObwod(), obliczPole());
    }
}