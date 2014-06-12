/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class BoruvkaMST
/*     */ {
/*  51 */   private Bag<Edge> mst = new Bag();
/*     */   private double weight;
/*     */ 
/*     */   public BoruvkaMST(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/*  59 */     UF localUF = new UF(paramEdgeWeightedGraph.V());
/*     */ 
/*  62 */     for (int i = 1; (i < paramEdgeWeightedGraph.V()) && (this.mst.size() < paramEdgeWeightedGraph.V() - 1); i += i)
/*     */     {
/*  66 */       Edge[] arrayOfEdge = new Edge[paramEdgeWeightedGraph.V()];
/*  67 */       for (Iterator localIterator = paramEdgeWeightedGraph.edges().iterator(); localIterator.hasNext(); ) { localEdge = (Edge)localIterator.next();
/*  68 */         k = localEdge.either(); m = localEdge.other(k);
/*  69 */         int n = localUF.find(k); int i1 = localUF.find(m);
/*  70 */         if (n != i1) {
/*  71 */           if ((arrayOfEdge[n] == null) || (less(localEdge, arrayOfEdge[n]))) arrayOfEdge[n] = localEdge;
/*  72 */           if ((arrayOfEdge[i1] == null) || (less(localEdge, arrayOfEdge[i1]))) arrayOfEdge[i1] = localEdge;
/*     */         }
/*     */       }
/*     */       Edge localEdge;
/*     */       int k;
/*     */       int m;
/*  76 */       for (int j = 0; j < paramEdgeWeightedGraph.V(); j++) {
/*  77 */         localEdge = arrayOfEdge[j];
/*  78 */         if (localEdge != null) {
/*  79 */           k = localEdge.either(); m = localEdge.other(k);
/*     */ 
/*  81 */           if (!localUF.connected(k, m)) {
/*  82 */             this.mst.add(localEdge);
/*  83 */             this.weight += localEdge.weight();
/*  84 */             localUF.union(k, m);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  91 */     assert (check(paramEdgeWeightedGraph));
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> edges()
/*     */   {
/* 100 */     return this.mst;
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/* 109 */     return this.weight;
/*     */   }
/*     */ 
/*     */   private static boolean less(Edge paramEdge1, Edge paramEdge2)
/*     */   {
/* 114 */     return paramEdge1.weight() < paramEdge2.weight();
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/* 121 */     double d1 = 0.0D;
/* 122 */     for (Edge localEdge1 : edges()) {
/* 123 */       d1 += localEdge1.weight();
/*     */     }
/* 125 */     double d2 = 1.0E-12D;
/* 126 */     if (Math.abs(d1 - weight()) > d2) {
/* 127 */       System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", new Object[] { Double.valueOf(d1), Double.valueOf(weight()) });
/* 128 */       return false;
/*     */     }
/*     */ 
/* 132 */     UF localUF = new UF(paramEdgeWeightedGraph.V());
/* 133 */     for (Iterator localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 134 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 135 */       if (localUF.connected(i, j)) {
/* 136 */         System.err.println("Not a forest");
/* 137 */         return false;
/*     */       }
/* 139 */       localUF.union(i, j);
/*     */     }
/* 143 */     Edge localEdge2;
/*     */     int i;
/*     */     int j;
/* 143 */     for (localIterator2 = paramEdgeWeightedGraph.edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 144 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 145 */       if (!localUF.connected(i, j)) {
/* 146 */         System.err.println("Not a spanning forest");
/* 147 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 152 */     for (localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/*     */ 
/* 155 */       localUF = new UF(paramEdgeWeightedGraph.V());
/* 156 */       for (localIterator3 = this.mst.iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 157 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 158 */         if (localEdge3 != localEdge2) localUF.union(k, m);
/*     */ 
/*     */       }
/*     */ 
/* 162 */       for (localIterator3 = paramEdgeWeightedGraph.edges().iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 163 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 164 */         if ((!localUF.connected(k, m)) && 
/* 165 */           (localEdge3.weight() < localEdge2.weight())) {
/* 166 */           System.err.println("Edge " + localEdge3 + " violates cut optimality conditions");
/* 167 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     Iterator localIterator3;
/*     */     Edge localEdge3;
/*     */     int k;
/*     */     int m;
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 181 */     In localIn = new In(paramArrayOfString[0]);
/* 182 */     EdgeWeightedGraph localEdgeWeightedGraph = new EdgeWeightedGraph(localIn);
/* 183 */     BoruvkaMST localBoruvkaMST = new BoruvkaMST(localEdgeWeightedGraph);
/* 184 */     for (Edge localEdge : localBoruvkaMST.edges()) {
/* 185 */       StdOut.println(localEdge);
/*     */     }
/* 187 */     StdOut.printf("%.5f\n", new Object[] { Double.valueOf(localBoruvkaMST.weight()) });
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BoruvkaMST
 * JD-Core Version:    0.6.2
 */