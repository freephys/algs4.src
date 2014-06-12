/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Point2D
/*     */   implements Comparable<Point2D>
/*     */ {
/*  31 */   public static final Comparator<Point2D> X_ORDER = new Point2D.XOrder(null);
/*     */ 
/*  36 */   public static final Comparator<Point2D> Y_ORDER = new Point2D.YOrder(null);
/*     */ 
/*  41 */   public static final Comparator<Point2D> R_ORDER = new Point2D.ROrder(null);
/*     */ 
/*  46 */   public final Comparator<Point2D> POLAR_ORDER = new Point2D.PolarOrder(null);
/*     */ 
/*  51 */   public final Comparator<Point2D> ATAN2_ORDER = new Point2D.Atan2Order(null);
/*     */ 
/*  56 */   public final Comparator<Point2D> DISTANCE_TO_ORDER = new Point2D.DistanceToOrder(null);
/*     */   private final double x;
/*     */   private final double y;
/*     */ 
/*     */   public Point2D(double paramDouble1, double paramDouble2)
/*     */   {
/*  70 */     if ((Double.isInfinite(paramDouble1)) || (Double.isInfinite(paramDouble2)))
/*  71 */       throw new IllegalArgumentException("Coordinates must be finite");
/*  72 */     if ((Double.isNaN(paramDouble1)) || (Double.isNaN(paramDouble2)))
/*  73 */       throw new IllegalArgumentException("Coordinates cannot be NaN");
/*  74 */     if (paramDouble1 == 0.0D) paramDouble1 = 0.0D;
/*  75 */     if (paramDouble2 == 0.0D) paramDouble2 = 0.0D;
/*  76 */     this.x = paramDouble1;
/*  77 */     this.y = paramDouble2;
/*     */   }
/*     */ 
/*     */   public double x()
/*     */   {
/*  85 */     return this.x;
/*     */   }
/*     */ 
/*     */   public double y()
/*     */   {
/*  93 */     return this.y;
/*     */   }
/*     */ 
/*     */   public double r()
/*     */   {
/* 101 */     return Math.sqrt(this.x * this.x + this.y * this.y);
/*     */   }
/*     */ 
/*     */   public double theta()
/*     */   {
/* 109 */     return Math.atan2(this.y, this.x);
/*     */   }
/*     */ 
/*     */   private double angleTo(Point2D paramPoint2D)
/*     */   {
/* 117 */     double d1 = paramPoint2D.x - this.x;
/* 118 */     double d2 = paramPoint2D.y - this.y;
/* 119 */     return Math.atan2(d2, d1);
/*     */   }
/*     */ 
/*     */   public static int ccw(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3)
/*     */   {
/* 130 */     double d = (paramPoint2D2.x - paramPoint2D1.x) * (paramPoint2D3.y - paramPoint2D1.y) - (paramPoint2D2.y - paramPoint2D1.y) * (paramPoint2D3.x - paramPoint2D1.x);
/* 131 */     if (d < 0.0D) return -1;
/* 132 */     if (d > 0.0D) return 1;
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */   public static double area2(Point2D paramPoint2D1, Point2D paramPoint2D2, Point2D paramPoint2D3)
/*     */   {
/* 144 */     return (paramPoint2D2.x - paramPoint2D1.x) * (paramPoint2D3.y - paramPoint2D1.y) - (paramPoint2D2.y - paramPoint2D1.y) * (paramPoint2D3.x - paramPoint2D1.x);
/*     */   }
/*     */ 
/*     */   public double distanceTo(Point2D paramPoint2D)
/*     */   {
/* 153 */     double d1 = this.x - paramPoint2D.x;
/* 154 */     double d2 = this.y - paramPoint2D.y;
/* 155 */     return Math.sqrt(d1 * d1 + d2 * d2);
/*     */   }
/*     */ 
/*     */   public double distanceSquaredTo(Point2D paramPoint2D)
/*     */   {
/* 164 */     double d1 = this.x - paramPoint2D.x;
/* 165 */     double d2 = this.y - paramPoint2D.y;
/* 166 */     return d1 * d1 + d2 * d2;
/*     */   }
/*     */ 
/*     */   public int compareTo(Point2D paramPoint2D)
/*     */   {
/* 176 */     if (this.y < paramPoint2D.y) return -1;
/* 177 */     if (this.y > paramPoint2D.y) return 1;
/* 178 */     if (this.x < paramPoint2D.x) return -1;
/* 179 */     if (this.x > paramPoint2D.x) return 1;
/* 180 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 261 */     if (paramObject == this) return true;
/* 262 */     if (paramObject == null) return false;
/* 263 */     if (paramObject.getClass() != getClass()) return false;
/* 264 */     Point2D localPoint2D = (Point2D)paramObject;
/* 265 */     return (this.x == localPoint2D.x) && (this.y == localPoint2D.y);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 273 */     return "(" + this.x + ", " + this.y + ")";
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 281 */     int i = Double.valueOf(this.x).hashCode();
/* 282 */     int j = Double.valueOf(this.y).hashCode();
/* 283 */     return 31 * i + j;
/*     */   }
/*     */ 
/*     */   public void draw()
/*     */   {
/* 290 */     StdDraw.point(this.x, this.y);
/*     */   }
/*     */ 
/*     */   public void drawTo(Point2D paramPoint2D)
/*     */   {
/* 298 */     StdDraw.line(this.x, this.y, paramPoint2D.x, paramPoint2D.y);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 306 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 307 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 308 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*     */ 
/* 310 */     StdDraw.setCanvasSize(800, 800);
/* 311 */     StdDraw.setXscale(0.0D, 100.0D);
/* 312 */     StdDraw.setYscale(0.0D, 100.0D);
/* 313 */     StdDraw.setPenRadius(0.005D);
/* 314 */     Point2D[] arrayOfPoint2D = new Point2D[k];
/* 315 */     for (int m = 0; m < k; m++) {
/* 316 */       n = StdRandom.uniform(100);
/* 317 */       int i1 = StdRandom.uniform(100);
/* 318 */       arrayOfPoint2D[m] = new Point2D(n, i1);
/* 319 */       arrayOfPoint2D[m].draw();
/*     */     }
/*     */ 
/* 323 */     Point2D localPoint2D = new Point2D(i, j);
/* 324 */     StdDraw.setPenColor(StdDraw.RED);
/* 325 */     StdDraw.setPenRadius(0.02D);
/* 326 */     localPoint2D.draw();
/*     */ 
/* 330 */     StdDraw.setPenRadius();
/* 331 */     StdDraw.setPenColor(StdDraw.BLUE);
/* 332 */     Arrays.sort(arrayOfPoint2D, localPoint2D.POLAR_ORDER);
/* 333 */     for (int n = 0; n < k; n++) {
/* 334 */       localPoint2D.drawTo(arrayOfPoint2D[n]);
/* 335 */       StdDraw.show(100);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DistanceToOrder
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     private DistanceToOrder()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 246 */       double d1 = Point2D.this.distanceSquaredTo(paramPoint2D1);
/* 247 */       double d2 = Point2D.this.distanceSquaredTo(paramPoint2D2);
/* 248 */       if (d1 < d2) return -1;
/* 249 */       if (d1 > d2) return 1;
/* 250 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class PolarOrder
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     private PolarOrder()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 225 */       double d1 = paramPoint2D1.x - Point2D.this.x;
/* 226 */       double d2 = paramPoint2D1.y - Point2D.this.y;
/* 227 */       double d3 = paramPoint2D2.x - Point2D.this.x;
/* 228 */       double d4 = paramPoint2D2.y - Point2D.this.y;
/*     */ 
/* 230 */       if ((d2 >= 0.0D) && (d4 < 0.0D)) return -1;
/* 231 */       if ((d4 >= 0.0D) && (d2 < 0.0D)) return 1;
/* 232 */       if ((d2 == 0.0D) && (d4 == 0.0D)) {
/* 233 */         if ((d1 >= 0.0D) && (d3 < 0.0D)) return -1;
/* 234 */         if ((d3 >= 0.0D) && (d1 < 0.0D)) return 1;
/* 235 */         return 0;
/*     */       }
/* 237 */       return -Point2D.ccw(Point2D.this, paramPoint2D1, paramPoint2D2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Atan2Order
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     private Atan2Order()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 214 */       double d1 = Point2D.this.angleTo(paramPoint2D1);
/* 215 */       double d2 = Point2D.this.angleTo(paramPoint2D2);
/* 216 */       if (d1 < d2) return -1;
/* 217 */       if (d1 > d2) return 1;
/* 218 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ROrder
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 204 */       double d = paramPoint2D1.x * paramPoint2D1.x + paramPoint2D1.y * paramPoint2D1.y - (paramPoint2D2.x * paramPoint2D2.x + paramPoint2D2.y * paramPoint2D2.y);
/* 205 */       if (d < 0.0D) return -1;
/* 206 */       if (d > 0.0D) return 1;
/* 207 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class YOrder
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 195 */       if (paramPoint2D1.y < paramPoint2D2.y) return -1;
/* 196 */       if (paramPoint2D1.y > paramPoint2D2.y) return 1;
/* 197 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class XOrder
/*     */     implements Comparator<Point2D>
/*     */   {
/*     */     public int compare(Point2D paramPoint2D1, Point2D paramPoint2D2)
/*     */     {
/* 186 */       if (paramPoint2D1.x < paramPoint2D2.x) return -1;
/* 187 */       if (paramPoint2D1.x > paramPoint2D2.x) return 1;
/* 188 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Point2D
 * JD-Core Version:    0.6.2
 */