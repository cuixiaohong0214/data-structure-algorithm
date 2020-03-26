package tree.threadebinarytree;
//线索二叉树
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root=new HeroNode(1,"tom");
        HeroNode node2=new HeroNode(3,"jack");
        HeroNode node3=new HeroNode(6,"smith");
        HeroNode node4=new HeroNode(8,"mary");
        HeroNode node5=new HeroNode(10,"king");
        HeroNode node6=new HeroNode(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        HeroNode  leftNode=node5.getLeft();
        HeroNode rightNode=node5.getRight();
        System.out.println("10号节点的前驱节点是："+leftNode);//3
        System.out.println("10号节点的后驱节点是："+rightNode);//1

        System.out.println("遍历中序线索化的二叉树");
        threadedBinaryTree.threadedList();//8,3,10,1,14,6

    }
}

class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱结点的指针，pre总是保留前一个节点
    private HeroNode pre=null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //重载
    public void threadedNodes(){
        this.threadedNodes(root);
    }
    //二叉树进行中序线索化
    public void threadedNodes(HeroNode node){

        if(node==null){
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前节点
        //处理前驱节点
        if(node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //让当前节点是下一个节点的前驱结点
        pre=node;
        //3.线索化右子树
        threadedNodes(node.getRight());
    }


    //中序遍历线索化二叉树（对应中序线索化）
    public void threadedList(){
        HeroNode node=root;
        while (node!=null){
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的有指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node=node.getRight();
        }
    }

    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if(this.root!=null){
            return this.root.preOrdreSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if(this.root!=null){
            return this.root.infixOrdreSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if(this.root!=null){
            return this.root.postOrdreSearch(no);
        }else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树");
        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //leftType==0表示指向的是左子树，如果是1表示指向前驱结点。
    private int leftType;
    //rightType==0表示指向的是右子树，如果是1表示指向后继结点。
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no){
        if(this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null && this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if (this.right!=null){
            this.right.delNode(no);
        }
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }

    }
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }

    }
    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);

    }


    public HeroNode preOrdreSearch(int no){
        if(this.no==no){
            return this;
        }
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrdreSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.preOrdreSearch(no);
        }
        return resNode;

    }
    public HeroNode infixOrdreSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrdreSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            resNode=this.right.infixOrdreSearch(no);
        }
        return resNode;

    }
    public HeroNode postOrdreSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrdreSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.postOrdreSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}
