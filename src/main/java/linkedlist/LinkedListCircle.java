package linkedlist;

public class LinkedListCircle {
    public static class Node{
        private int value;
        private Node next;
        public Node(int value,Node next){
            this.value=value;
            this.next=next;
        }
    }
    /**
     *  判断链表是否有环
     *
     * @param node
     * @return boolean
     * @author 崔晓鸿
     * @since 2019/12/25 10:57
     */
    public static boolean isLoop(Node node){
        Node fast=node;
       Node slow=node;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }

    /**
     *  求环的入口节点
     *
     * @param node
     * @return main.java.Node
     * @author 崔晓鸿
     * @since 2020/1/2 15:18
     */
    public static Node entryNodeOfLoop(Node node){
        Node fast=node;
        Node slow=node;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                break;
            }
        }
        //头节点
        Node head=node;
        //相遇节点
        Node meet=slow;
        //头结点和相遇节点相等时（有点复杂）
        while (head!=meet){
            head=head.next;
            meet=meet.next;
        }
        return head;
    }

    /**
     *  环内节点个数
     *
     * @param node
     * @return int
     * @author 崔晓鸿
     * @since 2020/1/2 15:48
     */
    public static int nodeNumOfLoop(Node node){
        Node fast=node;
        Node slow=node;
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                break;
            }
        }
        //计算环节点个数
        int count=0;
        //相遇节点
        Node temp=slow;
        do{
            slow=slow.next;
            count++;
        }while (slow!=temp);
        return count;
    }


    /**
     *  求链表长度
     *
     * @param node
     * @return int
     * @author 崔晓鸿
     * @since 2020/1/2 15:59
     */
    public static int nodeLength(Node node){
        Node slow=node;
        //入口节点
        Node entryNode = entryNodeOfLoop(node);
        //环长
        int LoopLength = nodeNumOfLoop(node);
        int length=0;
        while (entryNode!=slow){
            slow=slow.next;
            length++;
        }
        //链表长度
        int nodeLength=length+LoopLength;
        return nodeLength;
    }

    public static void main(String[] args) {
        Node node5=new Node(5,null);
        Node node4=new Node(4,node5);
        Node node3=new Node(3,node4);
        Node node2=new Node(2,node3);
        Node node1=new Node(1,node2);
        node5.next=node3;
        //是否有环
        boolean loop = isLoop(node1);
        System.out.println(loop);
        //入口节点
        Node entryNode = entryNodeOfLoop(node1);
        System.out.println(entryNode);
        //环的节点个数
        int nodeNum = nodeNumOfLoop(node1);
        System.out.println(nodeNum);
        //求链表长度
        int nodeLength = nodeLength(node1);
        System.out.println(nodeLength);

    }
}

