/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ResizingArrayQueue<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private Item[] q;
/*  38 */   private int N = 0;
/*  39 */   private int first = 0;
/*  40 */   private int last = 0;
/*     */ 
/*     */   public ResizingArrayQueue()
/*     */   {
/*  48 */     this.q = ((Object[])new Object[2]);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  56 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  64 */     return this.N;
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  69 */     assert (paramInt >= this.N);
/*  70 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/*  71 */     for (int i = 0; i < this.N; i++) {
/*  72 */       arrayOfObject[i] = this.q[((this.first + i) % this.q.length)];
/*     */     }
/*  74 */     this.q = arrayOfObject;
/*  75 */     this.first = 0;
/*  76 */     this.last = this.N;
/*     */   }
/*     */ 
/*     */   public void enqueue(Item paramItem)
/*     */   {
/*  85 */     if (this.N == this.q.length) resize(2 * this.q.length);
/*  86 */     this.q[(this.last++)] = paramItem;
/*  87 */     if (this.last == this.q.length) this.last = 0;
/*  88 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Item dequeue()
/*     */   {
/*  97 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/*  98 */     Object localObject = this.q[this.first];
/*  99 */     this.q[this.first] = null;
/* 100 */     this.N -= 1;
/* 101 */     this.first += 1;
/* 102 */     if (this.first == this.q.length) this.first = 0;
/*     */ 
/* 104 */     if ((this.N > 0) && (this.N == this.q.length / 4)) resize(this.q.length / 2);
/* 105 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/* 114 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/* 115 */     return this.q[this.first];
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 124 */     return new ResizingArrayQueue.ArrayIterator(null);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 145 */     ResizingArrayQueue localResizingArrayQueue = new ResizingArrayQueue();
/* 146 */     while (!StdIn.isEmpty()) {
/* 147 */       String str = StdIn.readString();
/* 148 */       if (!str.equals("-")) localResizingArrayQueue.enqueue(str);
/* 149 */       else if (!localResizingArrayQueue.isEmpty()) StdOut.print((String)localResizingArrayQueue.dequeue() + " ");
/*     */     }
/* 151 */     StdOut.println("(" + localResizingArrayQueue.size() + " left on queue)");
/*     */   }
/*     */ 
/*     */   private class ArrayIterator
/*     */     implements Iterator<Item>
/*     */   {
/* 129 */     private int i = 0;
/*     */ 
/*     */     private ArrayIterator() {  } 
/* 130 */     public boolean hasNext() { return this.i < ResizingArrayQueue.this.N; } 
/* 131 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 134 */       if (!hasNext()) throw new NoSuchElementException();
/* 135 */       Object localObject = ResizingArrayQueue.this.q[((this.i + ResizingArrayQueue.this.first) % ResizingArrayQueue.this.q.length)];
/* 136 */       this.i += 1;
/* 137 */       return localObject;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ResizingArrayQueue
 * JD-Core Version:    0.6.2
 */