int subarraySum(vector<int>& nums) {
    int sum = 0, n = nums.size();
    for (int i=0; i<n; i++)
        sum += nums[i]*(i+1)*(n-i);
    return sum;
}


# subarray sum

```java
public static int solution(int[] i1) {
        int left = 0, right = 0, temp = i1[left], sum = 0, len = i1.length;
        while(left < len) {
            sum += temp;
            if(right < len-1) {
                right++;
                temp += i1[right];
            } else {
                left++;
                right = left;
                if(left < len) temp = i1[left];
            }
        }
        return sum;
}
```

```Java
Public int sub(int[] a){
	int g = a[0];
	int res = g;
	for(int I = 1; I < a.length; I++){
		g += (I+1) * a[I];
		res += g;
	}
	return res;
}
```

vector<vector<int>> socialNetwork(vector<int>& counts) {
    map<int, vector<int>> groups;
    for (int i=0; i<counts.size(); i++)
        groups[counts[i]].push_back(i);
    vector<vector<int>> res;
    for (auto k : groups) {
        vector<int> e = k.second;
        int sz = k.first;
        int group_sz = e.size()/sz;
        for (int i=0; i<group_sz; i++) {
            vector<int> temp;
            for (int j=0; j<sz; j++)
                temp.push_back(e[i*sz + j]);
            res.push_back(temp);
        }
    }
    sort(res.begin(), res.end(), [](vector<int>& a, vector<int>& b){
        return a[0]<b[0];
    });
    return res;
}

int main() {
    vector<int> a = {3,3,3,3,3,1,3};
    vector<vector<int>> r1 = socialNetwork(a);
    for (auto v : r1) {
        for (auto i : v)
            cout << i << " ";
        cout << endl;
    }
    return 0;
}

# social network

