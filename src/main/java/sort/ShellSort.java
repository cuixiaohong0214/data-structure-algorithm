package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
//        int[] array={8,9,1,7,2,3,5,4,6,0};
//        System.out.println("排序前"+Arrays.toString(array));
//        shellSort(array);
//        System.out.println("排序后"+Arrays.toString(array));
        int[] array=new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*80000);
        }
        Date dateStart=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStartStr=simpleDateFormat.format(dateStart);
        System.out.println("排序前的时间是："+dateStartStr);

        shellSort2(array);

        Date dateEnd=new Date();
        String dateEndStr=simpleDateFormat.format(dateEnd);
        System.out.println("排序后的时间是："+dateEndStr);

    }
    //交换法
    public static void shellSort(int[] array){
        int temp=0;
        int count=0;
        for (int gap = array.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i-gap; j >=0 ; j-=gap) {
                    if (array[j]>array[j+gap]){
                        temp=array[j];
                        array[j]=array[j+gap];
                        array[j+gap]=temp;
                    }
                }
            }
            //System.out.println("希尔排序第"+(++count)+"轮排序后："+ Arrays.toString(array));
        }
    }

    //移动法
    public static void shellSort2(int[] array){
        for (int gap = array.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < array.length; i++) {
                int j=i;
                int temp=array[j];
                if(array[j]<array[j-gap]){
                    while (j-gap>=0 && temp<array[j-gap]){
                        //移动
                        array[j]=array[j-gap];
                        j-=gap;
                    }
                    //插入
                    array[j]=temp;
                }
            }
        }
    }
}
