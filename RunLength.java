/*    */ public class RunLength
/*    */ {
/*    */   private static final int R = 256;
/*    */   private static final int lgR = 8;
/*    */ 
/*    */   public static void expand()
/*    */   {
/* 27 */     boolean bool = false;
/* 28 */     while (!BinaryStdIn.isEmpty()) {
/* 29 */       int i = BinaryStdIn.readInt(8);
/* 30 */       for (int j = 0; j < i; j++)
/* 31 */         BinaryStdOut.write(bool);
/* 32 */       bool = !bool;
/*    */     }
/* 34 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void compress() {
/* 38 */     char c = '\000';
/* 39 */     boolean bool1 = false;
/* 40 */     while (!BinaryStdIn.isEmpty()) {
/* 41 */       boolean bool2 = BinaryStdIn.readBoolean();
/* 42 */       if (bool2 != bool1) {
/* 43 */         BinaryStdOut.write(c, 8);
/* 44 */         c = '\001';
/* 45 */         bool1 = !bool1;
/*    */       }
/*    */       else {
/* 48 */         if (c == 'ÿ') {
/* 49 */           BinaryStdOut.write(c, 8);
/* 50 */           c = '\000';
/* 51 */           BinaryStdOut.write(c, 8);
/*    */         }
/* 53 */         c = (char)(c + '\001');
/*    */       }
/*    */     }
/* 56 */     BinaryStdOut.write(c, 8);
/* 57 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 62 */     if (paramArrayOfString[0].equals("-")) compress();
/* 63 */     else if (paramArrayOfString[0].equals("+")) expand(); else
/* 64 */       throw new IllegalArgumentException("Illegal command line argument");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     RunLength
 * JD-Core Version:    0.6.2
 */