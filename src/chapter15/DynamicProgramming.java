package chapter15;

import java.util.Arrays;

/**
 * 2019年7月18日
 * 算法导论第十五章:动态规划
 * 动态规划用于子问题重叠的情况,即不同的子问题具有公共的子子问题.
 * 在这种情况下分治算法会重复的求解公共子问题(大一下做过的pell数列题目)<br>而动态规划算法对每个子子问题只求解一次,将其保存在一个表格中,避免重复计算
 * (有两道练习题没做)
 * ------------------------------------------------------------
 * <p>
 * 4个步骤来设计一个动态规划算法
 * __1.刻划一个最优解的结构特征
 * __2.递归地定义最优解的值
 * __3.计算最优解的值,通常采用自底向上的方法
 * __4.利用计算出的信息构造一个最优解
 * </p>
 *
 */
public class DynamicProgramming {
    public static int[] steel = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};


    public static void main(String[] args) {
        System.out.println("cutRod");
        System.out.println(cutRod(steel, 4));
        System.out.println(memoizedCutRod(steel, 10));
        System.out.println(bottomUpCutRod(steel, 4));
        int[] bestResult=new int[20];
        int[] bestFirstLength=new int[20];
        extendedBottomUpCutRod(steel,10,bestResult,bestFirstLength);
        System.out.println(Arrays.toString(bestResult));
        System.out.println(Arrays.toString(bestFirstLength));
        System.out.println();
        printCutRodSolution(steel,9,bestResult,bestFirstLength);
        printCutRodSolution(steel,8,bestResult,bestFirstLength);
        printCutRodSolution(steel,7,bestResult,bestFirstLength);
    }


    //输出长度为n的钢条的完整切割方案
    public static void printCutRodSolution(int[] p,int n,int[] r,int[] s){
        extendedBottomUpCutRod(steel,10,r,s);
        System.out.print("切"+n+"段,你应该这么切...");
        while(n>0){
            System.out.print(s[n]+" ");
            n=n-s[n];
        }
        System.out.println();
    }

    /**
     *  保存每个子问题求出的最优解和对应的第一段钢条切割长度
     * @param p 同下
     * @param n 同下
     * @param r 最优解数组
     * @param s 最优解对应的第一段钢条的切割长度sj
     */
    public static void extendedBottomUpCutRod(int[] p,int n,int[] r,int[] s){
        r[0]=0;
        for(int j=1;j<=n;j++){
            int q=-Integer.MAX_VALUE;
            for (int i = 1; i <=j; i++) {
                if(q<p[i]+r[j-i]){
                    q=p[i]+r[j-i];
                    s[j]=i;
                }
            }
            r[j]=q;
        }
        return;
    }



    /**
     * 自底向上法(bottom-up method)
     * 这种方法一般需要恰当的定义子问题"规模"的概念,使得任何子问题的求解都只 依赖 "更小的"子问题的求解.
     * 因而我们可以将子问题按规模排序,按由小到大的顺序进行求解.
     * 当求解莫格子问题时,她所依赖的哪些更小的子问题都已经求解完毕,结果已经保存.
     * 过程依次求解规模为j=0,1,...n的子问题
     *
     *
     */
    public static int bottomUpCutRod(int[]p, int n){
        int[] r = new int[n + 1];
        r[0]=0;//长度为0没有收益.
        int q;
        for (int j = 1; j <=n; j++) {
            q=-Integer.MAX_VALUE;
            for (int i = 1; i <=j; i++) {
                //当j==2 q1=1+r[1]=2     q2=5+r[0]=2; 所以r[j]=5;
                //当j==3 q1=1+r[2]=6    q2=2+r[1]=3;  q3=8+r[0]=8 所以r[j]=8;
                //...
                q=Integer.max(q,p[i]+r[j-i]);//直接访问r[j-i]获得规模为j-i的子问题的解,进而不必递归调用
            }
            r[j]=q;//不断的求出r[j]的最优解
        }
        return r[n];
    }



    /**
     * 带备忘的自顶向下法(top-down with memoization)
     * 过程中会保存每个子问题的解.就是保存过直接用,没保存过的普通的算法去算
     * 用无限小填充数组,因为切割田端的收益总是非负值.
     * @param p 同下
     * @param n 同下
     * @return 同下
     */
    public static int memoizedCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        Arrays.fill(r, -Integer.MAX_VALUE);
        return memoizedCutRodAux(p,n,r);
    }
    private static int memoizedCutRodAux(int[] p, int n, int[] r) {
        if (r[n] >= 0)
            return r[n];
        int q;
        if (n == 0)
            q = 0;
        else {
            q = -Integer.MAX_VALUE;
            for(int i=1;i<=n;i++)//计算最优解
                q=Integer.max(q,p[i]+memoizedCutRodAux(p,n-i,r));
        }
        r[n]=q;//保存最优解
        return q;
    }



    /**
     * 求钢铁切割的最大收益问题,不同的切割方案有不用的收益,这个函数采用一种直接自顶向下的递归方法(会又重复计算)
     * ->将钢条从左边割下长度为i的一段,只对右边剩下的长度为n-i的一段继续进行切割(为什么就计算右边,因为右边会递归地求解出最优解)
     * ->可描述为:第一段长度为n,收益为pn,剩余部分长度为0,对应的收益为r0=0.
     * ->得到更简洁的公式rn=max(pi+r(n-i));
     * 例如n=4时,i=2,p[i]=5,cutRod(p,2)返回5 通过max函数返回了5+5
     * -->cutRod(p,2):两次for循环 得到 1+1 和 5+0
     *
     * @param p 钢铁价格数组
     * @param n 钢铁的长度
     * @return 最大收益
     */
    public static int cutRod(int[] p, int n) {
        if (n == 0)
            return 0;
        int q = -Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i));
        }
        return q;
    }


}