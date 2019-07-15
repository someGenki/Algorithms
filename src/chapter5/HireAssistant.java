package chapter5;

/**
 * 这随便写的,概率还没好好学呢
 */
public class HireAssistant {
    public static void main(String[] args) {
            Hire_Assistant(66);

    }
    private static void Hire_Assistant(int n){
        int best=0;
        for (int i = 1; i < n; i++) {
           int ok= Interview(i);
            if(ok==1){
                best=i;
                Hire(i);
            }
        }
    }
    private static int Interview(int n){
        if(n%55==0) return 1;
        else return 0;
    }
    private static void Hire(int n){
        System.out.println("hire "+n+" ");
    }
}
