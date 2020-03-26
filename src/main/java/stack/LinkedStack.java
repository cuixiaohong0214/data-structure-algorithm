package stack;

/**
 * 链表实现栈
 */
public class LinkedStack {
    public class Node{
        private String value;
        private Node next;
        public Node(String value,Node node){
            this.value=value;
            this.next=node;
        }
    }
    private Node top=null;
    /**
     * 入栈
     */
    public void push(String value){
        Node newNode=new Node(value,null);
        if(top==null){
            top=newNode;
        }else{
            newNode.next=top;
            top=newNode;
        }
    }

    /**
     * 出栈
     */
    public String pop(){
        if(top==null){
            return null;
        }
        String value=top.value;
        top=top.next;
        return value;
    }
}
