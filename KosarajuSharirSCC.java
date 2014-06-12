/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class KosarajuSharirSCC
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] id;
/*     */   private int count;
/*     */ 
/*     */   public KosarajuSharirSCC(Digraph paramDigraph)
/*     */   {
/* 104 */     DepthFirstOrder localDepthFirstOrder = new DepthFirstOrder(paramDigraph.reverse());
/*     */ 
/* 107 */     this.marked = new boolean[paramDigraph.V()];
/* 108 */     this.id = new int[paramDigraph.V()];
/* 109 */     for (Iterator localIterator = localDepthFirstOrder.reversePost().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 110 */       if (this.marked[i] == 0) {
/* 111 */         dfs(paramDigraph, i);
/* 112 */         this.count += 1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 117 */     assert (check(paramDigraph));
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt)
/*     */   {
/* 122 */     this.marked[paramInt] = true;
/* 123 */     this.id[paramInt] = this.count;
/* 124 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 125 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/* 134 */     return this.count;
/*     */   }
/*     */ 
/*     */   public boolean stronglyConnected(int paramInt1, int paramInt2)
/*     */   {
/* 145 */     return this.id[paramInt1] == this.id[paramInt2];
/*     */   }
/*     */ 
/*     */   public int id(int paramInt)
/*     */   {
/* 154 */     return this.id[paramInt];
/*     */   }
/*     */ 
/*     */   private boolean check(Digraph paramDigraph)
/*     */   {
/* 159 */     TransitiveClosure localTransitiveClosure = new TransitiveClosure(paramDigraph);
/* 160 */     for (int i = 0; i < paramDigraph.V(); i++) {
/* 161 */       for (int j = 0; j < paramDigraph.V(); j++) {
/* 162 */         if (stronglyConnected(i, j) != ((localTransitiveClosure.reachable(i, j)) && (localTransitiveClosure.reachable(j, i))))
/* 163 */           return false;
/*     */       }
/*     */     }
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 173 */     In localIn = new In(paramArrayOfString[0]);
/* 174 */     Digraph localDigraph = new Digraph(localIn);
/* 175 */     KosarajuSharirSCC localKosarajuSharirSCC = new KosarajuSharirSCC(localDigraph);
/*     */ 
/* 178 */     int i = localKosarajuSharirSCC.count();
/* 179 */     StdOut.println(i + " components");
/*     */ 
/* 182 */     Queue[] arrayOfQueue = (Queue[])new Queue[i];
/* 183 */     for (int j = 0; j < i; j++) {
/* 184 */       arrayOfQueue[j] = new Queue();
/*     */     }
/* 186 */     for (j = 0; j < localDigraph.V(); j++) {
/* 187 */       arrayOfQueue[localKosarajuSharirSCC.id(j)].enqueue(Integer.valueOf(j));
/*     */     }
/*     */ 
/* 191 */     for (j = 0; j < i; j++) {
/* 192 */       for (Iterator localIterator = arrayOfQueue[j].iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 193 */         StdOut.print(k + " ");
/*     */       }
/* 195 */       StdOut.println();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     KosarajuSharirSCC
 * JD-Core Version:    0.6.2
 */