package queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        int headQueue; //用于显示头数据
        CircleArray circleArray=new CircleArray(4);
        circleArray.enqueue(3);
        headQueue= circleArray.headQueue();
        circleArray.enqueue(5);
        headQueue= circleArray.headQueue();
        circleArray.enqueue(8);
        headQueue= circleArray.headQueue();
        circleArray.dequeue();
        headQueue= circleArray.headQueue();
        circleArray.dequeue();
        headQueue= circleArray.headQueue();
        circleArray.enqueue(13);
        headQueue= circleArray.headQueue();
        circleArray.enqueue(15);
        headQueue= circleArray.headQueue();
        circleArray.enqueue(18);


//        CircleArray circleArray=new CircleArray(4);
//        circleArray.addQueue(3);
//        circleArray.addQueue(5);
//        circleArray.addQueue(8);
//        circleArray.showQueue();
//        circleArray.headQueue();
//        System.out.println("出队一个");
//        int queueOne = circleArray.getQueue();
//        System.out.printf("出队的是：%d\n",queueOne);
//        int headOne=circleArray.headQueue();
//        System.out.printf("头部数据：%d\n",headOne);
//        circleArray.showQueue();
//
//        System.out.println("再出队一个");
//        int queueTwo = circleArray.getQueue();
//        System.out.printf("出队的是：%d\n",queueTwo);
//        int headTwo = circleArray.headQueue();
//        System.out.printf("头部数据：%d\n",headTwo);
//        circleArray.showQueue();
//
//        System.out.println("入队俩个");
//        circleArray.addQueue(13);
//        circleArray.addQueue(15);
//        circleArray.showQueue();
//        System.out.println("第三次出队一个");
//        circleArray.getQueue();
//        int headThree = circleArray.headQueue();
//        System.out.printf("头部数据：%d\n",headThree);
//        circleArray.showQueue();
//        System.out.println("入队一个");
//        circleArray.addQueue(18);
//        circleArray.showQueue();
//
//        System.out.println("第四次出队一个");
//        circleArray.getQueue();
//        System.out.println("入队一个");
//        circleArray.addQueue(23);
//        circleArray.showQueue();

    }
}
class CircleArray{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;
    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        front=0;
        rear=0;
        arr=new int[maxSize];
    }
    //判断队列为满
    public boolean isFull(){
        return (rear+1) % maxSize==front;
    }
    //判断队列为空
    public boolean isEmpty(){
        return front==rear;
    }
    //求队列的有效个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //入队
    public void enqueue(int data){
        if(isFull()){
          throw new RuntimeException("队列满，不能入队");
        }
        arr[rear]=data;
        rear=(rear+1)%maxSize;
    }
    //出队
    public int dequeue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能出队");
        }
        int getData=arr[front];
        front=(front+1)%maxSize;
        return getData;
    }
    //显示队列
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //显示头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}
