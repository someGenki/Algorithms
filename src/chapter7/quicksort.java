package chapter7;

import java.util.Arrays;

/**
 * 2019年7月13日
 * 算法导论第七章:快速排序
 * 与归并排序一样运用了分治思想:
 * 1.分解:把数组A[p~r]分解成两个子数组(q为中轴),是的左边的每一个元素(A[p~q-1])都小于A[q],A[q]也小于A[q+1~r]的每一个元素,其中计算下标q也是划分过程的一部分
 * 2.解决:递归调用快速排序,对于q左右两边的子数组进行排序
 * 3.合并:因为子数组都是原址排序,不需要合并
 */
public class quicksort {
    //都不考虑arr[0],初始调用qSort中high的参数:arr1.length 要减去1
    private static int[] arr1 = {-1, 3, 2, 5, 4, 6, 1, 77, 8, 9, 81, 0, 7, 22};
    private static int[] arr2 = {-1, 2, 8, 7, 1, 3, 5, 6, 4};

    public static void main(String[] args) {
        qSort(arr2, 1, arr2.length - 1);
        System.out.println(Arrays.toString(arr1));
    }



    /**
     * Partition维护4个区域:low~i,i~j,j~high
     * 当A[j]>x,循环体唯一做的就是j++
     * 当A[j]<=x,i++,蒋欢arr[i]和arr[j],再j++,因为进行了交换,arr[i]<=x
     */
    private static int Partition(int[] arr, int low, int high) {
        int t;//交换过程中的临时变量
        int x = arr[high];//最后一个元素作为主元,并围绕它来划分子数组
        int i = low - 1;    //中轴.书P96,书中所指的被添加到元素值较小/较大的部分是值 下标i的左/右部分
        for (int j = low; j < high; j++) {//遍历整个数组
            if (arr[j] <= x) {
                i++;//有比主元x小,i的值当然要增加,然后交换值,小的放在主元位置(i)
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        t = arr[i + 1];//+1并交换是为了放在中轴位置,是左小右大
        arr[i + 1] = arr[high];
        arr[high] = t;
        i++;
        return i;
    }

    private static void qSort(int[] arr, int low, int high) {
        if (low < high) {
            int q = Partition(arr, low, high);
            System.out.println("主轴是:  " + arr[q] + "\t  " + Arrays.toString(arr));
            qSort(arr, low, q - 1);
            qSort(arr, q + 1, high);
        }
    }
}
