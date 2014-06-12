/*    */ public class Average
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 40 */     int i = 0;
/* 41 */     double d1 = 0.0D;
/*    */ 
/* 44 */     while (!StdIn.isEmpty()) {
/* 45 */       d2 = StdIn.readDouble();
/* 46 */       d1 += d2;
/* 47 */       i++;
/*    */     }
/*    */ 
/* 51 */     double d2 = d1 / i;
/*    */ 
/* 54 */     StdOut.println("Average is " + d2);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Average
 * JD-Core Version:    0.6.2
 */