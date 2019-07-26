package chapter18;

/**
 * 2019年7月23日
 * 算法导论第十八章:B树
 * 18.1:B树的定义(根为T.root):
 * 1.每个结点有一下性质:
 * --a. x.n,当前存储在结点x中的关键字个数
 * --b. x.n个关键字本身x.key1,x.key2,...,x.key(下标x.n),以升序存放
 * --c. x.leaf,一个布尔值,如果x是叶结点则为 TRUE;如果x为内部结点,则为 FALSE
 * 2.每个内部结点x还包含x.n+1个指向起孩子的指针.如果是叶结点则没有
 * 3.关键字x.key(i)对存储在各个子树中的关键字范围加以分割:如果ki为任意一个存储在以x.ci为根的子树中的关键字
 * 那么k1<=x.key1<=k2<=x.key2<=...<x.key(x.n)<=kx.n+1
 * 4.每个叶结点具有相同的深度,即树的高度h
 * 5.每个结点包含的关键字个数有上界和下界.用一个被称为B树的最小度数的固定整数t>=2来表示这些界:
 * --a.除了根结点以外的每个结点必须至少有t-1个关键字.因此,除了根结点意外的每个内部结点至少有t个孩子.
 * --b.每个结点至多包含2t-1个关键字.当一个结点恰好有2t-1个关键字时,该结点是满的(full)
 * --c.t的值越大,B树的高度就越小
 * <p>
 * 2019年7月23日22点21分:B树的删除先不看了...
 */
public class Btree {
    private final static int MINT = 4; //最小度数

    private class node {
        int n;//当前结点关键字的个数
        boolean leaf;//是不是叶结点
        int[] key;//n个关键字本身
        node[] child;//指向孩子的指针

        public node() {
            this.key = new int[MINT * 3 + 1];
            this.child = new node[MINT * 3 + 1];
        }

    }


    //结点和下标组成的有序对(y,i)
    private class result {
        node reX;//树结点
        int sub;//x下标i

        public result(node x, int i) {
            this.reX = x;
            this.sub = i;
        }
    }


    node root = null;//树根

    //B树的创建 (B-TREE-CREATE)
    public Btree() {
        node x = new node();
        x.leaf = true;
        x.n = 0;
        System.out.println("DISK-WRITE(x)");
        this.root = x;
    }


    /**
     * B树的插入:b树的插入不像BST,相反,是将新的关键字插入一个已经存在的叶结点上.
     * 由于不能不能将关键字插入一个满的叶结点,故引入一个操作 ,将一个满的结点y(有2t-1个关键字)按其<中间关键字>y.key(t)分裂(split)为各含t-1个关键字的结点
     * 中间关键字被提升到y的父结点,以标识两棵新树的划分点.但是如果y的父结点也是满树,就必须在插入新的关键字之前将其分裂,最终满结点的分裂会沿着树向上传播.
     * 我们不是等到找出插入过程中实际要分裂的满结点时才做分离了.相反,当沿着树往下查找新的关键字所属的位置时,就分裂沿途遇到的每个满结点(包括叶结点本身)
     * 因此,每当要分裂一个满结点y时,就能确保它的父结点不是满的
     * ---------------------------------------------------------------------------------------------------------------------------------
     */
    public void bTreeInsert(node root, int k) {
        node r = root;
        if (r.n == 2 * MINT - 1) {
            //处理了根节点r为满的情况:原来的根节点被分裂成两半且接到了新结点S上
            node s = new node();
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[1] = r;
            bTreeSplitChild(s, 1);
            //通过调用bTreeInsertNonFull();来完成将关键字k插入以非满的根节点为根的树中.
            //bTreeInsertNonFull();在需要时沿树向下递归,在必要时调用分裂函数来保证任何时刻它所递归处理的结点都是非满的
            bTreeInsertNonFull(s, k);
        } else bTreeInsertNonFull(r, k);
    }


