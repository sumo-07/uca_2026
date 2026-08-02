#include <stdio.h>

int sign(int x) {
    int negative= x >> 31; // MSB= 1
    int nonZero= !!x;   // x= 130 ---> !x --> 0 ---> 0! ---> 1
    return negative | nonZero;
}

int main() {
    printf("%d\n", sign(130));
    printf("%d\n", sign(-23));
    printf("%d\n", sign(0));
}