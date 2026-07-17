#include <stdio.h>
#include <string.h>

void reverse_string(char *str) {
    if (str == NULL)
        return;

    int start = 0;
    int end = strlen(str) - 1;

    while (start < end) {
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;
        start++;
        end--;
    }
}

int main() {
    char str[] = "Data Structures";
    reverse_string(str);
    printf("%s\n", str);
    return 0;
}
