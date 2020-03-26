package search;

import java.util.Arrays;

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] array={1,8,10,89,1000,1234};

        int index = fibSearch(array, 10);
        System.out.println("下标："+index);

    }

    //得到一个斐波那契数列，因为后边mid=low+F(k-1)-1需要使用
    public static int maxSize=20;
    public static int[] fib(){
        int[] f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < maxSize; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
    public static int fibSearch(int[] array,int key){
        int low=0;
        int high=array.length-1;
        int k=0;
        int mid=0;
        int f[]=fib();
        //获取到斐波那契分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //f[k]的值可能大于a的长度，因此使用Arrays类构造一个新的数组，并指向a[]
        //不足的部分用0填充，但实际要用array数组最后的数填充
        // temp={1,8,10,89,1000,1234,0,0}=>temp={1,8,10,89,1000,1234,1234,1234}
        int[] temp= Arrays.copyOf(array,f[k]);
        for (int i = high+1; i < temp.length; i++) {
            temp[i]=array[high];
        }

        while (low<=high){
            mid=low+f[k-1]-1;
            if(key<temp[mid]){
                //向左找
                high=mid-1;
                /**
                 * 为什么是k--
                 * 1.全部元素=前面的元素+后边的元素
                 * 2.f[k]=f[k-1]+f[k-2]
                 * 3.因为前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                 * 4.即在f[k-1]前面继续找 k--
                 * 5.即下次循环 mid=f[k-1-1]-1
                 */
                k--;
            }else if(key>temp[mid]){
                //向右找
                low=mid+1;
                /**
                 * 为什么是k-=2
                 * 1.全部元素=前面的元素+后边的元素
                 * 2.f[k]=f[k-1]+f[k-2]
                 * 3.因为后面有f[k-2]个元素，所以可以继续拆分f[k-2]=f[k-3]+f[k-4]
                 * 4.即在f[k-2]的前面进行查找k-=2
                 * 5.即下次循环mid=f[k-1-2]-1
                 */
                k-=2;
            }else {
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

}
