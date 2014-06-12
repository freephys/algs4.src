/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class StaticSETofInts
/*    */ {
/*    */   private int[] a;
/*    */ 
/*    */   public StaticSETofInts(int[] paramArrayOfInt)
/*    */   {
/* 36 */     this.a = new int[paramArrayOfInt.length];
/* 37 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/* 38 */       this.a[i] = paramArrayOfInt[i];
/*    */     }
/*    */ 
/* 41 */     Arrays.sort(this.a);
/*    */ 
/* 44 */     for (i = 1; i < this.a.length; i++)
/* 45 */       if (this.a[i] == this.a[(i - 1)])
/* 46 */         throw new IllegalArgumentException("Argument arrays contains duplicate keys.");
/*    */   }
/*    */ 
/*    */   public boolean contains(int paramInt)
/*    */   {
/* 55 */     return rank(paramInt) != -1;
/*    */   }
/*    */ 
/*    */   public int rank(int paramInt)
/*    */   {
/* 66 */     int i = 0;
/* 67 */     int j = this.a.length - 1;
/* 68 */     while (i <= j)
/*    */     {
/* 70 */       int k = i + (j - i) / 2;
/* 71 */       if (paramInt < this.a[k]) j = k - 1;
/* 72 */       else if (paramInt > this.a[k]) i = k + 1; else
/* 73 */         return k;
/*    */     }
/* 75 */     return -1;
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     StaticSETofInts
 * JD-Core Version:    0.6.2
 */