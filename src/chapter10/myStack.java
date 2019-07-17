package chapter10;

/**
 * 2019年7月16日
 * 算法导论第十章:栈
 */
public class myStack {
    private int[] base;
    private int top;
    private int capacity;

    public myStack(){
        this.capacity=1024;
        this.base=new int[this.capacity];
        this.top=-1;
    }
    public myStack(int size){
        this.capacity=size;
        this.base=new int[size];
        this.top=-1;
    }
    //判断是否为空
    public boolean empty(){
        if(this.top==0)
            return true;
        else return false;
    }
    //入栈
    public void push(int x){
        base[++top]=x;
    }
    //出栈
    public int pop(){
        if(top>=0){
            return base[top--];
        }
        else {
            System.out.println("underflow");
            return -1;
        }
    }
    //把栈里的东西都出栈
    public void clear(){
        while(top>=0){
            pop();
        }
    }
}
/*
    myStack ms=new myStack();
    for (int i = 1; i <4 ; i++) {
        ms.push(i);
    }
    for (int i = 1; i <4; i++) {
        System.out.print(ms.pop()+" ");
    }
    ms.clear();
 */
