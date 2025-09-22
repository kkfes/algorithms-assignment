package org.example.algos;

import org.example.utils.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

class SelectMoMTest {

    @Test
    void testSmallArray() {
        int[] arr = {3, 1, 4, 1, 5, 9};
        Metrics m = new Metrics();

        int k = 3; // третий по величине
        int result = SelectMoM.select(arr.clone(), k, m);

        Arrays.sort(arr);
        assertEquals(arr[k - 1], result);
    }

    @Test
    void testRandomArray() {
        Random rand = new Random(42);
        int[] arr = rand.ints(1000, 0, 10000).toArray();
        Metrics m = new Metrics();

        int k = 500;
        int result = SelectMoM.select(arr.clone(), k, m);

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        assertEquals(sorted[k - 1], result);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics m = new Metrics();
        assertEquals(42, SelectMoM.select(arr, 1, m));
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        assertEquals(4, SelectMoM.select(arr, 4, m));
    }
}
