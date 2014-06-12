/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IndexMaxPQ<Key extends Comparable<Key>>
/*     */   implements Iterable<Integer>
/*     */ {
/*     */   private int N;
/*     */   private int[] pq;
/*     */   private int[] qp;
/*     */   private Key[] keys;
/*     */ 
/*     */   public IndexMaxPQ(int paramInt)
/*     */   {
/*  50 */     this.keys = ((Comparable[])new Comparable[paramInt + 1]);
/*  51 */     this.pq = new int[paramInt + 1];
/*  52 */     this.qp = new int[paramInt + 1];
/*  53 */     for (int i = 0; i <= paramInt; i++) this.qp[i] = -1;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  60 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(int paramInt)
/*     */   {
/*  68 */     return this.qp[paramInt] != -1;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  76 */     return this.N;
/*     */   }
/*     */ 
/*     */   public void insert(int paramInt, Key paramKey)
/*     */   {
/*  87 */     if (contains(paramInt)) throw new IllegalArgumentException("index is already in the priority queue");
/*  88 */     this.N += 1;
/*  89 */     this.qp[paramInt] = this.N;
/*  90 */     this.pq[this.N] = paramInt;
/*  91 */     this.keys[paramInt] = paramKey;
/*  92 */     swim(this.N);
/*     */   }
/*     */ 
/*     */   public int maxIndex()
/*     */   {
/* 101 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 102 */     return this.pq[1];
/*     */   }
/*     */ 
/*     */   public Key maxKey()
/*     */   {
/* 111 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 112 */     return this.keys[this.pq[1]];
/*     */   }
/*     */ 
/*     */   public int delMax()
/*     */   {
/* 121 */     if (this.N == 0) throw new NoSuchElementException("Priority queue underflow");
/* 122 */     int i = this.pq[1];
/* 123 */     exch(1, this.N--);
/* 124 */     sink(1);
/* 125 */     this.qp[i] = -1;
/* 126 */     this.keys[this.pq[(this.N + 1)]] = null;
/* 127 */     this.pq[(this.N + 1)] = -1;
/* 128 */     return i;
/*     */   }
/*     */ 
/*     */   public Key keyOf(int paramInt)
/*     */   {
/* 139 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 140 */     return this.keys[paramInt];
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public void change(int paramInt, Key paramKey)
/*     */   {
/* 151 */     changeKey(paramInt, paramKey);
/*     */   }
/*     */ 
/*     */   public void changeKey(int paramInt, Key paramKey)
/*     */   {
/* 161 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 162 */     this.keys[paramInt] = paramKey;
/* 163 */     swim(this.qp[paramInt]);
/* 164 */     sink(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void increaseKey(int paramInt, Key paramKey)
/*     */   {
/* 177 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 178 */     if (this.keys[paramInt].compareTo(paramKey) >= 0) throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
/*     */ 
/* 181 */     this.keys[paramInt] = paramKey;
/* 182 */     swim(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void decreaseKey(int paramInt, Key paramKey)
/*     */   {
/* 194 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 195 */     if (this.keys[paramInt].compareTo(paramKey) <= 0) throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
/*     */ 
/* 197 */     this.keys[paramInt] = paramKey;
/* 198 */     sink(this.qp[paramInt]);
/*     */   }
/*     */ 
/*     */   public void delete(int paramInt)
/*     */   {
/* 208 */     if (!contains(paramInt)) throw new NoSuchElementException("index is not in the priority queue");
/* 209 */     int i = this.qp[paramInt];
/* 210 */     exch(i, this.N--);
/* 211 */     swim(i);
/* 212 */     sink(i);
/* 213 */     this.keys[paramInt] = null;
/* 214 */     this.qp[paramInt] = -1;
/*     */   }
/*     */ 
/*     */   private boolean less(int paramInt1, int paramInt2)
/*     */   {
/* 222 */     return this.keys[this.pq[paramInt1]].compareTo(this.keys[this.pq[paramInt2]]) < 0;
/*     */   }
/*     */ 
/*     */   private void exch(int paramInt1, int paramInt2) {
/* 226 */     int i = this.pq[paramInt1]; this.pq[paramInt1] = this.pq[paramInt2]; this.pq[paramInt2] = i;
/* 227 */     this.qp[this.pq[paramInt1]] = paramInt1; this.qp[this.pq[paramInt2]] = paramInt2;
/*     */   }
/*     */ 
/*     */   private void swim(int paramInt)
/*     */   {
/* 235 */     while ((paramInt > 1) && (less(paramInt / 2, paramInt))) {
/* 236 */       exch(paramInt, paramInt / 2);
/* 237 */       paramInt /= 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void sink(int paramInt) {
/* 242 */     while (2 * paramInt <= this.N) {
/* 243 */       int i = 2 * paramInt;
/* 244 */       if ((i < this.N) && (less(i, i + 1))) i++;
/* 245 */       if (!less(paramInt, i)) break;
/* 246 */       exch(paramInt, i);
/* 247 */       paramInt = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterator<Integer> iterator()
/*     */   {
/* 262 */     return new IndexMaxPQ.HeapIterator();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 290 */     String[] arrayOfString = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
/*     */ 
/* 292 */     IndexMaxPQ localIndexMaxPQ = new IndexMaxPQ(arrayOfString.length);
/* 293 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 294 */       localIndexMaxPQ.insert(i, arrayOfString[i]);
/*     */     }
/*     */ 
/* 298 */     for (Iterator localIterator = localIndexMaxPQ.iterator(); localIterator.hasNext(); ) { m = ((Integer)localIterator.next()).intValue();
/* 299 */       StdOut.println(m + " " + arrayOfString[m]);
/*     */     }
/*     */ 
/* 302 */     StdOut.println();
/*     */ 
/* 305 */     for (int j = 0; j < arrayOfString.length; j++) {
/* 306 */       if (StdRandom.uniform() < 0.5D)
/* 307 */         localIndexMaxPQ.increaseKey(j, arrayOfString[j] + arrayOfString[j]);
/*     */       else {
/* 309 */         localIndexMaxPQ.decreaseKey(j, arrayOfString[j].substring(0, 1));
/*     */       }
/*     */     }
/*     */ 
/* 313 */     while (!localIndexMaxPQ.isEmpty()) {
/* 314 */       String str1 = (String)localIndexMaxPQ.maxKey();
/* 315 */       m = localIndexMaxPQ.delMax();
/* 316 */       StdOut.println(m + " " + str1);
/*     */     }
/* 318 */     StdOut.println();
/*     */ 
/* 321 */     for (int k = 0; k < arrayOfString.length; k++) {
/* 322 */       localIndexMaxPQ.insert(k, arrayOfString[k]);
/*     */     }
/*     */ 
/* 326 */     int[] arrayOfInt = new int[arrayOfString.length];
/* 327 */     for (int m = 0; m < arrayOfString.length; m++)
/* 328 */       arrayOfInt[m] = m;
/* 329 */     StdRandom.shuffle(arrayOfInt);
/* 330 */     for (m = 0; m < arrayOfInt.length; m++) {
/* 331 */       String str2 = (String)localIndexMaxPQ.keyOf(arrayOfInt[m]);
/* 332 */       localIndexMaxPQ.delete(arrayOfInt[m]);
/* 333 */       StdOut.println(arrayOfInt[m] + " " + str2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class HeapIterator
/*     */     implements Iterator<Integer>
/*     */   {
/*     */     private IndexMaxPQ<Key> copy;
/*     */ 
/*     */     public HeapIterator()
/*     */     {
/* 271 */       this.copy = new IndexMaxPQ(IndexMaxPQ.this.pq.length - 1);
/* 272 */       for (int i = 1; i <= IndexMaxPQ.this.N; i++)
/* 273 */         this.copy.insert(IndexMaxPQ.this.pq[i], IndexMaxPQ.this.keys[IndexMaxPQ.this.pq[i]]); 
/*     */     }
/*     */ 
/* 276 */     public boolean hasNext() { return !this.copy.isEmpty(); } 
/* 277 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Integer next() {
/* 280 */       if (!hasNext()) throw new NoSuchElementException();
/* 281 */       return Integer.valueOf(this.copy.delMax());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     IndexMaxPQ
 * JD-Core Version:    0.6.2
 */