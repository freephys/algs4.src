/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class BreadthFirstPaths
/*     */ {
/*     */   private static final int INFINITY = 2147483647;
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private int[] distTo;
/*     */ 
/*     */   public BreadthFirstPaths(Graph paramGraph, int paramInt)
/*     */   {
/*  69 */     this.marked = new boolean[paramGraph.V()];
/*  70 */     this.distTo = new int[paramGraph.V()];
/*  71 */     this.edgeTo = new int[paramGraph.V()];
/*  72 */     bfs(paramGraph, paramInt);
/*     */ 
/*  74 */     assert (check(paramGraph, paramInt));
/*     */   }
/*     */ 
/*     */   public BreadthFirstPaths(Graph paramGraph, Iterable<Integer> paramIterable)
/*     */   {
/*  84 */     this.marked = new boolean[paramGraph.V()];
/*  85 */     this.distTo = new int[paramGraph.V()];
/*  86 */     this.edgeTo = new int[paramGraph.V()];
/*  87 */     for (int i = 0; i < paramGraph.V(); i++) this.distTo[i] = 2147483647;
/*  88 */     bfs(paramGraph, paramIterable);
/*     */   }
/*     */ 
/*     */   private void bfs(Graph paramGraph, int paramInt)
/*     */   {
/*  94 */     Queue localQueue = new Queue();
/*  95 */     for (int i = 0; i < paramGraph.V(); i++) this.distTo[i] = 2147483647;
/*  96 */     this.distTo[paramInt] = 0;
/*  97 */     this.marked[paramInt] = true;
/*  98 */     localQueue.enqueue(Integer.valueOf(paramInt));
/*     */     Iterator localIterator;
/* 100 */     while (!localQueue.isEmpty()) {
/* 101 */       i = ((Integer)localQueue.dequeue()).intValue();
/* 102 */       for (localIterator = paramGraph.adj(i).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 103 */         if (this.marked[j] == 0) {
/* 104 */           this.edgeTo[j] = i;
/* 105 */           this.distTo[i] += 1;
/* 106 */           this.marked[j] = true;
/* 107 */           localQueue.enqueue(Integer.valueOf(j));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void bfs(Graph paramGraph, Iterable<Integer> paramIterable)
/*     */   {
/* 115 */     Queue localQueue = new Queue();
/* 116 */     for (Iterator localIterator1 = paramIterable.iterator(); localIterator1.hasNext(); ) { int j = ((Integer)localIterator1.next()).intValue();
/* 117 */       this.marked[j] = true;
/* 118 */       this.distTo[j] = 0;
/* 119 */       localQueue.enqueue(Integer.valueOf(j));
/*     */     }
/*     */     int i;
/*     */     Iterator localIterator2;
/* 121 */     while (!localQueue.isEmpty()) {
/* 122 */       i = ((Integer)localQueue.dequeue()).intValue();
/* 123 */       for (localIterator2 = paramGraph.adj(i).iterator(); localIterator2.hasNext(); ) { int k = ((Integer)localIterator2.next()).intValue();
/* 124 */         if (this.marked[k] == 0) {
/* 125 */           this.edgeTo[k] = i;
/* 126 */           this.distTo[i] += 1;
/* 127 */           this.marked[k] = true;
/* 128 */           localQueue.enqueue(Integer.valueOf(k));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasPathTo(int paramInt)
/*     */   {
/* 140 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   public int distTo(int paramInt)
/*     */   {
/* 150 */     return this.distTo[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> pathTo(int paramInt)
/*     */   {
/* 160 */     if (!hasPathTo(paramInt)) return null;
/* 161 */     Stack localStack = new Stack();
/*     */ 
/* 163 */     for (int i = paramInt; this.distTo[i] != 0; i = this.edgeTo[i])
/* 164 */       localStack.push(Integer.valueOf(i));
/* 165 */     localStack.push(Integer.valueOf(i));
/* 166 */     return localStack;
/*     */   }
/*     */ 
/*     */   private boolean check(Graph paramGraph, int paramInt)
/*     */   {
/* 174 */     if (this.distTo[paramInt] != 0) {
/* 175 */       StdOut.println("distance of source " + paramInt + " to itself = " + this.distTo[paramInt]);
/* 176 */       return false;
/*     */     }
/*     */     Iterator localIterator;
/* 181 */     for (int i = 0; i < paramGraph.V(); i++) {
/* 182 */       for (localIterator = paramGraph.adj(i).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 183 */         if (hasPathTo(i) != hasPathTo(k)) {
/* 184 */           StdOut.println("edge " + i + "-" + k);
/* 185 */           StdOut.println("hasPathTo(" + i + ") = " + hasPathTo(i));
/* 186 */           StdOut.println("hasPathTo(" + k + ") = " + hasPathTo(k));
/* 187 */           return false;
/*     */         }
/* 189 */         if ((hasPathTo(i)) && (this.distTo[k] > this.distTo[i] + 1)) {
/* 190 */           StdOut.println("edge " + i + "-" + k);
/* 191 */           StdOut.println("distTo[" + i + "] = " + this.distTo[i]);
/* 192 */           StdOut.println("distTo[" + k + "] = " + this.distTo[k]);
/* 193 */           return false;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 200 */     for (i = 0; i < paramGraph.V(); i++) {
/* 201 */       if ((hasPathTo(i)) && (i != paramInt)) {
/* 202 */         int j = this.edgeTo[i];
/* 203 */         if (this.distTo[i] != this.distTo[j] + 1) {
/* 204 */           StdOut.println("shortest path edge " + j + "-" + i);
/* 205 */           StdOut.println("distTo[" + j + "] = " + this.distTo[j]);
/* 206 */           StdOut.println("distTo[" + i + "] = " + this.distTo[i]);
/* 207 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 218 */     In localIn = new In(paramArrayOfString[0]);
/* 219 */     Graph localGraph = new Graph(localIn);
/*     */ 
/* 222 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 223 */     BreadthFirstPaths localBreadthFirstPaths = new BreadthFirstPaths(localGraph, i);
/*     */ 
/* 225 */     for (int j = 0; j < localGraph.V(); j++)
/* 226 */       if (localBreadthFirstPaths.hasPathTo(j)) {
/* 227 */         StdOut.printf("%d to %d (%d):  ", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(localBreadthFirstPaths.distTo(j)) });
/* 228 */         for (Iterator localIterator = localBreadthFirstPaths.pathTo(j).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 229 */           if (k == i) StdOut.print(k); else
/* 230 */             StdOut.print("-" + k);
/*     */         }
/* 232 */         StdOut.println();
/*     */       }
/*     */       else
/*     */       {
/* 236 */         StdOut.printf("%d to %d (-):  not connected\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/*     */       }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BreadthFirstPaths
 * JD-Core Version:    0.6.2
 */