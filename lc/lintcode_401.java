401. 排序矩阵中的从小到大第k个数 

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
   //建一个最小堆，然后poll k 次
   
   public class point {
       public int x, y, val;
       public point(int x, int y, int val) {
           this.x = x;
           this.y = y;
           this.val = val;
       }
   }
    Comparator<Point> comp = new Comparator<>(Point) {
        public int compare(Point left, Point right) {
            return left.val - right.val;
        }
    };


    public int kthSmallest(int[][] matrix, int k) {
        if (matrix = null || matrix.length ==0 || matrix[0].length == 0)
        return 0;//横纵坐标都要考虑进去

        if (k > matrix.length * matrix[0].length) {
            return 0;//大于最大边界
        }
        return horizontal(matrix, k);
    }

    private int horizontal(int[][] matrix, int k) {
        Queue<Point> heap = new PriorityQueue<Point>(k, comp);
        for (int i = 0; i < Math.min(matrix.length, k); i++) {
            heap.offer(new Point(i, 0, matrix[i][0]));
        }
        for (int i = 0; i < k - 1; i++) {
            Point curr = heap.poll();
            if (curr.y + 1 < matrix[0].length) {
                heap.offer(new Point(curr.x, curr.y + 1, matrix[curr.x][curr.y + 1]));
            }
        }
        return heap.peek().val;
    }
 }