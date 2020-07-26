package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuicklySort {
    public static void main(String[] args) {
//        Integer[] arr = {1, 0, 5, 7, 3, 4, 2, 8};
        Integer[] arr = {2, 6, 7, 7};

        merge(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(Integer[] arr,Integer start,Integer end){

        if (start >= end){
            return;
        }
        int index = sort(arr, start, end);
        merge(arr,start,index);
        merge(arr,index+1,end);

    }

    public static int sort(Integer[] arr,Integer start,Integer end){
        Integer point = arr[start];
        Integer startIndex = start;
        Integer endIndex = end;

        while (endIndex>startIndex){

            while (endIndex>startIndex && arr[endIndex]>=point){
                endIndex--;
            }
            if (endIndex>startIndex){
                arr[startIndex++] = arr[endIndex];
            }
            while (endIndex>startIndex && arr[startIndex]<=point){
                startIndex++;
            }
            if (endIndex>startIndex) {
                arr[endIndex--] = arr[startIndex];
            }
        }

        arr[endIndex] = point;

        return endIndex;

    }


}
