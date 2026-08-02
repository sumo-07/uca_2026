#include <stdio.h>

int bitAnd(int x, int y) {
    return ~(~x | ~y); // demorgan law
}

int main() {
    printf("%d", bitAnd(6, 5));
    return 0;
}