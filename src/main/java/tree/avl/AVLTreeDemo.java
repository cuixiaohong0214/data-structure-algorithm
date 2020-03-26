package tree.avl;

//二叉平衡树
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr={4,3,6,5,7,8};
        //int[] arr={10,12,8,9,7,6};
        int[] arr={10,11,7,6,8,9};
        AVLTree avlTree=new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("平衡处理");
        System.out.println("树的高度="+avlTree.getRoot().height());//4
        System.out.println("树的左子树高度="+avlTree.getRoot().leftHeight());//1
        System.out.println("树的右子树高度="+avlTree.getRoot().rightHeight());//3
    }
}


class AVLTree{
    private Node root;
    public Node getRoot(){
        return root;
    }
    //添加节点
    public void add(Node node){
        if (root==null){
            root=node;
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else {
            System.out.println("空树");
        }
    }
    //查找要删除的节点
    public Node search(int value){
        if (root==null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找要删除节点的父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //删除 要删除的节点的右子树的最小值 并返回这个最小值
    public int delRightTreeMin(Node node){
        Node target=node;
        while (target.left!=null){
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }
    //删除节点
    public void delNode(int value){
        if(root==null){
            return;
        }else {
            //找到要删除的节点
            Node targetNode=search(value);
            if(targetNode==null){
                return;
            }
            if(root.left==null && root.right==null){
                root=null;
                return;
            }
            //找到targetNode的父节点
            Node parent=searchParent(value);
            //如果要删除的节点是叶子节点
            if(targetNode.left==null && targetNode.right==null){
                //看要删除的节点targetNode是父节点的左子节点还是右子节点
                if(parent.left!=null && parent.left.value==value){
                    parent.left=null;
                }else if(parent.right!=null && parent.right.value==value){
                    parent.right=null;
                }
            }else if(targetNode.left!=null && targetNode.right!=null){
                //如果要删除的节点有俩个子树
                int minVal=delRightTreeMin(targetNode.right);
                targetNode.value=minVal;

            }else {
                //如果要删除的节点只有一个子树
                if(targetNode.left!=null){
                    //看要删除的节点是父节点的左子节点还是右子节点
                    if(parent!=null){
                        if(parent.left!=null && parent.left.value==value){
                            parent.left=targetNode.left;
                        }else {
                            parent.right=targetNode.left;
                        }
                    }else {
                        root=targetNode.left;
                    }
                }else {
                    if(parent!=null){
                        if(parent.left!=null && parent.left.value==value){
                            parent.left=targetNode.right;
                        }else {
                            parent.right=targetNode.right;
                        }
                    }else {
                        root=targetNode.right;
                    }
                }
            }
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value<this.value){
            if (this.left==null){
                this.left=node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right==null){
                this.right=node;
            }else {
                this.right.add(node);
            }
        }
        //添加完一个节点后，如果（右子树高度-左子树高度）>1，左旋转
        if(rightHeight()-leftHeight()>1){
            //如果他的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right!=null && right.leftHeight()>right.rightHeight()){
                //先对右子节点进行右旋转
                right.rightRotate();
                //然后对当前节点进行左旋转
                leftRotate();
            }else {
                leftRotate();
            }
            return;  //一定要
        }
        //添加完一个节点后，如果（左子树高度-右子树高度）>1，右旋转
        if(leftHeight()-rightHeight()>1){
            //如果他的左子树的右子树高度大于它的左子树高度
            if(left!=null && left.rightHeight()>left.leftHeight()) {
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else {
                rightRotate();
            }

        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }

    }

    //查找要删除的节点
    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else {
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        if((this.left!=null && this.left.value==value)||(this.right!=null && this.right.value==value)){
            return this;
        }else {
            if(value<this.value && this.left!=null){
                return this.left.searchParent(value);
            }else if(value>=this.value && this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }
    //返回左子树高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }
    //返回右子树高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return right.height();
    }

    //左旋转
    private void leftRotate(){
        // 创建新的节点，以当前根节点的值
        Node newNode=new Node(value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left=left;
        // 把新的节点的右子树设置成带你过去节点的右子树的左子树
        newNode.right=right.left;
        // 把当前节点的值替换为右子节点的值
        value=right.value;
        // 把当前节点的右子树设置为当前节点右子树的右子树
        right=right.right;
        // 把当前节点的左子树设置成新的节点
        left=newNode;
    }

    //右旋转
    private void rightRotate(){
        Node newNode=new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
