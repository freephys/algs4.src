/*    */ public class Counter
/*    */   implements Comparable<Counter>
/*    */ {
/*    */   private final String name;
/*    */   private int count;
/*    */ 
/*    */   public Counter(String paramString)
/*    */   {
/* 40 */     this.name = paramString;
/*    */   }
/*    */ 
/*    */   public void increment()
/*    */   {
/* 47 */     this.count += 1;
/*    */   }
/*    */ 
/*    */   public int tally()
/*    */   {
/* 54 */     return this.count;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 61 */     return this.count + " " + this.name;
/*    */   }
/*    */ 
/*    */   public int compareTo(Counter paramCounter)
/*    */   {
/* 68 */     if (this.count < paramCounter.count) return -1;
/* 69 */     if (this.count > paramCounter.count) return 1;
/* 70 */     return 0;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 79 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 80 */     int j = Integer.parseInt(paramArrayOfString[1]);
/*    */ 
/* 83 */     Counter[] arrayOfCounter = new Counter[i];
/* 84 */     for (int k = 0; k < i; k++) {
/* 85 */       arrayOfCounter[k] = new Counter("counter" + k);
/*    */     }
/*    */ 
/* 89 */     for (k = 0; k < j; k++) {
/* 90 */       arrayOfCounter[StdRandom.uniform(i)].increment();
/*    */     }
/*    */ 
/* 94 */     for (k = 0; k < i; k++)
/* 95 */       StdOut.println(arrayOfCounter[k]);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Counter
 * JD-Core Version:    0.6.2
 */