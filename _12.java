package com.fishercoder.solutions;

/**
 * 12. Integer to Roman
 *
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
    public String intToRoman(int num) {

        String[][] base = new String[][]{
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // ¸öÎ»µÄ±íÊ¾
                {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // Ê®Î»µÄ±íÊ¾
                {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // °Ù±¶µÄ±íÊ¾
                {"M", "MM", "MMM", "", "", "", "", "", ""}}; // Ç§Î»µÄ±íÊ¾


        String result = "";

       
        for (int i = 0; num != 0; num /= 10, i++) {
            if (num % 10 != 0) {
                result = base[i][num % 10 - 1] + result;
            }
        }

        return result;
    }
}

