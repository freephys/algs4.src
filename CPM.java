/*    */ public class CPM
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 61 */     int i = StdIn.readInt();
/*    */ 
/* 64 */     int j = 2 * i;
/* 65 */     int k = 2 * i + 1;
/*    */ 
/* 68 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(2 * i + 2);
/* 69 */     for (int m = 0; m < i; m++) {
/* 70 */       double d = StdIn.readDouble();
/* 71 */       localEdgeWeightedDigraph.addEdge(new DirectedEdge(j, m, 0.0D));
/* 72 */       localEdgeWeightedDigraph.addEdge(new DirectedEdge(m + i, k, 0.0D));
/* 73 */       localEdgeWeightedDigraph.addEdge(new DirectedEdge(m, m + i, d));
/*    */ 
/* 76 */       int i1 = StdIn.readInt();
/* 77 */       for (int i2 = 0; i2 < i1; i2++) {
/* 78 */         int i3 = StdIn.readInt();
/* 79 */         localEdgeWeightedDigraph.addEdge(new DirectedEdge(i + m, i3, 0.0D));
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 84 */     AcyclicLP localAcyclicLP = new AcyclicLP(localEdgeWeightedDigraph, j);
/*    */ 
/* 87 */     StdOut.println(" job   start  finish");
/* 88 */     StdOut.println("--------------------");
/* 89 */     for (int n = 0; n < i; n++) {
/* 90 */       StdOut.printf("%4d %7.1f %7.1f\n", new Object[] { Integer.valueOf(n), Double.valueOf(localAcyclicLP.distTo(n)), Double.valueOf(localAcyclicLP.distTo(n + i)) });
/*    */     }
/* 92 */     StdOut.printf("Finish time: %7.1f\n", new Object[] { Double.valueOf(localAcyclicLP.distTo(k)) });
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     CPM
 * JD-Core Version:    0.6.2
 */