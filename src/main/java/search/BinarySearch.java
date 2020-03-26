package search;

import java.util.ArrayList;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={2,15,88,125,333,333,888,1234};
        int index=binarySearch(array,0,array.length-1,333);
        System.out.println("下标："+index);

        ArrayList<Integer> resIndexList = binarySearch2(array, 0, array.length - 1, 333);
        System.out.println("resIndexList="+resIndexList);

        int i = bsearch4(array, array.length, 333);
        System.out.println(i);



    }

    public static int binarySearch(int[] array,int left,int right,int findValue){
        if (left>right){
            return -1;
        }
        int mid=(left+right)/2;
        int midValue=array[mid];
        if(findValue>midValue){
            //向右递归
            return binarySearch(array,mid+1,right,findValue);
        }else if(findValue<midValue){
            //向左递归
            return binarySearch(array,left,mid-1,findValue);
        }else {
            //找到
            return mid;
        }
    }

    //如果找的数有多个
    public static ArrayList<Integer> binarySearch2(int[] array, int left, int right, int findValue){
        if (left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;
        int midValue=array[mid];
        if(findValue>midValue){
            //向右递归
            return binarySearch2(array,mid+1,right,findValue);
        }else if(findValue<midValue){
            //向左递归
            return binarySearch2(array,left,mid-1,findValue);
        }else {
            //找到 mid
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向左找
            int temp=mid-1;
            while (true){
                if(temp<0||array[temp]!=midValue){
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            //向右找
            temp=mid+1;
            while (true){
                if (temp>array.length-1||array[temp]!=midValue){
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }


    //1、查找的第一个值等于给定值的元素
    public static int bsearch1(int[] a,int n,int value){
        int low=0;
        int high=n-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(a[mid]>value){
                high=mid-1;
            }else if(a[mid]<value){
                low=mid+1;
            }else {
                if(mid==0||a[mid-1]!=value){
                    return mid;
                }else {
                    high=mid-1;
                }
            }
        }
        return -1;
    }

    //2、查找最后一个值等于给定值的元素
    public static int bsearch2(int[] a,int n,int value){
        int low=0;
        int high=n-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(a[mid]>value){
                high=mid-1;
            }else if(a[mid]<value){
                low=mid+1;
            }else {
                if(mid==n-1||a[mid+1]!=value){
                    return mid;
                }else {
                    low=mid+1;
                }
            }
        }
        return -1;
    }

    //3、查找第一个大于等于给定值的元素
    public static int bsearch3(int[] a,int n,int value){
        int low=0;
        int high=n-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(a[mid]>=value){
               if((mid==0)||a[mid-1]<value){
                   return mid;
               }else {
                   high=mid-1;
               }
            }else {
                low=mid+1;
            }
        }
        return -1;
    }

    //4、查找最后一个小于等于给定值的元素
    public static int bsearch4(int[] a,int n,int value){
        int low=0;
        int high=n-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(a[mid]>value){
                high=mid-1;
            }else {
               if((mid==n-1)||a[mid+1]>value){
                   return mid;
               }else {
                   low=mid+1;
               }
            }
        }
        return -1;
    }
}
