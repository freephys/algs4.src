/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class GabowSCC
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] id;
/*     */   private int[] preorder;
/*     */   private int pre;
/*     */   private int count;
/*     */   private Stack<Integer> stack1;
/*     */   private Stack<Integer> stack2;
/*     */ 
/*     */   public GabowSCC(Digraph paramDigraph)
/*     */   {
/*  65 */     this.marked = new boolean[paramDigraph.V()];
/*  66 */     this.stack1 = new Stack();
/*  67 */     this.stack2 = new Stack();
/*  68 */     this.id = new int[paramDigraph.V()];
/*  69 */     this.preorder = new int[paramDigraph.V()];
/*  70 */     for (int i = 0; i < paramDigraph.V(); i++) this.id[i] = -1;
/*     */ 
/*  72 */     for (i = 0; i < paramDigraph.V(); i++) {
/*  73 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */ 
/*     */     }
/*     */ 
/*  77 */     assert (check(paramDigraph));
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt) {
/*  81 */     this.marked[paramInt] = true;
/*  82 */     this.preorder[paramInt] = (this.pre++);
/*  83 */     this.stack1.push(Integer.valueOf(paramInt));
/*  84 */     this.stack2.push(Integer.valueOf(paramInt));
/*  85 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/*  86 */       if (this.marked[j] == 0) dfs(paramDigraph, j);
/*  87 */       else if (this.id[j] == -1) {
/*  88 */         while (this.preorder[((Integer)this.stack2.peek()).intValue()] > this.preorder[j]) {
/*  89 */           this.stack2.pop();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  94 */     if (((Integer)this.stack2.peek()).intValue() == paramInt) { this.stack2.pop();
/*     */       int i;
/*     */       do {
/*  98 */         i = ((Integer)this.stack1.pop()).intValue();
/*  99 */         this.id[i] = this.count;
/* 100 */       }while (i != paramInt);
/* 101 */       this.count += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/* 110 */     return this.count;
/*     */   }
/*     */ 
/*     */   public boolean stronglyConnected(int paramInt1, int paramInt2)
/*     */   {
/* 121 */     return this.id[paramInt1] == this.id[paramInt2];
/*     */   }
/*     */ 
/*     */   public int id(int paramInt)
/*     */   {
/* 130 */     return this.id[paramInt];
/*     */   }
/*     */ 
/*     */   private boolean check(Digraph paramDigraph)
/*     */   {
/* 135 */     TransitiveClosure localTransitiveClosure = new TransitiveClosure(paramDigraph);
/* 136 */     for (int i = 0; i < paramDigraph.V(); i++) {
/* 137 */       for (int j = 0; j < paramDigraph.V(); j++) {
/* 138 */         if (stronglyConnected(i, j) != ((localTransitiveClosure.reachable(i, j)) && (localTransitiveClosure.reachable(j, i))))
/* 139 */           return false;
/*     */       }
/*     */     }
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 149 */     In localIn = new In(paramArrayOfString[0]);
/* 150 */     Digraph localDigraph = new Digraph(localIn);
/* 151 */     GabowSCC localGabowSCC = new GabowSCC(localDigraph);
/*     */ 
/* 154 */     int i = localGabowSCC.count();
/* 155 */     StdOut.println(i + " components");
/*     */ 
/* 158 */     Queue[] arrayOfQueue = (Queue[])new Queue[i];
/* 159 */     for (int j = 0; j < i; j++) {
/* 160 */       arrayOfQueue[j] = new Queue();
/*     */     }
/* 162 */     for (j = 0; j < localDigraph.V(); j++) {
/* 163 */       arrayOfQueue[localGabowSCC.id(j)].enqueue(Integer.valueOf(j));
/*     */     }
/*     */ 
/* 167 */     for (j = 0; j < i; j++) {
/* 168 */       for (Iterator localIterator = arrayOfQueue[j].iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 169 */         StdOut.print(k + " ");
/*     */       }
/* 171 */       StdOut.println();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     GabowSCC
 * JD-Core Version:    0.6.2
 */