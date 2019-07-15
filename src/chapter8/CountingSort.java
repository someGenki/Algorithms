package chapter8;

import java.util.Arrays;

/**
 * 2019年7月13日
 * 算法导论第八章:计数排序
 * 假设输入是一个数组arr[1~n],arr.length=n,保证数据值在0~k之间;
 * 还需要两个数组:result[1~m]存放排序好的输出,temp[0...k]提供临时存储空间
 */

public class CountingSort {
    private static int[] arr1 = {-1, 2, 5, 4, 6, 1, 25, 8, 9, 31, 3, 7, 22};
    private static int[] arr2 = {0, 2, 5, 3, 0, 2, 3, 0, 3};

    public static void main(String[] args) {
        int[] result = new int[arr2.length];
        Counting_Sort(arr2, result, 33);
        System.out.println(Arrays.toString(result));
    }

    private static void Counting_Sort(int[] arr, int[] result, int k) {
        int[] temp = new int[k + 1];
        for (int i = 0; i <= k; i++)
            temp[i] = 0;//初始化
        for (int j = 1; j < arr.length ; j++)
            temp[arr[j]]++;//每个元素记录出现次数
        for (int i = 1; i <= k; i++)
            temp[i] = temp[i] + temp[i - 1];//记录i前面有多少元素是小于等于 i 的
        /*
         * 把每个元素arr[j]放到它在输出数组result的正确位置
         * 上一步算出了每个值都多少个值是小于等于它的,如果temp[arr[j]]==7,就在result[7]上放arr[j]同时temp[arr[j]--,不用担心没位置的问题
         * 因为不可能每个元素都是互异的,所以,将每一个值arr[j]放入数组result后,都要将temp[arr[j]]的值减去1
         * 这样,当遇到下一个等于arr[j]的元素,该元素可以被直接放到输出数组中arr[j]的前一个位置上
         */
        for (int j = arr.length - 1; j > 0; j--) {
        //for (int j = 1; j <arr.length; j++) {  //改成从头开始也是对的
            result[temp[arr[j]]] = arr[j];//在正确的位置上放上元素
            temp[arr[j]]--;
        }

    }
}
