class Solution {// 倒着推，推到起始时候的血量
    //最后一行和最后一列初始化//格子为会回复的hp
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];//hp值，每一个点至少维持的血量
        //下一层减去上一层的血量并且和1做大小比较。
        dp[m - 1][n - 1] = Math.max(1-dugeon[m-1][n-1],1);
        //更新最后一行和最后一列
        for(int i = m -2; i >= 0; i--){//列数
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1 );
        }
        for(int i = n-2; i>=0; i--){//行数
            dp[m-1][i] = Math.max(dp[m-1][i+1] - dungeon[m-1][i],1)
        }
        for(int i= m-2; i >=0; i-- ){
            for(int j = n-2l j >= 0; j--){
          //当前值
                int down =Math.max(dp[i+1][j] -dungeon[i][j],1);//当前行
                int right = Math.max(dp[i][j+1] - dungeon[i][j],1)；//右一行
            }
        }
        return Math.max(down, right);
    }
}