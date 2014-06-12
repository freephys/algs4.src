/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Bag<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private Bag.Node<Item> first;
/*     */ 
/*     */   public Bag()
/*     */   {
/*  63 */     this.first = null;
/*  64 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  72 */     return this.first == null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  80 */     return this.N;
/*     */   }
/*     */ 
/*     */   public void add(Item paramItem)
/*     */   {
/*  88 */     Bag.Node localNode = this.first;
/*  89 */     this.first = new Bag.Node(null);
/*  90 */     this.first.item = paramItem;
/*  91 */     this.first.next = localNode;
/*  92 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 101 */     return new Bag.ListIterator(this.first);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 127 */     Bag localBag = new Bag();
/* 128 */     while (!StdIn.isEmpty()) {
/* 129 */       localObject = StdIn.readString();
/* 130 */       localBag.add(localObject);
/*     */     }
/*     */ 
/* 133 */     StdOut.println("size of bag = " + localBag.size());
/* 134 */     for (Object localObject = localBag.iterator(); ((Iterator)localObject).hasNext(); ) { String str = (String)((Iterator)localObject).next();
/* 135 */       StdOut.println(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class ListIterator<Item>
/*     */     implements Iterator<Item>
/*     */   {
/*     */     private Bag.Node<Item> current;
/*     */ 
/*     */     public ListIterator()
/*     */     {
/*     */       Object localObject;
/* 109 */       this.current = localObject;
/*     */     }
/*     */     public boolean hasNext() {
/* 112 */       return this.current != null; } 
/* 113 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 116 */       if (!hasNext()) throw new NoSuchElementException();
/* 117 */       Object localObject = Bag.Node.access$100(this.current);
/* 118 */       this.current = Bag.Node.access$200(this.current);
/* 119 */       return localObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Node<Item>
/*     */   {
/*     */     private Item item;
/*     */     private Node<Item> next;
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Bag
 * JD-Core Version:    0.6.2
 */