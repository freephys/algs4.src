/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.lang.management.ThreadMXBean;
/*    */ 
/*    */ public class StopwatchCPU
/*    */ {
/*    */   private final ThreadMXBean threadTimer;
/*    */   private final long start;
/*    */   private static final double NANOSECONDS_PER_SECOND = 1000000000.0D;
/*    */ 
/*    */   public StopwatchCPU()
/*    */   {
/* 31 */     this.threadTimer = ManagementFactory.getThreadMXBean();
/* 32 */     this.start = this.threadTimer.getCurrentThreadCpuTime();
/*    */   }
/*    */ 
/*    */   public double elapsedTime()
/*    */   {
/* 39 */     long l = this.threadTimer.getCurrentThreadCpuTime();
/* 40 */     return (l - this.start) / 1000000000.0D;
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     StopwatchCPU
 * JD-Core Version:    0.6.2
 */