/*     */ public class KMP
/*     */ {
/*     */   private final int R;
/*     */   private int[][] dfa;
/*     */   private char[] pattern;
/*     */   private String pat;
/*     */ 
/*     */   public KMP(String paramString)
/*     */   {
/*  41 */     this.R = 256;
/*  42 */     this.pat = paramString;
/*     */ 
/*  45 */     int i = paramString.length();
/*  46 */     this.dfa = new int[this.R][i];
/*  47 */     this.dfa[paramString.charAt(0)][0] = 1;
/*  48 */     int j = 0; for (int k = 1; k < i; k++) {
/*  49 */       for (int m = 0; m < this.R; m++)
/*  50 */         this.dfa[m][k] = this.dfa[m][j];
/*  51 */       this.dfa[paramString.charAt(k)][k] = (k + 1);
/*  52 */       j = this.dfa[paramString.charAt(k)][j];
/*     */     }
/*     */   }
/*     */ 
/*     */   public KMP(char[] paramArrayOfChar, int paramInt)
/*     */   {
/*  58 */     this.R = paramInt;
/*  59 */     this.pattern = new char[paramArrayOfChar.length];
/*  60 */     for (int i = 0; i < paramArrayOfChar.length; i++) {
/*  61 */       this.pattern[i] = paramArrayOfChar[i];
/*     */     }
/*     */ 
/*  64 */     i = paramArrayOfChar.length;
/*  65 */     this.dfa = new int[paramInt][i];
/*  66 */     this.dfa[paramArrayOfChar[0]][0] = 1;
/*  67 */     int j = 0; for (int k = 1; k < i; k++) {
/*  68 */       for (int m = 0; m < paramInt; m++)
/*  69 */         this.dfa[m][k] = this.dfa[m][j];
/*  70 */       this.dfa[paramArrayOfChar[k]][k] = (k + 1);
/*  71 */       j = this.dfa[paramArrayOfChar[k]][j];
/*     */     }
/*     */   }
/*     */ 
/*     */   public int search(String paramString)
/*     */   {
/*  79 */     int i = this.pat.length();
/*  80 */     int j = paramString.length();
/*     */ 
/*  82 */     int k = 0; for (int m = 0; (k < j) && (m < i); k++) {
/*  83 */       m = this.dfa[paramString.charAt(k)][m];
/*     */     }
/*  85 */     if (m == i) return k - i;
/*  86 */     return j;
/*     */   }
/*     */ 
/*     */   public int search(char[] paramArrayOfChar)
/*     */   {
/*  94 */     int i = this.pattern.length;
/*  95 */     int j = paramArrayOfChar.length;
/*     */ 
/*  97 */     int k = 0; for (int m = 0; (k < j) && (m < i); k++) {
/*  98 */       m = this.dfa[paramArrayOfChar[k]][m];
/*     */     }
/* 100 */     if (m == i) return k - i;
/* 101 */     return j;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 107 */     String str1 = paramArrayOfString[0];
/* 108 */     String str2 = paramArrayOfString[1];
/* 109 */     char[] arrayOfChar1 = str1.toCharArray();
/* 110 */     char[] arrayOfChar2 = str2.toCharArray();
/*     */ 
/* 112 */     KMP localKMP1 = new KMP(str1);
/* 113 */     int i = localKMP1.search(str2);
/*     */ 
/* 115 */     KMP localKMP2 = new KMP(arrayOfChar1, 256);
/* 116 */     int j = localKMP2.search(arrayOfChar2);
/*     */ 
/* 119 */     StdOut.println("text:    " + str2);
/*     */ 
/* 121 */     StdOut.print("pattern: ");
/* 122 */     for (int k = 0; k < i; k++)
/* 123 */       StdOut.print(" ");
/* 124 */     StdOut.println(str1);
/*     */ 
/* 126 */     StdOut.print("pattern: ");
/* 127 */     for (k = 0; k < j; k++)
/* 128 */       StdOut.print(" ");
/* 129 */     StdOut.println(str1);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     KMP
 * JD-Core Version:    0.6.2
 */