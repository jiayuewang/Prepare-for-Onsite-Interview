200. Number of Islands


Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
class Solution {
    int m;
    int n;
    public int numIslands(char[][] grid) {
        int res = 0;
         m = grid.length;
        if (m == 0) return 0;
         n = grid[0].length;
       // if (m == 0) return 0;要在定义n之前检查m
        for (int i = 0; i < m; i++ ){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'){
                    dfs(grid, i,j);
                    res++;
                }
            }
        }
        return res;
    }
    private void dfs(char[][] grid, int i, int j){
        if (i < 0 ||j < 0||i >= m || j >= n||grid[i][j] == '0' ) return;
            grid[i][j] = '0';
            dfs(grid, i, j+1);
            dfs(grid, i+1, j);
            dfs(grid, i, j-1);
            dfs(grid, i-1, j);
        }
    
}