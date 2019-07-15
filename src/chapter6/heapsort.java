package chapter6;

import java.util.Arrays;

/**
 * 算法导论第六章:堆排序
 * MaxHeapify:维护最大堆性质的关键
 * BuildMaxHeap:将无序的输入数据数组中构造一个最大堆
 * HeapSort:对一个数组经行原址排序
 * Insert,extractMAX,increaseKey,maximum:利用队形成一个优先队列
 * 优先队列
 * Insert(s,x);把元素x插入到集合s中
 * Maximum(S):返回s中具有最大关键字(key)的元素
 * ExtractMax(S):去掉并返回S中具有最大key的元素
 * IncreaseKey(S,x,k):将元素x的key值增加到k(假设k不小于原来x的key) (如果是最小优先队列,变成DecreaseKey)
 */
public class heapsort {
    private static int[] arr1 = {0, 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    private static int[] arr2 = {0, 4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    private static final int INF = 99999999;

    //ps:这是一个最大堆
    static class Heap {
        int[] arr;
        int len;//数组元素个数
        int size;//堆的大小
        int capacity;//记录原来的长度
        int t;//临时变量

        public Heap(int[] arr, int capacity) {
            this.capacity = capacity;
            this.len = capacity - 1;
            this.size = 0;
            this.arr = new int[capacity];
            System.arraycopy(arr, 0, this.arr, 0, capacity);
        }

        /**
         * 最大堆从i号位置下滤
         * 让arr[i]的值在最大堆中逐级下降,从而使下标i为根节点的子树重新遵循最大堆的性质
         * 如果要保持左小右大,可以在交换完成后判断节点i的左右子树的大小再进行一次交换
         */
        public void MaxHeapify(int[] arr, int i) {
            int large;//记录最大叶子节点的位置
            int l = i * 2, r = i * 2 + 1;
            //下面两个if是找到更大的叶子节点
            if (l <= size && arr[l] > arr[i]) {
                large = l;
            } else large = i;
            if (r <= size && arr[r] > arr[large]) {
                large = r;
            }
            //如果large不是叶子节点,以i为节点的子树已经是最大堆了不交换,否则则交换

            if (large != i) {
                t = arr[i];
                arr[i] = arr[large];
                arr[large] = t;
                //在交换后,下标为large的节点的值是原来的arr[i],于是以该节点为根的子树可能会违反最大堆的结构,所以进行递归调用
                MaxHeapify(arr, large);
                //要是不用递归,就从最后一个父节点开始下滤(i=heap.size/2)
            }
        }

        /*将数组构建成最大堆*/
        public void BuildMaxHeap() {
            size = len;
            for (int i = len / 2; i > 0; i--)
                MaxHeapify(arr, i);
        }

        /**
         * 堆排序
         * 不断的取出堆顶元素放在arr数组末尾(从后往前填补),并再每次取出后用MaxHeapify调整下,直到规模从n-1~2
         */
        public void HeapSort() {
            BuildMaxHeap();
            for (int i = len; i >= 2; i--) {
                t = arr[1];
                arr[1] = arr[i];
                arr[i] = t;
                size--;
                MaxHeapify(arr, 1);
            }
            Arrays.toString(arr);
        }

        //相当于delete
        public int HeapExtractMax() {
            if (size < 1) {
                return -1;
            }
            int max = arr[1];
            arr[1] = arr[size--];
            MaxHeapify(arr, 1);
            return max;
        }

        //不相当于insert!是把arr[i]号的值增加到key,同时调整堆
        public void HeapIncreaseKey(int i, int key) {
            if (key < arr[i]) {
                System.out.println("new key is smaller than current key ");
            }
            arr[i] = key;
            while (i > 1 && arr[i / 2] < arr[i]) {
                t = arr[i];
                arr[i] = arr[i / 2];
                arr[i / 2] = t;
                i = i / 2;
            }
        }

        //最大堆插入
        public void MaxHeapInsert(int key) {
            size++;
            arr[size] = -INF;
            HeapIncreaseKey(size, key);
        }

    }

    public static void main(String[] args) {
        Heap heap = new Heap(arr1, arr1.length);
        System.out.println("排序前:"+Arrays.toString(heap.arr));
        heap.HeapSort();
        System.out.println("排序后:"+Arrays.toString(heap.arr));
    }
}
