/*     */ public class BoyerMoore
/*     */ {
/*     */   private final int R;
/*     */   private int[] right;
/*     */   private char[] pattern;
/*     */   private String pat;
/*     */ 
/*     */   public BoyerMoore(String paramString)
/*     */   {
/*  41 */     this.R = 256;
/*  42 */     this.pat = paramString;
/*     */ 
/*  45 */     this.right = new int[this.R];
/*  46 */     for (int i = 0; i < this.R; i++)
/*  47 */       this.right[i] = -1;
/*  48 */     for (i = 0; i < paramString.length(); i++)
/*  49 */       this.right[paramString.charAt(i)] = i;
/*     */   }
/*     */ 
/*     */   public BoyerMoore(char[] paramArrayOfChar, int paramInt)
/*     */   {
/*  54 */     this.R = paramInt;
/*  55 */     this.pattern = new char[paramArrayOfChar.length];
/*  56 */     for (int i = 0; i < paramArrayOfChar.length; i++) {
/*  57 */       this.pattern[i] = paramArrayOfChar[i];
/*     */     }
/*     */ 
/*  60 */     this.right = new int[paramInt];
/*  61 */     for (i = 0; i < paramInt; i++)
/*  62 */       this.right[i] = -1;
/*  63 */     for (i = 0; i < paramArrayOfChar.length; i++)
/*  64 */       this.right[paramArrayOfChar[i]] = i;
/*     */   }
/*     */ 
/*     */   public int search(String paramString)
/*     */   {
/*  69 */     int i = this.pat.length();
/*  70 */     int j = paramString.length();
/*     */     int k;
/*  72 */     for (int m = 0; m <= j - i; m += k) {
/*  73 */       k = 0;
/*  74 */       for (int n = i - 1; n >= 0; n--) {
/*  75 */         if (this.pat.charAt(n) != paramString.charAt(m + n)) {
/*  76 */           k = Math.max(1, n - this.right[paramString.charAt(m + n)]);
/*  77 */           break;
/*     */         }
/*     */       }
/*  80 */       if (k == 0) return m;
/*     */     }
/*  82 */     return j;
/*     */   }
/*     */ 
/*     */   public int search(char[] paramArrayOfChar)
/*     */   {
/*  88 */     int i = this.pattern.length;
/*  89 */     int j = paramArrayOfChar.length;
/*     */     int k;
/*  91 */     for (int m = 0; m <= j - i; m += k) {
/*  92 */       k = 0;
/*  93 */       for (int n = i - 1; n >= 0; n--) {
/*  94 */         if (this.pattern[n] != paramArrayOfChar[(m + n)]) {
/*  95 */           k = Math.max(1, n - this.right[paramArrayOfChar[(m + n)]]);
/*  96 */           break;
/*     */         }
/*     */       }
/*  99 */       if (k == 0) return m;
/*     */     }
/* 101 */     return j;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 108 */     String str1 = paramArrayOfString[0];
/* 109 */     String str2 = paramArrayOfString[1];
/* 110 */     char[] arrayOfChar1 = str1.toCharArray();
/* 111 */     char[] arrayOfChar2 = str2.toCharArray();
/*     */ 
/* 113 */     BoyerMoore localBoyerMoore1 = new BoyerMoore(str1);
/* 114 */     BoyerMoore localBoyerMoore2 = new BoyerMoore(arrayOfChar1, 256);
/* 115 */     int i = localBoyerMoore1.search(str2);
/* 116 */     int j = localBoyerMoore2.search(arrayOfChar2);
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
 * Qualified Name:     BoyerMoore
 * JD-Core Version:    0.6.2
 */