/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LinkedStack<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private LinkedStack<Item>.Node first;
/*     */ 
/*     */   public LinkedStack()
/*     */   {
/*  52 */     this.first = null;
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
/*     */   public void push(Item paramItem)
/*     */   {
/*  78 */     LinkedStack.Node localNode = this.first;
/*  79 */     this.first = new LinkedStack.Node(null);
/*  80 */     this.first.item = paramItem;
/*  81 */     this.first.next = localNode;
/*  82 */     this.N += 1;
/*  83 */     assert (check());
/*     */   }
/*     */ 
/*     */   public Item pop()
/*     */   {
/*  92 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/*  93 */     Object localObject = this.first.item;
/*  94 */     this.first = this.first.next;
/*  95 */     this.N -= 1;
/*  96 */     assert (check());
/*  97 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/* 107 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/* 108 */     return this.first.item;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 116 */     StringBuilder localStringBuilder = new StringBuilder();
/* 117 */     for (Iterator localIterator = iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 118 */       localStringBuilder.append(localObject + " "); }
/* 119 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 126 */     return new LinkedStack.ListIterator(null);
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 145 */     if (this.N == 0) {
/* 146 */       if (this.first != null) return false;
/*     */     }
/* 148 */     else if (this.N == 1) {
/* 149 */       if (this.first == null) return false;
/* 150 */       if (this.first.next != null) return false;
/*     */ 
/*     */     }
/* 153 */     else if (this.first.next == null) { return false; }
/*     */ 
/*     */ 
/* 157 */     int i = 0;
/* 158 */     for (LinkedStack.Node localNode = this.first; localNode != null; localNode = localNode.next) {
/* 159 */       i++;
/*     */     }
/* 161 */     if (i != this.N) return false;
/*     */ 
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 170 */     LinkedStack localLinkedStack = new LinkedStack();
/* 171 */     while (!StdIn.isEmpty()) {
/* 172 */       String str = StdIn.readString();
/* 173 */       if (!str.equals("-")) localLinkedStack.push(str);
/* 174 */       else if (!localLinkedStack.isEmpty()) StdOut.print((String)localLinkedStack.pop() + " ");
/*     */     }
/* 176 */     StdOut.println("(" + localLinkedStack.size() + " left on stack)");
/*     */   }
/*     */ 
/*     */   private class ListIterator
/*     */     implements Iterator<Item>
/*     */   {
/* 130 */     private LinkedStack<Item>.Node current = LinkedStack.this.first;
/*     */ 
/*     */     private ListIterator() {  } 
/* 131 */     public boolean hasNext() { return this.current != null; } 
/* 132 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 135 */       if (!hasNext()) throw new NoSuchElementException();
/* 136 */       Object localObject = LinkedStack.Node.access$100(this.current);
/* 137 */       this.current = LinkedStack.Node.access$200(this.current);
/* 138 */       return localObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Item item;
/*     */     private LinkedStack<Item>.Node next;
/*     */ 
/*     */     private Node()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     LinkedStack
 * JD-Core Version:    0.6.2
 */