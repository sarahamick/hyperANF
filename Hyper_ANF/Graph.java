import java.util.*;

//author: Martin Aumüller, ITU

class Graph {
    HashMap<Integer, HashSet<Integer>> g = new HashMap<Integer, HashSet<Integer>>();

    void add(Integer u, Integer v) {
        g.putIfAbsent(u, new HashSet<Integer>());
        g.putIfAbsent(v, new HashSet<Integer>());
        g.get(u).add(v);
        g.get(v).add(u);
        //System.out.println("Added edge " + u + " to " + v);
    }

    HashSet<Integer> neighbors(Integer u) {
        return g.get(u);
    }

    Set<Integer> vertices() {
        return g.keySet();
    }

    int size(){
      return vertices().size();
    }
}
