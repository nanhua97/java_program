package sort;

import java.util.Arrays;

/**
 * 冒泡排序:
 *      外层循环记录元素位置
 *      内层循环将元素两两比较,不断移动
 * 时间复杂度:
 *      最好:O(n^2)
 *      最坏:O(n^2)
 *      平均:O(n^2)
 * 空间复杂度:
 *      最好:0
 *      最坏:O(n)
 *      平均:O(1)
 * 稳定
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr = {1, 0, 5, 7, 3, 4, 2, 8, 9, 0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(Integer[] arr) {
        Integer temp;
        //将元素从右向左移动的方案
        /*for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }*/
        //将元素从左向右移动的方案
        for (int i = arr.length-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
