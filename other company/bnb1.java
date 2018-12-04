//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;

class Rextester
{  
    public static void main(String args[])
    {
        BankSystem bs = new BankSystem();
        System.out.println(bs.withdraw(0, 100, 0));  // false
        bs.deposite(0, 100, 1);
        bs.deposite(1, 250, 2);
        bs.withdraw(0, 30, 3);
        System.out.println(bs.check(0, 0, 2)[0]);  // 0
        System.out.println(bs.check(0, 0, 2)[1]);  // 100
        bs.deposite(1, 5, 7);
        System.out.println(bs.check(1, 3, 9)[0]);  // 250
        System.out.println(bs.check(1, 3, 9)[1]);  // 255
    }
}

/**
 * 设计一个银行帐户系统，实现：
 * 存钱（帐户id，存钱数目，日期）
 * 取钱（帐户id，存钱数目，日期）
 * 查账（帐户id，起始日期，结束日期）： 只需要返回两个数值，一个是起始日期的balance，一个是结束日期的balance。
 * 描述就是这么多，剩下的自己发挥。钱的类型用integer，日期什么的自定义，我直接拿了integer
 */
class BankSystem {
  Map<Integer, Integer> accountBalance;    // id -> balance
  Map<Integer, Map<Long, Integer>> accountStatement; // id -> timestamp -> balance
  public BankSystem() {
    this.accountBalance = new HashMap<>();
    this.accountStatement = new HashMap<>();
  }

  public void deposite(int id, int amount, long timestamp) {
    if (!accountBalance.containsKey(id)) {
      accountBalance.put(id, 0);
      accountStatement.put(id, new HashMap<>());
    }
    accountBalance.put(id, accountBalance.get(id) + amount);
    accountStatement.get(id).put(timestamp, accountBalance.get(id));
  }

  public boolean withdraw(int id, int amount, long timestamp) {
    if (!accountBalance.containsKey(id) || accountBalance.get(id) < amount) {
      return false;
    }

    accountBalance.put(id, accountBalance.get(id) - amount);
    accountStatement.get(id).put(timestamp, accountBalance.get(id));
    return true;
  }

  public int[] check(int id, long startTime, long endTime) {
    if (!accountBalance.containsKey(id)) {
      return new int[0];
    }

    int[] res = new int[2];
    Map<Long, Integer> statement = accountStatement.get(id);
    List<Long> timestamps = new ArrayList<>(statement.keySet());
    Collections.sort(timestamps);

    if (statement.containsKey(startTime)) {
      res[0] = statement.get(startTime);
    } else {
      int index = -(Collections.binarySearch(timestamps, startTime) + 1);
      res[0] = index == 0 ? 0 : statement.get(timestamps.get(index - 1));
    }
    if (statement.containsKey(endTime)) {
      res[1] = statement.get(endTime);
    } else {
      int index = -(Collections.binarySearch(timestamps, endTime) + 1);
      res[1] = index == 0 ? 0 : statement.get(timestamps.get(index - 1));
    }

    return res;
  }
}

