package algorithm10.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int verxs=data.length;
        //10000表示不连通
        int[][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        MGraph graph=new MGraph(verxs);
        MinTree minTree=new MinTree();
        minTree.createGraph(graph,verxs,data,weight);
        minTree.showGraph(graph);
        minTree.prim(graph,0);
    }

}

//最小生成树
class MinTree{
    public void createGraph(MGraph graph,int verxs,char[] data,int[][] weight){
        for (int i = 0; i < verxs; i++) {
            graph.data[i]=data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j]=weight[i][j];
            }
        }
    }
    public void showGraph(MGraph graph){
        for (int[] link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }
    //prim算法,得到最小生成树
    public void prim(MGraph graph,int v){
        // 标记节点是否被访问过
        int[] visited=new int[graph.verxs];
        visited[v]=1;
        //记录两个顶点下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;

        //有graph.verxs个点 ， 所以会有graph.verxs-1条边
        for (int k = 1; k < graph.verxs; k++) {
            //找边 i表示访问过的点  j表示没有访问过的点
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if(visited[i]==1 && visited[j]==0 && graph.weight[i][j]<minWeight){
                        h1=i;
                        h2=j;
                        minWeight=graph.weight[i][j];
                    }
                }
            }
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">，权值："+minWeight);
            minWeight=10000;
            visited[h2]=1;
        }
    }
}
class MGraph{
    //节点个数
    int verxs;
    //节点数据
    char[] data;
    //存放边
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data=new char[verxs];
        weight=new int[verxs][verxs];
    }
}
