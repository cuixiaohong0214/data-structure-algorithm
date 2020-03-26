package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr={4,6,8,5,9};
        System.out.println("排序前:"+ Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后:"+ Arrays.toString(arr));
    }

    public static void heapSort(int arr[]){
        int temp=0;
        //调整堆
        for (int i = arr.length/2-1; i >=0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        //交换
        for (int j = arr.length-1; j >0 ; j--) {
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
    }
    /**
     * 将数组调整成大顶堆
     * @param arr 待调整数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整 length在逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length){
        //先取出当前元素的值
        int temp=arr[i];
        //调整 k=i*2+1 k是i节点的左子节点
        for (int k = i*2+1; k < length; k=k*2+1) {
            //比较左右节点值，找最大
            if (k+1<length && arr[k]<arr[k+1]){
                k++;
            }
            //子节点与父节点比较，子节点大就交换
            if(arr[k]>temp){
                arr[i]=arr[k];
                i=k;
            }else {
                break;
            }
        }
        //for循环结束后，已经将以i为父节点的树的最大值，放在了顶部（局部）
        //将temp值放到调整后的位置
        arr[i]=temp;

    }
}
