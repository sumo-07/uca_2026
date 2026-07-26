#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char** removeComments(char** source, int sourceSize, int* returnSize) {
    int resCapacity = 10;
    int resSize = 0;
    char** result = (char**)malloc(sizeof(char*) * resCapacity);

    bool inBlock = false; // are we in block comment?
    
    int currCapacity = 100;
    char* currLine = (char*)malloc(sizeof(char) * currCapacity);
    int currLen = 0;
    currLine[0] = '\0';

    for (int lineIdx = 0; lineIdx < sourceSize; lineIdx++) {
        char* line = source[lineIdx];
        if (!inBlock) {
            currLen = 0;
            currLine[0] = '\0';
        }

        int lineLength = strlen(line);
        for (int i = 0; i < lineLength; i++) {
            // Normal Mode (no multiline comment)
            if (!inBlock) {
                // check for line comment
                if (i + 1 < lineLength && line[i] == '/' && line[i + 1] == '/') {
                    break; // ignore the full line
                }
                // check for /* ---> block comment
                else if (i + 1 < lineLength && line[i] == '/' && line[i + 1] == '*') {
                    inBlock = true;
                    i++; // skip the '*'
                }
                // Normal Character toh rkh lenge
                else {
                    if (currLen + 1 >= currCapacity) {
                        currCapacity *= 2;
                        currLine = (char*)realloc(currLine, sizeof(char) * currCapacity);
                    }
                    currLine[currLen++] = line[i];
                    currLine[currLen] = '\0';
                }
            }
            // block comment mode
            else {
                if (i + 1 < lineLength && line[i] == '*' && line[i + 1] == '/') {
                    inBlock = false;
                    i++; // skip the '/'
                }
                // nhi toh comment ke andar hee hai toh ignore krdenge unn characters ko
            }
        }

        // Add krdo line ko if we are not in the block comment and the line is not empty "" (line mei hai kuch code)
        if (!inBlock && currLen > 0) {
            if (resSize >= resCapacity) {
                resCapacity *= 2;
                result = (char**)realloc(result, sizeof(char*) * resCapacity);
            }
            char* lineDup = (char*)malloc((currLen + 1) * sizeof(char));
            strcpy(lineDup, currLine);
            result[resSize++] = lineDup;
        }
    }

    free(currLine);
    *returnSize = resSize;
    return result;
}

int main() {
    // Example 1
    char* source1[] = {
        "/*Test program */",
        "int main()",
        "{ ",
        "  // variable declaration ",
        "int a, b, c;",
        "/* This is a test",
        "   multiline  ",
        "   comment for ",
        "   testing */",
        "a = b + c;",
        "}"
    };
    int size1 = sizeof(source1) / sizeof(source1[0]);
    int returnSize1;
    char** result1 = removeComments(source1, size1, &returnSize1);

    printf("Example 1 Output:\n");
    for (int i = 0; i < returnSize1; i++) {
        printf("%s\n", result1[i]);
        free(result1[i]);
    }
    free(result1);

    printf("\n");

    // Example 2
    char* source2[] = {
        "a/*comment",
        "line",
        "more_comment*/b"
    };
    int size2 = sizeof(source2) / sizeof(source2[0]);
    int returnSize2;
    char** result2 = removeComments(source2, size2, &returnSize2);

    printf("Example 2 Output:\n");
    for (int i = 0; i < returnSize2; i++) {
        printf("%s\n", result2[i]);
        free(result2[i]);
    }
    free(result2);

    return 0;
}
