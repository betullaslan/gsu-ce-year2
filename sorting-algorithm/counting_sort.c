#include <stdio.h>

// N est le nombre maximum d'elements des tableaux A, B, compte
// Note: la valeur maximale du plus grand element du tableau A est egal a 50 - 1 = 49
#define N 50 

void TriParComptage(int A[], int taille) 
{
    // B est le tableau a copier les elements tries
    int B[N];

    // On trouve le plus grand element du tableau A
    int maximum = A[0];
    for(int i = 1; i < taille; i++){
        if(A[i] > maximum){
            maximum = A[i];
        }
    }

    // compte est le tableau qui contient le nombre de chaque element
    int compte[N];

    // On initialise le tableau compte avec tous les zeros.
    for(int i = 0; i <= maximum; i++){
        compte[i] = 0;
    }

    // On stocke le nombre de chaque element
    for(int i = 0; i < taille; i++){
        compte[A[i]]++;
    }

    // On stocke le nombre cumule du tableau
    for(int i = 1; i <= maximum; i++){
        compte[i] = compte[i - 1] + compte[i];
    }

    // On trouve l'indice de chaque element du tableau A dans le tableau compte
    // et place les elements dans le tableau B
    for(int i = taille - 1; i >= 0; i--){
        B[compte[A[i]] - 1] = A[i];
        compte[A[i]]--;
    }

    // On copie les elements tries dans le tableau A
    for(int i = 0; i < taille; i++){
        A[i] = B[i];
    }
}

void ImprimerTableau(int tableau[], int taille) 
{
    for(int i = 0; i < taille; i++){
        printf("%d  ", tableau[i]);
    }

    printf("\n");
}

int main() 
{
    // A est le tableau a trier
    int A[] = {5,3,0,4,6,2,5,1,0,2,5,3,4};

    // taille est le nombre d'elements du tableau A
    int taille = sizeof(A) / sizeof(A[0]);

    printf("Le tableau A avant le tri par comptage: \nA: ");
    ImprimerTableau(A, taille);

    TriParComptage(A, taille);
    printf("Le tableau A apres le tri par comptage: \nA: ");
    ImprimerTableau(A, taille);

    return 0;
}