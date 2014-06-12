/*    */ public class GREP
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 30 */     String str1 = "(.*" + paramArrayOfString[0] + ".*)";
/* 31 */     NFA localNFA = new NFA(str1);
/* 32 */     while (StdIn.hasNextLine()) {
/* 33 */       String str2 = StdIn.readLine();
/* 34 */       if (localNFA.recognizes(str2))
/* 35 */         StdOut.println(str2);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     GREP
 * JD-Core Version:    0.6.2
 */