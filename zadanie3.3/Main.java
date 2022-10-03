import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String figury = null;
        ArrayList<Figura> tablicaFigur = new ArrayList<Figura>();

        try {
			figury = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Nie podano argumentow");
			System.exit(0);
		}

        int nrParametru = 1;

        for (int i = 0; i < figury.length(); i++) {
            String figura = figury.toLowerCase().substring(i, i+1);

            switch (figura) {
                case "o":
                    double promien = 0;

                    try {
						promien = Double.parseDouble(args[nrParametru]);
						nrParametru++;
					}
					catch (ArrayIndexOutOfBoundsException e) { Wyjatki.zlaIloscArgumentow(); }
					catch (NumberFormatException e) { Wyjatki.niepoprawnyArgument(); }

                    tablicaFigur.add(Figury.nowaFigura(Figury.JedenParametr.KOLO, promien));

                    break;
                case "p":
                    double bok = 0;
                    
                    try {
						bok = Double.parseDouble(args[nrParametru]);
						nrParametru++;
					}
					catch (ArrayIndexOutOfBoundsException e) { Wyjatki.zlaIloscArgumentow(); }
					catch (NumberFormatException e) { Wyjatki.niepoprawnyArgument(); }

                    tablicaFigur.add(Figury.nowaFigura(Figury.JedenParametr.PIECIOKAT, bok));

                    break;
                case "s":
                    double bok1 = 0;

                    try {
						bok1 = Double.parseDouble(args[nrParametru]);
						nrParametru++;
					}
					catch (ArrayIndexOutOfBoundsException e) { Wyjatki.zlaIloscArgumentow(); }
					catch (NumberFormatException e) { Wyjatki.niepoprawnyArgument(); }

                    tablicaFigur.add(Figury.nowaFigura(Figury.JedenParametr.SZESCIOKAT, bok1));

                    break;
                case "c":
                    double[] boki = new double[4];
                    double kat = 0;
            
                    try {
						for(int j = 0; j < 4; j++) {
							boki[j] = Double.parseDouble(args[nrParametru]);
							nrParametru++;
						}
						
						kat = Double.parseDouble(args[nrParametru]);
						nrParametru++;
					}
					catch (ArrayIndexOutOfBoundsException e) { Wyjatki.zlaIloscArgumentow(); }
					catch (NumberFormatException e) { Wyjatki.niepoprawnyArgument();; }

                    Arrays.sort(boki);
                    if (boki[0] == boki[1] && boki[2] == boki[3]) {
                        if (boki[0] == boki[3]) {
                            if (kat == 90) {
                                tablicaFigur.add(Figury.nowaFigura(Figury.JedenParametr.KWADRAT, boki[0]));
                            }
                            else {
                                tablicaFigur.add(Figury.nowaFigura(Figury.DwaParametry.ROMB, boki[0], kat));
                            }
                        }
                        else if (kat == 90) {
                            tablicaFigur.add(Figury.nowaFigura(Figury.DwaParametry.PROSTOKAT, boki[0], boki[3]));
                        }
                        else {
                            Wyjatki.niepoprawnyArgument();
                        }
                    }
                    else {
                        Wyjatki.niepoprawnyArgument();
                    }

                    break;
                default:
                Wyjatki.niepoprawnyArgument();
    
            }

        }

        for(Figura f : tablicaFigur) {
			f.pokazInfo();
		}

    }
}