/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class FordFulkerson
/*     */ {
/*     */   private boolean[] marked;
/*     */   private FlowEdge[] edgeTo;
/*     */   private double value;
/*     */ 
/*     */   public FordFulkerson(FlowNetwork paramFlowNetwork, int paramInt1, int paramInt2)
/*     */   {
/*  54 */     if ((paramInt1 < 0) || (paramInt1 >= paramFlowNetwork.V())) {
/*  55 */       throw new IndexOutOfBoundsException("Source s is invalid: " + paramInt1);
/*     */     }
/*  57 */     if ((paramInt2 < 0) || (paramInt2 >= paramFlowNetwork.V())) {
/*  58 */       throw new IndexOutOfBoundsException("Sink t is invalid: " + paramInt2);
/*     */     }
/*  60 */     if (paramInt1 == paramInt2) {
/*  61 */       throw new IllegalArgumentException("Source equals sink");
/*     */     }
/*  63 */     this.value = excess(paramFlowNetwork, paramInt2);
/*  64 */     if (!isFeasible(paramFlowNetwork, paramInt1, paramInt2)) {
/*  65 */       throw new IllegalArgumentException("Initial flow is infeasible");
/*     */     }
/*     */ 
/*  69 */     while (hasAugmentingPath(paramFlowNetwork, paramInt1, paramInt2))
/*     */     {
/*  72 */       double d = (1.0D / 0.0D);
/*  73 */       for (int i = paramInt2; i != paramInt1; i = this.edgeTo[i].other(i)) {
/*  74 */         d = Math.min(d, this.edgeTo[i].residualCapacityTo(i));
/*     */       }
/*     */ 
/*  78 */       for (i = paramInt2; i != paramInt1; i = this.edgeTo[i].other(i)) {
/*  79 */         this.edgeTo[i].addResidualFlowTo(i, d);
/*     */       }
/*     */ 
/*  82 */       this.value += d;
/*     */     }
/*     */ 
/*  86 */     assert (check(paramFlowNetwork, paramInt1, paramInt2));
/*     */   }
/*     */ 
/*     */   public double value()
/*     */   {
/*  94 */     return this.value;
/*     */   }
/*     */ 
/*     */   public boolean inCut(int paramInt)
/*     */   {
/* 105 */     int i = this.marked.length;
/* 106 */     if ((paramInt < 0) || (paramInt >= i))
/* 107 */       throw new IndexOutOfBoundsException("vertex " + paramInt + " is not between 0 and " + (i - 1));
/* 108 */     return this.marked[paramInt];
/*     */   }
/*     */ 
/*     */   private boolean hasAugmentingPath(FlowNetwork paramFlowNetwork, int paramInt1, int paramInt2)
/*     */   {
/* 115 */     this.edgeTo = new FlowEdge[paramFlowNetwork.V()];
/* 116 */     this.marked = new boolean[paramFlowNetwork.V()];
/*     */ 
/* 119 */     Queue localQueue = new Queue();
/* 120 */     localQueue.enqueue(Integer.valueOf(paramInt1));
/* 121 */     this.marked[paramInt1] = true;
/*     */     int i;
/* 122 */     while (!localQueue.isEmpty()) {
/* 123 */       i = ((Integer)localQueue.dequeue()).intValue();
/*     */ 
/* 125 */       for (FlowEdge localFlowEdge : paramFlowNetwork.adj(i)) {
/* 126 */         int j = localFlowEdge.other(i);
/*     */ 
/* 129 */         if ((localFlowEdge.residualCapacityTo(j) > 0.0D) && 
/* 130 */           (this.marked[j] == 0)) {
/* 131 */           this.edgeTo[j] = localFlowEdge;
/* 132 */           this.marked[j] = true;
/* 133 */           localQueue.enqueue(Integer.valueOf(j));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 140 */     return this.marked[paramInt2];
/*     */   }
/*     */ 
/*     */   private double excess(FlowNetwork paramFlowNetwork, int paramInt)
/*     */   {
/* 147 */     double d = 0.0D;
/* 148 */     for (FlowEdge localFlowEdge : paramFlowNetwork.adj(paramInt)) {
/* 149 */       if (paramInt == localFlowEdge.from()) d -= localFlowEdge.flow(); else
/* 150 */         d += localFlowEdge.flow();
/*     */     }
/* 152 */     return d;
/*     */   }
/*     */ 
/*     */   private boolean isFeasible(FlowNetwork paramFlowNetwork, int paramInt1, int paramInt2)
/*     */   {
/* 157 */     double d = 9.999999999999999E-12D;
/*     */ 
/* 160 */     for (int i = 0; i < paramFlowNetwork.V(); i++) {
/* 161 */       for (FlowEdge localFlowEdge : paramFlowNetwork.adj(i)) {
/* 162 */         if ((localFlowEdge.flow() < -d) || (localFlowEdge.flow() > localFlowEdge.capacity() + d)) {
/* 163 */           System.err.println("Edge does not satisfy capacity constraints: " + localFlowEdge);
/* 164 */           return false;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 170 */     if (Math.abs(this.value + excess(paramFlowNetwork, paramInt1)) > d) {
/* 171 */       System.err.println("Excess at source = " + excess(paramFlowNetwork, paramInt1));
/* 172 */       System.err.println("Max flow         = " + this.value);
/* 173 */       return false;
/*     */     }
/* 175 */     if (Math.abs(this.value - excess(paramFlowNetwork, paramInt2)) > d) {
/* 176 */       System.err.println("Excess at sink   = " + excess(paramFlowNetwork, paramInt2));
/* 177 */       System.err.println("Max flow         = " + this.value);
/* 178 */       return false;
/*     */     }
/* 180 */     for (i = 0; i < paramFlowNetwork.V(); i++) {
/* 181 */       if ((i != paramInt1) && (i != paramInt2) && 
/* 182 */         (Math.abs(excess(paramFlowNetwork, i)) > d)) {
/* 183 */         System.err.println("Net flow out of " + i + " doesn't equal zero");
/* 184 */         return false;
/*     */       }
/*     */     }
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean check(FlowNetwork paramFlowNetwork, int paramInt1, int paramInt2)
/*     */   {
/* 196 */     if (!isFeasible(paramFlowNetwork, paramInt1, paramInt2)) {
/* 197 */       System.err.println("Flow is infeasible");
/* 198 */       return false;
/*     */     }
/*     */ 
/* 202 */     if (!inCut(paramInt1)) {
/* 203 */       System.err.println("source " + paramInt1 + " is not on source side of min cut");
/* 204 */       return false;
/*     */     }
/* 206 */     if (inCut(paramInt2)) {
/* 207 */       System.err.println("sink " + paramInt2 + " is on source side of min cut");
/* 208 */       return false;
/*     */     }
/*     */ 
/* 212 */     double d1 = 0.0D;
/* 213 */     for (int i = 0; i < paramFlowNetwork.V(); i++) {
/* 214 */       for (FlowEdge localFlowEdge : paramFlowNetwork.adj(i)) {
/* 215 */         if ((i == localFlowEdge.from()) && (inCut(localFlowEdge.from())) && (!inCut(localFlowEdge.to()))) {
/* 216 */           d1 += localFlowEdge.capacity();
/*     */         }
/*     */       }
/*     */     }
/* 220 */     double d2 = 9.999999999999999E-12D;
/* 221 */     if (Math.abs(d1 - this.value) > d2) {
/* 222 */       System.err.println("Max flow value = " + this.value + ", min cut value = " + d1);
/* 223 */       return false;
/*     */     }
/*     */ 
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 236 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 237 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 238 */     int k = 0; int m = i - 1;
/* 239 */     FlowNetwork localFlowNetwork = new FlowNetwork(i, j);
/* 240 */     StdOut.println(localFlowNetwork);
/*     */ 
/* 243 */     FordFulkerson localFordFulkerson = new FordFulkerson(localFlowNetwork, k, m);
/* 244 */     StdOut.println("Max flow from " + k + " to " + m);
/* 245 */     for (int n = 0; n < localFlowNetwork.V(); n++) {
/* 246 */       for (FlowEdge localFlowEdge : localFlowNetwork.adj(n)) {
/* 247 */         if ((n == localFlowEdge.from()) && (localFlowEdge.flow() > 0.0D)) {
/* 248 */           StdOut.println("   " + localFlowEdge);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 253 */     StdOut.print("Min cut: ");
/* 254 */     for (n = 0; n < localFlowNetwork.V(); n++) {
/* 255 */       if (localFordFulkerson.inCut(n)) StdOut.print(n + " ");
/*     */     }
/* 257 */     StdOut.println();
/*     */ 
/* 259 */     StdOut.println("Max flow value = " + localFordFulkerson.value());
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FordFulkerson
 * JD-Core Version:    0.6.2
 */