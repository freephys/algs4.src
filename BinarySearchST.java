/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class BinarySearchST<Key extends Comparable<Key>, Value>
/*     */ {
/*     */   private static final int INIT_CAPACITY = 2;
/*     */   private Key[] keys;
/*     */   private Value[] vals;
/*  32 */   private int N = 0;
/*     */ 
/*     */   public BinarySearchST() {
/*  35 */     this(2);
/*     */   }
/*     */ 
/*     */   public BinarySearchST(int paramInt) {
/*  39 */     this.keys = ((Comparable[])new Comparable[paramInt]);
/*  40 */     this.vals = ((Object[])new Object[paramInt]);
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  45 */     assert (paramInt >= this.N);
/*  46 */     Comparable[] arrayOfComparable = (Comparable[])new Comparable[paramInt];
/*  47 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/*  48 */     for (int i = 0; i < this.N; i++) {
/*  49 */       arrayOfComparable[i] = this.keys[i];
/*  50 */       arrayOfObject[i] = this.vals[i];
/*     */     }
/*  52 */     this.vals = arrayOfObject;
/*  53 */     this.keys = arrayOfComparable;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/*  59 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  64 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  69 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  74 */     if (isEmpty()) return null;
/*  75 */     int i = rank(paramKey);
/*  76 */     if ((i < this.N) && (this.keys[i].compareTo(paramKey) == 0)) return this.vals[i];
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   public int rank(Key paramKey)
/*     */   {
/*  82 */     int i = 0; int j = this.N - 1;
/*  83 */     while (i <= j) {
/*  84 */       int k = i + (j - i) / 2;
/*  85 */       int m = paramKey.compareTo(this.keys[k]);
/*  86 */       if (m < 0) j = k - 1;
/*  87 */       else if (m > 0) i = k + 1; else
/*  88 */         return k;
/*     */     }
/*  90 */     return i;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  96 */     if (paramValue == null) { delete(paramKey); return;
/*     */     }
/*  98 */     int i = rank(paramKey);
/*     */ 
/* 101 */     if ((i < this.N) && (this.keys[i].compareTo(paramKey) == 0)) {
/* 102 */       this.vals[i] = paramValue;
/* 103 */       return;
/*     */     }
/*     */ 
/* 107 */     if (this.N == this.keys.length) resize(2 * this.keys.length);
/*     */ 
/* 109 */     for (int j = this.N; j > i; j--) {
/* 110 */       this.keys[j] = this.keys[(j - 1)];
/* 111 */       this.vals[j] = this.vals[(j - 1)];
/*     */     }
/* 113 */     this.keys[i] = paramKey;
/* 114 */     this.vals[i] = paramValue;
/* 115 */     this.N += 1;
/*     */ 
/* 117 */     assert (check());
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/* 123 */     if (isEmpty()) return;
/*     */ 
/* 126 */     int i = rank(paramKey);
/*     */ 
/* 129 */     if ((i == this.N) || (this.keys[i].compareTo(paramKey) != 0)) {
/* 130 */       return;
/*     */     }
/*     */ 
/* 133 */     for (int j = i; j < this.N - 1; j++) {
/* 134 */       this.keys[j] = this.keys[(j + 1)];
/* 135 */       this.vals[j] = this.vals[(j + 1)];
/*     */     }
/*     */ 
/* 138 */     this.N -= 1;
/* 139 */     this.keys[this.N] = null;
/* 140 */     this.vals[this.N] = null;
/*     */ 
/* 143 */     if ((this.N > 0) && (this.N == this.keys.length / 4)) resize(this.keys.length / 2);
/*     */ 
/* 145 */     assert (check());
/*     */   }
/*     */ 
/*     */   public void deleteMin()
/*     */   {
/* 150 */     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
/* 151 */     delete(min());
/*     */   }
/*     */ 
/*     */   public void deleteMax()
/*     */   {
/* 156 */     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
/* 157 */     delete(max());
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 165 */     if (isEmpty()) return null;
/* 166 */     return this.keys[0];
/*     */   }
/*     */ 
/*     */   public Key max() {
/* 170 */     if (isEmpty()) return null;
/* 171 */     return this.keys[(this.N - 1)];
/*     */   }
/*     */ 
/*     */   public Key select(int paramInt) {
/* 175 */     if ((paramInt < 0) || (paramInt >= this.N)) return null;
/* 176 */     return this.keys[paramInt];
/*     */   }
/*     */ 
/*     */   public Key floor(Key paramKey) {
/* 180 */     int i = rank(paramKey);
/* 181 */     if ((i < this.N) && (paramKey.compareTo(this.keys[i]) == 0)) return this.keys[i];
/* 182 */     if (i == 0) return null;
/* 183 */     return this.keys[(i - 1)];
/*     */   }
/*     */ 
/*     */   public Key ceiling(Key paramKey) {
/* 187 */     int i = rank(paramKey);
/* 188 */     if (i == this.N) return null;
/* 189 */     return this.keys[i];
/*     */   }
/*     */ 
/*     */   public int size(Key paramKey1, Key paramKey2) {
/* 193 */     if (paramKey1.compareTo(paramKey2) > 0) return 0;
/* 194 */     if (contains(paramKey2)) return rank(paramKey2) - rank(paramKey1) + 1;
/* 195 */     return rank(paramKey2) - rank(paramKey1);
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys() {
/* 199 */     return keys(min(), max());
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys(Key paramKey1, Key paramKey2) {
/* 203 */     Queue localQueue = new Queue();
/* 204 */     if ((paramKey1 == null) && (paramKey2 == null)) return localQueue;
/* 205 */     if (paramKey1 == null) throw new NullPointerException("lo is null in keys()");
/* 206 */     if (paramKey2 == null) throw new NullPointerException("hi is null in keys()");
/* 207 */     if (paramKey1.compareTo(paramKey2) > 0) return localQueue;
/* 208 */     for (int i = rank(paramKey1); i < rank(paramKey2); i++)
/* 209 */       localQueue.enqueue(this.keys[i]);
/* 210 */     if (contains(paramKey2)) localQueue.enqueue(this.keys[rank(paramKey2)]);
/* 211 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 220 */     return (isSorted()) && (rankCheck());
/*     */   }
/*     */ 
/*     */   private boolean isSorted()
/*     */   {
/* 225 */     for (int i = 1; i < size(); i++)
/* 226 */       if (this.keys[i].compareTo(this.keys[(i - 1)]) < 0) return false;
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean rankCheck()
/*     */   {
/* 232 */     for (int i = 0; i < size(); i++)
/* 233 */       if (i != rank(select(i))) return false;
/* 234 */     for (i = 0; i < size(); i++)
/* 235 */       if (this.keys[i].compareTo(select(rank(this.keys[i]))) != 0) return false;
/* 236 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 244 */     BinarySearchST localBinarySearchST = new BinarySearchST();
/*     */     String str;
/* 245 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 246 */       str = StdIn.readString();
/* 247 */       localBinarySearchST.put(str, Integer.valueOf(i));
/*     */     }
/* 249 */     for (Iterator localIterator = localBinarySearchST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 250 */       StdOut.println(str + " " + localBinarySearchST.get(str));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BinarySearchST
 * JD-Core Version:    0.6.2
 */