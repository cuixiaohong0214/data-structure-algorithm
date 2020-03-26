package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array={-9,23,78,0,23,-567,70};
        System.out.println("排序前："+ Arrays.toString(array));
        quickSort(array,0,array.length-1);
        System.out.println("排序后："+Arrays.toString(array));

    }
    public static void quickSort(int[] array,int left,int right){
        int l=left;
        int r=right;
        int pivot=array[(left+right)/2];
        int temp;
        //while循环的目的是让比pivot值小的放到左边，比pivot值大的放到右边
        while (l<r){
            //在pivot的左边一直找，直到找到大于等于pivot值
            while(array[l]<pivot){
                l+=1;
            }
            //在pivot的右边一直找，直到找到小于等于pivot值
            while (array[r]>pivot){
                r-=1;
            }

            //如果l>=r说明pivot的左右两边的值，已经按照左边全是小于等于pivot值，右边全是大于等于pivot值
            if(l>=r){
                break;
            }

            //交换
            temp=array[l];
            array[l]=array[r];
            array[r]=temp;

            //如果交换完后，发现array[l]==pivot值 相等 r--,前移--------没太理解
            if(array[l]==pivot){
                r-=1;
            }
            //如果交换完后，发现array[r]==pivot值 相等 l++,后移-------没太理解
            if (array[r]==pivot){
                l+=1;
            }
        }
        //如果l==r,必须l++,r--,否则为出现栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left<r){
            quickSort(array,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(array,l,right);
        }

    }
}
