/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class LinearProbingHashST<Key, Value>
/*     */ {
/*     */   private static final int INIT_CAPACITY = 4;
/*     */   private int N;
/*     */   private int M;
/*     */   private Key[] keys;
/*     */   private Value[] vals;
/*     */ 
/*     */   public LinearProbingHashST()
/*     */   {
/*  27 */     this(4);
/*     */   }
/*     */ 
/*     */   public LinearProbingHashST(int paramInt)
/*     */   {
/*  32 */     this.M = paramInt;
/*  33 */     this.keys = ((Object[])new Object[this.M]);
/*  34 */     this.vals = ((Object[])new Object[this.M]);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  39 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  44 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/*  49 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   private int hash(Key paramKey)
/*     */   {
/*  54 */     return (paramKey.hashCode() & 0x7FFFFFFF) % this.M;
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  59 */     LinearProbingHashST localLinearProbingHashST = new LinearProbingHashST(paramInt);
/*  60 */     for (int i = 0; i < this.M; i++) {
/*  61 */       if (this.keys[i] != null) {
/*  62 */         localLinearProbingHashST.put(this.keys[i], this.vals[i]);
/*     */       }
/*     */     }
/*  65 */     this.keys = localLinearProbingHashST.keys;
/*  66 */     this.vals = localLinearProbingHashST.vals;
/*  67 */     this.M = localLinearProbingHashST.M;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  72 */     if (paramValue == null) delete(paramKey);
/*     */ 
/*  75 */     if (this.N >= this.M / 2) resize(2 * this.M);
/*     */ 
/*  78 */     for (int i = hash(paramKey); this.keys[i] != null; i = (i + 1) % this.M)
/*  79 */       if (this.keys[i].equals(paramKey)) { this.vals[i] = paramValue; return;
/*     */       }
/*  81 */     this.keys[i] = paramKey;
/*  82 */     this.vals[i] = paramValue;
/*  83 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  88 */     for (int i = hash(paramKey); this.keys[i] != null; i = (i + 1) % this.M)
/*  89 */       if (this.keys[i].equals(paramKey))
/*  90 */         return this.vals[i];
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/*  96 */     if (!contains(paramKey)) return;
/*     */ 
/*  99 */     int i = hash(paramKey);
/* 100 */     while (!paramKey.equals(this.keys[i])) {
/* 101 */       i = (i + 1) % this.M;
/*     */     }
/*     */ 
/* 105 */     this.keys[i] = null;
/* 106 */     this.vals[i] = null;
/*     */ 
/* 109 */     i = (i + 1) % this.M;
/* 110 */     while (this.keys[i] != null)
/*     */     {
/* 112 */       Object localObject1 = this.keys[i];
/* 113 */       Object localObject2 = this.vals[i];
/* 114 */       this.keys[i] = null;
/* 115 */       this.vals[i] = null;
/* 116 */       this.N -= 1;
/* 117 */       put(localObject1, localObject2);
/* 118 */       i = (i + 1) % this.M;
/*     */     }
/*     */ 
/* 121 */     this.N -= 1;
/*     */ 
/* 124 */     if ((this.N > 0) && (this.N <= this.M / 8)) resize(this.M / 2);
/*     */ 
/* 126 */     assert (check());
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 131 */     Queue localQueue = new Queue();
/* 132 */     for (int i = 0; i < this.M; i++)
/* 133 */       if (this.keys[i] != null) localQueue.enqueue(this.keys[i]);
/* 134 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 142 */     if (this.M < 2 * this.N) {
/* 143 */       System.err.println("Hash table size M = " + this.M + "; array size N = " + this.N);
/* 144 */       return false;
/*     */     }
/*     */ 
/* 148 */     for (int i = 0; i < this.M; i++) {
/* 149 */       if ((this.keys[i] != null) && 
/* 150 */         (get(this.keys[i]) != this.vals[i])) {
/* 151 */         System.err.println("get[" + this.keys[i] + "] = " + get(this.keys[i]) + "; vals[i] = " + this.vals[i]);
/* 152 */         return false;
/*     */       }
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 163 */     LinearProbingHashST localLinearProbingHashST = new LinearProbingHashST();
/*     */     String str;
/* 164 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 165 */       str = StdIn.readString();
/* 166 */       localLinearProbingHashST.put(str, Integer.valueOf(i));
/*     */     }
/*     */ 
/* 170 */     for (Iterator localIterator = localLinearProbingHashST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 171 */       StdOut.println(str + " " + localLinearProbingHashST.get(str));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LinearProbingHashST
 * JD-Core Version:    0.6.2
 */