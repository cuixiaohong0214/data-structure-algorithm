package search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] array={1,9,11,-1,34,89};
        int index=seqSearch(array,34);
        if(index==-1){
            System.out.println("没有找到");
        }else {
            System.out.println("找到，下标为："+index);
        }

    }

    public static int seqSearch(int[] array,int value){
        for (int i = 0; i < array.length; i++) {
            if(value==array[i]){
                return i;
            }
        }
        return -1;
    }
}
