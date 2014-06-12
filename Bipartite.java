/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class Bipartite
/*     */ {
/*     */   private boolean isBipartite;
/*     */   private boolean[] color;
/*     */   private boolean[] marked;
/*     */   private int[] edgeTo;
/*     */   private Stack<Integer> cycle;
/*     */ 
/*     */   public Bipartite(Graph paramGraph)
/*     */   {
/*  48 */     this.isBipartite = true;
/*  49 */     this.color = new boolean[paramGraph.V()];
/*  50 */     this.marked = new boolean[paramGraph.V()];
/*  51 */     this.edgeTo = new int[paramGraph.V()];
/*     */ 
/*  53 */     for (int i = 0; i < paramGraph.V(); i++) {
/*  54 */       if (this.marked[i] == 0) {
/*  55 */         dfs(paramGraph, i);
/*     */       }
/*     */     }
/*  58 */     assert (check(paramGraph));
/*     */   }
/*     */ 
/*     */   private void dfs(Graph paramGraph, int paramInt) {
/*  62 */     this.marked[paramInt] = true;
/*  63 */     for (Iterator localIterator = paramGraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/*  66 */       if (this.cycle != null) return;
/*     */ 
/*  69 */       if (this.marked[i] == 0) {
/*  70 */         this.edgeTo[i] = paramInt;
/*  71 */         this.color[i] = (this.color[paramInt] == 0 ? 1 : false);
/*  72 */         dfs(paramGraph, i);
/*     */       }
/*  76 */       else if (this.color[i] == this.color[paramInt]) {
/*  77 */         this.isBipartite = false;
/*  78 */         this.cycle = new Stack();
/*  79 */         this.cycle.push(Integer.valueOf(i));
/*  80 */         for (int j = paramInt; j != i; j = this.edgeTo[j]) {
/*  81 */           this.cycle.push(Integer.valueOf(j));
/*     */         }
/*  83 */         this.cycle.push(Integer.valueOf(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isBipartite()
/*     */   {
/*  93 */     return this.isBipartite;
/*     */   }
/*     */ 
/*     */   public boolean color(int paramInt)
/*     */   {
/* 105 */     if (!this.isBipartite)
/* 106 */       throw new UnsupportedOperationException("Graph is not bipartite");
/* 107 */     return this.color[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> oddCycle()
/*     */   {
/* 117 */     return this.cycle;
/*     */   }
/*     */ 
/*     */   private boolean check(Graph paramGraph)
/*     */   {
/*     */     int i;
/* 122 */     if (this.isBipartite)
/*     */     {
/*     */       Iterator localIterator1;
/* 123 */       for (i = 0; i < paramGraph.V(); i++) {
/* 124 */         for (localIterator1 = paramGraph.adj(i).iterator(); localIterator1.hasNext(); ) { int k = ((Integer)localIterator1.next()).intValue();
/* 125 */           if (this.color[i] == this.color[k]) {
/* 126 */             System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", new Object[] { Integer.valueOf(i), Integer.valueOf(k), Integer.valueOf(i), Integer.valueOf(k) });
/* 127 */             return false;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 136 */       i = -1; int j = -1;
/* 137 */       for (Iterator localIterator2 = oddCycle().iterator(); localIterator2.hasNext(); ) { int m = ((Integer)localIterator2.next()).intValue();
/* 138 */         if (i == -1) i = m;
/* 139 */         j = m;
/*     */       }
/* 141 */       if (i != j) {
/* 142 */         System.err.printf("cycle begins with %d and ends with %d\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
/* 143 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 155 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 156 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 157 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*     */ 
/* 159 */     Graph localGraph = new Graph(i);
/* 160 */     int[] arrayOfInt = new int[i];
/* 161 */     for (int m = 0; m < i; m++) arrayOfInt[m] = m;
/* 162 */     StdRandom.shuffle(arrayOfInt);
/*     */     int n;
/*     */     int i1;
/* 163 */     for (m = 0; m < j; m++) {
/* 164 */       n = StdRandom.uniform(i / 2);
/* 165 */       i1 = StdRandom.uniform(i / 2);
/* 166 */       localGraph.addEdge(arrayOfInt[n], arrayOfInt[(i / 2 + i1)]);
/*     */     }
/*     */ 
/* 170 */     for (m = 0; m < k; m++) {
/* 171 */       n = (int)(Math.random() * i);
/* 172 */       i1 = (int)(Math.random() * i);
/* 173 */       localGraph.addEdge(n, i1);
/*     */     }
/*     */ 
/* 176 */     StdOut.println(localGraph);
/*     */ 
/* 179 */     Bipartite localBipartite = new Bipartite(localGraph);
/* 180 */     if (localBipartite.isBipartite()) {
/* 181 */       StdOut.println("Graph is bipartite");
/* 182 */       for (n = 0; n < localGraph.V(); n++)
/* 183 */         StdOut.println(n + ": " + localBipartite.color(n));
/*     */     }
/*     */     else
/*     */     {
/* 187 */       StdOut.print("Graph has an odd-length cycle: ");
/* 188 */       for (Iterator localIterator = localBipartite.oddCycle().iterator(); localIterator.hasNext(); ) { i1 = ((Integer)localIterator.next()).intValue();
/* 189 */         StdOut.print(i1 + " ");
/*     */       }
/* 191 */       StdOut.println();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Bipartite
 * JD-Core Version:    0.6.2
 */