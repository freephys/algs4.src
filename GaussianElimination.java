/*    */ public class GaussianElimination
/*    */ {
/*    */   private static final double EPSILON = 1.0E-10D;
/*    */ 
/*    */   public static double[] lsolve(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1)
/*    */   {
/* 19 */     int i = paramArrayOfDouble1.length;
/*    */ 
/* 21 */     for (int j = 0; j < i; j++)
/*    */     {
/* 24 */       k = j;
/* 25 */       for (int m = j + 1; m < i; m++) {
/* 26 */         if (Math.abs(paramArrayOfDouble[m][j]) > Math.abs(paramArrayOfDouble[k][j])) {
/* 27 */           k = m;
/*    */         }
/*    */       }
/* 30 */       double[] arrayOfDouble2 = paramArrayOfDouble[j]; paramArrayOfDouble[j] = paramArrayOfDouble[k]; paramArrayOfDouble[k] = arrayOfDouble2;
/* 31 */       double d2 = paramArrayOfDouble1[j]; paramArrayOfDouble1[j] = paramArrayOfDouble1[k]; paramArrayOfDouble1[k] = d2;
/*    */ 
/* 34 */       if (Math.abs(paramArrayOfDouble[j][j]) <= 1.0E-10D) {
/* 35 */         throw new ArithmeticException("Matrix is singular or nearly singular");
/*    */       }
/*    */ 
/* 39 */       for (int i1 = j + 1; i1 < i; i1++) {
/* 40 */         double d3 = paramArrayOfDouble[i1][j] / paramArrayOfDouble[j][j];
/* 41 */         paramArrayOfDouble1[i1] -= d3 * paramArrayOfDouble1[j];
/* 42 */         for (int i2 = j; i2 < i; i2++) {
/* 43 */           paramArrayOfDouble[i1][i2] -= d3 * paramArrayOfDouble[j][i2];
/*    */         }
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 49 */     double[] arrayOfDouble1 = new double[i];
/* 50 */     for (int k = i - 1; k >= 0; k--) {
/* 51 */       double d1 = 0.0D;
/* 52 */       for (int n = k + 1; n < i; n++) {
/* 53 */         d1 += paramArrayOfDouble[k][n] * arrayOfDouble1[n];
/*    */       }
/* 55 */       arrayOfDouble1[k] = ((paramArrayOfDouble1[k] - d1) / paramArrayOfDouble[k][k]);
/*    */     }
/* 57 */     return arrayOfDouble1;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 63 */     int i = 3;
/* 64 */     double[][] arrayOfDouble = { { 0.0D, 1.0D, 1.0D }, { 2.0D, 4.0D, -2.0D }, { 0.0D, 3.0D, 15.0D } };
/*    */ 
/* 69 */     double[] arrayOfDouble1 = { 4.0D, 2.0D, 36.0D };
/* 70 */     double[] arrayOfDouble2 = lsolve(arrayOfDouble, arrayOfDouble1);
/*    */ 
/* 74 */     for (int j = 0; j < i; j++)
/* 75 */       StdOut.println(arrayOfDouble2[j]);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     GaussianElimination
 * JD-Core Version:    0.6.2
 */