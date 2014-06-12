/*     */ public class Interval2D
/*     */ {
/*     */   private final Interval1D x;
/*     */   private final Interval1D y;
/*     */ 
/*     */   public Interval2D(Interval1D paramInterval1D1, Interval1D paramInterval1D2)
/*     */   {
/*  35 */     this.x = paramInterval1D1;
/*  36 */     this.y = paramInterval1D2;
/*     */   }
/*     */ 
/*     */   public boolean intersects(Interval2D paramInterval2D)
/*     */   {
/*  46 */     if (!this.x.intersects(paramInterval2D.x)) return false;
/*  47 */     if (!this.y.intersects(paramInterval2D.y)) return false;
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean contains(Point2D paramPoint2D)
/*     */   {
/*  57 */     return (this.x.contains(paramPoint2D.x())) && (this.y.contains(paramPoint2D.y()));
/*     */   }
/*     */ 
/*     */   public double area()
/*     */   {
/*  65 */     return this.x.length() * this.y.length();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  74 */     return this.x + " x " + this.y;
/*     */   }
/*     */ 
/*     */   public void draw()
/*     */   {
/*  81 */     double d1 = (this.x.left() + this.x.right()) / 2.0D;
/*  82 */     double d2 = (this.y.left() + this.y.right()) / 2.0D;
/*  83 */     StdDraw.rectangle(d1, d2, this.x.length() / 2.0D, this.y.length() / 2.0D);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  90 */     double d1 = Double.parseDouble(paramArrayOfString[0]);
/*  91 */     double d2 = Double.parseDouble(paramArrayOfString[1]);
/*  92 */     double d3 = Double.parseDouble(paramArrayOfString[2]);
/*  93 */     double d4 = Double.parseDouble(paramArrayOfString[3]);
/*  94 */     int i = Integer.parseInt(paramArrayOfString[4]);
/*     */ 
/*  96 */     Interval1D localInterval1D1 = new Interval1D(d1, d2);
/*  97 */     Interval1D localInterval1D2 = new Interval1D(d3, d4);
/*  98 */     Interval2D localInterval2D = new Interval2D(localInterval1D1, localInterval1D2);
/*  99 */     localInterval2D.draw();
/*     */ 
/* 101 */     Counter localCounter = new Counter("hits");
/* 102 */     for (int j = 0; j < i; j++) {
/* 103 */       double d5 = StdRandom.uniform(0.0D, 1.0D);
/* 104 */       double d6 = StdRandom.uniform(0.0D, 1.0D);
/* 105 */       Point2D localPoint2D = new Point2D(d5, d6);
/*     */ 
/* 107 */       if (localInterval2D.contains(localPoint2D)) localCounter.increment(); else {
/* 108 */         localPoint2D.draw();
/*     */       }
/*     */     }
/* 111 */     StdOut.println(localCounter);
/* 112 */     StdOut.printf("box area = %.2f\n", new Object[] { Double.valueOf(localInterval2D.area()) });
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Interval2D
 * JD-Core Version:    0.6.2
 */