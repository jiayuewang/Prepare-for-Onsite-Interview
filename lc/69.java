public class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code her
        long start =1, end = x;
        while(start +1 < end){
            long mid = (end - start)/2 + start;// the type of long
            if(mid*mid <= x){
            start = mid;
        }else{
            end = mid;
        }
    }
  //return (int) start;
   // return (int) end;//注意要把end写在前面因为是return的最大的可能开平方数，如果start写在前面会runtime超时
    }
}
//        
//         if(end *end <= x){
//             return (int) end;
//         }
//         return (int) start;
//     }
// }

// java中有三种移位运算符

// <<      :     左移运算符，num << 1,相当于num乘以2

// >>      :     右移运算符，num >> 1,相当于num除以2

// >>>    :     无符号右移，忽略符号位，空位都以0补齐

// 下面来看看这些移位运算都是怎样使用的