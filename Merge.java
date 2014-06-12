/*     */ public class Merge
/*     */ {
/*     */   private static void merge(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  44 */     assert (isSorted(paramArrayOfComparable1, paramInt1, paramInt2));
/*  45 */     assert (isSorted(paramArrayOfComparable1, paramInt2 + 1, paramInt3));
/*     */ 
/*  48 */     for (int i = paramInt1; i <= paramInt3; i++) {
/*  49 */       paramArrayOfComparable2[i] = paramArrayOfComparable1[i];
/*     */     }
/*     */ 
/*  53 */     i = paramInt1; int j = paramInt2 + 1;
/*  54 */     for (int k = paramInt1; k <= paramInt3; k++) {
/*  55 */       if (i > paramInt2) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)];
/*  56 */       else if (j > paramInt3) paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/*  57 */       else if (less(paramArrayOfComparable2[j], paramArrayOfComparable2[i])) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)]; else {
/*  58 */         paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/*     */       }
/*     */     }
/*     */ 
/*  62 */     assert (isSorted(paramArrayOfComparable1, paramInt1, paramInt3));
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2)
/*     */   {
/*  67 */     if (paramInt2 <= paramInt1) return;
/*  68 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  69 */     sort(paramArrayOfComparable1, paramArrayOfComparable2, paramInt1, i);
/*  70 */     sort(paramArrayOfComparable1, paramArrayOfComparable2, i + 1, paramInt2);
/*  71 */     merge(paramArrayOfComparable1, paramArrayOfComparable2, paramInt1, i, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  79 */     Comparable[] arrayOfComparable = new Comparable[paramArrayOfComparable.length];
/*  80 */     sort(paramArrayOfComparable, arrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*  81 */     assert (isSorted(paramArrayOfComparable));
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/*  91 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  96 */     Object localObject = paramArrayOfObject[paramInt1];
/*  97 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  98 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 106 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2) {
/* 110 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 111 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   private static void merge(Comparable[] paramArrayOfComparable, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 123 */     for (int i = paramInt1; i <= paramInt3; i++) {
/* 124 */       paramArrayOfInt2[i] = paramArrayOfInt1[i];
/*     */     }
/*     */ 
/* 128 */     i = paramInt1; int j = paramInt2 + 1;
/* 129 */     for (int k = paramInt1; k <= paramInt3; k++)
/* 130 */       if (i > paramInt2) paramArrayOfInt1[k] = paramArrayOfInt2[(j++)];
/* 131 */       else if (j > paramInt3) paramArrayOfInt1[k] = paramArrayOfInt2[(i++)];
/* 132 */       else if (less(paramArrayOfComparable[paramArrayOfInt2[j]], paramArrayOfComparable[paramArrayOfInt2[i]])) paramArrayOfInt1[k] = paramArrayOfInt2[(j++)]; else
/* 133 */         paramArrayOfInt1[k] = paramArrayOfInt2[(i++)];
/*     */   }
/*     */ 
/*     */   public static int[] indexSort(Comparable[] paramArrayOfComparable)
/*     */   {
/* 144 */     int i = paramArrayOfComparable.length;
/* 145 */     int[] arrayOfInt1 = new int[i];
/* 146 */     for (int j = 0; j < i; j++) {
/* 147 */       arrayOfInt1[j] = j;
/*     */     }
/* 149 */     int[] arrayOfInt2 = new int[i];
/* 150 */     sort(paramArrayOfComparable, arrayOfInt1, arrayOfInt2, 0, i - 1);
/* 151 */     return arrayOfInt1;
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2)
/*     */   {
/* 156 */     if (paramInt2 <= paramInt1) return;
/* 157 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 158 */     sort(paramArrayOfComparable, paramArrayOfInt1, paramArrayOfInt2, paramInt1, i);
/* 159 */     sort(paramArrayOfComparable, paramArrayOfInt1, paramArrayOfInt2, i + 1, paramInt2);
/* 160 */     merge(paramArrayOfComparable, paramArrayOfInt1, paramArrayOfInt2, paramInt1, i, paramInt2);
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 165 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 166 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 175 */     String[] arrayOfString = StdIn.readAllStrings();
/* 176 */     sort(arrayOfString);
/* 177 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Merge
 * JD-Core Version:    0.6.2
 */