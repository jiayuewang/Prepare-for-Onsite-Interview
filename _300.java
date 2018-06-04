ublic static int lengthOfLIS(int[] nums) {
        //用来存储当前的增序数组，初始化为全0
        int [] res = new int[nums.length];
        int len = 0;
        //对nums中的每个元素找到其相应的插入位置
        for(int x:nums){
            //使用二叉搜索找到x应该插入的位置索引，如果没有相等元素则返回low+1的负值
            int i = Arrays.binarySearch(res, 0, len, x);
            //转换为正数
            if(i < 0) i = -(i+1);
            //将x插入相应位置，注意这里如果x比前面元素小，则对其进行替换
            res[i] = x;
            //len保存当前最大的增序数组长度，只有当i等于len时才会进行加一操作。
            if(i == len) len ++;
        }
        return len;
    }