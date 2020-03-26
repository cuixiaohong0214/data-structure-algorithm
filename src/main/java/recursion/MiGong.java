package recursion;

public class MiGong {
    public static void main(String[] args) {
        int [][] map=new int[8][7];
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        for (int j = 0; j < 7; j++) {
            map[0][j]=1;
            map[7][j]=1;
        }
        map[3][1]=1;
        map[3][2]=1;

        System.out.println("原始地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j]+"  ");
            }
            System.out.println();
        }

        setWay(map,1,1);

        System.out.println("走过并标识的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j]+"  ");
            }
            System.out.println();
        }
    }


    //下 右 左 上

    /**
     * 1.map[i][j]=0表示没有走过，1表示墙，2表示已经走过，3表示走过但走不通
     * 2.顶一个策略，下->右->左->上
     * @param map 地图
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }
}
