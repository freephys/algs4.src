/*     */ import java.util.InputMismatchException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Digraph
/*     */ {
/*     */   private final int V;
/*     */   private int E;
/*     */   private Bag<Integer>[] adj;
/*     */ 
/*     */   public Digraph(int paramInt)
/*     */   {
/*  63 */     if (paramInt < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
/*  64 */     this.V = paramInt;
/*  65 */     this.E = 0;
/*  66 */     this.adj = ((Bag[])new Bag[paramInt]);
/*  67 */     for (int i = 0; i < paramInt; i++)
/*  68 */       this.adj[i] = new Bag();
/*     */   }
/*     */ 
/*     */   public Digraph(In paramIn)
/*     */   {
/*     */     try
/*     */     {
/*  83 */       this.V = paramIn.readInt();
/*  84 */       if (this.V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
/*  85 */       this.adj = ((Bag[])new Bag[this.V]);
/*  86 */       for (int i = 0; i < this.V; i++) {
/*  87 */         this.adj[i] = new Bag();
/*     */       }
/*  89 */       i = paramIn.readInt();
/*  90 */       if (i < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
/*  91 */       for (int j = 0; j < i; j++) {
/*  92 */         int k = paramIn.readInt();
/*  93 */         int m = paramIn.readInt();
/*  94 */         addEdge(k, m);
/*     */       }
/*     */     }
/*     */     catch (NoSuchElementException localNoSuchElementException) {
/*  98 */       throw new InputMismatchException("Invalid input format in Digraph constructor");
/*     */     }
/*     */   }
/*     */ 
/*     */   public Digraph(Digraph paramDigraph)
/*     */   {
/* 107 */     this(paramDigraph.V());
/* 108 */     this.E = paramDigraph.E();
/*     */     Iterator localIterator;
/*     */     int j;
/* 109 */     for (int i = 0; i < paramDigraph.V(); i++)
/*     */     {
/* 111 */       Stack localStack = new Stack();
/* 112 */       for (localIterator = paramDigraph.adj[i].iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 113 */         localStack.push(Integer.valueOf(j));
/*     */       }
/* 115 */       for (localIterator = localStack.iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 116 */         this.adj[i].add(Integer.valueOf(j));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/* 126 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/* 134 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(int paramInt1, int paramInt2)
/*     */   {
/* 145 */     if ((paramInt1 < 0) || (paramInt1 >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt1 + " is not between 0 and " + (this.V - 1));
/* 146 */     if ((paramInt2 < 0) || (paramInt2 >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt2 + " is not between 0 and " + (this.V - 1));
/* 147 */     this.adj[paramInt1].add(Integer.valueOf(paramInt2));
/* 148 */     this.E += 1;
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> adj(int paramInt)
/*     */   {
/* 158 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException();
/* 159 */     return this.adj[paramInt];
/*     */   }
/*     */ 
/*     */   public Digraph reverse()
/*     */   {
/* 167 */     Digraph localDigraph = new Digraph(this.V);
/*     */     Iterator localIterator;
/* 168 */     for (int i = 0; i < this.V; i++) {
/* 169 */       for (localIterator = adj(i).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 170 */         localDigraph.addEdge(j, i);
/*     */       }
/*     */     }
/* 173 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 183 */     StringBuilder localStringBuilder = new StringBuilder();
/* 184 */     String str = System.getProperty("line.separator");
/* 185 */     localStringBuilder.append(this.V + " vertices, " + this.E + " edges " + str);
/* 186 */     for (int i = 0; i < this.V; i++) {
/* 187 */       localStringBuilder.append(String.format("%d: ", new Object[] { Integer.valueOf(i) }));
/* 188 */       for (Iterator localIterator = this.adj[i].iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 189 */         localStringBuilder.append(String.format("%d ", new Object[] { Integer.valueOf(j) }));
/*     */       }
/* 191 */       localStringBuilder.append(str);
/*     */     }
/* 193 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 200 */     In localIn = new In(paramArrayOfString[0]);
/* 201 */     Digraph localDigraph = new Digraph(localIn);
/* 202 */     StdOut.println(localDigraph);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Digraph
 * JD-Core Version:    0.6.2
 */