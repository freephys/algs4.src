/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SeparateChainingHashST<Key, Value>
/*     */ {
/*     */   private static final int INIT_CAPACITY = 4;
/*     */   private int N;
/*     */   private int M;
/*     */   private SequentialSearchST<Key, Value>[] st;
/*     */ 
/*     */   public SeparateChainingHashST()
/*     */   {
/*  30 */     this(4);
/*     */   }
/*     */ 
/*     */   public SeparateChainingHashST(int paramInt)
/*     */   {
/*  35 */     this.M = paramInt;
/*  36 */     this.st = ((SequentialSearchST[])new SequentialSearchST[paramInt]);
/*  37 */     for (int i = 0; i < paramInt; i++)
/*  38 */       this.st[i] = new SequentialSearchST();
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  43 */     SeparateChainingHashST localSeparateChainingHashST = new SeparateChainingHashST(paramInt);
/*     */     Iterator localIterator;
/*  44 */     for (int i = 0; i < this.M; i++) {
/*  45 */       for (localIterator = this.st[i].keys().iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/*  46 */         localSeparateChainingHashST.put(localObject, this.st[i].get(localObject));
/*     */       }
/*     */     }
/*  49 */     this.M = localSeparateChainingHashST.M;
/*  50 */     this.N = localSeparateChainingHashST.N;
/*  51 */     this.st = localSeparateChainingHashST.st;
/*     */   }
/*     */ 
/*     */   private int hash(Key paramKey)
/*     */   {
/*  56 */     return (paramKey.hashCode() & 0x7FFFFFFF) % this.M;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  61 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  66 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/*  71 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  76 */     int i = hash(paramKey);
/*  77 */     return this.st[i].get(paramKey);
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  82 */     if (paramValue == null) { delete(paramKey); return;
/*     */     }
/*     */ 
/*  85 */     if (this.N >= 10 * this.M) resize(2 * this.M);
/*     */ 
/*  87 */     int i = hash(paramKey);
/*  88 */     if (!this.st[i].contains(paramKey)) this.N += 1;
/*  89 */     this.st[i].put(paramKey, paramValue);
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/*  94 */     int i = hash(paramKey);
/*  95 */     if (this.st[i].contains(paramKey)) this.N -= 1;
/*  96 */     this.st[i].delete(paramKey);
/*     */ 
/*  99 */     if ((this.M > 4) && (this.N <= 2 * this.M)) resize(this.M / 2);
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 104 */     Queue localQueue = new Queue();
/*     */     Iterator localIterator;
/* 105 */     for (int i = 0; i < this.M; i++)
/* 106 */       for (localIterator = this.st[i].keys().iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 107 */         localQueue.enqueue(localObject);
/*     */       }
/* 109 */     return localQueue;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 117 */     SeparateChainingHashST localSeparateChainingHashST = new SeparateChainingHashST();
/*     */     String str;
/* 118 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 119 */       str = StdIn.readString();
/* 120 */       localSeparateChainingHashST.put(str, Integer.valueOf(i));
/*     */     }
/*     */ 
/* 124 */     for (Iterator localIterator = localSeparateChainingHashST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 125 */       StdOut.println(str + " " + localSeparateChainingHashST.get(str));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SeparateChainingHashST
 * JD-Core Version:    0.6.2
 */