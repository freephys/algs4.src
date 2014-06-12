/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class LookupIndex
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 39 */     String str1 = paramArrayOfString[0];
/* 40 */     String str2 = paramArrayOfString[1];
/* 41 */     In localIn = new In(str1);
/*    */ 
/* 43 */     ST localST1 = new ST();
/* 44 */     ST localST2 = new ST();
/*    */     String str3;
/*    */     Object localObject1;
/*    */     Object localObject2;
/* 46 */     while (localIn.hasNextLine()) {
/* 47 */       str3 = localIn.readLine();
/* 48 */       localObject1 = str3.split(str2);
/* 49 */       localObject2 = localObject1[0];
/* 50 */       for (int i = 1; i < localObject1.length; i++) {
/* 51 */         Comparable localComparable = localObject1[i];
/* 52 */         if (!localST1.contains((Comparable)localObject2)) localST1.put((Comparable)localObject2, new Queue());
/* 53 */         if (!localST2.contains(localComparable)) localST2.put(localComparable, new Queue());
/* 54 */         ((Queue)localST1.get((Comparable)localObject2)).enqueue(localComparable);
/* 55 */         ((Queue)localST2.get(localComparable)).enqueue(localObject2);
/*    */       }
/*    */     }
/*    */ 
/* 59 */     StdOut.println("Done indexing");
/*    */ 
/* 62 */     while (!StdIn.isEmpty()) {
/* 63 */       str3 = StdIn.readLine();
/* 64 */       if (localST1.contains(str3))
/* 65 */         for (localObject1 = ((Queue)localST1.get(str3)).iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (String)((Iterator)localObject1).next();
/* 66 */           StdOut.println("  " + (String)localObject2); }
/* 67 */       if (localST2.contains(str3))
/* 68 */         for (localObject1 = ((Queue)localST2.get(str3)).iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (String)((Iterator)localObject1).next();
/* 69 */           StdOut.println("  " + (String)localObject2);
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LookupIndex
 * JD-Core Version:    0.6.2
 */