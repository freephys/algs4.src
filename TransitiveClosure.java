/*    */ public class TransitiveClosure
/*    */ {
/*    */   private DirectedDFS[] tc;
/*    */ 
/*    */   public TransitiveClosure(Digraph paramDigraph)
/*    */   {
/* 56 */     this.tc = new DirectedDFS[paramDigraph.V()];
/* 57 */     for (int i = 0; i < paramDigraph.V(); i++)
/* 58 */       this.tc[i] = new DirectedDFS(paramDigraph, i);
/*    */   }
/*    */ 
/*    */   public boolean reachable(int paramInt1, int paramInt2)
/*    */   {
/* 69 */     return this.tc[paramInt1].marked(paramInt2);
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 76 */     In localIn = new In(paramArrayOfString[0]);
/* 77 */     Digraph localDigraph = new Digraph(localIn);
/*    */ 
/* 79 */     TransitiveClosure localTransitiveClosure = new TransitiveClosure(localDigraph);
/*    */ 
/* 82 */     StdOut.print("     ");
/* 83 */     for (int i = 0; i < localDigraph.V(); i++)
/* 84 */       StdOut.printf("%3d", new Object[] { Integer.valueOf(i) });
/* 85 */     StdOut.println();
/* 86 */     StdOut.println("--------------------------------------------");
/*    */ 
/* 89 */     for (i = 0; i < localDigraph.V(); i++) {
/* 90 */       StdOut.printf("%3d: ", new Object[] { Integer.valueOf(i) });
/* 91 */       for (int j = 0; j < localDigraph.V(); j++) {
/* 92 */         if (localTransitiveClosure.reachable(i, j)) StdOut.printf("  T", new Object[0]); else
/* 93 */           StdOut.printf("   ", new Object[0]);
/*    */       }
/* 95 */       StdOut.println();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TransitiveClosure
 * JD-Core Version:    0.6.2
 */