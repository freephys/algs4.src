/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class MaxPQ<Key>
/*     */   implements Iterable<Key>
/*     */ {
/*     */   private Key[] pq;
/*     */   private int N;
/*     */   private Comparator<Key> comparator;
/*     */ 
/*     */   public MaxPQ(int paramInt)
/*     */   {
/*  54 */     this.pq = ((Object[])new Object[paramInt + 1]);
/*  55 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public MaxPQ()
/*     */   {
/*  62 */     this(1);
/*     */   }
/*     */ 
/*     */   public MaxPQ(int paramInt, Comparator<Key> paramComparator)
/*     */   {
/*  72 */     this.comparator = paramComparator;
/*  73 */     this.pq = ((Object[])new Object[paramInt + 1]);
/*  74 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public MaxPQ(Comparator<Key> paramComparator)
/*     */   {
/*  82 */     this(1, paramComparator);
/*     */   }
/*     */ 
/*     */   public MaxPQ(Key[] paramArrayOfKey)
/*     */   {
/*  91 */     this.N = paramArrayOfKey.length;
/*  92 */     this.pq = ((Object[])new Object[paramArrayOfKey.length + 1]);
/*  93 */     for (int i = 0; i < this.N; i++)
/*  94 */       this.pq[(i + 1)] = paramArrayOfKey[i];
/*  95 */     for (i = this.N / 2; i >= 1; i--)
/*  96 */       sink(i);
/*  97 */     assert (isMaxHeap());
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 107 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 115 */     return this.N;
/*     */   }
/*     */ 
/*     */   public Key max()
/*     */   {
/* 124 */     if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
/* 125 */     return this.pq[1];
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/* 130 */     assert (paramInt > this.N);
/* 131 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/* 132 */     for (int i = 1; i <= this.N; i++) arrayOfObject[i] = this.pq[i];
/* 133 */     this.pq = arrayOfObject;
/*     */   }
/*     */ 
/*     */   public void insert(Key paramKey)
/*     */   {
/* 144 */     if (this.N >= this.pq.length - 1) resize(2 * this.pq.length);
/*     */ 
/* 147 */     this.pq[(++this.N)] = paramKey;
/* 148 */     swim(this.N);
/* 149 */     assert (isMaxHeap());
/*     */   }
/*     */ 
/*     */   public Key delMax()
/*     */   {
/* 158 */     if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
/* 159 */     Object localObject = this.pq[1];
/* 160 */     exch(1, this.N--);
/* 161 */     sink(1);
/* 162 */     this.pq[(this.N + 1)] = null;
/* 163 */     if ((this.N > 0) && (this.N == (this.pq.length - 1) / 4)) resize(this.pq.length / 2);
/* 164 */     assert (isMaxHeap());
/* 165 */     return localObject;
/*     */   }
/*     */ 
/*     */   private void swim(int paramInt)
/*     */   {
/* 174 */     while ((paramInt > 1) && (less(paramInt / 2, paramInt))) {
/* 175 */       exch(paramInt, paramInt / 2);
/* 176 */       paramInt /= 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sink(int paramInt) {
/* 181 */     while (2 * paramInt <= this.N) {
/* 182 */       int i = 2 * paramInt;
/* 183 */       if ((i < this.N) && (less(i, i + 1))) i++;
/* 184 */       if (!less(paramInt, i)) break;
/* 185 */       exch(paramInt, i);
/* 186 */       paramInt = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean less(int paramInt1, int paramInt2)
/*     */   {
/* 194 */     if (this.comparator == null) {
/* 195 */       return ((Comparable)this.pq[paramInt1]).compareTo(this.pq[paramInt2]) < 0;
/*     */     }
/*     */ 
/* 198 */     return this.comparator.compare(this.pq[paramInt1], this.pq[paramInt2]) < 0;
/*     */   }
/*     */ 
/*     */   private void exch(int paramInt1, int paramInt2)
/*     */   {
/* 203 */     Object localObject = this.pq[paramInt1];
/* 204 */     this.pq[paramInt1] = this.pq[paramInt2];
/* 205 */     this.pq[paramInt2] = localObject;
/*     */   }
/*     */ 
/*     */   private boolean isMaxHeap()
/*     */   {
/* 210 */     return isMaxHeap(1);
/*     */   }
/*     */ 
/*     */   private boolean isMaxHeap(int paramInt)
/*     */   {
/* 215 */     if (paramInt > this.N) return true;
/* 216 */     int i = 2 * paramInt; int j = 2 * paramInt + 1;
/* 217 */     if ((i <= this.N) && (less(paramInt, i))) return false;
/* 218 */     if ((j <= this.N) && (less(paramInt, j))) return false;
/* 219 */     return (isMaxHeap(i)) && (isMaxHeap(j));
/*     */   }
/*     */ 
/*     */   public Iterator<Key> iterator()
/*     */   {
/* 233 */     return new MaxPQ.HeapIterator();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 262 */     MaxPQ localMaxPQ = new MaxPQ();
/* 263 */     while (!StdIn.isEmpty()) {
/* 264 */       String str = StdIn.readString();
/* 265 */       if (!str.equals("-")) localMaxPQ.insert(str);
/* 266 */       else if (!localMaxPQ.isEmpty()) StdOut.print((String)localMaxPQ.delMax() + " ");
/*     */     }
/* 268 */     StdOut.println("(" + localMaxPQ.size() + " left on pq)");
/*     */   }
/*     */ 
/*     */   private class HeapIterator
/*     */     implements Iterator<Key>
/*     */   {
/*     */     private MaxPQ<Key> copy;
/*     */ 
/*     */     public HeapIterator()
/*     */     {
/* 243 */       if (MaxPQ.this.comparator == null) this.copy = new MaxPQ(MaxPQ.this.size()); else
/* 244 */         this.copy = new MaxPQ(MaxPQ.this.size(), MaxPQ.this.comparator);
/* 245 */       for (int i = 1; i <= MaxPQ.this.N; i++)
/* 246 */         this.copy.insert(MaxPQ.this.pq[i]); 
/*     */     }
/*     */ 
/* 249 */     public boolean hasNext() { return !this.copy.isEmpty(); } 
/* 250 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Key next() {
/* 253 */       if (!hasNext()) throw new NoSuchElementException();
/* 254 */       return this.copy.delMax();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     MaxPQ
 * JD-Core Version:    0.6.2
 */