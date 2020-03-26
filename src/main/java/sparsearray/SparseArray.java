package sparsearray;
//稀疏数组
public class SparseArray {
    public static void main(String[] args) {
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        System.out.println("原始二维数组：");
        for (int[] row:chessArr1){
            for (int dada:row){
                System.out.printf("%d\t",dada);
            }
            System.out.println();
        }

        //将二维数组转化为稀疏数组

        //1、获取非0的数字有几个，也就是稀疏数组会有几行
        int sum=0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }

        //2、初始化稀疏数组并赋值
        int[][] sparseArr=new int[sum+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        int sparseRow=0;
        for(int i=0;i<11;i++){
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j]!=0){
                    sparseRow++;
                    sparseArr[sparseRow][0]=i;
                    sparseArr[sparseRow][1]=j;
                    sparseArr[sparseRow][2]=chessArr1[i][j];
                }
            }
        }
        //3、打印转化后的稀疏数组
        System.out.println("稀疏数组：");
        for(int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组转化为二维数组

        //1、创建原始的二维数组
        int chessArr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];
        //2、恢复二维数组的数据
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //3、输出恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for(int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


    }
}
