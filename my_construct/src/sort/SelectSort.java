package sort;

import java.util.Arrays;

/**
 * 选择排序:
 *      外层循环记录元素位置,
 *      内层循环选出最大/小的数字,
 *      填入外层循环的位置
 * 时间复杂度:
 *      最优:O(n^2)
 *      最坏:O(n^2)
 *      平均:0(n^2)
 * 空间复杂度:
 *      平均:O(1)
 * 不稳定
 */
public class SelectSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 5, 0, 7, 5, 3, 4, 2, 8, 9, 0};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

            //记录最小元素及其索引
            int minIndex = i;
            int minNum = arr[i];

            for (int j = i; j < arr.length; j++) {
                if (arr[j] < minNum) {
                    minIndex = j;
                    minNum = arr[j];
                }
            }

            //将最小元素与当前元素交换
            arr[minIndex] = arr[i];
            arr[i] = minNum;

        }
    }
}
