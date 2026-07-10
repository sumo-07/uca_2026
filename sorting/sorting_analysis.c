#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void bubble_sort(int arr[], int n);
void insertion_sort(int arr[], int n);
void selection_sort(int arr[], int n);
void quick_sort(int arr[], int low, int high);
void run_quick_sort(int arr[], int n);
void merge_sort(int arr[], int n);
void heap_sort(int arr[], int n);

void generate_random(int arr[], int n);
void generate_ascending(int arr[], int n);
void generate_descending(int arr[], int n);

int is_sorted(int arr[], int n);
void copy_array(int dest[], int src[], int n);

typedef struct {
    const char *name;
    void (*sort_func)(int[], int);
} Algorithm;

void bubble_sort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        int swapped = 0;
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = 1;
            }
        }
        if (!swapped) break;
    }
}

void insertion_sort(int arr[], int n) {
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

void selection_sort(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        int temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    }
}

void quick_sort(int arr[], int low, int high) {
    if (low < high) {
        int pivot = arr[low + (high - low) / 2];
        int i = low;
        int j = high;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) quick_sort(arr, low, j);
        if (i < high) quick_sort(arr, i, high);
    }
}

void run_quick_sort(int arr[], int n) {
    quick_sort(arr, 0, n - 1);
}

void merge(int arr[], int temp[], int l, int m, int r) {
    int i = l;
    int j = m + 1;
    int k = l;
    while (i <= m && j <= r) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
        }
    }
    while (i <= m) {
        temp[k++] = arr[i++];
    }
    while (j <= r) {
        temp[k++] = arr[j++];
    }
    for (i = l; i <= r; i++) {
        arr[i] = temp[i];
    }
}

void merge_sort_recursive(int arr[], int temp[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;
        merge_sort_recursive(arr, temp, l, m);
        merge_sort_recursive(arr, temp, m + 1, r);
        merge(arr, temp, l, m, r);
    }
}

void merge_sort(int arr[], int n) {
    int *temp = (int *)malloc(n * sizeof(int));
    if (temp != NULL) {
        merge_sort_recursive(arr, temp, 0, n - 1);
        free(temp);
    }
}

void heapify(int arr[], int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && arr[l] > arr[largest]) {
        largest = l;
    }
    if (r < n && arr[r] > arr[largest]) {
        largest = r;
    }
    if (largest != i) {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
        heapify(arr, n, largest);
    }
}

void heap_sort(int arr[], int n) {
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }
    for (int i = n - 1; i > 0; i--) {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        heapify(arr, i, 0);
    }
}

void generate_random(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        arr[i] = rand();
    }
}

void generate_ascending(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        arr[i] = i;
    }
}

void generate_descending(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        arr[i] = n - i;
    }
}

int is_sorted(int arr[], int n) {
    for (int i = 0; i < n - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            return 0;
        }
    }
    return 1;
}

void copy_array(int dest[], int src[], int n) {
    for (int i = 0; i < n; i++) {
        dest[i] = src[i];
    }
}

int main() {
    srand(time(NULL));

    Algorithm algos[] = {
        {"Bubble Sort", bubble_sort},
        {"Insertion Sort", insertion_sort},
        {"Selection Sort", selection_sort},
        {"Quick Sort", run_quick_sort},
        {"Merge Sort", merge_sort},
        {"Heap Sort", heap_sort}
    };
    int num_algos = sizeof(algos) / sizeof(algos[0]);

    int start_size = 8000;
    int step_size = 4000;
    int num_iterations = 8;
    const char *types[] = {"Random", "Ascending", "Descending"};

    for (int iter = 0; iter < num_iterations; iter++) {
        int size = start_size + iter * step_size;

        int *orig_rand = (int *)malloc(size * sizeof(int));
        int *orig_asc = (int *)malloc(size * sizeof(int));
        int *orig_desc = (int *)malloc(size * sizeof(int));
        int *temp_arr = (int *)malloc(size * sizeof(int));

        if (!orig_rand || !orig_asc || !orig_desc || !temp_arr) {
            free(orig_rand);
            free(orig_asc);
            free(orig_desc);
            free(temp_arr);
            return 1;
        }

        generate_random(orig_rand, size);
        generate_ascending(orig_asc, size);
        generate_descending(orig_desc, size);

        for (int a = 0; a < num_algos; a++) {
            for (int t = 0; t < 3; t++) {
                if (t == 0) {
                    copy_array(temp_arr, orig_rand, size);
                } else if (t == 1) {
                    copy_array(temp_arr, orig_asc, size);
                } else {
                    copy_array(temp_arr, orig_desc, size);
                }

                clock_t start = clock();
                algos[a].sort_func(temp_arr, size);
                clock_t end = clock();
                double elapsed = ((double)(end - start) * 1000.0) / CLOCKS_PER_SEC;

                printf("%d %s %s %.2f\n", size, types[t], algos[a].name, elapsed);
            }
        }

        free(orig_rand);
        free(orig_asc);
        free(orig_desc);
        free(temp_arr);
    }

    return 0;
}