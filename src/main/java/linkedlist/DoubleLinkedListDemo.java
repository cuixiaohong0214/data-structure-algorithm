package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.addHero(hero1);
//        doubleLinkedList.addHero(hero2);
//        doubleLinkedList.addHero(hero3);
//        doubleLinkedList.addHero(hero4);
//        doubleLinkedList.showHero();

        System.out.println("测试有序添加");
        doubleLinkedList.addHeroByOrder(hero3);
        doubleLinkedList.addHeroByOrder(hero2);
        doubleLinkedList.addHeroByOrder(hero4);
        doubleLinkedList.addHeroByOrder(hero1);
        doubleLinkedList.showHero();

        HeroNode2 newHeroNode=new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.updateHero(newHeroNode);
        System.out.println("修改后的链表");
        doubleLinkedList.showHero();

        doubleLinkedList.delHero(3);
        System.out.println("删除后的链表");
        doubleLinkedList.showHero();

    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;
    public HeroNode2(int no,String name,String nickname){
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

class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");
    public HeroNode2 getHead(){return head;}
    //添加
    public void addHero(HeroNode2 heroNode){
        HeroNode2 temp=head;
        while (true){
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    //根据排名添加
    public void addHeroByOrder(HeroNode2 heroNode){
        HeroNode2 temp=head;
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
            if(temp.next!=null){
                heroNode.next=temp.next;
                temp.next.pre=heroNode;
            }
            temp.next=heroNode;
            heroNode.pre=temp;

        }
    }

    //修改
    public void updateHero(HeroNode2 newHeroNode){
        HeroNode2 temp=head.next;
        if(temp==null){
            System.out.println("链表为空");
        }
        boolean flag=false;
        while (true){
            if (temp==null){
                break;
            }
            if(temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d节点",newHeroNode.no);
        }

    }
    //删除
    public void delHero(int no){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }

    //显示
    public void showHero(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

}
