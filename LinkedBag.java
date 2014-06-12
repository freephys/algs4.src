/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LinkedBag<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private LinkedBag<Item>.Node first;
/*     */ 
/*     */   public LinkedBag()
/*     */   {
/*  62 */     this.first = null;
/*  63 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  71 */     return this.first == null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  79 */     return this.N;
/*     */   }
/*     */ 
/*     */   public void add(Item paramItem)
/*     */   {
/*  87 */     LinkedBag.Node localNode = this.first;
/*  88 */     this.first = new LinkedBag.Node(null);
/*  89 */     this.first.item = paramItem;
/*  90 */     this.first.next = localNode;
/*  91 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/*  99 */     return new LinkedBag.ListIterator(null);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 121 */     LinkedBag localLinkedBag = new LinkedBag();
/* 122 */     while (!StdIn.isEmpty()) {
/* 123 */       localObject = StdIn.readString();
/* 124 */       localLinkedBag.add(localObject);
/*     */     }
/*     */ 
/* 127 */     StdOut.println("size of bag = " + localLinkedBag.size());
/* 128 */     for (Object localObject = localLinkedBag.iterator(); ((Iterator)localObject).hasNext(); ) { String str = (String)((Iterator)localObject).next();
/* 129 */       StdOut.println(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class ListIterator
/*     */     implements Iterator<Item>
/*     */   {
/* 104 */     private LinkedBag<Item>.Node current = LinkedBag.this.first;
/*     */ 
/*     */     private ListIterator() {  } 
/* 106 */     public boolean hasNext() { return this.current != null; } 
/* 107 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 110 */       if (!hasNext()) throw new NoSuchElementException();
/* 111 */       Object localObject = LinkedBag.Node.access$100(this.current);
/* 112 */       this.current = LinkedBag.Node.access$200(this.current);
/* 113 */       return localObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Item item;
/*     */     private LinkedBag<Item>.Node next;
/*     */ 
/*     */     private Node()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LinkedBag
 * JD-Core Version:    0.6.2
 */