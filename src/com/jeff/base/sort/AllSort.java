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
        sort.heapSort(SORT_ARRAY);
        sort.radixSort(SORT_ARRAY);
        sort.mergeSort(SORT_ARRAY);
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
        System.out.println("Shell排序后的顺序是：" + Arrays.toString(array));
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


    private void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeapSort(array, i, array.length - 1);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            maxHeapSort(array, 0, i - 1);
        }
        System.out.println("Heap排序后的顺序是：" + Arrays.toString(array));
    }


    private void maxHeapSort(int[] array, int start, int end) {
        int c = start, l = 2 * c + 1, tmp = array[start];
        for (; l <= end; c = l, l = 2 * l + 1) {
            if (l < end && array[l] < array[l + 1])
                l++;
            if (tmp >= array[l])
                break;
            else {
                array[c] = array[l];
                array[l] = tmp;
            }
        }
    }


    private void radixSort(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp);

        }
        System.out.println("基数排序后的顺序是：" + Arrays.toString(array));
    }

    private void countSort(int[] array, int exp) {
        int[] buckets = new int[10];
        int[] output = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            buckets[(array[i] / exp) % 10]++;
        }
        for (int i = 1; i < buckets.length; i++) {
            buckets[i] += buckets[i - 1];
        }
        //栈的一种思想，后入先出
        for (int i = array.length - 1; i >= 0; i--) {
            //array[i]在桶中的索引
            int index = (array[i] / exp) % 10;
            output[buckets[index] - 1] = array[i];
            buckets[index]--;
        }
        for (int i = 0; i < output.length; i++) {
            array[i] = output[i];
        }
    }


    /**
     * @param array
     */
    private void mergeSort(int[] array) {
        mergeUp2Down(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 自上而下归并
     *
     * @param array
     * @param start
     * @param end
     */
    private void mergeUp2Down(int[] array, int start, int end) {
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        mergeUp2Down(array, start, mid);
        mergeUp2Down(array, mid + 1, end);
        merge(array, start, mid, end);

    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else
                tmp[k++] = array[j++];
        }
        //如果出现start到mid和mid+1到end的长度不等的情况下就要走到这里了~
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= end) {
            tmp[k++] = array[j++];
        }
        for (int l = 0; l < tmp.length; l++) {
            array[start + l] = tmp[l];
        }

    }


    private void mergeDown2Up(int[] array) {
        for (int i = 1; i < array.length; i = 2 * i) {
            mergeGroup(array, array.length, i);
        }
    }

    /**
     * @param array
     * @param length
     * @param gap    子数组的长度
     */
    private void mergeGroup(int[] array, int length, int gap) {
        int i = 0, twolen = 2 * gap;
        for (; i + twolen - 1 < length; i = twolen + i) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }
        if (i + gap - 1 < length - 1) {
            merge(array, i, i + gap - 1, length - 1);
        }
    }

    
}
