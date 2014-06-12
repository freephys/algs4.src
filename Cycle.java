/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Cycle
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private Stack<Integer> cycle;
/*     */ 
/*     */   public Cycle(Graph paramGraph)
/*     */   {
/*  50 */     if (hasSelfLoop(paramGraph)) return;
/*  51 */     if (hasParallelEdges(paramGraph)) return;
/*  52 */     this.marked = new boolean[paramGraph.V()];
/*  53 */     this.edgeTo = new int[paramGraph.V()];
/*  54 */     for (int i = 0; i < paramGraph.V(); i++)
/*  55 */       if (this.marked[i] == 0)
/*  56 */         dfs(paramGraph, -1, i);
/*     */   }
/*     */ 
/*     */   private boolean hasSelfLoop(Graph paramGraph)
/*     */   {
/*     */     Iterator localIterator;
/*  63 */     for (int i = 0; i < paramGraph.V(); i++) {
/*  64 */       for (localIterator = paramGraph.adj(i).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/*  65 */         if (i == j) {
/*  66 */           this.cycle = new Stack();
/*  67 */           this.cycle.push(Integer.valueOf(i));
/*  68 */           this.cycle.push(Integer.valueOf(i));
/*  69 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean hasParallelEdges(Graph paramGraph)
/*     */   {
/*  79 */     this.marked = new boolean[paramGraph.V()];
/*     */     Iterator localIterator;
/*     */     int j;
/*  81 */     for (int i = 0; i < paramGraph.V(); i++)
/*     */     {
/*  84 */       for (localIterator = paramGraph.adj(i).iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/*  85 */         if (this.marked[j] != 0) {
/*  86 */           this.cycle = new Stack();
/*  87 */           this.cycle.push(Integer.valueOf(i));
/*  88 */           this.cycle.push(Integer.valueOf(j));
/*  89 */           this.cycle.push(Integer.valueOf(i));
/*  90 */           return true;
/*     */         }
/*  92 */         this.marked[j] = true;
/*     */       }
/*     */ 
/*  96 */       for (localIterator = paramGraph.adj(i).iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/*  97 */         this.marked[j] = false;
/*     */       }
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean hasCycle()
/*     */   {
/* 108 */     return this.cycle != null;
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> cycle()
/*     */   {
/* 117 */     return this.cycle;
/*     */   }
/*     */ 
/*     */   private void dfs(Graph paramGraph, int paramInt1, int paramInt2) {
/* 121 */     this.marked[paramInt2] = true;
/* 122 */     for (Iterator localIterator = paramGraph.adj(paramInt2).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 125 */       if (this.cycle != null) return;
/*     */ 
/* 127 */       if (this.marked[i] == 0) {
/* 128 */         this.edgeTo[i] = paramInt2;
/* 129 */         dfs(paramGraph, paramInt2, i);
/*     */       }
/* 133 */       else if (i != paramInt1) {
/* 134 */         this.cycle = new Stack();
/* 135 */         for (int j = paramInt2; j != i; j = this.edgeTo[j]) {
/* 136 */           this.cycle.push(Integer.valueOf(j));
/*     */         }
/* 138 */         this.cycle.push(Integer.valueOf(i));
/* 139 */         this.cycle.push(Integer.valueOf(paramInt2));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 148 */     In localIn = new In(paramArrayOfString[0]);
/* 149 */     Graph localGraph = new Graph(localIn);
/* 150 */     Cycle localCycle = new Cycle(localGraph);
/* 151 */     if (localCycle.hasCycle()) {
/* 152 */       for (Iterator localIterator = localCycle.cycle().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 153 */         StdOut.print(i + " ");
/*     */       }
/* 155 */       StdOut.println();
/*     */     }
/*     */     else {
/* 158 */       StdOut.println("Graph is acyclic");
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Cycle
 * JD-Core Version:    0.6.2
 */