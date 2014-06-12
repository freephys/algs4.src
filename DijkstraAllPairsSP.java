/*    */ public class DijkstraAllPairsSP
/*    */ {
/*    */   private DijkstraSP[] all;
/*    */ 
/*    */   public DijkstraAllPairsSP(EdgeWeightedDigraph paramEdgeWeightedDigraph)
/*    */   {
/* 40 */     this.all = new DijkstraSP[paramEdgeWeightedDigraph.V()];
/* 41 */     for (int i = 0; i < paramEdgeWeightedDigraph.V(); i++)
/* 42 */       this.all[i] = new DijkstraSP(paramEdgeWeightedDigraph, i);
/*    */   }
/*    */ 
/*    */   public Iterable<DirectedEdge> path(int paramInt1, int paramInt2)
/*    */   {
/* 53 */     return this.all[paramInt1].pathTo(paramInt2);
/*    */   }
/*    */ 
/*    */   public boolean hasPath(int paramInt1, int paramInt2)
/*    */   {
/* 64 */     return dist(paramInt1, paramInt2) < (1.0D / 0.0D);
/*    */   }
/*    */ 
/*    */   public double dist(int paramInt1, int paramInt2)
/*    */   {
/* 75 */     return this.all[paramInt1].distTo(paramInt2);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DijkstraAllPairsSP
 * JD-Core Version:    0.6.2
 */