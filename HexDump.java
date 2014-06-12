/*    */ public class HexDump
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 30 */     int i = 16;
/* 31 */     if (paramArrayOfString.length == 1) {
/* 32 */       i = Integer.parseInt(paramArrayOfString[0]);
/*    */     }
/*    */ 
/* 36 */     for (int j = 0; !BinaryStdIn.isEmpty(); j++)
/* 37 */       if (i == 0) { BinaryStdIn.readChar(); } else {
/* 38 */         if (j == 0) StdOut.printf("", new Object[0]);
/* 39 */         else if (j % i == 0) StdOut.printf("\n", new Object[] { Integer.valueOf(j) }); else
/* 40 */           StdOut.print(" ");
/* 41 */         int k = BinaryStdIn.readChar();
/* 42 */         StdOut.printf("%02x", new Object[] { Integer.valueOf(k & 0xFF) });
/*    */       }
/* 44 */     if (i != 0) StdOut.println();
/* 45 */     StdOut.println(j * 8 + " bits");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     HexDump
 * JD-Core Version:    0.6.2
 */