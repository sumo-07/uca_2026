#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int value;
    int index;
} Pair;

// Global pointer to hold the counts of smaller elements to the right.
static int* ans;

void merge(Pair* arr, int low, int mid, int high) {
    int len1 = mid - low + 1;
    int len2 = high - mid;

    // Temporary arrays to hold the two halves during the merge step
    Pair* first = (Pair*)malloc(len1 * sizeof(Pair));
    Pair* second = (Pair*)malloc(len2 * sizeof(Pair));

    int mainArrayIndex = low;

    for (int i = 0; i < len1; i++) {
        first[i] = arr[mainArrayIndex++];
    }

    mainArrayIndex = mid + 1;

    
    for (int i = 0; i < len2; i++) {
        second[i] = arr[mainArrayIndex++];
    }

    mainArrayIndex = low;

    int index1 = 0;
    int index2 = 0;
    
    
    int rightCount = 0; // check how many are smaller

    while (index1 < len1 && index2 < len2) {
        if (second[index2].value < first[index1].value) {
            rightCount++;
            arr[mainArrayIndex++] = second[index2++];
        } else {
           // no element is smaller now(as the arrays are sorted in asc)
            ans[first[index1].index] += rightCount;
            arr[mainArrayIndex++] = first[index1++];
        }
    }

    // no loop for those elements which are left in the left array
    // because woh already greater hai
    while (index1 < len1) {
        ans[first[index1].index] += rightCount;
        arr[mainArrayIndex++] = first[index1++];
    }

    while (index2 < len2) {
        arr[mainArrayIndex++] = second[index2++];
    }

    free(first);
    free(second);
}

void mergeSort(Pair* arr, int low, int high) {
    if (low >= high) {
        return;
    }

    int mid = (low + high) / 2;
    mergeSort(arr, low, mid);
    mergeSort(arr, mid + 1, high);

    merge(arr, low, mid, high);
}


int* countSmaller(int* nums, int numsSize) {
    if (numsSize == 0) {
        return NULL;
    }

    // Initialize output array with zeros using calloc
    ans = (int*)calloc(numsSize, sizeof(int));
    if (ans == NULL) {
        return NULL;
    }

    // Create an array of Pairs to keep track of elements and their original indices
    Pair* arr = (Pair*)malloc(numsSize * sizeof(Pair));
    if (arr == NULL) {
        free(ans);
        return NULL;
    }

    for (int i = 0; i < numsSize; i++) {
        arr[i].value = nums[i];
        arr[i].index = i;
    }

    // Run merge sort to count smaller elements
    mergeSort(arr, 0, numsSize - 1);

    free(arr);
    return ans;
}

int main() {
    // Example 1
    int test1[] = {5, 2, 6, 1};
    int size1 = sizeof(test1) / sizeof(test1[0]);
    int* result1 = countSmaller(test1, size1);
    printf("Example 1 Output: ");
    for (int i = 0; i < size1; i++) {
        printf("%d ", result1[i]);
    }
    printf("\n");
    free(result1);

    // Example 2
    int test2[] = {-1};
    int size2 = sizeof(test2) / sizeof(test2[0]);
    int* result2 = countSmaller(test2, size2);
    printf("Example 2 Output: ");
    for (int i = 0; i < size2; i++) {
        printf("%d ", result2[i]);
    }
    printf("\n");
    free(result2);

    // Example 3
    int test3[] = {-1, -1};
    int size3 = sizeof(test3) / sizeof(test3[0]);
    int* result3 = countSmaller(test3, size3);
    printf("Example 3 Output: ");
    for (int i = 0; i < size3; i++) {
        printf("%d ", result3[i]);
    }
    printf("\n");
    free(result3);

    return 0;
}
