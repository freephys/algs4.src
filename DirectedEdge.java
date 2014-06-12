/*    */ public class DirectedEdge
/*    */ {
/*    */   private final int v;
/*    */   private final int w;
/*    */   private final double weight;
/*    */ 
/*    */   public DirectedEdge(int paramInt1, int paramInt2, double paramDouble)
/*    */   {
/* 38 */     if (paramInt1 < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
/* 39 */     if (paramInt2 < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
/* 40 */     if (Double.isNaN(paramDouble)) throw new IllegalArgumentException("Weight is NaN");
/* 41 */     this.v = paramInt1;
/* 42 */     this.w = paramInt2;
/* 43 */     this.weight = paramDouble;
/*    */   }
/*    */ 
/*    */   public int from()
/*    */   {
/* 51 */     return this.v;
/*    */   }
/*    */ 
/*    */   public int to()
/*    */   {
/* 59 */     return this.w;
/*    */   }
/*    */ 
/*    */   public double weight()
/*    */   {
/* 67 */     return this.weight;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 75 */     return this.v + "->" + this.w + " " + String.format("%5.2f", new Object[] { Double.valueOf(this.weight) });
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 82 */     DirectedEdge localDirectedEdge = new DirectedEdge(12, 23, 3.14D);
/* 83 */     StdOut.println(localDirectedEdge);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DirectedEdge
 * JD-Core Version:    0.6.2
 */