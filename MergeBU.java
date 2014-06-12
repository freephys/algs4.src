/*     */ public class MergeBU
/*     */ {
/*     */   private static void merge(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  44 */     for (int i = paramInt1; i <= paramInt3; i++) {
/*  45 */       paramArrayOfComparable2[i] = paramArrayOfComparable1[i];
/*     */     }
/*     */ 
/*  49 */     i = paramInt1; int j = paramInt2 + 1;
/*  50 */     for (int k = paramInt1; k <= paramInt3; k++)
/*  51 */       if (i > paramInt2) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)];
/*  52 */       else if (j > paramInt3) paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/*  53 */       else if (less(paramArrayOfComparable2[j], paramArrayOfComparable2[i])) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)]; else
/*  54 */         paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/*     */   }
/*     */ 
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  64 */     int i = paramArrayOfComparable.length;
/*  65 */     Comparable[] arrayOfComparable = new Comparable[i];
/*  66 */     for (int j = 1; j < i; j += j) {
/*  67 */       for (int k = 0; k < i - j; k += j + j) {
/*  68 */         int m = k;
/*  69 */         int n = k + j - 1;
/*  70 */         int i1 = Math.min(k + j + j - 1, i - 1);
/*  71 */         merge(paramArrayOfComparable, arrayOfComparable, m, n, i1);
/*     */       }
/*     */     }
/*  74 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  83 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  88 */     Object localObject = paramArrayOfObject[paramInt1];
/*  89 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  90 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/*  98 */     for (int i = 1; i < paramArrayOfComparable.length; i++)
/*  99 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 105 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 106 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 115 */     String[] arrayOfString = StdIn.readAllStrings();
/* 116 */     sort(arrayOfString);
/* 117 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     MergeBU
 * JD-Core Version:    0.6.2
 */