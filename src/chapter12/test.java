package chapter12;

public class test {
    public static void main(String[] args) {
        myBST mb=new myBST();
        for (int i = 0; i < 10; i++) {
            mb.insert(i);
        }
        mb.inOrderTreeWalk(mb.root);
    }
}
