/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class AdjMatrixEdgeWeightedDigraph
/*     */ {
/*     */   private int V;
/*     */   private int E;
/*     */   private DirectedEdge[][] adj;
/*     */ 
/*     */   public AdjMatrixEdgeWeightedDigraph(int paramInt)
/*     */   {
/*  47 */     if (paramInt < 0) throw new RuntimeException("Number of vertices must be nonnegative");
/*  48 */     this.V = paramInt;
/*  49 */     this.E = 0;
/*  50 */     this.adj = new DirectedEdge[paramInt][paramInt];
/*     */   }
/*     */ 
/*     */   public AdjMatrixEdgeWeightedDigraph(int paramInt1, int paramInt2)
/*     */   {
/*  61 */     this(paramInt1);
/*  62 */     if (paramInt2 < 0) throw new RuntimeException("Number of edges must be nonnegative");
/*  63 */     if (paramInt2 > paramInt1 * paramInt1) throw new RuntimeException("Too many edges");
/*     */ 
/*  66 */     while (this.E != paramInt2) {
/*  67 */       int i = (int)(paramInt1 * Math.random());
/*  68 */       int j = (int)(paramInt1 * Math.random());
/*  69 */       double d = Math.round(100.0D * Math.random()) / 100.0D;
/*  70 */       addEdge(new DirectedEdge(i, j, d));
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/*  79 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/*  87 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(DirectedEdge paramDirectedEdge)
/*     */   {
/*  96 */     int i = paramDirectedEdge.from();
/*  97 */     int j = paramDirectedEdge.to();
/*  98 */     if (this.adj[i][j] == null) {
/*  99 */       this.E += 1;
/* 100 */       this.adj[i][j] = paramDirectedEdge;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> adj(int paramInt)
/*     */   {
/* 111 */     return new AdjMatrixEdgeWeightedDigraph.AdjIterator(paramInt);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 144 */     String str = System.getProperty("line.separator");
/* 145 */     StringBuilder localStringBuilder = new StringBuilder();
/* 146 */     localStringBuilder.append(this.V + " " + this.E + str);
/* 147 */     for (int i = 0; i < this.V; i++) {
/* 148 */       localStringBuilder.append(i + ": ");
/* 149 */       for (DirectedEdge localDirectedEdge : adj(i)) {
/* 150 */         localStringBuilder.append(localDirectedEdge + "  ");
/*     */       }
/* 152 */       localStringBuilder.append(str);
/*     */     }
/* 154 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 161 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 162 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 163 */     AdjMatrixEdgeWeightedDigraph localAdjMatrixEdgeWeightedDigraph = new AdjMatrixEdgeWeightedDigraph(i, j);
/* 164 */     StdOut.println(localAdjMatrixEdgeWeightedDigraph);
/*     */   }
/*     */ 
/*     */   private class AdjIterator
/*     */     implements Iterator<DirectedEdge>, Iterable<DirectedEdge>
/*     */   {
/*     */     private int v;
/* 116 */     private int w = 0;
/*     */ 
/*     */     public AdjIterator(int arg2)
/*     */     {
/*     */       int i;
/* 117 */       this.v = i;
/*     */     }
/* 119 */     public Iterator<DirectedEdge> iterator() { return this; }
/*     */ 
/*     */     public boolean hasNext() {
/* 122 */       while (this.w < AdjMatrixEdgeWeightedDigraph.this.V) {
/* 123 */         if (AdjMatrixEdgeWeightedDigraph.this.adj[this.v][this.w] != null) return true;
/* 124 */         this.w += 1;
/*     */       }
/* 126 */       return false;
/*     */     }
/*     */ 
/*     */     public DirectedEdge next() {
/* 130 */       if (hasNext()) return AdjMatrixEdgeWeightedDigraph.this.adj[this.v][(this.w++)];
/* 131 */       throw new NoSuchElementException();
/*     */     }
/*     */     public void remove() {
/* 134 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     AdjMatrixEdgeWeightedDigraph
 * JD-Core Version:    0.6.2
 */