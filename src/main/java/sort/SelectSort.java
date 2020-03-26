package sort;


import java.text.SimpleDateFormat;
import java.util.Date;


public class SelectSort {
    public static void main(String[] args) {
//        int[] array={101,34,119,1};
//        System.out.println("排序前："+ Arrays.toString(array));
//        sort(array);
//        System.out.println("排序后："+ Arrays.toString(array));
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
        for (int i = 0; i <array.length-1 ; i++) {
            int minIndex=i;
            int min=array[i];
            //找到最小的
            for (int j = i+1; j <array.length; j++) {
                if(min>array[j]){
                    min=array[j];
                    minIndex=j;
                }
            }
            //进行交换
            if(minIndex!=i){
                array[minIndex]=array[i];
                array[i]=min;
            }
//            System.out.printf("第%d趟后：%s",i+1,Arrays.toString(array));
//            System.out.println();
        }
    }
}
