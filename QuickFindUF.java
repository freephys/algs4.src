/*     */ public class QuickFindUF
/*     */ {
/*     */   private int[] id;
/*     */   private int count;
/*     */ 
/*     */   public QuickFindUF(int paramInt)
/*     */   {
/*  37 */     this.count = paramInt;
/*  38 */     this.id = new int[paramInt];
/*  39 */     for (int i = 0; i < paramInt; i++)
/*  40 */       this.id[i] = i;
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/*  48 */     return this.count;
/*     */   }
/*     */ 
/*     */   public int find(int paramInt)
/*     */   {
/*  58 */     return this.id[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean connected(int paramInt1, int paramInt2)
/*     */   {
/*  70 */     return this.id[paramInt1] == this.id[paramInt2];
/*     */   }
/*     */ 
/*     */   public void union(int paramInt1, int paramInt2)
/*     */   {
/*  81 */     if (connected(paramInt1, paramInt2)) return;
/*  82 */     int i = this.id[paramInt1];
/*  83 */     for (int j = 0; j < this.id.length; j++)
/*  84 */       if (this.id[j] == i) this.id[j] = this.id[paramInt2];
/*  85 */     this.count -= 1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  95 */     int i = StdIn.readInt();
/*  96 */     QuickFindUF localQuickFindUF = new QuickFindUF(i);
/*  97 */     while (!StdIn.isEmpty()) {
/*  98 */       int j = StdIn.readInt();
/*  99 */       int k = StdIn.readInt();
/* 100 */       if (!localQuickFindUF.connected(j, k)) {
/* 101 */         localQuickFindUF.union(j, k);
/* 102 */         StdOut.println(j + " " + k);
/*     */       }
/*     */     }
/* 104 */     StdOut.println(localQuickFindUF.count() + " components");
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     QuickFindUF
 * JD-Core Version:    0.6.2
 */