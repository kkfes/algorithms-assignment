package org.example.algos;

import org.example.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    private double bruteForce(ClosestPair.Point[] pts) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double d = Math.sqrt(dx * dx + dy * dy);
                if (d < min) {
                    min = d;
                }
            }
        }
        return min;
    }

    @Test
    void testSmallRandom() {
        Random rand = new Random(42);
        ClosestPair.Point[] pts = new ClosestPair.Point[20];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
        }

        Metrics m = new Metrics();
        ClosestPair.Result res = ClosestPair.findClosest(pts, m);

        double expected = bruteForce(pts);
        assertEquals(expected, res.dist, 1e-9, "Closest pair distance mismatch");
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4)
        };
        Metrics m = new Metrics();
        ClosestPair.Result res = ClosestPair.findClosest(pts, m);

        assertEquals(5.0, res.dist, 1e-9);
    }

    @Test
    void testLargeRandom() {
        Random rand = new Random(123);
        ClosestPair.Point[] pts = new ClosestPair.Point[10000];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new ClosestPair.Point(rand.nextDouble() * 10000, rand.nextDouble() * 10000);
        }

        Metrics m = new Metrics();
        ClosestPair.Result res = ClosestPair.findClosest(pts, m);

        assertTrue(res.dist >= 0, "Distance must be non-negative");
        assertNotNull(res.p1);
        assertNotNull(res.p2);
    }
}
