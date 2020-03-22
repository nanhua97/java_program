package sort;

import java.util.Arrays;

/**
 * 归并排序:
 *      先将元素数组二分拆分至每个数组只有一个元素
 *      然后在逐层往上按序合并
 * 时间复杂度:
 *      最好:O(nlogn)
 *      最坏:O(nlogn)
 *      平均:O(nlogn)
 * 空间复杂度:
 *      最好:O(n)
 *      最坏:O(n)
 *      平均:O(n)
 * 稳定
 */
public class MergeSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 0, 5, 7, 3, 4, 2, 8, 9, 0};
        Integer[] merge = merge(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(merge));
    }

    public static Integer[] merge(Integer[] arr, int start, int end) {
        if (end == start) {
            return new Integer[]{arr[end]};
        }
        int center = start + (end - start) / 2;
        Integer[] preArr = merge(arr, start, center);
        Integer[] postArr = merge(arr, center + 1, end);
        Integer[] newArr = mergeSort(preArr, postArr);
        return newArr;
    }

    public static Integer[] mergeSort(Integer[] preArr, Integer[] postArr) {
        Integer[] arr = new Integer[preArr.length + postArr.length];
        int arrIndex = 0;
        int preIndex = 0;
        int postIndex = 0;
        while (preIndex < preArr.length && postIndex < postArr.length) {
            if (preArr[preIndex] < postArr[postIndex]) {
                arr[arrIndex++] = preArr[preIndex++];
            } else {
                arr[arrIndex++] = postArr[postIndex++];
            }
        }
        while (preIndex < preArr.length) {
            arr[arrIndex++] = preArr[preIndex++];
        }

        while (postIndex < postArr.length) {
            arr[arrIndex++] = postArr[postIndex++];
        }

        return arr;

    }

}
