/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class BellmanFordSP
/*     */ {
/*     */   private double[] distTo;
/*     */   private DirectedEdge[] edgeTo;
/*     */   private boolean[] onQueue;
/*     */   private Queue<Integer> queue;
/*     */   private int cost;
/*     */   private Iterable<DirectedEdge> cycle;
/*     */ 
/*     */   public BellmanFordSP(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  68 */     this.distTo = new double[paramEdgeWeightedDigraph.V()];
/*  69 */     this.edgeTo = new DirectedEdge[paramEdgeWeightedDigraph.V()];
/*  70 */     this.onQueue = new boolean[paramEdgeWeightedDigraph.V()];
/*  71 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/*  72 */       this.distTo[i] = (1.0D / 0.0D);
/*  73 */     this.distTo[paramInt] = 0.0D;
/*     */ 
/*  76 */     this.queue = new Queue();
/*  77 */     this.queue.enqueue(Integer.valueOf(paramInt));
/*  78 */     this.onQueue[paramInt] = true;
/*  79 */     while ((!this.queue.isEmpty()) && (!hasNegativeCycle())) {
/*  80 */       i = ((Integer)this.queue.dequeue()).intValue();
/*  81 */       this.onQueue[i] = false;
/*  82 */       relax(paramEdgeWeightedDigraph, i);
/*     */     }
/*     */ 
/*  85 */     assert (check(paramEdgeWeightedDigraph, paramInt));
/*     */   }
/*     */ 
/*     */   private void relax(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  90 */     for (DirectedEdge localDirectedEdge : paramEdgeWeightedDigraph.adj(paramInt)) {
/*  91 */       int i = localDirectedEdge.to();
/*  92 */       if (this.distTo[i] > this.distTo[paramInt] + localDirectedEdge.weight()) {
/*  93 */         this.distTo[paramInt] += localDirectedEdge.weight();
/*  94 */         this.edgeTo[i] = localDirectedEdge;
/*  95 */         if (this.onQueue[i] == 0) {
/*  96 */           this.queue.enqueue(Integer.valueOf(i));
/*  97 */           this.onQueue[i] = true;
/*     */         }
/*     */       }
/* 100 */       if (this.cost++ % paramEdgeWeightedDigraph.V() == 0)
/* 101 */         findNegativeCycle();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasNegativeCycle()
/*     */   {
/* 111 */     return this.cycle != null;
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> negativeCycle()
/*     */   {
/* 121 */     return this.cycle;
/*     */   }
/*     */ 
/*     */   private void findNegativeCycle()
/*     */   {
/* 126 */     int i = this.edgeTo.length;
/* 127 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(i);
/* 128 */     for (int j = 0; j < i; j++) {
/* 129 */       if (this.edgeTo[j] != null)
/* 130 */         localEdgeWeightedDigraph.addEdge(this.edgeTo[j]);
/*     */     }
/* 132 */     EdgeWeightedDirectedCycle localEdgeWeightedDirectedCycle = new EdgeWeightedDirectedCycle(localEdgeWeightedDigraph);
/* 133 */     this.cycle = localEdgeWeightedDirectedCycle.cycle();
/*     */   }
/*     */ 
/*     */   public double distTo(int paramInt)
/*     */   {
/* 145 */     if (hasNegativeCycle())
/* 146 */       throw new UnsupportedOperationException("Negative cost cycle exists");
/* 147 */     return this.distTo[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/* 157 */     return this.distTo[paramInt] < (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> pathTo(int paramInt)
/*     */   {
/* 169 */     if (hasNegativeCycle())
/* 170 */       throw new UnsupportedOperationException("Negative cost cycle exists");
/* 171 */     if (!hasPathTo(paramInt)) return null;
/* 172 */     Stack localStack = new Stack();
/* 173 */     for (DirectedEdge localDirectedEdge = this.edgeTo[paramInt]; localDirectedEdge != null; localDirectedEdge = this.edgeTo[localDirectedEdge.from()]) {
/* 174 */       localStack.push(localDirectedEdge);
/*     */     }
/* 176 */     return localStack;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*     */     Object localObject2;
/* 187 */     if (hasNegativeCycle()) {
/* 188 */       double d = 0.0D;
/* 189 */       for (localObject2 = negativeCycle().iterator(); ((Iterator)localObject2).hasNext(); ) { DirectedEdge localDirectedEdge = (DirectedEdge)((Iterator)localObject2).next();
/* 190 */         d += localDirectedEdge.weight();
/*     */       }
/* 192 */       if (d >= 0.0D) {
/* 193 */         System.err.println("error: weight of negative cycle = " + d);
/* 194 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 202 */       if ((this.distTo[paramInt] != 0.0D) || (this.edgeTo[paramInt] != null)) {
/* 203 */         System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
/* 204 */         return false;
/*     */       }
/* 206 */       for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/* 207 */         if ((i != paramInt) && 
/* 208 */           (this.edgeTo[i] == null) && (this.distTo[i] != (1.0D / 0.0D))) {
/* 209 */           System.err.println("distTo[] and edgeTo[] inconsistent");
/* 210 */           return false;
/*     */         }
/*     */       Object localObject1;
/* 215 */       for (i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/* 216 */         for (localObject1 = paramEdgeWeightedDigraph.adj(i).iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (DirectedEdge)((Iterator)localObject1).next();
/* 217 */           int k = ((DirectedEdge)localObject2).to();
/* 218 */           if (this.distTo[i] + ((DirectedEdge)localObject2).weight() < this.distTo[k]) {
/* 219 */             System.err.println("edge " + localObject2 + " not relaxed");
/* 220 */             return false;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 226 */       for (i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/* 227 */         if (this.edgeTo[i] != null) {
/* 228 */           localObject1 = this.edgeTo[i];
/* 229 */           int j = ((DirectedEdge)localObject1).from();
/* 230 */           if (i != ((DirectedEdge)localObject1).to()) return false;
/* 231 */           if (this.distTo[j] + ((DirectedEdge)localObject1).weight() != this.distTo[i]) {
/* 232 */             System.err.println("edge " + localObject1 + " on shortest path not tight");
/* 233 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 238 */     StdOut.println("Satisfies optimality conditions");
/* 239 */     StdOut.println();
/* 240 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 247 */     In localIn = new In(paramArrayOfString[0]);
/* 248 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 249 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(localIn);
/*     */ 
/* 251 */     BellmanFordSP localBellmanFordSP = new BellmanFordSP(localEdgeWeightedDigraph, i);
/*     */     Iterator localIterator;
/*     */     Object localObject;
/* 254 */     if (localBellmanFordSP.hasNegativeCycle()) {
/* 255 */       for (localIterator = localBellmanFordSP.negativeCycle().iterator(); localIterator.hasNext(); ) { localObject = (DirectedEdge)localIterator.next();
/* 256 */         StdOut.println(localObject);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 261 */       for (int j = 0; j < localEdgeWeightedDigraph.V(); j++)
/* 262 */         if (localBellmanFordSP.hasPathTo(j)) {
/* 263 */           StdOut.printf("%d to %d (%5.2f)  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(localBellmanFordSP.distTo(j)) });
/* 264 */           for (localObject = localBellmanFordSP.pathTo(j).iterator(); ((Iterator)localObject).hasNext(); ) { DirectedEdge localDirectedEdge = (DirectedEdge)((Iterator)localObject).next();
/* 265 */             StdOut.print(localDirectedEdge + "   ");
/*     */           }
/* 267 */           StdOut.println();
/*     */         }
/*     */         else {
/* 270 */           StdOut.printf("%d to %d           no path\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */         }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BellmanFordSP
 * JD-Core Version:    0.6.2
 */