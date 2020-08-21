public class QuickSort {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        int count = 105110;
        Integer[] a = new Integer[count];
        Integer[] b = new Integer[count];
        Integer[] c = new Integer[count];
        Integer[] d = new Integer[count];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10000000);
            // 已排序好的数组 当count 在 12510 左右的时候
            // 单向扫描划分方式 、三分双向扫描、双轴块排 都 会出现 java.lang.StackOverflowError
            // a[i] = i;
            b[i] = a[i];
            c[i] = a[i];
            d[i] = a[i];
        }
        System.err.println("初始化数组耗时： " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        forwardScanSort(a, 0, a.length - 1);
        System.err.println("单向扫描划分方式耗时：" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        threePartSort(b, 0, b.length - 1);
        System.err.println("三分单向扫描耗时：" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        threePart2Sort(c, 0, c.length - 1);
        System.err.println("三分双向扫描耗时：" + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        dualPivotQuickSort(d, 0, d.length - 1);
        System.err.println("双轴块排耗时：" + (System.currentTimeMillis() - time));
    }

    /**
     * 单向扫描划分方式
     *
     * @param a     数组
     * @param start 第一个数据的下标
     * @param end   最后一个数据的下标
     */
    public static void forwardScanSort(Integer[] nums, int start, int end) {
        if (start < end) {
            int point = nums[start];
            int i = start;
            int j = start + 1;
            while (j < end) {
                if (nums[j] < point) {
                    i++;
                    swapArray(nums, i, j);
                }
                j++;
            }
            swapArray(nums, start, i);
            forwardScanSort(nums, start, i - 1);
            forwardScanSort(nums, i + 1, end);
        }
    }

    /**
     * 三分单向扫描
     *
     * @param a     数组
     * @param start 第一个数据的下标
     * @param end   最后一个数据的下标
     */
    public static void threePartSort(Integer[] a, int start, int end) {
        if (start < end) {
            int point = a[start];
            int i = start;
            int k = start + 1;
            int j = end;
            while (k < j) {
                if (a[k] < point) {
                    swapArray(a, i, k);
                    i++;
                    k++;
                } else if (a[k] == point) {
                    k++;
                } else {
                    swapArray(a, k, j);
                    j--;
                }
            }
            threePartSort(a, start, i - 1);
            threePartSort(a, j + 1, end);
        }
    }

    /**
     * 三分双向扫描
     *
     * @param a     数组
     * @param start 第一个数据的下标
     * @param end   最后一个数据的下标
     */
    public static void threePart2Sort(Integer[] a, int start, int end) {
        if (start < end) {
            int point = a[start];
            int i = start;
            int k = start + 1;
            int j = end;
            OUT_LINE:
            while (k <= j) {
                if (a[k] < point) {
                    swapArray(a, i, k);
                    i++;
                    k++;
                } else if (a[k] == point) {
                    k++;
                } else {
                    while (a[j] > point) {
                        j--;
                        if (j < k) {
                            break OUT_LINE;
                        }
                    }

                    if (a[j] < point) {
                        swapArray(a, k, j);
                        swapArray(a, i, k);
                        i++;
                    } else {
                        swapArray(a, k, j);
                    }
                    k++;
                }
            }

            threePart2Sort(a, start, i - 1);
            threePart2Sort(a, j + 1, end);
        }

    }

    /**
     * 双轴快排
     *
     * @param a     数组
     * @param start 第一个数据的下标
     * @param end   最后一个数据的下标
     */
    public static void dualPivotQuickSort(Integer[] a, int start, int end) {
        if (start < end) {
            if (a[start] > a[end]) {
                swapArray(a, start, end);
            }
            int p1 = a[start];
            int p2 = a[end];
            int i = start;
            int j = end;
            int k = start + 1;
            OUT_LOOP:
            while (k < j) {
                if (a[k] < p1) {
                    i++;
                    swapArray(a, i, k);
                    k++;
                } else if (a[k] <= p2) {
                    k++;
                } else {
                    while (a[j] >= p2) {
                        j--;
                        if (j <= k) {
                            break OUT_LOOP;
                        }
                    }
                    if (a[j] < p1) {
                        swapArray(a, j, k);
                        i++;
                        swapArray(a, i, k);
                    } else {
                        swapArray(a, j, k);
                    }
                    k++;
                }
            }
            swapArray(a, i, start);
            swapArray(a, j, end);
            dualPivotQuickSort(a, start, i - 1);
            dualPivotQuickSort(a, i + 1, j - 1);
            dualPivotQuickSort(a, j + 1, end);
        }
    }

    /**
     * 交换数组的位置
     *
     * @param arrays
     * @param posA
     * @param posB
     */
    public static void swapArray(Object[] arrays, int posA, int posB) {
        Object o = arrays[posA];
        arrays[posA] = arrays[posB];
        arrays[posB] = o;
    }
}
