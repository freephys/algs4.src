/*    */ public class FrequencyCounter
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 49 */     int i = 0; int j = 0;
/* 50 */     int k = Integer.parseInt(paramArrayOfString[0]);
/* 51 */     ST localST = new ST();
/*    */ 
/* 54 */     while (!StdIn.isEmpty()) {
/* 55 */       localObject = StdIn.readString();
/* 56 */       if (((String)localObject).length() >= k) {
/* 57 */         j++;
/* 58 */         if (localST.contains((Comparable)localObject)) {
/* 59 */           localST.put((Comparable)localObject, Integer.valueOf(((Integer)localST.get((Comparable)localObject)).intValue() + 1));
/*    */         }
/*    */         else {
/* 62 */           localST.put((Comparable)localObject, Integer.valueOf(1));
/* 63 */           i++;
/*    */         }
/*    */       }
/*    */     }
/*    */ 
/* 68 */     Object localObject = "";
/* 69 */     localST.put((Comparable)localObject, Integer.valueOf(0));
/* 70 */     for (String str : localST.keys()) {
/* 71 */       if (((Integer)localST.get(str)).intValue() > ((Integer)localST.get((Comparable)localObject)).intValue()) {
/* 72 */         localObject = str;
/*    */       }
/*    */     }
/* 75 */     StdOut.println((String)localObject + " " + localST.get((Comparable)localObject));
/* 76 */     StdOut.println("distinct = " + i);
/* 77 */     StdOut.println("words    = " + j);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FrequencyCounter
 * JD-Core Version:    0.6.2
 */