package chapter2;

/**coding:UTF-8
 * 算法导论第二章:插入排序,分析算法,设计算法,归并排序
 * 循环不变性用来证明算法的正确性(初始化,保持,终止) P10
 * ps:分析算法没仔细看...XD
 * 分治法-递归:将原有问题分解成几个规模较小但类似于原问题的子问题,递归地求解这些子问题,然后在合并这些子问题的解来建立原问题的解
 * 分治模式在每层递归时有三个步骤:
 * --分解 原问题为若干子问题,这些子问题是原问题规模较小的实例.
 * --解决 这些子问题,递归地求解各个子问题.然而,若子问题的规模足够小,则直接求解.
 * --合并 这些子问题的解成原问题的解
 * 归并排序 算法完全遵循分治模式.
 * --分解:分解带排序的n个元素的序列成各具n/2个元素的子序列
 * --解决:使用归并排序递归地排序两个子序列
 * --合并:合并两个已排序的子序列已产生已排序的答案
 * <p>
 * 当待排序的序列长度为1时,递归"开始回升",这种情况不做任何工作,因为长度为1的每个序列都已排好序
 * 归并的关键是"合并",调用MERGE(Arr,p,q,r)来完成合并,p q r是数组下标,满足p<=q<r,n=r-p+1
 * 假设Arr[p...q]和Arr[q+1,r]都是已经排好序的.合并形成Arr[p..r]
 * 扑克牌例子:桌面上有两堆已排序的扑克牌,每堆都排序好(最小的在顶上)
 * --基本步骤:在两个扑克堆顶中选较小的一张,从堆中拿开放入输入输出堆.
 * --重复这个步骤,直到一个堆为空,把剩下的堆中的牌都放入输出堆
 * </p>
 * //别人的学习笔记 https://www.cnblogs.com/alan-forever/p/3361149.html
 */
public class InsertionSort {
    private static int[] arr1 = {-1, 3, 2, 5, 4, 6, 1, 77, 8, 9, 81, 0, 7, 22};
    private static int[] arr2 = {-1, 3, 2, 5, 4, 6, 1, 66, 8, 9, 81, 0, 7, 11};
    // 数组从1开始用
    private static final int INF = 99999999;

    public static void main(String[] args) {
        insertionSort(arr1);
        for (int i = 1; i < arr1.length; i++) {
            System.out.print(arr1[i] + "  ");
        }
        System.out.println("!");

        MergeSort(arr2, 1, arr2.length - 1);
        for (int i = 1; i < arr2.length; i++) {
            System.out.print(arr2[i] + "  ");
        }
        System.out.println("!");
    }

    //插入排序
    private static void insertionSort(int arr[]) {
        //像从桌上拿扑克牌,一个一个拿起来,并在拿起来的过程中,从后向前比较,找到比前者比他小,后者比他大的位置,把之后的往后挪(代码的话就是每比较一次往后挪动一次)空个位置
        //最后插入那个位置
        //对于前0~i个元素,构成了当前排序好的手牌,剩余的子数组是在桌子上的牌堆
        int key, j;
        for (int i = 2; i < arr.length; i++) {
            key = arr[i];//拿一个扑克牌
            j = i - 1;//遍历手牌
            while (j > 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;//插入手牌
        }
    }

    //归并排序 P20
    private static void MergeSort(int arr[], int p, int r) {
        if (p < r) {//若p>=r,则该子数组最多只有一个元素,所以已经排序好了
            int q = (p + r) / 2;
            MergeSort(arr, p, q);
            MergeSort(arr, q + 1, r);
            MERGE(arr, p, q, r);
        }
    }

    //归并排序的合并操作
    private static void MERGE(int arr[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 4], R = new int[n2 + 4];
        for (int i = 1; i <= n1; i++) {
            L[i] = arr[p + i - 1];
        }
        for (int i = 1; i <= n2; i++) {
            R[i] = arr[q + i];
        }
        L[n1 + 1] = INF;//设置哨兵,当现实一张为INF的牌,它不可能为较小的牌
        R[n2 + 1] = INF;//同时因为循环次数的限制条件,不会出现两个哨兵相比较
        int i = 1, j = 1;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
        }
    }

}
