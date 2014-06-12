/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class EdgeWeightedDirectedCycle
/*     */ {
/*     */   private boolean[] marked;
/*     */   private DirectedEdge[] edgeTo;
/*     */   private boolean[] onStack;
/*     */   private Stack<DirectedEdge> cycle;
/*     */ 
/*     */   public EdgeWeightedDirectedCycle(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*     */   {
/*  48 */     this.marked = new boolean[paramEdgeWeightedDigraph.V()];
/*  49 */     this.onStack = new boolean[paramEdgeWeightedDigraph.V()];
/*  50 */     this.edgeTo = new DirectedEdge[paramEdgeWeightedDigraph.V()];
/*  51 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++) {
/*  52 */       if (this.marked[i] == 0) dfs(paramEdgeWeightedDigraph, i);
/*     */     }
/*     */ 
/*  55 */     assert (check(paramEdgeWeightedDigraph));
/*     */   }
/*     */ 
/*     */   private void dfs(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/*  60 */     this.onStack[paramInt] = true;
/*  61 */     this.marked[paramInt] = true;
/*  62 */     for (DirectedEdge localDirectedEdge : paramEdgeWeightedDigraph.adj(paramInt)) {
/*  63 */       int i = localDirectedEdge.to();
/*     */ 
/*  66 */       if (this.cycle != null) return;
/*     */ 
/*  69 */       if (this.marked[i] == 0) {
/*  70 */         this.edgeTo[i] = localDirectedEdge;
/*  71 */         dfs(paramEdgeWeightedDigraph, i);
/*     */       }
/*  75 */       else if (this.onStack[i] != 0) {
/*  76 */         this.cycle = new Stack();
/*  77 */         while (localDirectedEdge.from() != i) {
/*  78 */           this.cycle.push(localDirectedEdge);
/*  79 */           localDirectedEdge = this.edgeTo[localDirectedEdge.from()];
/*     */         }
/*  81 */         this.cycle.push(localDirectedEdge);
/*     */       }
/*     */     }
/*     */ 
/*  85 */     this.onStack[paramInt] = false;
/*     */   }
/*     */ 
/*     */   public boolean hasCycle()
/*     */   {
/*  94 */     return this.cycle != null;
/*     */   }
/*     */ 
/*     */   public Iterable<DirectedEdge> cycle()
/*     */   {
/* 104 */     return this.cycle;
/*     */   }
/*     */ 
/*     */   private boolean check(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*     */   {
/* 112 */     if (hasCycle())
/*     */     {
/* 114 */       Object localObject1 = null; Object localObject2 = null;
/* 115 */       for (DirectedEdge localDirectedEdge : cycle()) {
/* 116 */         if (localObject1 == null) localObject1 = localDirectedEdge;
/* 117 */         if ((localObject2 != null) && 
/* 118 */           (localObject2.to() != localDirectedEdge.from())) {
/* 119 */           System.err.printf("cycle edges %s and %s not incident\n", new Object[] { localObject2, localDirectedEdge });
/* 120 */           return false;
/*     */         }
/*     */ 
/* 123 */         localObject2 = localDirectedEdge;
/*     */       }
/*     */ 
/* 126 */       if (localObject2.to() != localObject1.from()) {
/* 127 */         System.err.printf("cycle edges %s and %s not incident\n", new Object[] { localObject2, localObject1 });
/* 128 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 142 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 143 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 144 */     int k = Integer.parseInt(paramArrayOfString[2]);
/* 145 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(i);
/* 146 */     int[] arrayOfInt = new int[i];
/* 147 */     for (int m = 0; m < i; m++) arrayOfInt[m] = m;
/* 148 */     StdRandom.shuffle(arrayOfInt);
/*     */     int n;
/*     */     int i1;
/*     */     double d;
/* 149 */     for (m = 0; m < j; m++)
/*     */     {
/*     */       do {
/* 152 */         n = StdRandom.uniform(i);
/* 153 */         i1 = StdRandom.uniform(i);
/* 154 */       }while (n >= i1);
/* 155 */       d = Math.random();
/* 156 */       localEdgeWeightedDigraph.addEdge(new DirectedEdge(n, i1, d));
/*     */     }
/*     */ 
/* 160 */     for (m = 0; m < k; m++) {
/* 161 */       n = (int)(Math.random() * i);
/* 162 */       i1 = (int)(Math.random() * i);
/* 163 */       d = Math.random();
/* 164 */       localEdgeWeightedDigraph.addEdge(new DirectedEdge(n, i1, d));
/*     */     }
/*     */ 
/* 167 */     StdOut.println(localEdgeWeightedDigraph);
/*     */ 
/* 170 */     EdgeWeightedDirectedCycle localEdgeWeightedDirectedCycle = new EdgeWeightedDirectedCycle(localEdgeWeightedDigraph);
/* 171 */     if (localEdgeWeightedDirectedCycle.hasCycle()) {
/* 172 */       StdOut.print("Cycle: ");
/* 173 */       for (DirectedEdge localDirectedEdge : localEdgeWeightedDirectedCycle.cycle()) {
/* 174 */         StdOut.print(localDirectedEdge + " ");
/*     */       }
/* 176 */       StdOut.println();
/*     */     }
/*     */     else
/*     */     {
/* 181 */       StdOut.println("No directed cycle");
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     EdgeWeightedDirectedCycle
 * JD-Core Version:    0.6.2
 */