```java
public class MyClass {
    
    public static List<List<Integer>> solution(int[] i1) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < i1.length; i++){
            if(!map.containsKey(i1[i])) {
                map.put(i1[i], new ArrayList<>());
            }
            map.get(i1[i]).add(i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(Integer key : map.keySet()){
            List<Integer> all = map.get(key);
            int group = all.size() / key;
            for(int i = 0; i < group; i++){
                List<Integer> temp = new ArrayList<>();
                for(int j = 0; j < key; j++){
                    temp.add(all.get(i * key + j));
                }
                res.add(temp);
            }
        }
        Collections.sort(res, new Comparator<List<Integer>>(){
           public int compare(List<Integer> l1, List<Integer> l2) {
               return l1.get(0) - l2.get(0);
           } 
        });
        return res;
    }
    
    public static void main(String args[]) {
        int[] x= new int[]{3,3,3,3,3,1,3};
        
        // int[] res = solution(x);
        // for(int i = 0; i < res.length; i++) {
        //     System.out.println("" + res[i]);    
        // }

        List<List<Integer>> res = solution(x);
        for(List<Integer> r: res) {
            for(Integer i: r) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
    }
}

package linkedin;

import java.util.*;
import java.util.stream.Collectors;

public class GetMinimumUniqueSum {
	
	private static class Element implements Comparable<Element> {
		int val;
		int index;

		public Element(int v, int i) {
			this.val = v;
			this.index = i;
		}

		public int compareTo(Element other) {
			return Integer.compare(this.val, other.val);
		}

		public int getIndex() {
			return this.index;
		}

		public int getValue() {
			return this.val;
		}

		@Override
		public String toString() {
			return "Element [val=" + val + ", index=" + index + "]";
		}
		
		
	}

	static int[] counts(int[] nums, int[] maxes) {
		Arrays.sort(nums);
		int n = maxes.length;
		Element[] arr = new Element[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new Element(maxes[i], i);
		}
		Arrays.sort(arr);
		int[] res = new int[n];
		int index = 0;
		for (int i = 0; i < n; i++) {
			while (index < nums.length && nums[index] <= arr[i].val) {
				index++;
			}
			res[arr[i].index] = index;
		}
		
		return res;
	}

	static int getMinimumUniqueSum(int[] arr) {
		int n = arr.length;
		Set<Integer> counts = new HashSet<>();
		counts.add(arr[0]);
		int sum = arr[0];
		for (int i = 1; i < n; i++) {
			int num = arr[i];
			while (counts.contains(num)) {
				num++;
			}
			counts.add(num);
			sum += num;
		}

		return sum;
	}
	
	static int getUmbrellas(int n, List<Integer> p) {
		if (n == 0) {
			return 0;
		}
		Collections.sort(p);
		// nums[i] means amount of i returns nums[i] minimum number of coins
		int[] nums = new int[n + 1];

		// coins[0] + 1 may overflow if coins[0] == Integer.MAX_VALUE
		for (int i = p.get(0); i <= n; i++) {
			for (int coin : p) {
				if (coin > i) {
					break;
				}
				if (coin == i) {
					nums[i] = 1;
					break;
				}
				if (nums[i - coin] == 0) {
					// i - coin is not assigned yet
					continue;
				}
				int candidate = nums[i - coin] + 1;
				if (nums[i] == 0 || candidate < nums[i]) {
					// 0 means it's not assigned yet
					nums[i] = candidate;
				}
			}
		}

		if (nums[n] == 0) {
			return -1;
		}
		return nums[n];
	}
	
	private static class UnionFind {
		int[] id, size;
		int count;

		public UnionFind(int len) {
			this.id = new int[len];
			this.size = new int[len];
			this.count = len;
		}

		public boolean find(int p, int q) {
			return root(p) == root(q);
		}

		public void union(int p, int q) {
			int pi = root(p), qi = root(q);
			if (this.size[pi] < this.size[qi]) {
				this.id[pi] = qi;
				this.size[qi] += this.size[pi];
			} else {
				this.id[qi] = pi;
				this.size[pi] += this.size[qi];
			}
			this.count--;
		}

		public int root(int i) {
			while (i != id[i]) {
				id[i] = id[id[i]]; // path compression
				i = id[i];
			}
			return i;
		}
	}

	public static int zombieCluster(List<String> zombies) {
		if (zombies == null || zombies.isEmpty()) {
			return 0;
		}
		int m = zombies.size();
		int n = zombies.get(0).length();
		if (n == 0) {
			return 0;
		}
		
		UnionFind u = new UnionFind(n);
		for (int i = 0; i < n; i++) {
			u.id[i] = i;
		}
		for (int i = 0; i < m; i++) {
			String s = zombies.get(i);
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(j) == '1') {
					if (!u.find(i, j)) {
						u.union(i, j);
					}
				}
			}
		}

		return u.count;
	}

	public static long subarraySum(List<Integer> arr) {
		long sum = 0;
		int n = arr.size();
		for (int i = 0; i < n; i++) {
			sum += ((long) arr.get(i)) * (i + 1) * (n - i);
		}
		return sum;
	}
	
	public static void solution(List<Integer> counts) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < counts.size(); i++) {
			map.computeIfAbsent(counts.get(i), k -> new ArrayList<>()).add(i);
		}
		List<List<Integer>> res = new ArrayList<>();
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			int key = entry.getKey();
			List<Integer> val = entry.getValue();
			int group = val.size() / key;
			for (int i = 0; i < group; i++) {
				res.add(val.stream().skip(i * key).limit(key).collect(Collectors.toList()));
			}
		}
		Collections.sort(res, (a, b) -> Integer.compare(a.get(0), b.get(0)));
		for (List<Integer> l : res) {
			System.out.println(l.stream().map(String::valueOf).collect(Collectors.joining(" ")));
		}
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(counts(new int[]{2, 10,5,4,8,4}, new int[]{3, 1, 7, 8})));
	}
}

