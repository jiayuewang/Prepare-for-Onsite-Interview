public int networkDelayTime(int[][] times, int N, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            if (!map.containsKey(time[0])) 
                map.put(time[0], new LinkedList<int[]>()); 
            map.get(time[0]).add(new int[]{time[1], time[2]}); 
        }
        Set<Integer> seen = new HashSet<>();
        seen.add(K); int res = 0;
        if (map.containsKey(K)) pq.addAll(map.get(K)); 
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (seen.contains(cur[0])) continue; 
            List<int[]> outList = map.get(cur[0]); 
            if (outList != null) { 
                for (int[] neighbor : outList) {
                    if (!seen.contains(neighbor[0])) 
                        pq.add(new int[]{neighbor[0], cur[1] + neighbor[1]}); 
                }
            }
            seen.add(cur[0]); 
            res = Math.max(res, cur[1]);
        }
        return seen.size() == N ? res : -1; 
    }