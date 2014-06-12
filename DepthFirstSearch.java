/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class DepthFirstSearch
/*    */ {
/*    */   private boolean[] marked;
/*    */   private int count;
/*    */ 
/*    */   public DepthFirstSearch(Graph paramGraph, int paramInt)
/*    */   {
/* 49 */     this.marked = new boolean[paramGraph.V()];
/* 50 */     dfs(paramGraph, paramInt);
/*    */   }
/*    */ 
/*    */   private void dfs(Graph paramGraph, int paramInt)
/*    */   {
/* 55 */     this.count += 1;
/* 56 */     this.marked[paramInt] = true;
/* 57 */     for (Iterator localIterator = paramGraph.adj(paramInt).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 58 */       if (this.marked[i] == 0)
/* 59 */         dfs(paramGraph, i);
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean marked(int paramInt)
/*    */   {
/* 70 */     return this.marked[paramInt];
/*    */   }
/*    */ 
/*    */   public int count()
/*    */   {
/* 78 */     return this.count;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 85 */     In localIn = new In(paramArrayOfString[0]);
/* 86 */     Graph localGraph = new Graph(localIn);
/* 87 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 88 */     DepthFirstSearch localDepthFirstSearch = new DepthFirstSearch(localGraph, i);
/* 89 */     for (int j = 0; j < localGraph.V(); j++) {
/* 90 */       if (localDepthFirstSearch.marked(j)) {
/* 91 */         StdOut.print(j + " ");
/*    */       }
/*    */     }
/* 94 */     StdOut.println();
/* 95 */     if (localDepthFirstSearch.count() != localGraph.V()) StdOut.println("NOT connected"); else
/* 96 */       StdOut.println("connected");
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DepthFirstSearch
 * JD-Core Version:    0.6.2
 */