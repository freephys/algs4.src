/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ResizingArrayStack<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private Item[] a;
/*     */   private int N;
/*     */ 
/*     */   public ResizingArrayStack()
/*     */   {
/*  47 */     this.a = ((Object[])new Object[2]);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  55 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  63 */     return this.N;
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  69 */     assert (paramInt >= this.N);
/*  70 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/*  71 */     for (int i = 0; i < this.N; i++) {
/*  72 */       arrayOfObject[i] = this.a[i];
/*     */     }
/*  74 */     this.a = arrayOfObject;
/*     */   }
/*     */ 
/*     */   public void push(Item paramItem)
/*     */   {
/*  82 */     if (this.N == this.a.length) resize(2 * this.a.length);
/*  83 */     this.a[(this.N++)] = paramItem;
/*     */   }
/*     */ 
/*     */   public Item pop()
/*     */   {
/*  92 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/*  93 */     Object localObject = this.a[(this.N - 1)];
/*  94 */     this.a[(this.N - 1)] = null;
/*  95 */     this.N -= 1;
/*     */ 
/*  97 */     if ((this.N > 0) && (this.N == this.a.length / 4)) resize(this.a.length / 2);
/*  98 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/* 108 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/* 109 */     return this.a[(this.N - 1)];
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 117 */     return new ResizingArrayStack.ReverseArrayIterator();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 147 */     ResizingArrayStack localResizingArrayStack = new ResizingArrayStack();
/* 148 */     while (!StdIn.isEmpty()) {
/* 149 */       String str = StdIn.readString();
/* 150 */       if (!str.equals("-")) localResizingArrayStack.push(str);
/* 151 */       else if (!localResizingArrayStack.isEmpty()) StdOut.print((String)localResizingArrayStack.pop() + " ");
/*     */     }
/* 153 */     StdOut.println("(" + localResizingArrayStack.size() + " left on stack)");
/*     */   }
/*     */ 
/*     */   private class ReverseArrayIterator
/*     */     implements Iterator<Item>
/*     */   {
/*     */     private int i;
/*     */ 
/*     */     public ReverseArrayIterator()
/*     */     {
/* 125 */       this.i = ResizingArrayStack.this.N;
/*     */     }
/*     */ 
/*     */     public boolean hasNext() {
/* 129 */       return this.i > 0;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 133 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public Item next() {
/* 137 */       if (!hasNext()) throw new NoSuchElementException();
/* 138 */       return ResizingArrayStack.this.a[(--this.i)];
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ResizingArrayStack
 * JD-Core Version:    0.6.2
 */