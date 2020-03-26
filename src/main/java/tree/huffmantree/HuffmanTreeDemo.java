package tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
//赫夫曼树
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);

    }
    public static Node createHuffmanTree(int[] arr){
        //将arr放到ArrayList中
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int value:arr){
            nodes.add(new Node(value));
        }

        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);

            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);

            Node parent=new Node(leftNode.value+rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("树为空");
        }
    }
}

//Comparable为了排序
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //this.value-o.value表示从小到大排序
        return this.value-o.value;
    }
}
