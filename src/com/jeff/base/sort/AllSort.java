package com.jeff.base.sort;

import java.util.Arrays;

/**
 * 9大排序
 * 1.冒泡排序
 * 2.选择排序
 * 3.插入排序
 * 4.桶排序
 * 5.快速排序
 * 6.堆排序
 * 7.希尔排序
 * 8.归并排序
 * 9.基数排序
 * 用以上9中不同的方法按照从小到大的顺序排序
 */
public class AllSort {

    private static final int[] SORT_ARRAY = {43, 64, 21, 6565, 3424, 22, 6523, 345, 89, 68, 162, 528};

    public static void main(String[] args) {
        AllSort sort = new AllSort();
        sort.bubbleSort(SORT_ARRAY);
        sort.selectSort(SORT_ARRAY);
        sort.insertSort(SORT_ARRAY);
        sort.bucketSort(SORT_ARRAY);
        sort.quickSort(SORT_ARRAY);
        sort.shellSort(SORT_ARRAY);
    }


    /**
     * 冒泡排序
     *
     * @param array
     */
    private void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
        System.out.println("冒泡排序后的顺序是：" + Arrays.toString(array));
    }


    /**
     * 选择排序
     *
     * @param array
     */
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
                if (min != i) {
                    int tmp = array[min];
                    array[min] = array[i];
                    array[i] = tmp;
                }
            }
        }
        System.out.println("选择排序后的顺序是：" + Arrays.toString(array));
    }


    /**
     * 插入排序
     *
     * @param array
     */
    private void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int pos = i - 1;
            for (pos = i - 1; pos >= 0; pos--) {
                if (array[pos] < array[i]) {
                    break;
                }
            }
            if (pos != i - 1) {
                int tmp = array[i];
                for (int j = i - 1; j > pos; j--) {
                    array[j + 1] = array[j];
                }
                array[pos + 1] = tmp;
            }
        }
        System.out.println("插入排序后的顺序是：" + Arrays.toString(array));
    }

    /**
     * 桶排序
     *
     * @param array
     */
    private void bucketSort(int[] array) {
        int max = 0;
        for (int num : array) {
            if (num > max)
                max = num;
        }
        int[] buckets = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            buckets[array[i]]++;
        }
        for (int i = 0, j = 0; i < max; i++) {
            int key = buckets[i];
            while (key > 0) {
                array[j] = i;
                j++;
                key--;
            }
        }
        System.out.println("  桶排序后的顺序是：" + Arrays.toString(array));
    }

    private void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println("快速排序后的顺序是：" + Arrays.toString(array));
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int head = left;
            int tail = right;
            int x = array[head];
            while (head < tail) {
                while (head < tail && array[tail] > x) {
                    tail--;
                }
                if (head < tail) {
                    array[head] = array[tail];
                    head++;
                }
                while (head < tail && array[head] < x) {
                    head++;
                }
                if (head < tail) {
                    array[tail] = array[head];
                    tail--;
                }
            }
            array[head] = x;
            quickSort(array, left, head - 1);
            quickSort(array, head + 1, right);
        }

    }


    private void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] < array[i])
                    break;
            }
            if (j != i - 1) {
                int tmp = array[i];
                for (int k = i - 1; k > j; k--) {
                    array[k + 1] = array[k];
                }
                array[j + 1] = tmp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 希尔排序
     *
     * @param array
     */
    private void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {
            for (int i = 0; i < gap; i++) {
                //
                shellGroupSort(array, i, gap);
            }
        }
        System.out.println(Arrays.toString(array));
    }

    private void shellGroupSort(int[] array, int i, int gap) {
        for (int j = i + gap; j < array.length; j = j + gap) {
            int m;
            for (m = j - gap; m >= i; m -= gap) {
                if (array[m] < array[j])
                    break;
            }
            if (m != j - gap) {
                int tmp = array[j];
                for (int k = j - gap; k > m; k -= gap) {
                    array[k + gap] = array[k];
                }
                array[m + gap] = tmp;
            }
        }
    }
}
