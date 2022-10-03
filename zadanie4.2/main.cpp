#include <iostream>
#include <vector>
#include <stdlib.h>

class WierszTrojkataPascala
{

private:
    std::vector<int> ostatniWiersz;

public:
    WierszTrojkataPascala(int n)
    {
      if (n < 1)
      {
        return;
      }
     
      ostatniWiersz.push_back(1);
     
      for (int i = 0; i < n; i++)
      {
      std::vector<int> nowyWiersz;
      nowyWiersz.push_back(1);
      for (int k = 0; k < static_cast<int>(ostatniWiersz.size()) - 1; k++)
      {
      nowyWiersz.push_back(ostatniWiersz.at(k) + ostatniWiersz.at(k + 1));
      }
      nowyWiersz.push_back(1);
      ostatniWiersz = nowyWiersz;
      }

    }

    /* void print()
    {
        for (unsigned int i = 0; i < lista.size(); i++)
        {
            std::cout << lista[i] << ", ";
        }
        std::cout << std::endl;
    } */

    int wspolczynnik(int m)
    {
        if (m < 0 || m > static_cast<int>(ostatniWiersz.size()) - 1)
        {
            return -1;
        }
        return ostatniWiersz[static_cast<unsigned>(m)];
    }
   
   

   /* ~LiczbyPierwsze()
    {
        lista.~vector();
    }*/
  
};


int main(int argc, char **argv)

{
    int n;

    try
    {
        n = std::stoi(argv[1]);
    }
    catch (const std::exception &)
    {
        std::cout << argv[1] << " nie jest liczba calkowita" << std::endl;
        return 1;
  
    }

    if (n < 0 || n > 1000000)
    {
        std::cout << n << " nie jest liczba z zakresu [0; 1000000)" << std::endl;
        return 0;
    }

    WierszTrojkataPascala obiekt(n);
    //obiekt.print();
    //std::cout << n << std::endl;

    for (int i = 2; i < argc; i++)
    {
        int m;

        try
        {
            m = std::stoi(argv[i]);
        }
        catch (const std::exception &)
        {
            std::cout << argv[i] << " - nie jest liczba calkowita" << std::endl;
       
            continue;
        }

        int mLiczba = obiekt.wspolczynnik(m);

        if (mLiczba == -1)
        {
            std::cout << m << " - liczba spoza zakresu" << std::endl;
        }
        else
        {
            std::cout << m << " - " << mLiczba << std::endl;
        }
    }

    return 0;
}

