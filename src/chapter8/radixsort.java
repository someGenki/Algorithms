package chapter8;

import java.util.Arrays;

/**
 * 2019年7月13日
 * 算法导论第八章:基数排序
 * 基数排序的总体思路就是将待排序数据拆分成多个关键字进行排序，也就是说，基数排序的实质是多关键字排序
 * 基本思想:将所有待比较的元素数值（正整数）统一为同样的数位长度，数位较短的元素在前面补零占位。
 * 然后，从最低位开始，依次进行每一趟排序，直到最高位排序完成，则整个数列就成为一个有序的序列。
 * 参考:https://www.cnblogs.com/protected/p/6603536.html and https://blog.csdn.net/qq_34801169/article/details/81412446
 */
public class radixsort {
    private static int[] arr1 = {16, 21, 5, 49, 33, 456, 327, 56, 65, 234};

    public static void main(String[] args) {
        print(arr1, arr1.length);
        radixSort(arr1, 10, 3);
        print(arr1, arr1.length);


    }

    /**
     * @param arr   待排序数组
     * @param radix 进制数
     * @param d     最大数的位数
     */
    public static void radixSort(int[] arr, int radix, int d) {
        //缓存数组
        int[] temp = new int[arr.length];
        //buckets 用于记录待排序元素的信息
        int[] buckets = new int[radix];
        for (int i = 0, rate = 1; i < d; i++) {
            //重置buckets[],开始统计下一个关键字
            Arrays.fill(buckets, 0);
            //将arr中的元素复制到temp[]
            System.arraycopy(arr, 0, temp, 0, arr.length);

            //计算每个待排序数据的子关键字出现次数 (从最低位开始到最大位)
            for (int j = 0; j < arr.length; j++) {
                int subKey = (temp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                //记录j前面有多少元素是小于等于 j 的
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            //按子关键字对指定数据进行类似计数排序,放在该放的位置,同时计数减少
            //比如234, subKey是4,buckets[4]是2,就放在arr[1]的位置
            for (int m = arr.length - 1; m >= 0; m--) {
                int subKey = (temp[m] / rate) % radix;
                arr[--buckets[subKey]] = temp[m];
            }
            rate *= radix;
        }
    }


    /**
     * 求排序元素的最大位数
     *
     * @param t
     * @return
     */
    public static int getNumberCount(int t) {
        int count = 1;
        t = t / 10;
        while (t != 0) {
            count++;
            t /= 10;
        }
        return count;
    }

    //筛选出最大元素方便确定位数
    public static int getMax(int[] a, int n) {
        a[0] = a[1];
        for (int i = 2; i <= n; i++) {
            if (a[i] > a[0]) {
                a[0] = a[i];
            }
        }
        return a[0];
    }

    public static void print(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }
}
