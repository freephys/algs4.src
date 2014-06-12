/*    */ public class Count
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 35 */     Alphabet localAlphabet = new Alphabet(paramArrayOfString[0]);
/* 36 */     int i = localAlphabet.R();
/* 37 */     int[] arrayOfInt = new int[i];
/* 38 */     String str = StdIn.readAll();
/* 39 */     int j = str.length();
/* 40 */     for (int k = 0; k < j; k++)
/* 41 */       if (localAlphabet.contains(str.charAt(k)))
/* 42 */         arrayOfInt[localAlphabet.toIndex(str.charAt(k))] += 1;
/* 43 */     for (k = 0; k < i; k++)
/* 44 */       StdOut.println(localAlphabet.toChar(k) + " " + arrayOfInt[k]);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Count
 * JD-Core Version:    0.6.2
 */