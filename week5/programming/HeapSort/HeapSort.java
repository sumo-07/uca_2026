import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class HeapSort {
    public static class MyPriorityQueue {
        ArrayList<Integer> data;

        MyPriorityQueue() {
            data = new ArrayList<>();
        }

        MyPriorityQueue(int[] arr) {
            data = new ArrayList<>();
            for (int val : arr) { 
                data.add(val);
            }

            for (int i = (data.size() / 2) - 1; i >= 0; i--) { // (data.size() / 2 ) - 1 ---> this gives the last non-leaf node  
                downheapify(i);
            }
        }

        private void swap(int i, int j) {
            int ith = data.get(i); 
            int jth = data.get(j); 
            data.set(i, jth);
            data.set(j, ith);
        }

        private void downheapify(int pi) { 
            int maxi = pi; // max index
            int li = 2 * pi + 1; // left child index
            if (li < data.size() && data.get(li) > data.get(maxi)) { 
                maxi = li;
            }

            int ri = 2 * pi + 2; // right child index
            if (ri < data.size() && data.get(ri) > data.get(maxi)) { 
                maxi = ri;
            }

            if (maxi != pi) { 
                swap(maxi, pi);
                downheapify(maxi); 
            }
        }

        int remove() {
            if (data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }

            swap(0, data.size() - 1); 
            int val = data.remove(data.size() - 1);
            downheapify(0); 
            return val;
        }
    }

    public static int[] sort(int[] arr) {
        int n = arr.length;
        if (n == 0) return arr;
        MyPriorityQueue pq = new MyPriorityQueue(arr);
        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[i] = pq.remove();
        }
        return sorted;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void generateRandomArray(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt();
        }
    }

    public static void generateAscendingArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public static void generateDescendingArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr.length - i;
        }
    }

    public static double measureTime(int[] arr) {
        long start = System.nanoTime();
        sort(arr);
        long end = System.nanoTime();
        return ((double)(end - start) / 1_000_000.0);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {45, 12, 78, 34, 23, 89, 1, 56};

        System.out.println("Original Array:");
        printArray(arr);

        int[] sorted = sort(arr);

        System.out.println("\nSorted Array:");
        printArray(sorted);

        if (isSorted(sorted)) {
            System.out.println("\nValidation: Array is correctly sorted.");
        } else {
            System.out.println("\nValidation: Sorting failed.");
        }

        int[] sizes = {
            10000,
            20000,
            40000,
            80000,
            160000,
            320000
        };

        System.out.println("\nHEAP SORT PERFORMANCE");
        System.out.printf("%-10s %-18s %-18s %-18s\n",
               "Size",
               "Random (ms)",
               "Ascending (ms)",
               "Descending (ms)");
        System.out.println("---------------------------------------------------------------");

        for (int size : sizes) {
            int[] randomArray = new int[size];
            int[] ascendingArray = new int[size];
            int[] descendingArray = new int[size];

            generateRandomArray(randomArray);
            generateAscendingArray(ascendingArray);
            generateDescendingArray(descendingArray);

            double randomTime = measureTime(randomArray);
            double ascendingTime = measureTime(ascendingArray);
            double descendingTime = measureTime(descendingArray);

            System.out.printf("%-10d %-18.3f %-18.3f %-18.3f\n",
                   size,
                   randomTime,
                   ascendingTime,
                   descendingTime);
        }
    }
}