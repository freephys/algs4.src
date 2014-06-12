/*     */ public class FlowNetwork
/*     */ {
/*     */   private final int V;
/*     */   private int E;
/*     */   private Bag<FlowEdge>[] adj;
/*     */ 
/*     */   public FlowNetwork(int paramInt)
/*     */   {
/*  45 */     if (paramInt < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
/*  46 */     this.V = paramInt;
/*  47 */     this.E = 0;
/*  48 */     this.adj = ((Bag[])new Bag[paramInt]);
/*  49 */     for (int i = 0; i < paramInt; i++)
/*  50 */       this.adj[i] = new Bag();
/*     */   }
/*     */ 
/*     */   public FlowNetwork(int paramInt1, int paramInt2)
/*     */   {
/*  62 */     this(paramInt1);
/*  63 */     if (paramInt2 < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  64 */     for (int i = 0; i < paramInt2; i++) {
/*  65 */       int j = StdRandom.uniform(paramInt1);
/*  66 */       int k = StdRandom.uniform(paramInt1);
/*  67 */       double d = StdRandom.uniform(100);
/*  68 */       addEdge(new FlowEdge(j, k, d));
/*     */     }
/*     */   }
/*     */ 
/*     */   public FlowNetwork(In paramIn)
/*     */   {
/*  83 */     this(paramIn.readInt());
/*  84 */     int i = paramIn.readInt();
/*  85 */     if (i < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
/*  86 */     for (int j = 0; j < i; j++) {
/*  87 */       int k = paramIn.readInt();
/*  88 */       int m = paramIn.readInt();
/*  89 */       if ((k < 0) || (k >= this.V)) throw new IndexOutOfBoundsException("vertex " + k + " is not between 0 and " + (this.V - 1));
/*  90 */       if ((m < 0) || (m >= this.V)) throw new IndexOutOfBoundsException("vertex " + m + " is not between 0 and " + (this.V - 1));
/*  91 */       double d = paramIn.readDouble();
/*  92 */       addEdge(new FlowEdge(k, m, d));
/*     */     }
/*     */   }
/*     */ 
/*     */   public int V()
/*     */   {
/* 102 */     return this.V;
/*     */   }
/*     */ 
/*     */   public int E()
/*     */   {
/* 110 */     return this.E;
/*     */   }
/*     */ 
/*     */   public void addEdge(FlowEdge paramFlowEdge)
/*     */   {
/* 119 */     int i = paramFlowEdge.from();
/* 120 */     int j = paramFlowEdge.to();
/* 121 */     if ((i < 0) || (i >= this.V)) throw new IndexOutOfBoundsException("vertex " + i + " is not between 0 and " + (this.V - 1));
/* 122 */     if ((j < 0) || (j >= this.V)) throw new IndexOutOfBoundsException("vertex " + j + " is not between 0 and " + (this.V - 1));
/* 123 */     this.adj[i].add(paramFlowEdge);
/* 124 */     this.adj[j].add(paramFlowEdge);
/* 125 */     this.E += 1;
/*     */   }
/*     */ 
/*     */   public Iterable<FlowEdge> adj(int paramInt)
/*     */   {
/* 136 */     if ((paramInt < 0) || (paramInt >= this.V)) throw new IndexOutOfBoundsException("vertex " + paramInt + " is not between 0 and " + (this.V - 1));
/* 137 */     return this.adj[paramInt];
/*     */   }
/*     */ 
/*     */   public Iterable<FlowEdge> edges()
/*     */   {
/* 142 */     Bag localBag = new Bag();
/* 143 */     for (int i = 0; i < this.V; i++) {
/* 144 */       for (FlowEdge localFlowEdge : adj(i))
/* 145 */         if (localFlowEdge.to() != i)
/* 146 */           localBag.add(localFlowEdge);
/*     */     }
/* 148 */     return localBag;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 159 */     String str = System.getProperty("line.separator");
/* 160 */     StringBuilder localStringBuilder = new StringBuilder();
/* 161 */     localStringBuilder.append(this.V + " " + this.E + str);
/* 162 */     for (int i = 0; i < this.V; i++) {
/* 163 */       localStringBuilder.append(i + ":  ");
/* 164 */       for (FlowEdge localFlowEdge : this.adj[i]) {
/* 165 */         if (localFlowEdge.to() != i) localStringBuilder.append(localFlowEdge + "  ");
/*     */       }
/* 167 */       localStringBuilder.append(str);
/*     */     }
/* 169 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 176 */     In localIn = new In(paramArrayOfString[0]);
/* 177 */     FlowNetwork localFlowNetwork = new FlowNetwork(localIn);
/* 178 */     StdOut.println(localFlowNetwork);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FlowNetwork
 * JD-Core Version:    0.6.2
 */