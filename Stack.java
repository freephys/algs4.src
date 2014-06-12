/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Stack<Item>
/*     */   implements Iterable<Item>
/*     */ {
/*     */   private int N;
/*     */   private Stack.Node<Item> first;
/*     */ 
/*     */   public Stack()
/*     */   {
/*  56 */     this.first = null;
/*  57 */     this.N = 0;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  65 */     return this.first == null;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  73 */     return this.N;
/*     */   }
/*     */ 
/*     */   public void push(Item paramItem)
/*     */   {
/*  81 */     Stack.Node localNode = this.first;
/*  82 */     this.first = new Stack.Node(null);
/*  83 */     this.first.item = paramItem;
/*  84 */     this.first.next = localNode;
/*  85 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public Item pop()
/*     */   {
/*  94 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/*  95 */     Object localObject = this.first.item;
/*  96 */     this.first = this.first.next;
/*  97 */     this.N -= 1;
/*  98 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Item peek()
/*     */   {
/* 108 */     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
/* 109 */     return this.first.item;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 117 */     StringBuilder localStringBuilder = new StringBuilder();
/* 118 */     for (Iterator localIterator = iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 119 */       localStringBuilder.append(localObject + " "); }
/* 120 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public Iterator<Item> iterator()
/*     */   {
/* 129 */     return new Stack.ListIterator(this.first);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 155 */     Stack localStack = new Stack();
/* 156 */     while (!StdIn.isEmpty()) {
/* 157 */       String str = StdIn.readString();
/* 158 */       if (!str.equals("-")) localStack.push(str);
/* 159 */       else if (!localStack.isEmpty()) StdOut.print((String)localStack.pop() + " ");
/*     */     }
/* 161 */     StdOut.println("(" + localStack.size() + " left on stack)");
/*     */   }
/*     */ 
/*     */   private class ListIterator<Item>
/*     */     implements Iterator<Item>
/*     */   {
/*     */     private Stack.Node<Item> current;
/*     */ 
/*     */     public ListIterator()
/*     */     {
/*     */       Object localObject;
/* 137 */       this.current = localObject;
/*     */     }
/* 139 */     public boolean hasNext() { return this.current != null; } 
/* 140 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */     public Item next() {
/* 143 */       if (!hasNext()) throw new NoSuchElementException();
/* 144 */       Object localObject = Stack.Node.access$100(this.current);
/* 145 */       this.current = Stack.Node.access$200(this.current);
/* 146 */       return localObject;
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
 * Qualified Name:     Stack
 * JD-Core Version:    0.6.2
 */