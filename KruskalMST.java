/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class KruskalMST
/*     */ {
/*     */   private double weight;
/*  65 */   private Queue<Edge> mst = new Queue();
/*     */ 
/*     */   public KruskalMST(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/*  73 */     MinPQ localMinPQ = new MinPQ();
/*  74 */     for (Object localObject = paramEdgeWeightedGraph.edges().iterator(); ((Iterator)localObject).hasNext(); ) { localEdge = (Edge)((Iterator)localObject).next();
/*  75 */       localMinPQ.insert(localEdge);
/*     */     }
/*     */     Edge localEdge;
/*  79 */     localObject = new UF(paramEdgeWeightedGraph.V());
/*  80 */     while ((!localMinPQ.isEmpty()) && (this.mst.size() < paramEdgeWeightedGraph.V() - 1)) {
/*  81 */       localEdge = (Edge)localMinPQ.delMin();
/*  82 */       int i = localEdge.either();
/*  83 */       int j = localEdge.other(i);
/*  84 */       if (!((UF)localObject).connected(i, j)) {
/*  85 */         ((UF)localObject).union(i, j);
/*  86 */         this.mst.enqueue(localEdge);
/*  87 */         this.weight += localEdge.weight();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  92 */     assert (check(paramEdgeWeightedGraph));
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> edges()
/*     */   {
/* 101 */     return this.mst;
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/* 109 */     return this.weight;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/* 116 */     double d1 = 0.0D;
/* 117 */     for (Edge localEdge1 : edges()) {
/* 118 */       d1 += localEdge1.weight();
/*     */     }
/* 120 */     double d2 = 1.0E-12D;
/* 121 */     if (Math.abs(d1 - weight()) > d2) {
/* 122 */       System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", new Object[] { Double.valueOf(d1), Double.valueOf(weight()) });
/* 123 */       return false;
/*     */     }
/*     */ 
/* 127 */     UF localUF = new UF(paramEdgeWeightedGraph.V());
/* 128 */     for (Iterator localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 129 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 130 */       if (localUF.connected(i, j)) {
/* 131 */         System.err.println("Not a forest");
/* 132 */         return false;
/*     */       }
/* 134 */       localUF.union(i, j);
/*     */     }
/* 138 */     Edge localEdge2;
/*     */     int i;
/*     */     int j;
/* 138 */     for (localIterator2 = paramEdgeWeightedGraph.edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 139 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 140 */       if (!localUF.connected(i, j)) {
/* 141 */         System.err.println("Not a spanning forest");
/* 142 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 147 */     for (localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/*     */ 
/* 150 */       localUF = new UF(paramEdgeWeightedGraph.V());
/* 151 */       for (localIterator3 = this.mst.iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 152 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 153 */         if (localEdge3 != localEdge2) localUF.union(k, m);
/*     */ 
/*     */       }
/*     */ 
/* 157 */       for (localIterator3 = paramEdgeWeightedGraph.edges().iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 158 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 159 */         if ((!localUF.connected(k, m)) && 
/* 160 */           (localEdge3.weight() < localEdge2.weight())) {
/* 161 */           System.err.println("Edge " + localEdge3 + " violates cut optimality conditions");
/* 162 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     Iterator localIterator3;
/*     */     Edge localEdge3;
/*     */     int k;
/*     */     int m;
/* 169 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 177 */     In localIn = new In(paramArrayOfString[0]);
/* 178 */     EdgeWeightedGraph localEdgeWeightedGraph = new EdgeWeightedGraph(localIn);
/* 179 */     KruskalMST localKruskalMST = new KruskalMST(localEdgeWeightedGraph);
/* 180 */     for (Edge localEdge : localKruskalMST.edges()) {
/* 181 */       StdOut.println(localEdge);
/*     */     }
/* 183 */     StdOut.printf("%.5f\n", new Object[] { Double.valueOf(localKruskalMST.weight()) });
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     KruskalMST
 * JD-Core Version:    0.6.2
 */