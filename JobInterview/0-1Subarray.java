package JobInterview;

public class Zero_One_Subarray {
	//From Bloomburg
	//给出只含有0 和 1 两种value的array， 找出最长的以0 开头的 0101交替的subarray (要求连续的 不能skip).
	//examaple:  0 0 1 0 1 0 ----输出长度为5 的 0 1 0 1 0 
    //0 1 0 1 1 0 1---输出长度为4 的 0 1  0 1 （不是 0 1 0 1 0 1） 
	//开始还以为是DP,后来才懂要求连续的一段， two pointer 就可以解决 汗
	public static void main(String[] args){
        String res = find(new String[]{"0", "1", "0", "1", "1", "0", "1"});
        System.out.print(res);
    }
    
    private static String find(String[] arrs){
        String res = "";
        int j = 0;
        int i = 0;
        while(i < arrs.length && arrs[i] != "0")
            i++;
        j = i;
        
        for (i = j + 1; i < arrs.length; i++){
        	if (arrs[i] != arrs[i -1]){
        		if (i == arrs.length - 1){
                	String	temp = getString(arrs, j, i + 1);
                    if (temp.length() > res.length())
                        res = temp;
                }else
                	continue;
            }else{
            	String	temp = getString(arrs, j, i);
                if (temp.length() > res.length())
                    res = temp;
                j = i;
            }              
        }       
        
        return res;
    }
    
    private static String getString(String[] arrs, int j, int i){
        StringBuilder sb = new StringBuilder();
        for (int k = j; k < i; k++){
            sb.append(arrs[k]);
        }
        return sb.toString();
    }
}
