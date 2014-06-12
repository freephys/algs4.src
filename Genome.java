/*    */ public class Genome
/*    */ {
/*    */   public static void compress()
/*    */   {
/* 20 */     Alphabet localAlphabet = new Alphabet("ACTG");
/* 21 */     String str = BinaryStdIn.readString();
/* 22 */     int i = str.length();
/* 23 */     BinaryStdOut.write(i);
/*    */ 
/* 26 */     for (int j = 0; j < i; j++) {
/* 27 */       int k = localAlphabet.toIndex(str.charAt(j));
/* 28 */       BinaryStdOut.write(k, 2);
/*    */     }
/* 30 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void expand() {
/* 34 */     Alphabet localAlphabet = new Alphabet("ACTG");
/* 35 */     int i = BinaryStdIn.readInt();
/*    */ 
/* 37 */     for (int j = 0; j < i; j++) {
/* 38 */       int k = BinaryStdIn.readChar(2);
/* 39 */       BinaryStdOut.write(localAlphabet.toChar(k), 8);
/*    */     }
/* 41 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 46 */     if (paramArrayOfString[0].equals("-")) compress();
/* 47 */     else if (paramArrayOfString[0].equals("+")) expand(); else
/* 48 */       throw new IllegalArgumentException("Illegal command line argument");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Genome
 * JD-Core Version:    0.6.2
 */