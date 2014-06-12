/*     */ public class Complex
/*     */ {
/*     */   private final double re;
/*     */   private final double im;
/*     */ 
/*     */   public Complex(double paramDouble1, double paramDouble2)
/*     */   {
/*  36 */     this.re = paramDouble1;
/*  37 */     this.im = paramDouble2;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  42 */     if (this.im == 0.0D) return this.re + "";
/*  43 */     if (this.re == 0.0D) return this.im + "i";
/*  44 */     if (this.im < 0.0D) return this.re + " - " + -this.im + "i";
/*  45 */     return this.re + " + " + this.im + "i";
/*     */   }
/*     */ 
/*     */   public double abs() {
/*  49 */     return Math.hypot(this.re, this.im); } 
/*  50 */   public double phase() { return Math.atan2(this.im, this.re); }
/*     */ 
/*     */   public Complex plus(Complex paramComplex)
/*     */   {
/*  54 */     Complex localComplex = this;
/*  55 */     double d1 = localComplex.re + paramComplex.re;
/*  56 */     double d2 = localComplex.im + paramComplex.im;
/*  57 */     return new Complex(d1, d2);
/*     */   }
/*     */ 
/*     */   public Complex minus(Complex paramComplex)
/*     */   {
/*  62 */     Complex localComplex = this;
/*  63 */     double d1 = localComplex.re - paramComplex.re;
/*  64 */     double d2 = localComplex.im - paramComplex.im;
/*  65 */     return new Complex(d1, d2);
/*     */   }
/*     */ 
/*     */   public Complex times(Complex paramComplex)
/*     */   {
/*  70 */     Complex localComplex = this;
/*  71 */     double d1 = localComplex.re * paramComplex.re - localComplex.im * paramComplex.im;
/*  72 */     double d2 = localComplex.re * paramComplex.im + localComplex.im * paramComplex.re;
/*  73 */     return new Complex(d1, d2);
/*     */   }
/*     */ 
/*     */   public Complex times(double paramDouble)
/*     */   {
/*  79 */     return new Complex(paramDouble * this.re, paramDouble * this.im);
/*     */   }
/*     */ 
/*     */   public Complex conjugate() {
/*  83 */     return new Complex(this.re, -this.im);
/*     */   }
/*     */ 
/*     */   public Complex reciprocal() {
/*  87 */     double d = this.re * this.re + this.im * this.im;
/*  88 */     return new Complex(this.re / d, -this.im / d);
/*     */   }
/*     */ 
/*     */   public double re() {
/*  92 */     return this.re; } 
/*  93 */   public double im() { return this.im; }
/*     */ 
/*     */   public Complex divides(Complex paramComplex)
/*     */   {
/*  97 */     Complex localComplex = this;
/*  98 */     return localComplex.times(paramComplex.reciprocal());
/*     */   }
/*     */ 
/*     */   public Complex exp()
/*     */   {
/* 103 */     return new Complex(Math.exp(this.re) * Math.cos(this.im), Math.exp(this.re) * Math.sin(this.im));
/*     */   }
/*     */ 
/*     */   public Complex sin()
/*     */   {
/* 108 */     return new Complex(Math.sin(this.re) * Math.cosh(this.im), Math.cos(this.re) * Math.sinh(this.im));
/*     */   }
/*     */ 
/*     */   public Complex cos()
/*     */   {
/* 113 */     return new Complex(Math.cos(this.re) * Math.cosh(this.im), -Math.sin(this.re) * Math.sinh(this.im));
/*     */   }
/*     */ 
/*     */   public Complex tan()
/*     */   {
/* 118 */     return sin().divides(cos());
/*     */   }
/*     */ 
/*     */   public static Complex plus(Complex paramComplex1, Complex paramComplex2)
/*     */   {
/* 125 */     double d1 = paramComplex1.re + paramComplex2.re;
/* 126 */     double d2 = paramComplex1.im + paramComplex2.im;
/* 127 */     Complex localComplex = new Complex(d1, d2);
/* 128 */     return localComplex;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 135 */     Complex localComplex1 = new Complex(5.0D, 6.0D);
/* 136 */     Complex localComplex2 = new Complex(-3.0D, 4.0D);
/*     */ 
/* 138 */     StdOut.println("a            = " + localComplex1);
/* 139 */     StdOut.println("b            = " + localComplex2);
/* 140 */     StdOut.println("Re(a)        = " + localComplex1.re());
/* 141 */     StdOut.println("Im(a)        = " + localComplex1.im());
/* 142 */     StdOut.println("b + a        = " + localComplex2.plus(localComplex1));
/* 143 */     StdOut.println("a - b        = " + localComplex1.minus(localComplex2));
/* 144 */     StdOut.println("a * b        = " + localComplex1.times(localComplex2));
/* 145 */     StdOut.println("b * a        = " + localComplex2.times(localComplex1));
/* 146 */     StdOut.println("a / b        = " + localComplex1.divides(localComplex2));
/* 147 */     StdOut.println("(a / b) * b  = " + localComplex1.divides(localComplex2).times(localComplex2));
/* 148 */     StdOut.println("conj(a)      = " + localComplex1.conjugate());
/* 149 */     StdOut.println("|a|          = " + localComplex1.abs());
/* 150 */     StdOut.println("tan(a)       = " + localComplex1.tan());
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Complex
 * JD-Core Version:    0.6.2
 */