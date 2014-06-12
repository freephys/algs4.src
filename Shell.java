/*     */ public class Shell
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  51 */     int i = paramArrayOfComparable.length;
/*     */ 
/*  54 */     int j = 1;
/*  55 */     while (j < i / 3) j = 3 * j + 1;
/*     */ 
/*  57 */     while (j >= 1)
/*     */     {
/*  59 */       for (int k = j; k < i; k++) {
/*  60 */         for (int m = k; (m >= j) && (less(paramArrayOfComparable[m], paramArrayOfComparable[(m - j)])); m -= j) {
/*  61 */           exch(paramArrayOfComparable, m, m - j);
/*     */         }
/*     */       }
/*  64 */       assert (isHsorted(paramArrayOfComparable, j));
/*  65 */       j /= 3;
/*     */     }
/*  67 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  78 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  83 */     Object localObject = paramArrayOfObject[paramInt1];
/*  84 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  85 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/*  93 */     for (int i = 1; i < paramArrayOfComparable.length; i++)
/*  94 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   private static boolean isHsorted(Comparable[] paramArrayOfComparable, int paramInt)
/*     */   {
/* 100 */     for (int i = paramInt; i < paramArrayOfComparable.length; i++)
/* 101 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - paramInt)])) return false;
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 107 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 108 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 117 */     String[] arrayOfString = StdIn.readAllStrings();
/* 118 */     sort(arrayOfString);
/* 119 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Shell
 * JD-Core Version:    0.6.2
 */