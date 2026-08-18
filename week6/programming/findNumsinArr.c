#include <stdio.h>

int findNumber(int arr[], int n) {
    int res= 0;

    for(int bit= 0; bit < 32; bit++) {
        int count= 0;
        for(int i= 0; i < n; i++) {
            if(arr[i] & (1 << bit)) {
                count++;
            }
        }

        if(count % 3 != 0) {
            res= res |(1 << bit);
        }
    }
    return res;
}

int main() {
    int arr[] = {
        1, 2, 3, 4, 1, 2, 4,
        1, 2, 3, 4, 3, 3
    };

    int n = sizeof(arr) / sizeof(arr[0]);

    printf("Number repeated 4 times: %d\n", findNumber(arr, n));

    return 0;
}