package algorithm10.kruskal;

import java.util.Arrays;

public class KruskalCase {
    //边的个数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //INF 表示两个顶点不能连通
    private static final int INF=Integer.MAX_VALUE;
    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        int matrix[][]={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}};
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
//        EData[] edges = kruskalCase.getEdges();
//        System.out.println("排序前"+ Arrays.toString(edges));
//        kruskalCase.sortEdges(edges);
//        System.out.println("排序后"+Arrays.toString(edges));

        kruskalCase.kruskal();

    }

    //构造器
    public KruskalCase(char[] vertexs,int[][] matrix){
        int vlen=vertexs.length;
        //初始化顶点
        this.vertexs=new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i]=vertexs[i];
        }
        //初始化边
        this.matrix=new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j]=matrix[i][j];
            }
        }

        //统计边的个数
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%13d",matrix[i][j]);
            }
            System.out.println();;
        }
    }
    //获取图中的边，放到EData[]中   [['A','B',12],['B','F',7],……]
    private EData[] getEdges(){
        int index=0;
        EData[] edges=new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    edges[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //对边排序，冒泡
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    EData tmp=edges[j];
                    edges[j]=edges[j+1];
                    edges[j+1]=tmp;
                }
            }
        }
    }

    //获取顶点对应的下标
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    //Kruskal 算法
    public void kruskal(){
        //表示最后结果数组的索引
        int index=0;
        //用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        int[] ends=new int[edgeNum];
        //保存最后的最小生成树
        EData[] rets=new EData[edgeNum];

        //获取图中所有边的集合
        EData[] edges=getEdges();
        System.out.println("图的边的集合="+Arrays.toString(edges)+" 共"+edges.length);

        //按边的权值排序
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，没有回路则加入，有回路则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //第i条边的第一个点—起点
            int p1=getPosition(edges[i].start);
            //第i条边的第二个点—终点
            int p2=getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m=getEnd(ends,p1);
            //获取p2这个点点在已有最小生成树中的终点
            int n=getEnd(ends,p2);
            //判断是否构成回路，是否加入边
            if(m!=n){
                ends[m]=n;
                rets[index++]=edges[i];
            }
        }

        //打印最小生成树
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }


    /**
     * 获取下标为i的顶点的终点，用于后面判断连个顶底的终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个，ends是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}

//对象实例表示一条边
class EData{
    //边的一个点
    char start;
    //边的另一个点
    char end;
    //边的权值
    int weight;
    //构造器
    public EData(char start,char end,int weight){
        this.start=start;
        this.end=end;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                ">, weight=" + weight +
                '}';
    }
}
