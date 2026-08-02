#include <stdio.h>

int conditional(int x, int y, int z) {
    int mask= ~!!x + 1;

    int chooseY= mask & y;
    int chooseZ= ~mask & z;
    
    return chooseY | chooseZ;
}

int main() {
    printf("%d\n", conditional(1, 2, 3));
    printf("%d\n", conditional(0, 20, 30));
    return 0;
}