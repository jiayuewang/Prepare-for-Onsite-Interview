import java.util.*;


class FinalLoc implements Comparable<FinalLoc> {
 String a;
 String status;
 FinalLoc (String a, String status) {
  this.a = a;
  this.status = status;
 }
 public String toString() {
  return a + " " + status;
 }
 @Override
    public int compareTo(FinalLoc f) {
        return this.a.compareTo(f.a);
    }
}


public class MoveArmy {
 public static HashMap<String, HashMap<String, String>> support = new HashMap<>();
 public static void checkLocation (List<FinalLoc> res, List<String> a, HashMap<String, Integer> army, String loc) {
  int max = -1;
  for (int i = 0; i < a.size(); i++) {
   max = Math.max(army.get(a.get(i)), max);
  }
  Iterator<String> itr = a.iterator();
  while (itr.hasNext()) {
   String curA = itr.next();
   int strength = army.get(curA);
   if (strength < max) {
    res.add(new FinalLoc(curA, "[dead]"));
    if (support.containsKey(loc))
     support.get(loc).remove(curA);
    itr.remove();
   }
  }
  
  if (a.size() > 1) {
   itr = a.iterator();
   while (itr.hasNext()) {
    String curA = itr.next(); 
    res.add(new FinalLoc(curA, "[dead]"));
    if (support.containsKey(loc))
     support.get(loc).remove(curA);
    itr.remove();
   }
   
  } else if (a.size() == 1) {
   res.add(new FinalLoc(a.get(0), loc));
  }
 }

 public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  int cas = Integer.parseInt(input.nextLine());
  HashMap<String, Integer> army = new HashMap<>();
  List<FinalLoc> res = new ArrayList<>();
  HashMap<String, List<String>> location = new HashMap<>();
  
  while (cas-- > 0) {
   String[] line = input.nextLine().split(" ");
//   for (String s : line)
//    System.out.print(s + " ");
//   System.out.println(line[0]);
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
    String a = line[0];
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
    if (!army.containsKey(a1)) army.put(a1, 1);
    if (!army.containsKey(a2)) army.put(a2, 1);
    location.get(from).add(a1);
    support.get(from).put(a1, a2);
    army.put(a2, army.get(a2) + 1);
   }
  }
  input.close();
  if (support.size() != 0) {
   for (String l : support.keySet()) {
    if (location.get(l).size() <= 1) continue;
    HashMap<String, String> supRel = support.get(l);
    for (String supporter : supRel.keySet()) {
     String supportee = supRel.get(supporter);
     army.put(supportee, army.get(supportee) - 1);
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
     army.put(supportee, army.get(supportee) + 1);
    }
   }
  }
  for (String loc : location.keySet()) {
   checkLocation(res, location.get(loc), army, loc);
  }
  Collections.sort(res);
  System.out.println(res);

 }

}

/*
3
A Munich Hold
B Nohemia Move Munich
C Warsaw Support B

A [dead]
B Munich
C Warsaw

4
A Munich Hold
B Bohemia Move Munich
C Prussia Move Munich
D Warsaw Hold

A [dead]
B [dead]
C [dead]
D Warsaw

2
A Munich Hold
B Warsaw Move Bohemia

A Munich
B Bohemia

4
A Munich Support B
B Bohemia Move Prussia
C Prussia Hold
D Warsaw Move Munich

A B C D [dead]

2
A Munich Support B
B Oakland Move Munich

A B [dead]
*/