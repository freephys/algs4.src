/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class NFA
/*     */ {
/*     */   private Digraph G;
/*     */   private String regexp;
/*     */   private int M;
/*     */ 
/*     */   public NFA(String paramString)
/*     */   {
/*  38 */     this.regexp = paramString;
/*  39 */     this.M = paramString.length();
/*  40 */     Stack localStack = new Stack();
/*  41 */     this.G = new Digraph(this.M + 1);
/*  42 */     for (int i = 0; i < this.M; i++) {
/*  43 */       int j = i;
/*  44 */       if ((paramString.charAt(i) == '(') || (paramString.charAt(i) == '|')) {
/*  45 */         localStack.push(Integer.valueOf(i));
/*  46 */       } else if (paramString.charAt(i) == ')') {
/*  47 */         int k = ((Integer)localStack.pop()).intValue();
/*     */ 
/*  50 */         if (paramString.charAt(k) == '|') {
/*  51 */           j = ((Integer)localStack.pop()).intValue();
/*  52 */           this.G.addEdge(j, k + 1);
/*  53 */           this.G.addEdge(k, i);
/*     */         }
/*  55 */         else if (paramString.charAt(k) == '(') {
/*  56 */           j = k;
/*  57 */         } else if (!$assertionsDisabled) { throw new AssertionError(); }
/*     */ 
/*     */       }
/*     */ 
/*  61 */       if ((i < this.M - 1) && (paramString.charAt(i + 1) == '*')) {
/*  62 */         this.G.addEdge(j, i + 1);
/*  63 */         this.G.addEdge(i + 1, j);
/*     */       }
/*  65 */       if ((paramString.charAt(i) == '(') || (paramString.charAt(i) == '*') || (paramString.charAt(i) == ')'))
/*  66 */         this.G.addEdge(i, i + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean recognizes(String paramString)
/*     */   {
/*  72 */     DirectedDFS localDirectedDFS = new DirectedDFS(this.G, 0);
/*  73 */     Bag localBag1 = new Bag();
/*  74 */     for (int i = 0; i < this.G.V(); i++) {
/*  75 */       if (localDirectedDFS.marked(i)) localBag1.add(Integer.valueOf(i));
/*     */     }
/*     */ 
/*  78 */     for (i = 0; i < paramString.length(); i++) {
/*  79 */       Bag localBag2 = new Bag();
/*  80 */       for (Iterator localIterator2 = localBag1.iterator(); localIterator2.hasNext(); ) { int m = ((Integer)localIterator2.next()).intValue();
/*  81 */         if (m != this.M)
/*  82 */           if ((this.regexp.charAt(m) == paramString.charAt(i)) || (this.regexp.charAt(m) == '.'))
/*  83 */             localBag2.add(Integer.valueOf(m + 1));
/*     */       }
/*  85 */       localDirectedDFS = new DirectedDFS(this.G, localBag2);
/*  86 */       localBag1 = new Bag();
/*  87 */       for (int k = 0; k < this.G.V(); k++) {
/*  88 */         if (localDirectedDFS.marked(k)) localBag1.add(Integer.valueOf(k));
/*     */       }
/*     */ 
/*  91 */       if (localBag1.size() == 0) return false;
/*     */ 
/*     */     }
/*     */ 
/*  95 */     for (Iterator localIterator1 = localBag1.iterator(); localIterator1.hasNext(); ) { int j = ((Integer)localIterator1.next()).intValue();
/*  96 */       if (j == this.M) return true; 
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 102 */     String str1 = "(" + paramArrayOfString[0] + ")";
/* 103 */     String str2 = paramArrayOfString[1];
/* 104 */     if (str2.indexOf('|') >= 0) {
/* 105 */       throw new IllegalArgumentException("| character in text is not supported");
/*     */     }
/* 107 */     NFA localNFA = new NFA(str1);
/* 108 */     StdOut.println(localNFA.recognizes(str2));
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     NFA
 * JD-Core Version:    0.6.2
 */