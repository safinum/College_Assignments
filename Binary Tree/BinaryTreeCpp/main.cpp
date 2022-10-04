#include <iostream>
#include <vector>

using namespace std;

template <class T>
class Node
{
public:
    T key;
    Node<T> *left = NULL;
    Node<T> *right = NULL;
    Node<T> *parent = NULL;

    Node(T key)
    {
        this->key = key;
    }

    void copyNode(Node<T> *x)
    {
        x->key = this->key;
    }

    bool lessThan(T k)
    {
        return this->key < k;
    }

    bool greaterThan(T k)
    {
        return this->key > k;
    }

    bool equalsTo(T k)
    {
        return this->key == k;
    }

    void printTree() 
    {
        if (this->right != NULL)
        {
            this->right->printTree(true, "");
        }
        printNodeValue();
        if (this->left != NULL)
        {
            this->left->printTree(false, "");
        }
    }

private:
    void printNodeValue()
    {

        cout << this->key << endl;

    }

    void printTree(bool isRight, string indent)
    {
        if (this->right != NULL)
        {
            this->right->printTree(true, indent + (isRight ? "        " : " |      "));
        }
        cout << indent;
        if (isRight)
        {
            cout << " /";
        }
        else
        {
            cout << " \\";
        }
        cout << "----- ";
        printNodeValue();
        if (this->left != NULL)
        {
            this->left->printTree(false, indent + (isRight ? " |      " : "        "));
        }
    }
};

template <class T>
class BinaryTree
{
public:
    BinaryTree() {}

    Node<T> *root = NULL;

    Node<T> *insert(T k)
    {
        Node<T> *y = NULL;
        Node<T> *x = this->root;
        Node<T> *z = new Node<T>(k);

        while (x != NULL)
        {
            y = x;
            if (z->lessThan(y->key))
            {
                x = x->left;
            }
            else
            {
                x = x->right;
            }
        }
        z->parent = y;
        if (y == NULL)
        {
            this->root = z;
        }
        else
        {
            if (z->lessThan(y->key))
            {
                y->left = z;
            }
            else
            {
                y->right = z;
            }
        }
        return z;
    }

    Node<T> *minimum(Node<T> *x)
    {
        while (x->left != NULL)
        {
            x = x->left;
        }
        return x;
    }

    Node<T> *successor(Node<T> *x)
    {
        if (x->right != NULL)
        {
            return minimum(x->right);
        }
        Node<T> *y = x->parent;
        while (y != NULL && x == y->right)
        {
            x = y;
            y = y->parent;
        }
        return y;
    }

    Node<T> *deleteNode(Node<T> *z)
    {
        Node<T> *y;
        if (z->left == NULL || z->right == NULL)
        {
            y = z;
        }
        else
        {
            y = successor(z);
        }

        Node<T> *x;
        if (y->left != NULL)
        {
            x = y->left;
        }
        else
        {
            x = y->right;
        }

        if (x != NULL)
        {
            x->parent = y->parent;
        }

        if (y->parent == NULL)
        {
            this->root = x;
        }
        else
        {
            if (y == y->parent->left)
            {
                y->parent->left = x;
            }
            else
            {
                y->parent->right = x;
            }
        }

        if (y != z)
        {
            y->copyNode(z);
        }
        return y;
    }

    Node<T> *search(T k)
    {
        return search(this->root, k);
    }

    Node<T> *search(Node<T> *x, T k)
    {
        if (x == NULL || x->equalsTo(k))
        {
            return x;
        }
        if (x->greaterThan(k))
        {
            return search(x->left, k);
        }
        else
        {
            return search(x->right, k);
        }
    }

    void draw()
    {
        if (this->root == NULL)
        {
            return;
        }
        this->root->printTree();
    }

    void DestroyRecursive(Node<T> *node)
    {
        if (node != NULL)
        {
            DestroyRecursive(node->left);
            DestroyRecursive(node->right);
            delete node;
        }
    }
    
    ~BinaryTree()
    {
        DestroyRecursive(this->root);
    }
};

