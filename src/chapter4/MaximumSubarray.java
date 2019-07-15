package chapter4;

/**
 * 2019年7月11日
 * 算法导论第四章:分治策略 最大子数组问题
 * 1.子数组i~j有三种情况,最大子数组也就会有三种情况(中点左边,中点右边和跨过了中点)
 * 2.所以low~high的一个最大子数组必然是完全位于三种情况中的最大者
 * 3.递归地求解中点左边和中点右边的最大子数组,问题不变,规模更小,然后再选择三种情况中的最大者
 * 4.求跨越中点的最大子数组不是原问题规模更小的实例(存在限制:必须跨越中点)
 * 5.任何跨越中点的子数组都由两个子数组A[i,mid]和A[mid+1,j]组成,只需找到两边的最大子数组并合并即可
 */
public class MaximumSubarray {
    private static final int INF = 99999999;//定义无穷大:∞
    private static int[] arr1 = {0, 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};//[8~11]和是43
    private static int[] arr2 = {0, -111, -3, -25, -20, -33, -16, -23, -18, -20, -7, -1, -5, -22, -15, -4, -7};//所有都是负数变成找最小值

    public static void main(String[] args) {
        int[] Info = FindMaximumSubarray(arr1, 1, arr1.length - 1);
        System.out.println("最大子数组的起始是:" + Info[0] + " 终点是:" + Info[1] + " 和是:" + Info[2]);
        Info = FindMaximumSubarray(arr2, 1, arr2.length - 1);
        System.out.println("最大子数组的起始是:" + Info[0] + " 终点是:" + Info[1] + " 和是:" + Info[2]);
    }

    /**
     * 初始调用会求出arr[1~arr.length]的最大子数组
     * arr至少包含两个元素,则左右部分各至少有一个元素
     * 从FindMaxCrossingSubarray开始到结束 完成合并工作
     *
     * @return 下标元组和最大子数组中的值之和
     */
    private static int[] FindMaximumSubarray(int arr[], int low, int high) {
        int[] Info;
        if (high == low) {
            Info = new int[3];
            Info[0] = low;
            Info[1] = high;
            Info[2] = arr[low];
            return Info;
        } else {
            int mid = (low + high) / 2;
            int left_low, left_high, left_sum;
            int right_low, right_high, right_sum;
            int cross_low, cross_high, cross_sum;
            //接收多个返回值
            Info = FindMaximumSubarray(arr, low, mid);
            left_low = Info[0];
            left_high = Info[1];
            left_sum = Info[2];
            Info = FindMaximumSubarray(arr, mid + 1, high);
            right_low = Info[0];
            right_high = Info[1];
            right_sum = Info[2];
            Info = FindMaxCrossingSubarray(arr, low, mid, high);
            cross_low = Info[0];
            cross_high = Info[1];
            cross_sum = Info[2];
            Info = new int[3];
            //先用数组保存三个信息,再返回数组
            if (left_sum >= right_sum && left_sum >= cross_sum) {
                Info[0] = left_low;
                Info[1] = left_high;
                Info[2] = left_sum;
                return Info;
            } else if (right_sum >= left_sum && right_sum >= cross_sum) {
                Info[0] = right_low;
                Info[1] = right_high;
                Info[2] = right_sum;
                return Info;
            } else {
                Info[0] = cross_low;
                Info[1] = cross_high;
                Info[2] = cross_sum;
                return Info;
            }
        }
    }

    /**
     * 返回跨越中点的最大子数组的边界,并返回最大子数组的和
     */
    private static int[] FindMaxCrossingSubarray(int arr[], int low, int mid, int high) {
        int sum;//保存A[i~mid]中的所有值的和
        int[] crossingInfo = new int[3];
        int leftSum = -INF;//保存目前最大和
        int maxLeft = 0;
        sum = 0;
        //求出左半部分最大子数组
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = -INF;
        int maxRight = 0;
        sum = 0;
        //求出右半部分的最大子数组
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        crossingInfo[0] = maxLeft;
        crossingInfo[1] = maxRight;
        crossingInfo[2] = leftSum + rightSum;
        return crossingInfo;
    }
}
