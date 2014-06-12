/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class SuffixArray
/*     */ {
/*     */   private SuffixArray.Suffix[] suffixes;
/*     */ 
/*     */   public SuffixArray(String paramString)
/*     */   {
/*  63 */     int i = paramString.length();
/*  64 */     this.suffixes = new SuffixArray.Suffix[i];
/*  65 */     for (int j = 0; j < i; j++)
/*  66 */       this.suffixes[j] = new SuffixArray.Suffix(paramString, j, null);
/*  67 */     Arrays.sort(this.suffixes);
/*     */   }
/*     */ 
/*     */   public int length()
/*     */   {
/* 105 */     return this.suffixes.length;
/*     */   }
/*     */ 
/*     */   public int index(int paramInt)
/*     */   {
/* 117 */     if ((paramInt < 0) || (paramInt >= this.suffixes.length)) throw new IndexOutOfBoundsException();
/* 118 */     return this.suffixes[paramInt].index;
/*     */   }
/*     */ 
/*     */   public int lcp(int paramInt)
/*     */   {
/* 131 */     if ((paramInt < 1) || (paramInt >= this.suffixes.length)) throw new IndexOutOfBoundsException();
/* 132 */     return lcp(this.suffixes[paramInt], this.suffixes[(paramInt - 1)]);
/*     */   }
/*     */ 
/*     */   private static int lcp(SuffixArray.Suffix paramSuffix1, SuffixArray.Suffix paramSuffix2)
/*     */   {
/* 137 */     int i = Math.min(paramSuffix1.length(), paramSuffix2.length());
/* 138 */     for (int j = 0; j < i; j++) {
/* 139 */       if (paramSuffix1.charAt(j) != paramSuffix2.charAt(j)) return j;
/*     */     }
/* 141 */     return i;
/*     */   }
/*     */ 
/*     */   public String select(int paramInt)
/*     */   {
/* 151 */     if ((paramInt < 0) || (paramInt >= this.suffixes.length)) throw new IndexOutOfBoundsException();
/* 152 */     return this.suffixes[paramInt].toString();
/*     */   }
/*     */ 
/*     */   public int rank(String paramString)
/*     */   {
/* 163 */     int i = 0; int j = this.suffixes.length - 1;
/* 164 */     while (i <= j) {
/* 165 */       int k = i + (j - i) / 2;
/* 166 */       int m = compare(paramString, this.suffixes[k]);
/* 167 */       if (m < 0) j = k - 1;
/* 168 */       else if (m > 0) i = k + 1; else
/* 169 */         return k;
/*     */     }
/* 171 */     return i;
/*     */   }
/*     */ 
/*     */   private static int compare(String paramString, SuffixArray.Suffix paramSuffix)
/*     */   {
/* 176 */     int i = Math.min(paramString.length(), paramSuffix.length());
/* 177 */     for (int j = 0; j < i; j++) {
/* 178 */       if (paramString.charAt(j) < paramSuffix.charAt(j)) return -1;
/* 179 */       if (paramString.charAt(j) > paramSuffix.charAt(j)) return 1;
/*     */     }
/* 181 */     return paramString.length() - paramSuffix.length();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 188 */     String str1 = StdIn.readAll().replaceAll("\\s+", " ").trim();
/* 189 */     SuffixArray localSuffixArray = new SuffixArray(str1);
/*     */ 
/* 193 */     StdOut.println("  i ind lcp rnk select");
/* 194 */     StdOut.println("---------------------------");
/*     */ 
/* 196 */     for (int i = 0; i < str1.length(); i++) {
/* 197 */       int j = localSuffixArray.index(i);
/* 198 */       String str2 = "\"" + str1.substring(j, Math.min(j + 50, str1.length())) + "\"";
/* 199 */       assert (str1.substring(j).equals(localSuffixArray.select(i)));
/* 200 */       int k = localSuffixArray.rank(str1.substring(j));
/* 201 */       if (i == 0) {
/* 202 */         StdOut.printf("%3d %3d %3s %3d %s\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j), "-", Integer.valueOf(k), str2 });
/*     */       }
/*     */       else {
/* 205 */         int m = localSuffixArray.lcp(i);
/* 206 */         StdOut.printf("%3d %3d %3d %3d %s\n", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(m), Integer.valueOf(k), str2 });
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Suffix
/*     */     implements Comparable<Suffix>
/*     */   {
/*     */     private final String text;
/*     */     private final int index;
/*     */ 
/*     */     private Suffix(String paramString, int paramInt)
/*     */     {
/*  75 */       this.text = paramString;
/*  76 */       this.index = paramInt;
/*     */     }
/*     */     private int length() {
/*  79 */       return this.text.length() - this.index;
/*     */     }
/*     */     private char charAt(int paramInt) {
/*  82 */       return this.text.charAt(this.index + paramInt);
/*     */     }
/*     */ 
/*     */     public int compareTo(Suffix paramSuffix) {
/*  86 */       if (this == paramSuffix) return 0;
/*  87 */       int i = Math.min(length(), paramSuffix.length());
/*  88 */       for (int j = 0; j < i; j++) {
/*  89 */         if (charAt(j) < paramSuffix.charAt(j)) return -1;
/*  90 */         if (charAt(j) > paramSuffix.charAt(j)) return 1;
/*     */       }
/*  92 */       return length() - paramSuffix.length();
/*     */     }
/*     */ 
/*     */     public String toString() {
/*  96 */       return this.text.substring(this.index);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SuffixArray
 * JD-Core Version:    0.6.2
 */