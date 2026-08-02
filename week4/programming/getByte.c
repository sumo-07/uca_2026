#include <stdio.h>

int getByte(int x, int n) {
    int shift= n << 3;   // by how many steps to left shift
    int shiftedNumber= x >> shift;   // shift the number to the right
    return shiftedNumber & 0xFF;   // mask the number to get the byte
}

int main() {
    printf("0x%x", getByte(0x12345678, 1));
    return 0;
}