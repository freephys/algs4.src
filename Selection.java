/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Selection
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  46 */     int i = paramArrayOfComparable.length;
/*  47 */     for (int j = 0; j < i; j++) {
/*  48 */       int k = j;
/*  49 */       for (int m = j + 1; m < i; m++) {
/*  50 */         if (less(paramArrayOfComparable[m], paramArrayOfComparable[k])) k = m;
/*     */       }
/*  52 */       exch(paramArrayOfComparable, j, k);
/*  53 */       assert (isSorted(paramArrayOfComparable, 0, j));
/*     */     }
/*  55 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   public static void sort(Object[] paramArrayOfObject, Comparator paramComparator)
/*     */   {
/*  64 */     int i = paramArrayOfObject.length;
/*  65 */     for (int j = 0; j < i; j++) {
/*  66 */       int k = j;
/*  67 */       for (int m = j + 1; m < i; m++) {
/*  68 */         if (less(paramComparator, paramArrayOfObject[m], paramArrayOfObject[k])) k = m;
/*     */       }
/*  70 */       exch(paramArrayOfObject, j, k);
/*  71 */       assert (isSorted(paramArrayOfObject, paramComparator, 0, j));
/*     */     }
/*  73 */     assert (isSorted(paramArrayOfObject, paramComparator));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  83 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparator paramComparator, Object paramObject1, Object paramObject2)
/*     */   {
/*  88 */     return paramComparator.compare(paramObject1, paramObject2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  94 */     Object localObject = paramArrayOfObject[paramInt1];
/*  95 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  96 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 106 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/* 111 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 112 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Object[] paramArrayOfObject, Comparator paramComparator)
/*     */   {
/* 118 */     return isSorted(paramArrayOfObject, paramComparator, 0, paramArrayOfObject.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Object[] paramArrayOfObject, Comparator paramComparator, int paramInt1, int paramInt2)
/*     */   {
/* 123 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 124 */       if (less(paramComparator, paramArrayOfObject[i], paramArrayOfObject[(i - 1)])) return false;
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 132 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 133 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 142 */     String[] arrayOfString = StdIn.readAllStrings();
/* 143 */     sort(arrayOfString);
/* 144 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Selection
 * JD-Core Version:    0.6.2
 */