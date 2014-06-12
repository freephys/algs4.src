/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class AcyclicSP
/*     */ {
/*     */   private double[] distTo;
/*     */   private DirectedEdge[] edgeTo;
/*     */ 
/*     */   public AcyclicSP(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  53 */     this.distTo = new double[paramEdgeWeightedDigraph.V()];
/*  54 */     this.edgeTo = new DirectedEdge[paramEdgeWeightedDigraph.V()];
/*  55 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/*  56 */       this.distTo[i] = (1.0D / 0.0D);
/*  57 */     this.distTo[paramInt] = 0.0D;
/*     */ 
/*  60 */     Topological localTopological = new Topological(paramEdgeWeightedDigraph);
/*  61 */     if (!localTopological.hasOrder())
/*  62 */       throw new IllegalArgumentException("Digraph is not acyclic.");
/*  63 */     for (Iterator localIterator1 = localTopological.order().iterator(); localIterator1.hasNext(); ) { int j = ((Integer)localIterator1.next()).intValue();
/*  64 */       for (DirectedEdge localDirectedEdge : paramEdgeWeightedDigraph.adj(j))
/*  65 */         relax(localDirectedEdge);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void relax(DirectedEdge paramDirectedEdge)
/*     */   {
/*  71 */     int i = paramDirectedEdge.from(); int j = paramDirectedEdge.to();
/*  72 */     if (this.distTo[j] > this.distTo[i] + paramDirectedEdge.weight()) {
/*  73 */       this.distTo[i] += paramDirectedEdge.weight();
/*  74 */       this.edgeTo[j] = paramDirectedEdge;
/*     */     }
/*     */   }
/*     */ 
/*     */   public double distTo(int paramInt)
/*     */   {
/*  85 */     return this.distTo[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/*  95 */     return this.distTo[paramInt] < (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> pathTo(int paramInt)
/*     */   {
/* 105 */     if (!hasPathTo(paramInt)) return null;
/* 106 */     Stack localStack = new Stack();
/* 107 */     for (DirectedEdge localDirectedEdge = this.edgeTo[paramInt]; localDirectedEdge != null; localDirectedEdge = this.edgeTo[localDirectedEdge.from()]) {
/* 108 */       localStack.push(localDirectedEdge);
/*     */     }
/* 110 */     return localStack;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 118 */     In localIn = new In(paramArrayOfString[0]);
/* 119 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 120 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(localIn);
/*     */ 
/* 123 */     AcyclicSP localAcyclicSP = new AcyclicSP(localEdgeWeightedDigraph, i);
/* 124 */     for (int j = 0; j < localEdgeWeightedDigraph.V(); j++)
/* 125 */       if (localAcyclicSP.hasPathTo(j)) {
/* 126 */         StdOut.printf("%d to %d (%.2f)  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(localAcyclicSP.distTo(j)) });
/* 127 */         for (DirectedEdge localDirectedEdge : localAcyclicSP.pathTo(j)) {
/* 128 */           StdOut.print(localDirectedEdge + "   ");
/*     */         }
/* 130 */         StdOut.println();
/*     */       }
/*     */       else {
/* 133 */         StdOut.printf("%d to %d         no path\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     AcyclicSP
 * JD-Core Version:    0.6.2
 */