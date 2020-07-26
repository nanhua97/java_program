package sort;

import java.util.Arrays;

/**
 * 计数排序,一种特殊的桶排序
 */
public class CountingSort {
    public static void main(String[] args) {
        Integer[] arr = {2,5,3,0,2,3,0,3};
        countingSort(arr);
    }
    public static void countingSort(Integer[] arr){

        Integer max = 0;
        for (Integer integer : arr) {
            if (integer>max){
                max = integer;
            }
        }
        int[] bucket = new int[max+1];
        int[] bucket1 = new int[max+1];
        //计算每个元素的个数
        for (Integer integer : arr) {
            bucket[integer] += 1;
        }
        //依次累加
        for (int i = 0; i < bucket.length; i++) {
            if (i==0){
                bucket1[i] = bucket[i];
            }else {
                bucket1[i] = bucket1[i-1] + bucket[i];
            }
        }
        System.out.println(Arrays.toString(arr));
        // 取出元素
        int arrIndex = arr.length-1;
        int bucketIndex = bucket1.length-1;
        while(bucketIndex>1){
            int num = bucket1[bucketIndex]-bucket1[bucketIndex-1];
            for (int i = 0; i < num; i++) {
                arr[arrIndex--] = bucketIndex;
            }
            bucketIndex--;
        }
        for (int i = 0; i < bucket[0]; i++) {
            arr[arrIndex--] = 0;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(bucket));
        System.out.println(Arrays.toString(bucket1));
    }

}
