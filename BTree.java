/*     */ public class BTree<Key extends Comparable<Key>, Value>
/*     */ {
/*     */   private static final int M = 4;
/*  44 */   private BTree.Node root = new BTree.Node(0, null);
/*     */   private int HT;
/*     */   private int N;
/*     */ 
/*     */   public int size()
/*     */   {
/*  47 */     return this.N;
/*     */   }
/*     */   public int height() {
/*  50 */     return this.HT;
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey) {
/*  54 */     return search(this.root, paramKey, this.HT);
/*     */   }
/*  56 */   private Value search(BTree.Node paramNode, Key paramKey, int paramInt) { BTree.Entry[] arrayOfEntry = paramNode.children;
/*     */     int i;
/*  59 */     if (paramInt == 0) {
/*  60 */       for (i = 0; i < paramNode.m; i++) {
/*  61 */         if (eq(paramKey, arrayOfEntry[i].key)) return arrayOfEntry[i].value;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  67 */       for (i = 0; i < paramNode.m; i++) {
/*  68 */         if ((i + 1 == paramNode.m) || (less(paramKey, arrayOfEntry[(i + 1)].key)))
/*  69 */           return search(arrayOfEntry[i].next, paramKey, paramInt - 1);
/*     */       }
/*     */     }
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  79 */     BTree.Node localNode1 = insert(this.root, paramKey, paramValue, this.HT);
/*  80 */     this.N += 1;
/*  81 */     if (localNode1 == null) return;
/*     */ 
/*  84 */     BTree.Node localNode2 = new BTree.Node(2, null);
/*  85 */     localNode2.children[0] = new BTree.Entry(BTree.Node.access$100(this.root)[0].key, null, this.root);
/*  86 */     localNode2.children[1] = new BTree.Entry(BTree.Node.access$100(localNode1)[0].key, null, localNode1);
/*  87 */     this.root = localNode2;
/*  88 */     this.HT += 1;
/*     */   }
/*     */ 
/*     */   private BTree.Node insert(BTree.Node paramNode, Key paramKey, Value paramValue, int paramInt)
/*     */   {
/*  94 */     BTree.Entry localEntry = new BTree.Entry(paramKey, paramValue, null);
/*     */ 
/*  97 */     if (paramInt == 0)
/*     */     {
/*  98 */       for (i = 0; (i < paramNode.m) && 
/*  99 */         (!less(paramKey, BTree.Node.access$100(paramNode)[i].key)); i++);
/*     */     }
/*     */ 
/* 105 */     for (int i = 0; i < paramNode.m; i++) {
/* 106 */       if ((i + 1 == paramNode.m) || (less(paramKey, BTree.Node.access$100(paramNode)[(i + 1)].key))) {
/* 107 */         BTree.Node localNode = insert(BTree.Node.access$100(paramNode)[(i++)].next, paramKey, paramValue, paramInt - 1);
/* 108 */         if (localNode == null) return null;
/* 109 */         localEntry.key = BTree.Node.access$100(localNode)[0].key;
/* 110 */         localEntry.next = localNode;
/* 111 */         break;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 116 */     for (int j = paramNode.m; j > i; j--) paramNode.children[j] = paramNode.children[(j - 1)];
/* 117 */     paramNode.children[i] = localEntry;
/* 118 */     BTree.Node.access$208(paramNode);
/* 119 */     if (paramNode.m < 4) return null;
/* 120 */     return split(paramNode);
/*     */   }
/*     */ 
/*     */   private BTree.Node split(BTree.Node paramNode)
/*     */   {
/* 125 */     BTree.Node localNode = new BTree.Node(2, null);
/* 126 */     paramNode.m = 2;
/* 127 */     for (int i = 0; i < 2; i++)
/* 128 */       localNode.children[i] = paramNode.children[(2 + i)];
/* 129 */     return localNode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 134 */     return toString(this.root, this.HT, "") + "\n";
/*     */   }
/*     */   private String toString(BTree.Node paramNode, int paramInt, String paramString) {
/* 137 */     String str = "";
/* 138 */     BTree.Entry[] arrayOfEntry = paramNode.children;
/*     */     int i;
/* 140 */     if (paramInt == 0) {
/* 141 */       for (i = 0; i < paramNode.m; i++) {
/* 142 */         str = str + paramString + arrayOfEntry[i].key + " " + arrayOfEntry[i].value + "\n";
/*     */       }
/*     */     }
/*     */     else {
/* 146 */       for (i = 0; i < paramNode.m; i++) {
/* 147 */         if (i > 0) str = str + paramString + "(" + arrayOfEntry[i].key + ")\n";
/* 148 */         str = str + toString(arrayOfEntry[i].next, paramInt - 1, new StringBuilder().append(paramString).append("     ").toString());
/*     */       }
/*     */     }
/* 151 */     return str;
/*     */   }
/*     */ 
/*     */   private boolean less(Comparable paramComparable1, Comparable paramComparable2)
/*     */   {
/* 157 */     return paramComparable1.compareTo(paramComparable2) < 0;
/*     */   }
/*     */ 
/*     */   private boolean eq(Comparable paramComparable1, Comparable paramComparable2) {
/* 161 */     return paramComparable1.compareTo(paramComparable2) == 0;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 169 */     BTree localBTree = new BTree();
/*     */ 
/* 172 */     localBTree.put("www.cs.princeton.edu", "128.112.136.11");
/* 173 */     localBTree.put("www.princeton.edu", "128.112.128.15");
/* 174 */     localBTree.put("www.yale.edu", "130.132.143.21");
/* 175 */     localBTree.put("www.simpsons.com", "209.052.165.60");
/* 176 */     localBTree.put("www.apple.com", "17.112.152.32");
/* 177 */     localBTree.put("www.amazon.com", "207.171.182.16");
/* 178 */     localBTree.put("www.ebay.com", "66.135.192.87");
/* 179 */     localBTree.put("www.cnn.com", "64.236.16.20");
/* 180 */     localBTree.put("www.google.com", "216.239.41.99");
/* 181 */     localBTree.put("www.nytimes.com", "199.239.136.200");
/* 182 */     localBTree.put("www.microsoft.com", "207.126.99.140");
/* 183 */     localBTree.put("www.dell.com", "143.166.224.230");
/* 184 */     localBTree.put("www.slashdot.org", "66.35.250.151");
/* 185 */     localBTree.put("www.espn.com", "199.181.135.201");
/* 186 */     localBTree.put("www.weather.com", "63.111.66.11");
/* 187 */     localBTree.put("www.yahoo.com", "216.109.118.65");
/*     */ 
/* 190 */     StdOut.println("cs.princeton.edu:  " + (String)localBTree.get("www.cs.princeton.edu"));
/* 191 */     StdOut.println("hardvardsucks.com: " + (String)localBTree.get("www.harvardsucks.com"));
/* 192 */     StdOut.println("simpsons.com:      " + (String)localBTree.get("www.simpsons.com"));
/* 193 */     StdOut.println("apple.com:         " + (String)localBTree.get("www.apple.com"));
/* 194 */     StdOut.println("ebay.com:          " + (String)localBTree.get("www.ebay.com"));
/* 195 */     StdOut.println("dell.com:          " + (String)localBTree.get("www.dell.com"));
/* 196 */     StdOut.println();
/*     */ 
/* 198 */     StdOut.println("size:    " + localBTree.size());
/* 199 */     StdOut.println("height:  " + localBTree.height());
/* 200 */     StdOut.println(localBTree);
/* 201 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   private static class Entry
/*     */   {
/*     */     private Comparable key;
/*     */     private Object value;
/*     */     private BTree.Node next;
/*     */ 
/*     */     public Entry(Comparable paramComparable, Object paramObject, BTree.Node paramNode)
/*     */     {
/*  37 */       this.key = paramComparable;
/*  38 */       this.value = paramObject;
/*  39 */       this.next = paramNode;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class Node
/*     */   {
/*     */     private int m;
/*  26 */     private BTree.Entry[] children = new BTree.Entry[4];
/*     */ 
/*  27 */     private Node(int paramInt) { this.m = paramInt; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     BTree
 * JD-Core Version:    0.6.2
 */