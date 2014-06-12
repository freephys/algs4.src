/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ResizingArrayBag<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private Item[] a;
/*  31 */   private int N = 0;
/*     */ 
/*     */   public ResizingArrayBag()
/*     */   {
/*  37 */     this.a = ((Object[])new Object[2]);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  45 */     return this.N == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  53 */     return this.N;
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt)
/*     */   {
/*  58 */     assert (paramInt >= this.N);
/*  59 */     Object[] arrayOfObject = (Object[])new Object[paramInt];
/*  60 */     for (int i = 0; i < this.N; i++)
/*  61 */       arrayOfObject[i] = this.a[i];
/*  62 */     this.a = arrayOfObject;
/*     */   }
/*     */ 
/*     */   public void add(Item paramItem)
/*     */   {
/*  70 */     if (this.N == this.a.length) resize(2 * this.a.length);
/*  71 */     this.a[(this.N++)] = paramItem;
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/*  80 */     return new ResizingArrayBag.ArrayIterator(null);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  99 */     ResizingArrayBag localResizingArrayBag = new ResizingArrayBag();
/* 100 */     localResizingArrayBag.add("Hello");
/* 101 */     localResizingArrayBag.add("World");
/* 102 */     localResizingArrayBag.add("how");
/* 103 */     localResizingArrayBag.add("are");
/* 104 */     localResizingArrayBag.add("you");
/*     */ 
/* 106 */     for (String str : localResizingArrayBag)
/* 107 */       StdOut.println(str);
/*     */   }
/*     */ 
/*     */   private class ArrayIterator
/*     */     implements Iterator<Item>
/*     */   {
/*  85 */     private int i = 0;
/*     */ 
/*     */     private ArrayIterator() {  } 
/*  86 */     public boolean hasNext() { return this.i < ResizingArrayBag.this.N; } 
/*  87 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/*  90 */       if (!hasNext()) throw new NoSuchElementException();
/*  91 */       return ResizingArrayBag.this.a[(this.i++)];
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ResizingArrayBag
 * JD-Core Version:    0.6.2
 */