package com.fishercoder.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * 699. Falling Squares
 *
 * On an infinite number line (x-axis), we drop given squares in the order they are given.
 * The i-th square dropped (positions[i] = (left, side_length)) is a
 * square with the left-most point being positions[i][0] and sidelength positions[i][1].
 * The square is dropped with the bottom edge parallel to the number line, and
 * from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.
 * The squares are infinitely sticky on their bottom edge, and will
 * remain fixed to any positive length surface they touch (either the number line or another square).
 * Squares dropped adjacent to each other will not stick together prematurely.
 * Return a list ans of heights.
 * Each height ans[i] represents the current highest height of any square we have dropped,
 * after dropping squares represented by positions[0], positions[1], ..., positions[i].
*/
 // Example 1:

 // Input: [[1, 2], [2, 3], [6, 1]]
 // Output: [2, 5, 5]
 // Explanation:


 // After the first drop of positions[0] = [1, 2]:
 // _aa
 // _aa
 // -------
 // The maximum height of any square is 2.


 // After the second drop of positions[1] = [2, 3]:
 // __aaa
 // __aaa
 // __aaa
 // _aa__
 // _aa__
 // --------------
 // The maximum height of any square is 5.
 // The larger square stays on top of the smaller square despite where its center
 // of gravity is, because squares are infinitely sticky on their bottom edge.


 // After the third drop of positions[1] = [6, 1]:
 // __aaa
 // __aaa
 // __aaa
 // _aa
 // _aa___a
 // --------------
 // The maximum height of any square is still 5.

 // Thus, we return an answer of [2, 5, 5].


 // Example 2:

 // Input: [[100, 100], [200, 100]]
 // Output: [100, 100]
 // Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.

 // Note:
 // 1 <= positions.length <= 1000.
 // 1 <= positions[0] <= 10^8.
 // 1 <= positions[1] <= 10^6.
 // */

// 思路:俄罗斯方块，就是在一个一维的坐标轴上，依次从高处扔方块，求最大的高度，首先我们可以想到暴力解的做法，就是每当落入一个方块的时候，直接遍历求解最高的高度， 在求解最高高度的时候，就是寻找公共区间来判断最高高度。第二种我们可以采用线段树的做法。

// 题解1: 我们需要以下几个变量:最大高度，当前区间，当前版面上最大值。枚举每一个掉落方块，将方块的起始值拿出来记录，那么end值就是起始值加上这个方块的size。用一个变量baseheight来存储从起始到结束的当前最大高度，遍历所有的intervals，如果我们的intervals不在当前的区间，就跳过。更新baseheight为当前intervals中baseheight的最大值。更新当前矩阵的高度为方块的size加上baseheight中的最大值。每一次循环增加一个intervals。
// Time complexity:O(n^2) 
// Space complexity: O(n)

class Solution {
    private class Interval {
        int start, end, height;
        public Interval(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
    public List<Integer> fallingSquares(int[][] positions) {
        List<Interval> intervals = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int baseheight = 0;
        for (int[] position : positions) {
            Interval cur = new Interval(position[0], position[0] + position[1] - 1, position[1]);
            baseheight = Math.max(baseheight, getHeight(intervals, cur));
            res.add(baseheight);
        }
        return res;
    }
    private int getHeight(List<Interval> intervals, Interval cur) {
        int preHeight = 0;
        for (Interval i : intervals) {
            if (i.end < cur.start) continue;//没交集时continue
            if (i.start > cur.end) continue;//有交集更新最大高度值
            preHeight = Math.max(preHeight, i.height);
        }
        cur.height += preHeight;
        intervals.add(cur);
        return cur.height;
    }
}


// // 题解2:Map，用Map查找当前有交集的区间，取出第一个相交的区间，
// 如果当前区间不是开始区间，我们检查是否相交，
// 有相交时更新区间并添加到我们的范围数组中，
// // 当前区间没有intersection了就退出循环。
// Time complexity:O(n*log(n)) ，注意插入新的区间的复杂度是logn
// Space complexity: O(n)


class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> startHeight = new TreeMap<>();
        startHeight.put(0, 0); 
        int max = 0;
        for (int[] pos : positions) {
            int start = pos[0], end = start + pos[1];
            Integer from = startHeight.floorKey(start);
            int height = startHeight.subMap(from, end).values().stream().max(Integer::compare).get() + pos[1];
            //将新的区间都加进来并且更新
            max = Math.max(height, max);//在当前height和新的geight中更新最大值
            res.add(max);// remove interval within [start, end)
            int lastHeight = startHeight.floorEntry(end).getValue();
            startHeight.put(start, height);
            startHeight.put(end, lastHeight);
            startHeight.keySet().removeAll(new HashSet<>(startHeight.subMap(start, false, end, false).keySet()));
        }
        return res;
    }
}

//https://paper.dropbox.com/doc/699.-Falling-SquaresUber-Hard-QEr15EVWOHvTBMMrTBbH7
// my solution , image from link above