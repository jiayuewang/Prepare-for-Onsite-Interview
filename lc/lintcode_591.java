public class ConnectingGraph3 {
    /*
    * @param n: An integer
    */
    private int[] father = null;
    private int count;
    private int find(int x) {
        if(father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    public ConnectingGraph3(int n) {
        father = new int[n + 1];
        count = n;
        for(int i = 1; i <= n; ++i) {
            father[i] = i;
        }
        // do intialization if necessary
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count --;
        }
    }
        
    public int query() {
        // Write your code here
        return count;
    }
}