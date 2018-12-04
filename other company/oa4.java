import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {



    
    static HashMap<String, HashMap<String, String>> support = new HashMap<>();
    static void checkLocation (List<String> res, List<String> a, HashMap<String, Integer> army, String loc) {
  if (a.size() < 1) return;
  int max = -1; 
  for (int i = 0; i < a.size(); i++) {
   max = Math.max(army.get(a.get(i)), max);
  }
  Iterator<String> itr = a.iterator();
  while (itr.hasNext()) {
   String curA = itr.next();
   int strength = army.get(curA);
   if (strength < max) {
    //res.add(new FinalLoc(curA, "[dead]"));
    res.add(curA + " [dead]");
    if (support.containsKey(loc))
     support.get(loc).remove(curA);
    itr.remove();
   }
  }
  
  if (a.size() > 1) {
   itr = a.iterator();
   while (itr.hasNext()) {
    String curA = itr.next(); 
    //res.add(new FinalLoc(curA, "[dead]"));
    res.add(curA + " [dead]");
    if (support.containsKey(loc))
     support.get(loc).remove(curA);
    itr.remove();
   }
   
  } else if (a.size() == 1) {
   //res.add(new FinalLoc(a.get(0), loc));
   res.add(a.get(0) + " " + loc);
  }
 }
    static List<String> evaluateActions(List<String> actions) {
        HashMap<String, Integer> army = new HashMap<>();
  List<String> res = new ArrayList<>();
  HashMap<String, List<String>> location = new HashMap<>();
  int cas = 0;
  while (cas < actions.size()) {
   String[] line = actions.get(cas).split(" ");
   cas++;
   if (line[2].equals("Move")) {
    String a = line[0];
    String from = line[1];
    String to = line[3];
    if (!army.containsKey(a)) army.put(a, 1);
    if (!location.containsKey(from)) location.put(from, new ArrayList<>());
    if (!location.containsKey(to)) location.put(to, new ArrayList<>());
    location.get(to).add(a);
    location.get(from).remove(a);
   } else if (line[2].equals("Hold")) {
    String a = line[0];//army location 提取出来
    String from = line[1];
    if (!army.containsKey(a)) army.put(a, 1);
    if (!location.containsKey(from)) location.put(from, new ArrayList<>());
    location.get(from).add(a);
   } else if (line[2].equals("Support")) {
    String a1 = line[0];
    String a2 = line[3];
    String from = line[1];
    if (!location.containsKey(from)) location.put(from, new ArrayList<>());
    if (!support.containsKey(from)) support.put(from, new HashMap<>());
    if (!army.containsKey(a1)) army.put(a1, 1);// 记一下，一个军队suport另一个，记录supporter 位置，
    // sporter位置有战争，就不能supportl啦
    // 把
    if (!army.containsKey(a2)) army.put(a2, 1);
    location.get(from).add(a1);
    support.get(from).put(a1, a2);
    army.put(a2, army.get(a2) + 1);// 记录下来，处理support
   }
  }

  if (support.size() != 0) {
   for (String l : support.keySet()) {
    if (location.get(l).size() <= 1) continue;
    HashMap<String, String> supRel = support.get(l);
    for (String supporter : supRel.keySet()) {
     String supportee = supRel.get(supporter);
     army.put(supportee, army.get(supportee) - 1);//location >= 2发生战争，就不能suport 
    }
   }
   for (String l : support.keySet()) {
    checkLocation(res, location.get(l), army, l);
    location.remove(l);
   }
   for (String l : support.keySet()) {
    HashMap<String, String> supRel = support.get(l);
    for (String supporter : supRel.keySet()) {
     String supportee = supRel.get(supporter);
     army.put(supportee, army.get(supportee) + 1);//
    }
   }
  }
  for (String loc : location.keySet()) {
   checkLocation(res, location.get(loc), army, loc);
  }
  Collections.sort(res);
    return res;
    }

    public static void main(String[] args) throws IOException {