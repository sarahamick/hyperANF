import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
      Graph g = makeGraph();
      //int median = BFSonGraph(g);
      int median = HyperANFonGraph(g);
      System.out.println(median);
  }

  private static Graph makeGraph(){

    Graph g = new Graph();

    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      String stringLine = sc.nextLine();
      if(stringLine.startsWith("end")) break;
      else {
        String[] line = stringLine.split(" ");
        g.add(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
      }
    }
    return g;
  }

  private static int HyperANFonGraph(Graph g){
    HyperANF HyperANF = new HyperANF(g);
    return HyperANF.HyperANFonGraph();
  }
}
