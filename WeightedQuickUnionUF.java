/*     */ public class WeightedQuickUnionUF
/*     */ {
/*     */   private int[] id;
/*     */   private int[] sz;
/*     */   private int count;
/*     */ 
/*     */   public WeightedQuickUnionUF(int paramInt)
/*     */   {
/*  39 */     this.count = paramInt;
/*  40 */     this.id = new int[paramInt];
/*  41 */     this.sz = new int[paramInt];
/*  42 */     for (int i = 0; i < paramInt; i++) {
/*  43 */       this.id[i] = i;
/*  44 */       this.sz[i] = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/*  53 */     return this.count;
/*     */   }
/*     */ 
/*     */   public int find(int paramInt)
/*     */   {
/*  63 */     while (paramInt != this.id[paramInt])
/*  64 */       paramInt = this.id[paramInt];
/*  65 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public boolean connected(int paramInt1, int paramInt2)
/*     */   {
/*  77 */     return find(paramInt1) == find(paramInt2);
/*     */   }
/*     */ 
/*     */   public void union(int paramInt1, int paramInt2)
/*     */   {
/*  89 */     int i = find(paramInt1);
/*  90 */     int j = find(paramInt2);
/*  91 */     if (i == j) return;
/*     */ 
/*  94 */     if (this.sz[i] < this.sz[j]) { this.id[i] = j; this.sz[j] += this.sz[i]; } else {
/*  95 */       this.id[j] = i; this.sz[i] += this.sz[j];
/*  96 */     }this.count -= 1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 107 */     int i = StdIn.readInt();
/* 108 */     WeightedQuickUnionUF localWeightedQuickUnionUF = new WeightedQuickUnionUF(i);
/* 109 */     while (!StdIn.isEmpty()) {
/* 110 */       int j = StdIn.readInt();
/* 111 */       int k = StdIn.readInt();
/* 112 */       if (!localWeightedQuickUnionUF.connected(j, k)) {
/* 113 */         localWeightedQuickUnionUF.union(j, k);
/* 114 */         StdOut.println(j + " " + k);
/*     */       }
/*     */     }
/* 116 */     StdOut.println(localWeightedQuickUnionUF.count() + " components");
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     WeightedQuickUnionUF
 * JD-Core Version:    0.6.2
 */