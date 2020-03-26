package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList=new SingleLinkedList();
//        singleLinkedList.addHero(hero1);
//        singleLinkedList.addHero(hero2);
//        singleLinkedList.addHero(hero3);
//        singleLinkedList.addHero(hero4);
//        singleLinkedList.showHero();

        singleLinkedList.addHeroByOrder(hero1);
        singleLinkedList.addHeroByOrder(hero3);
        singleLinkedList.addHeroByOrder(hero4);
        singleLinkedList.addHeroByOrder(hero2);
        singleLinkedList.showHero();

        //测试单链表反转
        System.out.println("原单链表");
        singleLinkedList.showHero();
//        System.out.println("反转单链表");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.showHero();

        //测试逆序打印单链表
        System.out.println("逆序打印单链表，单链表结构并没有改变");
        reversePrint(singleLinkedList.getHead());


        HeroNode hero5=new HeroNode(2,"小卢","玉麒麟***");
        System.out.println("修改后------------");
        singleLinkedList.updateHero(hero5);
        singleLinkedList.showHero();
        System.out.println("删除后------------");
        singleLinkedList.delHero(1);
        singleLinkedList.showHero();

        //测试单链表有效节点个数
        System.out.println("有效节点个数="+getLength(singleLinkedList.getHead()));
        //测试倒数第2个节点
        System.out.println("倒数第2个节点"+findLastIndexNode(singleLinkedList.getHead(),2));



    }

    //获取单链表有效节点个数
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length=0;
        HeroNode temp=head.next;
        while (temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }


    /**
     * 查找单链表中的倒数第K个节点
     * 思路
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index表示倒数第index个节点
     * 3.先把链表从头到尾遍历，得到链表总长度个Length
     * 4.得到size后，从链表第一个开始遍历（size-index）个，就可以得到
     * 5.找到了返回该节点，否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        int size=getLength(head);
        if(index<=0||index>size){
            return null;
        }
        HeroNode temp=head.next;
        for (int i = 0; i < size-index; i++) {
            temp=temp.next;
        }
        return temp;
    }

    //单链表反转
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reverseHead=new HeroNode(0,"","");
        while (cur!=null){
            next=cur.next;
            cur.next=reverseHead.next;
            reverseHead.next=cur;
            cur=next;
        }
        head.next=reverseHead.next;
    }

    /**
     * 逆序打印单链表
     * 方式1：单链表反转，然后遍历，这样会破坏原来的单链表结构，不好
     * 方式2：可以利用栈，栈先进后出
     */
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        Stack<HeroNode> stack=new Stack<HeroNode>();
        HeroNode cur=head.next;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //合并俩个有序单链表，合并后依然有序

}

class SingleLinkedList{
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //直接添加到尾部
    public void addHero(HeroNode heroNode){
        HeroNode temp=head;
        while (true){
            //找到链表的最后
            if(temp.next==null){
                break;
            }
            //链表没有到最后，则temp后移
            temp=temp.next;
        }
        temp.next=heroNode;
    }

    //根据排名添加
    public void addHeroByOrder(HeroNode heroNode){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }else if(temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在，不能加入\n",heroNode.no);
        }else {
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改
    public void updateHero(HeroNode newHeroNode){
        HeroNode temp=head.next;
        if(temp==null){
            System.out.println("链表为空");
            return;
        }
        //表示是否找到该节点
        boolean flag=false;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点",newHeroNode.no);
        }
    }
    //删除
    public void delHero(int no){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next= temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }

    public void showHero(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }


}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
//                ", next=" + next +
                '}';
    }
}
