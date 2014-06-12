/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class BST<Key extends Comparable<Key>, Value>
/*     */ {
/*     */   private BST<Key, Value>.Node root;
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  46 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  51 */     return size(this.root);
/*     */   }
/*     */ 
/*     */   private int size(BST<Key, Value>.Node paramBST)
/*     */   {
/*  56 */     if (paramBST == null) return 0;
/*  57 */     return paramBST.N;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/*  66 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  71 */     return get(this.root, paramKey);
/*     */   }
/*     */ 
/*     */   private Value get(BST<Key, Value>.Node paramBST, Key paramKey) {
/*  75 */     if (paramBST == null) return null;
/*  76 */     int i = paramKey.compareTo(paramBST.key);
/*  77 */     if (i < 0) return get(paramBST.left, paramKey);
/*  78 */     if (i > 0) return get(paramBST.right, paramKey);
/*  79 */     return paramBST.val;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  87 */     if (paramValue == null) { delete(paramKey); return; }
/*  88 */     this.root = put(this.root, paramKey, paramValue);
/*  89 */     assert (check());
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node put(BST<Key, Value>.Node paramBST, Key paramKey, Value paramValue) {
/*  93 */     if (paramBST == null) return new BST.Node(paramKey, paramValue, 1);
/*  94 */     int i = paramKey.compareTo(paramBST.key);
/*  95 */     if (i < 0) paramBST.left = put(paramBST.left, paramKey, paramValue);
/*  96 */     else if (i > 0) paramBST.right = put(paramBST.right, paramKey, paramValue); else
/*  97 */       paramBST.val = paramValue;
/*  98 */     paramBST.N = (1 + size(paramBST.left) + size(paramBST.right));
/*  99 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public void deleteMin()
/*     */   {
/* 107 */     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
/* 108 */     this.root = deleteMin(this.root);
/* 109 */     assert (check());
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node deleteMin(BST<Key, Value>.Node paramBST) {
/* 113 */     if (paramBST.left == null) return paramBST.right;
/* 114 */     paramBST.left = deleteMin(paramBST.left);
/* 115 */     paramBST.N = (size(paramBST.left) + size(paramBST.right) + 1);
/* 116 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public void deleteMax() {
/* 120 */     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
/* 121 */     this.root = deleteMax(this.root);
/* 122 */     assert (check());
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node deleteMax(BST<Key, Value>.Node paramBST) {
/* 126 */     if (paramBST.right == null) return paramBST.left;
/* 127 */     paramBST.right = deleteMax(paramBST.right);
/* 128 */     paramBST.N = (size(paramBST.left) + size(paramBST.right) + 1);
/* 129 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey) {
/* 133 */     this.root = delete(this.root, paramKey);
/* 134 */     assert (check());
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node delete(BST<Key, Value>.Node paramBST, Key paramKey) {
/* 138 */     if (paramBST == null) return null;
/* 139 */     int i = paramKey.compareTo(paramBST.key);
/* 140 */     if (i < 0) { paramBST.left = delete(paramBST.left, paramKey);
/* 141 */     } else if (i > 0) { paramBST.right = delete(paramBST.right, paramKey);
/*     */     } else {
/* 143 */       if (paramBST.right == null) return paramBST.left;
/* 144 */       if (paramBST.left == null) return paramBST.right;
/* 145 */       BST<Key, Value>.Node localBST = paramBST;
/* 146 */       paramBST = min(localBST.right);
/* 147 */       paramBST.right = deleteMin(localBST.right);
/* 148 */       paramBST.left = localBST.left;
/*     */     }
/* 150 */     paramBST.N = (size(paramBST.left) + size(paramBST.right) + 1);
/* 151 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 159 */     if (isEmpty()) return null;
/* 160 */     return min(this.root).key;
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node min(BST<Key, Value>.Node paramBST) {
/* 164 */     if (paramBST.left == null) return paramBST;
/* 165 */     return min(paramBST.left);
/*     */   }
/*     */ 
/*     */   public Key max() {
/* 169 */     if (isEmpty()) return null;
/* 170 */     return max(this.root).key;
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node max(BST<Key, Value>.Node paramBST) {
/* 174 */     if (paramBST.right == null) return paramBST;
/* 175 */     return max(paramBST.right);
/*     */   }
/*     */ 
/*     */   public Key floor(Key paramKey) {
/* 179 */     BST.Node localNode = floor(this.root, paramKey);
/* 180 */     if (localNode == null) return null;
/* 181 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node floor(BST<Key, Value>.Node paramBST, Key paramKey) {
/* 185 */     if (paramBST == null) return null;
/* 186 */     int i = paramKey.compareTo(paramBST.key);
/* 187 */     if (i == 0) return paramBST;
/* 188 */     if (i < 0) return floor(paramBST.left, paramKey);
/* 189 */     BST.Node localNode = floor(paramBST.right, paramKey);
/* 190 */     if (localNode != null) return localNode;
/* 191 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public Key ceiling(Key paramKey) {
/* 195 */     BST.Node localNode = ceiling(this.root, paramKey);
/* 196 */     if (localNode == null) return null;
/* 197 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node ceiling(BST<Key, Value>.Node paramBST, Key paramKey) {
/* 201 */     if (paramBST == null) return null;
/* 202 */     int i = paramKey.compareTo(paramBST.key);
/* 203 */     if (i == 0) return paramBST;
/* 204 */     if (i < 0) {
/* 205 */       BST.Node localNode = ceiling(paramBST.left, paramKey);
/* 206 */       if (localNode != null) return localNode;
/* 207 */       return paramBST;
/*     */     }
/* 209 */     return ceiling(paramBST.right, paramKey);
/*     */   }
/*     */ 
/*     */   public Key select(int paramInt)
/*     */   {
/* 216 */     if ((paramInt < 0) || (paramInt >= size())) return null;
/* 217 */     BST.Node localNode = select(this.root, paramInt);
/* 218 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private BST<Key, Value>.Node select(BST<Key, Value>.Node paramBST, int paramInt)
/*     */   {
/* 223 */     if (paramBST == null) return null;
/* 224 */     int i = size(paramBST.left);
/* 225 */     if (i > paramInt) return select(paramBST.left, paramInt);
/* 226 */     if (i < paramInt) return select(paramBST.right, paramInt - i - 1);
/* 227 */     return paramBST;
/*     */   }
/*     */ 
/*     */   public int rank(Key paramKey) {
/* 231 */     return rank(paramKey, this.root);
/*     */   }
/*     */ 
/*     */   private int rank(Key paramKey, BST<Key, Value>.Node paramBST)
/*     */   {
/* 236 */     if (paramBST == null) return 0;
/* 237 */     int i = paramKey.compareTo(paramBST.key);
/* 238 */     if (i < 0) return rank(paramKey, paramBST.left);
/* 239 */     if (i > 0) return 1 + size(paramBST.left) + rank(paramKey, paramBST.right);
/* 240 */     return size(paramBST.left);
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 247 */     return keys(min(), max());
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys(Key paramKey1, Key paramKey2) {
/* 251 */     Queue localQueue = new Queue();
/* 252 */     keys(this.root, localQueue, paramKey1, paramKey2);
/* 253 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void keys(BST<Key, Value>.Node paramBST, Queue<Key> paramQueue, Key paramKey1, Key paramKey2) {
/* 257 */     if (paramBST == null) return;
/* 258 */     int i = paramKey1.compareTo(paramBST.key);
/* 259 */     int j = paramKey2.compareTo(paramBST.key);
/* 260 */     if (i < 0) keys(paramBST.left, paramQueue, paramKey1, paramKey2);
/* 261 */     if ((i <= 0) && (j >= 0)) paramQueue.enqueue(paramBST.key);
/* 262 */     if (j > 0) keys(paramBST.right, paramQueue, paramKey1, paramKey2); 
/*     */   }
/*     */ 
/*     */   public int size(Key paramKey1, Key paramKey2)
/*     */   {
/* 266 */     if (paramKey1.compareTo(paramKey2) > 0) return 0;
/* 267 */     if (contains(paramKey2)) return rank(paramKey2) - rank(paramKey1) + 1;
/* 268 */     return rank(paramKey2) - rank(paramKey1);
/*     */   }
/*     */ 
/*     */   public int height()
/*     */   {
/* 273 */     return height(this.root);
/*     */   }
/* 275 */   private int height(BST<Key, Value>.Node paramBST) { if (paramBST == null) return -1;
/* 276 */     return 1 + Math.max(height(paramBST.left), height(paramBST.right));
/*     */   }
/*     */ 
/*     */   public Iterable<Key> levelOrder()
/*     */   {
/* 282 */     Queue localQueue1 = new Queue();
/* 283 */     Queue localQueue2 = new Queue();
/* 284 */     localQueue2.enqueue(this.root);
/* 285 */     while (!localQueue2.isEmpty()) {
/* 286 */       BST.Node localNode = (BST.Node)localQueue2.dequeue();
/* 287 */       if (localNode != null) {
/* 288 */         localQueue1.enqueue(localNode.key);
/* 289 */         localQueue2.enqueue(localNode.left);
/* 290 */         localQueue2.enqueue(localNode.right);
/*     */       }
/*     */     }
/* 292 */     return localQueue1;
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 299 */     if (!isBST()) StdOut.println("Not in symmetric order");
/* 300 */     if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
/* 301 */     if (!isRankConsistent()) StdOut.println("Ranks not consistent");
/* 302 */     return (isBST()) && (isSizeConsistent()) && (isRankConsistent());
/*     */   }
/*     */ 
/*     */   private boolean isBST()
/*     */   {
/* 308 */     return isBST(this.root, null, null);
/*     */   }
/*     */ 
/*     */   private boolean isBST(BST<Key, Value>.Node paramBST, Key paramKey1, Key paramKey2)
/*     */   {
/* 315 */     if (paramBST == null) return true;
/* 316 */     if ((paramKey1 != null) && (paramBST.key.compareTo(paramKey1) <= 0)) return false;
/* 317 */     if ((paramKey2 != null) && (paramBST.key.compareTo(paramKey2) >= 0)) return false;
/* 318 */     return (isBST(paramBST.left, paramKey1, paramBST.key)) && (isBST(paramBST.right, paramBST.key, paramKey2));
/*     */   }
/*     */ 
/*     */   private boolean isSizeConsistent() {
/* 322 */     return isSizeConsistent(this.root);
/*     */   }
/* 324 */   private boolean isSizeConsistent(BST<Key, Value>.Node paramBST) { if (paramBST == null) return true;
/* 325 */     if (paramBST.N != size(paramBST.left) + size(paramBST.right) + 1) return false;
/* 326 */     return (isSizeConsistent(paramBST.left)) && (isSizeConsistent(paramBST.right));
/*     */   }
/*     */ 
/*     */   private boolean isRankConsistent()
/*     */   {
/* 331 */     for (int i = 0; i < size(); i++)
/* 332 */       if (i != rank(select(i))) return false;
/* 333 */     for (Comparable localComparable : keys())
/* 334 */       if (localComparable.compareTo(select(rank(localComparable))) != 0) return false;
/* 335 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 343 */     BST localBST = new BST();
/*     */     String str;
/* 344 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 345 */       str = StdIn.readString();
/* 346 */       localBST.put(str, Integer.valueOf(i));
/*     */     }
/*     */ 
/* 349 */     for (Iterator localIterator = localBST.levelOrder().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 350 */       StdOut.println(str + " " + localBST.get(str));
/*     */     }
/* 352 */     StdOut.println();
/*     */ 
/* 354 */     for (localIterator = localBST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 355 */       StdOut.println(str + " " + localBST.get(str));
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Key key;
/*     */     private Value val;
/*     */     private BST<Key, Value>.Node left;
/*     */     private BST<Key, Value>.Node right;
/*     */     private int N;
/*     */ 
/*     */     public Node(Value paramInt, int arg3)
/*     */     {
/*  38 */       this.key = paramInt;
/*     */       Object localObject;
/*  39 */       this.val = localObject;
/*     */       int i;
/*  40 */       this.N = i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BST
 * JD-Core Version:    0.6.2
 */