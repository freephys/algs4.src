/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Interval1D
/*     */ {
/*  29 */   public static final Comparator<Interval1D> LEFT_ENDPOINT_ORDER = new Interval1D.LeftComparator(null);
/*     */ 
/*  34 */   public static final Comparator<Interval1D> RIGHT_ENDPOINT_ORDER = new Interval1D.RightComparator(null);
/*     */ 
/*  39 */   public static final Comparator<Interval1D> LENGTH_ORDER = new Interval1D.LengthComparator(null);
/*     */   private final double left;
/*     */   private final double right;
/*     */ 
/*     */   public Interval1D(double paramDouble1, double paramDouble2)
/*     */   {
/*  55 */     if ((Double.isInfinite(paramDouble1)) || (Double.isInfinite(paramDouble2)))
/*  56 */       throw new IllegalArgumentException("Endpoints must be finite");
/*  57 */     if ((Double.isNaN(paramDouble1)) || (Double.isNaN(paramDouble2))) {
/*  58 */       throw new IllegalArgumentException("Endpoints cannot be NaN");
/*     */     }
/*  60 */     if (paramDouble1 <= paramDouble2) {
/*  61 */       this.left = paramDouble1;
/*  62 */       this.right = paramDouble2;
/*     */     } else {
/*  64 */       throw new IllegalArgumentException("Illegal interval");
/*     */     }
/*     */   }
/*     */ 
/*     */   public double left()
/*     */   {
/*  72 */     return this.left;
/*     */   }
/*     */ 
/*     */   public double right()
/*     */   {
/*  80 */     return this.right;
/*     */   }
/*     */ 
/*     */   public boolean intersects(Interval1D paramInterval1D)
/*     */   {
/*  89 */     if (this.right < paramInterval1D.left) return false;
/*  90 */     if (paramInterval1D.right < this.left) return false;
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/* 100 */     return (this.left <= paramDouble) && (paramDouble <= this.right);
/*     */   }
/*     */ 
/*     */   public double length()
/*     */   {
/* 108 */     return this.right - this.left;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 116 */     return "[" + this.left + ", " + this.right + "]";
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 161 */     Interval1D[] arrayOfInterval1D = new Interval1D[4];
/* 162 */     arrayOfInterval1D[0] = new Interval1D(15.0D, 33.0D);
/* 163 */     arrayOfInterval1D[1] = new Interval1D(45.0D, 60.0D);
/* 164 */     arrayOfInterval1D[2] = new Interval1D(20.0D, 70.0D);
/* 165 */     arrayOfInterval1D[3] = new Interval1D(46.0D, 55.0D);
/*     */ 
/* 167 */     StdOut.println("Unsorted");
/* 168 */     for (int i = 0; i < arrayOfInterval1D.length; i++)
/* 169 */       StdOut.println(arrayOfInterval1D[i]);
/* 170 */     StdOut.println();
/*     */ 
/* 172 */     StdOut.println("Sort by left endpoint");
/* 173 */     Arrays.sort(arrayOfInterval1D, LEFT_ENDPOINT_ORDER);
/* 174 */     for (i = 0; i < arrayOfInterval1D.length; i++)
/* 175 */       StdOut.println(arrayOfInterval1D[i]);
/* 176 */     StdOut.println();
/*     */ 
/* 178 */     StdOut.println("Sort by right endpoint");
/* 179 */     Arrays.sort(arrayOfInterval1D, RIGHT_ENDPOINT_ORDER);
/* 180 */     for (i = 0; i < arrayOfInterval1D.length; i++)
/* 181 */       StdOut.println(arrayOfInterval1D[i]);
/* 182 */     StdOut.println();
/*     */ 
/* 184 */     StdOut.println("Sort by length");
/* 185 */     Arrays.sort(arrayOfInterval1D, LENGTH_ORDER);
/* 186 */     for (i = 0; i < arrayOfInterval1D.length; i++)
/* 187 */       StdOut.println(arrayOfInterval1D[i]);
/* 188 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   private static class LengthComparator
/*     */     implements Comparator<Interval1D>
/*     */   {
/*     */     public int compare(Interval1D paramInterval1D1, Interval1D paramInterval1D2)
/*     */     {
/* 146 */       double d1 = paramInterval1D1.length();
/* 147 */       double d2 = paramInterval1D2.length();
/* 148 */       if (d1 < d2) return -1;
/* 149 */       if (d1 > d2) return 1;
/* 150 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class RightComparator
/*     */     implements Comparator<Interval1D>
/*     */   {
/*     */     public int compare(Interval1D paramInterval1D1, Interval1D paramInterval1D2)
/*     */     {
/* 135 */       if (paramInterval1D1.right < paramInterval1D2.right) return -1;
/* 136 */       if (paramInterval1D1.right > paramInterval1D2.right) return 1;
/* 137 */       if (paramInterval1D1.left < paramInterval1D2.left) return -1;
/* 138 */       if (paramInterval1D1.left > paramInterval1D2.left) return 1;
/* 139 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class LeftComparator
/*     */     implements Comparator<Interval1D>
/*     */   {
/*     */     public int compare(Interval1D paramInterval1D1, Interval1D paramInterval1D2)
/*     */     {
/* 124 */       if (paramInterval1D1.left < paramInterval1D2.left) return -1;
/* 125 */       if (paramInterval1D1.left > paramInterval1D2.left) return 1;
/* 126 */       if (paramInterval1D1.right < paramInterval1D2.right) return -1;
/* 127 */       if (paramInterval1D1.right > paramInterval1D2.right) return 1;
/* 128 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Interval1D
 * JD-Core Version:    0.6.2
 */