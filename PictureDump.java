/*    */ import java.awt.Color;
/*    */ 
/*    */ public class PictureDump
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 21 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 22 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 23 */     Picture localPicture = new Picture(i, j);
/* 24 */     int k = 0;
/* 25 */     for (int m = 0; m < j; m++) {
/* 26 */       for (int n = 0; n < i; n++) {
/* 27 */         localPicture.set(n, m, Color.RED);
/* 28 */         if (!BinaryStdIn.isEmpty()) {
/* 29 */           k++;
/* 30 */           boolean bool = BinaryStdIn.readBoolean();
/* 31 */           if (bool) localPicture.set(n, m, Color.BLACK); else
/* 32 */             localPicture.set(n, m, Color.WHITE);
/*    */         }
/*    */       }
/*    */     }
/* 36 */     localPicture.show();
/* 37 */     StdOut.println(k + " bits");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     PictureDump
 * JD-Core Version:    0.6.2
 */