/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class EdgeWeightedDigraph
/*     */ {
/*     */   private final int V;
/*     */   private int E;
/*     */   private Bag<DirectedEdge>[] adj;
/*     */ 
/*     */   public EdgeWeightedDigraph(int paramInt)
/*     */   {
/*  44 */     if (paramInt < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
/*  45 */     this.V = paramInt;
/*  46 */     this.E = 0;
/*  47 */     this.adj = ((Bag[])new Bag[paramInt]);
/*  48 */     for (int i = 0; i < paramInt; i++)
/*  49 */       this.adj[i] = new Bag();
/*     */   }
/*     */ 
/*     */   public EdgeWeightedDigraph(int paramInt1, int paramInt2)
/*     */   {
/*  60 */     this(paramInt1);
/*  61 */     if (paramInt2 < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
/*  62 */     for (int i = 0; i < paramInt2; i++) {
/*  63 */       int j = (int)(Math.random() * paramInt1);
/*  64 */       int k = (int)(Math.random() * paramInt1);
/*  65 */       double d = Math.round(100.0D * Math.random()) / 100.0D;
/*  66 */       DirectedEdge localDirectedEdge = new DirectedEdge(j, k, d);
/*  67 */       addEdge(localDirectedEdge);
/*     */     }
/*     */   }
/*     */ 
/*     */   public EdgeWeightedDigraph(In paramIn)
/*     */   {
/*  82 */     this(paramIn.readInt());
/*  83 */     int i = paramIn.readInt();
/*  84 */     if (i < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  85 */     for (int j = 0; j < i; j++) {
/*  86 */       int k = paramIn.readInt();
/*  87 */       int m = paramIn.readInt();
/*  88 */       if ((k < 0) || (k >= this.V)) throw new IndexOutOfBoundsException("vertex " + k + " is not between 0 and " + (this.V - 1));
/*  89 */       if ((m < 0) || (m >= this.V)) throw new IndexOutOfBoundsException("vertex " + m + " is not between 0 and " + (this.V - 1));
/*  90 */       double d = paramIn.readDouble();
/*  91 */       addEdge(new DirectedEdge(k, m, d));
/*     */     }
/*     */   }
/*     */ 
/*     */   public EdgeWeightedDigraph(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*     */   {
/* 100 */     this(paramEdgeWeightedDigraph.V());
/* 101 */     this.E = paramEdgeWeightedDigraph.E();
/*     */     Iterator localIterator;
/*     */     DirectedEdge localDirectedEdge;
/* 102 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/*     */     {
/* 104 */       Stack localStack = new Stack();
/* 105 */       for (localIterator = paramEdgeWeightedDigraph.adj[i].iterator(); localIterator.hasNext(); ) { localDirectedEdge = (DirectedEdge)localIterator.next();
/* 106 */         localStack.push(localDirectedEdge);
/*     */       }
/* 108 */       for (localIterator = localStack.iterator(); localIterator.hasNext(); ) { localDirectedEdge = (DirectedEdge)localIterator.next();
/* 109 */         this.adj[i].add(localDirectedEdge);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/* 119 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/* 127 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(DirectedEdge paramDirectedEdge)
/*     */   {
/* 135 */     int i = paramDirectedEdge.from();
/* 136 */     this.adj[i].add(paramDirectedEdge);
/* 137 */     this.E += 1;
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> adj(int paramInt)
/*     */   {
/* 148 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt + " is not between 0 and " + (this.V - 1));
/* 149 */     return this.adj[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> edges()
/*     */   {
/* 159 */     Bag localBag = new Bag();
/* 160 */     for (int i = 0; i < this.V; i++) {
/* 161 */       for (DirectedEdge localDirectedEdge : adj(i)) {
/* 162 */         localBag.add(localDirectedEdge);
/*     */       }
/*     */     }
/* 165 */     return localBag;
/*     */   }
/*     */ 
/*     */   public int outdegree(int paramInt)
/*     */   {
/* 176 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt + " is not between 0 and " + (this.V - 1));
/* 177 */     return this.adj[paramInt].size();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 187 */     String str = System.getProperty("line.separator");
/* 188 */     StringBuilder localStringBuilder = new StringBuilder();
/* 189 */     localStringBuilder.append(this.V + " " + this.E + str);
/* 190 */     for (int i = 0; i < this.V; i++) {
/* 191 */       localStringBuilder.append(i + ": ");
/* 192 */       for (DirectedEdge localDirectedEdge : this.adj[i]) {
/* 193 */         localStringBuilder.append(localDirectedEdge + "  ");
/*     */       }
/* 195 */       localStringBuilder.append(str);
/*     */     }
/* 197 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 204 */     In localIn = new In(paramArrayOfString[0]);
/* 205 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(localIn);
/* 206 */     StdOut.println(localEdgeWeightedDigraph);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     EdgeWeightedDigraph
 * JD-Core Version:    0.6.2
 */