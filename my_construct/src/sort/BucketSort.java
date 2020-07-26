package sort;

import java.util.*;

public class BucketSort {
    public static void main(String[] args) {
        Integer[] arr = {22,5,11,41,45,26,27,10,7,8,30,27,42,43,40};
        System.out.println(Arrays.toString(arr));
        bucketSort(arr);
    }

    public static void bucketSort(Integer[] arr){
        HashMap<Integer, List<Integer>> bucketList = new HashMap<>();
        Integer[] bucketIndex = new Integer[bucketList.size()];
        for (Integer integer : arr) {
            int buck = integer / 10;
            int element = integer % 10;
            if (bucketList.get(buck) == null) {
                bucketList.put(buck,new ArrayList<Integer>());
            }
            bucketList.get(buck).add(element);
        }
        HashMap<Integer, Integer[]> integerHashMap = new HashMap<>();
        bucketList.forEach((key,item)->{
            Integer[] k = item.toArray(new Integer[0]);
            QuicklySort.merge(k,0,k.length-1);
            integerHashMap.put(key,k);
        });

    }
}
