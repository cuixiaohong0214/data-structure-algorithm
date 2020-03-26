package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] array={3,9,-1,10,-2};
//        System.out.println("排序前："+Arrays.toString(array));
//        sort(array);
//        System.out.println("排序后："+Arrays.toString(array));
        int[] array=new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*80000);
        }
        Date dateStart=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStartStr=simpleDateFormat.format(dateStart);
        System.out.println("排序前的时间是："+dateStartStr);

        sort(array);

        Date dateEnd=new Date();
        String dateEndStr=simpleDateFormat.format(dateEnd);
        System.out.println("排序后的时间是："+dateEndStr);

    }
    public static void sort(int[] array){
        //临时变量
        int temp=0;
        //设标识，记录是否比较过
        boolean flag=false;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j]>array[j+1]){
                    flag=true;
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
//            System.out.printf("第%d趟排序后的数组为：%s",i+1, Arrays.toString(array));
//            System.out.println();
            if(!flag){
                break;
            }else {
                flag=false;
            }

        }
    }
}
