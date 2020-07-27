package sort;

import java.util.*;

public class BucketSort {
    public static void main(String[] args) {
        Integer[] arr = {22,5,11,41,45,26,27,10,7,8,30,27,42,43,40};
        System.out.println(Arrays.toString(arr));
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void bucketSort(Integer[] arr) {
        HashMap<Integer, List<Integer>> bucketList = new HashMap<>();
        List bucketIndex = new ArrayList<Integer>();
        for (Integer integer : arr) {
            int buck = integer / 10;
            int element = integer % 10;
            if (bucketList.get(buck) == null) {
                bucketList.put(buck, new ArrayList<Integer>());
                bucketIndex.add(buck);
            }
            bucketList.get(buck).add(element);
        }
        Integer[] bucketIndexArr = (Integer[]) bucketIndex.toArray(new Integer[0]);
        QuicklySort.merge(bucketIndexArr, 0, bucketIndexArr.length - 1);
        Integer index = 0;
        for (Integer integer : bucketIndexArr) {
            Integer[] k = bucketList.get(integer).toArray(new Integer[0]);
            QuicklySort.merge(k, 0, k.length - 1);
            for (Integer integer1 : k) {
                arr[index++] = integer*10 + integer1;
            }
        }

    }
}
