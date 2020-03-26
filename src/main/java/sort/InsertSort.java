package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] array={101,34,119,1};
        System.out.println("排序前："+Arrays.toString(array));
        insertSort(array);
        System.out.println("排序后："+Arrays.toString(array));

    }
    public static void insertSort(int[] array){
        int insertIndex=0;
        int insertValue=0;
        for (int i = 1; i < array.length; i++) {
            insertIndex=i-1;
            insertValue=array[i];
            //找位置
            while (insertIndex>=0 && insertValue < array[insertIndex]){
                array[insertIndex+1]=array[insertIndex];//后移
                insertIndex--;
            }
            //插入
            if(insertIndex+1 != i){
                array[insertIndex+1]=insertValue;
            }
//            System.out.printf("第%d排序的结果是%s：",i, Arrays.toString(array));
//            System.out.println();
        }
    }
}
