/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TrieSET
/*     */   implements Iterable<String>
/*     */ {
/*     */   private static final int R = 256;
/*     */   private TrieSET.Node root;
/*     */   private int N;
/*     */ 
/*     */   public boolean contains(String paramString)
/*     */   {
/*  62 */     TrieSET.Node localNode = get(this.root, paramString, 0);
/*  63 */     if (localNode == null) return false;
/*  64 */     return localNode.isString;
/*     */   }
/*     */ 
/*     */   private TrieSET.Node get(TrieSET.Node paramNode, String paramString, int paramInt) {
/*  68 */     if (paramNode == null) return null;
/*  69 */     if (paramInt == paramString.length()) return paramNode;
/*  70 */     int i = paramString.charAt(paramInt);
/*  71 */     return get(paramNode.next[i], paramString, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void add(String paramString)
/*     */   {
/*  80 */     this.root = add(this.root, paramString, 0);
/*     */   }
/*     */ 
/*     */   private TrieSET.Node add(TrieSET.Node paramNode, String paramString, int paramInt) {
/*  84 */     if (paramNode == null) paramNode = new TrieSET.Node(null);
/*  85 */     if (paramInt == paramString.length()) {
/*  86 */       if (!paramNode.isString) this.N += 1;
/*  87 */       paramNode.isString = true;
/*     */     }
/*     */     else {
/*  90 */       int i = paramString.charAt(paramInt);
/*  91 */       paramNode.next[i] = add(paramNode.next[i], paramString, paramInt + 1);
/*     */     }
/*  93 */     return paramNode;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 101 */     return this.N;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 109 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public Iterator<String> iterator()
/*     */   {
/* 119 */     return keysWithPrefix("").iterator();
/*     */   }
/*     */ 
/*     */   public Iterable<String> keysWithPrefix(String paramString)
/*     */   {
/* 129 */     Queue localQueue = new Queue();
/* 130 */     TrieSET.Node localNode = get(this.root, paramString, 0);
/* 131 */     collect(localNode, new StringBuilder(paramString), localQueue);
/* 132 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TrieSET.Node paramNode, StringBuilder paramStringBuilder, Queue<String> paramQueue) {
/* 136 */     if (paramNode == null) return;
/* 137 */     if (paramNode.isString) paramQueue.enqueue(paramStringBuilder.toString());
/*     */     int j;
/* 138 */     for (int i = 0; i < 256; j = (char)(i + 1)) {
/* 139 */       paramStringBuilder.append(i);
/* 140 */       collect(paramNode.next[i], paramStringBuilder, paramQueue);
/* 141 */       paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Iterable<String> keysThatMatch(String paramString)
/*     */   {
/* 153 */     Queue localQueue = new Queue();
/* 154 */     StringBuilder localStringBuilder = new StringBuilder();
/* 155 */     collect(this.root, localStringBuilder, paramString, localQueue);
/* 156 */     return localQueue;
/*     */   }
/*     */ 
/*     */   private void collect(TrieSET.Node paramNode, StringBuilder paramStringBuilder, String paramString, Queue<String> paramQueue) {
/* 160 */     if (paramNode == null) return;
/* 161 */     int i = paramStringBuilder.length();
/* 162 */     if ((i == paramString.length()) && (paramNode.isString))
/* 163 */       paramQueue.enqueue(paramStringBuilder.toString());
/* 164 */     if (i == paramString.length())
/* 165 */       return;
/* 166 */     char c = paramString.charAt(i);
/* 167 */     if (c == '.')
/*     */     {
/*     */       int k;
/* 168 */       for (int j = 0; j < 256; k = (char)(j + 1)) {
/* 169 */         paramStringBuilder.append(j);
/* 170 */         collect(paramNode.next[j], paramStringBuilder, paramString, paramQueue);
/* 171 */         paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */       }
/*     */     }
/*     */     else {
/* 175 */       paramStringBuilder.append(c);
/* 176 */       collect(paramNode.next[c], paramStringBuilder, paramString, paramQueue);
/* 177 */       paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String longestPrefixOf(String paramString)
/*     */   {
/* 190 */     int i = longestPrefixOf(this.root, paramString, 0, -1);
/* 191 */     if (i == -1) return null;
/* 192 */     return paramString.substring(0, i);
/*     */   }
/*     */ 
/*     */   private int longestPrefixOf(TrieSET.Node paramNode, String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 200 */     if (paramNode == null) return paramInt2;
/* 201 */     if (paramNode.isString) paramInt2 = paramInt1;
/* 202 */     if (paramInt1 == paramString.length()) return paramInt2;
/* 203 */     int i = paramString.charAt(paramInt1);
/* 204 */     return longestPrefixOf(paramNode.next[i], paramString, paramInt1 + 1, paramInt2);
/*     */   }
/*     */ 
/*     */   public void delete(String paramString)
/*     */   {
/* 213 */     this.root = delete(this.root, paramString, 0);
/*     */   }
/*     */ 
/*     */   private TrieSET.Node delete(TrieSET.Node paramNode, String paramString, int paramInt) {
/* 217 */     if (paramNode == null) return null;
/* 218 */     if (paramInt == paramString.length()) {
/* 219 */       if (paramNode.isString) this.N -= 1;
/* 220 */       paramNode.isString = false;
/*     */     }
/*     */     else {
/* 223 */       i = paramString.charAt(paramInt);
/* 224 */       paramNode.next[i] = delete(paramNode.next[i], paramString, paramInt + 1);
/*     */     }
/*     */ 
/* 228 */     if (paramNode.isString) return paramNode;
/* 229 */     for (int i = 0; i < 256; i++)
/* 230 */       if (paramNode.next[i] != null)
/* 231 */         return paramNode;
/* 232 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 240 */     TrieSET localTrieSET = new TrieSET();
/* 241 */     while (!StdIn.isEmpty()) {
/* 242 */       localObject = StdIn.readString();
/* 243 */       localTrieSET.add((String)localObject);
/*     */     }
/*     */     String str;
/* 247 */     if (localTrieSET.size() < 100) {
/* 248 */       StdOut.println("keys(\"\"):");
/* 249 */       for (localObject = localTrieSET.iterator(); ((Iterator)localObject).hasNext(); ) { str = (String)((Iterator)localObject).next();
/* 250 */         StdOut.println(str);
/*     */       }
/* 252 */       StdOut.println();
/*     */     }
/*     */ 
/* 255 */     StdOut.println("longestPrefixOf(\"shellsort\"):");
/* 256 */     StdOut.println(localTrieSET.longestPrefixOf("shellsort"));
/* 257 */     StdOut.println();
/*     */ 
/* 259 */     StdOut.println("longestPrefixOf(\"xshellsort\"):");
/* 260 */     StdOut.println(localTrieSET.longestPrefixOf("xshellsort"));
/* 261 */     StdOut.println();
/*     */ 
/* 263 */     StdOut.println("keysWithPrefix(\"shor\"):");
/* 264 */     for (Object localObject = localTrieSET.keysWithPrefix("shor").iterator(); ((Iterator)localObject).hasNext(); ) { str = (String)((Iterator)localObject).next();
/* 265 */       StdOut.println(str); }
/* 266 */     StdOut.println();
/*     */ 
/* 268 */     StdOut.println("keysWithPrefix(\"shortening\"):");
/* 269 */     for (localObject = localTrieSET.keysWithPrefix("shortening").iterator(); ((Iterator)localObject).hasNext(); ) { str = (String)((Iterator)localObject).next();
/* 270 */       StdOut.println(str); }
/* 271 */     StdOut.println();
/*     */ 
/* 273 */     StdOut.println("keysThatMatch(\".he.l.\"):");
/* 274 */     for (localObject = localTrieSET.keysThatMatch(".he.l.").iterator(); ((Iterator)localObject).hasNext(); ) { str = (String)((Iterator)localObject).next();
/* 275 */       StdOut.println(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Node
/*     */   {
/*  44 */     private Node[] next = new Node[256];
/*     */     private boolean isString;
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     TrieSET
 * JD-Core Version:    0.6.2
 */