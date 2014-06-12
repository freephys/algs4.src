/*    */ public class Stopwatch
/*    */ {
/*    */   private final long start;
/*    */ 
/*    */   public Stopwatch()
/*    */   {
/* 27 */     this.start = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   public double elapsedTime()
/*    */   {
/* 35 */     long l = System.currentTimeMillis();
/* 36 */     return (l - this.start) / 1000.0D;
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Stopwatch
 * JD-Core Version:    0.6.2
 */