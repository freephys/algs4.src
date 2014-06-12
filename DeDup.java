/*    */ public class DeDup
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 35 */     SET localSET = new SET();
/*    */ 
/* 38 */     while (!StdIn.isEmpty()) {
/* 39 */       String str = StdIn.readString();
/* 40 */       if (!localSET.contains(str)) {
/* 41 */         localSET.add(str);
/* 42 */         StdOut.println(str);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DeDup
 * JD-Core Version:    0.6.2
 */