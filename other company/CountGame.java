package JobInterview;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountGame {
	private static int target;	
    private List<String> answerList = new ArrayList<String>();
    public List<String> getAnswerList() {
        return answerList;
    }
    public class Data{
        public float[]  arr;
        public String expStr = "";
        public String[] strs;
        public Data(){}
        public Data(int arr[]) {
        	this.arr = new float[arr.length];
        	this.strs = new String[arr.length];
        	for (int i = 0; i < arr.length; i++){
        		this.arr[i] = Float.valueOf(arr[i]);
        		this.strs[i] = String.valueOf(arr[i]);
        	}
        }
    }
    public void count(Data data){
        float[] arr = data.arr;
        if(arr.length <= 1){
            if(arr.length == 1 && arr[0] == CountGame.target){
                answerList.add(data.expStr.substring(1, data.expStr.length() - 1));
            }
            return;
        }
        for(int i = 0,len = arr.length;i < len - 1; i++){
            for(int j = i+1; j < len; j++){
                float x = arr[i];
                float y = arr[j];
                String xs = data.strs[i];
                String ys = data.strs[j];
                for(int k = 0; k < 6; k++){
                    Data newData = getNewArr(data,i);
                    switch(k){
                        case 0:
                        newData.arr[j-1] = x + y;
                            newData.expStr=xs+"+"+ys;
                        break;
                        case 1:
                        newData.arr[j-1] = x - y;
                            newData.expStr=xs+"-"+ys;
                        break;
                        case 2:
                        newData.arr[j-1] = y - x;
                        newData.expStr=ys+"-"+xs;
                        break;
                        case 3:
                        newData.arr[j-1] = x * y;
                            newData.expStr = xs + "*" + ys;
                        break;
                        case 4:
                        if(y!=0){
                            newData.arr[j-1] = x/y;
                                newData.expStr = xs + "/" + ys;
                        }else {
                            continue;
                        }
                        break;
                        case 5:
                        if(x != 0){
                            newData.arr[j-1] = y/x;
                                newData.expStr = ys+"/"+xs;
                        }else {
                            continue;
                        }
                        break;
                    }
                    newData.expStr = "(" + newData.expStr + ")";
                    newData.strs[j-1] = newData.expStr;
                    count(newData);
                }
            }
        }        
    }
    private Data getNewArr(Data data, int i) {
        Data newData = new Data();
        newData.expStr = data.expStr;
        newData.arr = new float[data.arr.length-1];
        newData.strs = new String[data.arr.length-1];
        for(int m = 0, len = data.arr.length,n = 0; m < len; m++){
            if(m != i){
                newData.arr[n] = data.arr[m];
                newData.strs[n] = data.strs[m];
                n++;
            }
        }
        return newData;
    }
    
    public List<String> easyCount(int[] inputValues){
        CountGame count24 = new CountGame();
        count24.count(new Data(inputValues));
        Set<String> set = new HashSet<String>(count24.getAnswerList());//remove duplicated set
        return new ArrayList<String>(set);
    }
    
    public static void main(String[] args) {
    	CountGame count24 = new CountGame();
    	int[] inputValues = new int[]{};
    	try{
    		inputValues = count24.getInputValues();    		

        	List<String> answerStris= count24.easyCount(inputValues);
        	if (answerStris.size() == 0){
        		System.out.println("none");
        	}else{
        		for (String string : answerStris) {
        			System.out.println(string);
        		}
        	}
        	return;
    	}catch(Exception ex){
    		System.out.println("Input values are invalid!");
    	}    	
    }
	private int[] getInputValues() throws InterruptedException, IOException, NumberFormatException {
		System.out.println("Input values(please use white space to seperate them): ");
  	  
    	BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	String inputString = bufferRead.readLine();
    	
    	String[] inputArr = inputString.split(" ");
    			
    	target = Integer.valueOf(inputArr[0]);
    	int[] inputValues = new int[inputArr.length - 1];
    	for(int i = 1; i < inputArr.length; i++){
    		inputValues[i - 1] = Integer.valueOf(inputArr[i]);
    	}
    	return inputValues;
	}
}