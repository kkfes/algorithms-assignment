# 📘 Algorithms Assignment

## 📑 Overview
This project implements several classic **Divide-and-Conquer algorithms** with safe recursion patterns, metrics collection, and runtime analysis.

Implemented algorithms:
- MergeSort
- QuickSort
- Deterministic Select (Median-of-Medians)
- Closest Pair of Points (2D)

Metrics collected:
- Comparisons
- Allocations
- Recursion depth
- Execution time (ns)

---

## 🏗 Architecture Notes
- `Metrics` class tracks comparisons, allocations, recursion depth, and runtime.
- Each algorithm uses recursion carefully:
    - **MergeSort**: small-`n` cutoff with insertion sort, buffer reuse.
    - **QuickSort**: randomized pivot, tail recursion optimization (smaller partition first).
    - **SelectMoM**: group by 5, median-of-medians pivot, recurse only on one side.
    - **Closest Pair**: recursive split by x, "strip" check by y-order.

---

## 📊 Recurrence Analysis

### MergeSort
- Recurrence: `T(n) = 2T(n/2) + Θ(n)`
- Master Theorem Case 2 ⇒ `Θ(n log n)`.

### QuickSort
- Expected recurrence: `T(n) = T(n/2) + T(n/2) + Θ(n)`
- Randomized pivot ⇒ expected depth `Θ(log n)`, average `Θ(n log n)`.

### Select (Median-of-Medians)
- Recurrence: `T(n) = T(n/5) + T(7n/10) + Θ(n)`
- Solves to `Θ(n)`.

### Closest Pair
- Recurrence: `T(n) = 2T(n/2) + Θ(n)`
- Master Theorem Case 2 ⇒ `Θ(n log n)`.

---

## 📸 Screenshots / Examples

### MergeSort
Input: `[5, 2, 9, 1, 5, 6]`  
Output:
