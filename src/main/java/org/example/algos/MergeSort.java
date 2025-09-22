package org.example.algos;

import org.example.utils.Metrics;

public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    private static Metrics metrics;

    // Точка входа
    public static void sort(int[] array, Metrics m) {
        metrics = m;
        metrics.reset();

        metrics.startTimer(); // замер времени

        int[] buffer = new int[array.length];
        metrics.incAllocations(array.length);

        mergeSort(array, buffer, 0, array.length - 1);

        metrics.stopTimer(); // остановка таймера
    }

    // Рекурсивный mergesort
    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (left >= right) return;

        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }

        metrics.enterRecursion();
        int mid = left + (right - left) / 2;

        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
        metrics.exitRecursion();
    }

    // Слияние двух отсортированных частей
    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.incComparisons(1);
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int p = left; p <= right; p++) {
            arr[p] = buffer[p];
        }
    }

    // Insertion sort для маленьких массивов
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incComparisons(1);
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}
