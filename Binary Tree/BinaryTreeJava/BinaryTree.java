import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Klasa przechowująca funkcje do wykonania na drzewie binarnym.
 */
public class BinaryTree<T extends Comparable<T>> {
    Node<T> root; 

    /** 
     * Funkcja wstawiająca podaną wartość do drzewa.
     * 
     * @param k Wartość do wstawienia.
     */
    public Node<T> insert (T k) {
        Node<T> y = null;
        Node<T> x = this.root;
        Node<T> z = new Node<T>(k);

        while (x != null) {
            y = x;
            if (z.lessThan(y.key)) {
                x = x.left;
            }
            else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            this.root = z;
        }
        else {
            if (z.lessThan(y.key)) {
                y.left = z;
            }
            else {
                y.right = z;
            }
        }
        return z;
    }

    /**
     * Funkcja zwracająca najmniejszy węzeł w drzewie.
     * 
     * @param x Węzeł drzewa.
     */
    public Node<T> minimum (Node<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Funkcja zwracająca następnik danego węzła drzewa.
     * 
     * @param x Węzeł drzewa.
     */
    public Node<T> successor (Node<T> x) {
        if (x.right != null) {
            return minimum(x.right);
        }
        Node<T> y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * Funkcja usuwająca dany węzeł z drzewa.
     * 
     * @param z Węzeł drzewa.
     */
    public Node<T> delete (Node<T> z) {
        Node<T> y;
        if (z.left == null || z.right == null) {
            y = z;
        }
        else {
            y = successor(z);
        }

        Node<T> x;
        if (y.left != null) {
            x = y.left;
        }
        else {
            x = y.right;
        }

        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            this.root = x;
        }
        else {
            if (y == y.parent.left) {
                y.parent.left = x;
            }
            else {
                y.parent.right = x;
            }
        }

        if (y != z) {
            y.copy(z);
        }
        return y;
    }

    /** 
     * Funkcja wyszukująca podaną wartość w drzewie, zaczynając od korzenia.
     * 
     * @param k Wartość do wyszukania.
     */
    public Node<T> search(T k){
        return search(this.root, k);
    }

    /**
     * Funkcja wyszukująca podaną wartość w drzewie.
     * 
     * @param x Węzeł drzewa.
     * @param k Wartość do wyszukania.
     */
    public Node<T> search (Node<T> x, T k) { //x = root
        if (x == null || x.equalsTo(k)) {
            return x;
        }
        if (x.greaterThan(k)) {
            return search(x.left, k);
        }
        else {
            return search(x.right, k);
        }
    }

    private void inOrder(Node<T> x, ArrayList<Node<T>> nodes){
        if(x == null) return;   
        inOrder(x.left, nodes);
        nodes.add(x);
        inOrder(x.right, nodes);
    }

    /**
     * Funkcja zwracająca węzły drzewa uporządkowane metodą inOrder.
     */
    public ArrayList<Node<T>> inOrder () {
        ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();
        inOrder(this.root, nodes);
        return nodes;
    }

    /**
     * Funkcja rysująca drzewo.
     * 
     * @param out OutputStreamWriter, potrzebny do narysowania drzewa.
     */
    public void draw (OutputStreamWriter out) {
        if(this.root == null){
            return;
        }        
        try {
            this.root.printTree(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
