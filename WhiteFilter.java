/*    */ public class WhiteFilter
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 33 */     SET localSET = new SET();
/*    */ 
/* 36 */     In localIn = new In(paramArrayOfString[0]);
/*    */     String str;
/* 37 */     while (!localIn.isEmpty()) {
/* 38 */       str = localIn.readString();
/* 39 */       localSET.add(str);
/*    */     }
/*    */ 
/* 43 */     while (!StdIn.isEmpty()) {
/* 44 */       str = StdIn.readString();
/* 45 */       if (localSET.contains(str))
/* 46 */         StdOut.println(str);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     WhiteFilter
 * JD-Core Version:    0.6.2
 */