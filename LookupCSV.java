/*    */ public class LookupCSV
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 35 */     int i = Integer.parseInt(paramArrayOfString[1]);
/* 36 */     int j = Integer.parseInt(paramArrayOfString[2]);
/*    */ 
/* 39 */     ST localST = new ST();
/*    */ 
/* 42 */     In localIn = new In(paramArrayOfString[0]);
/*    */     String str1;
/* 43 */     while (localIn.hasNextLine()) {
/* 44 */       str1 = localIn.readLine();
/* 45 */       String[] arrayOfString = str1.split(",");
/* 46 */       String str2 = arrayOfString[i];
/* 47 */       String str3 = arrayOfString[j];
/* 48 */       localST.put(str2, str3);
/*    */     }
/*    */ 
/* 51 */     while (!StdIn.isEmpty()) {
/* 52 */       str1 = StdIn.readString();
/* 53 */       if (localST.contains(str1)) StdOut.println(localST.get(str1)); else
/* 54 */         StdOut.println("Not found");
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LookupCSV
 * JD-Core Version:    0.6.2
 */