/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class EdgeWeightedGraph
/*     */ {
/*     */   private final int V;
/*     */   private int E;
/*     */   private Bag<Edge>[] adj;
/*     */ 
/*     */   public EdgeWeightedGraph(int paramInt)
/*     */   {
/*  56 */     if (paramInt < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
/*  57 */     this.V = paramInt;
/*  58 */     this.E = 0;
/*  59 */     this.adj = ((Bag[])new Bag[paramInt]);
/*  60 */     for (int i = 0; i < paramInt; i++)
/*  61 */       this.adj[i] = new Bag();
/*     */   }
/*     */ 
/*     */   public EdgeWeightedGraph(int paramInt1, int paramInt2)
/*     */   {
/*  73 */     this(paramInt1);
/*  74 */     if (paramInt2 < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  75 */     for (int i = 0; i < paramInt2; i++) {
/*  76 */       int j = (int)(Math.random() * paramInt1);
/*  77 */       int k = (int)(Math.random() * paramInt1);
/*  78 */       double d = Math.round(100.0D * Math.random()) / 100.0D;
/*  79 */       Edge localEdge = new Edge(j, k, d);
/*  80 */       addEdge(localEdge);
/*     */     }
/*     */   }
/*     */ 
/*     */   public EdgeWeightedGraph(In paramIn)
/*     */   {
/*  95 */     this(paramIn.readInt());
/*  96 */     int i = paramIn.readInt();
/*  97 */     if (i < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  98 */     for (int j = 0; j < i; j++) {
/*  99 */       int k = paramIn.readInt();
/* 100 */       int m = paramIn.readInt();
/* 101 */       double d = paramIn.readDouble();
/* 102 */       Edge localEdge = new Edge(k, m, d);
/* 103 */       addEdge(localEdge);
/*     */     }
/*     */   }
/*     */ 
/*     */   public EdgeWeightedGraph(EdgeWeightedGraph paramEdgeWeightedGraph)
/*     */   {
/* 112 */     this(paramEdgeWeightedGraph.V());
/* 113 */     this.E = paramEdgeWeightedGraph.E();
/*     */     Iterator localIterator;
/*     */     Edge localEdge;
/* 114 */     for (int i = 0; i < paramEdgeWeightedGraph.V(); i++)
/*     */     {
/* 116 */       Stack localStack = new Stack();
/* 117 */       for (localIterator = paramEdgeWeightedGraph.adj[i].iterator(); localIterator.hasNext(); ) { localEdge = (Edge)localIterator.next();
/* 118 */         localStack.push(localEdge);
/*     */       }
/* 120 */       for (localIterator = localStack.iterator(); localIterator.hasNext(); ) { localEdge = (Edge)localIterator.next();
/* 121 */         this.adj[i].add(localEdge);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/* 132 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/* 140 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(Edge paramEdge)
/*     */   {
/* 149 */     int i = paramEdge.either();
/* 150 */     int j = paramEdge.other(i);
/* 151 */     if ((i < 0) || (i >= this.V)) throw new IndexOutOfBoundsException("vertex " + i + " is not between 0 and " + (this.V - 1));
/* 152 */     if ((j < 0) || (j >= this.V)) throw new IndexOutOfBoundsException("vertex " + j + " is not between 0 and " + (this.V - 1));
/* 153 */     this.adj[i].add(paramEdge);
/* 154 */     this.adj[j].add(paramEdge);
/* 155 */     this.E += 1;
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> adj(int paramInt)
/*     */   {
/* 165 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt + " is not between 0 and " + (this.V - 1));
/* 166 */     return this.adj[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Edge> edges()
/*     */   {
/* 176 */     Bag localBag = new Bag();
/*     */     int j;
/* 177 */     for (int i = 0; i < this.V; i++) {
/* 178 */       j = 0;
/* 179 */       for (Edge localEdge : adj(i)) {
/* 180 */         if (localEdge.other(i) > i) {
/* 181 */           localBag.add(localEdge);
/*     */         }
/* 184 */         else if (localEdge.other(i) == i) {
/* 185 */           if (j % 2 == 0) localBag.add(localEdge);
/* 186 */           j++;
/*     */         }
/*     */       }
/*     */     }
/* 190 */     return localBag;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 200 */     String str = System.getProperty("line.separator");
/* 201 */     StringBuilder localStringBuilder = new StringBuilder();
/* 202 */     localStringBuilder.append(this.V + " " + this.E + str);
/* 203 */     for (int i = 0; i < this.V; i++) {
/* 204 */       localStringBuilder.append(i + ": ");
/* 205 */       for (Edge localEdge : this.adj[i]) {
/* 206 */         localStringBuilder.append(localEdge + "  ");
/*     */       }
/* 208 */       localStringBuilder.append(str);
/*     */     }
/* 210 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 217 */     In localIn = new In(paramArrayOfString[0]);
/* 218 */     EdgeWeightedGraph localEdgeWeightedGraph = new EdgeWeightedGraph(localIn);
/* 219 */     StdOut.println(localEdgeWeightedGraph);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     EdgeWeightedGraph
 * JD-Core Version:    0.6.2
 */