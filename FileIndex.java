/*    */ import java.io.File;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class FileIndex
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 35 */     ST localST = new ST();
/*    */ 
/* 38 */     StdOut.println("Indexing files");
/*    */     Object localObject2;
/* 39 */     for (localObject2 : paramArrayOfString) {
/* 40 */       StdOut.println("  " + (String)localObject2);
/* 41 */       File localFile = new File((String)localObject2);
/* 42 */       In localIn = new In(localFile);
/* 43 */       while (!localIn.isEmpty()) {
/* 44 */         String str = localIn.readString();
/* 45 */         if (!localST.contains(str)) localST.put(str, new SET());
/* 46 */         SET localSET2 = (SET)localST.get(str);
/* 47 */         localSET2.add(localFile);
/*    */       }
/*    */     }
/*    */     Iterator localIterator;
/* 53 */     while (!StdIn.isEmpty()) {
/* 54 */       ??? = StdIn.readString();
/* 55 */       if (localST.contains((Comparable)???)) {
/* 56 */         SET localSET1 = (SET)localST.get((Comparable)???);
/* 57 */         for (localIterator = localSET1.iterator(); localIterator.hasNext(); ) { localObject2 = (File)localIterator.next();
/* 58 */           StdOut.println("  " + ((File)localObject2).getName());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FileIndex
 * JD-Core Version:    0.6.2
 */