    /**
     * 辅助的递归过程将关键字插入结点x,要求假定在调用该函数时,x是非满的
     * 第一个if语句内:处理x是叶结点的情况,将关键字k插入x.如果不是,则必须将k出入以内部节点x为根的子树中适当的叶结点中去.
     * else while语句决定x的哪个子节点递归下降. else while if 检查是否递归降至一个满子结点上.
     * 若是,则用分裂函数将该子节点分裂为两个非满的孩子.后两行的if语句,确定向哪两个孩子中的哪一个下降是正确的-↓
     * ->(注意,在第十六行中i增加1后无需做硬盘读取,因为这种情况下递归会降至一个刚刚由分裂函数创建的子结点上)
     * 最后一行递归地将k插入合适的子树中.
     */
    public void bTreeInsertNonFull(node x, int k) {
        int i = x.n;
        if (x.leaf) {
            while (i >= 1 && k < x.key[i]) {
                x.key[i + 1] = x.key[i];//数组一部分元素后移
                i--;
            }
            x.key[i + 1] = k;
            x.n++;
            System.out.println("DISK-WRITE(x)");
        } else {
            while (i >= 1 && k < x.key[i]) {
                i--;
            }
            i++;
            System.out.println("DIST-READ(x,ci");
            if (x.child[i].n == 2 * MINT - 1) {
                bTreeSplitChild(x, i);
                if (k > x.key[i])
                    i++;
            }
            bTreeInsertNonFull(x.child[i], k);
        }
    }


    /**
     * 该过程把这个子节点分裂成两个,并调整x,使之包含多出来的child .
     * 要分裂一个满的根,首先要让根成为一个新的空根结点的孩子,这样才能使用bTreeSplitChild.
     * 树的高度会增加1.分裂是树长高的唯一 唯一 唯一 途径.
     * (11点35分:这里可能会出现OutOfIndex.)
     *
     * <p>
     * //1到9行创建结点z,并将y的t-1个关键字以及相应的t个孩子给他.
     * //第十行调整y的关键字个数.
     * //11到17行将z插入作为x的一个孩子,并提升y的中间关键字到x来分隔y和z,然后调整x的关键字个数.
     * //最后3行写出所有修改过的磁盘页面
     * </p>
     *
     * @param x 非满的内部结点x(假定在内存中)
     * @param i 一个使x.ci为满结点的下标i (x.ci也假定在内存中)
     */
    private void bTreeSplitChild(node x, int i) {
        //开始时,结点y有2t个孩子(2t-1关键字),在分裂后减少到t个孩子(t-1个关键字).
        node z = new node();
        node y = x.child[i];
        z.leaf = y.leaf;//z结点的leaf属性得根y的一样
        z.n = MINT - 1;

        //取走y右半部分(t-1个)的关键字(是奇数,y.n/2+1后的部分)
        for (int j = 1; j < MINT; j++) {
            z.key[j] = y.key[j + MINT];
        }

        //这部分干啥的?
        if (!y.leaf) { //如果y不是叶子结点,还要把y保存子结点的指针给z一半
            for (int j = 1; j <= MINT; j++) {
                z.child[j] = y.child[j + MINT];
            }
        }
        y.n = MINT - 1;//把一部分y的关键字拿走,y的关键字数也要更新

        //z成为x的新孩子,他在x的孩子表(x.child[])中进位于y之后.
        for (int j = x.n + 1; j > i; j--) {
            x.child[j + 1] = x.child[j];//往后移动元素,把x.child[i+1]位置空出来
        }
        x.child[i + 1] = z;

        // y的中间关键字上升到x中,成为分隔y和z的关键字
        for (int j = x.n; j >= i; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[i] = y.key[MINT];
        x.n++;//x多了个关键字,x.n要更新

        System.out.println("DISK_WRITE(Y)");
        System.out.println("DISK_WRITE(Z)");
        System.out.println("DISK_WRITE(X)");
    }


    /**
     * 顶层调用 bTreeSearch(T.root,k)
     *
     * @param x 指向某子树根结点x的指针
     * @param k 要搜索的关键字
     *          如果k在B树中,返回的是由结点y和使得y.key(i)的下标i组成的有序对(y,i);否则返回null;
     */
    public result bTreeSearch(node x, int k) {
        int i = 1;
        while (i <= x.n && k > x.key[i]) {
            i++;
        } //??,找出最小小标i,使得k<=x.key(i),若找不到,则置i为x.n+1.
        if (i <= x.n && k == x.key[i]) { //检查是否在范围内找到关键字
            return new result(x, i); //还要返回下标i 到时候在改改吧
        } else if (x.leaf) //是叶结点结束这次不成功的查找
            return null;
        else {  //递归搜索
            System.out.println("DISK-READ(x,ci)");
            return bTreeSearch(x.child[i], k);
        }
    }


    public static void main(String[] args) {
        System.out.println("你心里有没有点B树?");
    }

}
