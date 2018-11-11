package com.fishercoder.solutions;

 // Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 // For example,
 // 123 -> "One Hundred Twenty Three"
 // 12345 -> "Twelve Thousand Three Hundred Forty Five"
 // 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 // Hint:

 // Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 // Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 // There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
 // */
// 题解：
// 这道题让我们把一个整型数转为用英文单词描述，根据Hint提示，3位一组的进行处理，input范围为0到231 - 1之间，上限是billion位，3个一组也只需处理四组即可。


// 1. StringBuilder 解法

// 我们可以建立4个String数组，我们需要把1到19的英文单词都列出来，放到两个数组里分别存放1-9和10-19，还要把20,30，... 到90的不超过100列出来放到另一个数组里，建立第四个数组存放{"", "Thousand", "Million", "Billion"}，然后我们需要用技巧，比如一个三位数n，百位数表示为n/100，后两位数一起表示为n%100，十位数表示为n%100/10，个位数表示为n%10，分别将十位和个位数字的单词从两个数组中取出来。然后再来处理百位上的数字，还要记得加上Hundred。
// Running time: O(1)
// Space: O(1)
// 参见代码如下


    StringBuilder：
    public class Solution {
      public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] big= {"", "Thousand", "Million", "Billion"};
        String[] smallThantwenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] smallThanHundred = {"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] smallThanten = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        StringBuilder res = new StringBuilder();
        int number = 0;
        while (num != 0) {
            int temp = num % 1000;
            int num1 = temp % 10, num2 = (temp / 10) % 10, num3 = temp / 100;
            StringBuilder sb = new StringBuilder();
            if (num3 != 0) sb.append(smallThanten[num3] + " Hundred ");
            if (num2 == 1) sb.append(smallThantwenty[num1] + " ");
            else {
                if (num2 > 1) sb.append(smallThanHundred[num2] + " ");
                if (num1 > 0) sb.append(smallThanten[num1] + " ");
            }
            if(sb.length() != 0) sb.append(big[number] + " ");
            res.insert(0, sb);
            num /= 1000;
            number++;
        }
        return res.toString().trim();
    }
    }
    
// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_0A2A689CAD76E969DE5CB2E19233B28850A86392A300FAD3CF57E0505E2216F9_1515230420816_image.png)

// 2. Recursive解法

// 与解法1类似，我们可以将数字转化成字符串后，每3个字符分为1组，每组数字构成的串+“Billion/Million/Thousand”之类；可以自定义一个子函数来求3位以内的数的英文表示，这里需要提前将0-9、10-19、20、30、40、...、90之类的英文表示存入数组中，以便利用下标来访问主函数中调用四次这个帮助函数，然后中间要插入"Thousand", "Million", "Billion"到对应的位置，最后check一下末尾是否有空格，把空格都删掉，返回的时候检查下输入是否为0，是的话要返回'Zero'。
// Running time: O(1)
// Space: O(1)
// 参见代码如下


    public class Solution {
      String[] small = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
     String[] tenTotwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
     String[] smallthanHundred = new String[] {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
       private String helper(int num) {
            String res = new String();
            if (num < 10) res = small[num];
            else if (num < 20) res = tenTotwenty[num -10];
            else if (num < 100) res = smallthanHundred [num/10] + " " + helper(num % 10);
            else if (num < 1000) res = helper(num/100) + " Hundred " +  helper(num % 100);
            else if (num < 1000000) res = helper(num/1000) + " Thousand " +  helper(num % 1000);
            else if (num < 1000000000) res = helper(num/1000000) + " Million " +  helper(num % 1000000);
            else res = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
            return res.trim();
        }
    
           public String numberToWords(int num) {
            if (num == 0) return "Zero";
            return helper(num);
        }
    }

//  会比使用StringBuilder快3ms.
 

// ![](https://d2mxuefqeaa7sj.cloudfront.net/s_0A2A689CAD76E969DE5CB2E19233B28850A86392A300FAD3CF57E0505E2216F9_1515230614600_image.png)




