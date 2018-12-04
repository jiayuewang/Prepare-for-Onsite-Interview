import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    public static String fractionToDecimal(int numerator, int denominator) {// - 1/3
        if(numerator == 0) return "0";
        StringBuilder res =new StringBuilder();
        res.append(((numerator > 0) ^ (denominator > 0) )? "-" : "");//-
        long num = Math.abs((long)numerator);//1
        long den = Math.abs((long) denominator);//3
        res.append(num/den);// 0
        num %= den;//num = 1
        if (num == 0) return res.toString();
        res.append(".");// res = 0.
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());// map.put(1,..)
        
        while(num != 0){
         num *= 10;// 1
            res.append(num/den);// 3
            num %= den;// 1
            if(map.containsKey(num)){//  1
                int index = map.get(num);//  index = 0//余数相等， = reminder相等
                res.insert(index, "(");// 0.(3)
                res.append(")");
                break;
            }else{
                map.put(num, res.length());
            }
         }
        return res.toString();
    }
    public static void main(String[] args) {
       int numr = 50, denr = 22; 
    String res = fractionToDecimal(numr, denr); 
       System.out.println(res);
    }
}
// + —
// 判断整数
// 判断小数


