## Lista nr 6(na ocenę)

Zadanie 1 Napisz korzystając z wątków języka Java następującą symulację:
## Funkcjonalność na ocenę 3.0:
• Plansza do symulacji jest prostokątem n na m pól.
• Rozmiar planszy (m i n), szybkość działania (k) i prawdopodobieństwo zmiany koloru
(p) powinny być podane jako parametry. Początkowe kolory pól powinny być losowe.
• Każde pole jest wątkiem który co pewien czas (opóźnienie jest równe losowo wybranej
liczbie milisekund z przedziału [0.5k, 1.5k]) wykonuje nastepujące czynności:
– z prawdopodobieństwem p zmienia swój kolor na losowy;
– z prawdopodobieństwem 1 − p zostawia kolor taki jaki był.
## Funkcjonalność na ocenę 4.0:
• Każde pole jest wątkiem który co pewien czas (opóźnienie jest równe losowo wybranej
liczbie milisekund z przedziału [0.5k, 1.5k]) wykonuje nastepujące czynności:
– z prawdopodobieństwem p zmienia swój kolor na losowy;
– z prawdopodobieństwem 1 − p sprawdza kolory swoich czterech sąsiadów (planszę
traktujemy jako dwuwymiarowy torus) i przyjmuje jako kolor średnią z nich.
• Należy zadbać o synchronizację planszy i pól, aby nie dochodziło do konfliktów (czytania
koloru sąsiada w trakcie jego zmiany).
## Funkcjonalność na ocenę 5.0:
• Należy rozszerzyć wykonaną funkcjonalność o możliwość zawieszenia oraz ponownego
wystartowania wątka (np. poprzez kliknięcie na pole). Wówczas pole zatrzymanego
wątka nie bierze udziału w obliczaniu koloru sąsiadów.
