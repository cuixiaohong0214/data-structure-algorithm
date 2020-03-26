package tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("先序遍历：");
        arrBinaryTree.preOrder();//1245367
        System.out.println("中序遍历：");
        arrBinaryTree.infixOrder();//4251637
        System.out.println("后序遍历：");
        arrBinaryTree.postOrder();//4526731
    }
}
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //重载
    public void preOrder(){
        this.preOrder(0);
    }
    public void infixOrder(){
        this.infixOrder(0);
    }
    public void postOrder(){
        this.postOrder(0);
    }

    //先序遍历
    public void preOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("二叉树为空");
        }
        System.out.println(arr[index]);
        if(2*index+1<arr.length){
            preOrder(2*index+1);
        }
        if(2*index+2<arr.length){
            preOrder(2*index+2);
        }
    }

    //中序遍历
    public void infixOrder(int index){
        if(arr==null || arr.length==0){
            System.out.println("二叉树为空");
        }
        if(2*index+1<arr.length){
            infixOrder(2*index+1);
        }
        System.out.println(arr[index]);
        if(2*index+2<arr.length){
            infixOrder(2*index+2);
        }
    }
    //后序遍历
    public void postOrder(int index){
        if(arr==null||arr.length==0){
            System.out.println("二叉树为空");
        }
        if(2*index+1<arr.length){
            postOrder(2*index+1);
        }
        if(2*index+2<arr.length){
            postOrder(2*index+2);
        }
        System.out.println(arr[index]);
    }
}
