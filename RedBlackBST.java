/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class RedBlackBST<Key extends Comparable<Key>, Value>
/*     */ {
/*     */   private static final boolean RED = true;
/*     */   private static final boolean BLACK = false;
/*     */   private RedBlackBST<Key, Value>.Node root;
/*     */ 
/*     */   private boolean isRed(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/*  57 */     if (paramRedBlackBST == null) return false;
/*  58 */     return paramRedBlackBST.color == true;
/*     */   }
/*     */ 
/*     */   private int size(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/*  63 */     if (paramRedBlackBST == null) return 0;
/*  64 */     return paramRedBlackBST.N;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  73 */     return size(this.root);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/*  77 */     return this.root == null;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  85 */     return get(this.root, paramKey);
/*     */   }
/*     */ 
/*     */   private Value get(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey) {
/*  89 */     while (paramRedBlackBST != null) {
/*  90 */       int i = paramKey.compareTo(paramRedBlackBST.key);
/*  91 */       if (i < 0) paramRedBlackBST = paramRedBlackBST.left;
/*  92 */       else if (i > 0) paramRedBlackBST = paramRedBlackBST.right; else
/*  93 */         return paramRedBlackBST.val;
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/* 100 */     return get(paramKey) != null;
/*     */   }
/*     */ 
/*     */   private boolean contains(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey)
/*     */   {
/* 105 */     return get(paramRedBlackBST, paramKey) != null;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/* 115 */     this.root = put(this.root, paramKey, paramValue);
/* 116 */     this.root.color = false;
/* 117 */     assert (check());
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node put(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey, Value paramValue)
/*     */   {
/* 122 */     if (paramRedBlackBST == null) return new RedBlackBST.Node(paramKey, paramValue, true, 1);
/*     */ 
/* 124 */     int i = paramKey.compareTo(paramRedBlackBST.key);
/* 125 */     if (i < 0) paramRedBlackBST.left = put(paramRedBlackBST.left, paramKey, paramValue);
/* 126 */     else if (i > 0) paramRedBlackBST.right = put(paramRedBlackBST.right, paramKey, paramValue); else {
/* 127 */       paramRedBlackBST.val = paramValue;
/*     */     }
/*     */ 
/* 130 */     if ((isRed(paramRedBlackBST.right)) && (!isRed(paramRedBlackBST.left))) paramRedBlackBST = rotateLeft(paramRedBlackBST);
/* 131 */     if ((isRed(paramRedBlackBST.left)) && (isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left))) paramRedBlackBST = rotateRight(paramRedBlackBST);
/* 132 */     if ((isRed(paramRedBlackBST.left)) && (isRed(paramRedBlackBST.right))) flipColors(paramRedBlackBST);
/* 133 */     paramRedBlackBST.N = (size(paramRedBlackBST.left) + size(paramRedBlackBST.right) + 1);
/*     */ 
/* 135 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   public void deleteMin()
/*     */   {
/* 144 */     if (isEmpty()) throw new NoSuchElementException("BST underflow");
/*     */ 
/* 147 */     if ((!isRed(this.root.left)) && (!isRed(this.root.right))) {
/* 148 */       this.root.color = true;
/*     */     }
/* 150 */     this.root = deleteMin(this.root);
/* 151 */     if (!isEmpty()) this.root.color = false;
/* 152 */     assert (check());
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node deleteMin(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 157 */     if (paramRedBlackBST.left == null) {
/* 158 */       return null;
/*     */     }
/* 160 */     if ((!isRed(paramRedBlackBST.left)) && (!isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left))) {
/* 161 */       paramRedBlackBST = moveRedLeft(paramRedBlackBST);
/*     */     }
/* 163 */     paramRedBlackBST.left = deleteMin(paramRedBlackBST.left);
/* 164 */     return balance(paramRedBlackBST);
/*     */   }
/*     */ 
/*     */   public void deleteMax()
/*     */   {
/* 170 */     if (isEmpty()) throw new NoSuchElementException("BST underflow");
/*     */ 
/* 173 */     if ((!isRed(this.root.left)) && (!isRed(this.root.right))) {
/* 174 */       this.root.color = true;
/*     */     }
/* 176 */     this.root = deleteMax(this.root);
/* 177 */     if (!isEmpty()) this.root.color = false;
/* 178 */     assert (check());
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node deleteMax(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 183 */     if (isRed(paramRedBlackBST.left)) {
/* 184 */       paramRedBlackBST = rotateRight(paramRedBlackBST);
/*     */     }
/* 186 */     if (paramRedBlackBST.right == null) {
/* 187 */       return null;
/*     */     }
/* 189 */     if ((!isRed(paramRedBlackBST.right)) && (!isRed(RedBlackBST.Node.access$400(paramRedBlackBST).left))) {
/* 190 */       paramRedBlackBST = moveRedRight(paramRedBlackBST);
/*     */     }
/* 192 */     paramRedBlackBST.right = deleteMax(paramRedBlackBST.right);
/*     */ 
/* 194 */     return balance(paramRedBlackBST);
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/* 199 */     if (!contains(paramKey)) {
/* 200 */       System.err.println("symbol table does not contain " + paramKey);
/* 201 */       return;
/*     */     }
/*     */ 
/* 205 */     if ((!isRed(this.root.left)) && (!isRed(this.root.right))) {
/* 206 */       this.root.color = true;
/*     */     }
/* 208 */     this.root = delete(this.root, paramKey);
/* 209 */     if (!isEmpty()) this.root.color = false;
/* 210 */     assert (check());
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node delete(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey)
/*     */   {
/* 215 */     assert (contains(paramRedBlackBST, paramKey));
/*     */ 
/* 217 */     if (paramKey.compareTo(paramRedBlackBST.key) < 0) {
/* 218 */       if ((!isRed(paramRedBlackBST.left)) && (!isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left)))
/* 219 */         paramRedBlackBST = moveRedLeft(paramRedBlackBST);
/* 220 */       paramRedBlackBST.left = delete(paramRedBlackBST.left, paramKey);
/*     */     }
/*     */     else {
/* 223 */       if (isRed(paramRedBlackBST.left))
/* 224 */         paramRedBlackBST = rotateRight(paramRedBlackBST);
/* 225 */       if ((paramKey.compareTo(paramRedBlackBST.key) == 0) && (paramRedBlackBST.right == null))
/* 226 */         return null;
/* 227 */       if ((!isRed(paramRedBlackBST.right)) && (!isRed(RedBlackBST.Node.access$400(paramRedBlackBST).left)))
/* 228 */         paramRedBlackBST = moveRedRight(paramRedBlackBST);
/* 229 */       if (paramKey.compareTo(paramRedBlackBST.key) == 0) {
/* 230 */         RedBlackBST.Node localNode = min(paramRedBlackBST.right);
/* 231 */         paramRedBlackBST.key = localNode.key;
/* 232 */         paramRedBlackBST.val = localNode.val;
/*     */ 
/* 235 */         paramRedBlackBST.right = deleteMin(paramRedBlackBST.right);
/*     */       } else {
/* 237 */         paramRedBlackBST.right = delete(paramRedBlackBST.right, paramKey);
/*     */       }
/*     */     }
/* 239 */     return balance(paramRedBlackBST);
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node rotateRight(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 248 */     assert ((paramRedBlackBST != null) && (isRed(paramRedBlackBST.left)));
/* 249 */     RedBlackBST.Node localNode = paramRedBlackBST.left;
/* 250 */     paramRedBlackBST.left = localNode.right;
/* 251 */     localNode.right = paramRedBlackBST;
/* 252 */     localNode.color = RedBlackBST.Node.access$400(localNode).color;
/* 253 */     localNode.right.color = true;
/* 254 */     localNode.N = paramRedBlackBST.N;
/* 255 */     paramRedBlackBST.N = (size(paramRedBlackBST.left) + size(paramRedBlackBST.right) + 1);
/* 256 */     return localNode;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node rotateLeft(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 261 */     assert ((paramRedBlackBST != null) && (isRed(paramRedBlackBST.right)));
/* 262 */     RedBlackBST.Node localNode = paramRedBlackBST.right;
/* 263 */     paramRedBlackBST.right = localNode.left;
/* 264 */     localNode.left = paramRedBlackBST;
/* 265 */     localNode.color = RedBlackBST.Node.access$300(localNode).color;
/* 266 */     localNode.left.color = true;
/* 267 */     localNode.N = paramRedBlackBST.N;
/* 268 */     paramRedBlackBST.N = (size(paramRedBlackBST.left) + size(paramRedBlackBST.right) + 1);
/* 269 */     return localNode;
/*     */   }
/*     */ 
/*     */   private void flipColors(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 275 */     assert ((paramRedBlackBST != null) && (paramRedBlackBST.left != null) && (paramRedBlackBST.right != null));
/* 276 */     assert (((!isRed(paramRedBlackBST)) && (isRed(paramRedBlackBST.left)) && (isRed(paramRedBlackBST.right))) || ((isRed(paramRedBlackBST)) && (!isRed(paramRedBlackBST.left)) && (!isRed(paramRedBlackBST.right))));
/*     */ 
/* 278 */     paramRedBlackBST.color = (!paramRedBlackBST.color);
/* 279 */     paramRedBlackBST.left.color = (!RedBlackBST.Node.access$300(paramRedBlackBST).color);
/* 280 */     paramRedBlackBST.right.color = (!RedBlackBST.Node.access$400(paramRedBlackBST).color);
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node moveRedLeft(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 286 */     assert (paramRedBlackBST != null);
/* 287 */     assert ((isRed(paramRedBlackBST)) && (!isRed(paramRedBlackBST.left)) && (!isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left)));
/*     */ 
/* 289 */     flipColors(paramRedBlackBST);
/* 290 */     if (isRed(RedBlackBST.Node.access$400(paramRedBlackBST).left)) {
/* 291 */       paramRedBlackBST.right = rotateRight(paramRedBlackBST.right);
/* 292 */       paramRedBlackBST = rotateLeft(paramRedBlackBST);
/*     */     }
/* 294 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node moveRedRight(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 300 */     assert (paramRedBlackBST != null);
/* 301 */     assert ((isRed(paramRedBlackBST)) && (!isRed(paramRedBlackBST.right)) && (!isRed(RedBlackBST.Node.access$400(paramRedBlackBST).left)));
/* 302 */     flipColors(paramRedBlackBST);
/* 303 */     if (isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left)) {
/* 304 */       paramRedBlackBST = rotateRight(paramRedBlackBST);
/*     */     }
/* 306 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node balance(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 311 */     assert (paramRedBlackBST != null);
/*     */ 
/* 313 */     if (isRed(paramRedBlackBST.right)) paramRedBlackBST = rotateLeft(paramRedBlackBST);
/* 314 */     if ((isRed(paramRedBlackBST.left)) && (isRed(RedBlackBST.Node.access$300(paramRedBlackBST).left))) paramRedBlackBST = rotateRight(paramRedBlackBST);
/* 315 */     if ((isRed(paramRedBlackBST.left)) && (isRed(paramRedBlackBST.right))) flipColors(paramRedBlackBST);
/*     */ 
/* 317 */     paramRedBlackBST.N = (size(paramRedBlackBST.left) + size(paramRedBlackBST.right) + 1);
/* 318 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   public int height()
/*     */   {
/* 327 */     return height(this.root);
/*     */   }
/* 329 */   private int height(RedBlackBST<Key, Value>.Node paramRedBlackBST) { if (paramRedBlackBST == null) return -1;
/* 330 */     return 1 + Math.max(height(paramRedBlackBST.left), height(paramRedBlackBST.right));
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 339 */     if (isEmpty()) return null;
/* 340 */     return min(this.root).key;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node min(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 345 */     assert (paramRedBlackBST != null);
/* 346 */     if (paramRedBlackBST.left == null) return paramRedBlackBST;
/* 347 */     return min(paramRedBlackBST.left);
/*     */   }
/*     */ 
/*     */   public Key max()
/*     */   {
/* 352 */     if (isEmpty()) return null;
/* 353 */     return max(this.root).key;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node max(RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 358 */     assert (paramRedBlackBST != null);
/* 359 */     if (paramRedBlackBST.right == null) return paramRedBlackBST;
/* 360 */     return max(paramRedBlackBST.right);
/*     */   }
/*     */ 
/*     */   public Key floor(Key paramKey)
/*     */   {
/* 365 */     RedBlackBST.Node localNode = floor(this.root, paramKey);
/* 366 */     if (localNode == null) return null;
/* 367 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node floor(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey)
/*     */   {
/* 372 */     if (paramRedBlackBST == null) return null;
/* 373 */     int i = paramKey.compareTo(paramRedBlackBST.key);
/* 374 */     if (i == 0) return paramRedBlackBST;
/* 375 */     if (i < 0) return floor(paramRedBlackBST.left, paramKey);
/* 376 */     RedBlackBST.Node localNode = floor(paramRedBlackBST.right, paramKey);
/* 377 */     if (localNode != null) return localNode;
/* 378 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   public Key ceiling(Key paramKey)
/*     */   {
/* 383 */     RedBlackBST.Node localNode = ceiling(this.root, paramKey);
/* 384 */     if (localNode == null) return null;
/* 385 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node ceiling(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey)
/*     */   {
/* 390 */     if (paramRedBlackBST == null) return null;
/* 391 */     int i = paramKey.compareTo(paramRedBlackBST.key);
/* 392 */     if (i == 0) return paramRedBlackBST;
/* 393 */     if (i > 0) return ceiling(paramRedBlackBST.right, paramKey);
/* 394 */     RedBlackBST.Node localNode = ceiling(paramRedBlackBST.left, paramKey);
/* 395 */     if (localNode != null) return localNode;
/* 396 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   public Key select(int paramInt)
/*     */   {
/* 402 */     if ((paramInt < 0) || (paramInt >= size())) return null;
/* 403 */     RedBlackBST.Node localNode = select(this.root, paramInt);
/* 404 */     return localNode.key;
/*     */   }
/*     */ 
/*     */   private RedBlackBST<Key, Value>.Node select(RedBlackBST<Key, Value>.Node paramRedBlackBST, int paramInt)
/*     */   {
/* 409 */     assert (paramRedBlackBST != null);
/* 410 */     assert ((paramInt >= 0) && (paramInt < size(paramRedBlackBST)));
/* 411 */     int i = size(paramRedBlackBST.left);
/* 412 */     if (i > paramInt) return select(paramRedBlackBST.left, paramInt);
/* 413 */     if (i < paramInt) return select(paramRedBlackBST.right, paramInt - i - 1);
/* 414 */     return paramRedBlackBST;
/*     */   }
/*     */ 
/*     */   public int rank(Key paramKey)
/*     */   {
/* 419 */     return rank(paramKey, this.root);
/*     */   }
/*     */ 
/*     */   private int rank(Key paramKey, RedBlackBST<Key, Value>.Node paramRedBlackBST)
/*     */   {
/* 424 */     if (paramRedBlackBST == null) return 0;
/* 425 */     int i = paramKey.compareTo(paramRedBlackBST.key);
/* 426 */     if (i < 0) return rank(paramKey, paramRedBlackBST.left);
/* 427 */     if (i > 0) return 1 + size(paramRedBlackBST.left) + rank(paramKey, paramRedBlackBST.right);
/* 428 */     return size(paramRedBlackBST.left);
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 437 */     return keys(min(), max());
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys(Key paramKey1, Key paramKey2)
/*     */   {
/* 442 */     Queue localQueue = new Queue();
/*     */ 
/* 444 */     keys(this.root, localQueue, paramKey1, paramKey2);
/* 445 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void keys(RedBlackBST<Key, Value>.Node paramRedBlackBST, Queue<Key> paramQueue, Key paramKey1, Key paramKey2)
/*     */   {
/* 451 */     if (paramRedBlackBST == null) return;
/* 452 */     int i = paramKey1.compareTo(paramRedBlackBST.key);
/* 453 */     int j = paramKey2.compareTo(paramRedBlackBST.key);
/* 454 */     if (i < 0) keys(paramRedBlackBST.left, paramQueue, paramKey1, paramKey2);
/* 455 */     if ((i <= 0) && (j >= 0)) paramQueue.enqueue(paramRedBlackBST.key);
/* 456 */     if (j > 0) keys(paramRedBlackBST.right, paramQueue, paramKey1, paramKey2);
/*     */   }
/*     */ 
/*     */   public int size(Key paramKey1, Key paramKey2)
/*     */   {
/* 461 */     if (paramKey1.compareTo(paramKey2) > 0) return 0;
/* 462 */     if (contains(paramKey2)) return rank(paramKey2) - rank(paramKey1) + 1;
/* 463 */     return rank(paramKey2) - rank(paramKey1);
/*     */   }
/*     */ 
/*     */   private boolean check()
/*     */   {
/* 471 */     if (!isBST()) StdOut.println("Not in symmetric order");
/* 472 */     if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
/* 473 */     if (!isRankConsistent()) StdOut.println("Ranks not consistent");
/* 474 */     if (!is23()) StdOut.println("Not a 2-3 tree");
/* 475 */     if (!isBalanced()) StdOut.println("Not balanced");
/* 476 */     return (isBST()) && (isSizeConsistent()) && (isRankConsistent()) && (is23()) && (isBalanced());
/*     */   }
/*     */ 
/*     */   private boolean isBST()
/*     */   {
/* 482 */     return isBST(this.root, null, null);
/*     */   }
/*     */ 
/*     */   private boolean isBST(RedBlackBST<Key, Value>.Node paramRedBlackBST, Key paramKey1, Key paramKey2)
/*     */   {
/* 489 */     if (paramRedBlackBST == null) return true;
/* 490 */     if ((paramKey1 != null) && (paramRedBlackBST.key.compareTo(paramKey1) <= 0)) return false;
/* 491 */     if ((paramKey2 != null) && (paramRedBlackBST.key.compareTo(paramKey2) >= 0)) return false;
/* 492 */     return (isBST(paramRedBlackBST.left, paramKey1, paramRedBlackBST.key)) && (isBST(paramRedBlackBST.right, paramRedBlackBST.key, paramKey2));
/*     */   }
/*     */ 
/*     */   private boolean isSizeConsistent() {
/* 496 */     return isSizeConsistent(this.root);
/*     */   }
/* 498 */   private boolean isSizeConsistent(RedBlackBST<Key, Value>.Node paramRedBlackBST) { if (paramRedBlackBST == null) return true;
/* 499 */     if (paramRedBlackBST.N != size(paramRedBlackBST.left) + size(paramRedBlackBST.right) + 1) return false;
/* 500 */     return (isSizeConsistent(paramRedBlackBST.left)) && (isSizeConsistent(paramRedBlackBST.right));
/*     */   }
/*     */ 
/*     */   private boolean isRankConsistent()
/*     */   {
/* 505 */     for (int i = 0; i < size(); i++)
/* 506 */       if (i != rank(select(i))) return false;
/* 507 */     for (Comparable localComparable : keys())
/* 508 */       if (localComparable.compareTo(select(rank(localComparable))) != 0) return false;
/* 509 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean is23()
/*     */   {
/* 514 */     return is23(this.root);
/*     */   }
/* 516 */   private boolean is23(RedBlackBST<Key, Value>.Node paramRedBlackBST) { if (paramRedBlackBST == null) return true;
/* 517 */     if (isRed(paramRedBlackBST.right)) return false;
/* 518 */     if ((paramRedBlackBST != this.root) && (isRed(paramRedBlackBST)) && (isRed(paramRedBlackBST.left)))
/* 519 */       return false;
/* 520 */     return (is23(paramRedBlackBST.left)) && (is23(paramRedBlackBST.right));
/*     */   }
/*     */ 
/*     */   private boolean isBalanced()
/*     */   {
/* 525 */     int i = 0;
/* 526 */     RedBlackBST.Node localNode = this.root;
/* 527 */     while (localNode != null) {
/* 528 */       if (!isRed(localNode)) i++;
/* 529 */       localNode = localNode.left;
/*     */     }
/* 531 */     return isBalanced(this.root, i);
/*     */   }
/*     */ 
/*     */   private boolean isBalanced(RedBlackBST<Key, Value>.Node paramRedBlackBST, int paramInt)
/*     */   {
/* 536 */     if (paramRedBlackBST == null) return paramInt == 0;
/* 537 */     if (!isRed(paramRedBlackBST)) paramInt--;
/* 538 */     return (isBalanced(paramRedBlackBST.left, paramInt)) && (isBalanced(paramRedBlackBST.right, paramInt));
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 546 */     RedBlackBST localRedBlackBST = new RedBlackBST();
/*     */     String str;
/* 547 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 548 */       str = StdIn.readString();
/* 549 */       localRedBlackBST.put(str, Integer.valueOf(i));
/*     */     }
/* 551 */     for (Iterator localIterator = localRedBlackBST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 552 */       StdOut.println(str + " " + localRedBlackBST.get(str)); }
/* 553 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private Key key;
/*     */     private Value val;
/*     */     private RedBlackBST<Key, Value>.Node left;
/*     */     private RedBlackBST<Key, Value>.Node right;
/*     */     private boolean color;
/*     */     private int N;
/*     */ 
/*     */     public Node(Value paramBoolean, boolean paramInt, int arg4)
/*     */     {
/*  45 */       this.key = paramBoolean;
/*  46 */       this.val = paramInt;
/*     */       boolean bool;
/*  47 */       this.color = bool;
/*     */       int i;
/*  48 */       this.N = i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     RedBlackBST
 * JD-Core Version:    0.6.2
 */