/*    */ public class LongestCommonSubstring
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 25 */     In localIn1 = new In(paramArrayOfString[0]);
/* 26 */     In localIn2 = new In(paramArrayOfString[1]);
/* 27 */     String str1 = localIn1.readAll().trim().replaceAll("\\s+", " ");
/* 28 */     String str2 = localIn2.readAll().trim().replaceAll("\\s+", " ");
/* 29 */     int i = str1.length();
/* 30 */     int j = str2.length();
/*    */ 
/* 33 */     String str3 = str1 + '\001' + str2;
/* 34 */     int k = str3.length();
/*    */ 
/* 37 */     SuffixArray localSuffixArray = new SuffixArray(str3);
/*    */ 
/* 40 */     String str4 = "";
/* 41 */     for (int m = 1; m < k; m++)
/*    */     {
/* 44 */       if ((localSuffixArray.index(m) >= i) || (localSuffixArray.index(m - 1) >= i))
/*    */       {
/* 47 */         if ((localSuffixArray.index(m) <= i) || (localSuffixArray.index(m - 1) <= i))
/*    */         {
/* 50 */           int n = localSuffixArray.lcp(m);
/* 51 */           if (n > str4.length()) {
/* 52 */             str4 = str3.substring(localSuffixArray.index(m), localSuffixArray.index(m) + n);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 57 */     StdOut.println(str4.length());
/* 58 */     StdOut.println("'" + str4 + "'");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LongestCommonSubstring
 * JD-Core Version:    0.6.2
 */