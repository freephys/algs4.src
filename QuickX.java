/*     */ public class QuickX
/*     */ {
/*     */   private static final int CUTOFF = 8;
/*     */ 
/*     */   public static void sort(Comparable[] paramArrayOfComparable)
/*     */   {
/*  37 */     sort(paramArrayOfComparable, 0, paramArrayOfComparable.length - 1);
/*     */   }
/*     */ 
/*     */   private static void sort(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2) {
/*  41 */     int i = paramInt2 - paramInt1 + 1;
/*     */ 
/*  44 */     if (i <= 8) {
/*  45 */       insertionSort(paramArrayOfComparable, paramInt1, paramInt2);
/*  46 */       return;
/*     */     }
/*     */ 
/*  50 */     if (i <= 40) {
/*  51 */       j = median3(paramArrayOfComparable, paramInt1, paramInt1 + i / 2, paramInt2);
/*  52 */       exch(paramArrayOfComparable, j, paramInt1);
/*     */     }
/*     */     else
/*     */     {
/*  57 */       j = i / 8;
/*  58 */       k = paramInt1 + i / 2;
/*  59 */       m = median3(paramArrayOfComparable, paramInt1, paramInt1 + j, paramInt1 + j + j);
/*  60 */       n = median3(paramArrayOfComparable, k - j, k, k + j);
/*  61 */       int i1 = median3(paramArrayOfComparable, paramInt2 - j - j, paramInt2 - j, paramInt2);
/*  62 */       i2 = median3(paramArrayOfComparable, m, n, i1);
/*  63 */       exch(paramArrayOfComparable, i2, paramInt1);
/*     */     }
/*     */ 
/*  67 */     int j = paramInt1; int k = paramInt2 + 1;
/*  68 */     int m = paramInt1; int n = paramInt2 + 1;
/*  69 */     Comparable localComparable = paramArrayOfComparable[paramInt1];
/*     */     while (true) {
/*  71 */       if (less(paramArrayOfComparable[(++j)], localComparable)) {
/*  72 */         if (j == paramInt2) break label179; 
/*     */       } else { label179: while (less(localComparable, paramArrayOfComparable[(--k)])) {
/*  74 */           if (k == paramInt1) break;
/*     */         }
/*     */ 
/*  77 */         if ((j == k) && (eq(paramArrayOfComparable[j], localComparable)))
/*  78 */           exch(paramArrayOfComparable, ++m, j);
/*  79 */         if (j >= k)
/*     */           break;
/*  81 */         exch(paramArrayOfComparable, j, k);
/*  82 */         if (eq(paramArrayOfComparable[j], localComparable)) exch(paramArrayOfComparable, ++m, j);
/*  83 */         if (eq(paramArrayOfComparable[k], localComparable)) exch(paramArrayOfComparable, --n, k);
/*     */       }
/*     */     }
/*     */ 
/*  87 */     j = k + 1;
/*  88 */     for (int i2 = paramInt1; i2 <= m; i2++) exch(paramArrayOfComparable, i2, k--);
/*  89 */     for (i2 = paramInt2; i2 >= n; i2--) exch(paramArrayOfComparable, i2, j++);
/*     */ 
/*  91 */     sort(paramArrayOfComparable, paramInt1, k);
/*  92 */     sort(paramArrayOfComparable, j, paramInt2);
/*     */   }
/*     */ 
/*     */   private static void insertionSort(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2)
/*     */   {
/*  98 */     for (int i = paramInt1; i <= paramInt2; i++)
/*  99 */       for (int j = i; (j > paramInt1) && (less(paramArrayOfComparable[j], paramArrayOfComparable[(j - 1)])); j--)
/* 100 */         exch(paramArrayOfComparable, j, j - 1);
/*     */   }
/*     */ 
/*     */   private static int median3(Comparable[] paramArrayOfComparable, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 106 */     return less(paramArrayOfComparable[paramInt3], paramArrayOfComparable[paramInt1]) ? paramInt3 : less(paramArrayOfComparable[paramInt3], paramArrayOfComparable[paramInt2]) ? paramInt2 : less(paramArrayOfComparable[paramInt1], paramArrayOfComparable[paramInt2]) ? paramInt1 : less(paramArrayOfComparable[paramInt1], paramArrayOfComparable[paramInt3]) ? paramInt3 : less(paramArrayOfComparable[paramInt2], paramArrayOfComparable[paramInt3]) ? paramInt2 : paramInt1;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 117 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static boolean eq(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 122 */     return paramComparable1.compareTo(paramComparable2) == 0;
/*     */   }
/*     */ 
/*     */   private static void exch(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 127 */     Object localObject = paramArrayOfObject[paramInt1];
/* 128 */     paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/* 129 */     paramArrayOfObject[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(Comparable[] paramArrayOfComparable)
/*     */   {
/* 137 */     for (int i = 1; i < paramArrayOfComparable.length; i++)
/* 138 */       if (less(paramArrayOfComparable[i], paramArrayOfComparable[(i - 1)])) return false;
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   private static void show(Comparable[] paramArrayOfComparable)
/*     */   {
/* 144 */     for (int i = 0; i < paramArrayOfComparable.length; i++)
/* 145 */       StdOut.println(paramArrayOfComparable[i]);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 155 */     String[] arrayOfString = StdIn.readAllStrings();
/* 156 */     sort(arrayOfString);
/* 157 */     show(arrayOfString);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     QuickX
 * JD-Core Version:    0.6.2
 */