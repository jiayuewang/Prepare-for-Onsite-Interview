public class Solution {
    /**
     * @param version1: the first given number
     * @param version2: the second given number
     * @return: the result of comparing
     */
    public int compareVersion(String version1, String version2) {
        // Write your code here
        int length1 = version1.length();
        int length2 = version2.length();
        int i,j;
        while(i < length1 || j < length2){
            int num1,nums2;//循环一遍以后nums要置0了。所以在while里初始化
            while(i < length1 && version1.charAt(i) !='.'){//因为有可能给两个点的情况
              nums1 = nums1*10 + (version1.charAt(i++) -'0');
            }
            while(j < length2 || version2.charAt(i) !='.'){//注意这两个地方是while
              nums2 = nums2*10 + (version2.charAt(j++) - '0');  
            }
            if(nums1 > nums2) return 1;
            if(nums2> nums1) return -1;
            i++; j++;//之前ij都指向.了，所以此时再加一次
        }
        return 0;
    }
}

//   int i = 0;
//         int j = 0;
//         int length1 = version1.length();
//         int length2 = version2.length();
//         while(i<length1 || j<length2){
//             int num1 = 0, num2 = 0;
//             while(i<length1 && version1.charAt(i)!='.') num1 = num1*10+(version1.charAt(i++)-'0');
//             while(j<length2 && version2.charAt(j)!='.') num2 = num2*10+(version2.charAt(j++)-'0');
//             if(num1>num2) return 1;
//             else if(num1<num2) return -1;
//             i++;
//             j++;
//         }
//         return 0;
// 由于两个版本号所含的小数点个数不同，有可能是1和1.1.1比较，还有可能开头有无效0，比如01和1就是相同版本，还有可能末尾无效0，比如1.0和1也是同一版本。对于没有小数点的数字，可以默认为最后一位是小数点，而版本号比较的核心思想是相同位置的数字比较，比如题目给的例子，1.2和13.37比较，我们都知道应该显示1和13比较，13比1大，所以后面的不用再比了，再比如1.1和1.2比较，前面都是1，则比较小数点后面的数字。那么算法就是每次对应取出相同位置的小数点之前所有的字符，把他们转为数字比较，若不同则可直接得到答案，若相同，再对应往下取。如果一个数字已经没有小数点了，则默认取出为0，和另一个比较，这样也解决了末尾无效0的情况。