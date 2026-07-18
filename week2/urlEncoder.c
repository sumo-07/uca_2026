#include <stdio.h>

void url_encode(char *str, int true_length) {
    int space_count = 0;
    for (int i = 0; i < true_length; i++) {
        if (str[i] == ' ') {
            space_count++;
        }
    }
    // %20= 3 characters, ' ' is 1 character ==> total needed 2 extra spaces
    int new_index = true_length + space_count * 2;
    str[new_index] = '\0';
    for (int i = true_length - 1; i >= 0; i--) {
        if (str[i] == ' ') {
            str[new_index - 1] = '0';
            str[new_index - 2] = '2';
            str[new_index - 3] = '%';
            new_index -= 3;
        } else {
            str[new_index - 1] = str[i];
            new_index--;
        }
    }
}

int main(void) {
    char str[100] = "Hello World  ";
    int true_length = 11;
    url_encode(str, true_length);
    printf("%s\n", str);
    return 0;
}
