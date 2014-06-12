/*    */ public class RandomSeq
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 39 */     int i = Integer.parseInt(paramArrayOfString[0]);
/*    */ 
/* 42 */     if (paramArrayOfString.length == 1)
/*    */     {
/* 44 */       for (int j = 0; j < i; j++) {
/* 45 */         double d2 = StdRandom.uniform();
/* 46 */         StdOut.println(d2);
/*    */       }
/*    */ 
/*    */     }
/* 50 */     else if (paramArrayOfString.length == 3) {
/* 51 */       double d1 = Double.parseDouble(paramArrayOfString[1]);
/* 52 */       double d3 = Double.parseDouble(paramArrayOfString[2]);
/*    */ 
/* 55 */       for (int k = 0; k < i; k++) {
/* 56 */         double d4 = StdRandom.uniform(d1, d3);
/* 57 */         StdOut.printf("%.2f\n", new Object[] { Double.valueOf(d4) });
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 62 */       throw new IllegalArgumentException("Invalid number of arguments");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     RandomSeq
 * JD-Core Version:    0.6.2
 */