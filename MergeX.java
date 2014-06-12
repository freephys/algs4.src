/*     */ public class MergeX
/*     */ {
/*     */   private static final int CUTOFF = 7;
/*     */ 
/*     */   private static void merge(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  44 */     assert (isSorted(paramArrayOfComparable1, paramInt1, paramInt2));
/*  45 */     assert (isSorted(paramArrayOfComparable1, paramInt2 + 1, paramInt3));
/*     */ 
/*  47 */     int i = paramInt1; int j = paramInt2 + 1;
/*  48 */     for (int k = paramInt1; k <= paramInt3; k++) {
/*  49 */       if (i > paramInt2) paramArrayOfComparable2[k] = paramArrayOfComparable1[(j++)];
/*  50 */       else if (j > paramInt3) paramArrayOfComparable2[k] = paramArrayOfComparable1[(i++)];
/*  51 */       else if (less(paramArrayOfComparable1[j], paramArrayOfComparable1[i])) paramArrayOfComparable2[k] = paramArrayOfComparable1[(j++)]; else {
/*  52 */         paramArrayOfComparable2[k] = paramArrayOfComparable1[(i++)];
/*     */       }
/*     */     }
/*     */ 
/*  56 */     assert (isSorted(paramArrayOfComparable2, paramInt1, paramInt3));
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2)
/*     */   {
/*  61 */     if (paramInt2 <= paramInt1 + 7) {
/*  62 */       insertionSort(paramArrayOfComparable2, paramInt1, paramInt2);
/*  63 */       return;
/*     */     }
/*  65 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  66 */     sort(paramArrayOfComparable2, paramArrayOfComparable1, paramInt1, i);
/*  67 */     sort(paramArrayOfComparable2, paramArrayOfComparable1, i + 1, paramInt2);
/*     */ 
/*  75 */     if (!less(paramArrayOfComparable1[(i + 1)], paramArrayOfComparable1[i])) {
/*  76 */       System.arraycopy(paramArrayOfComparable1, paramInt1, paramArrayOfComparable2, paramInt1, paramInt2 - paramInt1 + 1);
/*  77 */       return;
/*     */     }
/*     */ 
/*  80 */     merge(paramArrayOfComparable1, paramArrayOfComparable2, paramInt1, i, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  88 */     Comparable[] arrayOfComparable = (Comparable[])paramArrayOfComparable.clone();
/*  89 */     sort(arrayOfComparable, paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*  90 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static void insertionSort(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  96 */     for (int i = paramInt1; i <= paramInt2; i++)
/*  97 */       for (int j = i; (j > paramInt1) && (less(paramArrayOfComparable[j], paramArrayOfComparable[(j - 1)])); j--)
/*  98 */         exch(paramArrayOfComparable, j, j - 1);
/*     */   }
/*     */ 
/*     */   private static void exch(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/* 104 */     Comparable localComparable = paramArrayOfComparable[paramInt1];
/* 105 */     paramArrayOfComparable[paramInt1] = paramArrayOfComparable[paramInt2];
/* 106 */     paramArrayOfComparable[paramInt2] = localComparable;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 111 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 118 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2) {
/* 122 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 123 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 129 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 130 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 140 */     String[] arrayOfString = StdIn.readAllStrings();
/* 141 */     sort(arrayOfString);
/* 142 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     MergeX
 * JD-Core Version:    0.6.2
 */