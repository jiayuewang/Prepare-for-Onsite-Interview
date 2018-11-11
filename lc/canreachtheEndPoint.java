1,
public class Solution {
    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
     int[] dx = {0,1,0,-1};
     int[] dy = {1,0,-1, 1};
    public boolean reachEndpoint(int[][] map) {
        // Write your code here
        if(map == null || map.length == 0|| map[0].length == 0) return false;
        int m = map.length, n = map[0].length;
        boolean[][] visited = new boolean[m][n];
       return dfs(map,0,0,visited);
    }
    private boolean dfs(int[][] map, int x, int y, boolean[][] visited) {
        int m = map.length, n = map[0].length;//这行要写，因为这个block里的参数要重新定义
        if (x < 0|| x >= m || y < 0|| y >= n || map[x][y] == 0 || visited[x][y]) return false;
        if (map[x][y] == 9) return true;
        visited[x][y] = true;
    for (int i = 0; i < 4; i++) {
       if(dfs(map,x+dx[i],y+dy[i],visited)){
           return true;
       }
    }
    return false;
    
}
}









2,
class Coordinate {
    int row;
    int col;
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    int[] xDirections = new int[] {1, 0, -1, 0};
    int[] yDirections = new int[] {0, -1, 0, 1};
    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        // Write your code here
        return dfs(new Coordinate(0, 0), map);
    }
    
    private boolean dfs(Coordinate coordinate, int[][] map) {
        if (map[coordinate.row][coordinate.col] == 9) {
            return true;
        }
        
        for (int i = 0; i < 4; i++) {
            int nextX = coordinate.row + xDirections[i];
            int nextY = coordinate.col + yDirections[i];
            
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                continue;
            }
            
            if (map[nextX][nextY] == 0) {
                continue;
            }
            
            if (map[nextX][nextY] == 1) {
                map[nextX][nextY] = 0;
            }
            
            if (dfs(new Coordinate(nextX, nextY), map)) {
                return true;
            };
        }
        
        return false;
    }
}