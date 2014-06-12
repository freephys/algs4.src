/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Insertion
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  46 */     int i = paramArrayOfComparable.length;
/*  47 */     for (int j = 0; j < i; j++) {
/*  48 */       for (int k = j; (k > 0) && (less(paramArrayOfComparable[k], paramArrayOfComparable[(k - 1)])); k--) {
/*  49 */         exch(paramArrayOfComparable, k, k - 1);
/*     */       }
/*  51 */       assert (isSorted(paramArrayOfComparable, 0, j));
/*     */     }
/*  53 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   public static void sort(Object[] paramArrayOfObject, Comparator paramComparator)
/*     */   {
/*  62 */     int i = paramArrayOfObject.length;
/*  63 */     for (int j = 0; j < i; j++) {
/*  64 */       for (int k = j; (k > 0) && (less(paramComparator, paramArrayOfObject[k], paramArrayOfObject[(k - 1)])); k--) {
/*  65 */         exch(paramArrayOfObject, k, k - 1);
/*     */       }
/*  67 */       assert (isSorted(paramArrayOfObject, paramComparator, 0, j));
/*     */     }
/*  69 */     assert (isSorted(paramArrayOfObject, paramComparator));
/*     */   }
/*     */ 
/*     */   public static int[] indexSort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  81 */     int i = paramArrayOfComparable.length;
/*  82 */     int[] arrayOfInt = new int[i];
/*  83 */     for (int j = 0; j < i; j++) {
/*  84 */       arrayOfInt[j] = j;
/*     */     }
/*  86 */     for (j = 0; j < i; j++) {
/*  87 */       for (int k = j; (k > 0) && (less(paramArrayOfComparable[arrayOfInt[k]], paramArrayOfComparable[arrayOfInt[(k - 1)]])); k--)
/*  88 */         exch(arrayOfInt, k, k - 1);
/*     */     }
/*  90 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  99 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparator paramComparator, Object paramObject1, Object paramObject2)
/*     */   {
/* 104 */     return paramComparator.compare(paramObject1, paramObject2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 109 */     Object localObject = paramArrayOfObject[paramInt1];
/* 110 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/* 111 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static void exch(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 116 */     int i = paramArrayOfInt[paramInt1];
/* 117 */     paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
/* 118 */     paramArrayOfInt[paramInt2] = i;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 125 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/* 130 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 131 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Object[] paramArrayOfObject, Comparator paramComparator) {
/* 136 */     return isSorted(paramArrayOfObject, paramComparator, 0, paramArrayOfObject.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Object[] paramArrayOfObject, Comparator paramComparator, int paramInt1, int paramInt2)
/*     */   {
/* 141 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 142 */       if (less(paramComparator, paramArrayOfObject[i], paramArrayOfObject[(i - 1)])) return false;
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 148 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 149 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 158 */     String[] arrayOfString = StdIn.readAllStrings();
/* 159 */     sort(arrayOfString);
/* 160 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Insertion
 * JD-Core Version:    0.6.2
 */