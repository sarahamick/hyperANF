import java.util.*;

//author: Sarah Amick

public class HyperANF{

  private Graph g;
  private double n;
  private Set<Integer> vertices;

  public HyperANF(Graph g){
    this.g = g;
    this.n = this.g.size();
    vertices = g.vertices();
  }

  public int HyperANFonGraph(){

    Map<Integer, ApproxSet> c = new HashMap<>();
    for(int v : vertices){
      ApproxSet counter = new ApproxSet();
      counter.add(v);
      c.put(v, counter);
    }

    Map<Integer, ApproxSet> m = new HashMap<>();
    int d = 0;
    double reach = 0;

    while(reach<((n*n)/2)){
      reach = 0;
      for(int v : vertices){
        ApproxSet counter = new ApproxSet();
        counter.add(v);
        counter.addSet(c.get(v));
        m.put(v, counter);
        for(int adjV : g.neighbors(v)) m.get(v).addSet(c.get(adjV));
        reach = reach + m.get(v).estimateSize();
      }
      for(int v : vertices) c.put(v, m.get(v));
      d = d+1;
    }
    return d;
  }
}
