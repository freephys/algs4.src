/*     */ public class InsertionX
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  46 */     int i = paramArrayOfComparable.length;
/*     */ 
/*  49 */     for (int j = i - 1; j > 0; j--) {
/*  50 */       if (less(paramArrayOfComparable[j], paramArrayOfComparable[(j - 1)])) exch(paramArrayOfComparable, j, j - 1);
/*     */     }
/*     */ 
/*  53 */     for (j = 2; j < i; j++) {
/*  54 */       Comparable localComparable = paramArrayOfComparable[j];
/*  55 */       int k = j;
/*  56 */       while (less(localComparable, paramArrayOfComparable[(k - 1)])) {
/*  57 */         paramArrayOfComparable[k] = paramArrayOfComparable[(k - 1)];
/*  58 */         k--;
/*     */       }
/*  60 */       paramArrayOfComparable[k] = localComparable;
/*     */     }
/*     */ 
/*  63 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  73 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  78 */     Object localObject = paramArrayOfObject[paramInt1];
/*  79 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  80 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/*  88 */     for (int i = 1; i < paramArrayOfComparable.length; i++)
/*  89 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/*  95 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/*  96 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 105 */     String[] arrayOfString = StdIn.readAllStrings();
/* 106 */     sort(arrayOfString);
/* 107 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     InsertionX
 * JD-Core Version:    0.6.2
 */