package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        int n=8;
        String Vertexs[]={"1","2","3","4","5","6","7","8"};
        Graph graph=new Graph(n);
        //添加节点
        for(String vertex:Vertexs){
            graph.insertVertex(vertex);
        }
        //添加边  A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);
        graph.showGraph();

        //深度优先遍历
        System.out.println("深度优先遍历");
        graph.dfs();
        System.out.println();
        //广度优先遍历
        System.out.println("广度优先遍历");
        graph.bfs();

    }

    //存储顶点集合
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;
    //记录某个节点是否被访问过
    private boolean[] isVisited;

    //构造器
    public Graph(int n){
        edges=new int[n][n];
        vertexList=new ArrayList<String>(n);
        numOfEdges=0;
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

    //图的常用方法
    //得到节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i（下标）对应的数据 0->A 1->B
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link:edges){
            System.err.println(Arrays.toString(link));
        }
    }

    //得到第一个邻接节点的下标w
    public int getFirstNeighboor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeightboor(int v1,int v2){
        for (int j = v2+1; j < vertexList.size(); j++) {
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历
    public void dfs(boolean[] isVisited,int i){
        // 首先我们访问该节点，输出
        System.out.print(getValueByIndex(i)+"->");
        // 将节点设置为已经访问
        isVisited[i]=true;
        // 查找节点i的第一个邻接节点
        int w=getFirstNeighboor(i);
        while (w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            w=getNextNeightboor(i,w);
        }
    }
    //遍历所有节点，都进行深度优先遍历
    public void dfs(){
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //广度优先遍历
    public void bfs(boolean[] isVisited,int i){
        //表示队列的头节点对应下标
        int u;
        //邻接节点
        int w;
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已经访问
        isVisited[i]=true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头结点下标
            u= (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标
            w=getFirstNeighboor(u);
            while (w!=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w]=true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w=getNextNeightboor(u,w);
            }
        }

    }
    //遍历所有节点，都进行广度优先遍历
    public void bfs(){
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

}
