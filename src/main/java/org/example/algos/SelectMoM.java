package org.example.algos;

import org.example.utils.Metrics;

import java.util.Arrays;

public class SelectMoM {
    private static Metrics metrics;

    public static int select(int[] arr, int k, Metrics m) {
        metrics = m;
        metrics.reset();
        metrics.startTimer();

        int result = selectRecursive(arr, 0, arr.length - 1, k);

        metrics.stopTimer();
        return result;
    }

    private static int selectRecursive(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        // выбираем pivot через медиану медиан
        int pivotIndex = pivotIndex(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        int length = pivotIndex - left + 1;

        if (k == length) {
            return arr[pivotIndex];
        } else if (k < length) {
            return selectRecursive(arr, left, pivotIndex - 1, k);
        } else {
            return selectRecursive(arr, pivotIndex + 1, right, k - length);
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            metrics.incComparisons(1);
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int pivotIndex(int[] arr, int left, int right) {
        if (right - left < 5) {
            Arrays.sort(arr, left, right + 1);
            return (left + right) / 2;
        }

        int subRight = left;
        for (int i = left; i <= right; i += 5) {
            int subEnd = Math.min(i + 4, right);
            Arrays.sort(arr, i, subEnd + 1);
            int median = i + (subEnd - i) / 2;
            swap(arr, median, subRight);
            subRight++;
        }

        return pivotIndex(arr, left, subRight - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
