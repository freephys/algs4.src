/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IndexMinPQ<Key extends Comparable<Key>>
/*     */   implements Iterable<Integer>
/*     */ {
/*     */   private int NMAX;
/*     */   private int N;
/*     */   private int[] pq;
/*     */   private int[] qp;
/*     */   private Key[] keys;
/*     */ 
/*     */   public IndexMinPQ(int paramInt)
/*     */   {
/*  51 */     if (paramInt < 0) throw new IllegalArgumentException();
/*  52 */     this.NMAX = paramInt;
/*  53 */     this.keys = ((Comparable[])new Comparable[paramInt + 1]);
/*  54 */     this.pq = new int[paramInt + 1];
/*  55 */     this.qp = new int[paramInt + 1];
/*  56 */     for (int i = 0; i <= paramInt; i++) this.qp[i] = -1;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  64 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(int paramInt)
/*     */   {
/*  73 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/*  74 */     return this.qp[paramInt] != -1;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  82 */     return this.N;
/*     */   }
/*     */ 
/*     */   public void insert(int paramInt, Key paramKey)
/*     */   {
/*  93 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/*  94 */     if (contains(paramInt)) throw new IllegalArgumentException("index is already in the priority queue");
/*  95 */     this.N += 1;
/*  96 */     this.qp[paramInt] = this.N;
/*  97 */     this.pq[this.N] = paramInt;
/*  98 */     this.keys[paramInt] = paramKey;
/*  99 */     swim(this.N);
/*     */   }
/*     */ 
/*     */   public int minIndex()
/*     */   {
/* 108 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 109 */     return this.pq[1];
/*     */   }
/*     */ 
/*     */   public Key minKey()
/*     */   {
/* 118 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 119 */     return this.keys[this.pq[1]];
/*     */   }
/*     */ 
/*     */   public int delMin()
/*     */   {
/* 128 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 129 */     int i = this.pq[1];
/* 130 */     exch(1, this.N--);
/* 131 */     sink(1);
/* 132 */     this.qp[i] = -1;
/* 133 */     this.keys[this.pq[(this.N + 1)]] = null;
/* 134 */     this.pq[(this.N + 1)] = -1;
/* 135 */     return i;
/*     */   }
/*     */ 
/*     */   public Key keyOf(int paramInt)
/*     */   {
/* 146 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/* 147 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 148 */     return this.keys[paramInt];
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public void change(int paramInt, Key paramKey)
/*     */   {
/* 159 */     changeKey(paramInt, paramKey);
/*     */   }
/*     */ 
/*     */   public void changeKey(int paramInt, Key paramKey)
/*     */   {
/* 170 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/* 171 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 172 */     this.keys[paramInt] = paramKey;
/* 173 */     swim(this.qp[paramInt]);
/* 174 */     sink(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void decreaseKey(int paramInt, Key paramKey)
/*     */   {
/* 186 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/* 187 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 188 */     if (this.keys[paramInt].compareTo(paramKey) <= 0) throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
/* 189 */     this.keys[paramInt] = paramKey;
/* 190 */     swim(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void increaseKey(int paramInt, Key paramKey)
/*     */   {
/* 202 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/* 203 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 204 */     if (this.keys[paramInt].compareTo(paramKey) >= 0) throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
/* 205 */     this.keys[paramInt] = paramKey;
/* 206 */     sink(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void delete(int paramInt)
/*     */   {
/* 216 */     if ((paramInt < 0) || (paramInt >= this.NMAX)) throw new IndexOutOfBoundsException();
/* 217 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 218 */     int i = this.qp[paramInt];
/* 219 */     exch(i, this.N--);
/* 220 */     swim(i);
/* 221 */     sink(i);
/* 222 */     this.keys[paramInt] = null;
/* 223 */     this.qp[paramInt] = -1;
/*     */   }
/*     */ 
/*     */   private boolean greater(int paramInt1, int paramInt2)
/*     */   {
/* 231 */     return this.keys[this.pq[paramInt1]].compareTo(this.keys[this.pq[paramInt2]]) > 0;
/*     */   }
/*     */ 
/*     */   private void exch(int paramInt1, int paramInt2) {
/* 235 */     int i = this.pq[paramInt1]; this.pq[paramInt1] = this.pq[paramInt2]; this.pq[paramInt2] = i;
/* 236 */     this.qp[this.pq[paramInt1]] = paramInt1; this.qp[this.pq[paramInt2]] = paramInt2;
/*     */   }
/*     */ 
/*     */   private void swim(int paramInt)
/*     */   {
/* 244 */     while ((paramInt > 1) && (greater(paramInt / 2, paramInt))) {
/* 245 */       exch(paramInt, paramInt / 2);
/* 246 */       paramInt /= 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sink(int paramInt) {
/* 251 */     while (2 * paramInt <= this.N) {
/* 252 */       int i = 2 * paramInt;
/* 253 */       if ((i < this.N) && (greater(i, i + 1))) i++;
/* 254 */       if (!greater(paramInt, i)) break;
/* 255 */       exch(paramInt, i);
/* 256 */       paramInt = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterator<Integer> iterator()
/*     */   {
/* 271 */     return new IndexMinPQ.HeapIterator();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 300 */     String[] arrayOfString = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
/*     */ 
/* 302 */     IndexMinPQ localIndexMinPQ = new IndexMinPQ(arrayOfString.length);
/* 303 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 304 */       localIndexMinPQ.insert(i, arrayOfString[i]);
/*     */     }
/*     */ 
/* 308 */     while (!localIndexMinPQ.isEmpty()) {
/* 309 */       i = localIndexMinPQ.delMin();
/* 310 */       StdOut.println(i + " " + arrayOfString[i]);
/*     */     }
/* 312 */     StdOut.println();
/*     */ 
/* 315 */     for (i = 0; i < arrayOfString.length; i++) {
/* 316 */       localIndexMinPQ.insert(i, arrayOfString[i]);
/*     */     }
/*     */ 
/* 320 */     for (Iterator localIterator = localIndexMinPQ.iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 321 */       StdOut.println(j + " " + arrayOfString[j]);
/*     */     }
/* 323 */     while (!localIndexMinPQ.isEmpty())
/* 324 */       localIndexMinPQ.delMin();
/*     */   }
/*     */ 
/*     */   private class HeapIterator
/*     */     implements Iterator<Integer>
/*     */   {
/*     */     private IndexMinPQ<Key> copy;
/*     */ 
/*     */     public HeapIterator()
/*     */     {
/* 280 */       this.copy = new IndexMinPQ(IndexMinPQ.this.pq.length - 1);
/* 281 */       for (int i = 1; i <= IndexMinPQ.this.N; i++)
/* 282 */         this.copy.insert(IndexMinPQ.this.pq[i], IndexMinPQ.this.keys[IndexMinPQ.this.pq[i]]); 
/*     */     }
/*     */ 
/* 285 */     public boolean hasNext() { return !this.copy.isEmpty(); } 
/* 286 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Integer next() {
/* 289 */       if (!hasNext()) throw new NoSuchElementException();
/* 290 */       return Integer.valueOf(this.copy.delMin());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     IndexMinPQ
 * JD-Core Version:    0.6.2
 */