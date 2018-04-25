130. Heapify
//Heapify一个Array，也就是对array中的元素进行siftup或者siftdown的操作。根据min heap定义进行操作即可。

//这里值得注意的是，对于扫描整个array的情况下，siftup和siftdown有complexity上的区别。

//基本的原因在于：siftdown的complexity，实质上是node相对于bottom移动的次数，而根据binary heap本身的特性，决定了约靠近bottom的node越多；相对照的是siftup，是node相对于root节点的移动次数。

//如果Heapify可以用O(n)实现，那么HeapSort所需的时间复杂度为何是O(nlogn)？因为HeapSort其实包含了两个步骤，第一步，Heapify，build (min) heap，所需时间复杂度O(n)，第二步，依次删除最小值（min heap），对于Heap来说，删除操作的复杂度是O(logn)， 而这个删除需要执行O(n)，来得到最终sort的结果，于是总体时间复杂度是O(nlogn)。
public class Solution {
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    public void heapify(int[] A) {
        // write your code here
        for (int i =0; i<A.length; i++) {
            siftup(A,i);
        }
    }
    private void siftup(int[] A, int k) {
        while(k != 0) {
            int father = (k - 1) / 2;
            if(A[k] > A[father]){
                break;
            }
            int temp = A[k];
            A[k] = A[father];
            A[father] = temp;
            k=father;
        }
    }
}


    
 