package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {

        Integer[] arr = {1, 0, 5, 7, 3, 4, 2, 8};
        merge(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void merge(Integer[] arr,int start,int end){
        if (start<end){
            //从两端遍历找出当前元素应该在的位置
            Integer index = quickSort(arr,start,end);
            merge(arr,start,index-1);
            merge(arr,index+1,end);
        }
    }
    public static Integer quickSort(Integer[] arr, Integer start,Integer end){
        //哨兵,哨兵位置决定了游标起始的方向
        Integer sentry = arr[end];

        while(start<end){
            //哨兵为最后，则先动前边的游标
            //当哨兵左边元素小于哨兵时,左边游标右移,哨兵不动
            while (arr[start]<=sentry && start<end){
                start++;
            }
            //当哨兵左边元素大于哨兵时,将此处元素赋给右边游标,开始移动右浮标
            arr[end] = arr[start];

            //当哨兵右边元素大于哨兵时,右边游标左移,哨兵不动
            while (arr[end]>=sentry && start<end){
                end--;
            }
            //当哨兵右边元素小于哨兵时,将此处元素赋给左边游标,开始移动左游标
            arr[start] = arr[end];

        }
        //当左右游标重合,即为哨兵的位置
        arr[end] = sentry;

        return start;
    }

}
