package com.tcl.algorithm;

public class HeapSort {

    public static void main(String[] args) {
        int[] a = new int[]{0, 9, 8, 2, 5, 6, 3, 8, 9, 4, 6, 1, 32, 5, 2};

        print(a);

        new HeapSort().heapMaxSort(a);
        new HeapSort().heapMinSort(a);
    }

    private static void print(int[] a) {
        for (int i : a) {
            System.out.print(i + "-");
        }
        System.out.println();
    }


    void heapMaxSort(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        buildHeap(arr, true);
        for (int i = 0; i < n; i++) {
            res[i] = arr[0];
            swap(arr, 0, n - 1 - i);
            maxHeapify(arr, 0, n - 1 - i);
        }
        print(res);
    }

    void heapMinSort(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        buildHeap(arr, false);
        for (int i = 0; i < n; i++) {
            res[i] = arr[0];
            swap(arr, 0, n - 1 - i);
            minHeapify(arr, 0, n - 1 - i);
        }
        print(res);
    }


    void buildHeap(int[] arr, boolean max) {
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            if (max) maxHeapify(arr, i, n);
            else minHeapify(arr, i, n);
        }
    }

    void maxHeapify(int[] arr, int i, int n) {
        int largest = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        if (l < n) {
            if (arr[l] > arr[largest]) largest = l;

            if (r < n && arr[r] > arr[largest]) largest = r;
        }
        if (largest != i) {
            swap(arr, largest, i);
            maxHeapify(arr, largest, n);
        }
    }

    void minHeapify(int[] arr, int i, int n) {
        int smallest = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        if (l < n) {
            if (arr[l] < arr[smallest]) smallest = l;

            if (r < n && arr[r] < arr[smallest]) smallest = r;
        }
        if (smallest != i) {
            swap(arr, smallest, i);
            minHeapify(arr, smallest, n);
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;
    }
}
