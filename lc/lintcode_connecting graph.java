public class ConnectingGraph {
    /*
    * @param n: An integer
    */
    private int[] father = null;
    private int find(int x) {
        if(father[x] == x) {
            return x;// self-
        }
        return father[x] = find(father[x]);
    }
    public ConnectingGraph(int n) {
        // do intialization if necessary
        father = new int[n+1];
        //点的下标是1-n，所以0-n总共是n+1个点


        for(int i = 1; i <= n; ++i) {
            father[i] = i;//每个点自己是自己的老大
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        if(root_a != root_b) {
            father[root_a] = root_b;
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        int root_a = find(a);
        int root_b = find(b);
        return root_a ==root_b;
    }
}