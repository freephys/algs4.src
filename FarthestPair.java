/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class FarthestPair
/*    */ {
/*    */   private Point2D best1;
/*    */   private Point2D best2;
/* 20 */   private double bestDistance = (-1.0D / 0.0D);
/*    */ 
/*    */   public FarthestPair(Point2D[] paramArrayOfPoint2D) {
/* 23 */     GrahamScan localGrahamScan = new GrahamScan(paramArrayOfPoint2D);
/*    */ 
/* 26 */     if (paramArrayOfPoint2D.length <= 1) return;
/*    */ 
/* 29 */     Point2D localPoint2D1 = 0;
/* 30 */     for (Object localObject = localGrahamScan.hull().iterator(); ((Iterator)localObject).hasNext(); ) { Point2D localPoint2D2 = (Point2D)((Iterator)localObject).next();
/* 31 */       localPoint2D1++;
/*    */     }
/*    */ 
/* 34 */     localObject = new Point2D[localPoint2D1 + 1];
/* 35 */     int i = 1;
/* 36 */     for (Iterator localIterator = localGrahamScan.hull().iterator(); localIterator.hasNext(); ) { localPoint2D4 = (Point2D)localIterator.next();
/* 37 */       localObject[(i++)] = localPoint2D4;
/*    */     }
/*    */ 
/* 41 */     if (localPoint2D1 == 1) return;
/*    */ 
/* 44 */     if (localPoint2D1 == 2) {
/* 45 */       this.best1 = localObject[1];
/* 46 */       this.best2 = localObject[2];
/* 47 */       this.bestDistance = this.best1.distanceTo(this.best2);
/* 48 */       return;
/*    */     }
/*    */ 
/* 52 */     Point2D localPoint2D3 = 2;
/* 53 */     while (Point2D.area2(localObject[localPoint2D1], localObject[(localPoint2D3 + 1)], localObject[1]) > Point2D.area2(localObject[localPoint2D1], localObject[localPoint2D3], localObject[1])) {
/* 54 */       localPoint2D3++;
/*    */     }
/*    */ 
/* 57 */     Point2D localPoint2D4 = localPoint2D3;
/* 58 */     for (int j = 1; j <= localPoint2D3; j++)
/*    */     {
/* 60 */       if (localObject[j].distanceTo(localObject[localPoint2D4]) > this.bestDistance) {
/* 61 */         this.best1 = localObject[j];
/* 62 */         this.best2 = localObject[localPoint2D4];
/* 63 */         this.bestDistance = localObject[j].distanceTo(localObject[localPoint2D4]);
/*    */       }
/* 65 */       while ((localPoint2D4 < localPoint2D1) && (Point2D.area2(localObject[j], localObject[(localPoint2D4 + 1)], localObject[(j + 1)]) > Point2D.area2(localObject[j], localObject[localPoint2D4], localObject[(j + 1)]))) {
/* 66 */         localPoint2D4++;
/*    */ 
/* 68 */         double d = localObject[j].distanceTo(localObject[localPoint2D4]);
/* 69 */         if (d > this.bestDistance) {
/* 70 */           this.best1 = localObject[j];
/* 71 */           this.best2 = localObject[localPoint2D4];
/* 72 */           this.bestDistance = localObject[j].distanceTo(localObject[localPoint2D4]);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/* 78 */   public Point2D either() { return this.best1; } 
/* 79 */   public Point2D other() { return this.best2; } 
/* 80 */   public double distance() { return this.bestDistance; }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 84 */     int i = StdIn.readInt();
/* 85 */     Point2D[] arrayOfPoint2D = new Point2D[i];
/* 86 */     for (int j = 0; j < i; j++) {
/* 87 */       int k = StdIn.readInt();
/* 88 */       int m = StdIn.readInt();
/* 89 */       arrayOfPoint2D[j] = new Point2D(k, m);
/*    */     }
/* 91 */     FarthestPair localFarthestPair = new FarthestPair(arrayOfPoint2D);
/* 92 */     StdOut.println(localFarthestPair.distance() + " from " + localFarthestPair.either() + " to " + localFarthestPair.other());
/*    */   }
/*    */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     FarthestPair
 * JD-Core Version:    0.6.2
 */