/*    */ public class Multiway
/*    */ {
/*    */   private static void merge(In[] paramArrayOfIn)
/*    */   {
/* 44 */     int i = paramArrayOfIn.length;
/* 45 */     IndexMinPQ localIndexMinPQ = new IndexMinPQ(i);
/* 46 */     for (int j = 0; j < i; j++) {
/* 47 */       if (!paramArrayOfIn[j].isEmpty()) {
/* 48 */         localIndexMinPQ.insert(j, paramArrayOfIn[j].readString());
/*    */       }
/*    */     }
/* 51 */     while (!localIndexMinPQ.isEmpty()) {
/* 52 */       StdOut.print((String)localIndexMinPQ.minKey() + " ");
/* 53 */       j = localIndexMinPQ.delMin();
/* 54 */       if (!paramArrayOfIn[j].isEmpty())
/* 55 */         localIndexMinPQ.insert(j, paramArrayOfIn[j].readString());
/*    */     }
/* 57 */     StdOut.println();
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 68 */     int i = paramArrayOfString.length;
/* 69 */     In[] arrayOfIn = new In[i];
/* 70 */     for (int j = 0; j < i; j++)
/* 71 */       arrayOfIn[j] = new In(paramArrayOfString[j]);
/* 72 */     merge(arrayOfIn);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Multiway
 * JD-Core Version:    0.6.2
 */