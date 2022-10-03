#define _USE_MATH_DEFINES
#include <stdio.h>
#include <vector>
#include <stdlib.h>
#include <math.h>
#include <cstring>
#include <iostream>
#include <algorithm>
#include <memory>

class Figura
{
public:
    virtual ~Figura(){};

    virtual double ObliczPole() = 0;
    virtual double ObliczObwod() = 0;
};

class Czworokat : public Figura
{
protected:
    double bok1;
    double bok2;
    double bok3;
    double bok4;

    virtual double ObliczObwod()
    {
        return bok1 + bok2 + bok3 + bok4;
    }
};

class Kwadrat : public Czworokat
{
public:
    Kwadrat(double a)
    {
        bok1 = bok2 = bok3 = bok4 = a;
    }
    double ObliczPole()
    {
        return (bok1 * bok1);
    }
    // double ObliczObwod()
    // {
    //     return (4 * bok1);
    // }
};

class Prostokat : public Czworokat
{

public:
    Prostokat(double a, double b, double k)
    {
        bok1 = bok3 = a;
        bok2 = bok4 = b;
    }
    double ObliczPole()
    {
        return (bok1 * bok2);
    }
    // double ObliczObwod()
    // {
    //     return ((2 * bok1) + (2 * bok2));
    // }
};

class Romb : public Czworokat
{
private:
    double kat;

public:
    Romb(double a, double k)
    {
        bok1 = bok2 = bok3 = bok4 = a;
        kat = k;
    }
    double ObliczPole()

    {
        return (bok1 * bok1 * sin(kat * M_PI / 180));
    }
    // double ObliczObwod()
    // {
    //     return (4 * bok1);
    // }
};

class Kolo : public Figura
{
private:
    double promien;

public:
    Kolo(double r)
    {
        promien = r;
    }
    double ObliczPole()
    {
        return (promien * promien * M_PI);
    }
    double ObliczObwod()
    {
        return (2 * promien * M_PI);
    }
};

class Pieciokat : public Figura
{
private:
    double bok;

public:
    Pieciokat(double a)
    {
        bok = a;
    }
    double ObliczPole()
    {
        return (bok * bok * sqrt(25 + 10 * sqrt(5)) / 4);
    }
    double ObliczObwod()
    {
        return (5 * bok);
    }
};

class Szesciokat : public Figura
{
private:
    double bok;

public:
    Szesciokat(double a)
    {
        bok = a;
    }
    double ObliczPole()
    {
        return (bok * bok * 3 * sqrt(3) / 2);
    }
    double ObliczObwod()
    {
        return (bok * 6);
    }
};



void displayError(std::string error)
{
    std::cout << error << std::endl;
}

int main(int argc, char *argv[])
{
    char *figury = argv[1];
    int ileFigur = strlen(figury);

    std::vector<std::unique_ptr<Figura>> VectorFigur;

    int liczbaParametrow = 0;

    for (int i = 0; i < ileFigur; i++)
    {
        if (figury[i] == 'o' || figury[i] == 'p' || figury[i] == 's')
        {
            liczbaParametrow++;
        }
        else if (figury[i] == 'c')
        {
            liczbaParametrow += 5;
        }
        else
        {
            displayError("Niepoprawna figura");
            return 2;
        }
    }

    if (liczbaParametrow != (argc - 2))
    {
        displayError("Niepoprawna liczba argumentow");
        return 2;
    }

    std::vector<double> liczby;
    int iterator = 0;

    for (int i = 0; i < liczbaParametrow; i++)
    {
        try
        {
            double liczba = std::stod(argv[i + 2]);
            if (liczba <= 0)
            {
                displayError("Ujemna liczba");
                return 2;
            }
            liczby.push_back(liczba);
        }
        catch (std::exception &)
        {
            displayError("Nieprawidlowa liczba");
            return 2;
        }
    }

    for (int i = 0; i < ileFigur; i++)
    {
        double bok1 = liczby[iterator];
        if (figury[i] == 'o')
        {
            VectorFigur.emplace_back(new Kolo(bok1));
            iterator++;
        }
        else if (figury[i] == 'p')
        {
            VectorFigur.emplace_back(new Pieciokat(bok1));
            iterator++;
        }
        else if (figury[i] == 's')
        {
            VectorFigur.emplace_back(new Szesciokat(bok1));
            iterator++;
        }
        else if (figury[i] == 'c')
        {
            double bok2 = liczby[iterator + 1], bok3 = liczby[iterator + 2], bok4 = liczby[iterator + 3];
            double kat = liczby[iterator + 4];

            if (kat <= 0 || kat >= 180)
            {
                displayError("Nieprawidlowy kat");
                return 2;
            }
            if (bok4 == bok3 && bok3 == bok2 && bok2 == bok1)
            {
                if (kat == 90)
                {
                    VectorFigur.emplace_back(new Kwadrat(bok1));
                }
                else
                {
                    VectorFigur.emplace_back(new Romb(bok1, kat));
                }
            }

            else if (bok1 == bok3 && bok2 == bok4 && kat == 90)
            {
                VectorFigur.emplace_back(new Prostokat(bok1, bok2, kat));
            }

            else
            {
                displayError("Niepoprawne parametry figury");
                return 2;
            }
            iterator += 5;

        }
        else
        {
            displayError("Niepoprawny argument");
            return 2;
        }
    }
    for (int i = 0; i < ileFigur; i++)
    {
        std::cout << "Pole figury " << i + 1 << ": " << VectorFigur[i]->ObliczPole() << ", obwód: " << VectorFigur[i]->ObliczObwod() << std::endl;
    }

    return 0;
}
