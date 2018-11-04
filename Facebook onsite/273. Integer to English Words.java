

// Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

// For example,

// 123 -> "One Hundred Twenty Three"
// 12345 -> "Twelve Thousand Three Hundred Forty Five"
// 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 

// Hint:

// Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
// Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
// There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)



把数字翻译成英文。这个跟Integer to Roman很像，把情况分清楚就不难解决。代码大都参考了Discuss里hwy_2015的，简洁易懂。
主要是以1000为一个单位来把数组分成组，每个组内单独处理tens和lessThanTwenty的情况。
Time Complexity - O(n)， Space Complexity - O(n)。

public class Solution {
    private final String[] lessThanTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"" ,"Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if(num <= 0) {
            return "Zero";
        }
        String words = "";
        int index = 0;
        
        while(num > 0) {
            if(num % 1000 != 0) {
                words = getNum(num % 1000) + thousands[index] + " " + words;
            }   
            num /= 1000;
            index++;
        }
        
        return words.trim();
    }
    
    private String getNum(int num) {
        if(num <= 0) {
            return "";
        } else if (num < 20) {
            return lessThanTwenty[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + getNum(num % 10);
        } else {
            return lessThanTwenty[num / 100] + " Hundred " + getNum(num % 100);
        }
    }
}


public class Solution {
    private final String[] LESS_THAN_20 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};//the "TEN" in this array may not be used, but adding it here will be convienient for us to use the index
    private final String[] THOUSANDS = new String[]{"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int n) {
        long num = n;//avoid overflow of case MIN_VALUE to positive; you can also directly output a string without using long
        if (num == 0) {
            return "Zero";
        }
        int i = 0;//use i to record how many three digits have been counted.
        String res = "";
        if (num < 0) {
            res += "Negative ";
            num = -num;
        }
        while (num > 0) {//loops at most four times, cuz int has at most 10 digits
            if (num % 1000 != 0) {//if curr three digits are not all zero
                res = helper(num % 1000) + THOUSANDS[i] + " " + res;//each time we count three digits, remember to add " "!!!
            }//if curr three digits(can be 3,2,1 though) are all 0, we should skip curr three digits,add nothing to res
            num /= 1000;//delete three digits which has been counted on the right
            i++;//we increment i for each three digits
        }
        return res.trim();//The tail may still have a redundant " "
    }
    
    private String helper(long num) {
        if (num == 0) {//if the digits left are all zero, return ""
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";//remember to add " " cuz after this may be followed by THOUSANDS[i]
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);//TENS[num/10] + " " + (LESS_THAN_20[num%10] + " ",or ""(num == 0))
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);//remember to use LESS_THAN_20[num / 100] here
        }//LESS_THAN_20[num/100] + " Hundred " + (TENS[num/10] + " " + (LESS_THAN_20[num%10] + " ",or ""(num == 0)))
    }
}


//if given num is not negative
public class Solution {
    private final String[] LESS_THAN_20 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};//the "TEN" in this array may not be used, but adding it here will be convienient for us to use the index
    private final String[] THOUSANDS = new String[]{"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int i = 0;//use i to record how many three digits have been counted.
        String res = "";
        while (num > 0) {//loops at most four times cuz max 2^31 -1 guaranteed
            if (num % 1000 != 0) {//if curr three digits are not all zero
                res = helper(num % 1000) + THOUSANDS[i] + " " + res;//each time we count three digits, remember to add " "!!!
            }//if curr three digits(can be 3,2,1 though) are all 0, we should skip curr three digits,add nothing to res
            num /= 1000;//delete three digits which has been counted on the right
            i++;//we increment i for each three digits
        }
        return res.trim();//The tail may still have a redundant " "
    }
    
    private String helper(int num) {
        if (num == 0) {//if the digits left are all zero, return ""
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";//remember to add " " cuz after this may be followed by THOUSANDS[i]
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);//TENS[num/10] + " " + (LESS_THAN_20[num%10] + " ",or ""(num == 0))
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);//remember to use LESS_THAN_20[num / 100] here
        }//LESS_THAN_20[num/100] + " Hundred " + (TENS[num/10] + " " + (LESS_THAN_20[num%10] + " ",or ""(num == 0)))
    }
}
test case:

2834567 -> "Two Million Eight Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    
2834567%1000=567 Five Hundred Sixty Seven thousands[i] i==0 "";
2834%1000=834 Eight Hundred Thirty Four Thousand Five Hundred Sixty Seven  
2%1000=2 Two Million Eight Hundred Thirty Four Thousand Five Hundred Sixty Seven  
