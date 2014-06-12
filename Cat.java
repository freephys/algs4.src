/*    */ public class Cat
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 47 */     Out localOut = new Out(paramArrayOfString[(paramArrayOfString.length - 1)]);
/* 48 */     for (int i = 0; i < paramArrayOfString.length - 1; i++) {
/* 49 */       In localIn = new In(paramArrayOfString[i]);
/* 50 */       String str = localIn.readAll();
/* 51 */       localOut.println(str);
/* 52 */       localIn.close();
/*    */     }
/* 54 */     localOut.close();
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Cat
 * JD-Core Version:    0.6.2
 */