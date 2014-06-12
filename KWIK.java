/*    */ public class KWIK
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 38 */     In localIn = new In(paramArrayOfString[0]);
/* 39 */     int i = Integer.parseInt(paramArrayOfString[1]);
/*    */ 
/* 42 */     String str1 = localIn.readAll().replaceAll("\\s+", " ");
/* 43 */     int j = str1.length();
/*    */ 
/* 46 */     SuffixArray localSuffixArray = new SuffixArray(str1);
/*    */ 
/* 49 */     while (StdIn.hasNextLine()) {
/* 50 */       String str2 = StdIn.readLine();
/* 51 */       for (int k = localSuffixArray.rank(str2); k < j; k++) {
/* 52 */         int m = localSuffixArray.index(k);
/* 53 */         int n = Math.min(j, m + str2.length());
/* 54 */         if (!str2.equals(str1.substring(m, n))) break;
/* 55 */         int i1 = Math.max(0, localSuffixArray.index(k) - i);
/* 56 */         int i2 = Math.min(j, localSuffixArray.index(k) + i + str2.length());
/* 57 */         StdOut.println(str1.substring(i1, i2));
/*    */       }
/* 59 */       StdOut.println();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     KWIK
 * JD-Core Version:    0.6.2
 */