/*     */ public class Vector
/*     */ {
/*     */   private int N;
/*     */   private double[] data;
/*     */ 
/*     */   public Vector(int paramInt)
/*     */   {
/*  49 */     this.N = paramInt;
/*  50 */     this.data = new double[this.N];
/*     */   }
/*     */ 
/*     */   public Vector(double[] paramArrayOfDouble)
/*     */   {
/*  60 */     this.N = paramArrayOfDouble.length;
/*     */ 
/*  63 */     this.data = new double[this.N];
/*  64 */     for (int i = 0; i < this.N; i++)
/*  65 */       this.data[i] = paramArrayOfDouble[i];
/*     */   }
/*     */ 
/*     */   public int length()
/*     */   {
/*  73 */     return this.N;
/*     */   }
/*     */ 
/*     */   public double dot(Vector paramVector)
/*     */   {
/*  83 */     if (this.N != paramVector.N) throw new IllegalArgumentException("Dimensions don't agree");
/*  84 */     double d = 0.0D;
/*  85 */     for (int i = 0; i < this.N; i++)
/*  86 */       d += this.data[i] * paramVector.data[i];
/*  87 */     return d;
/*     */   }
/*     */ 
/*     */   public double magnitude()
/*     */   {
/*  95 */     return Math.sqrt(dot(this));
/*     */   }
/*     */ 
/*     */   public double distanceTo(Vector paramVector)
/*     */   {
/* 105 */     if (this.N != paramVector.N) throw new IllegalArgumentException("Dimensions don't agree");
/* 106 */     return minus(paramVector).magnitude();
/*     */   }
/*     */ 
/*     */   public Vector plus(Vector paramVector)
/*     */   {
/* 116 */     if (this.N != paramVector.N) throw new IllegalArgumentException("Dimensions don't agree");
/* 117 */     Vector localVector = new Vector(this.N);
/* 118 */     for (int i = 0; i < this.N; i++)
/* 119 */       this.data[i] += paramVector.data[i];
/* 120 */     return localVector;
/*     */   }
/*     */ 
/*     */   public Vector minus(Vector paramVector)
/*     */   {
/* 130 */     if (this.N != paramVector.N) throw new IllegalArgumentException("Dimensions don't agree");
/* 131 */     Vector localVector = new Vector(this.N);
/* 132 */     for (int i = 0; i < this.N; i++)
/* 133 */       this.data[i] -= paramVector.data[i];
/* 134 */     return localVector;
/*     */   }
/*     */ 
/*     */   public double cartesian(int paramInt)
/*     */   {
/* 143 */     return this.data[paramInt];
/*     */   }
/*     */ 
/*     */   public Vector times(double paramDouble)
/*     */   {
/* 152 */     Vector localVector = new Vector(this.N);
/* 153 */     for (int i = 0; i < this.N; i++)
/* 154 */       localVector.data[i] = (paramDouble * this.data[i]);
/* 155 */     return localVector;
/*     */   }
/*     */ 
/*     */   public Vector direction()
/*     */   {
/* 164 */     if (magnitude() == 0.0D) throw new ArithmeticException("Zero-vector has no direction");
/* 165 */     return times(1.0D / magnitude());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 175 */     String str = "";
/* 176 */     for (int i = 0; i < this.N; i++)
/* 177 */       str = str + this.data[i] + " ";
/* 178 */     return str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 185 */     double[] arrayOfDouble1 = { 1.0D, 2.0D, 3.0D, 4.0D };
/* 186 */     double[] arrayOfDouble2 = { 5.0D, 2.0D, 4.0D, 1.0D };
/* 187 */     Vector localVector1 = new Vector(arrayOfDouble1);
/* 188 */     Vector localVector2 = new Vector(arrayOfDouble2);
/*     */ 
/* 190 */     StdOut.println("   x       = " + localVector1);
/* 191 */     StdOut.println("   y       = " + localVector2);
/*     */ 
/* 193 */     Vector localVector3 = localVector1.plus(localVector2);
/* 194 */     StdOut.println("   z       = " + localVector3);
/*     */ 
/* 196 */     localVector3 = localVector3.times(10.0D);
/* 197 */     StdOut.println(" 10z       = " + localVector3);
/*     */ 
/* 199 */     StdOut.println("  |x|      = " + localVector1.magnitude());
/* 200 */     StdOut.println(" <x, y>    = " + localVector1.dot(localVector2));
/* 201 */     StdOut.println("dist(x, y) = " + localVector1.distanceTo(localVector2));
/* 202 */     StdOut.println("dir(x)     = " + localVector1.direction());
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Vector
 * JD-Core Version:    0.6.2
 */