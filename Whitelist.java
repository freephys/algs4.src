/*    */ public class Whitelist
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 50 */     In localIn = new In(paramArrayOfString[0]);
/* 51 */     int[] arrayOfInt = localIn.readAllInts();
/* 52 */     StaticSETofInts localStaticSETofInts = new StaticSETofInts(arrayOfInt);
/*    */ 
/* 55 */     while (!StdIn.isEmpty()) {
/* 56 */       int i = StdIn.readInt();
/* 57 */       if (!localStaticSETofInts.contains(i))
/* 58 */         StdOut.println(i);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Whitelist
 * JD-Core Version:    0.6.2
 */