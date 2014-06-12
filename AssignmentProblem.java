/*     */ public class AssignmentProblem
/*     */ {
/*     */   private static final int UNMATCHED = -1;
/*     */   private int N;
/*     */   private double[][] weight;
/*     */   private double[] px;
/*     */   private double[] py;
/*     */   private int[] xy;
/*     */   private int[] yx;
/*     */ 
/*     */   public AssignmentProblem(double[][] paramArrayOfDouble)
/*     */   {
/*  30 */     this.N = paramArrayOfDouble.length;
/*  31 */     this.weight = new double[this.N][this.N];
/*  32 */     for (int i = 0; i < this.N; i++) {
/*  33 */       for (int j = 0; j < this.N; j++) {
/*  34 */         this.weight[i][j] = paramArrayOfDouble[i][j];
/*     */       }
/*     */     }
/*  37 */     this.px = new double[this.N];
/*  38 */     this.py = new double[this.N];
/*     */ 
/*  41 */     this.xy = new int[this.N];
/*  42 */     this.yx = new int[this.N];
/*  43 */     for (i = 0; i < this.N; i++) this.xy[i] = -1;
/*  44 */     for (i = 0; i < this.N; i++) this.yx[i] = -1;
/*     */ 
/*  47 */     for (i = 0; i < this.N; i++) {
/*  48 */       assert (isDualFeasible());
/*  49 */       assert (isComplementarySlack());
/*  50 */       augment();
/*     */     }
/*  52 */     assert (check());
/*     */   }
/*     */ 
/*     */   private void augment()
/*     */   {
/*  59 */     EdgeWeightedDigraph localEdgeWeightedDigraph = new EdgeWeightedDigraph(2 * this.N + 2);
/*  60 */     int i = 2 * this.N; int j = 2 * this.N + 1;
/*  61 */     for (int k = 0; k < this.N; k++) {
/*  62 */       if (this.xy[k] == -1) localEdgeWeightedDigraph.addEdge(new DirectedEdge(i, k, 0.0D));
/*     */     }
/*  64 */     for (k = 0; k < this.N; k++) {
/*  65 */       if (this.yx[k] == -1) localEdgeWeightedDigraph.addEdge(new DirectedEdge(this.N + k, j, this.py[k]));
/*     */     }
/*  67 */     for (k = 0; k < this.N; k++) {
/*  68 */       for (int m = 0; m < this.N; m++) {
/*  69 */         if (this.xy[k] == m) localEdgeWeightedDigraph.addEdge(new DirectedEdge(this.N + m, k, 0.0D)); else {
/*  70 */           localEdgeWeightedDigraph.addEdge(new DirectedEdge(k, this.N + m, reduced(k, m)));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  75 */     DijkstraSP localDijkstraSP = new DijkstraSP(localEdgeWeightedDigraph, i);
/*     */ 
/*  78 */     for (DirectedEdge localDirectedEdge : localDijkstraSP.pathTo(j)) {
/*  79 */       int i1 = localDirectedEdge.from(); int i2 = localDirectedEdge.to() - this.N;
/*  80 */       if (i1 < this.N) {
/*  81 */         this.xy[i1] = i2;
/*  82 */         this.yx[i2] = i1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  87 */     for (int n = 0; n < this.N; n++) this.px[n] += localDijkstraSP.distTo(n);
/*  88 */     for (n = 0; n < this.N; n++) this.py[n] += localDijkstraSP.distTo(this.N + n);
/*     */   }
/*     */ 
/*     */   private double reduced(int paramInt1, int paramInt2)
/*     */   {
/*  93 */     return this.weight[paramInt1][paramInt2] + this.px[paramInt1] - this.py[paramInt2];
/*     */   }
/*     */ 
/*     */   public double dualRow(int paramInt)
/*     */   {
/*  98 */     return this.px[paramInt];
/*     */   }
/*     */ 
/*     */   public double dualCol(int paramInt)
/*     */   {
/* 103 */     return this.py[paramInt];
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/* 108 */     double d = 0.0D;
/* 109 */     for (int i = 0; i < this.N; i++) {
/* 110 */       if (this.xy[i] != -1)
/* 111 */         d += this.weight[i][this.xy[i]];
/*     */     }
/* 113 */     return d;
/*     */   }
/*     */ 
/*     */   public int sol(int paramInt) {
/* 117 */     return this.xy[paramInt];
/*     */   }
/*     */ 
/*     */   private boolean isDualFeasible()
/*     */   {
/* 123 */     for (int i = 0; i < this.N; i++) {
/* 124 */       for (int j = 0; j < this.N; j++) {
/* 125 */         if (reduced(i, j) < 0.0D) {
/* 126 */           StdOut.println("Dual variables are not feasible");
/* 127 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean isComplementarySlack()
/*     */   {
/* 138 */     for (int i = 0; i < this.N; i++) {
/* 139 */       if ((this.xy[i] != -1) && (reduced(i, this.xy[i]) != 0.0D)) {
/* 140 */         StdOut.println("Primal and dual variables are not complementary slack");
/* 141 */         return false;
/*     */       }
/*     */     }
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean isPerfectMatching()
/*     */   {
/* 151 */     boolean[] arrayOfBoolean = new boolean[this.N];
/* 152 */     for (int i = 0; i < this.N; i++) {
/* 153 */       if (arrayOfBoolean[this.xy[i]] != 0) {
/* 154 */         StdOut.println("Not a perfect matching");
/* 155 */         return false;
/*     */       }
/* 157 */       arrayOfBoolean[this.xy[i]] = true;
/*     */     }
/*     */ 
/* 161 */     for (i = 0; i < this.N; i++) {
/* 162 */       if (this.xy[this.yx[i]] != i) {
/* 163 */         StdOut.println("xy[] and yx[] are not inverses");
/* 164 */         return false;
/*     */       }
/*     */     }
/* 167 */     for (i = 0; i < this.N; i++) {
/* 168 */       if (this.yx[this.xy[i]] != i) {
/* 169 */         StdOut.println("xy[] and yx[] are not inverses");
/* 170 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 180 */     return (isPerfectMatching()) && (isDualFeasible()) && (isComplementarySlack());
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) {
/* 184 */     In localIn = new In(paramArrayOfString[0]);
/* 185 */     int i = localIn.readInt();
/* 186 */     double[][] arrayOfDouble = new double[i][i];
/* 187 */     for (int j = 0; j < i; j++) {
/* 188 */       for (k = 0; k < i; k++) {
/* 189 */         arrayOfDouble[j][k] = localIn.readDouble();
/*     */       }
/*     */     }
/*     */ 
/* 193 */     AssignmentProblem localAssignmentProblem = new AssignmentProblem(arrayOfDouble);
/* 194 */     StdOut.println("weight = " + localAssignmentProblem.weight());
/* 195 */     for (int k = 0; k < i; k++) {
/* 196 */       StdOut.println(k + "-" + localAssignmentProblem.sol(k) + "' " + arrayOfDouble[k][localAssignmentProblem.sol(k)]);
/*     */     }
/* 198 */     for (k = 0; k < i; k++)
/* 199 */       StdOut.println("px[" + k + "] = " + localAssignmentProblem.dualRow(k));
/* 200 */     for (k = 0; k < i; k++)
/* 201 */       StdOut.println("py[" + k + "] = " + localAssignmentProblem.dualCol(k));
/* 202 */     for (k = 0; k < i; k++)
/* 203 */       for (int m = 0; m < i; m++)
/* 204 */         StdOut.println("reduced[" + k + "-" + m + "] = " + localAssignmentProblem.reduced(k, m));
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     AssignmentProblem
 * JD-Core Version:    0.6.2
 */