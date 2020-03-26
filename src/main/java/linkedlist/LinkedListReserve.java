package linkedlist;

public class LinkedListReserve {
    public static class Node{
        private int value;
        private Node next;
        public Node(int value,Node next){
            this.value=value;
            this.next=next;
        }
    }
    public static Node reverse(Node node){
        //循环遍历实现
        Node reverse=null;
        while (node!=null){
            Node next=node.next;
            node.next=reverse;
            reverse=node;
            node=next;
        }
        return reverse;
//        //递归实现
//        if(node==null||node.next==null){
//            return node;
//        }
//        Node nextList=node.next;
//        Node reverse = reverse(nextList);
//        nextList.next=node;
//        node.next=null;
//        return reverse;
    }
    public static void main(String[] args) {
        Node node5=new Node(5,null);
        Node node4=new Node(4,node5);
        Node node3=new Node(3,node4);
        Node node2 =new Node(2,node3);
        Node node1=new Node(1,node2);
        Node reverse = reverse(node1);
        System.out.println(reverse);
    }
}
