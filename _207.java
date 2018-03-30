package com.fishercoder.solutions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**There are a total of n courses you have to take, labeled from 0 to n - 1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 For example:
 2, [[1,0]]
 There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.

 2, [[1,0],[0,1]]
 There are a total of 2 courses to take.
 To take course 1 you should have finished course 0,
 and to take course 0 you should also have finished course 1. So it is impossible.

 Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 click to show more hints.


 Hints:
 This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
 Topological sort could also be done via BFS.*/



//  典型的拓扑排序。在一个有向图中，每次找到一个没有前驱节点的节点（也就是入度为0的节点），
//  然后把它指向其他节点的边都去掉，重复这个过程（BFS），直到所有节点已被找到，或者没有符合条件的节点（如果图中有环存在）。

// 回顾一下图的三种表示方式：边表示法（即题目中表示方法），邻接表法，邻接矩阵。用邻接表存储图比较方便寻找入度为0的节点。


class Solution {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
    if (numCourses <= 0)
        return false;
    Queue<Integer> queue = new LinkedList<>();
    int[] inDegree = new int[numCourses];
    for (int i = 0; i < prerequisites.length; i++) {
        inDegree[prerequisites[i][1]]++;//一维数组in来表示每个顶点的入度。我们开始先根据输入来建立这个有向图，并将入度数组也初始化好。
    }
    for (int i = 0; i < inDegree.length; i++) {
        if (inDegree[i] == 0)// //然后我们定义一个queue变量，将所有入度为0的点放入队列中，然后开始遍历队列，

            queue.offer(i);
    }
    while (!queue.isEmpty()) {//然
        int x = queue.poll();//将inDegree为0的一个一个poll出来做dfs
        for (int i = 0; i < prerequisites.length; i++) {//遍历所有课程
            if (x == prerequisites[i][0]) {//3->4 转化为(4,3)，3作为先修课，进行取出
                inDegree[prerequisites[i][1]]--;
                if (inDegree[prerequisites[i][1]] == 0)
                    queue.offer(prerequisites[i][1]);//每到达一个新节点，将其入度减一，如果此时该点入度为0，则放入队列末尾。offer是放在对位、、队尾
            }
        }
    }
    for (int i = 0; i < inDegree.length; i++) {
        if (inDegree[i] != 0)
            return false;
    }
    return true;
}
}


上面这个解法是错的。。。。计算成了出度，就当没看见，继续往下看


//p[]是每个边，p[0]是入点，p[1]是出点，出度表示从该点出去的边数
// 可以看成判断有没有环，找环路，拓扑排序就是找入度为0的元素。 每一次找到所有入度的点
// 使用BFS实现，定义二维数组graph来表示这个有向图，一维数组in来表示每个顶点的入度。
// 我们开始先根据输入来建立这个有向图，
// 并将入度数组也初始化好。然后我们定义一个queue变量，
// 将所有入度为0的点放入队列中，然后开始遍历队列，
// 从graph里遍历其连接的点，每到达一个新节点，将其入度减一，
// 如果此时该点入度为0，则放入队列末尾。直到遍历完队列中所有的值，
// 若此时还有节点的入度不为0，则说明环存在，返回false，反之则返回true.

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int i, tmp, size = prerequisites.length;
        int[] inDegree  = new int[numCourses];
        Queue<Integer> q = new LinkedList<Integer>();

        for (i = 0; i < size; i++)
            inDegree[prerequisites[i][0]]++;
        for (i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) q.add(i);
        int num = q.size();

        while (!q.isEmpty()) {
            tmp = q.remove();

            for (i = 0; i < size; i++) {
                if (prerequisites[i][1] == tmp) {
                    inDegree[prerequisites[i][0]]--;
                    if (inDegree[prerequisites[i][0]] == 0) {
                        num++;
                        q.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return num == numCourses;
    }
}
//  On On