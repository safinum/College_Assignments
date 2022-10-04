## Lista na laboratoria nr 7 (na ocenę)

## W języku Java i C++ stwórz implementację drzewa binarnego. 
Należy użyć typów ge-
nerycznych w obu językach. Programy powinny tworzyć i obsługiwać drzewa przynajmniej
dla typów: Integer, Double oraz String. Nie zapomnij o udokumentowaniu kodu i wyge-
nerowaniu dokumentacji.
Szczegółowy opis drzew binarnych można znaleźć w rozdziale 13 książki: T.H. Cormen, Ch.E.
Leiserson, R.L. Rivest, „Wprowadzenie do algorytmów”, WNT, Warszawa 1997 (ISBN 83-
204-2144-6). Wycinek z powyższej książki można znaleźć tutaj.
## Funkcjonalność na ocenę 3.0:
• Oba programy powinny implementować następujące operacje na drzewie binarnym: prze-
szukiwanie (search), wstawianie (insert), usuwanie (delete) elementów oraz wyświe-
tlenie (draw) całego drzewa. Wyświetlenie drzewa może być zwykłym wypisaniem wierz-
chołków na konsoli przy użyciu np. metody inOrder lub innej (zobacz tutaj), ale w taki
sposób, żeby dało się jednoznacznie odczytać hierarchię w drzewie.
## Funkcjonalność na ocenę 4.0:
Program w Java ma działać w technologii klient-serwer. Zakładamy, że z serwerem może
się kontaktować wielu klientów (przykład na wykładzie). Operacje na drzewie powinny być
wykonywane na serwerze. Klient powinien być aplikacją, która umożliwia wysyłanie odpo-
wiednich zadań do serwera i wyświetlanie otrzymanej odpowiedzi z serwera w następujący
sposób:
• Wysłanie polecenia search powinno spowodować wyszukanie drzewa na serwerze oraz
zwrócić na kliencie komunikat o znalezieniu bądź braku szukanego elementu.
• Wysłanie polecenia insert i delete powinno spowodować wykonanie operacji na ser-
werze a następnie wyświetlenie na kliencie drzewa po modyfikacji.
• Wysłanie polecenia draw powinno spowodować wyświetlenie na kliencie drzewa o aktu-
alnych danych.
• Dodatkowo na kliencie powinno być możliwe ustawienie typu drzewa, na którym bę-
dziemy pracować (Integer, Double lub String).
