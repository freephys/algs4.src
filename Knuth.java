/*    */ public class Knuth
/*    */ {
/*    */   public static void shuffle(Object[] paramArrayOfObject)
/*    */   {
/* 57 */     int i = paramArrayOfObject.length;
/* 58 */     for (int j = 0; j < i; j++)
/*    */     {
/* 60 */       int k = j + (int)(Math.random() * (i - j));
/* 61 */       Object localObject = paramArrayOfObject[k];
/* 62 */       paramArrayOfObject[k] = paramArrayOfObject[j];
/* 63 */       paramArrayOfObject[j] = localObject;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 74 */     String[] arrayOfString = StdIn.readAllStrings();
/*    */ 
/* 77 */     shuffle(arrayOfString);
/*    */ 
/* 80 */     for (int i = 0; i < arrayOfString.length; i++)
/* 81 */       StdOut.println(arrayOfString[i]);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Knuth
 * JD-Core Version:    0.6.2
 */