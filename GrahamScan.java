/*    */ import java.util.Arrays;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class GrahamScan
/*    */ {
/* 16 */   private Stack<Point2D> hull = new Stack();
/*    */ 
/*    */   public GrahamScan(Point2D[] paramArrayOfPoint2D)
/*    */   {
/* 21 */     int i = paramArrayOfPoint2D.length;
/* 22 */     Point2D[] arrayOfPoint2D = new Point2D[i];
/* 23 */     for (int j = 0; j < i; j++) {
/* 24 */       arrayOfPoint2D[j] = paramArrayOfPoint2D[j];
/*    */     }
/*    */ 
/* 29 */     Arrays.sort(arrayOfPoint2D);
/*    */ 
/* 33 */     Arrays.sort(arrayOfPoint2D, 1, i, arrayOfPoint2D[0].POLAR_ORDER);
/*    */ 
/* 35 */     this.hull.push(arrayOfPoint2D[0]);
/*    */ 
/* 39 */     for (j = 1; (j < i) && 
/* 40 */       (arrayOfPoint2D[0].equals(arrayOfPoint2D[j])); j++);
/* 41 */     if (j == i) return;
/*    */ 
/* 45 */     for (int k = j + 1; (k < i) && 
/* 46 */       (Point2D.ccw(arrayOfPoint2D[0], arrayOfPoint2D[j], arrayOfPoint2D[k]) == 0); k++);
/* 47 */     this.hull.push(arrayOfPoint2D[(k - 1)]);
/*    */ 
/* 50 */     for (int m = k; m < i; m++) {
/* 51 */       Point2D localPoint2D = (Point2D)this.hull.pop();
/* 52 */       while (Point2D.ccw((Point2D)this.hull.peek(), localPoint2D, arrayOfPoint2D[m]) <= 0) {
/* 53 */         localPoint2D = (Point2D)this.hull.pop();
/*    */       }
/* 55 */       this.hull.push(localPoint2D);
/* 56 */       this.hull.push(arrayOfPoint2D[m]);
/*    */     }
/*    */ 
/* 59 */     assert (isConvex());
/*    */   }
/*    */ 
/*    */   public Iterable<Point2D> hull()
/*    */   {
/* 64 */     Stack localStack = new Stack();
/*    */     Point2D localPoint2D;
/* 65 */     for (Iterator localIterator = this.hull.iterator(); localIterator.hasNext(); localStack.push(localPoint2D)) localPoint2D = (Point2D)localIterator.next();
/* 66 */     return localStack;
/*    */   }
/*    */ 
/*    */   private boolean isConvex()
/*    */   {
/* 71 */     int i = this.hull.size();
/* 72 */     if (i <= 2) return true;
/*    */ 
/* 74 */     Point2D[] arrayOfPoint2D = new Point2D[i];
/* 75 */     int j = 0;
/* 76 */     for (Point2D localPoint2D : hull()) {
/* 77 */       arrayOfPoint2D[(j++)] = localPoint2D;
/*    */     }
/*    */ 
/* 80 */     for (int k = 0; k < i; k++) {
/* 81 */       if (Point2D.ccw(arrayOfPoint2D[k], arrayOfPoint2D[((k + 1) % i)], arrayOfPoint2D[((k + 2) % i)]) <= 0) {
/* 82 */         return false;
/*    */       }
/*    */     }
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 90 */     int i = StdIn.readInt();
/* 91 */     Point2D[] arrayOfPoint2D = new Point2D[i];
/* 92 */     for (int j = 0; j < i; j++) {
/* 93 */       int k = StdIn.readInt();
/* 94 */       int m = StdIn.readInt();
/* 95 */       arrayOfPoint2D[j] = new Point2D(k, m);
/*    */     }
/* 97 */     GrahamScan localGrahamScan = new GrahamScan(arrayOfPoint2D);
/* 98 */     for (Point2D localPoint2D : localGrahamScan.hull())
/* 99 */       StdOut.println(localPoint2D);
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     GrahamScan
 * JD-Core Version:    0.6.2
 */