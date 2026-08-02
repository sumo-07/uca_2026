#include <stdio.h>

int isPower2(int x) {

    // Check x is non-zero
    int nonZero = !!x;

    // Check x is positive
    int positive = !(x >> 31);

    // Check x & (x-1) == 0
    int singleBit = !(x & (x + ~0));

    return nonZero & positive & singleBit;
}

int main() {
    printf("%d\n", isPower2(-2));
    printf("%d\n", isPower2(5));
    printf("%d\n", isPower2(8));
    printf("%d\n", isPower2(0));
    return 0;
}