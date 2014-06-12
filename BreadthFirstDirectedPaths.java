/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class BreadthFirstDirectedPaths
/*     */ {
/*     */   private static final int INFINITY = 2147483647;
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private int[] distTo;
/*     */ 
/*     */   public BreadthFirstDirectedPaths(Digraph paramDigraph, int paramInt)
/*     */   {
/*  54 */     this.marked = new boolean[paramDigraph.V()];
/*  55 */     this.distTo = new int[paramDigraph.V()];
/*  56 */     this.edgeTo = new int[paramDigraph.V()];
/*  57 */     for (int i = 0; i < paramDigraph.V(); i++) this.distTo[i] = 2147483647;
/*  58 */     bfs(paramDigraph, paramInt);
/*     */   }
/*     */ 
/*     */   public BreadthFirstDirectedPaths(Digraph paramDigraph, Iterable<Integer> paramIterable)
/*     */   {
/*  68 */     this.marked = new boolean[paramDigraph.V()];
/*  69 */     this.distTo = new int[paramDigraph.V()];
/*  70 */     this.edgeTo = new int[paramDigraph.V()];
/*  71 */     for (int i = 0; i < paramDigraph.V(); i++) this.distTo[i] = 2147483647;
/*  72 */     bfs(paramDigraph, paramIterable);
/*     */   }
/*     */ 
/*     */   private void bfs(Digraph paramDigraph, int paramInt)
/*     */   {
/*  77 */     Queue localQueue = new Queue();
/*  78 */     this.marked[paramInt] = true;
/*  79 */     this.distTo[paramInt] = 0;
/*  80 */     localQueue.enqueue(Integer.valueOf(paramInt));
/*     */     int i;
/*     */     Iterator localIterator;
/*  81 */     while (!localQueue.isEmpty()) {
/*  82 */       i = ((Integer)localQueue.dequeue()).intValue();
/*  83 */       for (localIterator = paramDigraph.adj(i).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/*  84 */         if (this.marked[j] == 0) {
/*  85 */           this.edgeTo[j] = i;
/*  86 */           this.distTo[i] += 1;
/*  87 */           this.marked[j] = true;
/*  88 */           localQueue.enqueue(Integer.valueOf(j));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void bfs(Digraph paramDigraph, Iterable<Integer> paramIterable)
/*     */   {
/*  96 */     Queue localQueue = new Queue();
/*  97 */     for (Iterator localIterator1 = paramIterable.iterator(); localIterator1.hasNext(); ) { int j = ((Integer)localIterator1.next()).intValue();
/*  98 */       this.marked[j] = true;
/*  99 */       this.distTo[j] = 0;
/* 100 */       localQueue.enqueue(Integer.valueOf(j));
/*     */     }
/*     */     int i;
/*     */     Iterator localIterator2;
/* 102 */     while (!localQueue.isEmpty()) {
/* 103 */       i = ((Integer)localQueue.dequeue()).intValue();
/* 104 */       for (localIterator2 = paramDigraph.adj(i).iterator(); localIterator2.hasNext(); ) { int k = ((Integer)localIterator2.next()).intValue();
/* 105 */         if (this.marked[k] == 0) {
/* 106 */           this.edgeTo[k] = i;
/* 107 */           this.distTo[i] += 1;
/* 108 */           this.marked[k] = true;
/* 109 */           localQueue.enqueue(Integer.valueOf(k));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/* 121 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   public int distTo(int paramInt)
/*     */   {
/* 131 */     return this.distTo[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> pathTo(int paramInt)
/*     */   {
/* 141 */     if (!hasPathTo(paramInt)) return null;
/* 142 */     Stack localStack = new Stack();
/*     */ 
/* 144 */     for (int i = paramInt; this.distTo[i] != 0; i = this.edgeTo[i])
/* 145 */       localStack.push(Integer.valueOf(i));
/* 146 */     localStack.push(Integer.valueOf(i));
/* 147 */     return localStack;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 154 */     In localIn = new In(paramArrayOfString[0]);
/* 155 */     Digraph localDigraph = new Digraph(localIn);
/*     */ 
/* 158 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 159 */     BreadthFirstDirectedPaths localBreadthFirstDirectedPaths = new BreadthFirstDirectedPaths(localDigraph, i);
/*     */ 
/* 161 */     for (int j = 0; j < localDigraph.V(); j++)
/* 162 */       if (localBreadthFirstDirectedPaths.hasPathTo(j)) {
/* 163 */         StdOut.printf("%d to %d (%d):  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(localBreadthFirstDirectedPaths.distTo(j)) });
/* 164 */         for (Iterator localIterator = localBreadthFirstDirectedPaths.pathTo(j).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 165 */           if (k == i) StdOut.print(k); else
/* 166 */             StdOut.print("->" + k);
/*     */         }
/* 168 */         StdOut.println();
/*     */       }
/*     */       else
/*     */       {
/* 172 */         StdOut.printf("%d to %d (-):  not connected\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BreadthFirstDirectedPaths
 * JD-Core Version:    0.6.2
 */