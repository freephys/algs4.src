/*     */ public class UF
/*     */ {
/*     */   private int[] id;
/*     */   private byte[] rank;
/*     */   private int count;
/*     */ 
/*     */   public UF(int paramInt)
/*     */   {
/* 106 */     if (paramInt < 0) throw new IllegalArgumentException();
/* 107 */     this.count = paramInt;
/* 108 */     this.id = new int[paramInt];
/* 109 */     this.rank = new byte[paramInt];
/* 110 */     for (int i = 0; i < paramInt; i++) {
/* 111 */       this.id[i] = i;
/* 112 */       this.rank[i] = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int find(int paramInt)
/*     */   {
/* 123 */     if ((paramInt < 0) || (paramInt >= this.id.length)) throw new IndexOutOfBoundsException();
/* 124 */     while (paramInt != this.id[paramInt]) {
/* 125 */       this.id[paramInt] = this.id[this.id[paramInt]];
/* 126 */       paramInt = this.id[paramInt];
/*     */     }
/* 128 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public int count()
/*     */   {
/* 136 */     return this.count;
/*     */   }
/*     */ 
/*     */   public boolean connected(int paramInt1, int paramInt2)
/*     */   {
/* 148 */     return find(paramInt1) == find(paramInt2);
/*     */   }
/*     */ 
/*     */   public void union(int paramInt1, int paramInt2)
/*     */   {
/* 161 */     int i = find(paramInt1);
/* 162 */     int j = find(paramInt2);
/* 163 */     if (i == j) return;
/*     */ 
/* 166 */     if (this.rank[i] < this.rank[j]) { this.id[i] = j;
/* 167 */     } else if (this.rank[i] > this.rank[j]) { this.id[j] = i;
/*     */     } else {
/* 169 */       this.id[j] = i;
/*     */       int tmp87_86 = i;
/*     */       byte[] tmp87_83 = this.rank; tmp87_83[tmp87_86] = ((byte)(tmp87_83[tmp87_86] + 1));
/*     */     }
/* 172 */     this.count -= 1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 184 */     int i = StdIn.readInt();
/* 185 */     UF localUF = new UF(i);
/* 186 */     while (!StdIn.isEmpty()) {
/* 187 */       int j = StdIn.readInt();
/* 188 */       int k = StdIn.readInt();
/* 189 */       if (!localUF.connected(j, k)) {
/* 190 */         localUF.union(j, k);
/* 191 */         StdOut.println(j + " " + k);
/*     */       }
/*     */     }
/* 193 */     StdOut.println(localUF.count() + " components");
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     UF
 * JD-Core Version:    0.6.2
 */