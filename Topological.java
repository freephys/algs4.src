/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Topological
/*     */ {
/*     */   private Iterable<Integer> order;
/*     */ 
/*     */   public Topological(Digraph paramDigraph)
/*     */   {
/*  62 */     DirectedCycle localDirectedCycle = new DirectedCycle(paramDigraph);
/*  63 */     if (!localDirectedCycle.hasCycle()) {
/*  64 */       DepthFirstOrder localDepthFirstOrder = new DepthFirstOrder(paramDigraph);
/*  65 */       this.order = localDepthFirstOrder.reversePost();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Topological(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*     */   {
/*  75 */     EdgeWeightedDirectedCycle localEdgeWeightedDirectedCycle = new EdgeWeightedDirectedCycle(paramEdgeWeightedDigraph);
/*  76 */     if (!localEdgeWeightedDirectedCycle.hasCycle()) {
/*  77 */       DepthFirstOrder localDepthFirstOrder = new DepthFirstOrder(paramEdgeWeightedDigraph);
/*  78 */       this.order = localDepthFirstOrder.reversePost();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> order()
/*     */   {
/*  90 */     return this.order;
/*     */   }
/*     */ 
/*     */   public boolean hasOrder()
/*     */   {
/*  99 */     return this.order != null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 106 */     String str1 = paramArrayOfString[0];
/* 107 */     String str2 = paramArrayOfString[1];
/* 108 */     SymbolDigraph localSymbolDigraph = new SymbolDigraph(str1, str2);
/* 109 */     Topological localTopological = new Topological(localSymbolDigraph.G());
/* 110 */     for (Iterator localIterator = localTopological.order().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 111 */       StdOut.println(localSymbolDigraph.name(i));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Topological
 * JD-Core Version:    0.6.2
 */