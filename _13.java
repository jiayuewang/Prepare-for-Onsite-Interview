package com.fishercoder.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 *
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * */

 public int romanToInt(String s) {

        int result = 0;
        int prev = 0; // 记录前一个数字的值

        for (int i = s.length() - 1; i > -1; i--) {
            switch (s.charAt(i)) {
                case 'I': // 1
                    if (1 < prev) {
                        result -= 1;
                    } else {
                        result += 1;

                    }
                    prev = 1;
                    break;

                case 'V': // 5

                    if (5 < prev) {
                        result -= 5;
                    } else {
                        result += 5;
                    }

                    prev = 5;

                    break;
                case 'X': // 10
                    if (10 < prev) {
                        result -= 10;
                    } else {
                        result += 10;
                    }

                    prev = 10;
                    break;
                case 'L': // 50
                    if (50 < prev) {
                        result -= 50;
                    } else {
                        result += 50;
                    }

                    prev = 50;
                    break;
                case 'C': // 100
                    if (100 < prev) {
                        result -= 100;
                    } else {
                        result += 100;
                    }

                    prev = 100;
                    break;
                case 'D': // 500
                    if (500 < prev) {
                        result -= 500;
                    } else {
                        result += 500;
                    }

                    prev = 500;
                    break;
                case 'M': // 1000
                    result += 1000;
                    prev = 1000;
                    break;
            }
        }

        return result;
    }
}


}
