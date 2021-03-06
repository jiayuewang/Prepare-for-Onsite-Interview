  public int maxA(int N) {
        int[] dp = new int[N+1];
        if(N <= 3) return N;
        dp[0] = 0;dp[1] = 1;dp[2] = 2;dp[3] = 3;
        for(int i=4;i<=N;i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = i - 4; j >= 3; j--) {
                dp[i] = Math.max(dp[i], dp[j] * (i-j-1));
            }
        }
        return dp[N];
    }

    这道题给了我们四个操作，
    分别是打印A，全选，复制，粘贴。每个操作都算一个步骤，给了我们一个数字N，
    问我们N个操作最多能输出多个A。我们可以分析题目中的例子可以发现，N步最少都能打印N个A出来，
    因为我们可以每步都是打印A。那么能超过N的情况肯定就是使用了复制粘贴，
    这里由于全选和复制要占用两步，所以能增加A的个数的操作其实只有N-2步，那么我们如何确定打印几个A，
    剩下都是粘贴呢，其实是个trade off，A打印的太多或太少，都不会得到最大结果，所以打印A和粘贴的次数要接近，
    最简单的方法就是遍历所有的情况然后取最大值，打印A的次数在[1, N-3]之间，粘贴的次数为N-2-i，加上打印出的部分，
    就是N-1-i了

