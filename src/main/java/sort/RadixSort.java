package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array={53,3,542,748,14,214};
        System.out.println("排序前："+Arrays.toString(array));
        radixSort(array);
        System.out.println("排序后："+Arrays.toString(array));
    }
    public static void radixSort(int[] array){
        //找到最大的数
        int max=array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i]>max){
                max=array[i];
            }
        }
        //得到最大数是几位数
        int maxLength=(max+"").length();
        //定义一个二维数组表示10个桶
        int[][] bucket=new int[10][array.length];
        //定义一个一位数组记录每个桶放了多少数据
        //bucketElementCounts[0]记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts=new int[10];

        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            //针对每个元素的对应位进行排序处理，依次是个位，十位，百位
            for (int j = 0; j < array.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement=array[j] /n % 10;
                //放到对应捅中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一位数组的下包一次取出放入原来的数组）
            int index=0;
            //遍历每个桶，并将桶中是数据，放入到原来数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据才放
                if(bucketElementCounts[k]!=0){
                    //循环该桶，即第k个桶（第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        array[index++]=bucket[k][l];
                    }
                }
                //每轮处理后，需要将bucketElementCounts[k]=0
                bucketElementCounts[k]=0;
            }
            System.out.println("第"+(i+1)+"轮，排序处理array="+ Arrays.toString(array));
        }

    }
}
