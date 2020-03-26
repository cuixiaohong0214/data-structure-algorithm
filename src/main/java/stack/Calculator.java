package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression="3+2*6-2";
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';
        String keepNum="";
        while (true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){//如果是运算符
                if(!operStack.isEmpty()){
                    /**
                     * 如果符号栈中有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，
                     * 就需要从数栈中pop出俩个数，从符号栈中pop出一个符号，进行运算，
                     * 将得到的结果入数栈，然后将当前的操作符入符号栈
                     */
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else {
                    //如果符号栈为空
                    operStack.push(ch);
                }
            }else {//如果是数，入数栈
                //numStack.push(ch-48);
                /**
                 * 处理多位数
                 * 1、不能发现是一个数就立即入栈，因为可能是多位数
                 * 2、在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                 * 3、因此我们需要定义一个变量字符串，用于拼接
                 */
                keepNum+=ch;
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
            index++;
            if(index>=expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        int res2=numStack.pop();
        System.out.printf("表达式%s=%d",expression,res2);

    }
}
class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top=-1;
    public ArrayStack2(int arrMaxSize){
        maxSize=arrMaxSize;
        stack=new int[maxSize];
    }
    // 栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    // 栈空
    public boolean isEmpty(){
        return top==-1;
    }
    // 入栈
    public void push(int data){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=data;
    }
    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value=stack[top];
        top--;
        return value;
    }
    // 遍历栈
    public void show(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //返回栈顶值，不是pop
    public int peek(){
        return stack[top];
    }
    //返回运算符的优先级
    public int priority(int oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}
