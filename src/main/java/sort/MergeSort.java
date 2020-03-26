package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array={8,4,5,7,1,3,6,2};
        int[] temp=new int[array.length];
        System.out.println("排序前："+ Arrays.toString(array));
        mergeSort(array,0,array.length-1,temp);
        System.out.println("排序后："+ Arrays.toString(array));

    }
    //分+合
    public static void mergeSort(int[] array,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;
            //左递归进行分解
            mergeSort(array,left,mid,temp);
            //右递归进行分解
            mergeSort(array,mid+1,right,temp);
            //合并
            merge(array,left,mid,right,temp);
        }
    }
    //合并过程
    public static void merge(int[] array,int left,int mid,int right,int[] temp){
        //左边有序序列初始索引
        int i=left;
        //右边有序序列初始索引
        int j=mid+1;
        //指向temp数组的当前索引
        int t=0;
        //1、先把左右两边有序的数据按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完
        while (i<=mid && j<=right){
            if(array[i]<=array[j]){
                temp[t]=array[i];
                t++;
                i++;
            }else {
                temp[t]=array[j];
                t++;
                j++;
            }
        }
        //2、把剩余的数据全部填充到temp数组中
        while (i<=mid){
            temp[t]=array[i];
            t++;
            i++;
        }
        while (j<=right){
            temp[t]=array[j];
            t++;
            j++;
        }
        //3、将temp数组的元素拷贝到array（注意：并不是每次都拷贝所有）----这一步有难度
        t=0;
        int tempLeft=left;
        //tempLeft与right 0 1 , 2 3 ,0 3 , 4 5 , 6 7 , 4 7 , 0 7
        while (tempLeft<=right){
            array[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}
