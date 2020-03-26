package algorithm10.dynamic;

import java.util.Arrays;

//动态规划
//01背包问题
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w={1,4,3};
        //物品的价值
        int[] val={1500,3000,2000};
        //背包的容量
        int m=4;
        //物品的个数
        int n=val.length;
        //记录放入商品的情况
        int[][] path=new int[n+1][m+1];

        //v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1];
        //初始化第一行和第一列，也可不处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;
        }
        //动态规划
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else {
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j]=1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }

            }
        }

        //放入商品后的表格
        for (int i = 0; i < v.length; i++) {
            System.out.println(Arrays.toString(v[i]));
        }
        //放入的最大结果
        int i=path.length-1;
        int j=path[0].length-1;
        while (i>0 && j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包中\n",i);
                j-=w[i-1];
            }
            i--;
        }


    }
}
