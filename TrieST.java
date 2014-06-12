/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TrieST<Value>
/*     */ {
/*     */   private static final int R = 256;
/*     */   private TrieST.Node root;
/*     */   private int N;
/*     */ 
/*     */   public Value get(String paramString)
/*     */   {
/*  75 */     TrieST.Node localNode = get(this.root, paramString, 0);
/*  76 */     if (localNode == null) return null;
/*  77 */     return localNode.val;
/*     */   }
/*     */ 
/*     */   public boolean contains(String paramString)
/*     */   {
/*  88 */     return get(paramString) != null;
/*     */   }
/*     */ 
/*     */   private TrieST.Node get(TrieST.Node paramNode, String paramString, int paramInt) {
/*  92 */     if (paramNode == null) return null;
/*  93 */     if (paramInt == paramString.length()) return paramNode;
/*  94 */     int i = paramString.charAt(paramInt);
/*  95 */     return get(paramNode.next[i], paramString, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void put(String paramString, Value paramValue)
/*     */   {
/* 107 */     if (paramValue == null) delete(paramString); else
/* 108 */       this.root = put(this.root, paramString, paramValue, 0);
/*     */   }
/*     */ 
/*     */   private TrieST.Node put(TrieST.Node paramNode, String paramString, Value paramValue, int paramInt) {
/* 112 */     if (paramNode == null) paramNode = new TrieST.Node(null);
/* 113 */     if (paramInt == paramString.length()) {
/* 114 */       if (paramNode.val == null) this.N += 1;
/* 115 */       paramNode.val = paramValue;
/* 116 */       return paramNode;
/*     */     }
/* 118 */     int i = paramString.charAt(paramInt);
/* 119 */     paramNode.next[i] = put(paramNode.next[i], paramString, paramValue, paramInt + 1);
/* 120 */     return paramNode;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 128 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 136 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public Iterable<String> keys()
/*     */   {
/* 146 */     return keysWithPrefix("");
/*     */   }
/*     */ 
/*     */   public Iterable<String> keysWithPrefix(String paramString)
/*     */   {
/* 156 */     Queue localQueue = new Queue();
/* 157 */     TrieST.Node localNode = get(this.root, paramString, 0);
/* 158 */     collect(localNode, new StringBuilder(paramString), localQueue);
/* 159 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TrieST.Node paramNode, StringBuilder paramStringBuilder, Queue<String> paramQueue) {
/* 163 */     if (paramNode == null) return;
/* 164 */     if (paramNode.val != null) paramQueue.enqueue(paramStringBuilder.toString());
/*     */     int j;
/* 165 */     for (int i = 0; i < 256; j = (char)(i + 1)) {
/* 166 */       paramStringBuilder.append(i);
/* 167 */       collect(paramNode.next[i], paramStringBuilder, paramQueue);
/* 168 */       paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterable<String> keysThatMatch(String paramString)
/*     */   {
/* 180 */     Queue localQueue = new Queue();
/* 181 */     collect(this.root, new StringBuilder(), paramString, localQueue);
/* 182 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TrieST.Node paramNode, StringBuilder paramStringBuilder, String paramString, Queue<String> paramQueue) {
/* 186 */     if (paramNode == null) return;
/* 187 */     int i = paramStringBuilder.length();
/* 188 */     if ((i == paramString.length()) && (paramNode.val != null))
/* 189 */       paramQueue.enqueue(paramStringBuilder.toString());
/* 190 */     if (i == paramString.length())
/* 191 */       return;
/* 192 */     char c = paramString.charAt(i);
/* 193 */     if (c == '.')
/*     */     {
/*     */       int k;
/* 194 */       for (int j = 0; j < 256; k = (char)(j + 1)) {
/* 195 */         paramStringBuilder.append(j);
/* 196 */         collect(paramNode.next[j], paramStringBuilder, paramString, paramQueue);
/* 197 */         paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */       }
/*     */     }
/*     */     else {
/* 201 */       paramStringBuilder.append(c);
/* 202 */       collect(paramNode.next[c], paramStringBuilder, paramString, paramQueue);
/* 203 */       paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String longestPrefixOf(String paramString)
/*     */   {
/* 216 */     int i = longestPrefixOf(this.root, paramString, 0, 0);
/* 217 */     return paramString.substring(0, i);
/*     */   }
/*     */ 
/*     */   private int longestPrefixOf(TrieST.Node paramNode, String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 225 */     if (paramNode == null) return paramInt2;
/* 226 */     if (paramNode.val != null) paramInt2 = paramInt1;
/* 227 */     if (paramInt1 == paramString.length()) return paramInt2;
/* 228 */     int i = paramString.charAt(paramInt1);
/* 229 */     return longestPrefixOf(paramNode.next[i], paramString, paramInt1 + 1, paramInt2);
/*     */   }
/*     */ 
/*     */   public void delete(String paramString)
/*     */   {
/* 238 */     this.root = delete(this.root, paramString, 0);
/*     */   }
/*     */ 
/*     */   private TrieST.Node delete(TrieST.Node paramNode, String paramString, int paramInt) {
/* 242 */     if (paramNode == null) return null;
/* 243 */     if (paramInt == paramString.length()) {
/* 244 */       if (paramNode.val != null) this.N -= 1;
/* 245 */       paramNode.val = null;
/*     */     }
/*     */     else {
/* 248 */       i = paramString.charAt(paramInt);
/* 249 */       paramNode.next[i] = delete(paramNode.next[i], paramString, paramInt + 1);
/*     */     }
/*     */ 
/* 253 */     if (paramNode.val != null) return paramNode;
/* 254 */     for (int i = 0; i < 256; i++)
/* 255 */       if (paramNode.next[i] != null)
/* 256 */         return paramNode;
/* 257 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 266 */     TrieST localTrieST = new TrieST();
/*     */     String str;
/* 267 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 268 */       str = StdIn.readString();
/* 269 */       localTrieST.put(str, Integer.valueOf(i));
/*     */     }
/*     */ 
/* 273 */     if (localTrieST.size() < 100) {
/* 274 */       StdOut.println("keys(\"\"):");
/* 275 */       for (localIterator = localTrieST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 276 */         StdOut.println(str + " " + localTrieST.get(str));
/*     */       }
/* 278 */       StdOut.println();
/*     */     }
/*     */ 
/* 281 */     StdOut.println("longestPrefixOf(\"shellsort\"):");
/* 282 */     StdOut.println(localTrieST.longestPrefixOf("shellsort"));
/* 283 */     StdOut.println();
/*     */ 
/* 285 */     StdOut.println("keysWithPrefix(\"shor\"):");
/* 286 */     for (Iterator localIterator = localTrieST.keysWithPrefix("shor").iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 287 */       StdOut.println(str); }
/* 288 */     StdOut.println();
/*     */ 
/* 290 */     StdOut.println("keysThatMatch(\".he.l.\"):");
/* 291 */     for (localIterator = localTrieST.keysThatMatch(".he.l.").iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 292 */       StdOut.println(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Node
/*     */   {
/*     */     private Object val;
/*  57 */     private Node[] next = new Node[256];
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TrieST
 * JD-Core Version:    0.6.2
 */