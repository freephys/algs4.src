/*    */ public class LRS
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 32 */     String str1 = StdIn.readAll().replaceAll("\\s+", " ");
/* 33 */     SuffixArray localSuffixArray = new SuffixArray(str1);
/*    */ 
/* 35 */     int i = localSuffixArray.length();
/*    */ 
/* 37 */     String str2 = "";
/* 38 */     for (int j = 1; j < i; j++) {
/* 39 */       int k = localSuffixArray.lcp(j);
/* 40 */       if (k > str2.length())
/*    */       {
/* 42 */         str2 = str1.substring(localSuffixArray.index(j), localSuffixArray.index(j) + k);
/*    */       }
/*    */     }
/*    */ 
/* 46 */     StdOut.println("'" + str2 + "'");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LRS
 * JD-Core Version:    0.6.2
 */