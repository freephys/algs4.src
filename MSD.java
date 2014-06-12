/*     */ public class MSD
/*     */ {
/*     */   private static final int R = 256;
/*     */   private static final int CUTOFF = 15;
/*     */ 
/*     */   public static void sort(String[] paramArrayOfString)
/*     */   {
/*  31 */     int i = paramArrayOfString.length;
/*  32 */     String[] arrayOfString = new String[i];
/*  33 */     sort(paramArrayOfString, 0, i - 1, 0, arrayOfString);
/*     */   }
/*     */ 
/*     */   private static int charAt(String paramString, int paramInt)
/*     */   {
/*  38 */     assert ((paramInt >= 0) && (paramInt <= paramString.length()));
/*  39 */     if (paramInt == paramString.length()) return -1;
/*  40 */     return paramString.charAt(paramInt);
/*     */   }
/*     */ 
/*     */   private static void sort(String[] paramArrayOfString1, int paramInt1, int paramInt2, int paramInt3, String[] paramArrayOfString2)
/*     */   {
/*  47 */     if (paramInt2 <= paramInt1 + 15) {
/*  48 */       insertion(paramArrayOfString1, paramInt1, paramInt2, paramInt3);
/*  49 */       return;
/*     */     }
/*     */ 
/*  53 */     int[] arrayOfInt = new int[258];
/*     */     int j;
/*  54 */     for (int i = paramInt1; i <= paramInt2; i++) {
/*  55 */       j = charAt(paramArrayOfString1[i], paramInt3);
/*  56 */       arrayOfInt[(j + 2)] += 1;
/*     */     }
/*     */ 
/*  60 */     for (i = 0; i < 257; i++) {
/*  61 */       arrayOfInt[(i + 1)] += arrayOfInt[i];
/*     */     }
/*     */ 
/*  64 */     for (i = paramInt1; i <= paramInt2; i++) {
/*  65 */       j = charAt(paramArrayOfString1[i], paramInt3);
/*     */       int tmp118_117 = (j + 1);
/*     */       int[] tmp118_112 = arrayOfInt;
/*     */       int tmp120_119 = tmp118_112[tmp118_117]; tmp118_112[tmp118_117] = (tmp120_119 + 1); paramArrayOfString2[tmp120_119] = paramArrayOfString1[i];
/*     */     }
/*     */ 
/*  70 */     for (i = paramInt1; i <= paramInt2; i++) {
/*  71 */       paramArrayOfString1[i] = paramArrayOfString2[(i - paramInt1)];
/*     */     }
/*     */ 
/*  75 */     for (i = 0; i < 256; i++)
/*  76 */       sort(paramArrayOfString1, paramInt1 + arrayOfInt[i], paramInt1 + arrayOfInt[(i + 1)] - 1, paramInt3 + 1, paramArrayOfString2);
/*     */   }
/*     */ 
/*     */   private static void insertion(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     for (int i = paramInt1; i <= paramInt2; i++)
/*  83 */       for (int j = i; (j > paramInt1) && (less(paramArrayOfString[j], paramArrayOfString[(j - 1)], paramInt3)); j--)
/*  84 */         exch(paramArrayOfString, j, j - 1);
/*     */   }
/*     */ 
/*     */   private static void exch(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/*  89 */     String str = paramArrayOfString[paramInt1];
/*  90 */     paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/*  91 */     paramArrayOfString[paramInt2] = str;
/*     */   }
/*     */ 
/*     */   private static boolean less(String paramString1, String paramString2, int paramInt)
/*     */   {
/* 103 */     assert (paramString1.substring(0, paramInt).equals(paramString2.substring(0, paramInt)));
/* 104 */     for (int i = paramInt; i < Math.min(paramString1.length(), paramString2.length()); i++) {
/* 105 */       if (paramString1.charAt(i) < paramString2.charAt(i)) return true;
/* 106 */       if (paramString1.charAt(i) > paramString2.charAt(i)) return false;
/*     */     }
/* 108 */     return paramString1.length() < paramString2.length();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 113 */     String[] arrayOfString = StdIn.readAllStrings();
/* 114 */     int i = arrayOfString.length;
/* 115 */     sort(arrayOfString);
/* 116 */     for (int j = 0; j < i; j++)
/* 117 */       StdOut.println(arrayOfString[j]);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     MSD
 * JD-Core Version:    0.6.2
 */