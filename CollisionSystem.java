/*     */ import java.awt.Color;
/*     */ 
/*     */ public class CollisionSystem
/*     */ {
/*     */   private MinPQ<CollisionSystem.Event> pq;
/*  15 */   private double t = 0.0D;
/*  16 */   private double hz = 0.5D;
/*     */   private Particle[] particles;
/*     */ 
/*     */   public CollisionSystem(Particle[] paramArrayOfParticle)
/*     */   {
/*  21 */     this.particles = paramArrayOfParticle;
/*     */   }
/*     */ 
/*     */   private void predict(Particle paramParticle, double paramDouble)
/*     */   {
/*  26 */     if (paramParticle == null) return;
/*     */ 
/*  29 */     for (int i = 0; i < this.particles.length; i++) {
/*  30 */       double d2 = paramParticle.timeToHit(this.particles[i]);
/*  31 */       if (this.t + d2 <= paramDouble) {
/*  32 */         this.pq.insert(new CollisionSystem.Event(this.t + d2, paramParticle, this.particles[i]));
/*     */       }
/*     */     }
/*     */ 
/*  36 */     double d1 = paramParticle.timeToHitVerticalWall();
/*  37 */     double d3 = paramParticle.timeToHitHorizontalWall();
/*  38 */     if (this.t + d1 <= paramDouble) this.pq.insert(new CollisionSystem.Event(this.t + d1, paramParticle, null));
/*  39 */     if (this.t + d3 <= paramDouble) this.pq.insert(new CollisionSystem.Event(this.t + d3, null, paramParticle));
/*     */   }
/*     */ 
/*     */   private void redraw(double paramDouble)
/*     */   {
/*  44 */     StdDraw.clear();
/*  45 */     for (int i = 0; i < this.particles.length; i++) {
/*  46 */       this.particles[i].draw();
/*     */     }
/*  48 */     StdDraw.show(20);
/*  49 */     if (this.t < paramDouble)
/*  50 */       this.pq.insert(new CollisionSystem.Event(this.t + 1.0D / this.hz, null, null));
/*     */   }
/*     */ 
/*     */   public void simulate(double paramDouble)
/*     */   {
/*  61 */     this.pq = new MinPQ();
/*  62 */     for (int i = 0; i < this.particles.length; i++) {
/*  63 */       predict(this.particles[i], paramDouble);
/*     */     }
/*  65 */     this.pq.insert(new CollisionSystem.Event(0.0D, null, null));
/*     */ 
/*  69 */     while (!this.pq.isEmpty())
/*     */     {
/*  72 */       CollisionSystem.Event localEvent = (CollisionSystem.Event)this.pq.delMin();
/*  73 */       if (localEvent.isValid()) {
/*  74 */         Particle localParticle1 = localEvent.a;
/*  75 */         Particle localParticle2 = localEvent.b;
/*     */ 
/*  78 */         for (int j = 0; j < this.particles.length; j++)
/*  79 */           this.particles[j].move(localEvent.time - this.t);
/*  80 */         this.t = localEvent.time;
/*     */ 
/*  83 */         if ((localParticle1 != null) && (localParticle2 != null)) localParticle1.bounceOff(localParticle2);
/*  84 */         else if ((localParticle1 != null) && (localParticle2 == null)) localParticle1.bounceOffVerticalWall();
/*  85 */         else if ((localParticle1 == null) && (localParticle2 != null)) localParticle2.bounceOffHorizontalWall();
/*  86 */         else if ((localParticle1 == null) && (localParticle2 == null)) redraw(paramDouble);
/*     */ 
/*  89 */         predict(localParticle1, paramDouble);
/*  90 */         predict(localParticle2, paramDouble);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 147 */     StdDraw.setXscale(0.04545454545454546D, 0.9545454545454546D);
/* 148 */     StdDraw.setYscale(0.04545454545454546D, 0.9545454545454546D);
/*     */ 
/* 156 */     StdDraw.show(0);
/*     */     int i;
/*     */     Particle[] arrayOfParticle;
/*     */     int j;
/* 162 */     if (paramArrayOfString.length == 1) {
/* 163 */       i = Integer.parseInt(paramArrayOfString[0]);
/* 164 */       arrayOfParticle = new Particle[i];
/* 165 */       for (j = 0; j < i; j++) arrayOfParticle[j] = new Particle();
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 170 */       i = StdIn.readInt();
/* 171 */       arrayOfParticle = new Particle[i];
/* 172 */       for (j = 0; j < i; j++) {
/* 173 */         double d1 = StdIn.readDouble();
/* 174 */         double d2 = StdIn.readDouble();
/* 175 */         double d3 = StdIn.readDouble();
/* 176 */         double d4 = StdIn.readDouble();
/* 177 */         double d5 = StdIn.readDouble();
/* 178 */         double d6 = StdIn.readDouble();
/* 179 */         int k = StdIn.readInt();
/* 180 */         int m = StdIn.readInt();
/* 181 */         int n = StdIn.readInt();
/* 182 */         Color localColor = new Color(k, m, n);
/* 183 */         arrayOfParticle[j] = new Particle(d1, d2, d3, d4, d5, d6, localColor);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 188 */     CollisionSystem localCollisionSystem = new CollisionSystem(arrayOfParticle);
/* 189 */     localCollisionSystem.simulate(10000.0D);
/*     */   }
/*     */ 
/*     */   private static class Event
/*     */     implements Comparable<Event>
/*     */   {
/*     */     private final double time;
/*     */     private final Particle a;
/*     */     private final Particle b;
/*     */     private final int countA;
/*     */     private final int countB;
/*     */ 
/*     */     public Event(double paramDouble, Particle paramParticle1, Particle paramParticle2)
/*     */     {
/* 114 */       this.time = paramDouble;
/* 115 */       this.a = paramParticle1;
/* 116 */       this.b = paramParticle2;
/* 117 */       if (paramParticle1 != null) this.countA = paramParticle1.count(); else
/* 118 */         this.countA = -1;
/* 119 */       if (paramParticle2 != null) this.countB = paramParticle2.count(); else
/* 120 */         this.countB = -1;
/*     */     }
/*     */ 
/*     */     public int compareTo(Event paramEvent)
/*     */     {
/* 125 */       if (this.time < paramEvent.time) return -1;
/* 126 */       if (this.time > paramEvent.time) return 1;
/* 127 */       return 0;
/*     */     }
/*     */ 
/*     */     public boolean isValid()
/*     */     {
/* 132 */       if ((this.a != null) && (this.a.count() != this.countA)) return false;
/* 133 */       if ((this.b != null) && (this.b.count() != this.countB)) return false;
/* 134 */       return true;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     CollisionSystem
 * JD-Core Version:    0.6.2
 */