package org.example;

import org.example.algos.ClosestPair;
import org.example.utils.Metrics;
import java.util.Arrays;
import org.example.algos.MergeSort;
import org.example.algos.QuickSort;
import org.example.algos.SelectMoM;
import org.example.algos.ClosestPair.Point;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Algorithms Demo ===");

        // 1. MergeSort
        int[] arr1 = {5, 2, 9, 1, 5, 6};
        Metrics metrics1 = new Metrics();
        metrics1.reset();
        metrics1.startTimer();
        MergeSort.sort(arr1, metrics1);
        metrics1.stopTimer();
        System.out.println("\n--- MergeSort ---");
        System.out.println("Sorted: " + Arrays.toString(arr1));
        System.out.println(metrics1);

        // 2. QuickSort
        int[] arr2 = {8, 4, 7, 3, 2, 6, 5, 1};
        Metrics metrics2 = new Metrics();
        metrics2.reset();
        metrics2.startTimer();
        QuickSort.sort(arr2, metrics2);
        metrics2.stopTimer();
        System.out.println("\n--- QuickSort ---");
        System.out.println("Sorted: " + Arrays.toString(arr2));
        System.out.println(metrics2);

        // 3. Deterministic Select (Median-of-Medians)
        int[] arr3 = {7, 2, 9, 4, 5, 1, 8};
        int k = 3;
        Metrics metrics3 = new Metrics();
        metrics3.reset();
        metrics3.startTimer();
        int kth = SelectMoM.select(arr3, k, metrics3);
        metrics3.stopTimer();
        System.out.println("\n--- Deterministic Select ---");
        System.out.println(k + "-th smallest element = " + kth);
        System.out.println(metrics3);

        // 4. Closest Pair of Points
        Point[] points = {
                new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4)
        };
        Metrics metrics4 = new Metrics();
        metrics4.reset();
        metrics4.startTimer();
        ClosestPair.Result dist = ClosestPair.findClosest(points, metrics4);
        metrics4.stopTimer();
        System.out.println("\n--- Closest Pair of Points ---");
        System.out.println("Distance = " + dist.toString());
        System.out.println(metrics4);
    }
}
