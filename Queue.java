/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Queue<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private Queue.Node<Item> first;
/*     */   private Queue.Node<Item> last;
/*     */ 
/*     */   public Queue()
/*     */   {
/*  51 */     this.first = null;
/*  52 */     this.last = null;
/*  53 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  61 */     return this.first == null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  69 */     return this.N;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/*  78 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/*  79 */     return this.first.item;
/*     */   }
/*     */ 
/*     */   public void enqueue(Item paramItem)
/*     */   {
/*  87 */     Queue.Node localNode = this.last;
/*  88 */     this.last = new Queue.Node(null);
/*  89 */     this.last.item = paramItem;
/*  90 */     this.last.next = null;
/*  91 */     if (isEmpty()) this.first = this.last; else
/*  92 */       localNode.next = this.last;
/*  93 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Item dequeue()
/*     */   {
/* 102 */     if (isEmpty()) throw new NoSuchElementException("Queue underflow");
/* 103 */     Object localObject = this.first.item;
/* 104 */     this.first = this.first.next;
/* 105 */     this.N -= 1;
/* 106 */     if (isEmpty()) this.last = null;
/* 107 */     return localObject;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 115 */     StringBuilder localStringBuilder = new StringBuilder();
/* 116 */     for (Iterator localIterator = iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 117 */       localStringBuilder.append(localObject + " "); }
/* 118 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 126 */     return new Queue.ListIterator(this.first);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 153 */     Queue localQueue = new Queue();
/* 154 */     while (!StdIn.isEmpty()) {
/* 155 */       String str = StdIn.readString();
/* 156 */       if (!str.equals("-")) localQueue.enqueue(str);
/* 157 */       else if (!localQueue.isEmpty()) StdOut.print((String)localQueue.dequeue() + " ");
/*     */     }
/* 159 */     StdOut.println("(" + localQueue.size() + " left on queue)");
/*     */   }
/*     */ 
/*     */   private class ListIterator<Item>
/*     */     implements Iterator<Item>
/*     */   {
/*     */     private Queue.Node<Item> current;
/*     */ 
/*     */     public ListIterator()
/*     */     {
/*     */       Object localObject;
/* 134 */       this.current = localObject;
/*     */     }
/*     */     public boolean hasNext() {
/* 137 */       return this.current != null; } 
/* 138 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 141 */       if (!hasNext()) throw new NoSuchElementException();
/* 142 */       Object localObject = Queue.Node.access$000(this.current);
/* 143 */       this.current = Queue.Node.access$200(this.current);
/* 144 */       return localObject;
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
 * Qualified Name:     Queue
 * JD-Core Version:    0.6.2
 */