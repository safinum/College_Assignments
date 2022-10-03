public class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]); // Liczba kolumn.
        int m = Integer.parseInt(args[1]); // Liczba wierszy.
        int k = Integer.parseInt(args[2]); // Liczba milisekund, z ktorej bedzie losowany czas.
        double p = Double.parseDouble(args[3]); // Prawdopodobienstwo zmiany koloru.

        if (n > 0 && m > 0) {
            new Frame(n, m, k, p);
        }
        else {
            System.err.println("Niepoprawne wymiary panelu");
        }
    }
}