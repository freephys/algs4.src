/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class DegreesOfSeparation
/*     */ {
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  91 */     String str1 = paramArrayOfString[0];
/*  92 */     String str2 = paramArrayOfString[1];
/*  93 */     String str3 = paramArrayOfString[2];
/*     */ 
/*  97 */     SymbolGraph localSymbolGraph = new SymbolGraph(str1, str2);
/*  98 */     Graph localGraph = localSymbolGraph.G();
/*  99 */     if (!localSymbolGraph.contains(str3)) {
/* 100 */       StdOut.println(str3 + " not in database.");
/* 101 */       return;
/*     */     }
/*     */ 
/* 104 */     int i = localSymbolGraph.index(str3);
/* 105 */     BreadthFirstPaths localBreadthFirstPaths = new BreadthFirstPaths(localGraph, i);
/*     */ 
/* 107 */     while (!StdIn.isEmpty()) {
/* 108 */       String str4 = StdIn.readLine();
/* 109 */       if (localSymbolGraph.contains(str4)) {
/* 110 */         int j = localSymbolGraph.index(str4);
/*     */         Iterator localIterator;
/* 111 */         if (localBreadthFirstPaths.hasPathTo(j)) {
/* 112 */           for (localIterator = localBreadthFirstPaths.pathTo(j).iterator(); localIterator.hasNext(); ) { int k = ((Integer)localIterator.next()).intValue();
/* 113 */             StdOut.println("   " + localSymbolGraph.name(k));
/*     */           }
/*     */         }
/*     */         else
/* 117 */           StdOut.println("Not connected");
/*     */       }
/*     */       else
/*     */       {
/* 121 */         StdOut.println("   Not in database.");
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DegreesOfSeparation
 * JD-Core Version:    0.6.2
 */