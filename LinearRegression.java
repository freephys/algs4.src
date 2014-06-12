/*     */ public class LinearRegression
/*     */ {
/*     */   private final int N;
/*     */   private final double alpha;
/*     */   private final double beta;
/*     */   private final double R2;
/*     */   private final double svar;
/*     */   private final double svar0;
/*     */   private final double svar1;
/*     */ 
/*     */   public LinearRegression(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*     */   {
/*  40 */     if (paramArrayOfDouble1.length != paramArrayOfDouble2.length) {
/*  41 */       throw new IllegalArgumentException("array lengths are not equal");
/*     */     }
/*  43 */     this.N = paramArrayOfDouble1.length;
/*     */ 
/*  46 */     double d1 = 0.0D; double d2 = 0.0D; double d3 = 0.0D;
/*  47 */     for (int i = 0; i < this.N; i++) d1 += paramArrayOfDouble1[i];
/*  48 */     for (i = 0; i < this.N; i++) d3 += paramArrayOfDouble1[i] * paramArrayOfDouble1[i];
/*  49 */     for (i = 0; i < this.N; i++) d2 += paramArrayOfDouble2[i];
/*  50 */     double d4 = d1 / this.N;
/*  51 */     double d5 = d2 / this.N;
/*     */ 
/*  54 */     double d6 = 0.0D; double d7 = 0.0D; double d8 = 0.0D;
/*  55 */     for (int j = 0; j < this.N; j++) {
/*  56 */       d6 += (paramArrayOfDouble1[j] - d4) * (paramArrayOfDouble1[j] - d4);
/*  57 */       d7 += (paramArrayOfDouble2[j] - d5) * (paramArrayOfDouble2[j] - d5);
/*  58 */       d8 += (paramArrayOfDouble1[j] - d4) * (paramArrayOfDouble2[j] - d5);
/*     */     }
/*  60 */     this.beta = (d8 / d6);
/*  61 */     this.alpha = (d5 - this.beta * d4);
/*     */ 
/*  64 */     double d9 = 0.0D;
/*  65 */     double d10 = 0.0D;
/*  66 */     for (int k = 0; k < this.N; k++) {
/*  67 */       double d11 = this.beta * paramArrayOfDouble1[k] + this.alpha;
/*  68 */       d9 += (d11 - paramArrayOfDouble2[k]) * (d11 - paramArrayOfDouble2[k]);
/*  69 */       d10 += (d11 - d5) * (d11 - d5);
/*     */     }
/*     */ 
/*  72 */     k = this.N - 2;
/*  73 */     this.R2 = (d10 / d7);
/*  74 */     this.svar = (d9 / k);
/*  75 */     this.svar1 = (this.svar / d6);
/*  76 */     this.svar0 = (this.svar / this.N + d4 * d4 * this.svar1);
/*     */   }
/*     */ 
/*     */   public double intercept()
/*     */   {
/*  84 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   public double slope()
/*     */   {
/*  92 */     return this.beta;
/*     */   }
/*     */ 
/*     */   public double R2()
/*     */   {
/* 100 */     return this.R2;
/*     */   }
/*     */ 
/*     */   public double interceptStdErr()
/*     */   {
/* 108 */     return Math.sqrt(this.svar0);
/*     */   }
/*     */ 
/*     */   public double slopeStdErr()
/*     */   {
/* 116 */     return Math.sqrt(this.svar1);
/*     */   }
/*     */ 
/*     */   public double predict(double paramDouble)
/*     */   {
/* 127 */     return this.beta * paramDouble + this.alpha;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 136 */     String str = "";
/* 137 */     str = str + String.format("%.2f N + %.2f", new Object[] { Double.valueOf(slope()), Double.valueOf(intercept()) });
/* 138 */     return str + "  (R^2 = " + String.format("%.3f", new Object[] { Double.valueOf(R2()) }) + ")";
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LinearRegression
 * JD-Core Version:    0.6.2
 */