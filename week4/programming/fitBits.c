#include <stdio.h>

int fitBits(int x, int n) {
    int shift= 32 + (~n + 1); // ~n= ~(n + 1)

    int leftShift= x << shift;
    
    int restore= leftShift >> shift;

    int compare= restore ^ x; // comparing---> agr 0 aya means same result hai  
    return !compare; // 0---> 1, 0 nhi hai toh 1
}

int main() {
    printf("%d\n", fitBits(5,3));
    printf("%d\n", fitBits(-4,3));
}

