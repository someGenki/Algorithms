package chapter10;

/**
 * 2019年7月16日
 * 算法导论第十章:链表(单向,带有头节点)
 * 10.3粗略的阅览了下,差不多算是没看
 */
public class myLinkedList {
    public class myNode {//节点结构体
        int data;//数据域
        myNode next;//指针域

        public myNode(int data) {
            this.next = null;
            this.data = data;
        }
    }

    myNode head = new myNode(Integer.MAX_VALUE);//头节点,数据域无用
    myNode tail = head;//记录最后一个用节点,方便尾插法

    //给链表添加元素(放在末尾)(尾插法)
    public void addNode(int data) {
        myNode newNode = new myNode(data);
        tail.next = newNode;
        tail = tail.next;
    }

    //给链表添加元素(放在前端)(头插法)
    public void addNodeOnHead(int data) {
        if (head.next != null) {
            myNode newNode = new myNode(data);
            newNode.next = head.next;
            head.next = newNode;
        } else
            addNode(data); //保证可以记录到链表的尾节点
    }

    //链表的删除:将元素x从链表中删除(删除链表中所以含有x的节点)
    public void deleteNode(int x) {
        myNode t = head;
        myNode p = head.next;
        while (p != null) {
            if (p.data == x) {
                t.next = p.next;
                p = p.next;
                continue;
            }
            t = p;
            p = p.next;
        }
    }

    //在链表中搜寻(查找)值是key的节点,并返回这个节点
    //System.out.println(ml.searchNode(3).next.data);
    public myNode searchNode(int key) {
        myNode x = this.head;
        while (x != null && x.data != key) {
            x = x.next;
        }
        return x;
    }

    //翻转一个链表 在遍历链表的时候，修改当前结点的指针域的指向，让其指向前驱结点。
    public void ReverseLinkedList() {
        myNode curNode = head.next;
        myNode preNode = null;
        myNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next; // 记录当前节点的后一个节点
            curNode.next = preNode; // 如果是第一个节点,让它的next=null;
            preNode = curNode;     // 然后当前节点变成它next节点要用的前驱节点
            curNode = nextNode;   // 改变当前节点
        }                        //
        head.next = preNode;    //
        FindTailNode();        //翻转后要更新尾节点
    }

    // 更新尾节点
    public void FindTailNode() {
        myNode t = head.next;
        while (t != null) {
            tail = t;
            t = t.next;
        }
    }

    //正向输出链表
    public void showList() {
        for (myNode p = head.next; p != null; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    //反向输出链表 用递归实现
    public void ReserveShowList() {
        RecurveShowList(head.next);
        System.out.println();
    }

    //反向输出的子函数
    public void RecurveShowList(myNode node) {
        if (node != null) {
            RecurveShowList(node.next);
            System.out.print(node.data + " ");
        }
    }

}
