hash的基础概念。
判断一个字符串是否是数字，包括小数负数等情况，然后写很多testcase，Python用的是unittest。电面完50分钟给结果。
homework：就是plag detection，简单hash。注意考虑各种情况，包括文件不存在，字符串太短等等。写完当天给结果。

onsite：1 午饭（纯粹聊天，非behavior）2 tour 3 tech面1：hash的基础概念，什么是hash function， array vs linkedlist，two sum, 判断一个string是否是另一个的substring（我本来以为动态规划结果仔细一想不就是简单匹配吗浪费了好久时间），翻转链表。4 behavior：为什么选Tripadvisor，如何看待团队矛盾等等。5tech2：是个呆了18年的大佬。inner join vs outer join, 几个使用outer join和union 的查询，case查询，hash function, 翻转链表again，罗马数字转普通数字（leetcode原题）。
总结：题目感觉不是很难，挂在哪真的不好说，可能不够深入？TripAdvisor总部在needham，距离波士顿市区二三十公里，周围啥也没有。另外TripAdvisor不是很大吧，虽然3000员工只有10%是engineer。好处是几乎全是白人，没有印度人。


静态方法的好处就是不用生成类的实例就可以直接调用。

static方法修饰的成员不再属于某个对象，而是属于它所在的类。只需要通过其类名就可以访问，不需要再消耗资源反复创建对象。

在类第一次加载的时候，static就已经在内存中了，直到程序结束后，该内存才会释放。

如果不是static修饰的成员函数，在使用完之后就会立即被JVM回收。



什么时候使用static？

如果这个方法是作为一个工具来使用的，就声明为static，不需要new一个对象就可以使用。比如：connect DB就可以声明一个Connection()的static方法，

因为是静态的，说明connection DB不是某个对象所特有的功能，只是作为一种连接到DB的工具。

The advantage of static methods is that they can be called directly without generating an instance of the class.

The member modified by the static method no longer belongs to an object, but belongs to the class it is in. It only needs to be accessed by its class name, and it does not need to consume resources to create objects repeatedly.

When the class is first loaded, static is already in memory, and the memory is not released until the program ends.

If it is not a statically modified member function, it will be reclaimed by the JVM immediately after use.



When do you use static?

If this method is used as a tool, it is declared static and can be used without a new object. For example: connect DB can declare a static method of Connection (),

Because it is static, the connection DB is not a feature unique to an object, just as a tool to connect to the DB.

1.  HashCode的特性

（1）HashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，HashCode经常用于确定对象的存储地址；

（2）如果两个对象相同， equals方法一定返回true，并且这两个对象的HashCode一定相同；

（3）两个对象的HashCode相同，并不一定表示两个对象就相同，即equals()不一定为true，只能够说明这两个对象在一个散列存储结构中。

（4）如果对象的equals方法被重写，那么对象的HashCode也尽量重写。


(1) The existence of HashCode is mainly used for the shortcut of searching, such as Hashtable, HashMap, etc., 
HashCode is often used to determine the storage address of the object;

(2) If the two objects are the same, the equals method must return true, 
and the HashCode of the two objects must be the same;

(3) The HashCode of two objects is the same, and does not necessarily mean that
 two objects are the same, that is, equals() is not necessarily true, 
 only the two objects can be described in a hash storage structure.

(4) If the object's equals method is overridden, then the object's HashCode 
is also rewritten as much as possible.

how to implement hash map
how to purge data in cache
explain inheritance and encapsulation
how to handle error
how to handle data of huge sizes
how does asynchronous programming change the way websites are designed
explain ajax in javascript


Hash code

252. Meeting Rooms
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals,(x, y)-> x.start - y.start);
        for(int i = 1;i < intervals.length; i++){
            if(intervals[i -1].end > intervals[i].start){
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public int minMeetingRooms(Interval[] intervals) {
            int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int res = 0;
        int j = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (starts[i] < ends[j]) {
                res++;
            } else j++;
        }
        return res;
    }

    follow up是如果你的用户不仅想知道不重复的还想知道重复的，你用什么数据结构去表示，


    一开始说用一个map，key是0的话value就是一个不重叠的时间段组成的list，key是1就是重叠的时间段组成的list
    ，面试官说这样可以但是用户还是不能直观地看到哪里重叠，后来我就说用list of list，
    然后他才说这才是他想要的，或者建一个class

66 plusOne
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1 ;i>=0; i--){//剪完了等于0是可行的
            if(digits[i]< 9){
                digits[i]++;
                return digits;
            }
              if(digits[i]==9){
                digits[i]=0;//之后下一位继续判断到没到末尾
            }
        }
        int[] c=new int [digits.length+1];
        c[0]=1;
        return c; //return 数组不用写括号
    }
}

79. Word Search
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

public class Solution {
    static boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }
        
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }
        
        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) || 
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) || 
           search(board, word, i, j+1, index+1)){
            return true;
        }
        
        visited[i][j] = false;
        return false;
    }
}


    public void test1() {
        board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        assertEquals(true, solution1.exist(board, "ABCEFSADEESE"));
        assertEquals(true, solution2.exist(board, "ABCEFSADEESE"));
    }
