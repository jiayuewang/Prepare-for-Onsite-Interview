package JobInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class Node{
    int val;
    Node next;
    Node pre;
    public Node(int v){
        val = v;
        next = null;
        pre = null;
    }
}

class RandomSet{
    Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
    Node head = new Node(0);
    Node tail = new Node(-1);
    Random random = new Random();
    public RandomSet(){
        head.next = tail;
        tail.pre = head;
    }
    
    public boolean insert(int val){
        boolean res = false;
        Node cur = new Node(val);
        List<Node> list = null;
        if (!map.containsKey(val)){
            list = new ArrayList<Node>();
            map.put(val, list);
            res = true;
        }else{
            list = map.get(val);
        }
        list.add(cur);
        //add node to tail
        add2tail(cur);
        return res;
    }
    
    public boolean remove(int val){
    	for (int key : map.keySet())
    		System.out.println(key);
        if (map.containsKey(val))
            return false;
        List<Node> list = map.get(val);
        Node node = list.get(list.size() - 1);
        //remove from head
        remove4head(node);
        
        list.remove(list.size() - 1);
        if (list.size() == 0)
            map.remove(val);
        return true;
    }
    
    private void add2tail(Node node){
        node.pre = tail.pre;
        tail.pre = node;
        node.next = tail;
        node.pre.next = node;
    }
    
    private void remove4head(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}

public class Insert_Delete_GetRandom_O1_Duplicates_allowed {
	public static void main(String[] args){
		/*RandomSet r = new RandomSet();
        boolean res = r.insert(1);
        System.out.println(res);
        
        res = r.insert(1);
        System.out.println(res);
        
        res = r.insert(2);
        System.out.println(res);
        
        res = r.remove(1);
        System.out.println(res);*/
        
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.print(list);
        list.remove("c");
        System.out.print(list);
    }
}
