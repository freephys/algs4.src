/*    */ public class DoublingRatio
/*    */ {
/*    */   public static double timeTrial(int paramInt)
/*    */   {
/* 41 */     int i = 1000000;
/* 42 */     int[] arrayOfInt = new int[paramInt];
/* 43 */     for (int j = 0; j < paramInt; j++) {
/* 44 */       arrayOfInt[j] = StdRandom.uniform(-i, i);
/*    */     }
/* 46 */     Stopwatch localStopwatch = new Stopwatch();
/* 47 */     int k = ThreeSum.count(arrayOfInt);
/* 48 */     return localStopwatch.elapsedTime();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 57 */     double d1 = timeTrial(125);
/* 58 */     for (int i = 250; ; i += i) {
/* 59 */       double d2 = timeTrial(i);
/* 60 */       StdOut.printf("%6d %7.1f %5.1f\n", new Object[] { Integer.valueOf(i), Double.valueOf(d2), Double.valueOf(d2 / d1) });
/* 61 */       d1 = d2;
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DoublingRatio
 * JD-Core Version:    0.6.2
 */