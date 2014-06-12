/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class BinarySearch
/*    */ {
/*    */   public static int rank(int paramInt, int[] paramArrayOfInt)
/*    */   {
/* 53 */     int i = 0;
/* 54 */     int j = paramArrayOfInt.length - 1;
/* 55 */     while (i <= j)
/*    */     {
/* 57 */       int k = i + (j - i) / 2;
/* 58 */       if (paramInt < paramArrayOfInt[k]) j = k - 1;
/* 59 */       else if (paramInt > paramArrayOfInt[k]) i = k + 1; else
/* 60 */         return k;
/*    */     }
/* 62 */     return -1;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 73 */     In localIn = new In(paramArrayOfString[0]);
/* 74 */     int[] arrayOfInt = localIn.readAllInts();
/*    */ 
/* 77 */     Arrays.sort(arrayOfInt);
/*    */ 
/* 80 */     while (!StdIn.isEmpty()) {
/* 81 */       int i = StdIn.readInt();
/* 82 */       if (rank(i, arrayOfInt) == -1)
/* 83 */         StdOut.println(i);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BinarySearch
 * JD-Core Version:    0.6.2
 */