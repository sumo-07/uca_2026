#include <stdio.h>

int find_substring(const char *haystack, const char *needle) {
    if (haystack == NULL || needle == NULL) {
        return -1;
    }
    if (needle[0] == '\0') {
        return 0;
    }
    int i = 0;
    while (haystack[i] != '\0') {
        int j = 0;
        while (haystack[i + j] != '\0' && needle[j] != '\0' && haystack[i + j] == needle[j]) {
            j++;
        }

        //needle matched
        if (needle[j] == '\0') {
            return i;
        }
        i++;
    }
    return -1;
}

int main() {
    const char *haystack = "Embedded Systems";
    const char *needle = "bed";
    printf("Index: %d\n", find_substring(haystack, needle));
    return 0;
}
