/*     */ public class Quick3way
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  44 */     StdRandom.shuffle(paramArrayOfComparable);
/*  45 */     sort(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*  46 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  51 */     if (paramInt2 <= paramInt1) return;
/*  52 */     int i = paramInt1; int j = paramInt2;
/*  53 */     Comparable localComparable = paramArrayOfComparable[paramInt1];
/*  54 */     int k = paramInt1;
/*  55 */     while (k <= j) {
/*  56 */       int m = paramArrayOfComparable[k].compareTo(localComparable);
/*  57 */       if (m < 0) exch(paramArrayOfComparable, i++, k++);
/*  58 */       else if (m > 0) exch(paramArrayOfComparable, k, j--); else {
/*  59 */         k++;
/*     */       }
/*     */     }
/*     */ 
/*  63 */     sort(paramArrayOfComparable, paramInt1, i - 1);
/*  64 */     sort(paramArrayOfComparable, j + 1, paramInt2);
/*  65 */     assert (isSorted(paramArrayOfComparable, paramInt1, paramInt2));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  76 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean eq(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  81 */     return paramComparable1.compareTo(paramComparable2) == 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  86 */     Object localObject = paramArrayOfObject[paramInt1];
/*  87 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  88 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/*  96 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2) {
/* 100 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 101 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 109 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 110 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 119 */     String[] arrayOfString = StdIn.readAllStrings();
/* 120 */     sort(arrayOfString);
/* 121 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Quick3way
 * JD-Core Version:    0.6.2
 */