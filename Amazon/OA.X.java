
    public static int maximumMinimumPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            
            int n = matrix.length;
            int m = matrix[0].length;
            
            int[][] dp = new int[n][m];      
            dp[0][0] = matrix[0][0];
            
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][0], matrix[i][0]); 
            }
            for (int i = 1; i < m; i++) {
                dp[0][i] = Math.min(dp[0][i - 1], matrix[0][i]);
            }
            
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j] = Math.min(Math.max(dp[i - 1][j], dp[i][j - 1]), matrix[i][j]);
                }
            }
            
            return dp[n - 1][m - 1];
        }


Number of substring with exactly K distinct character

Time(n^2)
Space(1)


    public class CountKSubStr{
    {
        int countkDist(String str, int k) {
            int res = 0;
            int len = str.length();
            int cnt[] = new int[26];
            for (int i = 0; i < len; i++)
            {
                int distCount = 0;
                Arrays.fill(cnt, 0);
                for (int j=i; j<len; j++)
                {
                    if (cnt[str.charAt(j) - 'a'] == 0)
                        distCount++;
                    cnt[str.charAt(j) - 'a']++;
                    if (distCount == k)
                        res++;
                }
            }
            return res;
        }
    }


Subtree with Maximum Average

Time(n)
Space(n)


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
        
        resultType max = null;
        
        public TreeNode findSubtree2(TreeNode root) {
            // write your code here
            helper(root);
            return max.node;
        }
        
        private resultType helper(TreeNode root){
            
            if (root == null){
                return new resultType(0, 0, null);
            }
    
            if (root.left == null && root.right == null) {
                return new resultType(1, root.val, root);
            }
            
            int count = 1, sum = root.val;
            
            for (TreeNode child : root.children) {
                count += helper(child).count;
                sum += helper(child).sum;
            }        
            
            if (max == null || sum * max.count > max.sum * count){
                max = new resultType(count, sum, root);
            }
            
            return new resultType(count, sum, root);
        }
Two Sum

Time(nlogn)
Space(n)


    class Product {
      int index, space;
      Product(int index, int space) {
        this.index = index;
        this.space = space;
      }
    }
    
    public int[] func(ArrayList<Integer> packagesSpace, int len) {
    
        List<Product> products = new ArrayList<>();
        int[] res = new int[2];
    
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
            if (left < right && products.get(left).space + products.get(right).space == len - 30 {
              res[0] = Math.min(products.get(left).index, products.get(right).index);
              res[0] = Math.max(products.get(left).index, products.get(right).index);  
              return res;
            }
        }
        
        return null;
    }