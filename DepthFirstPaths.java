/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DepthFirstPaths
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private final int s;
/*     */ 
/*     */   public DepthFirstPaths(Graph paramGraph, int paramInt)
/*     */   {
/*  56 */     this.s = paramInt;
/*  57 */     this.edgeTo = new int[paramGraph.V()];
/*  58 */     this.marked = new boolean[paramGraph.V()];
/*  59 */     dfs(paramGraph, paramInt);
/*     */   }
/*     */ 
/*     */   private void dfs(Graph paramGraph, int paramInt)
/*     */   {
/*  64 */     this.marked[paramInt] = true;
/*  65 */     for (Iterator localIterator = paramGraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  66 */       if (this.marked[i] == 0) {
/*  67 */         this.edgeTo[i] = paramInt;
/*  68 */         dfs(paramGraph, i);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/*  79 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> pathTo(int paramInt)
/*     */   {
/*  90 */     if (!hasPathTo(paramInt)) return null;
/*  91 */     Stack localStack = new Stack();
/*  92 */     for (int i = paramInt; i != this.s; i = this.edgeTo[i])
/*  93 */       localStack.push(Integer.valueOf(i));
/*  94 */     localStack.push(Integer.valueOf(this.s));
/*  95 */     return localStack;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 102 */     In localIn = new In(paramArrayOfString[0]);
/* 103 */     Graph localGraph = new Graph(localIn);
/* 104 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 105 */     DepthFirstPaths localDepthFirstPaths = new DepthFirstPaths(localGraph, i);
/*     */ 
/* 107 */     for (int j = 0; j < localGraph.V(); j++)
/* 108 */       if (localDepthFirstPaths.hasPathTo(j)) {
/* 109 */         StdOut.printf("%d to %d:  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/* 110 */         for (Iterator localIterator = localDepthFirstPaths.pathTo(j).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 111 */           if (k == i) StdOut.print(k); else
/* 112 */             StdOut.print("-" + k);
/*     */         }
/* 114 */         StdOut.println();
/*     */       }
/*     */       else
/*     */       {
/* 118 */         StdOut.printf("%d to %d:  not connected\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DepthFirstPaths
 * JD-Core Version:    0.6.2
 */