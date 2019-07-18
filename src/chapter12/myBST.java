package chapter12;
/**
 * 2019年7月17日
 * 算法导论第12章:二叉树(有parent指针)
 * 删除没看..红黑树也没看..
 */
public class myBST {
    //定义树节点类
    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode p;//父节点 parent

        public TreeNode(int data) {
            this.data = data;
            this.p=this.left = this.right = null;
        }
    }

    TreeNode root = null;

    //迭代插入
    public void insert(int data){
        TreeNode z=new TreeNode(data);
        TreeNode y=null;
        TreeNode x=this.root;
        while(x!=null){
            y=x;
            if(data<x.data)
                x=x.left;
            else x=x.right;
        }
        z.p=y;
        if(y==null) this.root=z;        //如果树是空树,z作为树根
        else if(data<y.data) y.left=z;//比子根y小放左边
        else y.right=z;             //比子根y大放右边
    }




    //中序遍历
    public void inOrderTreeWalk(TreeNode root) {
        if (root != null) {
            inOrderTreeWalk(root.left);
            System.out.print(root.data+" ");
            inOrderTreeWalk(root.right);
        }
    }

    //二叉树的递归查找   初次调用时参数要给树根
    public TreeNode treeSearch(TreeNode root, int key) {
        //如果节点存在 返回节点的指针,否则就返回空指针null
        if (root == null || key == root.data)
            return root;
        if (key < root.data) {
            return treeSearch(root.left, key);
        } else {
            return treeSearch(root.right, key);
        }
    }

    //二叉树的迭代查找  返回指向关键字key的节点的指针
    public TreeNode itertativeTreeSearch(int key){
        TreeNode x=root;
        while(x!=null && key!=x.data){
            if (key<x.data) x=x.left;
            else x=x.right;
        }
        return x;
    }

    //二叉树的最小值的节点,不给参数就默认从树根找起
    public TreeNode treeMinimum(){
        TreeNode x=root;
        while(x.left!=null)
            x=x.left;
        return x;
    }
    public TreeNode treeMinimum(TreeNode x){
        while(x.left!=null)
            x=x.left;
        return x;
    }

    //二叉树的最大值的节点
    public TreeNode treeMaximum(){
        TreeNode x=root;
        while(x.right!=null)
            x=x.right;
        return x;
    }
    public TreeNode treeMaximum(TreeNode x){
        while(x.right!=null)
            x=x.right;
        return x;
    }


    /**
     * 前驱节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的前一个节点为该节点的前驱节点；(是大于x.key的最小关键字的结点。)
     * 后继节点：对一棵二叉树进行中序遍历，遍历后的顺序，当前节点的后一个节点为该节点的后继节点；(是小于x.key的最大关键字的结点。)
     * @return 返回一棵二叉搜索树中节点x的后继; 如果x是这颗树中最大的关键字,则返回null
     * 如果x没有右子树,就一直向上找,知道遇到一个其parent有左孩子的节点(书164)(关键字13的节点没有右子树,因此他的后继节点是最低的祖先并且其左孩子也是一个祖先
     */
    public TreeNode treeSuccessor(TreeNode x){
        if(x.right!=null)
            return treeMinimum(x.right);
        TreeNode y=x.p;
        while(y!=null && x==y.right){
            x=y;
            y=y.p;
        }
        return y;
    }
}
