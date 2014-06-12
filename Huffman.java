/*     */ public class Huffman
/*     */ {
/*     */   private static final int R = 256;
/*     */ 
/*     */   public static void compress()
/*     */   {
/*  55 */     String str1 = BinaryStdIn.readString();
/*  56 */     char[] arrayOfChar = str1.toCharArray();
/*     */ 
/*  59 */     int[] arrayOfInt = new int[256];
/*  60 */     for (int i = 0; i < arrayOfChar.length; i++) {
/*  61 */       arrayOfInt[arrayOfChar[i]] += 1;
/*     */     }
/*     */ 
/*  64 */     Huffman.Node localNode = buildTrie(arrayOfInt);
/*     */ 
/*  67 */     String[] arrayOfString = new String[256];
/*  68 */     buildCode(arrayOfString, localNode, "");
/*     */ 
/*  71 */     writeTrie(localNode);
/*     */ 
/*  74 */     BinaryStdOut.write(arrayOfChar.length);
/*     */ 
/*  77 */     for (int j = 0; j < arrayOfChar.length; j++) {
/*  78 */       String str2 = arrayOfString[arrayOfChar[j]];
/*  79 */       for (int k = 0; k < str2.length(); k++) {
/*  80 */         if (str2.charAt(k) == '0') {
/*  81 */           BinaryStdOut.write(false);
/*     */         }
/*  83 */         else if (str2.charAt(k) == '1')
/*  84 */           BinaryStdOut.write(true);
/*     */         else {
/*  86 */           throw new IllegalStateException("Illegal state");
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  91 */     BinaryStdOut.close();
/*     */   }
/*     */ 
/*     */   private static Huffman.Node buildTrie(int[] paramArrayOfInt)
/*     */   {
/*  98 */     MinPQ localMinPQ = new MinPQ();
/*     */     int j;
/*  99 */     for (int i = 0; i < 256; j = (char)(i + 1)) {
/* 100 */       if (paramArrayOfInt[i] > 0) {
/* 101 */         localMinPQ.insert(new Huffman.Node(i, paramArrayOfInt[i], null, null));
/*     */       }
/*     */     }
/* 104 */     while (localMinPQ.size() > 1) {
/* 105 */       Huffman.Node localNode1 = (Huffman.Node)localMinPQ.delMin();
/* 106 */       Huffman.Node localNode2 = (Huffman.Node)localMinPQ.delMin();
/* 107 */       Huffman.Node localNode3 = new Huffman.Node('\000', localNode1.freq + localNode2.freq, localNode1, localNode2);
/* 108 */       localMinPQ.insert(localNode3);
/*     */     }
/* 110 */     return (Huffman.Node)localMinPQ.delMin();
/*     */   }
/*     */ 
/*     */   private static void writeTrie(Huffman.Node paramNode)
/*     */   {
/* 116 */     if (paramNode.isLeaf()) {
/* 117 */       BinaryStdOut.write(true);
/* 118 */       BinaryStdOut.write(paramNode.ch, 8);
/* 119 */       return;
/*     */     }
/* 121 */     BinaryStdOut.write(false);
/* 122 */     writeTrie(paramNode.left);
/* 123 */     writeTrie(paramNode.right);
/*     */   }
/*     */ 
/*     */   private static void buildCode(String[] paramArrayOfString, Huffman.Node paramNode, String paramString)
/*     */   {
/* 128 */     if (!paramNode.isLeaf()) {
/* 129 */       buildCode(paramArrayOfString, paramNode.left, paramString + '0');
/* 130 */       buildCode(paramArrayOfString, paramNode.right, paramString + '1');
/*     */     }
/*     */     else {
/* 133 */       paramArrayOfString[paramNode.ch] = paramString;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void expand()
/*     */   {
/* 142 */     Huffman.Node localNode1 = readTrie();
/*     */ 
/* 145 */     int i = BinaryStdIn.readInt();
/*     */ 
/* 148 */     for (int j = 0; j < i; j++) {
/* 149 */       Huffman.Node localNode2 = localNode1;
/* 150 */       while (!localNode2.isLeaf()) {
/* 151 */         boolean bool = BinaryStdIn.readBoolean();
/* 152 */         if (bool) localNode2 = localNode2.right; else
/* 153 */           localNode2 = localNode2.left;
/*     */       }
/* 155 */       BinaryStdOut.write(localNode2.ch, 8);
/*     */     }
/* 157 */     BinaryStdOut.close();
/*     */   }
/*     */ 
/*     */   private static Huffman.Node readTrie()
/*     */   {
/* 162 */     boolean bool = BinaryStdIn.readBoolean();
/* 163 */     if (bool) {
/* 164 */       return new Huffman.Node(BinaryStdIn.readChar(), -1, null, null);
/*     */     }
/*     */ 
/* 167 */     return new Huffman.Node('\000', -1, readTrie(), readTrie());
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 173 */     if (paramArrayOfString[0].equals("-")) compress();
/* 174 */     else if (paramArrayOfString[0].equals("+")) expand(); else
/* 175 */       throw new IllegalArgumentException("Illegal command line argument");
/*     */   }
/*     */ 
/*     */   private static class Node
/*     */     implements Comparable<Node>
/*     */   {
/*     */     private final char ch;
/*     */     private final int freq;
/*     */     private final Node left;
/*     */     private final Node right;
/*     */ 
/*     */     Node(char paramChar, int paramInt, Node paramNode1, Node paramNode2)
/*     */     {
/*  33 */       this.ch = paramChar;
/*  34 */       this.freq = paramInt;
/*  35 */       this.left = paramNode1;
/*  36 */       this.right = paramNode2;
/*     */     }
/*     */ 
/*     */     private boolean isLeaf()
/*     */     {
/*  41 */       assert (((this.left == null) && (this.right == null)) || ((this.left != null) && (this.right != null)));
/*  42 */       return (this.left == null) && (this.right == null);
/*     */     }
/*     */ 
/*     */     public int compareTo(Node paramNode)
/*     */     {
/*  47 */       return this.freq - paramNode.freq;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Huffman
 * JD-Core Version:    0.6.2
 */