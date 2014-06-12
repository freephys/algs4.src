/*     */ public class FFT
/*     */ {
/*     */   public static Complex[] fft(Complex[] paramArrayOfComplex)
/*     */   {
/*  25 */     int i = paramArrayOfComplex.length;
/*     */ 
/*  28 */     if (i == 1) return new Complex[] { paramArrayOfComplex[0] };
/*     */ 
/*  31 */     if (i % 2 != 0) throw new IllegalArgumentException("N is not a power of 2");
/*     */ 
/*  34 */     Complex[] arrayOfComplex1 = new Complex[i / 2];
/*  35 */     for (int j = 0; j < i / 2; j++) {
/*  36 */       arrayOfComplex1[j] = paramArrayOfComplex[(2 * j)];
/*     */     }
/*  38 */     Complex[] arrayOfComplex2 = fft(arrayOfComplex1);
/*     */ 
/*  41 */     Complex[] arrayOfComplex3 = arrayOfComplex1;
/*  42 */     for (int k = 0; k < i / 2; k++) {
/*  43 */       arrayOfComplex3[k] = paramArrayOfComplex[(2 * k + 1)];
/*     */     }
/*  45 */     Complex[] arrayOfComplex4 = fft(arrayOfComplex3);
/*     */ 
/*  48 */     Complex[] arrayOfComplex5 = new Complex[i];
/*  49 */     for (int m = 0; m < i / 2; m++) {
/*  50 */       double d = -2 * m * 3.141592653589793D / i;
/*  51 */       Complex localComplex = new Complex(Math.cos(d), Math.sin(d));
/*  52 */       arrayOfComplex5[m] = arrayOfComplex2[m].plus(localComplex.times(arrayOfComplex4[m]));
/*  53 */       arrayOfComplex5[(m + i / 2)] = arrayOfComplex2[m].minus(localComplex.times(arrayOfComplex4[m]));
/*     */     }
/*  55 */     return arrayOfComplex5;
/*     */   }
/*     */ 
/*     */   public static Complex[] ifft(Complex[] paramArrayOfComplex)
/*     */   {
/*  61 */     int i = paramArrayOfComplex.length;
/*  62 */     Complex[] arrayOfComplex = new Complex[i];
/*     */ 
/*  65 */     for (int j = 0; j < i; j++) {
/*  66 */       arrayOfComplex[j] = paramArrayOfComplex[j].conjugate();
/*     */     }
/*     */ 
/*  70 */     arrayOfComplex = fft(arrayOfComplex);
/*     */ 
/*  73 */     for (j = 0; j < i; j++) {
/*  74 */       arrayOfComplex[j] = arrayOfComplex[j].conjugate();
/*     */     }
/*     */ 
/*  78 */     for (j = 0; j < i; j++) {
/*  79 */       arrayOfComplex[j] = arrayOfComplex[j].times(1.0D / i);
/*     */     }
/*     */ 
/*  82 */     return arrayOfComplex;
/*     */   }
/*     */ 
/*     */   public static Complex[] cconvolve(Complex[] paramArrayOfComplex1, Complex[] paramArrayOfComplex2)
/*     */   {
/*  91 */     if (paramArrayOfComplex1.length != paramArrayOfComplex2.length) throw new IllegalArgumentException("Dimensions don't agree");
/*     */ 
/*  93 */     int i = paramArrayOfComplex1.length;
/*     */ 
/*  96 */     Complex[] arrayOfComplex1 = fft(paramArrayOfComplex1);
/*  97 */     Complex[] arrayOfComplex2 = fft(paramArrayOfComplex2);
/*     */ 
/* 100 */     Complex[] arrayOfComplex3 = new Complex[i];
/* 101 */     for (int j = 0; j < i; j++) {
/* 102 */       arrayOfComplex3[j] = arrayOfComplex1[j].times(arrayOfComplex2[j]);
/*     */     }
/*     */ 
/* 106 */     return ifft(arrayOfComplex3);
/*     */   }
/*     */ 
/*     */   public static Complex[] convolve(Complex[] paramArrayOfComplex1, Complex[] paramArrayOfComplex2)
/*     */   {
/* 112 */     Complex localComplex = new Complex(0.0D, 0.0D);
/*     */ 
/* 114 */     Complex[] arrayOfComplex1 = new Complex[2 * paramArrayOfComplex1.length];
/* 115 */     for (int i = 0; i < paramArrayOfComplex1.length; i++) arrayOfComplex1[i] = paramArrayOfComplex1[i];
/* 116 */     for (i = paramArrayOfComplex1.length; i < 2 * paramArrayOfComplex1.length; i++) arrayOfComplex1[i] = localComplex;
/*     */ 
/* 118 */     Complex[] arrayOfComplex2 = new Complex[2 * paramArrayOfComplex2.length];
/* 119 */     for (int j = 0; j < paramArrayOfComplex2.length; j++) arrayOfComplex2[j] = paramArrayOfComplex2[j];
/* 120 */     for (j = paramArrayOfComplex2.length; j < 2 * paramArrayOfComplex2.length; j++) arrayOfComplex2[j] = localComplex;
/*     */ 
/* 122 */     return cconvolve(arrayOfComplex1, arrayOfComplex2);
/*     */   }
/*     */ 
/*     */   public static void show(Complex[] paramArrayOfComplex, String paramString)
/*     */   {
/* 127 */     StdOut.println(paramString);
/* 128 */     StdOut.println("-------------------");
/* 129 */     for (int i = 0; i < paramArrayOfComplex.length; i++) {
/* 130 */       StdOut.println(paramArrayOfComplex[i]);
/*     */     }
/* 132 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 182 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 183 */     Complex[] arrayOfComplex1 = new Complex[i];
/*     */ 
/* 186 */     for (int j = 0; j < i; j++) {
/* 187 */       arrayOfComplex1[j] = new Complex(j, 0.0D);
/* 188 */       arrayOfComplex1[j] = new Complex(-2.0D * Math.random() + 1.0D, 0.0D);
/*     */     }
/* 190 */     show(arrayOfComplex1, "x");
/*     */ 
/* 193 */     Complex[] arrayOfComplex2 = fft(arrayOfComplex1);
/* 194 */     show(arrayOfComplex2, "y = fft(x)");
/*     */ 
/* 197 */     Complex[] arrayOfComplex3 = ifft(arrayOfComplex2);
/* 198 */     show(arrayOfComplex3, "z = ifft(y)");
/*     */ 
/* 201 */     Complex[] arrayOfComplex4 = cconvolve(arrayOfComplex1, arrayOfComplex1);
/* 202 */     show(arrayOfComplex4, "c = cconvolve(x, x)");
/*     */ 
/* 205 */     Complex[] arrayOfComplex5 = convolve(arrayOfComplex1, arrayOfComplex1);
/* 206 */     show(arrayOfComplex5, "d = convolve(x, x)");
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FFT
 * JD-Core Version:    0.6.2
 */