/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class LazyPrimMST
/*     */ {
/*     */   private double weight;
/*     */   private Queue<Edge> mst;
/*     */   private boolean[] marked;
/*     */   private MinPQ<Edge> pq;
/*     */ 
/*     */   public LazyPrimMST(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/*  79 */     this.mst = new Queue();
/*  80 */     this.pq = new MinPQ();
/*  81 */     this.marked = new boolean[paramEdgeWeightedGraph.V()];
/*  82 */     for (int i = 0; i < paramEdgeWeightedGraph.V(); i++) {
/*  83 */       if (this.marked[i] == 0) prim(paramEdgeWeightedGraph, i);
/*     */     }
/*     */ 
/*  86 */     assert (check(paramEdgeWeightedGraph));
/*     */   }
/*     */ 
/*     */   private void prim(EdgeWeightedGraph paramEdgeWeightedGraph, int paramInt)
/*     */   {
/*  91 */     scan(paramEdgeWeightedGraph, paramInt);
/*  92 */     while (!this.pq.isEmpty()) {
/*  93 */       Edge localEdge = (Edge)this.pq.delMin();
/*  94 */       int i = localEdge.either(); int j = localEdge.other(i);
/*  95 */       assert ((this.marked[i] != 0) || (this.marked[j] != 0));
/*  96 */       if ((this.marked[i] == 0) || (this.marked[j] == 0)) {
/*  97 */         this.mst.enqueue(localEdge);
/*  98 */         this.weight += localEdge.weight();
/*  99 */         if (this.marked[i] == 0) scan(paramEdgeWeightedGraph, i);
/* 100 */         if (this.marked[j] == 0) scan(paramEdgeWeightedGraph, j); 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void scan(EdgeWeightedGraph paramEdgeWeightedGraph, int paramInt)
/*     */   {
/* 106 */     assert (this.marked[paramInt] == 0);
/* 107 */     this.marked[paramInt] = true;
/* 108 */     for (Edge localEdge : paramEdgeWeightedGraph.adj(paramInt))
/* 109 */       if (this.marked[localEdge.other(paramInt)] == 0) this.pq.insert(localEdge);
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> edges()
/*     */   {
/* 118 */     return this.mst;
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/* 126 */     return this.weight;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/* 133 */     double d1 = 0.0D;
/* 134 */     for (Edge localEdge1 : edges()) {
/* 135 */       d1 += localEdge1.weight();
/*     */     }
/* 137 */     double d2 = 1.0E-12D;
/* 138 */     if (Math.abs(d1 - weight()) > d2) {
/* 139 */       System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", new Object[] { Double.valueOf(d1), Double.valueOf(weight()) });
/* 140 */       return false;
/*     */     }
/*     */ 
/* 144 */     UF localUF = new UF(paramEdgeWeightedGraph.V());
/* 145 */     for (Iterator localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 146 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 147 */       if (localUF.connected(i, j)) {
/* 148 */         System.err.println("Not a forest");
/* 149 */         return false;
/*     */       }
/* 151 */       localUF.union(i, j);
/*     */     }
/* 155 */     Edge localEdge2;
/*     */     int i;
/*     */     int j;
/* 155 */     for (localIterator2 = paramEdgeWeightedGraph.edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 156 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 157 */       if (!localUF.connected(i, j)) {
/* 158 */         System.err.println("Not a spanning forest");
/* 159 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 164 */     for (localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/*     */ 
/* 167 */       localUF = new UF(paramEdgeWeightedGraph.V());
/* 168 */       for (localIterator3 = this.mst.iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 169 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 170 */         if (localEdge3 != localEdge2) localUF.union(k, m);
/*     */ 
/*     */       }
/*     */ 
/* 174 */       for (localIterator3 = paramEdgeWeightedGraph.edges().iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 175 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 176 */         if ((!localUF.connected(k, m)) && 
/* 177 */           (localEdge3.weight() < localEdge2.weight())) {
/* 178 */           System.err.println("Edge " + localEdge3 + " violates cut optimality conditions");
/* 179 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     Iterator localIterator3;
/*     */     Edge localEdge3;
/*     */     int k;
/*     */     int m;
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 194 */     In localIn = new In(paramArrayOfString[0]);
/* 195 */     EdgeWeightedGraph localEdgeWeightedGraph = new EdgeWeightedGraph(localIn);
/* 196 */     LazyPrimMST localLazyPrimMST = new LazyPrimMST(localEdgeWeightedGraph);
/* 197 */     for (Edge localEdge : localLazyPrimMST.edges()) {
/* 198 */       StdOut.println(localEdge);
/*     */     }
/* 200 */     StdOut.printf("%.5f\n", new Object[] { Double.valueOf(localLazyPrimMST.weight()) });
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LazyPrimMST
 * JD-Core Version:    0.6.2
 */