/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DijkstraSP
/*     */ {
/*     */   private double[] distTo;
/*     */   private DirectedEdge[] edgeTo;
/*     */   private IndexMinPQ<Double> pq;
/*     */ 
/*     */   public DijkstraSP(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  65 */     for (Iterator localIterator = paramEdgeWeightedDigraph.edges().iterator(); localIterator.hasNext(); ) { localObject = (DirectedEdge)localIterator.next();
/*  66 */       if (((DirectedEdge)localObject).weight() < 0.0D)
/*  67 */         throw new IllegalArgumentException("edge " + localObject + " has negative weight");
/*     */     }
/*     */     Object localObject;
/*  70 */     this.distTo = new double[paramEdgeWeightedDigraph.V()];
/*  71 */     this.edgeTo = new DirectedEdge[paramEdgeWeightedDigraph.V()];
/*  72 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/*  73 */       this.distTo[i] = (1.0D / 0.0D);
/*  74 */     this.distTo[paramInt] = 0.0D;
/*     */ 
/*  77 */     this.pq = new IndexMinPQ(paramEdgeWeightedDigraph.V());
/*  78 */     this.pq.insert(paramInt, Double.valueOf(this.distTo[paramInt]));
/*  79 */     while (!this.pq.isEmpty()) {
/*  80 */       i = this.pq.delMin();
/*  81 */       for (localObject = paramEdgeWeightedDigraph.adj(i).iterator(); ((Iterator)localObject).hasNext(); ) { DirectedEdge localDirectedEdge = (DirectedEdge)((Iterator)localObject).next();
/*  82 */         relax(localDirectedEdge);
/*     */       }
/*     */     }
/*     */ 
/*  86 */     assert (check(paramEdgeWeightedDigraph, paramInt));
/*     */   }
/*     */ 
/*     */   private void relax(DirectedEdge paramDirectedEdge)
/*     */   {
/*  91 */     int i = paramDirectedEdge.from(); int j = paramDirectedEdge.to();
/*  92 */     if (this.distTo[j] > this.distTo[i] + paramDirectedEdge.weight()) {
/*  93 */       this.distTo[i] += paramDirectedEdge.weight();
/*  94 */       this.edgeTo[j] = paramDirectedEdge;
/*  95 */       if (this.pq.contains(j)) this.pq.decreaseKey(j, Double.valueOf(this.distTo[j])); else
/*  96 */         this.pq.insert(j, Double.valueOf(this.distTo[j]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public double distTo(int paramInt)
/*     */   {
/* 107 */     return this.distTo[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/* 117 */     return this.distTo[paramInt] < (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> pathTo(int paramInt)
/*     */   {
/* 127 */     if (!hasPathTo(paramInt)) return null;
/* 128 */     Stack localStack = new Stack();
/* 129 */     for (DirectedEdge localDirectedEdge = this.edgeTo[paramInt]; localDirectedEdge != null; localDirectedEdge = this.edgeTo[localDirectedEdge.from()]) {
/* 130 */       localStack.push(localDirectedEdge);
/*     */     }
/* 132 */     return localStack;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/* 142 */     for (Iterator localIterator = paramEdgeWeightedDigraph.edges().iterator(); localIterator.hasNext(); ) { localObject = (DirectedEdge)localIterator.next();
/* 143 */       if (((DirectedEdge)localObject).weight() < 0.0D) {
/* 144 */         System.err.println("negative edge weight detected");
/* 145 */         return false;
/*     */       }
/*     */     }
/*     */     Object localObject;
/* 150 */     if ((this.distTo[paramInt] != 0.0D) || (this.edgeTo[paramInt] != null)) {
/* 151 */       System.err.println("distTo[s] and edgeTo[s] inconsistent");
/* 152 */       return false;
/*     */     }
/* 154 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/* 155 */       if ((i != paramInt) && 
/* 156 */         (this.edgeTo[i] == null) && (this.distTo[i] != (1.0D / 0.0D))) {
/* 157 */         System.err.println("distTo[] and edgeTo[] inconsistent");
/* 158 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 163 */     for (i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/* 164 */       for (localObject = paramEdgeWeightedDigraph.adj(i).iterator(); ((Iterator)localObject).hasNext(); ) { DirectedEdge localDirectedEdge = (DirectedEdge)((Iterator)localObject).next();
/* 165 */         int k = localDirectedEdge.to();
/* 166 */         if (this.distTo[i] + localDirectedEdge.weight() < this.distTo[k]) {
/* 167 */           System.err.println("edge " + localDirectedEdge + " not relaxed");
/* 168 */           return false;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 174 */     for (i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/* 175 */       if (this.edgeTo[i] != null) {
/* 176 */         localObject = this.edgeTo[i];
/* 177 */         int j = ((DirectedEdge)localObject).from();
/* 178 */         if (i != ((DirectedEdge)localObject).to()) return false;
/* 179 */         if (this.distTo[j] + ((DirectedEdge)localObject).weight() != this.distTo[i]) {
/* 180 */           System.err.println("edge " + localObject + " on shortest path not tight");
/* 181 */           return false;
/*     */         }
/*     */       }
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 192 */     In localIn = new In(paramArrayOfString[0]);
/* 193 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(localIn);
/* 194 */     int i = Integer.parseInt(paramArrayOfString[1]);
/*     */ 
/* 197 */     DijkstraSP localDijkstraSP = new DijkstraSP(localEdgeWeightedDigraph, i);
/*     */ 
/* 201 */     for (int j = 0; j < localEdgeWeightedDigraph.V(); j++)
/* 202 */       if (localDijkstraSP.hasPathTo(j)) {
/* 203 */         StdOut.printf("%d to %d (%.2f)  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(localDijkstraSP.distTo(j)) });
/* 204 */         if (localDijkstraSP.hasPathTo(j)) {
/* 205 */           for (DirectedEdge localDirectedEdge : localDijkstraSP.pathTo(j)) {
/* 206 */             StdOut.print(localDirectedEdge + "   ");
/*     */           }
/*     */         }
/* 209 */         StdOut.println();
/*     */       }
/*     */       else {
/* 212 */         StdOut.printf("%d to %d         no path\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DijkstraSP
 * JD-Core Version:    0.6.2
 */