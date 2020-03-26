package recursion;

public class Queen8 {
    int max=8;
    int[] array=new int[max];
    static int count=0;
    public static void main(String[] args) {
        Queen8 queen=new Queen8();
        queen.check(0);
        System.out.println("八个皇后的摆法种数："+count);
    }
    //放置第n个皇后时，检测是否和之前已经摆放的的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            /**
             * 1.array[i]==array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
             * 2.Math.abs(n-i)==Math.abs(array[n]-array[i] 表示判断第n个皇后和第i个皇后时候在同一斜线上
             * n=1 放置在第2列       array[1]=1
             * Math.abs(1-0)==1      Math.abs(array[n]-array[i])=1
             * Math.abs(n-i)==Math.abs(array[n]-array[i] 其实就是行数之差=列数之差，则在同一斜线
             *3.判断在同一行，没必要，n每次都在递增
             */
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }

        }
        return true;
    }

    //放置皇后
    //check是每一次递归时，进入check中都有for (int i = 0; i < max; i++)，因此有回溯
    private void check(int n){
        if(n==max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }
    }
    
    //输出可以将皇后摆放的位置
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
