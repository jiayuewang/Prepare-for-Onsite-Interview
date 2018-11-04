
// You are given a m x n 2D grid initialized with these three possible values.

// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

// For example, given the 2D grid:
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// After running your function, the 2D grid should be:
//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4




DFS: 找的时候如果当前点的值大于idx的值，就是符合条件的点。
复杂度: 时间 MN4^K, 空间 4^N
4思路：依然是一个DFS，遍历数组中的每一个元素，碰到原始是０的，也就是门，就以这个点开始做DFS．
然后向上下左右方向走，需要注意的是为了防止循环调用，我们需要判断一下如果下一个位置的元素比当前元素小或者相等，就没有必要再去那个位置
然后看答案是假如这个点没有更新 那从它开始的四周也就没必要再更新了 没想清楚 这样的话感觉是mn的时间复杂度？


//dfs solution
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }
    
    private void dfs(int[][] rooms, int x, int y, int val) {//val means the curr distance from gate
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < val) {//skip -1 and longer distance
            return;//should be rooms[x][y] < val, not rooms[x][y] <= val because this will skip the 0 (gate)
        }
        rooms[x][y] = val;//if intended val <= curr val, we will change the curr val
        dfs(rooms, x + 1, y, val + 1);
        dfs(rooms, x - 1, y, val + 1);
        dfs(rooms, x, y + 1, val + 1);
        dfs(rooms, x, y - 1, val + 1);
    }
}

//bfs solution:时间 O(NM) 空间 O(N)

实际上就是找每个房间到最近的门的距离，我们从每个门开始，广度优先搜索并记录层数就行了。
如果某个房间之前被标记过距离，那就选择这个距离和当前距离中较小的那个。这题要注意剪枝，如果下一步是门或者下一步是墙或者下一步已经访问过了，就不要加入队列中。否则会超时。
BFS的解法，借助queue来实现：
我的看上去麻烦一点：
我犯过的错，就是之前用depth来记录高度，其实完全不应该，因为更新距离应该是赋值rooms[i][j] + 1；出了好几次bug；

此题的精髓还是在于BFS，因为BFS能够保证每个点访问一次，并且能够保证每次访问都是最短距离：level order的厉害之处！！

时间复杂度为O(mn)，是因为第一步找到room为0的两重for循环时间复杂度为O(mn)，然后在BFS当中每个点访问once，所以时间复杂度也为O(mn)；

// We can understand it by level-order BFS.
// First we put all 0s to a queue, let's say these these 0s are in level 1. 
// Then from each 0 of the queue, we will go up, down, left and right, all these positions that are rooms are at level 1, and so forth.
//  So assume we only have Gate A and Gate B, and we have a room C and all the other positions are walls. 
//  Assume that distance between AC is 3 and distance between BC is 4. 
//  So for Gate A, room C is at its level 3, for Gate B, room C is at its level 4. Since we are doing level-order BFS, 
//  so C will always first be accessed by the gate that is closer to it, so it will be A.
 
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                // 如果遇到一个门，从门开始广度优先搜索，标记连通的节点到自己的距离
                if(rooms[i][j] == 0) bfs(rooms, i, j);
            }
        }
    }
    
    public void bfs(int[][] rooms, int i, int j){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i * rooms[0].length + j);
        int dist = 0;
        // 用一个集合记录已经访问过的点
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(i * rooms[0].length + j);
        while(!queue.isEmpty()){
            int size = queue.size();
            // 记录深度的搜索
            for(int k = 0; k < size; k++){
                Integer curr = queue.poll();
                int row = curr / rooms[0].length;
                int col = curr % rooms[0].length;
                // 选取之前标记的值和当前的距离的较小值
                rooms[row][col] = Math.min(rooms[row][col], dist);
                int up = (row - 1) * rooms[0].length + col;
                int down = (row + 1) * rooms[0].length + col;
                int left = row * rooms[0].length + col - 1;
                int right = row * rooms[0].length + col + 1;
                if(row > 0 && rooms[row - 1][col] > 0 && !visited.contains(up)){
                    queue.offer(up);
                    visited.add(up);
                }
                if(col > 0 && rooms[row][col - 1] > 0 && !visited.contains(left)){
                    queue.offer(left);
                    visited.add(left);
                }
                if(row < rooms.length - 1 && rooms[row + 1][col] > 0 && !visited.contains(down)){
                    queue.offer(down);
                    visited.add(down);
                }
                if(col < rooms[0].length - 1 && rooms[row][col + 1] > 0 && !visited.contains(right)){
                    queue.offer(right);
                    visited.add(right);
                }
            }
            dist++;
        }
    }
}


//bfs
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int coordinate = queue.poll();
                int x = coordinate / n;
                int y = coordinate % n;
                if (rooms[x][y] == Integer.MAX_VALUE) {//check 1 more time !!! else [0][0] in eg above will be 4 instead of 3
                    rooms[x][y] = level;
                }
                if (isValid(rooms, x + 1, y)) {
                    queue.offer((x + 1) * n + y);
                }
                if (isValid(rooms, x - 1, y)) {
                    queue.offer((x - 1) * n + y);
                }
                if (isValid(rooms, x, y + 1)) {
                    queue.offer(x * n + y + 1);
                }
                if (isValid(rooms, x, y - 1)) {
                    queue.offer(x * n + y - 1);
                }
            }
            level++;
        }
    }
    
    private boolean isValid(int[][] rooms, int x, int y) {
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] != Integer.MAX_VALUE) {
            return false;
        }
        return true;
    }
}