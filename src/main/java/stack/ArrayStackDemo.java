package stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack=new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.show();
        System.out.println("出栈俩次");
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.show();
    }
}
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top=-1;
    public ArrayStack(int arrMaxSize){
        maxSize=arrMaxSize;
        stack=new int[maxSize];
    }
    // 栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    // 栈空
    public boolean isEmpty(){
        return top==-1;
    }
    // 入栈
    public void push(int data){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=data;
    }
    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value=stack[top];
        top--;
        return value;
    }
    // 遍历栈
    public void show(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
