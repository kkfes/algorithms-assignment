package org.example.algos;

import org.example.utils.Metrics;

import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] arr, Metrics m) {
        if (arr == null || arr.length <= 1) return;

        m.reset();
        m.startTimer();
        quicksort(arr, 0, arr.length - 1, m);
        m.stopTimer();
    }

    private static void quicksort(int[] arr, int left, int right, Metrics m) {
        while (left < right) {
            m.enterRecursion();

            int pivotIndex = left + rnd.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];

            int i = left;
            int j = right;

            // partition
            while (i <= j) {
                while (arr[i] < pivot) { i++; m.incComparisons(1); }
                while (arr[j] > pivot) { j--; m.incComparisons(1); }
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }

            // Рекурсируемся на меньшей половине
            if (j - left < right - i) {
                if (left < j) quicksort(arr, left, j, m);
                left = i; // итерация по большой части
            } else {
                if (i < right) quicksort(arr, i, right, m);
                right = j; // итерация по большой части
            }

            m.exitRecursion();
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
