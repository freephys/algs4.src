/*     */ public class QuickUnionUF
/*     */ {
/*     */   private int[] id;
/*     */   private int count;
/*     */ 
/*     */   public QuickUnionUF(int paramInt)
/*     */   {
/*  38 */     this.id = new int[paramInt];
/*  39 */     this.count = paramInt;
/*  40 */     for (int i = 0; i < paramInt; i++)
/*  41 */       this.id[i] = i;
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/*  50 */     return this.count;
/*     */   }
/*     */ 
/*     */   public int find(int paramInt)
/*     */   {
/*  60 */     while (paramInt != this.id[paramInt])
/*  61 */       paramInt = this.id[paramInt];
/*  62 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public boolean connected(int paramInt1, int paramInt2)
/*     */   {
/*  74 */     return find(paramInt1) == find(paramInt2);
/*     */   }
/*     */ 
/*     */   public void union(int paramInt1, int paramInt2)
/*     */   {
/*  86 */     int i = find(paramInt1);
/*  87 */     int j = find(paramInt2);
/*  88 */     if (i == j) return;
/*  89 */     this.id[i] = j;
/*  90 */     this.count -= 1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 100 */     int i = StdIn.readInt();
/* 101 */     QuickUnionUF localQuickUnionUF = new QuickUnionUF(i);
/* 102 */     while (!StdIn.isEmpty()) {
/* 103 */       int j = StdIn.readInt();
/* 104 */       int k = StdIn.readInt();
/* 105 */       if (!localQuickUnionUF.connected(j, k)) {
/* 106 */         localQuickUnionUF.union(j, k);
/* 107 */         StdOut.println(j + " " + k);
/*     */       }
/*     */     }
/* 109 */     StdOut.println(localQuickUnionUF.count() + " components");
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     QuickUnionUF
 * JD-Core Version:    0.6.2
 */