/*     */ public class Simplex
/*     */ {
/*     */   private static final double EPSILON = 1.0E-10D;
/*     */   private double[][] a;
/*     */   private int M;
/*     */   private int N;
/*     */   private int[] basis;
/*     */ 
/*     */   public Simplex(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/*  26 */     this.M = paramArrayOfDouble1.length;
/*  27 */     this.N = paramArrayOfDouble2.length;
/*  28 */     this.a = new double[this.M + 1][this.N + this.M + 1];
/*  29 */     for (int i = 0; i < this.M; i++)
/*  30 */       for (int j = 0; j < this.N; j++)
/*  31 */         this.a[i][j] = paramArrayOfDouble[i][j];
/*  32 */     for (i = 0; i < this.M; i++) this.a[i][(this.N + i)] = 1.0D;
/*  33 */     for (i = 0; i < this.N; i++) this.a[this.M][i] = paramArrayOfDouble2[i];
/*  34 */     for (i = 0; i < this.M; i++) this.a[i][(this.M + this.N)] = paramArrayOfDouble1[i];
/*     */ 
/*  36 */     this.basis = new int[this.M];
/*  37 */     for (i = 0; i < this.M; i++) this.basis[i] = (this.N + i);
/*     */ 
/*  39 */     solve();
/*     */ 
/*  42 */     assert (check(paramArrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2));
/*     */   }
/*     */ 
/*     */   private void solve()
/*     */   {
/*     */     while (true)
/*     */     {
/*  50 */       int i = bland();
/*  51 */       if (i == -1) {
/*     */         break;
/*     */       }
/*  54 */       int j = minRatioRule(i);
/*  55 */       if (j == -1) throw new ArithmeticException("Linear program is unbounded");
/*     */ 
/*  58 */       pivot(j, i);
/*     */ 
/*  61 */       this.basis[j] = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   private int bland()
/*     */   {
/*  67 */     for (int i = 0; i < this.M + this.N; i++)
/*  68 */       if (this.a[this.M][i] > 0.0D) return i;
/*  69 */     return -1;
/*     */   }
/*     */ 
/*     */   private int dantzig()
/*     */   {
/*  74 */     int i = 0;
/*  75 */     for (int j = 1; j < this.M + this.N; j++) {
/*  76 */       if (this.a[this.M][j] > this.a[this.M][i]) i = j;
/*     */     }
/*  78 */     if (this.a[this.M][i] <= 0.0D) return -1;
/*  79 */     return i;
/*     */   }
/*     */ 
/*     */   private int minRatioRule(int paramInt)
/*     */   {
/*  84 */     int i = -1;
/*  85 */     for (int j = 0; j < this.M; j++) {
/*  86 */       if (this.a[j][paramInt] > 0.0D)
/*  87 */         if (i == -1) i = j;
/*  88 */         else if (this.a[j][(this.M + this.N)] / this.a[j][paramInt] < this.a[i][(this.M + this.N)] / this.a[i][paramInt]) i = j;
/*     */     }
/*  90 */     return i;
/*     */   }
/*     */ 
/*     */   private void pivot(int paramInt1, int paramInt2)
/*     */   {
/*  97 */     for (int i = 0; i <= this.M; i++) {
/*  98 */       for (int j = 0; j <= this.M + this.N; j++) {
/*  99 */         if ((i != paramInt1) && (j != paramInt2)) this.a[i][j] -= this.a[paramInt1][j] * this.a[i][paramInt2] / this.a[paramInt1][paramInt2];
/*     */       }
/*     */     }
/* 102 */     for (i = 0; i <= this.M; i++) {
/* 103 */       if (i != paramInt1) this.a[i][paramInt2] = 0.0D;
/*     */     }
/*     */ 
/* 106 */     for (i = 0; i <= this.M + this.N; i++)
/* 107 */       if (i != paramInt2) this.a[paramInt1][i] /= this.a[paramInt1][paramInt2];
/* 108 */     this.a[paramInt1][paramInt2] = 1.0D;
/*     */   }
/*     */ 
/*     */   public double value()
/*     */   {
/* 113 */     return -this.a[this.M][(this.M + this.N)];
/*     */   }
/*     */ 
/*     */   public double[] primal()
/*     */   {
/* 118 */     double[] arrayOfDouble = new double[this.N];
/* 119 */     for (int i = 0; i < this.M; i++)
/* 120 */       if (this.basis[i] < this.N) arrayOfDouble[this.basis[i]] = this.a[i][(this.M + this.N)];
/* 121 */     return arrayOfDouble;
/*     */   }
/*     */ 
/*     */   public double[] dual()
/*     */   {
/* 126 */     double[] arrayOfDouble = new double[this.M];
/* 127 */     for (int i = 0; i < this.M; i++)
/* 128 */       arrayOfDouble[i] = (-this.a[this.M][(this.N + i)]);
/* 129 */     return arrayOfDouble;
/*     */   }
/*     */ 
/*     */   private boolean isPrimalFeasible(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
/*     */   {
/* 135 */     double[] arrayOfDouble = primal();
/*     */ 
/* 138 */     for (int i = 0; i < arrayOfDouble.length; i++) {
/* 139 */       if (arrayOfDouble[i] < 0.0D) {
/* 140 */         StdOut.println("x[" + i + "] = " + arrayOfDouble[i] + " is negative");
/* 141 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 146 */     for (i = 0; i < this.M; i++) {
/* 147 */       double d = 0.0D;
/* 148 */       for (int j = 0; j < this.N; j++) {
/* 149 */         d += paramArrayOfDouble[i][j] * arrayOfDouble[j];
/*     */       }
/* 151 */       if (d > paramArrayOfDouble1[i] + 1.0E-10D) {
/* 152 */         StdOut.println("not primal feasible");
/* 153 */         StdOut.println("b[" + i + "] = " + paramArrayOfDouble1[i] + ", sum = " + d);
/* 154 */         return false;
/*     */       }
/*     */     }
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean isDualFeasible(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
/*     */   {
/* 162 */     double[] arrayOfDouble = dual();
/*     */ 
/* 165 */     for (int i = 0; i < arrayOfDouble.length; i++) {
/* 166 */       if (arrayOfDouble[i] < 0.0D) {
/* 167 */         StdOut.println("y[" + i + "] = " + arrayOfDouble[i] + " is negative");
/* 168 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 173 */     for (i = 0; i < this.N; i++) {
/* 174 */       double d = 0.0D;
/* 175 */       for (int j = 0; j < this.M; j++) {
/* 176 */         d += paramArrayOfDouble[j][i] * arrayOfDouble[j];
/*     */       }
/* 178 */       if (d < paramArrayOfDouble1[i] - 1.0E-10D) {
/* 179 */         StdOut.println("not dual feasible");
/* 180 */         StdOut.println("c[" + i + "] = " + paramArrayOfDouble1[i] + ", sum = " + d);
/* 181 */         return false;
/*     */       }
/*     */     }
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean isOptimal(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/* 189 */     double[] arrayOfDouble1 = primal();
/* 190 */     double[] arrayOfDouble2 = dual();
/* 191 */     double d1 = value();
/*     */ 
/* 194 */     double d2 = 0.0D;
/* 195 */     for (int i = 0; i < arrayOfDouble1.length; i++)
/* 196 */       d2 += paramArrayOfDouble2[i] * arrayOfDouble1[i];
/* 197 */     double d3 = 0.0D;
/* 198 */     for (int j = 0; j < arrayOfDouble2.length; j++)
/* 199 */       d3 += arrayOfDouble2[j] * paramArrayOfDouble1[j];
/* 200 */     if ((Math.abs(d1 - d2) > 1.0E-10D) || (Math.abs(d1 - d3) > 1.0E-10D)) {
/* 201 */       StdOut.println("value = " + d1 + ", cx = " + d2 + ", yb = " + d3);
/* 202 */       return false;
/*     */     }
/*     */ 
/* 205 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean check(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {
/* 209 */     return (isPrimalFeasible(paramArrayOfDouble, paramArrayOfDouble1)) && (isDualFeasible(paramArrayOfDouble, paramArrayOfDouble2)) && (isOptimal(paramArrayOfDouble1, paramArrayOfDouble2));
/*     */   }
/*     */ 
/*     */   public void show()
/*     */   {
/* 214 */     StdOut.println("M = " + this.M);
/* 215 */     StdOut.println("N = " + this.N);
/* 216 */     for (int i = 0; i <= this.M; i++) {
/* 217 */       for (int j = 0; j <= this.M + this.N; j++) {
/* 218 */         StdOut.printf("%7.2f ", new Object[] { Double.valueOf(this.a[i][j]) });
/*     */       }
/* 220 */       StdOut.println();
/*     */     }
/* 222 */     StdOut.println("value = " + value());
/* 223 */     for (i = 0; i < this.M; i++)
/* 224 */       if (this.basis[i] < this.N) StdOut.println("x_" + this.basis[i] + " = " + this.a[i][(this.M + this.N)]);
/* 225 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   public static void test(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/* 230 */     Simplex localSimplex = new Simplex(paramArrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
/* 231 */     StdOut.println("value = " + localSimplex.value());
/* 232 */     double[] arrayOfDouble1 = localSimplex.primal();
/* 233 */     for (int i = 0; i < arrayOfDouble1.length; i++)
/* 234 */       StdOut.println("x[" + i + "] = " + arrayOfDouble1[i]);
/* 235 */     double[] arrayOfDouble2 = localSimplex.dual();
/* 236 */     for (int j = 0; j < arrayOfDouble2.length; j++)
/* 237 */       StdOut.println("y[" + j + "] = " + arrayOfDouble2[j]);
/*     */   }
/*     */ 
/*     */   public static void test1() {
/* 241 */     double[][] arrayOfDouble = { { -1.0D, 1.0D, 0.0D }, { 1.0D, 4.0D, 0.0D }, { 2.0D, 1.0D, 0.0D }, { 3.0D, -4.0D, 0.0D }, { 0.0D, 0.0D, 1.0D } };
/*     */ 
/* 248 */     double[] arrayOfDouble1 = { 1.0D, 1.0D, 1.0D };
/* 249 */     double[] arrayOfDouble2 = { 5.0D, 45.0D, 27.0D, 24.0D, 4.0D };
/* 250 */     test(arrayOfDouble, arrayOfDouble2, arrayOfDouble1);
/*     */   }
/*     */ 
/*     */   public static void test2()
/*     */   {
/* 256 */     double[] arrayOfDouble1 = { 13.0D, 23.0D };
/* 257 */     double[] arrayOfDouble2 = { 480.0D, 160.0D, 1190.0D };
/* 258 */     double[][] arrayOfDouble = { { 5.0D, 15.0D }, { 4.0D, 4.0D }, { 35.0D, 20.0D } };
/*     */ 
/* 263 */     test(arrayOfDouble, arrayOfDouble2, arrayOfDouble1);
/*     */   }
/*     */ 
/*     */   public static void test3()
/*     */   {
/* 268 */     double[] arrayOfDouble1 = { 2.0D, 3.0D, -1.0D, -12.0D };
/* 269 */     double[] arrayOfDouble2 = { 3.0D, 2.0D };
/* 270 */     double[][] arrayOfDouble = { { -2.0D, -9.0D, 1.0D, 9.0D }, { 1.0D, 1.0D, -1.0D, -2.0D } };
/*     */ 
/* 274 */     test(arrayOfDouble, arrayOfDouble2, arrayOfDouble1);
/*     */   }
/*     */ 
/*     */   public static void test4()
/*     */   {
/* 279 */     double[] arrayOfDouble1 = { 10.0D, -57.0D, -9.0D, -24.0D };
/* 280 */     double[] arrayOfDouble2 = { 0.0D, 0.0D, 1.0D };
/* 281 */     double[][] arrayOfDouble = { { 0.5D, -5.5D, -2.5D, 9.0D }, { 0.5D, -1.5D, -0.5D, 1.0D }, { 1.0D, 0.0D, 0.0D, 0.0D } };
/*     */ 
/* 286 */     test(arrayOfDouble, arrayOfDouble2, arrayOfDouble1);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/* 294 */       test1(); } catch (ArithmeticException localArithmeticException1) {
/* 295 */       localArithmeticException1.printStackTrace();
/* 296 */     }StdOut.println("--------------------------------");
/*     */     try {
/* 298 */       test2(); } catch (ArithmeticException localArithmeticException2) {
/* 299 */       localArithmeticException2.printStackTrace();
/* 300 */     }StdOut.println("--------------------------------");
/*     */     try {
/* 302 */       test3(); } catch (ArithmeticException localArithmeticException3) {
/* 303 */       localArithmeticException3.printStackTrace();
/* 304 */     }StdOut.println("--------------------------------");
/*     */     try {
/* 306 */       test4(); } catch (ArithmeticException localArithmeticException4) {
/* 307 */       localArithmeticException4.printStackTrace();
/* 308 */     }StdOut.println("--------------------------------");
/*     */ 
/* 311 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 312 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 313 */     double[] arrayOfDouble1 = new double[j];
/* 314 */     double[] arrayOfDouble2 = new double[i];
/* 315 */     double[][] arrayOfDouble = new double[i][j];
/* 316 */     for (int k = 0; k < j; k++)
/* 317 */       arrayOfDouble1[k] = StdRandom.uniform(1000);
/* 318 */     for (k = 0; k < i; k++)
/* 319 */       arrayOfDouble2[k] = StdRandom.uniform(1000);
/* 320 */     for (k = 0; k < i; k++)
/* 321 */       for (int m = 0; m < j; m++)
/* 322 */         arrayOfDouble[k][m] = StdRandom.uniform(100);
/* 323 */     Simplex localSimplex = new Simplex(arrayOfDouble, arrayOfDouble2, arrayOfDouble1);
/* 324 */     StdOut.println(localSimplex.value());
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Simplex
 * JD-Core Version:    0.6.2
 */