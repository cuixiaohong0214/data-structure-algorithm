package algorithm10.divideandconquer;

// 分治算法
// 汉诺塔的移动
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }
    public static void hanoiTower(int num,char a,char b,char c){
        if(num==1){
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            //1、先把最上面的所有盘A->B，移动过程会用到C
            hanoiTower(num-1,a,c,b);
            //2、最下边的盘A->C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3、B上所有的盘B->C,移动过程会用到A
            hanoiTower(num-1,b,a,c);
        }
    }
}
