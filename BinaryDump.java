/*    */ public class BinaryDump
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 26 */     int i = 16;
/* 27 */     if (paramArrayOfString.length == 1) {
/* 28 */       i = Integer.parseInt(paramArrayOfString[0]);
/*    */     }
/*    */ 
/* 32 */     for (int j = 0; !BinaryStdIn.isEmpty(); j++)
/* 33 */       if (i == 0) { BinaryStdIn.readBoolean(); } else {
/* 34 */         if ((j != 0) && (j % i == 0)) StdOut.println();
/* 35 */         if (BinaryStdIn.readBoolean()) StdOut.print(1); else
/* 36 */           StdOut.print(0);
/*    */       }
/* 38 */     if (i != 0) StdOut.println();
/* 39 */     StdOut.println(j + " bits");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BinaryDump
 * JD-Core Version:    0.6.2
 */