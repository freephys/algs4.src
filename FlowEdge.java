/*     */ public class FlowEdge
/*     */ {
/*     */   private final int v;
/*     */   private final int w;
/*     */   private final double capacity;
/*     */   private double flow;
/*     */ 
/*     */   public FlowEdge(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/*  41 */     if (paramInt1 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  42 */     if (paramInt2 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  43 */     if (paramDouble < 0.0D) throw new IllegalArgumentException("Edge capacity must be nonnegaitve");
/*  44 */     this.v = paramInt1;
/*  45 */     this.w = paramInt2;
/*  46 */     this.capacity = paramDouble;
/*  47 */     this.flow = 0.0D;
/*     */   }
/*     */ 
/*     */   public FlowEdge(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
/*     */   {
/*  64 */     if (paramInt1 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  65 */     if (paramInt2 < 0) throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
/*  66 */     if (paramDouble1 < 0.0D) throw new IllegalArgumentException("Edge capacity must be nonnegaitve");
/*  67 */     if (paramDouble2 > paramDouble1) throw new IllegalArgumentException("Flow exceeds capacity");
/*  68 */     if (paramDouble2 < 0.0D) throw new IllegalArgumentException("Flow must be nonnnegative");
/*  69 */     this.v = paramInt1;
/*  70 */     this.w = paramInt2;
/*  71 */     this.capacity = paramDouble1;
/*  72 */     this.flow = paramDouble2;
/*     */   }
/*     */ 
/*     */   public FlowEdge(FlowEdge paramFlowEdge)
/*     */   {
/*  80 */     this.v = paramFlowEdge.v;
/*  81 */     this.w = paramFlowEdge.w;
/*  82 */     this.capacity = paramFlowEdge.capacity;
/*  83 */     this.flow = paramFlowEdge.flow;
/*     */   }
/*     */ 
/*     */   public int from()
/*     */   {
/*  91 */     return this.v;
/*     */   }
/*     */ 
/*     */   public int to()
/*     */   {
/*  99 */     return this.w;
/*     */   }
/*     */ 
/*     */   public double capacity()
/*     */   {
/* 107 */     return this.capacity;
/*     */   }
/*     */ 
/*     */   public double flow()
/*     */   {
/* 115 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public int other(int paramInt)
/*     */   {
/* 128 */     if (paramInt == this.v) return this.w;
/* 129 */     if (paramInt == this.w) return this.v;
/* 130 */     throw new IllegalArgumentException("Illegal endpoint");
/*     */   }
/*     */ 
/*     */   public double residualCapacityTo(int paramInt)
/*     */   {
/* 145 */     if (paramInt == this.v) return this.flow;
/* 146 */     if (paramInt == this.w) return this.capacity - this.flow;
/* 147 */     throw new IllegalArgumentException("Illegal endpoint");
/*     */   }
/*     */ 
/*     */   public void addResidualFlowTo(int paramInt, double paramDouble)
/*     */   {
/* 162 */     if (paramInt == this.v) this.flow -= paramDouble;
/* 163 */     else if (paramInt == this.w) this.flow += paramDouble; else
/* 164 */       throw new IllegalArgumentException("Illegal endpoint");
/* 165 */     if (Double.isNaN(paramDouble)) throw new IllegalArgumentException("Change in flow = NaN");
/* 166 */     if (this.flow < 0.0D) throw new IllegalArgumentException("Flow is negative");
/* 167 */     if (this.flow > this.capacity) throw new IllegalArgumentException("Flow exceeds capacity");
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 176 */     return this.v + "->" + this.w + " " + this.flow + "/" + this.capacity;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 184 */     FlowEdge localFlowEdge = new FlowEdge(12, 23, 3.14D);
/* 185 */     StdOut.println(localFlowEdge);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FlowEdge
 * JD-Core Version:    0.6.2
 */