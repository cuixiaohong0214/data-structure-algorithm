package tree.huffmancode;


import java.io.*;
import java.util.*;

//赫夫曼编码
public class HuffmanCode {
    public static void main(String[] args) {
//        String content="i like like like java do you like a java";
//        byte[] contentBytes=content.getBytes();
//
//        //压缩
//        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果是："+Arrays.toString(huffmanCodesBytes)+" 长度是："+huffmanCodesBytes.length);
//
//        //解压
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("解压后，原来的字符串是："+new String(sourceBytes));


//        //压缩文件
//        String srcFile="e://test.png";
//        String dstFile="e://test.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件成功");
        //解压文件
        String zipFile="e://test.zip";
        String dstFile="e://test2.png";
        unZipFile(zipFile,dstFile);
        System.out.println("解压成功");

    }

    //统计每个byte的次数转成node
    public static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<Node>();
        //统计每个byte出现的次数
        Map<Byte,Integer> counts=new HashMap<>();
        for (byte b:bytes){
            Integer count=counts.get(b);
            if(count==null){
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //将键值对转成node，加到nodes中
        for(Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //创建Huffman树
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node leftNode=nodes.get(0);
            Node rightNode=nodes.get(1);
            Node parent=new Node(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }


    //生成赫夫曼编码
    //生成赫夫曼编码表
    static Map<Byte,String> huffmanCodes=new HashMap<Byte, String>();
    //拼接路径
    static StringBuilder stringBuilder=new StringBuilder();

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入接点
     * @param code 路径 左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2=new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node!=null){
            if(node.data==null){
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            }else {
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    //重载getCodes
    public static Map<Byte,String> getCodes(Node root){
        if(root==null){
            return null;
        }
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * @param bytes 原始的字符串对应byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder=new StringBuilder();
        for (byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //统计返回byte[] huffmanCodeBytes长度
        //一句话也可 int len=(stringBuilder.length()+7)/8
        int len;
        if(stringBuilder.length()%8==0){
            len=stringBuilder.length()/8;
        }else {
            len=stringBuilder.length()/8+1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String strByte;
            if(i+8>stringBuilder.length()){
                //不够8位
                strByte=stringBuilder.substring(i);
            }else {
                strByte=stringBuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index]=(byte) Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //封装
    public static byte[] huffmanZip(byte[] bytes){

        List<Node> nodes=getNodes(bytes);
        //创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @param flag 标志师父需要补高位，如果是true，表示需要补高位，如果是最后一个字节，无需补高位
     * @param b 传入的byte
     * @return 是该b对应的二进制的字符串（是按补码返回）
     */
    public static String byteToBitString(boolean flag,byte b){
        int temp=b;
        //如果是正数我们还存在补高位
        if(flag){
            //按位与256    1 0000 0000 | 0000 0001 =>1 0000 0001
            temp|=256;
        }
        //返回的是temp对应的二进制的补码
        String str=Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }
    
    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //stringBuilder放的是赫夫曼字节数组对应的二进制字符串
        StringBuilder stringBuilder=new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b=huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag=(i==huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换 a->100  100->a
        Map<String,Byte> map=new HashMap<String,Byte>();
        for (Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list=new ArrayList<>();
        //i可以理解为索引，扫描stringBuilder
        //向list中存放所有的字符
        for (int i = 0; i < stringBuilder.length();) {
            int count=1;
            boolean flag=true;
            Byte b=null;
            while (flag){
                String key =stringBuilder.substring(i,i+count);
                b=map.get(key);
                if(b==null){
                    //没有匹配到
                    count++;
                }else {
                    flag=false;
                }
            }
            list.add(b);
            //i移动到count
            i+=count;
        }

        //把list中的数据放到byte[]中 返回
        byte b[]=new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i]=list.get(i);
        }
        return b;

    }




    //压缩文件
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os=null;
        ObjectOutputStream oos=null;
        //创建文件的输入流
        FileInputStream is=null;
        try{
            //创建文件的输入流
            is=new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b=new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes=huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os=new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos=new ObjectOutputStream(os);
            //把赫夫曼编码的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    //解压文件
    public static void unZipFile(String zipFile,String dstFile){
        // 定义文件输入流
        InputStream is=null;
        // 定义一个对象输入流
        ObjectInputStream ois=null;
        // 定义文件的输出流
        OutputStream os=null;
        try {
            // 创建文件输入流
            is=new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois=new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes=(byte[])ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte,String> huffmanCodes =(Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes数组写入到目标文件
            os=new FileOutputStream(dstFile);
            // 写数据到dstFile文件
            os.write(bytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e2){
                System.out.println(e2.getMessage());
            }
        }
    }




    public static void preOrdre(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("这是个空树");
        }
    }




}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

}
