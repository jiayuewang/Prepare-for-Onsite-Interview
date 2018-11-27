
https://www.jiuzhang.com/solution/number-of-distinct-islands-ii/


Record all the points along the path during DFS. Then add all the eight combinations of reflection, rotation into a array of points. From their, sort each list where time complexity would be O(n^2 * log(n^2)). Substract the first point's x and y value from all the points in the list, which identifies a shape uniquely.

In Java, HashSet only compares hashCode() and equals() function so we have to convert the shape into a String and we only need to take the smallest string out of the eight combinations for uniqueness comparison.

class Solution {
    private final int[] dir = {-1, 0, 1, 0, -1};
    private final int[][] refl = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        Set<String> set = new HashSet<>();

        List<Point> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dfs(grid, i, j, list);
                if (list.size() > 0) {
                    set.add(norm(list));
                    list.clear();
                }
            }
        }
        return set.size();
    }
    
    private void dfs(int[][] grid, int row, int col, List<Point> list) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) return;
        grid[row][col] = 2;
        list.add(new Point(row, col));
        for (int i = 0; i < 4; i++) {
            dfs(grid, row + dir[i], col + dir[i+1], list);
        }
    }
    
    @SuppressWarnings("unchecked")
	private String norm(List<Point> list) {
        List<Point>[] comb = new List[8];
        
        for (int i = 0; i < 4; i++) {
            comb[i] = new ArrayList<Point>();
            comb[i+4] = new ArrayList<Point>();
            for (Point p : list) {
                comb[i].add(new Point(p.x * refl[i][0], p.y * refl[i][1]));
                comb[i+4].add(new Point(p.y * refl[i][1], p.x * refl[i][0]));
            }
        }
        
        for (int i = 0; i < 8; i++) {
        	Collections.sort(comb[i]);
        }
        String[] s = new String[8];
        for (int i = 0; i < 8; i++) {
           	StringBuilder sb = new StringBuilder();
            int x0 = comb[i].get(0).x, y0 = comb[i].get(0).y;
        	for (Point p : comb[i]) {
        		sb.append(p.x - x0);
                sb.append(',');
                sb.append(p.y - y0);
                sb.append('!');
        	}
        	s[i] = sb.toString();
        }
        Arrays.sort(s);
        return s[0];
    }
    
    public static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point p) {
            return this.x - p.x == 0 ? this.y - p.y : this.x - p.x;
        }
        @Override
        public String toString() {
            return String.format("(%d,%d)", this.x, this.y);
        }
    }
}


使用union find, 对上下左右的联通块先进行判断到底有几个集合。
然后再把当前点和现存的集合merge，使用hashset避免重复merge进同一个集合。
比如:

X
X X
X
如果当前元素放入中间的话，最终的集合个数为5 - 4 = 1 （出现了三次merge）

比如:
X
XX
如果当前元素放右上的话最终集合个数为2 - 1 = 1
具体实现看代码comment。



//union find data structure
class UnionFind {
    private int [] father;
    public UnionFind(int n){
        father = new int[n];
        for (int i = 0; i < n; i++){
            father[i] = i;
        }
    }
    
    public int find(int x){
        if (x == father[x]){
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
        }
    }
}

public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
        if (operators == null || operators.length == 0){
            return new ArrayList <Integer> ();
        }
        
        //存已经发现的岛屿
        HashSet <Integer> exisiting = new HashSet <> ();
        List <Integer> ans = new ArrayList<>();
       
        UnionFind ds = new UnionFind (n * m);
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        //num维护的是当前联通块个数
        int num = 0;
        for (Point p : operators){
            //如果operators 出现重复，直接给他跳掉
            if (exisiting.contains(m * p.x + p.y)){
                ans.add(ans.get(ans.size() - 1));
                continue;
            }
            exisiting.add(m * p.x + p.y);
            //先把当前operator当作独立的联通块，一会看看能不能消掉
            num ++;
            //leaders维护上下左右总共有几个集合
            HashSet <Integer> leaders = new HashSet <> ();
            for (int i = 0; i < 4; i++){
                int x = p.x + dx[i];
                int y = p.y + dy[i];
                if (inBound(x, y, n ,m) && exisiting.contains(m * x + y)){
                    leaders.add(ds.find(m * x + y));
                }
            }
            //对上下左右存在的集合进行合并当前元素
            for (Integer v : leaders){
                ds.union(v, m * p.x + p.y);
                num --;
            }
            ans.add(num);
        }
        return ans;
    }
    
    public boolean inBound(int x, int y, int m, int n){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}