/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LinkedQueue<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private LinkedQueue<Item>.Node first;
/*     */   private LinkedQueue<Item>.Node last;
/*     */ 
/*     */   public LinkedQueue()
/*     */   {
/*  51 */     this.first = null;
/*  52 */     this.last = null;
/*  53 */     this.N = 0;
/*  54 */     assert (check());
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  62 */     return this.first == null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  70 */     return this.N;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/*  79 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/*  80 */     return this.first.item;
/*     */   }
/*     */ 
/*     */   public void enqueue(Item paramItem)
/*     */   {
/*  88 */     LinkedQueue.Node localNode = this.last;
/*  89 */     this.last = new LinkedQueue.Node(null);
/*  90 */     this.last.item = paramItem;
/*  91 */     this.last.next = null;
/*  92 */     if (isEmpty()) this.first = this.last; else
/*  93 */       localNode.next = this.last;
/*  94 */     this.N += 1;
/*  95 */     assert (check());
/*     */   }
/*     */ 
/*     */   public Item dequeue()
/*     */   {
/* 104 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/* 105 */     Object localObject = this.first.item;
/* 106 */     this.first = this.first.next;
/* 107 */     this.N -= 1;
/* 108 */     if (isEmpty()) this.last = null;
/* 109 */     assert (check());
/* 110 */     return localObject;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 118 */     StringBuilder localStringBuilder = new StringBuilder();
/* 119 */     for (Iterator localIterator = iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 120 */       localStringBuilder.append(localObject + " "); }
/* 121 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 126 */     if (this.N == 0) {
/* 127 */       if (this.first != null) return false;
/* 128 */       if (this.last != null) return false;
/*     */     }
/* 130 */     else if (this.N == 1) {
/* 131 */       if ((this.first == null) || (this.last == null)) return false;
/* 132 */       if (this.first != this.last) return false;
/* 133 */       if (this.first.next != null) return false; 
/*     */     }
/*     */     else
/*     */     {
/* 136 */       if (this.first == this.last) return false;
/* 137 */       if (this.first.next == null) return false;
/* 138 */       if (this.last.next != null) return false;
/*     */ 
/* 141 */       int i = 0;
/* 142 */       for (LinkedQueue.Node localNode = this.first; localNode != null; localNode = localNode.next) {
/* 143 */         i++;
/*     */       }
/* 145 */       if (i != this.N) return false;
/*     */ 
/* 148 */       localNode = this.first;
/* 149 */       while (localNode.next != null) {
/* 150 */         localNode = localNode.next;
/*     */       }
/* 152 */       if (this.last != localNode) return false;
/*     */     }
/*     */ 
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 164 */     return new LinkedQueue.ListIterator(null);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 187 */     LinkedQueue localLinkedQueue = new LinkedQueue();
/* 188 */     while (!StdIn.isEmpty()) {
/* 189 */       String str = StdIn.readString();
/* 190 */       if (!str.equals("-")) localLinkedQueue.enqueue(str);
/* 191 */       else if (!localLinkedQueue.isEmpty()) StdOut.print((String)localLinkedQueue.dequeue() + " ");
/*     */     }
/* 193 */     StdOut.println("(" + localLinkedQueue.size() + " left on queue)");
/*     */   }
/*     */ 
/*     */   private class ListIterator
/*     */     implements Iterator<Item>
/*     */   {
/* 169 */     private LinkedQueue<Item>.Node current = LinkedQueue.this.first;
/*     */ 
/*     */     private ListIterator() {  } 
/* 171 */     public boolean hasNext() { return this.current != null; } 
/* 172 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 175 */       if (!hasNext()) throw new NoSuchElementException();
/* 176 */       Object localObject = LinkedQueue.Node.access$000(this.current);
/* 177 */       this.current = LinkedQueue.Node.access$200(this.current);
/* 178 */       return localObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Item item;
/*     */     private LinkedQueue<Item>.Node next;
/*     */ 
/*     */     private Node()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LinkedQueue
 * JD-Core Version:    0.6.2
 */