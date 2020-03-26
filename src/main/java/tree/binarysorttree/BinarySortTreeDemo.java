package tree.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除节点后：");
        binarySortTree.infixOrder();

    }
}

//创建二叉排序树
class BinarySortTree{
    private Node root;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
