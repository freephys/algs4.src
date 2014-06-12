/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class MinPQ<Key>
/*     */   implements Iterable<Key>
/*     */ {
/*     */   private Key[] pq;
/*     */   private int N;
/*     */   private Comparator<Key> comparator;
/*     */ 
/*     */   public MinPQ(int paramInt)
/*     */   {
/*  52 */     this.pq = ((Object[])new Object[paramInt + 1]);
/*  53 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public MinPQ()
/*     */   {
/*  60 */     this(1);
/*     */   }
/*     */ 
/*     */   public MinPQ(int paramInt, Comparator<Key> paramComparator)
/*     */   {
/*  70 */     this.comparator = paramComparator;
/*  71 */     this.pq = ((Object[])new Object[paramInt + 1]);
/*  72 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public MinPQ(Comparator<Key> paramComparator)
/*     */   {
/*  79 */     this(1, paramComparator);
/*     */   }
/*     */ 
/*     */   public MinPQ(Key[] paramArrayOfKey)
/*     */   {
/*  87 */     this.N = paramArrayOfKey.length;
/*  88 */     this.pq = ((Object[])new Object[paramArrayOfKey.length + 1]);
/*  89 */     for (int i = 0; i < this.N; i++)
/*  90 */       this.pq[(i + 1)] = paramArrayOfKey[i];
/*  91 */     for (i = this.N / 2; i >= 1; i--)
/*  92 */       sink(i);
/*  93 */     assert (isMinHeap());
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 101 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 109 */     return this.N;
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 118 */     if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
/* 119 */     return this.pq[1];
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/* 124 */     assert (paramInt > this.N);
/* 125 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/* 126 */     for (int i = 1; i <= this.N; i++) arrayOfObject[i] = this.pq[i];
/* 127 */     this.pq = arrayOfObject;
/*     */   }
/*     */ 
/*     */   public void insert(Key paramKey)
/*     */   {
/* 136 */     if (this.N == this.pq.length - 1) resize(2 * this.pq.length);
/*     */ 
/* 139 */     this.pq[(++this.N)] = paramKey;
/* 140 */     swim(this.N);
/* 141 */     assert (isMinHeap());
/*     */   }
/*     */ 
/*     */   public Key delMin()
/*     */   {
/* 150 */     if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
/* 151 */     exch(1, this.N);
/* 152 */     Object localObject = this.pq[(this.N--)];
/* 153 */     sink(1);
/* 154 */     this.pq[(this.N + 1)] = null;
/* 155 */     if ((this.N > 0) && (this.N == (this.pq.length - 1) / 4)) resize(this.pq.length / 2);
/* 156 */     assert (isMinHeap());
/* 157 */     return localObject;
/*     */   }
/*     */ 
/*     */   private void swim(int paramInt)
/*     */   {
/* 166 */     while ((paramInt > 1) && (greater(paramInt / 2, paramInt))) {
/* 167 */       exch(paramInt, paramInt / 2);
/* 168 */       paramInt /= 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sink(int paramInt) {
/* 173 */     while (2 * paramInt <= this.N) {
/* 174 */       int i = 2 * paramInt;
/* 175 */       if ((i < this.N) && (greater(i, i + 1))) i++;
/* 176 */       if (!greater(paramInt, i)) break;
/* 177 */       exch(paramInt, i);
/* 178 */       paramInt = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean greater(int paramInt1, int paramInt2)
/*     */   {
/* 186 */     if (this.comparator == null) {
/* 187 */       return ((Comparable)this.pq[paramInt1]).compareTo(this.pq[paramInt2]) > 0;
/*     */     }
/*     */ 
/* 190 */     return this.comparator.compare(this.pq[paramInt1], this.pq[paramInt2]) > 0;
/*     */   }
/*     */ 
/*     */   private void exch(int paramInt1, int paramInt2)
/*     */   {
/* 195 */     Object localObject = this.pq[paramInt1];
/* 196 */     this.pq[paramInt1] = this.pq[paramInt2];
/* 197 */     this.pq[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private boolean isMinHeap()
/*     */   {
/* 202 */     return isMinHeap(1);
/*     */   }
/*     */ 
/*     */   private boolean isMinHeap(int paramInt)
/*     */   {
/* 207 */     if (paramInt > this.N) return true;
/* 208 */     int i = 2 * paramInt; int j = 2 * paramInt + 1;
/* 209 */     if ((i <= this.N) && (greater(paramInt, i))) return false;
/* 210 */     if ((j <= this.N) && (greater(paramInt, j))) return false;
/* 211 */     return (isMinHeap(i)) && (isMinHeap(j));
/*     */   }
/*     */ 
/*     */   public Iterator<Key> iterator()
/*     */   {
/* 225 */     return new MinPQ.HeapIterator();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 253 */     MinPQ localMinPQ = new MinPQ();
/* 254 */     while (!StdIn.isEmpty()) {
/* 255 */       String str = StdIn.readString();
/* 256 */       if (!str.equals("-")) localMinPQ.insert(str);
/* 257 */       else if (!localMinPQ.isEmpty()) StdOut.print((String)localMinPQ.delMin() + " ");
/*     */     }
/* 259 */     StdOut.println("(" + localMinPQ.size() + " left on pq)");
/*     */   }
/*     */ 
/*     */   private class HeapIterator
/*     */     implements Iterator<Key>
/*     */   {
/*     */     private MinPQ<Key> copy;
/*     */ 
/*     */     public HeapIterator()
/*     */     {
/* 234 */       if (MinPQ.this.comparator == null) this.copy = new MinPQ(MinPQ.this.size()); else
/* 235 */         this.copy = new MinPQ(MinPQ.this.size(), MinPQ.this.comparator);
/* 236 */       for (int i = 1; i <= MinPQ.this.N; i++)
/* 237 */         this.copy.insert(MinPQ.this.pq[i]); 
/*     */     }
/*     */ 
/* 240 */     public boolean hasNext() { return !this.copy.isEmpty(); } 
/* 241 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Key next() {
/* 244 */       if (!hasNext()) throw new NoSuchElementException();
/* 245 */       return this.copy.delMin();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     MinPQ
 * JD-Core Version:    0.6.2
 */