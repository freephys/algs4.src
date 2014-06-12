/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Graph
/*     */ {
/*     */   private final int V;
/*     */   private int E;
/*     */   private Bag<Integer>[] adj;
/*     */ 
/*     */   public Graph(int paramInt)
/*     */   {
/*  67 */     if (paramInt < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
/*  68 */     this.V = paramInt;
/*  69 */     this.E = 0;
/*  70 */     this.adj = ((Bag[])new Bag[paramInt]);
/*  71 */     for (int i = 0; i < paramInt; i++)
/*  72 */       this.adj[i] = new Bag();
/*     */   }
/*     */ 
/*     */   public Graph(In paramIn)
/*     */   {
/*  86 */     this(paramIn.readInt());
/*  87 */     int i = paramIn.readInt();
/*  88 */     if (i < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  89 */     for (int j = 0; j < i; j++) {
/*  90 */       int k = paramIn.readInt();
/*  91 */       int m = paramIn.readInt();
/*  92 */       addEdge(k, m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Graph(Graph paramGraph)
/*     */   {
/* 101 */     this(paramGraph.V());
/* 102 */     this.E = paramGraph.E();
/*     */     Iterator localIterator;
/*     */     int j;
/* 103 */     for (int i = 0; i < paramGraph.V(); i++)
/*     */     {
/* 105 */       Stack localStack = new Stack();
/* 106 */       for (localIterator = paramGraph.adj[i].iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 107 */         localStack.push(Integer.valueOf(j));
/*     */       }
/* 109 */       for (localIterator = localStack.iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 110 */         this.adj[i].add(Integer.valueOf(j));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/* 120 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/* 128 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(int paramInt1, int paramInt2)
/*     */   {
/* 138 */     if ((paramInt1 < 0) || (paramInt1 >= this.V)) throw new IndexOutOfBoundsException();
/* 139 */     if ((paramInt2 < 0) || (paramInt2 >= this.V)) throw new IndexOutOfBoundsException();
/* 140 */     this.E += 1;
/* 141 */     this.adj[paramInt1].add(Integer.valueOf(paramInt2));
/* 142 */     this.adj[paramInt2].add(Integer.valueOf(paramInt1));
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> adj(int paramInt)
/*     */   {
/* 153 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException();
/* 154 */     return this.adj[paramInt];
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 165 */     StringBuilder localStringBuilder = new StringBuilder();
/* 166 */     String str = System.getProperty("line.separator");
/* 167 */     localStringBuilder.append(this.V + " vertices, " + this.E + " edges " + str);
/* 168 */     for (int i = 0; i < this.V; i++) {
/* 169 */       localStringBuilder.append(i + ": ");
/* 170 */       for (Iterator localIterator = this.adj[i].iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 171 */         localStringBuilder.append(j + " ");
/*     */       }
/* 173 */       localStringBuilder.append(str);
/*     */     }
/* 175 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 183 */     In localIn = new In(paramArrayOfString[0]);
/* 184 */     Graph localGraph = new Graph(localIn);
/* 185 */     StdOut.println(localGraph);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Graph
 * JD-Core Version:    0.6.2
 */