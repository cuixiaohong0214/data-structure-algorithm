package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root=new HeroNode(1,"宋江");
        HeroNode node2=new HeroNode(2,"吴用");
        HeroNode node3=new HeroNode(3,"卢俊义");
        HeroNode node4=new HeroNode(4,"林冲");
        HeroNode node5=new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

//        //前序查找
//        System.out.println("前序查找");
//        HeroNode resNode=binaryTree.preOrderSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到");
//        }

//        //中序序查找
//        System.out.println("中序查找");
//        HeroNode resNode=binaryTree.infixOrderSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到");
//        }

        //后序序查找
        System.out.println("后序查找");
        HeroNode resNode=binaryTree.postOrderSearch(15);
        if(resNode!=null){
            System.out.printf("找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.printf("没有找到");
        }
        System.out.println();
        //删除
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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
