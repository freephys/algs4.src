/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class Arbitrage
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 55 */     int i = StdIn.readInt();
/* 56 */     String[] arrayOfString = new String[i];
/*    */ 
/* 59 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(i);
/*    */     DirectedEdge localDirectedEdge;
/* 60 */     for (int j = 0; j < i; j++) {
/* 61 */       arrayOfString[j] = StdIn.readString();
/* 62 */       for (int k = 0; k < i; k++) {
/* 63 */         double d2 = StdIn.readDouble();
/* 64 */         localDirectedEdge = new DirectedEdge(j, k, -Math.log(d2));
/* 65 */         localEdgeWeightedDigraph.addEdge(localDirectedEdge);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 70 */     BellmanFordSP localBellmanFordSP = new BellmanFordSP(localEdgeWeightedDigraph, 0);
/*    */     double d1;
/*    */     Iterator localIterator;
/* 71 */     if (localBellmanFordSP.hasNegativeCycle()) {
/* 72 */       d1 = 1000.0D;
/* 73 */       for (localIterator = localBellmanFordSP.negativeCycle().iterator(); localIterator.hasNext(); ) { localDirectedEdge = (DirectedEdge)localIterator.next();
/* 74 */         StdOut.printf("%10.5f %s ", new Object[] { Double.valueOf(d1), arrayOfString[localDirectedEdge.from()] });
/* 75 */         d1 *= Math.exp(-localDirectedEdge.weight());
/* 76 */         StdOut.printf("= %10.5f %s\n", new Object[] { Double.valueOf(d1), arrayOfString[localDirectedEdge.to()] }); }
/*    */     }
/*    */     else
/*    */     {
/* 80 */       StdOut.println("No arbitrage opportunity");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Arbitrage
 * JD-Core Version:    0.6.2
 */