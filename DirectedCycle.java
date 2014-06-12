/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DirectedCycle
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private boolean[] onStack;
/*     */   private Stack<Integer> cycle;
/*     */ 
/*     */   public DirectedCycle(Digraph paramDigraph)
/*     */   {
/*  55 */     this.marked = new boolean[paramDigraph.V()];
/*  56 */     this.onStack = new boolean[paramDigraph.V()];
/*  57 */     this.edgeTo = new int[paramDigraph.V()];
/*  58 */     for (int i = 0; i < paramDigraph.V(); i++)
/*  59 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt)
/*     */   {
/*  64 */     this.onStack[paramInt] = true;
/*  65 */     this.marked[paramInt] = true;
/*  66 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/*  69 */       if (this.cycle != null) return;
/*     */ 
/*  72 */       if (this.marked[i] == 0) {
/*  73 */         this.edgeTo[i] = paramInt;
/*  74 */         dfs(paramDigraph, i);
/*     */       }
/*  78 */       else if (this.onStack[i] != 0) {
/*  79 */         this.cycle = new Stack();
/*  80 */         for (int j = paramInt; j != i; j = this.edgeTo[j]) {
/*  81 */           this.cycle.push(Integer.valueOf(j));
/*     */         }
/*  83 */         this.cycle.push(Integer.valueOf(i));
/*  84 */         this.cycle.push(Integer.valueOf(paramInt));
/*     */       }
/*     */     }
/*     */ 
/*  88 */     this.onStack[paramInt] = false;
/*     */   }
/*     */ 
/*     */   public boolean hasCycle()
/*     */   {
/*  96 */     return this.cycle != null;
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> cycle()
/*     */   {
/* 105 */     return this.cycle;
/*     */   }
/*     */ 
/*     */   private boolean check(Digraph paramDigraph)
/*     */   {
/* 112 */     if (hasCycle())
/*     */     {
/* 114 */       int i = -1; int j = -1;
/* 115 */       for (Iterator localIterator = cycle().iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 116 */         if (i == -1) i = k;
/* 117 */         j = k;
/*     */       }
/* 119 */       if (i != j) {
/* 120 */         System.err.printf("cycle begins with %d and ends with %d\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/* 121 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 133 */     In localIn = new In(paramArrayOfString[0]);
/* 134 */     Digraph localDigraph = new Digraph(localIn);
/*     */ 
/* 136 */     DirectedCycle localDirectedCycle = new DirectedCycle(localDigraph);
/* 137 */     if (localDirectedCycle.hasCycle()) {
/* 138 */       StdOut.print("Cycle: ");
/* 139 */       for (Iterator localIterator = localDirectedCycle.cycle().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 140 */         StdOut.print(i + " ");
/*     */       }
/* 142 */       StdOut.println();
/*     */     }
/*     */     else
/*     */     {
/* 146 */       StdOut.println("No cycle");
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DirectedCycle
 * JD-Core Version:    0.6.2
 */