/*     */ public class Quick3string
/*     */ {
/*     */   private static final int CUTOFF = 15;
/*     */ 
/*     */   public static void sort(String[] paramArrayOfString)
/*     */   {
/*  31 */     StdRandom.shuffle(paramArrayOfString);
/*  32 */     sort(paramArrayOfString, 0, paramArrayOfString.length - 1, 0);
/*  33 */     assert (isSorted(paramArrayOfString));
/*     */   }
/*     */ 
/*     */   private static int charAt(String paramString, int paramInt)
/*     */   {
/*  38 */     assert ((paramInt >= 0) && (paramInt <= paramString.length()));
/*  39 */     if (paramInt == paramString.length()) return -1;
/*  40 */     return paramString.charAt(paramInt);
/*     */   }
/*     */ 
/*     */   private static void sort(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  48 */     if (paramInt2 <= paramInt1 + 15) {
/*  49 */       insertion(paramArrayOfString, paramInt1, paramInt2, paramInt3);
/*  50 */       return;
/*     */     }
/*     */ 
/*  53 */     int i = paramInt1; int j = paramInt2;
/*  54 */     int k = charAt(paramArrayOfString[paramInt1], paramInt3);
/*  55 */     int m = paramInt1 + 1;
/*  56 */     while (m <= j) {
/*  57 */       int n = charAt(paramArrayOfString[m], paramInt3);
/*  58 */       if (n < k) exch(paramArrayOfString, i++, m++);
/*  59 */       else if (n > k) exch(paramArrayOfString, m, j--); else {
/*  60 */         m++;
/*     */       }
/*     */     }
/*     */ 
/*  64 */     sort(paramArrayOfString, paramInt1, i - 1, paramInt3);
/*  65 */     if (k >= 0) sort(paramArrayOfString, i, j, paramInt3 + 1);
/*  66 */     sort(paramArrayOfString, j + 1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   private static void insertion(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  71 */     for (int i = paramInt1; i <= paramInt2; i++)
/*  72 */       for (int j = i; (j > paramInt1) && (less(paramArrayOfString[j], paramArrayOfString[(j - 1)], paramInt3)); j--)
/*  73 */         exch(paramArrayOfString, j, j - 1);
/*     */   }
/*     */ 
/*     */   private static void exch(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/*  78 */     String str = paramArrayOfString[paramInt1];
/*  79 */     paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/*  80 */     paramArrayOfString[paramInt2] = str;
/*     */   }
/*     */ 
/*     */   private static boolean less(String paramString1, String paramString2, int paramInt)
/*     */   {
/*  92 */     assert (paramString1.substring(0, paramInt).equals(paramString2.substring(0, paramInt)));
/*  93 */     for (int i = paramInt; i < Math.min(paramString1.length(), paramString2.length()); i++) {
/*  94 */       if (paramString1.charAt(i) < paramString2.charAt(i)) return true;
/*  95 */       if (paramString1.charAt(i) > paramString2.charAt(i)) return false;
/*     */     }
/*  97 */     return paramString1.length() < paramString2.length();
/*     */   }
/*     */ 
/*     */   private static boolean isSorted(String[] paramArrayOfString)
/*     */   {
/* 102 */     for (int i = 1; i < paramArrayOfString.length; i++)
/* 103 */       if (paramArrayOfString[i].compareTo(paramArrayOfString[(i - 1)]) < 0) return false;
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 111 */     String[] arrayOfString = StdIn.readAllStrings();
/* 112 */     int i = arrayOfString.length;
/*     */ 
/* 115 */     sort(arrayOfString);
/*     */ 
/* 118 */     for (int j = 0; j < i; j++)
/* 119 */       StdOut.println(arrayOfString[j]);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Quick3string
 * JD-Core Version:    0.6.2
 */