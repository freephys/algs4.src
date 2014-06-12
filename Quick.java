/*     */ public class Quick
/*     */ {
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  49 */     StdRandom.shuffle(paramArrayOfComparable);
/*  50 */     sort(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  55 */     if (paramInt2 <= paramInt1) return;
/*  56 */     int i = partition(paramArrayOfComparable, paramInt1, paramInt2);
/*  57 */     sort(paramArrayOfComparable, paramInt1, i - 1);
/*  58 */     sort(paramArrayOfComparable, i + 1, paramInt2);
/*  59 */     assert (isSorted(paramArrayOfComparable, paramInt1, paramInt2));
/*     */   }
/*     */ 
/*     */   private static int partition(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  65 */     int i = paramInt1;
/*  66 */     int j = paramInt2 + 1;
/*  67 */     Comparable localComparable = paramArrayOfComparable[paramInt1];
/*     */     while (true)
/*     */     {
/*  71 */       if (less(paramArrayOfComparable[(++i)], localComparable)) {
/*  72 */         if (i == paramInt2) break label34; 
/*     */       }
/*     */       else
/*     */       {
/*  75 */         label34: while (less(localComparable, paramArrayOfComparable[(--j)])) {
/*  76 */           if (j == paramInt1) break;
/*     */         }
/*     */ 
/*  79 */         if (i >= j)
/*     */           break;
/*  81 */         exch(paramArrayOfComparable, i, j);
/*     */       }
/*     */     }
/*     */ 
/*  85 */     exch(paramArrayOfComparable, paramInt1, j);
/*     */ 
/*  88 */     return j;
/*     */   }
/*     */ 
/*     */   public static Comparable select(Comparable[] paramArrayOfComparable, int paramInt)
/*     */   {
/*  99 */     if ((paramInt < 0) || (paramInt >= paramArrayOfComparable.length)) {
/* 100 */       throw new IndexOutOfBoundsException("Selected element out of bounds");
/*     */     }
/* 102 */     StdRandom.shuffle(paramArrayOfComparable);
/* 103 */     int i = 0; int j = paramArrayOfComparable.length - 1;
/* 104 */     while (j > i) {
/* 105 */       int k = partition(paramArrayOfComparable, i, j);
/* 106 */       if (k > paramInt) j = k - 1;
/* 107 */       else if (k < paramInt) i = k + 1; else
/* 108 */         return paramArrayOfComparable[k];
/*     */     }
/* 110 */     return paramArrayOfComparable[i];
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 121 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 126 */     Object localObject = paramArrayOfObject[paramInt1];
/* 127 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/* 128 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 136 */     return isSorted(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2) {
/* 140 */     for (int i = paramInt1 + 1; i <= paramInt2; i++)
/* 141 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 142 */     return true;
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
/* 160 */     String[] arrayOfString = StdIn.readAllStrings();
/* 161 */     sort(arrayOfString);
/* 162 */     show(arrayOfString);
/*     */ 
/* 165 */     StdRandom.shuffle(arrayOfString);
/*     */ 
/* 168 */     StdOut.println();
/* 169 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 170 */       String str = (String)select(arrayOfString, i);
/* 171 */       StdOut.println(str);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Quick
 * JD-Core Version:    0.6.2
 */