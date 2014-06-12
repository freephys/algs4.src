/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class FloydWarshall
/*     */ {
/*     */   private boolean hasNegativeCycle;
/*     */   private double[][] distTo;
/*     */   private DirectedEdge[][] edgeTo;
/*     */ 
/*     */   public FloydWarshall(AdjMatrixEdgeWeightedDigraph paramAdjMatrixEdgeWeightedDigraph)
/*     */   {
/*  50 */     int i = paramAdjMatrixEdgeWeightedDigraph.V();
/*  51 */     this.distTo = new double[i][i];
/*  52 */     this.edgeTo = new DirectedEdge[i][i];
/*     */ 
/*  55 */     for (int j = 0; j < i; j++) {
/*  56 */       for (int k = 0; k < i; k++) {
/*  57 */         this.distTo[j][k] = (1.0D / 0.0D);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  62 */     for (j = 0; j < paramAdjMatrixEdgeWeightedDigraph.V(); j++) {
/*  63 */       for (DirectedEdge localDirectedEdge : paramAdjMatrixEdgeWeightedDigraph.adj(j)) {
/*  64 */         this.distTo[localDirectedEdge.from()][localDirectedEdge.to()] = localDirectedEdge.weight();
/*  65 */         this.edgeTo[localDirectedEdge.from()][localDirectedEdge.to()] = localDirectedEdge;
/*     */       }
/*     */ 
/*  68 */       if (this.distTo[j][j] >= 0.0D) {
/*  69 */         this.distTo[j][j] = 0.0D;
/*  70 */         this.edgeTo[j][j] = null;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  75 */     for (j = 0; j < i; j++)
/*     */     {
/*  77 */       for (int m = 0; m < i; m++)
/*  78 */         if (this.edgeTo[m][j] != null) {
/*  79 */           for (int n = 0; n < i; n++) {
/*  80 */             if (this.distTo[m][n] > this.distTo[m][j] + this.distTo[j][n]) {
/*  81 */               this.distTo[m][j] += this.distTo[j][n];
/*  82 */               this.edgeTo[m][n] = this.edgeTo[j][n];
/*     */             }
/*     */           }
/*     */ 
/*  86 */           if (this.distTo[m][m] < 0.0D) {
/*  87 */             this.hasNegativeCycle = true;
/*  88 */             return;
/*     */           }
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasNegativeCycle()
/*     */   {
/*  99 */     return this.hasNegativeCycle;
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> negativeCycle()
/*     */   {
/* 108 */     for (int i = 0; i < this.distTo.length; i++)
/*     */     {
/* 110 */       if (this.distTo[i][i] < 0.0D) {
/* 111 */         int j = this.edgeTo.length;
/* 112 */         EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(j);
/* 113 */         for (int k = 0; k < j; k++)
/* 114 */           if (this.edgeTo[i][k] != null)
/* 115 */             localEdgeWeightedDigraph.addEdge(this.edgeTo[i][k]);
/* 116 */         EdgeWeightedDirectedCycle localEdgeWeightedDirectedCycle = new EdgeWeightedDirectedCycle(localEdgeWeightedDigraph);
/* 117 */         assert (localEdgeWeightedDirectedCycle.hasCycle());
/* 118 */         return localEdgeWeightedDirectedCycle.cycle();
/*     */       }
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean hasPath(int paramInt1, int paramInt2)
/*     */   {
/* 132 */     return this.distTo[paramInt1][paramInt2] < (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public double dist(int paramInt1, int paramInt2)
/*     */   {
/* 144 */     if (hasNegativeCycle())
/* 145 */       throw new UnsupportedOperationException("Negative cost cycle exists");
/* 146 */     return this.distTo[paramInt1][paramInt2];
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> path(int paramInt1, int paramInt2)
/*     */   {
/* 158 */     if (hasNegativeCycle())
/* 159 */       throw new UnsupportedOperationException("Negative cost cycle exists");
/* 160 */     if (!hasPath(paramInt1, paramInt2)) return null;
/* 161 */     Stack localStack = new Stack();
/* 162 */     for (DirectedEdge localDirectedEdge = this.edgeTo[paramInt1][paramInt2]; localDirectedEdge != null; localDirectedEdge = this.edgeTo[paramInt1][localDirectedEdge.from()]) {
/* 163 */       localStack.push(localDirectedEdge);
/*     */     }
/* 165 */     return localStack;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/* 172 */     if (!hasNegativeCycle()) {
/* 173 */       for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/* 174 */         for (DirectedEdge localDirectedEdge : paramEdgeWeightedDigraph.adj(i)) {
/* 175 */           int j = localDirectedEdge.to();
/* 176 */           for (int k = 0; k < paramEdgeWeightedDigraph.V(); k++) {
/* 177 */             if (this.distTo[k][j] > this.distTo[k][i] + localDirectedEdge.weight()) {
/* 178 */               System.err.println("edge " + localDirectedEdge + " is eligible");
/* 179 */               return false;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 195 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 196 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 197 */     AdjMatrixEdgeWeightedDigraph localAdjMatrixEdgeWeightedDigraph = new AdjMatrixEdgeWeightedDigraph(i);
/*     */     int i1;
/* 198 */     for (int k = 0; k < j; k++) {
/* 199 */       m = (int)(i * Math.random());
/* 200 */       i1 = (int)(i * Math.random());
/* 201 */       double d = Math.round(100.0D * (Math.random() - 0.15D)) / 100.0D;
/* 202 */       if (m == i1) localAdjMatrixEdgeWeightedDigraph.addEdge(new DirectedEdge(m, i1, Math.abs(d))); else {
/* 203 */         localAdjMatrixEdgeWeightedDigraph.addEdge(new DirectedEdge(m, i1, d));
/*     */       }
/*     */     }
/* 206 */     StdOut.println(localAdjMatrixEdgeWeightedDigraph);
/*     */ 
/* 209 */     FloydWarshall localFloydWarshall = new FloydWarshall(localAdjMatrixEdgeWeightedDigraph);
/*     */ 
/* 212 */     StdOut.printf("  ", new Object[0]);
/* 213 */     for (int m = 0; m < localAdjMatrixEdgeWeightedDigraph.V(); m++) {
/* 214 */       StdOut.printf("%6d ", new Object[] { Integer.valueOf(m) });
/*     */     }
/* 216 */     StdOut.println();
/* 217 */     for (m = 0; m < localAdjMatrixEdgeWeightedDigraph.V(); m++) {
/* 218 */       StdOut.printf("%3d: ", new Object[] { Integer.valueOf(m) });
/* 219 */       for (i1 = 0; i1 < localAdjMatrixEdgeWeightedDigraph.V(); i1++) {
/* 220 */         if (localFloydWarshall.hasPath(m, i1)) StdOut.printf("%6.2f ", new Object[] { Double.valueOf(localFloydWarshall.dist(m, i1)) }); else
/* 221 */           StdOut.printf("  Inf ", new Object[0]);
/*     */       }
/* 223 */       StdOut.println();
/*     */     }
/*     */ 
/* 227 */     if (localFloydWarshall.hasNegativeCycle()) {
/* 228 */       StdOut.println("Negative cost cycle:");
/* 229 */       for (DirectedEdge localDirectedEdge1 : localFloydWarshall.negativeCycle())
/* 230 */         StdOut.println(localDirectedEdge1);
/* 231 */       StdOut.println();
/*     */     }
/*     */     else
/*     */     {
/* 236 */       for (int n = 0; n < localAdjMatrixEdgeWeightedDigraph.V(); n++)
/* 237 */         for (int i2 = 0; i2 < localAdjMatrixEdgeWeightedDigraph.V(); i2++)
/* 238 */           if (localFloydWarshall.hasPath(n, i2)) {
/* 239 */             StdOut.printf("%d to %d (%5.2f)  ", new Object[] { Integer.valueOf(n), Integer.valueOf(i2), Double.valueOf(localFloydWarshall.dist(n, i2)) });
/* 240 */             for (DirectedEdge localDirectedEdge2 : localFloydWarshall.path(n, i2))
/* 241 */               StdOut.print(localDirectedEdge2 + "  ");
/* 242 */             StdOut.println();
/*     */           }
/*     */           else {
/* 245 */             StdOut.printf("%d to %d no path\n", new Object[] { Integer.valueOf(n), Integer.valueOf(i2) });
/*     */           }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FloydWarshall
 * JD-Core Version:    0.6.2
 */