package chapter10;

/*
    2019年7月16日
    算法导论第十章:队列(这是循环队列)
 */
public class myQueue {
    private int[] base;
    private int front, rear;
    private int size;

    public myQueue() {
        this.size = 1024;
        this.base = new int[this.size];
        this.front = this.rear = 0;
    }

    public myQueue(int size) {
        this.size = size + 1;
        this.base = new int[size];
        this.front = this.rear = 0;
    }

    public boolean IsFull() {
        if ((rear + 1) % size == front)
            return true;
        else return false;
    }

    public boolean IsEmpty() {
        if (front == rear)
            return true;
        else return false;
    }
    public int ShowFront(){
        System.out.println(base[front]);
        return base[front];
    }
    public int ShowRear(){
        System.out.println(base[rear-1]);
        return base[rear-1];
    }
    //从队尾入队
    public void Enqueue(int key) {
        if (IsFull() == false) {
            base[rear] = key;
            rear = (rear + 1) % size;
        } else {
            System.out.println("Queue Full!");
        }
    }

    //从队头出队
    public int Dequeue() {
        if (IsEmpty()) {
            System.out.println("Queue Empty!Can't Dequeue!");
            return -1;
        } else {
            int tmp = base[front];
            front = (front + 1) % size;
            return tmp;
        }
    }

    //从队尾出队
    public int DequeueOnTail() {
        if (IsEmpty()) {
            System.out.println("Queue Empty!Can't Dequeue!");
            return -1;
        }else {
            int tmp=base[rear--];
//            rear=(rear-1);
            return tmp;
        }
    }

}
/*
    myQueue mq=new myQueue();
    for (int i = 1; i < 4; i++) {
        mq.Enqueue(i);
    }
    for (int i = 1; i < 4; i++) {
        System.out.print(mq.Dequeue()+" ");
    }
 */
