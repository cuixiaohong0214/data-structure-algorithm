package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
//        //逆波兰的数字和符号用空格隔开 方便截取
//        //(3+4)*5-6 后缀表达式：3 4 + 5 * 6 -
//        String suffixExpression="3 4 + 5 * 6 -";
//        //使用ArrayList配合栈完成
//        List<String> list = getListString(suffixExpression);
//        System.out.println("list="+list);
//        int res = calculate(list);
//        System.out.println("计算的结果是"+res);

        /**
         * 中缀转后缀，并计算
         * 1.  1+((2+3)*4)-5 转成 1 2 3 + 4 * + 5 -
         * 2.  将中缀表达式转成对应的list,方便
         * 3.  将中缀表达式list转成后缀表达式list 即：[1,+,(,(,2,+,3,),*,4,),-,5] =>  [1,2,3,+,4,*,+,5,-]
         */

        String expresstion="1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expresstion);
        System.out.println("中缀表达式对应的List="+infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List="+suffixExpressionList);

        System.out.printf("%s=%d",expresstion,calculate(suffixExpressionList));


    }
    //将后缀表达式的数据和运算符依次放入到ArrayList
    public static List<String> getListString(String suffixExpression){
        String[] split=suffixExpression.split(" ");
        List<String> list=new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }

    //计算
    public static int calculate(List<String> items){
        Stack<String> stack=new Stack<String>();
        for (String item:items){
            if(item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop()) ;
                int num1 = Integer.parseInt(stack.pop()) ;
                int res=0;
                if(("+").equals(item)){
                    res=num1 +num2;
                }else if(("-").equals(item)){
                    res=num1-num2;
                }else if(("*").equals(item)){
                    res=num1*num2;
                }else if(("/").equals(item)){
                    res=num1/num2;
                }else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(""+res);
            }
        }
        //最后留在栈中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

    //中缀转后缀

    //将中缀表达式转成对应的List   str="1+((2+3)*4)-5"
    public static  List<String> toInfixExpressionList(String s){
        List<String> list=new ArrayList<String>();
        int i=0;
        String str;
        char c;
        do{
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){//如果是一个非数字，加到ls
                list.add(""+c);
                i++;
            }else {//如果是一个数字
                str="";
                while (i<s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                list.add(str);
            }
        }while (i<s.length());
        return list;
    }

    //将中缀表达式转化为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> list){
        //符号栈
        Stack<String> s1=new Stack<String>();
        //s2只有添加，没有pop,加上如果用栈还得逆序输出，所以这里直接用list
        List<String> s2=new ArrayList<String>();
        for (String item:list){
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//去掉左括号
            }else {
                //是运算符 比较优先级
                while (s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次放到s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }

        return s2;
    }
}

class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //返回优先级
    public static int getValue(String operagion){
        int result=0;
        switch ( operagion){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
