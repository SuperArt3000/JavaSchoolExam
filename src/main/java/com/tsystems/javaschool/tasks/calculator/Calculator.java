package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */

    StringBuilder result = new StringBuilder("Answer:");
    List<Character> list = new ArrayList<>();
    List<String> listInt = new ArrayList<>();
    List<String> listOperators = new ArrayList<>();
    List<Integer> listIndexPriority = new ArrayList<>();
    int validLeft = 0;
    int validRight = 0;
    int countMulty = 0;
    int countDevide = 0;
    int countPlus = 0;
    int countMinus = 0;
    int countLeft = 0;
    int countRight = 0;

    public String evaluate(String statement) {
        // TODO: Implement the logic here
        if(statement == "") return null;
        for (int i = 0; i < statement.length(); i++) {
            list.add(statement.charAt(i));
        }
        StringBuilder str;
        for (int i = 0; i < list.size(); i++) {
            str = new StringBuilder("");
            if (isNumber(list.get(i))) {
                str.append(list.get(i));
                if (checkArr(list, i + 1)) {
                    if (isNumber(list.get(i + 1)) || list.get(i + 1).equals('.')) {
                        if (list.get(i + 1).equals('.')) {
                            if (list.get(i + 2).equals('.')) return null;
                            str.append(list.get(i + 1));
                            str.append(list.get(i + 2));
                            i = i + 2;
                        } else {
                            str.append(list.get(i + 1));
                            i++;
                        }
                    }
                }
                listInt.add(str.toString());
            }
                if (isFunction(list.get(i))) {
                    if(list.get(i).equals('(') || list.get(i).equals(')')){
                        if(list.get(i).equals('(')) validLeft = validLeft + i;
                        if(list.get(i).equals(')')) validRight = validRight +i;
                        listIndexPriority.add(i);
                    }
                    listOperators.add(String.valueOf(list.get(i)));
                }
        }
        sumCounters();
        if(checkValidPriority()){
            return null;
        }


//        System.out.println("Multy: " + countMulty);
//        System.out.println("Devide: " + countDevide);
//        System.out.println("Plus: " + countPlus);
//        System.out.println("Minus: " + countMinus);
        listInt.stream().forEach(s -> System.out.println(s));
        System.out.println();
        listOperators.stream().forEach(s -> System.out.print(s));
        System.out.println();

        int count = countMulty + countDevide + countPlus + countMinus;
        while (count != 0){
            count = countMulty + countDevide + countPlus + countMinus;
            calculate();
        }


        result.append(listInt.get(listInt.size() - 1));
        return result.toString();
    }


    public static boolean isNumber(char c) {
        if (c >= '0' && c <= '9')
            return true;
        else
            return false;
    }
    public static boolean isFunction(char c) {
        if(c == '+' || c == '-' || c == '*'|| c == '/' || c == '(' || c == ')')
            return true;
        else
            return false;
    }

    public static boolean checkArr(List list ,int i){
        if(i < list.size()){
            return true;
        }
        return false;
    }

    public boolean checkValidPriority(){
        if(countLeft != countRight)
            return true;
        if(validLeft > validRight)
            return true;
        return false;
    }

    public String multyply(int index){
        int result = Integer.parseInt(listInt.get(index)) * Integer.parseInt(listInt.get(index + 1));
        System.out.println("multyply");
        System.out.println(Integer.parseInt(listInt.get(index)));
        System.out.println(Integer.parseInt(listInt.get(index + 1)));
        System.out.println(result);
        return String.valueOf(result);
    }
    public String devide(int index){
        int result = Integer.parseInt(listInt.get(index)) / Integer.parseInt(listInt.get(index + 1));
        System.out.println("devide");
        System.out.println(Integer.parseInt(listInt.get(index)));
        System.out.println(Integer.parseInt(listInt.get(index + 1)));
        System.out.println(result);
        return String.valueOf(result);
    }
    public String plus(int index){
        int result = Integer.parseInt(listInt.get(index)) + Integer.parseInt(listInt.get(index + 1));
        System.out.println("plus");
        System.out.println(Integer.parseInt(listInt.get(index)));
        System.out.println(Integer.parseInt(listInt.get(index + 1)));
        System.out.println(result);
        return String.valueOf(result);
    }

    public String minus(int index){
        int result = Integer.parseInt(listInt.get(index)) - Integer.parseInt(listInt.get(index + 1));
        System.out.println("minus");
        System.out.println(Integer.parseInt(listInt.get(index)));
        System.out.println(Integer.parseInt(listInt.get(index + 1)));
        System.out.println(result);
        return String.valueOf(result);
    }

    public void sumCounters(){
        for (String s : listOperators) {
            if(s.equals("*")){
                countMulty++;
            }
            if(s.equals("/")){
                countDevide++;
            }
            if(s.equals("+")){
                countPlus++;
            }
            if(s.equals("-")){
                countMinus++;
            }
            if(s.equals("(")){
                countLeft++;
            }
            if(s.equals(")")){
                countRight++;
            }
        }
    }

    public void calculate(){
        if(countMulty != 0) {
            for (int i = 0; i < listOperators.size(); i++) {
                if(listOperators.get(i).equals("*")) {
                    listInt.set(i, multyply(i));
                    listInt.remove(i + 1);
                    listOperators.remove(i);
                    countMulty = countMulty - 1;
                }
            }
        } else if(countDevide != 0) {
            for (int i = 0; i < listOperators.size(); i++) {
                if(listOperators.get(i).equals("/")){
                    listInt.set(i, devide(i));
                    listInt.remove(i + 1);
                    listOperators.remove(i);
                    countDevide = countDevide - 1;
                }
            }
        } else if(countMinus != 0) {
            for (int i = 0; i < listOperators.size(); i++) {
                if(listOperators.get(i).equals("-")){
                    listInt.set(i, minus(i));
                    listInt.remove(i + 1);
                    listOperators.remove(i);
                    countMinus = countMinus - 1;
                }
            }
        } else if (countPlus != 0) {
            for (int i = 0; i < listOperators.size(); i++) {
                if (listOperators.get(i).equals("+")) {
                    listInt.set(i, plus(i));
                    listInt.remove(i + 1);
                    listOperators.remove(i);
                    countPlus = countPlus - 1;
                }
            }
        }
    }

}

