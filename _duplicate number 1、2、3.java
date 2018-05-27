包含重复值1//On Sn
public class solution {
 public boolean containDuplicate（int[] nums）{
    Set<Integer> set = new HashSet<>();
    for(int i = 0; i< nums.length;i++){
        if(set.contains(nums[i])) return true;
        set.add(nums[i]);
    }
    return false;
 }
}
包含重复值2 On Sk
维护一个大小为k的窗口
public class solution {
 public boolean containDuplicate（int[] nums）{
    Set<Integer> set = new HashSet<>();
    for(int i = 0; i< nums.length;i++){
        if(set.contains(nums[i])) return true;
        set.add(nums[i]);
        if（set.size()>k）set.remove(nums[i-k]);//将窗口第一个元素remove掉
    }
    return false;
 }
}

包含重复值3
二叉搜索树， Onlogk Sk


维护一个大小为k的窗口
public class solution {
 public boolean containDuplicate（int[] nums）{
    TreeSet<Integer> set = new TreeSet<>();//红黑树
    for(int i = 0; i< nums.length;i++){
        int x = nums[i];

        if(set.ceiling(x) != null&& set.ceiling(x)<= t+x) return true;//最大小于x的数字，ceiling找最小的较大值
        if(set.floor(x)!= null && x <= t+set.floor(x)) return true;//最小大于x的数字----》 比x小的最大值
        set.add(nums[i]);
    }
    return false;
 }
}