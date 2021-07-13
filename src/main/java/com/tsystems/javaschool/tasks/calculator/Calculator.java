package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

public class Calculator{

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    Stack<Double> stack;
    String postfix;

    public Calculator() {
        stack = new Stack<>();
        postfix = "";
    }

    public String evaluate(String statement) {
        if(statement == null) return null;
        PostFix convertor = new PostFix(statement);
        postfix = convertor.doTrans();
        double val;
        double tmpResult = 0;
        double num1, num2;
        if(postfix.isEmpty()) return null;
        String[] tmp = postfix.split(" ");
        for(int j=0; j<tmp.length; j++){
            if(tmp[j].isEmpty())
                return null;
            if(!tmp[j].equals("+") && !tmp[j].equals("-") &&
                    !tmp[j].equals("*") && !tmp[j].equals("/")){
                try{
                    val = Double.valueOf(tmp[j]);
                }catch(NumberFormatException ex){
                    return null;
                }
                stack.push(val);
            }else{
                num2 = Double.valueOf(stack.pop());
                num1 = Double.valueOf(stack.pop());
                if(tmp[j].equals("+")){
                    tmpResult = num1 + num2;
                }
                if(tmp[j].equals("-")){
                    tmpResult = num1 - num2;
                }
                if(tmp[j].equals("*")){
                    tmpResult = num1 * num2;
                }
                if(tmp[j].equals("/")){
                    if(num2 == 0)
                        return null;
                    tmpResult = num1 / num2;
                }
                stack.push(tmpResult);
            }
        }
        String result = stack.pop().toString();
        if((Double.parseDouble(result) % 1) == 0){
            int res = (int) Double.parseDouble(result);
            return String.valueOf(res);
        }
        return result;
    }

}

