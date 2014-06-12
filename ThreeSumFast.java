/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class ThreeSumFast
/*     */ {
/*     */   private static boolean containsDuplicates(int[] paramArrayOfInt)
/*     */   {
/*  62 */     for (int i = 1; i < paramArrayOfInt.length; i++)
/*  63 */       if (paramArrayOfInt[i] == paramArrayOfInt[(i - 1)]) return true;
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   public static void printAll(int[] paramArrayOfInt)
/*     */   {
/*  73 */     int i = paramArrayOfInt.length;
/*  74 */     Arrays.sort(paramArrayOfInt);
/*  75 */     if (containsDuplicates(paramArrayOfInt)) throw new IllegalArgumentException("array contains duplicate integers");
/*  76 */     for (int j = 0; j < i; j++)
/*  77 */       for (int k = j + 1; k < i; k++) {
/*  78 */         int m = Arrays.binarySearch(paramArrayOfInt, -(paramArrayOfInt[j] + paramArrayOfInt[k]));
/*  79 */         if (m > k) StdOut.println(paramArrayOfInt[j] + " " + paramArrayOfInt[k] + " " + paramArrayOfInt[m]);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static int count(int[] paramArrayOfInt)
/*     */   {
/*  90 */     int i = paramArrayOfInt.length;
/*  91 */     Arrays.sort(paramArrayOfInt);
/*  92 */     if (containsDuplicates(paramArrayOfInt)) throw new IllegalArgumentException("array contains duplicate integers");
/*  93 */     int j = 0;
/*  94 */     for (int k = 0; k < i; k++) {
/*  95 */       for (int m = k + 1; m < i; m++) {
/*  96 */         int n = Arrays.binarySearch(paramArrayOfInt, -(paramArrayOfInt[k] + paramArrayOfInt[m]));
/*  97 */         if (n > m) j++;
/*     */       }
/*     */     }
/* 100 */     return j;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 109 */     In localIn = new In(paramArrayOfString[0]);
/* 110 */     int[] arrayOfInt = localIn.readAllInts();
/* 111 */     int i = count(arrayOfInt);
/* 112 */     StdOut.println(i);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ThreeSumFast
 * JD-Core Version:    0.6.2
 */