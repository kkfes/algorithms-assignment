package org.example.algos;

import org.example.utils.Metrics;
import java.util.*;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static class Result {
        public final Point p1, p2;
        public final double dist;
        public Result(Point p1, Point p2, double dist) {
            this.p1 = p1; this.p2 = p2; this.dist = dist;
        }
    }

    public static Result findClosest(Point[] points, Metrics m) {
        Point[] pts = points.clone();
        Arrays.sort(pts, Comparator.comparingDouble(p -> p.x));
        return closest(pts, 0, pts.length - 1, new Point[pts.length], m);
    }

    private static Result closest(Point[] pts, int l, int r, Point[] buffer, Metrics m) {
        if (r - l <= 3) {
            return bruteForce(pts, l, r, m);
        }

        int mid = (l + r) / 2;
        double midX = pts[mid].x;

        Result left = closest(pts, l, mid, buffer, m);
        Result right = closest(pts, mid + 1, r, buffer, m);

        Result best = left.dist < right.dist ? left : right;
        double delta = best.dist;

        // merge sort by y
        mergeByY(pts, l, mid, r, buffer);

        // build strip
        List<Point> strip = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Math.abs(pts[i].x - midX) < delta) {
                strip.add(pts[i]);
            }
        }

        // check neighbors in strip
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < delta; j++) {
                m.incComparisons(1);
                double d = dist(strip.get(i), strip.get(j));
                if (d < best.dist) {
                    best = new Result(strip.get(i), strip.get(j), d);
                    delta = d;
                }
            }
        }

        return best;
    }

    private static void mergeByY(Point[] pts, int l, int mid, int r, Point[] buffer) {
        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            if (pts[i].y <= pts[j].y) buffer[k++] = pts[i++];
            else buffer[k++] = pts[j++];
        }
        while (i <= mid) buffer[k++] = pts[i++];
        while (j <= r) buffer[k++] = pts[j++];
        for (i = l; i <= r; i++) pts[i] = buffer[i];
    }

    private static Result bruteForce(Point[] pts, int l, int r, Metrics m) {
        double minDist = Double.POSITIVE_INFINITY;
        Point a = null, b = null;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                m.incComparisons(1);
                double d = dist(pts[i], pts[j]);
                if (d < minDist) {
                    minDist = d;
                    a = pts[i]; b = pts[j];
                }
            }
        }
        return new Result(a, b, minDist);
    }

    private static double dist(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
