/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TarjanSCC
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] id;
/*     */   private int[] low;
/*     */   private int pre;
/*     */   private int count;
/*     */   private Stack<Integer> stack;
/*     */ 
/*     */   public TarjanSCC(Digraph paramDigraph)
/*     */   {
/*  65 */     this.marked = new boolean[paramDigraph.V()];
/*  66 */     this.stack = new Stack();
/*  67 */     this.id = new int[paramDigraph.V()];
/*  68 */     this.low = new int[paramDigraph.V()];
/*  69 */     for (int i = 0; i < paramDigraph.V(); i++) {
/*  70 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */ 
/*     */     }
/*     */ 
/*  74 */     assert (check(paramDigraph));
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt) {
/*  78 */     this.marked[paramInt] = true;
/*  79 */     this.low[paramInt] = (this.pre++);
/*  80 */     int i = this.low[paramInt];
/*  81 */     this.stack.push(Integer.valueOf(paramInt));
/*  82 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/*  83 */       if (this.marked[k] == 0) dfs(paramDigraph, k);
/*  84 */       if (this.low[k] < i) i = this.low[k];
/*     */     }
/*  86 */     if (i < this.low[paramInt]) { this.low[paramInt] = i; } else {
/*     */       int j;
/*     */       do {
/*  89 */         j = ((Integer)this.stack.pop()).intValue();
/*  90 */         this.id[j] = this.count;
/*  91 */         this.low[j] = paramDigraph.V();
/*  92 */       }while (j != paramInt);
/*  93 */       this.count += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/* 102 */     return this.count;
/*     */   }
/*     */ 
/*     */   public boolean stronglyConnected(int paramInt1, int paramInt2)
/*     */   {
/* 114 */     return this.id[paramInt1] == this.id[paramInt2];
/*     */   }
/*     */ 
/*     */   public int id(int paramInt)
/*     */   {
/* 123 */     return this.id[paramInt];
/*     */   }
/*     */ 
/*     */   private boolean check(Digraph paramDigraph)
/*     */   {
/* 128 */     TransitiveClosure localTransitiveClosure = new TransitiveClosure(paramDigraph);
/* 129 */     for (int i = 0; i < paramDigraph.V(); i++) {
/* 130 */       for (int j = 0; j < paramDigraph.V(); j++) {
/* 131 */         if (stronglyConnected(i, j) != ((localTransitiveClosure.reachable(i, j)) && (localTransitiveClosure.reachable(j, i))))
/* 132 */           return false;
/*     */       }
/*     */     }
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 142 */     In localIn = new In(paramArrayOfString[0]);
/* 143 */     Digraph localDigraph = new Digraph(localIn);
/* 144 */     TarjanSCC localTarjanSCC = new TarjanSCC(localDigraph);
/*     */ 
/* 147 */     int i = localTarjanSCC.count();
/* 148 */     StdOut.println(i + " components");
/*     */ 
/* 151 */     Queue[] arrayOfQueue = (Queue[])new Queue[i];
/* 152 */     for (int j = 0; j < i; j++) {
/* 153 */       arrayOfQueue[j] = new Queue();
/*     */     }
/* 155 */     for (j = 0; j < localDigraph.V(); j++) {
/* 156 */       arrayOfQueue[localTarjanSCC.id(j)].enqueue(Integer.valueOf(j));
/*     */     }
/*     */ 
/* 160 */     for (j = 0; j < i; j++) {
/* 161 */       for (Iterator localIterator = arrayOfQueue[j].iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 162 */         StdOut.print(k + " ");
/*     */       }
/* 164 */       StdOut.println();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TarjanSCC
 * JD-Core Version:    0.6.2
 */