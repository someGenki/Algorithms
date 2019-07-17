package chapter8;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 2019年7月15日
 * 算法导论第八章:桶排序
 * 把数据分配到每个桶,再对每个桶进行排序
 * 参考:https://blog.csdn.net/qq_34801169/article/details/81412402
 * 忘记的话可以从参考这个  动图不错 https://www.cnblogs.com/guoyaohua/p/8600214.html
 */
public class bucketsort {
    private static int[] arr1 = {16, 21, 5, 49, 33, 456, 327, 56, 65, 234};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr1));
//        bucketSort(arr1);
//        System.out.println(Arrays.toString(arr1));
        bucketSort2(arr1, 10);
        System.out.println(Arrays.toString(arr1));
    }

    public static void bucketSort2(int[] arr, int bucketSize) {
        int n = arr.length;
        int min = arr[0];
        int max = arr[1];
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (arr[i] < min) min = arr[i];
            else if (arr[i] > max) max = arr[i];
        }
        System.out.println("min " + min);
        System.out.println("max " + max);
        //桶数量
        int bucketCount = (max - min + 1) / bucketSize;
        //对应的桶
        List<ArrayList<Integer>> BucketList = new ArrayList<>(bucketCount);
        for (int i = 0; i < n; i++) {
            BucketList.add(new ArrayList<Integer>(5));
        }
        for (int i : arr) {
            int index = (int) ((i - min) / (max - min + 1.0) * n);
            System.out.println("index:" + index);
            BucketList.get(index).add(i);
        }
        for (int i = 0; i < BucketList.size(); i++) {
            java.util.Collections.sort(BucketList.get(i));
        }
        int j = 0;
        for (ArrayList<Integer> A : BucketList) {
            for (int i : A) {
                arr[j++] = i;
            }
        }

    }

    public static void bucketSort(int[] arr) {
        //1.构造桶
        //确定桶的个数n
        int n = arr.length;
        //声明并初始化一个list,存放链表
        List<ArrayList<Integer>> Blist = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Blist.add(new ArrayList<>(5));
        }

        //2.将数组中的元素放到桶中
        //确定元素的最值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }
        //确定每个元素放入桶的编号并放进去
        for (int i : arr) {
            //加1是为了保证index< A.length，防止程序抛出IndexOutofBoundsEx;
            int index = (int) ((i - min) / (max - min + 1.0) * n);
            //放入对应的桶中
            Blist.get(index).add(i);
        }

        //3.桶内排序
        for (int i = 0; i < Blist.size(); i++) {
            java.util.Collections.sort(Blist.get(i));
        }

        //4.合并数据
        int j = 0;
        for (ArrayList<Integer> A : Blist) {
            for (int i : A) {
                arr[j++] = i;
            }
        }

    }

}
