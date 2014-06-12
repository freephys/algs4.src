/*     */ public class SuffixArrayX
/*     */ {
/*     */   private static final int CUTOFF = 5;
/*     */   private final char[] text;
/*     */   private final int[] index;
/*     */   private final int N;
/*     */ 
/*     */   public SuffixArrayX(String paramString)
/*     */   {
/*  62 */     this.N = paramString.length();
/*  63 */     paramString = paramString + '\000';
/*  64 */     this.text = paramString.toCharArray();
/*  65 */     this.index = new int[this.N];
/*  66 */     for (int i = 0; i < this.N; i++) {
/*  67 */       this.index[i] = i;
/*     */     }
/*     */ 
/*  71 */     sort(0, this.N - 1, 0);
/*     */   }
/*     */ 
/*     */   private void sort(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  78 */     if (paramInt2 <= paramInt1 + 5) {
/*  79 */       insertion(paramInt1, paramInt2, paramInt3);
/*  80 */       return;
/*     */     }
/*     */ 
/*  83 */     int i = paramInt1; int j = paramInt2;
/*  84 */     int k = this.text[(this.index[paramInt1] + paramInt3)];
/*  85 */     int m = paramInt1 + 1;
/*  86 */     while (m <= j) {
/*  87 */       int n = this.text[(this.index[m] + paramInt3)];
/*  88 */       if (n < k) exch(i++, m++);
/*  89 */       else if (n > k) exch(m, j--); else {
/*  90 */         m++;
/*     */       }
/*     */     }
/*     */ 
/*  94 */     sort(paramInt1, i - 1, paramInt3);
/*  95 */     if (k > 0) sort(i, j, paramInt3 + 1);
/*  96 */     sort(j + 1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   private void insertion(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 101 */     for (int i = paramInt1; i <= paramInt2; i++)
/* 102 */       for (int j = i; (j > paramInt1) && (less(this.index[j], this.index[(j - 1)], paramInt3)); j--)
/* 103 */         exch(j, j - 1);
/*     */   }
/*     */ 
/*     */   private boolean less(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 108 */     if (paramInt1 == paramInt2) return false;
/* 109 */     paramInt1 += paramInt3;
/* 110 */     paramInt2 += paramInt3;
/* 111 */     while ((paramInt1 < this.N) && (paramInt2 < this.N)) {
/* 112 */       if (this.text[paramInt1] < this.text[paramInt2]) return true;
/* 113 */       if (this.text[paramInt1] > this.text[paramInt2]) return false;
/* 114 */       paramInt1++;
/* 115 */       paramInt2++;
/*     */     }
/* 117 */     return paramInt1 > paramInt2;
/*     */   }
/*     */ 
/*     */   private void exch(int paramInt1, int paramInt2)
/*     */   {
/* 122 */     int i = this.index[paramInt1];
/* 123 */     this.index[paramInt1] = this.index[paramInt2];
/* 124 */     this.index[paramInt2] = i;
/*     */   }
/*     */ 
/*     */   public int length()
/*     */   {
/* 132 */     return this.N;
/*     */   }
/*     */ 
/*     */   public int index(int paramInt)
/*     */   {
/* 144 */     if ((paramInt < 0) || (paramInt >= this.N)) throw new IndexOutOfBoundsException();
/* 145 */     return this.index[paramInt];
/*     */   }
/*     */ 
/*     */   public int lcp(int paramInt)
/*     */   {
/* 157 */     if ((paramInt < 1) || (paramInt >= this.N)) throw new IndexOutOfBoundsException();
/* 158 */     return lcp(this.index[paramInt], this.index[(paramInt - 1)]);
/*     */   }
/*     */ 
/*     */   private int lcp(int paramInt1, int paramInt2)
/*     */   {
/* 163 */     int i = 0;
/* 164 */     while ((paramInt1 < this.N) && (paramInt2 < this.N)) {
/* 165 */       if (this.text[paramInt1] != this.text[paramInt2]) return i;
/* 166 */       paramInt1++;
/* 167 */       paramInt2++;
/* 168 */       i++;
/*     */     }
/* 170 */     return i;
/*     */   }
/*     */ 
/*     */   public String select(int paramInt)
/*     */   {
/* 180 */     if ((paramInt < 0) || (paramInt >= this.N)) throw new IndexOutOfBoundsException();
/* 181 */     return new String(this.text, this.index[paramInt], this.N - this.index[paramInt]);
/*     */   }
/*     */ 
/*     */   public int rank(String paramString)
/*     */   {
/* 192 */     int i = 0; int j = this.N - 1;
/* 193 */     while (i <= j) {
/* 194 */       int k = i + (j - i) / 2;
/* 195 */       int m = compare(paramString, this.index[k]);
/* 196 */       if (m < 0) j = k - 1;
/* 197 */       else if (m > 0) i = k + 1; else
/* 198 */         return k;
/*     */     }
/* 200 */     return i;
/*     */   }
/*     */ 
/*     */   private int compare(String paramString, int paramInt)
/*     */   {
/* 205 */     int i = paramString.length();
/* 206 */     int j = 0;
/* 207 */     while ((paramInt < this.N) && (j < i)) {
/* 208 */       if (paramString.charAt(j) != this.text[paramInt]) return paramString.charAt(j) - this.text[paramInt];
/* 209 */       paramInt++;
/* 210 */       j++;
/*     */     }
/*     */ 
/* 213 */     if (paramInt < this.N) return -1;
/* 214 */     if (j < i) return 1;
/* 215 */     return 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 223 */     String str1 = StdIn.readAll().replaceAll("\n", " ").trim();
/* 224 */     SuffixArrayX localSuffixArrayX = new SuffixArrayX(str1);
/*     */ 
/* 226 */     SuffixArray localSuffixArray = new SuffixArray(str1);
/* 227 */     int i = 1;
/*     */     String str3;
/* 228 */     for (int j = 0; (i != 0) && (j < str1.length()); j++) {
/* 229 */       if (localSuffixArray.index(j) != localSuffixArrayX.index(j)) {
/* 230 */         StdOut.println("suffixReference(" + j + ") = " + localSuffixArray.index(j));
/* 231 */         StdOut.println("suffix(" + j + ") = " + localSuffixArrayX.index(j));
/* 232 */         String str2 = "\"" + str1.substring(localSuffixArrayX.index(j), Math.min(localSuffixArrayX.index(j) + 50, str1.length())) + "\"";
/* 233 */         str3 = "\"" + str1.substring(localSuffixArray.index(j), Math.min(localSuffixArray.index(j) + 50, str1.length())) + "\"";
/* 234 */         StdOut.println(str2);
/* 235 */         StdOut.println(str3);
/* 236 */         i = 0;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 242 */     StdOut.println("  i ind lcp rnk  select");
/* 243 */     StdOut.println("---------------------------");
/*     */ 
/* 245 */     for (j = 0; j < str1.length(); j++) {
/* 246 */       int k = localSuffixArrayX.index(j);
/* 247 */       str3 = "\"" + str1.substring(k, Math.min(k + 50, str1.length())) + "\"";
/* 248 */       int m = localSuffixArrayX.rank(str1.substring(k));
/* 249 */       assert (str1.substring(k).equals(localSuffixArrayX.select(j)));
/* 250 */       if (j == 0) {
/* 251 */         StdOut.printf("%3d %3d %3s %3d  %s\n", new Object[] { Integer.valueOf(j), Integer.valueOf(k), "-", Integer.valueOf(m), str3 });
/*     */       }
/*     */       else
/*     */       {
/* 255 */         int n = localSuffixArrayX.lcp(j);
/* 256 */         StdOut.printf("%3d %3d %3d %3d  %s\n", new Object[] { Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(n), Integer.valueOf(m), str3 });
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SuffixArrayX
 * JD-Core Version:    0.6.2
 */