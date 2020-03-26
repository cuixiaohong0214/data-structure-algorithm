package hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Emp emp1=new Emp(1,"景辞");
        Emp emp2=new Emp(2,"风眠晚");
        Emp emp3=new Emp(3,"东华帝君");
        Emp emp4=new Emp(4,"白凤九");
        Emp emp5=new Emp(10,"沈晔");
        Emp emp6=new Emp(11,"阿兰若");

        hashTable.add(emp1);
        hashTable.add(emp2);
        hashTable.add(emp3);
        hashTable.add(emp4);
        hashTable.add(emp5);
        hashTable.add(emp6);

        hashTable.list();
        System.out.println("寻找帝君，id为3");
         hashTable.findEmpById(3);


    }
}

class HashTable{
    private EmpLinkedList[] empLinkedListArray;
    //表示多少条链表
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedListArray=new EmpLinkedList[size];
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        int empLinkedListNo=hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //根据id查找
    public void findEmpById(int id){
        int empLinkedListNO=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到 id=%d\n",(empLinkedListNO+1),id);
        }else {
            System.out.println("在哈希表中，没有找到");
        }
    }

    //散列函数
    public int hashFun(int id){
        return id%size;
    }
}

class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        if(head==null){
            head=emp;
            return;
        }
        Emp curEmp=head;
        while (true){
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
    }

    public void list(int no){
        if(head==null){
            System.out.println("第 "+(no+1)+" 条链表为空");
            return;
        }
        System.out.print("第 "+(no+1)+" 条链表信息为 ");
        Emp curEmp=head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        System.out.println();

    }

    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){
                break;
            }
            if(curEmp.next==null){
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }

}

class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
