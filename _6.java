package com.fishercoder.solutions;

/**
 * 6. ZigZag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
*/

 P   A   H   N
 A P L S I I G
 Y   I   R

 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */

public String convert(String s, int nRows) {
    char[] c = s.toCharArray();
    int len = c.length;
    StringBuffer[] sb = new StringBuffer[nRows];
    for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
    
    int i = 0;
    while (i < len) {
        for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
            sb[idx].append(c[i++]);
        for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
            sb[idx].append(c[i++]);
    }
    for (int idx = 1; idx < sb.length; idx++)
        sb[0].append(sb[idx]);
    return sb[0].toString();
}


public solution2 {

	public String convert(String s, int numRows) {
		StringBuilder[] sb = new StringBuilder[numRows];
		char[] c = s.toCharArray();
		int len = s.length();
		for (int i = 0; i < numRows; i++) {
			sb[i] = new StringBuilder();//this is an important step to initialize it
		}
		int i = 0;
		while (i < len) {
			for (int index = 0; index < numRows && i < len; index++) {
				sb[index].append(c[i++]);// vertically down
			}

			for (int index = numRows - 2; index >= 1 && i < len; index--) {
				/**Why it should start from numRows - 2? Think of the example when numRows = 3
				 the starting point of obliquely going up is 1, which is numRows-2.*/
				sb[index].append(c[i++]);// obliquely up
			}
		}

		for (i = 1; i < numRows; i++) {
			sb[0].append(sb[i]);
		}
		return sb[0].toString();
	}

}
