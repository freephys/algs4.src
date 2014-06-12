/*    */ public class ThreeSum
/*    */ {
/*    */   public static void printAll(int[] paramArrayOfInt)
/*    */   {
/* 49 */     int i = paramArrayOfInt.length;
/* 50 */     for (int j = 0; j < i; j++)
/* 51 */       for (int k = j + 1; k < i; k++)
/* 52 */         for (int m = k + 1; m < i; m++)
/* 53 */           if (paramArrayOfInt[j] + paramArrayOfInt[k] + paramArrayOfInt[m] == 0)
/* 54 */             StdOut.println(paramArrayOfInt[j] + " " + paramArrayOfInt[k] + " " + paramArrayOfInt[m]);
/*    */   }
/*    */ 
/*    */   public static int count(int[] paramArrayOfInt)
/*    */   {
/* 67 */     int i = paramArrayOfInt.length;
/* 68 */     int j = 0;
/* 69 */     for (int k = 0; k < i; k++) {
/* 70 */       for (int m = k + 1; m < i; m++) {
/* 71 */         for (int n = m + 1; n < i; n++) {
/* 72 */           if (paramArrayOfInt[k] + paramArrayOfInt[m] + paramArrayOfInt[n] == 0) {
/* 73 */             j++;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 78 */     return j;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 87 */     In localIn = new In(paramArrayOfString[0]);
/* 88 */     int[] arrayOfInt = localIn.readAllInts();
/*    */ 
/* 90 */     Stopwatch localStopwatch = new Stopwatch();
/* 91 */     int i = count(arrayOfInt);
/* 92 */     StdOut.println("elapsed time = " + localStopwatch.elapsedTime());
/* 93 */     StdOut.println(i);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ThreeSum
 * JD-Core Version:    0.6.2
 */