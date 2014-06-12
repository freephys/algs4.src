/*     */ import java.awt.Color;
/*     */ 
/*     */ public class Particle
/*     */ {
/*     */   private static final double INFINITY = (1.0D / 0.0D);
/*     */   private double rx;
/*     */   private double ry;
/*     */   private double vx;
/*     */   private double vy;
/*     */   private double radius;
/*     */   private double mass;
/*     */   private Color color;
/*     */   private int count;
/*     */ 
/*     */   public Particle(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, Color paramColor)
/*     */   {
/*  24 */     this.vx = paramDouble3;
/*  25 */     this.vy = paramDouble4;
/*  26 */     this.rx = paramDouble1;
/*  27 */     this.ry = paramDouble2;
/*  28 */     this.radius = paramDouble5;
/*  29 */     this.mass = paramDouble6;
/*  30 */     this.color = paramColor;
/*     */   }
/*     */ 
/*     */   public Particle()
/*     */   {
/*  35 */     this.rx = Math.random();
/*  36 */     this.ry = Math.random();
/*  37 */     this.vx = (0.01D * (Math.random() - 0.5D));
/*  38 */     this.vy = (0.01D * (Math.random() - 0.5D));
/*  39 */     this.radius = 0.01D;
/*  40 */     this.mass = 0.5D;
/*  41 */     this.color = Color.BLACK;
/*     */   }
/*     */ 
/*     */   public void move(double paramDouble)
/*     */   {
/*  46 */     this.rx += this.vx * paramDouble;
/*  47 */     this.ry += this.vy * paramDouble;
/*     */   }
/*     */ 
/*     */   public void draw()
/*     */   {
/*  52 */     StdDraw.setPenColor(this.color);
/*  53 */     StdDraw.filledCircle(this.rx, this.ry, this.radius);
/*     */   }
/*     */ 
/*     */   public int count() {
/*  57 */     return this.count;
/*     */   }
/*     */ 
/*     */   public double timeToHit(Particle paramParticle)
/*     */   {
/*  62 */     Particle localParticle = this;
/*  63 */     if (localParticle == paramParticle) return (1.0D / 0.0D);
/*  64 */     double d1 = paramParticle.rx - localParticle.rx;
/*  65 */     double d2 = paramParticle.ry - localParticle.ry;
/*  66 */     double d3 = paramParticle.vx - localParticle.vx;
/*  67 */     double d4 = paramParticle.vy - localParticle.vy;
/*  68 */     double d5 = d1 * d3 + d2 * d4;
/*  69 */     if (d5 > 0.0D) return (1.0D / 0.0D);
/*  70 */     double d6 = d3 * d3 + d4 * d4;
/*  71 */     double d7 = d1 * d1 + d2 * d2;
/*  72 */     double d8 = localParticle.radius + paramParticle.radius;
/*  73 */     double d9 = d5 * d5 - d6 * (d7 - d8 * d8);
/*     */ 
/*  75 */     if (d9 < 0.0D) return (1.0D / 0.0D);
/*  76 */     return -(d5 + Math.sqrt(d9)) / d6;
/*     */   }
/*     */ 
/*     */   public double timeToHitVerticalWall()
/*     */   {
/*  81 */     if (this.vx > 0.0D) return (1.0D - this.rx - this.radius) / this.vx;
/*  82 */     if (this.vx < 0.0D) return (this.radius - this.rx) / this.vx;
/*  83 */     return (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public double timeToHitHorizontalWall()
/*     */   {
/*  88 */     if (this.vy > 0.0D) return (1.0D - this.ry - this.radius) / this.vy;
/*  89 */     if (this.vy < 0.0D) return (this.radius - this.ry) / this.vy;
/*  90 */     return (1.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public void bounceOff(Particle paramParticle)
/*     */   {
/*  95 */     double d1 = paramParticle.rx - this.rx;
/*  96 */     double d2 = paramParticle.ry - this.ry;
/*  97 */     double d3 = paramParticle.vx - this.vx;
/*  98 */     double d4 = paramParticle.vy - this.vy;
/*  99 */     double d5 = d1 * d3 + d2 * d4;
/* 100 */     double d6 = this.radius + paramParticle.radius;
/*     */ 
/* 103 */     double d7 = 2.0D * this.mass * paramParticle.mass * d5 / ((this.mass + paramParticle.mass) * d6);
/* 104 */     double d8 = d7 * d1 / d6;
/* 105 */     double d9 = d7 * d2 / d6;
/*     */ 
/* 108 */     this.vx += d8 / this.mass;
/* 109 */     this.vy += d9 / this.mass;
/* 110 */     paramParticle.vx -= d8 / paramParticle.mass;
/* 111 */     paramParticle.vy -= d9 / paramParticle.mass;
/*     */ 
/* 114 */     this.count += 1;
/* 115 */     paramParticle.count += 1;
/*     */   }
/*     */ 
/*     */   public void bounceOffVerticalWall()
/*     */   {
/* 120 */     this.vx = (-this.vx);
/* 121 */     this.count += 1;
/*     */   }
/*     */ 
/*     */   public void bounceOffHorizontalWall()
/*     */   {
/* 126 */     this.vy = (-this.vy);
/* 127 */     this.count += 1;
/*     */   }
/*     */ 
/*     */   public double kineticEnergy() {
/* 131 */     return 0.5D * this.mass * (this.vx * this.vx + this.vy * this.vy);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Particle
 * JD-Core Version:    0.6.2
 */