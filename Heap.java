/*     */ public class Heap
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  44 */     int i = paramArrayOfComparable.length;
/*  45 */     for (int j = i / 2; j >= 1; j--)
/*  46 */       sink(paramArrayOfComparable, j, i);
/*  47 */     while (i > 1) {
/*  48 */       exch(paramArrayOfComparable, 1, i--);
/*  49 */       sink(paramArrayOfComparable, 1, i);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void sink(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  58 */     while (2 * paramInt1 <= paramInt2) {
/*  59 */       int i = 2 * paramInt1;
/*  60 */       if ((i < paramInt2) && (less(paramArrayOfComparable, i, i + 1))) i++;
/*  61 */       if (!less(paramArrayOfComparable, paramInt1, i)) break;
/*  62 */       exch(paramArrayOfComparable, paramInt1, i);
/*  63 */       paramInt1 = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  72 */     return paramArrayOfComparable[(paramInt1 - 1)].compareTo(paramArrayOfComparable[(paramInt2 - 1)]) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2) {
/*  76 */     Object localObject = paramArrayOfObject[(paramInt1 - 1)];
/*  77 */     paramArrayOfObject[(paramInt1 - 1)] = paramArrayOfObject[(paramInt2 - 1)];
/*  78 */     paramArrayOfObject[(paramInt2 - 1)] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  83 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/*  91 */     for (int i = 1; i < paramArrayOfComparable.length; i++)
/*  92 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/*  99 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 100 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 109 */     String[] arrayOfString = StdIn.readAllStrings();
/* 110 */     sort(arrayOfString);
/* 111 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Heap
 * JD-Core Version:    0.6.2
 */