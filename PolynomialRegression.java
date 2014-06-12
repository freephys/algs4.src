/*     */ import Jama.Matrix;
/*     */ import Jama.QRDecomposition;
/*     */ 
/*     */ public class PolynomialRegression
/*     */ {
/*     */   private final int N;
/*     */   private final int degree;
/*     */   private final Matrix beta;
/*     */   private double SSE;
/*     */   private double SST;
/*     */ 
/*     */   public PolynomialRegression(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt)
/*     */   {
/*  48 */     this.degree = paramInt;
/*  49 */     this.N = paramArrayOfDouble1.length;
/*     */ 
/*  52 */     double[][] arrayOfDouble = new double[this.N][paramInt + 1];
/*  53 */     for (int i = 0; i < this.N; i++) {
/*  54 */       for (int j = 0; j <= paramInt; j++) {
/*  55 */         arrayOfDouble[i][j] = Math.pow(paramArrayOfDouble1[i], j);
/*     */       }
/*     */     }
/*  58 */     Matrix localMatrix1 = new Matrix(arrayOfDouble);
/*     */ 
/*  61 */     Matrix localMatrix2 = new Matrix(paramArrayOfDouble2, this.N);
/*     */ 
/*  64 */     QRDecomposition localQRDecomposition = new QRDecomposition(localMatrix1);
/*  65 */     this.beta = localQRDecomposition.solve(localMatrix2);
/*     */ 
/*  69 */     double d1 = 0.0D;
/*  70 */     for (int k = 0; k < this.N; k++)
/*  71 */       d1 += paramArrayOfDouble2[k];
/*  72 */     double d2 = d1 / this.N;
/*     */ 
/*  75 */     for (int m = 0; m < this.N; m++) {
/*  76 */       double d3 = paramArrayOfDouble2[m] - d2;
/*  77 */       this.SST += d3 * d3;
/*     */     }
/*     */ 
/*  81 */     Matrix localMatrix3 = localMatrix1.times(this.beta).minus(localMatrix2);
/*  82 */     this.SSE = (localMatrix3.norm2() * localMatrix3.norm2());
/*     */   }
/*     */ 
/*     */   public double beta(int paramInt)
/*     */   {
/*  90 */     return this.beta.get(paramInt, 0);
/*     */   }
/*     */ 
/*     */   public int degree()
/*     */   {
/*  98 */     return this.degree;
/*     */   }
/*     */ 
/*     */   public double R2()
/*     */   {
/* 106 */     if (this.SST == 0.0D) return 1.0D;
/* 107 */     return 1.0D - this.SSE / this.SST;
/*     */   }
/*     */ 
/*     */   public double predict(double paramDouble)
/*     */   {
/* 119 */     double d = 0.0D;
/* 120 */     for (int i = this.degree; i >= 0; i--)
/* 121 */       d = beta(i) + paramDouble * d;
/* 122 */     return d;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     String str = "";
/* 132 */     int i = this.degree;
/*     */ 
/* 135 */     while ((i >= 0) && (Math.abs(beta(i)) < 1.E-05D)) {
/* 136 */       i--;
/*     */     }
/*     */ 
/* 139 */     for (i = i; i >= 0; i--) {
/* 140 */       if (i == 0) str = str + String.format("%.2f ", new Object[] { Double.valueOf(beta(i)) });
/* 141 */       else if (i == 1) str = str + String.format("%.2f N + ", new Object[] { Double.valueOf(beta(i)) }); else
/* 142 */         str = str + String.format("%.2f N^%d + ", new Object[] { Double.valueOf(beta(i)), Integer.valueOf(i) });
/*     */     }
/* 144 */     return str + "  (R^2 = " + String.format("%.3f", new Object[] { Double.valueOf(R2()) }) + ")";
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) {
/* 148 */     double[] arrayOfDouble1 = { 10.0D, 20.0D, 40.0D, 80.0D, 160.0D, 200.0D };
/* 149 */     double[] arrayOfDouble2 = { 100.0D, 350.0D, 1500.0D, 6700.0D, 20160.0D, 40000.0D };
/* 150 */     PolynomialRegression localPolynomialRegression = new PolynomialRegression(arrayOfDouble1, arrayOfDouble2, 3);
/* 151 */     StdOut.println(localPolynomialRegression);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     PolynomialRegression
 * JD-Core Version:    0.6.2
 */