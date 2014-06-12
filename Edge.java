/*     */ public class Edge
/*     */   implements Comparable<Edge>
/*     */ {
/*     */   private final int v;
/*     */   private final int w;
/*     */   private final double weight;
/*     */ 
/*     */   public Edge(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/*  40 */     if (paramInt1 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  41 */     if (paramInt2 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  42 */     if (Double.isNaN(paramDouble)) throw new IllegalArgumentException("Weight is NaN");
/*  43 */     this.v = paramInt1;
/*  44 */     this.w = paramInt2;
/*  45 */     this.weight = paramDouble;
/*     */   }
/*     */ 
/*     */   public double weight()
/*     */   {
/*  53 */     return this.weight;
/*     */   }
/*     */ 
/*     */   public int either()
/*     */   {
/*  61 */     return this.v;
/*     */   }
/*     */ 
/*     */   public int other(int paramInt)
/*     */   {
/*  74 */     if (paramInt == this.v) return this.w;
/*  75 */     if (paramInt == this.w) return this.v;
/*  76 */     throw new IllegalArgumentException("Illegal endpoint");
/*     */   }
/*     */ 
/*     */   public int compareTo(Edge paramEdge)
/*     */   {
/*  86 */     if (weight() < paramEdge.weight()) return -1;
/*  87 */     if (weight() > paramEdge.weight()) return 1;
/*  88 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  96 */     return String.format("%d-%d %.5f", new Object[] { Integer.valueOf(this.v), Integer.valueOf(this.w), Double.valueOf(this.weight) });
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 103 */     Edge localEdge = new Edge(12, 23, 3.14D);
/* 104 */     StdOut.println(localEdge);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Edge
 * JD-Core Version:    0.6.2
 */