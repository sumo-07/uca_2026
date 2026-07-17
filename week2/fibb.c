#include <stdio.h>

int brute_force(int n) {
    int fibs[100];
    int count = 0;
    int a = 1, b = 1;
    // generating fibb no.s
    while (a <= n) {
        fibs[count++] = a;
        int next = a + b;
        a = b;
        b = next;
    }

    // actual work
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        if (i % 2 == 0) {
            for (int j = 0; j < count; j++) {
                if (fibs[j] == i) {
                    sum += i;
                    break;
                }
            }
        }
    }
    return sum;
}

int optimized(int n) {
    int sum = 0;
    int a = 1, b = 2;

    while (a <= n) {
        if (a % 2 == 0)
            sum += a;

        int next = a + b;
        a = b;
        b = next;
    }
    return sum;
}

int main() {
    int n;
    if (scanf("%d", &n) != 1) {
        return 1;
    }
    int brute_res = brute_force(n);
    int opt_res = optimized(n);
    printf("%d\n", brute_res);
    printf("%d\n", opt_res);
    return 0;
}
