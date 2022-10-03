import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Klasa wyświetlająca menu i umożliwiająca wykonanie operacji na drzewie binarnym.
 */
public class Main {

    /**
     * Metoda główna programu.
     * Wyświetla menu, umożliwia wstawianie elementów do drzewa, usuwanie ich, wyszukiwanie oraz rysowanie drzewa.
     */
    public static void main(String[] args) throws IOException {

        BinaryTree<String> drzewo0 = new BinaryTree<String>();
        BinaryTree<Double> drzewo1 = new BinaryTree<Double>();
        BinaryTree<Integer> drzewo2 = new BinaryTree<Integer>();
        
        Scanner scanner = new Scanner(System.in);

        String opcjaMenu;
        String typDrzewa;

        OutputStreamWriter out = new OutputStreamWriter(System.out);
        System.out.print("Podaj typ drzewa (0 - String, 1 - Double, 2 - Int): ");

        typDrzewa = scanner.next();
        int typ = 0;

        try {
            typ = Integer.parseInt(typDrzewa);
            if (typ < 0 || typ > 2) {
                System.out.println("Nieprawidłowy zakres");
                scanner.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy typ");
            scanner.close();
            return;
        }
        
        System.out.println("Opcje do wybrania");
        System.out.println("1. Dodaj element");
        System.out.println("2. Usuń element");
        System.out.println("3. Wyszukaj element");
        System.out.println("4. Narysuj drzewo");
        System.out.println("5. Zakończ program");

        while (true) {
            System.out.print("Podaj opcje: ");
            opcjaMenu = scanner.next();
            int opcja = 0;

            try {
                opcja = Integer.parseInt(opcjaMenu);
                if (opcja < 1 || opcja > 5) {
                    System.out.println("Nieprawidłowy numer opcji");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa opcja");
            }

            String k0;
            double k1;
            int k2;

            switch (opcja) { 
                case 1: // insert
                    System.out.print("Podaj wartosc do dodania: ");
                    if(typ == 0) { //ok
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        drzewo0.insert(k0);
                    } else if(typ == 1){ //ok
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        try {
                            k1 = Double.parseDouble(k0);
                            drzewo1.insert(k1);
                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Double");
                            break;
                        }
                    } else {
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        try {
                            k2 = Integer.parseInt(k0);
                            drzewo2.insert(k2);
                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Integer");
                            break;
                        }
                    }
                    System.out.println("Element dodany");
                    break;

                case 2: // delete
                    System.out.print("Podaj wartosc do usuniecia: ");

                    if(typ == 0) {
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        Node<String> x = drzewo0.search(k0);
                        if (x != null) {
                            drzewo0.delete(x);
                            //Node<String> y = drzewo0.delete(x);
                            //y = null;
                            System.out.println("Element usunięty");
                        } else {
                            System.out.println("Brak elementu");
                        }
                    } else if(typ == 1){
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        try {
                            k1 = Double.parseDouble(k0);
                            Node<Double> x = drzewo1.search(k1);
                            if (x != null) {
                                drzewo1.delete(x);
                                //Node<Double> y = drzewo1.delete(x);
                                //y = null;
                                System.out.println("Element usunięty");
                            } else {
                                System.out.println("Brak elementu");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Double");
                            break;
                        }
                    } else {
                        scanner.nextLine();
                        k0 = scanner.nextLine();
                        try {
                            k2 = Integer.parseInt(k0);
                            Node<Integer> x = drzewo2.search(k2);
                            if (x != null) {
                                drzewo2.delete(x);
                                //Node<Integer> y = drzewo2.delete(x);
                                //y = null;
                                System.out.println("Element usunięty");
                            } else {
                                System.out.println("Brak elementu");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Integer");
                            break;
                        }
                    }
                    break;

                case 3: //search
                    System.out.print("Podaj wartość do wyszukania: ");

                    if (typ == 0) {
                        scanner.nextLine();
                        k0 = scanner.nextLine();

                        Node<String> x = drzewo0.search(k0);
                        if (x != null) {
                            System.out.println("Element istnieje");
                        } else {
                            System.out.println("Brak elementu");
                        }
                    }
                    else if (typ == 1) {
                        scanner.nextLine();
                        k0 = scanner.nextLine();

                        try {
                            k1 = Double.parseDouble(k0);
                            Node<Double> x = drzewo1.search(k1);
                            if (x != null) {
                                System.out.println("Element istnieje");
                            } else {
                                System.out.println("Brak elementu");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Double");
                            break;
                        }
                    }
                    else {
                        scanner.nextLine();
                        k0 = scanner.nextLine();

                        try {
                            k2 = Integer.parseInt(k0);
                            Node<Integer> x = drzewo2.search(k2);
                            if (x != null) {
                                System.out.println("Element istnieje");
                            } else {
                                System.out.println("Brak elementu");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Podaj wartość Integer");
                            break;
                        }
                    }
                    break;

                case 4: // print
                    
                if(typ == 0) {
                    drzewo0.draw(out);
                } else if(typ == 1){
                    drzewo1.draw(out);
                } else {
                    drzewo2.draw(out);
                }
                out.flush();
                    break;

                case 5: // end
                    System.out.println("Koniec programu");
                    scanner.close();
                    return;
            }
        }
    }
}
