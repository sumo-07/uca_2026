#include <stdio.h>

int logicalShift(int x, int n) {
    int shifted= x >> n; // arithmetic shift
    int mask= ~(((1 << 31) >> n) << 1); // mask with 0s in the left n bits and 1s elsewhere
    // return shifted; // 8 ke aage f ara tha
    return shifted & mask;
}

int main() {
    printf("0x%x", logicalShift(0x87654321, 4));
    return 0;
}