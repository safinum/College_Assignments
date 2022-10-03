public abstract class Figury implements Obliczenia {

    public enum JedenParametr {
        KOLO, KWADRAT, PIECIOKAT, SZESCIOKAT;
    }

    public enum DwaParametry {
        PROSTOKAT, ROMB;
    }

    public static Figura nowaFigura (JedenParametr jParam, double parametr)
    {
        switch (jParam)
        {
            case KOLO:
                return new Kolo(parametr);
            case KWADRAT:
                return new Kwadrat(parametr);
            case PIECIOKAT:
                return new Pieciokat(parametr);
            case SZESCIOKAT:
                return new Szesciokat(parametr);
        }
        return null;
    }

    public static Figura nowaFigura (DwaParametry dParam, double paramOne, double paramTwo)
    {
        switch (dParam)
        {
            case PROSTOKAT:
                return new Prostokat(paramOne, paramTwo);
            case ROMB:
                return new Romb(paramOne, paramTwo);
        }
        return null;
    }
    

    public static double ObliczPole (JedenParametr jParam, double param){
        switch (jParam)
        {
            case KOLO:
                return Math.PI * param * param;
            case KWADRAT:
                return param * param;
            case PIECIOKAT:
                return param * param * Math.sqrt(25 + 10 * Math.sqrt(5)) / 4;
            case SZESCIOKAT:
                return 3 * param * param * Math.sqrt(3) / 2;
        }
        return -1;
    }

    public static double ObliczPole (DwaParametry dParam, double paramOne, double paramTwo) {
        switch (dParam) {
            case PROSTOKAT:
                return paramOne * paramTwo;
            case ROMB:
                return paramOne * paramOne * Math.sin(Math.toRadians(paramTwo));
        }
        return -1;
    }

    public static double ObliczObwod (JedenParametr jParam, double param){
        switch (jParam)
        {
            case KOLO:
                return 2 * Math.PI * param;
            case KWADRAT:
                return 4 * param;
            case PIECIOKAT:
                return 5 * param;
        case SZESCIOKAT:
                return 6 * param;
        }
        return -1;
    }

    public static double ObliczObwod (DwaParametry dParam, double paramOne, double paramTwo) {
        switch (dParam) {
            case PROSTOKAT:
                return 2 * (paramOne + paramTwo);
            case ROMB:
                return 4 * paramOne;
        }
        return -1;
    }

}
