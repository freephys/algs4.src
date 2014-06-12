/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SequentialSearchST<Key, Value>
/*     */ {
/*     */   private int N;
/*     */   private SequentialSearchST<Key, Value>.Node first;
/*     */ 
/*     */   public int size()
/*     */   {
/*  83 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  91 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/* 101 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/* 111 */     for (SequentialSearchST.Node localNode = this.first; localNode != null; localNode = localNode.next) {
/* 112 */       if (paramKey.equals(localNode.key)) return localNode.val;
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/* 125 */     if (paramValue == null) { delete(paramKey); return; }
/* 126 */     for (SequentialSearchST.Node localNode = this.first; localNode != null; localNode = localNode.next)
/* 127 */       if (paramKey.equals(localNode.key)) { localNode.val = paramValue; return; }
/* 128 */     this.first = new SequentialSearchST.Node(paramKey, paramValue, this.first);
/* 129 */     this.N += 1;
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/* 138 */     this.first = delete(this.first, paramKey);
/*     */   }
/*     */ 
/*     */   private SequentialSearchST<Key, Value>.Node delete(SequentialSearchST<Key, Value>.Node paramSequentialSearchST, Key paramKey)
/*     */   {
/* 144 */     if (paramSequentialSearchST == null) return null;
/* 145 */     if (paramKey.equals(paramSequentialSearchST.key)) { this.N -= 1; return paramSequentialSearchST.next; }
/* 146 */     paramSequentialSearchST.next = delete(paramSequentialSearchST.next, paramKey);
/* 147 */     return paramSequentialSearchST;
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 158 */     Queue localQueue = new Queue();
/* 159 */     for (SequentialSearchST.Node localNode = this.first; localNode != null; localNode = localNode.next)
/* 160 */       localQueue.enqueue(localNode.key);
/* 161 */     return localQueue;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 169 */     SequentialSearchST localSequentialSearchST = new SequentialSearchST();
/*     */     String str;
/* 170 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 171 */       str = StdIn.readString();
/* 172 */       localSequentialSearchST.put(str, Integer.valueOf(i));
/*     */     }
/* 174 */     for (Iterator localIterator = localSequentialSearchST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 175 */       StdOut.println(str + " " + localSequentialSearchST.get(str));
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Key key;
/*     */     private Value val;
/*     */     private SequentialSearchST<Key, Value>.Node next;
/*     */ 
/*     */     public Node(Value paramSequentialSearchST, SequentialSearchST<Key, Value>.Node arg3)
/*     */     {
/*  66 */       this.key = paramSequentialSearchST;
/*     */       Object localObject1;
/*  67 */       this.val = localObject1;
/*     */       Object localObject2;
/*  68 */       this.next = localObject2;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SequentialSearchST
 * JD-Core Version:    0.6.2
 */