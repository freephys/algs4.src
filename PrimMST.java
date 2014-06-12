/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class PrimMST
/*     */ {
/*     */   private Edge[] edgeTo;
/*     */   private double[] distTo;
/*     */   private boolean[] marked;
/*     */   private IndexMinPQ<Double> pq;
/*     */ 
/*     */   public PrimMST(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/*  76 */     this.edgeTo = new Edge[paramEdgeWeightedGraph.V()];
/*  77 */     this.distTo = new double[paramEdgeWeightedGraph.V()];
/*  78 */     this.marked = new boolean[paramEdgeWeightedGraph.V()];
/*  79 */     this.pq = new IndexMinPQ(paramEdgeWeightedGraph.V());
/*  80 */     for (int i = 0; i < paramEdgeWeightedGraph.V(); i++) this.distTo[i] = (1.0D / 0.0D);
/*     */ 
/*  82 */     for (i = 0; i < paramEdgeWeightedGraph.V(); i++) {
/*  83 */       if (this.marked[i] == 0) prim(paramEdgeWeightedGraph, i);
/*     */     }
/*     */ 
/*  86 */     assert (check(paramEdgeWeightedGraph));
/*     */   }
/*     */ 
/*     */   private void prim(EdgeWeightedGraph paramEdgeWeightedGraph, int paramInt)
/*     */   {
/*  91 */     this.distTo[paramInt] = 0.0D;
/*  92 */     this.pq.insert(paramInt, Double.valueOf(this.distTo[paramInt]));
/*  93 */     while (!this.pq.isEmpty()) {
/*  94 */       int i = this.pq.delMin();
/*  95 */       scan(paramEdgeWeightedGraph, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void scan(EdgeWeightedGraph paramEdgeWeightedGraph, int paramInt)
/*     */   {
/* 101 */     this.marked[paramInt] = true;
/* 102 */     for (Edge localEdge : paramEdgeWeightedGraph.adj(paramInt)) {
/* 103 */       int i = localEdge.other(paramInt);
/* 104 */       if (this.marked[i] == 0)
/* 105 */         if (localEdge.weight() < this.distTo[i]) {
/* 106 */           this.distTo[i] = localEdge.weight();
/* 107 */           this.edgeTo[i] = localEdge;
/* 108 */           if (this.pq.contains(i)) this.pq.decreaseKey(i, Double.valueOf(this.distTo[i])); else
/* 109 */             this.pq.insert(i, Double.valueOf(this.distTo[i]));
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> edges()
/*     */   {
/* 120 */     Queue localQueue = new Queue();
/* 121 */     for (int i = 0; i < this.edgeTo.length; i++) {
/* 122 */       Edge localEdge = this.edgeTo[i];
/* 123 */       if (localEdge != null) {
/* 124 */         localQueue.enqueue(localEdge);
/*     */       }
/*     */     }
/* 127 */     return localQueue;
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/* 135 */     double d = 0.0D;
/* 136 */     for (Edge localEdge : edges())
/* 137 */       d += localEdge.weight();
/* 138 */     return d;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/* 146 */     double d1 = 0.0D;
/* 147 */     for (Edge localEdge1 : edges()) {
/* 148 */       d1 += localEdge1.weight();
/*     */     }
/* 150 */     double d2 = 1.0E-12D;
/* 151 */     if (Math.abs(d1 - weight()) > d2) {
/* 152 */       System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", new Object[] { Double.valueOf(d1), Double.valueOf(weight()) });
/* 153 */       return false;
/*     */     }
/*     */ 
/* 157 */     UF localUF = new UF(paramEdgeWeightedGraph.V());
/* 158 */     for (Iterator localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 159 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 160 */       if (localUF.connected(i, j)) {
/* 161 */         System.err.println("Not a forest");
/* 162 */         return false;
/*     */       }
/* 164 */       localUF.union(i, j);
/*     */     }
/* 168 */     Edge localEdge2;
/*     */     int i;
/*     */     int j;
/* 168 */     for (localIterator2 = paramEdgeWeightedGraph.edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/* 169 */       i = localEdge2.either(); j = localEdge2.other(i);
/* 170 */       if (!localUF.connected(i, j)) {
/* 171 */         System.err.println("Not a spanning forest");
/* 172 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 177 */     for (localIterator2 = edges().iterator(); localIterator2.hasNext(); ) { localEdge2 = (Edge)localIterator2.next();
/*     */ 
/* 180 */       localUF = new UF(paramEdgeWeightedGraph.V());
/* 181 */       for (localIterator3 = edges().iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 182 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 183 */         if (localEdge3 != localEdge2) localUF.union(k, m);
/*     */ 
/*     */       }
/*     */ 
/* 187 */       for (localIterator3 = paramEdgeWeightedGraph.edges().iterator(); localIterator3.hasNext(); ) { localEdge3 = (Edge)localIterator3.next();
/* 188 */         k = localEdge3.either(); m = localEdge3.other(k);
/* 189 */         if ((!localUF.connected(k, m)) && 
/* 190 */           (localEdge3.weight() < localEdge2.weight())) {
/* 191 */           System.err.println("Edge " + localEdge3 + " violates cut optimality conditions");
/* 192 */           return false;
/*     */         }
/*     */       }
/*     */     }
/*     */     Iterator localIterator3;
/*     */     Edge localEdge3;
/*     */     int k;
/*     */     int m;
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 206 */     In localIn = new In(paramArrayOfString[0]);
/* 207 */     EdgeWeightedGraph localEdgeWeightedGraph = new EdgeWeightedGraph(localIn);
/* 208 */     PrimMST localPrimMST = new PrimMST(localEdgeWeightedGraph);
/* 209 */     for (Edge localEdge : localPrimMST.edges()) {
/* 210 */       StdOut.println(localEdge);
/*     */     }
/* 212 */     StdOut.printf("%.5f\n", new Object[] { Double.valueOf(localPrimMST.weight()) });
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     PrimMST
 * JD-Core Version:    0.6.2
 */