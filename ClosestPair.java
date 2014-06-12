/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class ClosestPair
/*     */ {
/*     */   private Point2D best1;
/*     */   private Point2D best2;
/*  19 */   private double bestDistance = (1.0D / 0.0D);
/*     */ 
/*     */   public ClosestPair(Point2D[] paramArrayOfPoint2D) {
/*  22 */     int i = paramArrayOfPoint2D.length;
/*  23 */     if (i <= 1) return;
/*     */ 
/*  26 */     Point2D[] arrayOfPoint2D1 = new Point2D[i];
/*  27 */     for (int j = 0; j < i; j++) arrayOfPoint2D1[j] = paramArrayOfPoint2D[j];
/*  28 */     Arrays.sort(arrayOfPoint2D1, Point2D.X_ORDER);
/*     */ 
/*  31 */     for (j = 0; j < i - 1; j++) {
/*  32 */       if (arrayOfPoint2D1[j].equals(arrayOfPoint2D1[(j + 1)])) {
/*  33 */         this.bestDistance = 0.0D;
/*  34 */         this.best1 = arrayOfPoint2D1[j];
/*  35 */         this.best2 = arrayOfPoint2D1[(j + 1)];
/*  36 */         return;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  41 */     Point2D[] arrayOfPoint2D2 = new Point2D[i];
/*  42 */     for (int k = 0; k < i; k++) arrayOfPoint2D2[k] = arrayOfPoint2D1[k];
/*     */ 
/*  45 */     Point2D[] arrayOfPoint2D3 = new Point2D[i];
/*     */ 
/*  47 */     closest(arrayOfPoint2D1, arrayOfPoint2D2, arrayOfPoint2D3, 0, i - 1);
/*     */   }
/*     */ 
/*     */   private double closest(Point2D[] paramArrayOfPoint2D1, Point2D[] paramArrayOfPoint2D2, Point2D[] paramArrayOfPoint2D3, int paramInt1, int paramInt2)
/*     */   {
/*  55 */     if (paramInt2 <= paramInt1) return (1.0D / 0.0D);
/*     */ 
/*  57 */     int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  58 */     Point2D localPoint2D = paramArrayOfPoint2D1[i];
/*     */ 
/*  61 */     double d1 = closest(paramArrayOfPoint2D1, paramArrayOfPoint2D2, paramArrayOfPoint2D3, paramInt1, i);
/*  62 */     double d2 = closest(paramArrayOfPoint2D1, paramArrayOfPoint2D2, paramArrayOfPoint2D3, i + 1, paramInt2);
/*  63 */     double d3 = Math.min(d1, d2);
/*     */ 
/*  66 */     merge(paramArrayOfPoint2D2, paramArrayOfPoint2D3, paramInt1, i, paramInt2);
/*     */ 
/*  69 */     int j = 0;
/*  70 */     for (int k = paramInt1; k <= paramInt2; k++) {
/*  71 */       if (Math.abs(paramArrayOfPoint2D2[k].x() - localPoint2D.x()) < d3) {
/*  72 */         paramArrayOfPoint2D3[(j++)] = paramArrayOfPoint2D2[k];
/*     */       }
/*     */     }
/*     */ 
/*  76 */     for (k = 0; k < j; k++)
/*     */     {
/*  78 */       for (int m = k + 1; (m < j) && (paramArrayOfPoint2D3[m].y() - paramArrayOfPoint2D3[k].y() < d3); m++) {
/*  79 */         double d4 = paramArrayOfPoint2D3[k].distanceTo(paramArrayOfPoint2D3[m]);
/*  80 */         if (d4 < d3) {
/*  81 */           d3 = d4;
/*  82 */           if (d4 < this.bestDistance) {
/*  83 */             this.bestDistance = d3;
/*  84 */             this.best1 = paramArrayOfPoint2D3[k];
/*  85 */             this.best2 = paramArrayOfPoint2D3[m];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  91 */     return d3;
/*     */   }
/*     */   public Point2D either() {
/*  94 */     return this.best1; } 
/*  95 */   public Point2D other() { return this.best2; }
/*     */ 
/*     */   public double distance() {
/*  98 */     return this.bestDistance;
/*     */   }
/*     */ 
/*     */   private static boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 103 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private static void merge(Comparable[] paramArrayOfComparable1, Comparable[] paramArrayOfComparable2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 110 */     for (int i = paramInt1; i <= paramInt3; i++) {
/* 111 */       paramArrayOfComparable2[i] = paramArrayOfComparable1[i];
/*     */     }
/*     */ 
/* 115 */     i = paramInt1; int j = paramInt2 + 1;
/* 116 */     for (int k = paramInt1; k <= paramInt3; k++)
/* 117 */       if (i > paramInt2) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)];
/* 118 */       else if (j > paramInt3) paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/* 119 */       else if (less(paramArrayOfComparable2[j], paramArrayOfComparable2[i])) paramArrayOfComparable1[k] = paramArrayOfComparable2[(j++)]; else
/* 120 */         paramArrayOfComparable1[k] = paramArrayOfComparable2[(i++)];
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 127 */     int i = StdIn.readInt();
/* 128 */     Point2D[] arrayOfPoint2D = new Point2D[i];
/* 129 */     for (int j = 0; j < i; j++) {
/* 130 */       double d1 = StdIn.readDouble();
/* 131 */       double d2 = StdIn.readDouble();
/* 132 */       arrayOfPoint2D[j] = new Point2D(d1, d2);
/*     */     }
/* 134 */     ClosestPair localClosestPair = new ClosestPair(arrayOfPoint2D);
/* 135 */     StdOut.println(localClosestPair.distance() + " from " + localClosestPair.either() + " to " + localClosestPair.other());
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ClosestPair
 * JD-Core Version:    0.6.2
 */