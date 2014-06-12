/*     */ public class Alphabet
/*     */ {
/*  15 */   public static final Alphabet BINARY = new Alphabet("01");
/*  16 */   public static final Alphabet OCTAL = new Alphabet("01234567");
/*  17 */   public static final Alphabet DECIMAL = new Alphabet("0123456789");
/*  18 */   public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
/*  19 */   public static final Alphabet DNA = new Alphabet("ACTG");
/*  20 */   public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
/*  21 */   public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
/*  22 */   public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
/*  23 */   public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
/*  24 */   public static final Alphabet ASCII = new Alphabet(128);
/*  25 */   public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
/*  26 */   public static final Alphabet UNICODE16 = new Alphabet(65536);
/*     */   private char[] alphabet;
/*     */   private int[] inverse;
/*     */   private int R;
/*     */ 
/*     */   public Alphabet(String paramString)
/*     */   {
/*  36 */     boolean[] arrayOfBoolean = new boolean[65535];
/*  37 */     for (int i = 0; i < paramString.length(); i++) {
/*  38 */       char c = paramString.charAt(i);
/*  39 */       if (arrayOfBoolean[c] != 0)
/*  40 */         throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
/*  41 */       arrayOfBoolean[c] = true;
/*     */     }
/*     */ 
/*  44 */     this.alphabet = paramString.toCharArray();
/*  45 */     this.R = paramString.length();
/*  46 */     this.inverse = new int[65535];
/*  47 */     for (i = 0; i < this.inverse.length; i++) {
/*  48 */       this.inverse[i] = -1;
/*     */     }
/*     */ 
/*  51 */     for (i = 0; i < this.R; i++)
/*  52 */       this.inverse[this.alphabet[i]] = i;
/*     */   }
/*     */ 
/*     */   private Alphabet(int paramInt)
/*     */   {
/*  57 */     this.alphabet = new char[paramInt];
/*  58 */     this.inverse = new int[paramInt];
/*  59 */     this.R = paramInt;
/*     */ 
/*  62 */     for (int i = 0; i < paramInt; i++)
/*  63 */       this.alphabet[i] = ((char)i);
/*  64 */     for (i = 0; i < paramInt; i++)
/*  65 */       this.inverse[i] = i;
/*     */   }
/*     */ 
/*     */   public Alphabet()
/*     */   {
/*  70 */     this(256);
/*     */   }
/*     */ 
/*     */   public boolean contains(char paramChar)
/*     */   {
/*  75 */     return this.inverse[paramChar] != -1;
/*     */   }
/*     */ 
/*     */   public int R()
/*     */   {
/*  80 */     return this.R;
/*     */   }
/*     */ 
/*     */   public int lgR()
/*     */   {
/*  85 */     int i = 0;
/*  86 */     for (int j = this.R - 1; j >= 1; j /= 2)
/*  87 */       i++;
/*  88 */     return i;
/*     */   }
/*     */ 
/*     */   public int toIndex(char paramChar)
/*     */   {
/*  93 */     if ((paramChar < 0) || (paramChar >= this.inverse.length) || (this.inverse[paramChar] == -1)) {
/*  94 */       throw new IllegalArgumentException("Character " + paramChar + " not in alphabet");
/*     */     }
/*  96 */     return this.inverse[paramChar];
/*     */   }
/*     */ 
/*     */   public int[] toIndices(String paramString)
/*     */   {
/* 101 */     char[] arrayOfChar = paramString.toCharArray();
/* 102 */     int[] arrayOfInt = new int[paramString.length()];
/* 103 */     for (int i = 0; i < arrayOfChar.length; i++)
/* 104 */       arrayOfInt[i] = toIndex(arrayOfChar[i]);
/* 105 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   public char toChar(int paramInt)
/*     */   {
/* 110 */     if ((paramInt < 0) || (paramInt >= this.R)) {
/* 111 */       throw new IndexOutOfBoundsException("Alphabet index out of bounds");
/*     */     }
/* 113 */     return this.alphabet[paramInt];
/*     */   }
/*     */ 
/*     */   public String toChars(int[] paramArrayOfInt)
/*     */   {
/* 118 */     StringBuilder localStringBuilder = new StringBuilder(paramArrayOfInt.length);
/* 119 */     for (int i = 0; i < paramArrayOfInt.length; i++)
/* 120 */       localStringBuilder.append(toChar(paramArrayOfInt[i]));
/* 121 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 126 */     int[] arrayOfInt1 = BASE64.toIndices("NowIsTheTimeForAllGoodMen");
/* 127 */     String str1 = BASE64.toChars(arrayOfInt1);
/* 128 */     StdOut.println(str1);
/*     */ 
/* 130 */     int[] arrayOfInt2 = DNA.toIndices("AACGAACGGTTTACCCCG");
/* 131 */     String str2 = DNA.toChars(arrayOfInt2);
/* 132 */     StdOut.println(str2);
/*     */ 
/* 134 */     int[] arrayOfInt3 = DECIMAL.toIndices("01234567890123456789");
/* 135 */     String str3 = DECIMAL.toChars(arrayOfInt3);
/* 136 */     StdOut.println(str3);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Alphabet
 * JD-Core Version:    0.6.2
 */