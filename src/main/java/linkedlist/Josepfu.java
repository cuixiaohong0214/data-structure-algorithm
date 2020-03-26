package linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addChild(5);
        //circleSingleLinkedList.showChild();
        //测试小孩出圈
        circleSingleLinkedList.countChild(1,2,5);
    }

}
class CircleSingleLinkedList{
    //创建first节点，当前没有编号
    private Child first=new Child(-1);

    //添加节点，构建环形链表
    public void addChild(int nums){
        if(nums<1){
            System.out.println("num的值不正确");
            return;
        }
        Child curBoy=null;

        for (int i = 1; i <= nums; i++) {
            Child boy=new Child(i);
            if (i==1){
               first=boy;
               first.setNext(first);
               curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }

    }

    //循环当前环形链表
    public void showChild(){
        if(first==null){
            System.out.println("没有小孩");
            return;
        }
        Child curBoy=first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext()==first){
                break;
            }
            curBoy=curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有几个小孩在圈中
     */

    public void countChild(int startNo,int countNum,int nums){
        //校验数据
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //辅助指针
        Child helper=first;
        //将helper事先指向环形链表的最后这个节点
        while (true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo-1; j++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        while(true){
            if (helper==first){
                break;
            }
            //让first和helper同时移动countNum-1
            for (int j = 0; j < countNum-1; j++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //first指向的节点就是出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",helper.getNo());
    }
}

class Child{
    private int no;
    public Child next;
    public Child(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }
}
