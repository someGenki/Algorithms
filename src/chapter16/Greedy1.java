package chapter16;

import java.util.HashSet;
import java.util.Set;

/**
 * 2019年7月20日
 * 算法导论第十六章:活动选择问题
 * 一个调度竞争共享资源的多个活动问题,目标是选出一个最大的相互兼容的活动集合
 * 假定有一个n个活动的集合S={a1,a2....,an},使用同一间教室,且这个教室在某个时刻只能供一个活动使用.
 * 每个活动都有要给开始时间si和结束时间fi.如果被选中,任务ai发生在半开时间区间[si,fi)期间.
 * 如果两个活动的区间不重叠,则称他们是兼容的.也就是说若si>=fj or sj>=fi,则ai和aj是兼容的.
 * 在<b>在活动选择问题</b>中,希望选出一个最大兼容活动集.
 * <p>
 * 令Sij表示在ai结束之后开始,且在aj开始之前结束的那些活动的集合
 * <p>
 * 贪心选择:直觉告诉我们,应该选择S中最早结束的活动,因为它剩下的资源(教室)可供它之后 尽量多的活动使用.(前提:结束时间已按单调递增排序
 * 所以,我们找了a1,接着要寻找a1结束后开始的活动.
 * #贪心算法通常是自顶向下的设计:做出一个选择,然后求解剩下的那个子问题,而不是自底向上地求解出很多子问题,然后再做出选择.
 *
 * 练习1:不再选择最早结束的活动,而是选择最晚开始的活动  设计这样的算法
 * 练习2:区间图着色问题
 * 练习3:用动态规划解决0-1背包问题 (tips:当我们考虑是否将一个商品装入背包时,必须比较包含此商品的子问题的解与不包含它的子问题的解,然后才能做出选择)
 * ->商品 10-60$ 20-100$ 30-120$  背包容量 50
 */
public class Greedy1 {

    private static int[] start = {-1, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
    private static int[] end = {-1, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
    private static int[] set =new int[66];
    private static int cnt=0;
    private static Set sets=new HashSet();

    public static void main(String[] args) {
        System.out.print("greedy:");
//        recursiveActivitySelector(start,end,0,end.length-1);
        greedyActivitySelector(start,end);
        for (Object str : sets) {
            System.out.print(str+" ");
        }
    }

    public static void greedyActivitySelector(int[]s,int[] f){
        int n=s.length-1;
        sets.add(1);
        int k=1;//  记录最近加入集合A的活动的下标
        for(int m=2;m<=n;m++){
            if(s[m]>=f[k]){//检查是否兼容
                sets.add(m);
                k=m;
            }
        }
    }


    /**
     * @param s&f 活动的开始和结束时间
     * @param k   指出要求解的子问题Sk
     * @param n   问题规模
     * @return 一个最大兼容活动集
     * <p>
     * 为了方便初始化算法,添加一个虚拟活动a0,起结束时间是f0=0. 这样S0就是完整的活动集S
     */
    public static void recursiveActivitySelector(int[] s, int[] f, int k, int n) {
        int m = k + 1;  //↓,m的开始时间再k的结束前,不兼容
        while (m <= n && s[m] < f[k]) { //查早Sk中最早结束的活动.(循环查找ak+1,ak+2...an,直至找到第一个与ak兼容的活动am<br>此活动满足sm>=fk.)
            m++; //find the first activity in Sk to finish
        }
        if (m <= n) {
            //如果循环因为查找成功而结束,把m加入集合
            sets.add(m);
            recursiveActivitySelector(s,f,m,n);
        }
        //如果循环因为m>n而终止,意味已经检查完Sk的所有活动,并未找到与ak兼容的,所以返回空(不做)
    }

    /*
    https://blog.csdn.net/cyp331203/article/details/43199963
    https://blog.csdn.net/qq_32400847/article/details/51336300
    https://blog.csdn.net/qq_32400847/article/details/51336300
    https://www.cnblogs.com/hapjin/p/4477726.html
    https://liyiwen.iteye.com/blog/345796
     */
}