int main()
{
    BinaryTree<int> drzewo2;
    BinaryTree<double> drzewo1;
    BinaryTree<string> drzewo0;

    int opcja, typ;

    cout << "Podaj typ drzewa (0 - String, 1 - Double, 2 - Int): ";
    cin >> typ;

    if (!cin)
    {
        cin.clear();
        cin.sync();
        cout << "Nieprawidłowy typ" << endl;
        return 0;
    }
    else if (typ < 0 || typ > 2) 
    {
        cout << "Nieprawidłowy typ" << endl;
        return 0;
    }

    cout << "Opcje do wybrania:" << endl;
    cout << "1. Dodaj element" << endl;
    cout << "2. Usuń element" << endl;
    cout << "3. Wyszukaj element" << endl;
    cout << "4. Narysuj drzewo" << endl;
    cout << "5. Zakończ program" << endl;

    while (true)
    {
        cout << "Podaj opcję: ";

        cin >> opcja; 

        if (!cin)
        {
            cin.clear();
            cin.sync();
            cout << "Nieprawidłowa opcja" << endl;
            return 0;
        }
        else if (opcja < 1 || opcja > 5) 
        {
            cout << "Nieprawidłowa opcja" << endl; 
            return 0;
        }

        string k0;
        double k1;
        int k2;

        switch (opcja)
        {
        case 1:
            cout << "Podaj wartość do dodania: ";
            if (typ == 0)
            {
                cin >> k0;
                drzewo0.insert(k0);
            }
            else if (typ == 1)
            {
                cin >> k1;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                drzewo1.insert(k1);
            }
            else
            {
                cin >> k2;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                drzewo2.insert(k2);
            }
            cout << "dodany" << endl;
            break;

        case 2: 
            cout << "Podaj wartość do usunięcia: ";
            if (typ == 0)
            {
                cin >> k0;
                Node<string> *x = drzewo0.search(k0);
                if (x != NULL)
                {
                    Node<string> *y = drzewo0.deleteNode(x);
                    delete y;
                    cout << "usuniety" << endl;
                }
                else
                {
                    cout << "nie ma" << endl;
                }
            }
            else if (typ == 1)
            {
                cin >> k1;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                Node<double> *x = drzewo1.search(k1);
                if (x != NULL)
                {
                    Node<double> *y = drzewo1.deleteNode(x);
                    delete y;
                    cout << "usuniety" << endl;
                }
                else
                {
                    cout << "nie ma" << endl;
                }
            }
            else
            {
                cin >> k2;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                Node<int> *x = drzewo2.search(k2);
                if (x != NULL)
                {
                    Node<int> *y = drzewo2.deleteNode(x);
                    delete y;
                    cout << "usuniety" << endl;
                }
                else
                {
                    cout << "nie ma" << endl;
                }
            }
            break;

        case 3:
            cout << "Podaj wartość do wyszukania: ";

            if (typ == 0)
            {
                cin >> k0;
                Node<string> *x = drzewo0.search(k0);
                if (x != NULL)
                {
                    cout << "Element istnieje" << endl;
                }
                else
                {
                    cout << "Brak elementu" << endl;
                }
            }
            else if (typ == 1)
            {
                cin >> k1;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                Node<double> *x = drzewo1.search(k1);
                if (x != NULL)
                {
                    cout << "Element istnieje" << endl;
                }
                else
                {
                    cout << "Brak elementu" << endl;
                }
            }
            else
            {
                cin >> k2;
                if (!cin)
                {
                    cin.clear();
                    cin.sync();
                    cout << "Nieprawidłowa wartość" << endl;
                    break;
                }
                Node<int> *x = drzewo2.search(k2);
                if (x != NULL)
                {
                    cout << "Element istnieje" << endl;
                }
                else
                {
                    cout << "Brak elementu" << endl;
                }
            }

            break;

        case 4:
            if (typ == 0)
            {
                drzewo0.draw();
            }
            else if (typ == 1)
            {
                drzewo1.draw();
            }
            else
            {
                drzewo2.draw();
            }
            break;

        case 5:
            cout << "Koniec programu" << endl;
            return 0;
        }
    }
}