package chapter10;

import com.sun.source.tree.Tree;

/**
 * 2019年7月17日
 * 算法导论第10章:二叉树(无parent指针)
 */
public class myBinarySearchTree {
    //定义树节点类
    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    TreeNode root = null;


    //二叉树的插入
    public void insert(int data) {
        this.root = subInsert(this.root, data);
    }

    //二叉树插入的子函数 做成子函数是方便树根的相连(树根的递归返回)
    private TreeNode subInsert(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
        } else {
            if (data < root.data)
                root.left = subInsert(root.left, data);
            else if (data > root.data)
                root.right = subInsert(root.right, data);

        }
        return root;
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
     * @return 返回一棵二叉搜索树中节点x的后继; 如果x是这颗树中最大的关键字,则返回nu'l'l
     * https://www.cnblogs.com/Renyi-Fan/p/8252227.html shift! 书上的要parent指针,还是看他的吧 先不完成了
     */
    private   TreeNode treeSuccessor(TreeNode x){
            if(x.right!=null)
                return treeMinimum(x.right);
            TreeNode y=x;
            return y;
    }
}
