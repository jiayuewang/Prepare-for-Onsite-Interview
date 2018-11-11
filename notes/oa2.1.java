
class Product {
  int index, space;
  Product(int index, int space) {
    this.index = index;
    this.space = space;
  }
}

//。。。。。。。。 improve modularity and security of the code
//index --> package space
//

public int[] func(ArrayList<Integer> packagesSpace, int len) {

    List<Product> products = new ArrayList<>();
    int[] res = new int[2];//Attributes

    for (int i = 0; i < packagesSpace.size(); i++) {
      products.add(new Product(i, packagesSpace.get(i)));
    }
    
    Collections.sort(products, (a, b) -> a.space - b.space);
    
    int left = 0, right = products.length - 1
  
    while (left < right) {    
         while (left < right && products.get(left).space + products.get(right).space< len - 30) {
          left++;
        }         
        while (left < right && products.get(left).space + products.get(right).space > len - 30) {
          right--;
        }



        if (left < right && products.get(left).space + products.get(right).space == len - 30){

          res[0] = Math.min(products.get(left).index, products.get(right).index);
          res[1] = Math.max(products.get(left).index, products.get(right).index);  
          return res;
          //the first
          //With the double pointer method, the first pair of results will contain our maximum value.
        }
    }
    
    return null;
}




解法2，

public int[] func(ArrayList<Integer> packagesSpace, int len) {

AllayList<Integer> ans = new ArrayList<>();
Integer[] indexes = new Integer[packagesSpace.size()];

for(int i = 0; i < index.length; i++){
  indexes[i] = i;
}
Arrays.sort(indexs, new Comparator<Integer>(){
  public int compare(Integer o1, Integer o2){
    if(packagesSpace.get(o1) != packagesSpace.get(o2)) {
      return packagesSpace.get(o1) - packagesSpace.get(o2);
    }
    return o2 - o1;
  }
});
int left = 0, right = indexes.length -1;
int maxIdOne = -1, maxIsTwo = -1, maxLen = 0;
while(left < right){
  while(left < right && packagesSpace.get(indexes[left]) + packagesSpace.get(indexes[right])< 30+maxLen){
    left++;
  }
  while(left < right && packagesSpace.get(indexes[left]) + packagesSpace.get(indexes[right])> 30+maxLen){
    right--;
  }
  if(packagesSpace.get(indexes[left]) + packagesSpace.get(indexes[right])== truckSpace){
    left++;
  )
}

 The first alternative method that i can think of is ...

 Loop through each element xx and find if there is another value 
 that equals to target - xtarget−x.


public int[] func(ArrayList<Integer> packagesSpace, int len) {
  int left = 0, right = 0;
    for (int i = 0; i < packagesSpace.size(); i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (packagesSpace.get(j) == len - packagesSpace.get(i)){
                left1 = i, right1 = j;
                if(Math.max(packagesSpace.get(j),packagesSpace.get(i) > 
                  Math.max(packagesSpace.get(right),packagesSpace.get(left)){
                  right = right1;
                  left = left1;
                }
              }
            }
          }
    if(packagesSpace.get(left) == len - packagesSpace.get(right1)){
      return new int[]{left, right};
    }
    throw new IllegalArgumentException("No two sum solution");
}
*******

public int[] func(ArrayList<Integer> packagesSpace, int len) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] ans =new int[2];
    int temp = 0;
    for (int i = 0; i < nums.length; i++) {
        int complement = length - packagesSpace.get(i) - 30;
        if (map.containsKey(complement)) {
            large = Math.max(packagesSpace.get(i), complement);
            if(temp < laege){
              temp = large;
              ans[0] = map.get(complement);
              ans[i] = i;
            }
          }
          map.put(packages.get(i), i);
   }
   return ans;
  }
}
























//recurtionaly traverse and divide and conquer
// recursion, traverse each node and caculate the sum of each subtree and caculate 
// numbers of children in each subtree.
// in the meatime , caompare avaerage value, if current value larger than the previous one,
// 为update the value
// we need 投designe a new class, since when we do recurtion, we need to returen dirrent paraters 
//to the root. like cur count and sum as well as the root of current subtree.
// when we find the maximum sunbtree
// return the root of the class tppe that we define.
// O n   O h（constants）

  private class resultType{
        public int count = 0;
        public int sum = 0;
        public TreeNode node;
        public resultType(int count, int sum， TreeNode node){
            this.count = count;
            this.sum = sum;
            this.node = node;
        }
    }
    // divide and conquer
    // we desine a class 
    //Member variables, node is the root of subtree, sum is the current sum of the subtree and
    // count is the number of children
    // are the current sum and number .....

    
   // Recursively traverse each node, traversing while 
   //recording the value of each node, and updating count and sum
    
    resultType max = null;//Global variable
    // subtree represents the root node of the current maximum average
     // represents the ResultType of the current maximum average
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        helper(root);//Pass root to the helper function, 
        //which helps us record the average of the subtree
        return max.node;//result
    }
 
    
    private resultType helper(TreeNode root){
        
        if (root == null){
            return new resultType(0, 0, null);
        }
         //Then return to the root node of this subtree. This question is added, the return can not be the leaf festival
//Point (because the leaf node has no subordinates), it must be a node with child nodes.
        if (root.left == null && root.right == null) {
            return new resultType(1, root.val, root);
        }
        
        int count = 1, sum = root.val;//record this root
     // divide and conquer   
       // divide: calculate maximum value of sum and count in each subtree 
        //conquer： accumulate sum of count and sum
        for (TreeNode child : root.children) {
            count += helper(child).count;
            sum += helper(child).sum;
            //The root node of the upper layer
        }        
        //double curAve = (double) curSum/curCnt;
        if (max == null || sum * max.count > max.sum * count){
            max = new resultType(count, sum, root);
        }
        // // //这里看一下最大值要不要更新
        // if (resAve < curAve){
        //     resAve = curAve;
        //     result = root;
        // }
        
        return new resultType(count, sum, root);
    }