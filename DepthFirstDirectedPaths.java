/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DepthFirstDirectedPaths
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private final int s;
/*     */ 
/*     */   public DepthFirstDirectedPaths(Digraph paramDigraph, int paramInt)
/*     */   {
/*  54 */     this.marked = new boolean[paramDigraph.V()];
/*  55 */     this.edgeTo = new int[paramDigraph.V()];
/*  56 */     this.s = paramInt;
/*  57 */     dfs(paramDigraph, paramInt);
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt) {
/*  61 */     this.marked[paramInt] = true;
/*  62 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  63 */       if (this.marked[i] == 0) {
/*  64 */         this.edgeTo[i] = paramInt;
/*  65 */         dfs(paramDigraph, i);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/*  77 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> pathTo(int paramInt)
/*     */   {
/*  89 */     if (!hasPathTo(paramInt)) return null;
/*  90 */     Stack localStack = new Stack();
/*  91 */     for (int i = paramInt; i != this.s; i = this.edgeTo[i])
/*  92 */       localStack.push(Integer.valueOf(i));
/*  93 */     localStack.push(Integer.valueOf(this.s));
/*  94 */     return localStack;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 101 */     In localIn = new In(paramArrayOfString[0]);
/* 102 */     Digraph localDigraph = new Digraph(localIn);
/*     */ 
/* 105 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 106 */     DepthFirstDirectedPaths localDepthFirstDirectedPaths = new DepthFirstDirectedPaths(localDigraph, i);
/*     */ 
/* 108 */     for (int j = 0; j < localDigraph.V(); j++)
/* 109 */       if (localDepthFirstDirectedPaths.hasPathTo(j)) {
/* 110 */         StdOut.printf("%d to %d:  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/* 111 */         for (Iterator localIterator = localDepthFirstDirectedPaths.pathTo(j).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 112 */           if (k == i) StdOut.print(k); else
/* 113 */             StdOut.print("-" + k);
/*     */         }
/* 115 */         StdOut.println();
/*     */       }
/*     */       else
/*     */       {
/* 119 */         StdOut.printf("%d to %d:  not connected\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DepthFirstDirectedPaths
 * JD-Core Version:    0.6.2
 */