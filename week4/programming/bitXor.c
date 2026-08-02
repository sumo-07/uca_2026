#include <stdio.h>

int bitXor(int x, int y) {
    int a = ~x & y; // to get the middle T
    int b = x & ~y; // to get the middle T
    return ~(~a & ~b); // using demorgan
}

int main() {
    printf("%d", bitXor(4, 5));
    return 0;
}