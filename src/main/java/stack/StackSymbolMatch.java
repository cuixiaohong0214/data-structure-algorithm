package stack;

import java.util.HashMap;
import java.util.Stack;

public class StackSymbolMatch {
    public static boolean isMatch(String str){
        Stack stack = new Stack();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put('}','{');
        hashMap.put(']','[');
        hashMap.put('>','<');
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(hashMap.containsValue(chars[i])){
                //左括号就放到栈中
                stack.push(chars[i]);
            }else{
                if(stack.size()<=0||hashMap.get(chars[i])==null){
                    return false;
                }
                //右括号就从map中获取对应的value(也就是左括号)与栈中存放的最上边的元素比较，相等则是一对
                if(hashMap.get(chars[i]).equals(stack.get(stack.size()-1))){
                    stack.pop();
                }
            }


        }
        if(stack.size()==0){
            return true;
        }else {
            return false;
        }

    }


//    public static boolean isMatch(String str) {
//        boolean flag=false;
//        Stack<Character> stackA=new Stack<>();
//        char[] chars = str.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if(stackA.size()==0){
//                stackA.push(chars[i]);
//                continue;
//            }
//            boolean isMatch = matchRule(String.valueOf(stackA.get(stackA.size() - 1)), String.valueOf(chars[i]));
//            if(isMatch){
//                stackA.pop();
//            }else{
//                stackA.push(chars[i]);
//            }
//
//
//        }
//        if (stackA.size()==0){
//            flag=true;
//        }
//
//        return flag;
//    }
//    public static boolean matchRule(String str1,String str2){
//        boolean flag=false;
//        if("(".equals(str1)&&")".equals(str2)){
//            flag= true;
//        }
//        if("[".equals(str1)&&"]".equals(str2)){
//            flag= true;
//        }
//        if("{".equals(str1)&&"}".equals(str2)){
//            flag= true;
//        }
//        if("<".equals(str1)&&">".equals(str2)){
//            flag= true;
//        }
//        return flag;
//    }

    public static void main(String[] args) {
        String symbol="(<>)<<[]>>";
        boolean match = isMatch(symbol);
        System.out.println(match);


    }
}
