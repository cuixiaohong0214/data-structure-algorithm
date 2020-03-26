package queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(3);
        arrayQueue.addQueue(3);
        arrayQueue.addQueue(5);
        arrayQueue.addQueue(8);
        arrayQueue.showQueue();
        arrayQueue.headQueue();
        System.out.println("出队一个");
        int queueOne = arrayQueue.getQueue();
        System.out.printf("出队的是：%d\n",queueOne);
        int headOne=arrayQueue.headQueue();
        System.out.printf("头部数据：%d\n",headOne);
        arrayQueue.showQueue();

        System.out.println("再出队一个");
        int queueTwo = arrayQueue.getQueue();
        System.out.printf("出队的是：%d\n",queueTwo);
        int headTwo = arrayQueue.headQueue();
        System.out.printf("头部数据：%d\n",headTwo);
        arrayQueue.showQueue();

    }

}
class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1;
        rear=-1;
    }
    //判断队列为满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列为空
    public boolean  isEmpty(){
        return rear==front;
    }
    //入队
    public void addQueue(int data){
        if(!isFull()){
            rear++;
            arr[rear]=data;
        }
    }
    //出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];

    }
    //显示头数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front+1];
    }

    //显示队列数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

}