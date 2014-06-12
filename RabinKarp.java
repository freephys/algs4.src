/*     */ import java.math.BigInteger;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RabinKarp
/*     */ {
/*     */   private String pat;
/*     */   private long patHash;
/*     */   private int M;
/*     */   private long Q;
/*     */   private int R;
/*     */   private long RM;
/*     */ 
/*     */   public RabinKarp(int paramInt, char[] paramArrayOfChar)
/*     */   {
/*  45 */     throw new UnsupportedOperationException("Operation not supported yet");
/*     */   }
/*     */ 
/*     */   public RabinKarp(String paramString) {
/*  49 */     this.pat = paramString;
/*  50 */     this.R = 256;
/*  51 */     this.M = paramString.length();
/*  52 */     this.Q = longRandomPrime();
/*     */ 
/*  55 */     this.RM = 1L;
/*  56 */     for (int i = 1; i <= this.M - 1; i++)
/*  57 */       this.RM = (this.R * this.RM % this.Q);
/*  58 */     this.patHash = hash(paramString, this.M);
/*     */   }
/*     */ 
/*     */   private long hash(String paramString, int paramInt)
/*     */   {
/*  63 */     long l = 0L;
/*  64 */     for (int i = 0; i < paramInt; i++)
/*  65 */       l = (this.R * l + paramString.charAt(i)) % this.Q;
/*  66 */     return l;
/*     */   }
/*     */ 
/*     */   private boolean check(String paramString, int paramInt)
/*     */   {
/*  71 */     for (int i = 0; i < this.M; i++)
/*  72 */       if (this.pat.charAt(i) != paramString.charAt(paramInt + i))
/*  73 */         return false;
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean check(int paramInt)
/*     */   {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   public int search(String paramString)
/*     */   {
/*  84 */     int i = paramString.length();
/*  85 */     if (i < this.M) return i;
/*  86 */     long l = hash(paramString, this.M);
/*     */ 
/*  89 */     if ((this.patHash == l) && (check(paramString, 0))) {
/*  90 */       return 0;
/*     */     }
/*     */ 
/*  93 */     for (int j = this.M; j < i; j++)
/*     */     {
/*  95 */       l = (l + this.Q - this.RM * paramString.charAt(j - this.M) % this.Q) % this.Q;
/*  96 */       l = (l * this.R + paramString.charAt(j)) % this.Q;
/*     */ 
/*  99 */       int k = j - this.M + 1;
/* 100 */       if ((this.patHash == l) && (check(paramString, k))) {
/* 101 */         return k;
/*     */       }
/*     */     }
/*     */ 
/* 105 */     return i;
/*     */   }
/*     */ 
/*     */   private static long longRandomPrime()
/*     */   {
/* 111 */     BigInteger localBigInteger = BigInteger.probablePrime(31, new Random());
/* 112 */     return localBigInteger.longValue();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 117 */     String str1 = paramArrayOfString[0];
/* 118 */     String str2 = paramArrayOfString[1];
/* 119 */     char[] arrayOfChar1 = str1.toCharArray();
/* 120 */     char[] arrayOfChar2 = str2.toCharArray();
/*     */ 
/* 122 */     RabinKarp localRabinKarp = new RabinKarp(str1);
/* 123 */     int i = localRabinKarp.search(str2);
/*     */ 
/* 126 */     StdOut.println("text:    " + str2);
/*     */ 
/* 129 */     StdOut.print("pattern: ");
/* 130 */     for (int j = 0; j < i; j++)
/* 131 */       StdOut.print(" ");
/* 132 */     StdOut.println(str1);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     RabinKarp
 * JD-Core Version:    0.6.2
 */