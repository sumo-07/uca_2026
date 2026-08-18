#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef int (*compareFunc)(const void *, const void *);

void swap(void *a, void *b, size_t size)
{

    void *temp = malloc(size);

    if (temp == NULL)
    {
        printf("Memory Allocation failed\n");
        exit(0);
    }

    memcpy(temp, a, size);
    memcpy(a, b, size);
    memcpy(b, temp, size);

    free(temp);
}

void heapify(void *arr, int n, int i, size_t size, compareFunc cmp)
{

    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    char *base = (char *)arr;

    if (left < n)
    {
        void *leftEle = base + left * size;
        void *largestEle = base + largest * size;

        if (cmp(leftEle, largestEle) > 0)
        {
            largest = left;
        }
    }

    if (right < n)
    {
        void *rightEle = base + right * size;
        void *largestEle = base + largest * size;

        if (cmp(rightEle, largestEle) > 0)
        {
            largest = right;
        }
    }

    if (largest != i)
    {
        void *current = base + i * size;
        void *largestEle = base + largest * size;

        swap(current, largestEle, size);

        heapify(arr, n, largest, size, cmp);
    }
}

void heapSort(void *arr, int n, size_t size, compareFunc cmp)
{

    for (int i = n / 2 - 1; i >= 0; i--)
    {
        heapify(arr, n, i, size, cmp);
    }

    for (int i = n - 1; i > 0; i--)
    {
        char *base = (char *)arr;

        swap(base, base + i * size, size);

        heapify(arr, i, 0, size, cmp);
    }
}

int compareInt(const void *a, const void *b)
{

    int x = *(const int *)a;
    int y = *(const int *)b;

    if (x > y)
        return 1;
    if (x < y)
        return -1;

    return 0;
}

int compareDouble(const void *a, const void *b)
{

    double x = *(const double *)a;
    double y = *(const double *)b;

    if (x > y)
        return 1;
    if (x < y)
        return -1;

    return 0;
}

int compareFloat(const void *a, const void *b)
{

    float x = *(const float *)a;
    float y = *(const float *)b;

    if (x > y)
        return 1;
    if (x < y)
        return -1;

    return 0;
}

int compareChar(const void *a, const void *b)
{

    char x = *(const char *)a;
    char y = *(const char *)b;

    if (x > y)
        return 1;
    if (x < y)
        return -1;

    return 0;
}

int compareString(const void *a, const void *b)
{

    const char *str1 = *(const char **)a;
    const char *str2 = *(const char **)b;

    return strcmp(str1, str2);
}

int main()
{

    int intArr[] = {40, 10, 30, 50, 20, 60};

    int n = sizeof(intArr) / sizeof(intArr[0]);

    heapSort(intArr, n, sizeof(int), compareInt);

    printf("Sorted integers:\n");

    for (int i = 0; i < n; i++)
    {
        printf("%d ", intArr[i]);
    }

    printf("\n\n");

    double doubleArr[] = {3.14, 1.1, 9.8, 2.5, 6.7};

    int d = sizeof(doubleArr) / sizeof(doubleArr[0]);

    heapSort(doubleArr, d, sizeof(double), compareDouble);

    printf("Sorted doubles:\n");

    for (int i = 0; i < d; i++)
    {
        printf("%.2lf ", doubleArr[i]);
    }

    printf("\n\n");

    float floatArr[] = {4.5f, 1.2f, 9.8f, 2.3f, 6.7f};

    int f = sizeof(floatArr) / sizeof(floatArr[0]);

    heapSort(floatArr, f, sizeof(float), compareFloat);

    printf("Sorted floats:\n");

    for (int i = 0; i < f; i++)
    {
        printf("%.2f ", floatArr[i]);
    }

    printf("\n\n");

    char charArr[] = {'z', 'a', 'k', 'b', 'm'};

    int c = sizeof(charArr) / sizeof(charArr[0]);

    heapSort(charArr, c, sizeof(char), compareChar);

    printf("Sorted characters:\n");

    for (int i = 0; i < c; i++)
    {
        printf("%c ", charArr[i]);
    }

    printf("\n\n");

    char *stringArr[] = {"banana", "apple", "orange", "grape", "mango"};

    int s = sizeof(stringArr) / sizeof(stringArr[0]);

    heapSort(stringArr, s, sizeof(char *), compareString);

    printf("Sorted strings:\n");

    for (int i = 0; i < s; i++)
    {
        printf("%s\n", stringArr[i]);
    }

    return 0;
}