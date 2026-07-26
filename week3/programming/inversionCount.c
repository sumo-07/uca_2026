#include <stdio.h>
#include <stdlib.h>

static int ans = 0;

void merge(int* nums, int low, int mid, int high) {
    // Count Pairs (Inversions)
    int j = mid + 1;
    for (int i = low; i <= mid; i++) {
        while (j <= high && nums[i] > nums[j]) {
            j++;
        }
        ans += j - (mid + 1);
    }

    // Merging
    int leftS = mid - low + 1;
    int rightS = high - mid;
    int* left = (int*)malloc(leftS * sizeof(int));
    int* right = (int*)malloc(rightS * sizeof(int));

    int mainArrayIndex = low;
    for (int i = 0; i < leftS; i++) {
        left[i] = nums[mainArrayIndex++];
    }

    mainArrayIndex = mid + 1;
    for (int i = 0; i < rightS; i++) {
        right[i] = nums[mainArrayIndex++];
    }

    mainArrayIndex = low;
    int index1 = 0;
    int index2 = 0;

    while (index1 < leftS && index2 < rightS) {
        if (left[index1] < right[index2]) {
            nums[mainArrayIndex++] = left[index1++];
        } else {
            nums[mainArrayIndex++] = right[index2++];
        }
    }

    while (index1 < leftS) {
        nums[mainArrayIndex++] = left[index1++];
    }

    while (index2 < rightS) {
        nums[mainArrayIndex++] = right[index2++];
    }

    free(left);
    free(right);
}

void mergeSort(int* nums, int low, int high) {
    if (low >= high) {
        return;
    }

    int mid = (low + high) / 2;

    mergeSort(nums, low, mid);
    mergeSort(nums, mid + 1, high);

    merge(nums, low, mid, high);
}

int inversionCount(int* nums, int numsSize) {
    ans = 0;
    int n = numsSize - 1;
    mergeSort(nums, 0, n);
    return ans;
}

int main() {
    int test1[] = {1, 2, 4, 1, 3, 5};
    int size1 = sizeof(test1) / sizeof(test1[0]);
    printf("Example 1 Output: %d\n", inversionCount(test1, size1));

    int test2[] = {2, 3, 4, 5, 6};
    int size2 = sizeof(test2) / sizeof(test2[0]);
    printf("Example 2 Output: %d\n", inversionCount(test2, size2));

    return 0;
}
