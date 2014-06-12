/*    */ public class DoublingTest
/*    */ {
/*    */   public static double timeTrial(int paramInt)
/*    */   {
/* 40 */     int i = 1000000;
/* 41 */     int[] arrayOfInt = new int[paramInt];
/* 42 */     for (int j = 0; j < paramInt; j++) {
/* 43 */       arrayOfInt[j] = StdRandom.uniform(-i, i);
/*    */     }
/* 45 */     Stopwatch localStopwatch = new Stopwatch();
/* 46 */     int k = ThreeSum.count(arrayOfInt);
/* 47 */     return localStopwatch.elapsedTime();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 55 */     for (int i = 250; ; i += i) {
/* 56 */       double d = timeTrial(i);
/* 57 */       StdOut.printf("%7d %5.1f\n", new Object[] { Integer.valueOf(i), Double.valueOf(d) });
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DoublingTest
 * JD-Core Version:    0.6.2
 */