/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TST<Value>
/*     */ {
/*     */   private int N;
/*     */   private TST<Value>.Node root;
/*     */ 
/*     */   public int size()
/*     */   {
/*  41 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean contains(String paramString)
/*     */   {
/*  48 */     return get(paramString) != null;
/*     */   }
/*     */ 
/*     */   public Value get(String paramString) {
/*  52 */     if (paramString == null) throw new NullPointerException();
/*  53 */     if (paramString.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
/*  54 */     TST.Node localNode = get(this.root, paramString, 0);
/*  55 */     if (localNode == null) return null;
/*  56 */     return localNode.val;
/*     */   }
/*     */ 
/*     */   private TST<Value>.Node get(TST<Value>.Node paramTST, String paramString, int paramInt)
/*     */   {
/*  61 */     if (paramString == null) throw new NullPointerException();
/*  62 */     if (paramString.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
/*  63 */     if (paramTST == null) return null;
/*  64 */     int i = paramString.charAt(paramInt);
/*  65 */     if (i < paramTST.c) return get(paramTST.left, paramString, paramInt);
/*  66 */     if (i > paramTST.c) return get(paramTST.right, paramString, paramInt);
/*  67 */     if (paramInt < paramString.length() - 1) return get(paramTST.mid, paramString, paramInt + 1);
/*  68 */     return paramTST;
/*     */   }
/*     */ 
/*     */   public void put(String paramString, Value paramValue)
/*     */   {
/*  76 */     if (!contains(paramString)) this.N += 1;
/*  77 */     this.root = put(this.root, paramString, paramValue, 0);
/*     */   }
/*     */ 
/*     */   private TST<Value>.Node put(TST<Value>.Node paramTST, String paramString, Value paramValue, int paramInt) {
/*  81 */     char c = paramString.charAt(paramInt);
/*  82 */     if (paramTST == null) {
/*  83 */       paramTST = new TST.Node(null);
/*  84 */       paramTST.c = c;
/*     */     }
/*  86 */     if (c < paramTST.c) paramTST.left = put(paramTST.left, paramString, paramValue, paramInt);
/*  87 */     else if (c > paramTST.c) paramTST.right = put(paramTST.right, paramString, paramValue, paramInt);
/*  88 */     else if (paramInt < paramString.length() - 1) paramTST.mid = put(paramTST.mid, paramString, paramValue, paramInt + 1); else
/*  89 */       paramTST.val = paramValue;
/*  90 */     return paramTST;
/*     */   }
/*     */ 
/*     */   public String longestPrefixOf(String paramString)
/*     */   {
/*  98 */     if ((paramString == null) || (paramString.length() == 0)) return null;
/*  99 */     int i = 0;
/* 100 */     TST.Node localNode = this.root;
/* 101 */     int j = 0;
/* 102 */     while ((localNode != null) && (j < paramString.length())) {
/* 103 */       int k = paramString.charAt(j);
/* 104 */       if (k < localNode.c) { localNode = localNode.left;
/* 105 */       } else if (k > localNode.c) { localNode = localNode.right;
/*     */       } else {
/* 107 */         j++;
/* 108 */         if (localNode.val != null) i = j;
/* 109 */         localNode = localNode.mid;
/*     */       }
/*     */     }
/* 112 */     return paramString.substring(0, i);
/*     */   }
/*     */ 
/*     */   public Iterable<String> keys()
/*     */   {
/* 117 */     Queue localQueue = new Queue();
/* 118 */     collect(this.root, "", localQueue);
/* 119 */     return localQueue;
/*     */   }
/*     */ 
/*     */   public Iterable<String> prefixMatch(String paramString)
/*     */   {
/* 124 */     Queue localQueue = new Queue();
/* 125 */     TST.Node localNode = get(this.root, paramString, 0);
/* 126 */     if (localNode == null) return localQueue;
/* 127 */     if (localNode.val != null) localQueue.enqueue(paramString);
/* 128 */     collect(localNode.mid, paramString, localQueue);
/* 129 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TST<Value>.Node paramTST, String paramString, Queue<String> paramQueue)
/*     */   {
/* 134 */     if (paramTST == null) return;
/* 135 */     collect(paramTST.left, paramString, paramQueue);
/* 136 */     if (paramTST.val != null) paramQueue.enqueue(paramString + paramTST.c);
/* 137 */     collect(paramTST.mid, paramString + paramTST.c, paramQueue);
/* 138 */     collect(paramTST.right, paramString, paramQueue);
/*     */   }
/*     */ 
/*     */   public Iterable<String> wildcardMatch(String paramString)
/*     */   {
/* 144 */     Queue localQueue = new Queue();
/* 145 */     collect(this.root, "", 0, paramString, localQueue);
/* 146 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TST<Value>.Node paramTST, String paramString1, int paramInt, String paramString2, Queue<String> paramQueue) {
/* 150 */     if (paramTST == null) return;
/* 151 */     int i = paramString2.charAt(paramInt);
/* 152 */     if ((i == 46) || (i < paramTST.c)) collect(paramTST.left, paramString1, paramInt, paramString2, paramQueue);
/* 153 */     if ((i == 46) || (i == paramTST.c)) {
/* 154 */       if ((paramInt == paramString2.length() - 1) && (paramTST.val != null)) paramQueue.enqueue(paramString1 + paramTST.c);
/* 155 */       if (paramInt < paramString2.length() - 1) collect(paramTST.mid, paramString1 + paramTST.c, paramInt + 1, paramString2, paramQueue);
/*     */     }
/* 157 */     if ((i == 46) || (i > paramTST.c)) collect(paramTST.right, paramString1, paramInt, paramString2, paramQueue);
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 165 */     TST localTST = new TST();
/*     */     String str;
/* 166 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 167 */       str = StdIn.readString();
/* 168 */       localTST.put(str, Integer.valueOf(i));
/*     */     }
/*     */ 
/* 173 */     for (Iterator localIterator = localTST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 174 */       StdOut.println(str + " " + localTST.get(str));
/*     */     }
/*     */   }
/*     */ 
/*     */   private class Node
/*     */   {
/*     */     private char c;
/*     */     private TST<Value>.Node left;
/*     */     private TST<Value>.Node mid;
/*     */     private TST<Value>.Node right;
/*     */     private Value val;
/*     */ 
/*     */     private Node()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TST
 * JD-Core Version:    0.6.2
 */