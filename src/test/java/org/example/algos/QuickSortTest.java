package org.example.algos;

import org.example.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 3, 8, 4, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(1000, -5000, 5000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRecursionDepthBound() {
        int n = 1 << 16; // 65536 элементов
        int[] arr = new Random(42).ints(n, -100000, 100000).toArray();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        long depth = m.getMaxDepth();
        long logN = (long) (Math.log(n) / Math.log(2));

        assert depth <= logN * 3 : "Recursion depth too high: " + depth;
    }
}
