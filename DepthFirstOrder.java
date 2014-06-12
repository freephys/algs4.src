/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DepthFirstOrder
/*     */ {
/*     */   private boolean[] marked;
/*     */   private int[] pre;
/*     */   private int[] post;
/*     */   private Queue<Integer> preorder;
/*     */   private Queue<Integer> postorder;
/*     */   private int preCounter;
/*     */   private int postCounter;
/*     */ 
/*     */   public DepthFirstOrder(Digraph paramDigraph)
/*     */   {
/*  67 */     this.pre = new int[paramDigraph.V()];
/*  68 */     this.post = new int[paramDigraph.V()];
/*  69 */     this.postorder = new Queue();
/*  70 */     this.preorder = new Queue();
/*  71 */     this.marked = new boolean[paramDigraph.V()];
/*  72 */     for (int i = 0; i < paramDigraph.V(); i++)
/*  73 */       if (this.marked[i] == 0) dfs(paramDigraph, i);
/*     */   }
/*     */ 
/*     */   public DepthFirstOrder(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*     */   {
/*  81 */     this.pre = new int[paramEdgeWeightedDigraph.V()];
/*  82 */     this.post = new int[paramEdgeWeightedDigraph.V()];
/*  83 */     this.postorder = new Queue();
/*  84 */     this.preorder = new Queue();
/*  85 */     this.marked = new boolean[paramEdgeWeightedDigraph.V()];
/*  86 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/*  87 */       if (this.marked[i] == 0) dfs(paramEdgeWeightedDigraph, i);
/*     */   }
/*     */ 
/*     */   private void dfs(Digraph paramDigraph, int paramInt)
/*     */   {
/*  92 */     this.marked[paramInt] = true;
/*  93 */     this.pre[paramInt] = (this.preCounter++);
/*  94 */     this.preorder.enqueue(Integer.valueOf(paramInt));
/*  95 */     for (Iterator localIterator = paramDigraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  96 */       if (this.marked[i] == 0) {
/*  97 */         dfs(paramDigraph, i);
/*     */       }
/*     */     }
/* 100 */     this.postorder.enqueue(Integer.valueOf(paramInt));
/* 101 */     this.post[paramInt] = (this.postCounter++);
/*     */   }
/*     */ 
/*     */   private void dfs(EdgeWeightedDigraph paramEdgeWeightedDigraph, int paramInt)
/*     */   {
/* 106 */     this.marked[paramInt] = true;
/* 107 */     this.pre[paramInt] = (this.preCounter++);
/* 108 */     this.preorder.enqueue(Integer.valueOf(paramInt));
/* 109 */     for (DirectedEdge localDirectedEdge : paramEdgeWeightedDigraph.adj(paramInt)) {
/* 110 */       int i = localDirectedEdge.to();
/* 111 */       if (this.marked[i] == 0) {
/* 112 */         dfs(paramEdgeWeightedDigraph, i);
/*     */       }
/*     */     }
/* 115 */     this.postorder.enqueue(Integer.valueOf(paramInt));
/* 116 */     this.post[paramInt] = (this.postCounter++);
/*     */   }
/*     */ 
/*     */   public int pre(int paramInt)
/*     */   {
/* 125 */     return this.pre[paramInt];
/*     */   }
/*     */ 
/*     */   public int post(int paramInt)
/*     */   {
/* 134 */     return this.post[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> post()
/*     */   {
/* 142 */     return this.postorder;
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> pre()
/*     */   {
/* 150 */     return this.preorder;
/*     */   }
/*     */ 
/*     */   public Iterable<Integer> reversePost()
/*     */   {
/* 158 */     Stack localStack = new Stack();
/* 159 */     for (Iterator localIterator = this.postorder.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 160 */       localStack.push(Integer.valueOf(i)); }
/* 161 */     return localStack;
/*     */   }
/*     */ 
/*     */   private boolean check(Digraph paramDigraph)
/*     */   {
/* 169 */     int i = 0;
/* 170 */     for (Iterator localIterator = post().iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 171 */       if (post(j) != i) {
/* 172 */         StdOut.println("post(v) and post() inconsistent");
/* 173 */         return false;
/*     */       }
/* 175 */       i++;
/*     */     }
/* 180 */     int j;
/* 179 */     i = 0;
/* 180 */     for (localIterator = pre().iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 181 */       if (pre(j) != i) {
/* 182 */         StdOut.println("pre(v) and pre() inconsistent");
/* 183 */         return false;
/*     */       }
/* 185 */       i++;
/*     */     }
/*     */ 
/* 189 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 196 */     In localIn = new In(paramArrayOfString[0]);
/* 197 */     Digraph localDigraph = new Digraph(localIn);
/*     */ 
/* 199 */     DepthFirstOrder localDepthFirstOrder = new DepthFirstOrder(localDigraph);
/* 200 */     StdOut.println("   v  pre post");
/* 201 */     StdOut.println("--------------");
/* 202 */     for (int i = 0; i < localDigraph.V(); i++) {
/* 203 */       StdOut.printf("%4d %4d %4d\n", new Object[] { Integer.valueOf(i), Integer.valueOf(localDepthFirstOrder.pre(i)), Integer.valueOf(localDepthFirstOrder.post(i)) });
/*     */     }
/*     */ 
/* 206 */     StdOut.print("Preorder:  ");
/* 207 */     for (Iterator localIterator = localDepthFirstOrder.pre().iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 208 */       StdOut.print(j + " ");
/*     */     }
/* 213 */     int j;
/* 210 */     StdOut.println();
/*     */ 
/* 212 */     StdOut.print("Postorder: ");
/* 213 */     for (localIterator = localDepthFirstOrder.post().iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 214 */       StdOut.print(j + " ");
/*     */     }
/* 216 */     StdOut.println();
/*     */ 
/* 218 */     StdOut.print("Reverse postorder: ");
/* 219 */     for (localIterator = localDepthFirstOrder.reversePost().iterator(); localIterator.hasNext(); ) { j = ((Integer)localIterator.next()).intValue();
/* 220 */       StdOut.print(j + " ");
/*     */     }
/* 222 */     StdOut.println();
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DepthFirstOrder
 * JD-Core Version:    0.6.2
 */