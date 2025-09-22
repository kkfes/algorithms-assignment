package org.example.algos;

import org.example.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(1000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = arr.clone();

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testReversedArray() {
        int[] arr = {9, 7, 5, 3, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);

        assertArrayEquals(expected, arr);
    }
}