/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class CC
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] id;
/*     */   private int[] size;
/*     */   private int count;
/*     */ 
/*     */   public CC(Graph paramGraph)
/*     */   {
/*  65 */     this.marked = new boolean[paramGraph.V()];
/*  66 */     this.id = new int[paramGraph.V()];
/*  67 */     this.size = new int[paramGraph.V()];
/*  68 */     for (int i = 0; i < paramGraph.V(); i++)
/*  69 */       if (this.marked[i] == 0) {
/*  70 */         dfs(paramGraph, i);
/*  71 */         this.count += 1;
/*     */       }
/*     */   }
/*     */ 
/*     */   private void dfs(Graph paramGraph, int paramInt)
/*     */   {
/*  78 */     this.marked[paramInt] = true;
/*  79 */     this.id[paramInt] = this.count;
/*  80 */     this.size[this.count] += 1;
/*  81 */     for (Iterator localIterator = paramGraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  82 */       if (this.marked[i] == 0)
/*  83 */         dfs(paramGraph, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int id(int paramInt)
/*     */   {
/*  94 */     return this.id[paramInt];
/*     */   }
/*     */ 
/*     */   public int size(int paramInt)
/*     */   {
/* 103 */     return this.size[this.id[paramInt]];
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/* 111 */     return this.count;
/*     */   }
/*     */ 
/*     */   public boolean areConnected(int paramInt1, int paramInt2)
/*     */   {
/* 122 */     return id(paramInt1) == id(paramInt2);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 130 */     In localIn = new In(paramArrayOfString[0]);
/* 131 */     Graph localGraph = new Graph(localIn);
/* 132 */     CC localCC = new CC(localGraph);
/*     */ 
/* 135 */     int i = localCC.count();
/* 136 */     StdOut.println(i + " components");
/*     */ 
/* 139 */     Queue[] arrayOfQueue = (Queue[])new Queue[i];
/* 140 */     for (int j = 0; j < i; j++) {
/* 141 */       arrayOfQueue[j] = new Queue();
/*     */     }
/* 143 */     for (j = 0; j < localGraph.V(); j++) {
/* 144 */       arrayOfQueue[localCC.id(j)].enqueue(Integer.valueOf(j));
/*     */     }
/*     */ 
/* 148 */     for (j = 0; j < i; j++) {
/* 149 */       for (Iterator localIterator = arrayOfQueue[j].iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 150 */         StdOut.print(k + " ");
/*     */       }
/* 152 */       StdOut.println();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     CC
 * JD-Core Version:    0.6.2
 */