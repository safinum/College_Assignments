import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Klasa odpowiedzialna za wierzchołki drzewa binarnego.
 * Przechowuje wartość klucza, lewe dziecko, prawe dziecko oraz rodzica danego wierzchołka.
 */
public class Node<T extends Comparable<T>> {

    T key;
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    
    /**
     * Funkcja przypisująca wartość do danego węzła.
     * 
     * @param key Wartość węzła.
     */
    public Node (T key) {
        this.key = key;
    }

    /**
     * Funkcja kopiująca wartość podanego węzła do aktualnego węzła.
     * 
     * @param x Węzeł drzewa.
     */
    public void copy (Node<T> x) {
        x.key = this.key;
    }

    /**
     * Funkcja sprawdzająca czy podana wartość jest mniejsza od wartości aktualnego węzła.
     * 
     * @param k Wartość węzła.
     */
    public boolean lessThan(T k){
        return this.key.compareTo(k) < 0;
    }

    /**
     * Funkcja sprawdzająca czy podana wartość jest większa od wartości aktualnego węzła.
     * 
     * @param k Wartość węzła.
     */
    public boolean greaterThan(T k){
        return this.key.compareTo(k) > 0;
    }

    /**
     * Funkcja sprawdzająca czy podana wartość jest taka sama jak wartość aktualnego węzła.
     * 
     * @param k Wartość węzła.
     */
    public boolean equalsTo(T k){
        return this.key.compareTo(k) == 0;
    }

    /**
     * Funkcja rysująca drzewo. Użyto tego algorytmu:
     * https://stackoverflow.com/a/19484210
     * 
     * @param out OutputStreamWriter
     */
    public void printTree(OutputStreamWriter out) throws IOException {
        if (right != null) {
            right.printTree(out, true, "");
        }
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, "");
        }
    }
    
    private void printNodeValue(OutputStreamWriter out) throws IOException {
        if (key == null) {
            out.write("<null>");
        } else {
            out.write(key.toString());
        }
        out.write('\n');
    }
    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
        if (right != null) {
            right.printTree(out, true, indent + (isRight ? "        " : " |      "));
        }
        out.write(indent);
        if (isRight) {
            out.write(" /");
        } else {
            out.write(" \\");
        }
        out.write("----- ");
        printNodeValue(out);
        if (left != null) {
            left.printTree(out, false, indent + (isRight ? " |      " : "        "));
        }
    }
}
