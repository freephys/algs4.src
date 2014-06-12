/*    */ public class BipartiteMatching
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 25 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 26 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 27 */     int k = 2 * i; int m = 2 * i + 1;
/* 28 */     FlowNetwork localFlowNetwork = new FlowNetwork(2 * i + 2);
/* 29 */     for (int n = 0; n < j; n++) {
/* 30 */       i1 = StdRandom.uniform(i);
/* 31 */       int i2 = StdRandom.uniform(i) + i;
/* 32 */       localFlowNetwork.addEdge(new FlowEdge(i1, i2, (1.0D / 0.0D)));
/* 33 */       StdOut.println(i1 + "-" + i2);
/*    */     }
/* 35 */     for (n = 0; n < i; n++) {
/* 36 */       localFlowNetwork.addEdge(new FlowEdge(k, n, 1.0D));
/* 37 */       localFlowNetwork.addEdge(new FlowEdge(n + i, m, 1.0D));
/*    */     }
/*    */ 
/* 42 */     FordFulkerson localFordFulkerson = new FordFulkerson(localFlowNetwork, k, m);
/* 43 */     StdOut.println();
/* 44 */     StdOut.println("Size of maximum matching = " + (int)localFordFulkerson.value());
/* 45 */     for (int i1 = 0; i1 < i; i1++)
/* 46 */       for (FlowEdge localFlowEdge : localFlowNetwork.adj(i1))
/* 47 */         if ((localFlowEdge.from() == i1) && (localFlowEdge.flow() > 0.0D))
/* 48 */           StdOut.println(localFlowEdge.from() + "-" + localFlowEdge.to());
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BipartiteMatching
 * JD-Core Version:    0.6.2
 */