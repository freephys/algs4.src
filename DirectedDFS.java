/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DirectedDFS
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int count;
/*     */ 
/*     */   public DirectedDFS(Digraph paramDigraph, int paramInt)
/*     */   {
/*  50 */     this.marked = new boolean[paramDigraph.V()];
/*  51 */     dfs(paramDigraph, paramInt);
/*     */   }
/*     */ 
/*     */   public DirectedDFS(Digraph paramDigraph, Iterable<Integer> paramIterable)
/*     */   {
/*  61 */     this.marked = new boolean[paramDigraph.V()];
/*  62 */     for (Iterator localIterator = paramIterable.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  63 */       if (this.marked[i] == 0) dfs(paramDigraph, i); 
/*     */     }
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt)
/*     */   {
/*  68 */     this.count += 1;
/*  69 */     this.marked[paramInt] = true;
/*  70 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  71 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean marked(int paramInt)
/*     */   {
/*  82 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/*  92 */     return this.count;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 101 */     In localIn = new In(paramArrayOfString[0]);
/* 102 */     Digraph localDigraph = new Digraph(localIn);
/*     */ 
/* 105 */     Bag localBag = new Bag();
/* 106 */     for (int i = 1; i < paramArrayOfString.length; i++) {
/* 107 */       j = Integer.parseInt(paramArrayOfString[i]);
/* 108 */       localBag.add(Integer.valueOf(j));
/*     */     }
/*     */ 
/* 112 */     DirectedDFS localDirectedDFS = new DirectedDFS(localDigraph, localBag);
/*     */ 
/* 115 */     for (int j = 0; j < localDigraph.V(); j++) {
/* 116 */       if (localDirectedDFS.marked(j)) StdOut.print(j + " ");
/*     */     }
/* 118 */     StdOut.println();
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DirectedDFS
 * JD-Core Version:    0.6.2
 */