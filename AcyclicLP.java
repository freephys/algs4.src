/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class AcyclicLP
/*     */ {
/*     */   private double[] distTo;
/*     */   private DirectedEdge[] edgeTo;
/*     */ 
/*     */   public AcyclicLP(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  54 */     this.distTo = new double[paramEdgeWeightedDigraph.V()];
/*  55 */     this.edgeTo = new DirectedEdge[paramEdgeWeightedDigraph.V()];
/*  56 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++) this.distTo[i] = (-1.0D / 0.0D);
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
/*  72 */     if (this.distTo[j] < this.distTo[i] + paramDirectedEdge.weight()) {
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
/*  95 */     return this.distTo[paramInt] > (-1.0D / 0.0D);
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
/* 119 */     In localIn = new In(paramArrayOfString[0]);
/* 120 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 121 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(localIn);
/*     */ 
/* 123 */     AcyclicLP localAcyclicLP = new AcyclicLP(localEdgeWeightedDigraph, i);
/*     */ 
/* 125 */     for (int j = 0; j < localEdgeWeightedDigraph.V(); j++)
/* 126 */       if (localAcyclicLP.hasPathTo(j)) {
/* 127 */         StdOut.printf("%d to %d (%.2f)  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Double.valueOf(localAcyclicLP.distTo(j)) });
/* 128 */         for (DirectedEdge localDirectedEdge : localAcyclicLP.pathTo(j)) {
/* 129 */           StdOut.print(localDirectedEdge + "   ");
/*     */         }
/* 131 */         StdOut.println();
/*     */       }
/*     */       else {
/* 134 */         StdOut.printf("%d to %d         no path\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     AcyclicLP
 * JD-Core Version:    0.6.2
 */