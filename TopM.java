/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class TopM
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 44 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 45 */     MinPQ localMinPQ = new MinPQ(i + 1);
/*    */ 
/* 47 */     while (StdIn.hasNextLine())
/*    */     {
/* 49 */       localObject1 = StdIn.readLine();
/* 50 */       localObject2 = new Transaction((String)localObject1);
/* 51 */       localMinPQ.insert(localObject2);
/*    */ 
/* 54 */       if (localMinPQ.size() > i) {
/* 55 */         localMinPQ.delMin();
/*    */       }
/*    */     }
/*    */ 
/* 59 */     Object localObject1 = new Stack();
/* 60 */     for (Object localObject2 = localMinPQ.iterator(); ((Iterator)localObject2).hasNext(); ) { localTransaction = (Transaction)((Iterator)localObject2).next();
/* 61 */       ((Stack)localObject1).push(localTransaction);
/*    */     }
/* 62 */     Transaction localTransaction;
/* 62 */     for (localObject2 = ((Stack)localObject1).iterator(); ((Iterator)localObject2).hasNext(); ) { localTransaction = (Transaction)((Iterator)localObject2).next();
/* 63 */       StdOut.println(localTransaction);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TopM
 * JD-Core Version:    0.6.2
 */