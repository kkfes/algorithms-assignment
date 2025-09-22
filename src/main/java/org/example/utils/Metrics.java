package org.example.utils;

public class Metrics {
    private long comparisons;
    private long allocations;
    private long maxDepth;
    private long currentDepth;
    private long startTime;
    private long elapsedTime;

    public void reset() {
        comparisons = allocations = maxDepth = currentDepth = 0;
        startTime = elapsedTime = 0;
    }

    public void incComparisons(long n) { comparisons += n; }
    public void incAllocations(long n) { allocations += n; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { elapsedTime = System.nanoTime() - startTime; }

    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public long getMaxDepth() { return maxDepth; }
    public long getElapsedTime() { return elapsedTime; }

    @Override
    public String toString() {
        return "Metrics{" +
                "time=" + elapsedTime + "ns, " +
                "comparisons=" + comparisons + ", " +
                "allocations=" + allocations + ", " +
                "maxDepth=" + maxDepth +
                '}';
    }
}
