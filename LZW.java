/*    */ public class LZW
/*    */ {
/*    */   private static final int R = 256;
/*    */   private static final int L = 4096;
/*    */   private static final int W = 12;
/*    */ 
/*    */   public static void compress()
/*    */   {
/* 25 */     String str1 = BinaryStdIn.readString();
/* 26 */     TST localTST = new TST();
/* 27 */     for (int i = 0; i < 256; i++)
/* 28 */       localTST.put("" + (char)i, Integer.valueOf(i));
/* 29 */     i = 257;
/*    */ 
/* 31 */     while (str1.length() > 0) {
/* 32 */       String str2 = localTST.longestPrefixOf(str1);
/* 33 */       BinaryStdOut.write(((Integer)localTST.get(str2)).intValue(), 12);
/* 34 */       int j = str2.length();
/* 35 */       if ((j < str1.length()) && (i < 4096))
/* 36 */         localTST.put(str1.substring(0, j + 1), Integer.valueOf(i++));
/* 37 */       str1 = str1.substring(j);
/*    */     }
/* 39 */     BinaryStdOut.write(256, 12);
/* 40 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void expand()
/*    */   {
/* 45 */     String[] arrayOfString = new String[4096];
/*    */ 
/* 49 */     for (int i = 0; i < 256; i++)
/* 50 */       arrayOfString[i] = ("" + (char)i);
/* 51 */     arrayOfString[(i++)] = "";
/*    */ 
/* 53 */     int j = BinaryStdIn.readInt(12);
/* 54 */     Object localObject = arrayOfString[j];
/*    */     while (true)
/*    */     {
/* 57 */       BinaryStdOut.write((String)localObject);
/* 58 */       j = BinaryStdIn.readInt(12);
/* 59 */       if (j == 256) break;
/* 60 */       String str = arrayOfString[j];
/* 61 */       if (i == j) str = (String)localObject + ((String)localObject).charAt(0);
/* 62 */       if (i < 4096) arrayOfString[(i++)] = ((String)localObject + str.charAt(0));
/* 63 */       localObject = str;
/*    */     }
/* 65 */     BinaryStdOut.close();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 71 */     if (paramArrayOfString[0].equals("-")) compress();
/* 72 */     else if (paramArrayOfString[0].equals("+")) expand(); else
/* 73 */       throw new IllegalArgumentException("Illegal command line argument");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LZW
 * JD-Core Version:    0.6.2
 */