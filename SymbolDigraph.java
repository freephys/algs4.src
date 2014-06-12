/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SymbolDigraph
/*     */ {
/*     */   private ST<String, Integer> st;
/*     */   private String[] keys;
/*     */   private Digraph G;
/*     */ 
/*     */   public SymbolDigraph(String paramString1, String paramString2)
/*     */   {
/*  54 */     this.st = new ST();
/*     */ 
/*  58 */     In localIn = new In(paramString1);
/*  59 */     while (localIn.hasNextLine()) {
/*  60 */       localObject = localIn.readLine().split(paramString2);
/*  61 */       for (int i = 0; i < localObject.length; i++) {
/*  62 */         if (!this.st.contains(localObject[i])) {
/*  63 */           this.st.put(localObject[i], Integer.valueOf(this.st.size()));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  68 */     this.keys = new String[this.st.size()];
/*  69 */     for (Object localObject = this.st.keys().iterator(); ((Iterator)localObject).hasNext(); ) { String str = (String)((Iterator)localObject).next();
/*  70 */       this.keys[((Integer)this.st.get(str)).intValue()] = str;
/*     */     }
/*     */ 
/*  75 */     this.G = new Digraph(this.st.size());
/*  76 */     localIn = new In(paramString1);
/*  77 */     while (localIn.hasNextLine()) {
/*  78 */       localObject = localIn.readLine().split(paramString2);
/*  79 */       int j = ((Integer)this.st.get(localObject[0])).intValue();
/*  80 */       for (int k = 1; k < localObject.length; k++) {
/*  81 */         int m = ((Integer)this.st.get(localObject[k])).intValue();
/*  82 */         this.G.addEdge(j, m);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean contains(String paramString)
/*     */   {
/*  93 */     return this.st.contains(paramString);
/*     */   }
/*     */ 
/*     */   public int index(String paramString)
/*     */   {
/* 102 */     return ((Integer)this.st.get(paramString)).intValue();
/*     */   }
/*     */ 
/*     */   public String name(int paramInt)
/*     */   {
/* 111 */     return this.keys[paramInt];
/*     */   }
/*     */ 
/*     */   public Digraph G()
/*     */   {
/* 120 */     return this.G;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 128 */     String str1 = paramArrayOfString[0];
/* 129 */     String str2 = paramArrayOfString[1];
/* 130 */     SymbolDigraph localSymbolDigraph = new SymbolDigraph(str1, str2);
/* 131 */     Digraph localDigraph = localSymbolDigraph.G();
/*     */     Iterator localIterator;
/* 132 */     while (!StdIn.isEmpty()) {
/* 133 */       String str3 = StdIn.readLine();
/* 134 */       for (localIterator = localDigraph.adj(localSymbolDigraph.index(str3)).iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 135 */         StdOut.println("   " + localSymbolDigraph.name(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SymbolDigraph
 * JD-Core Version:    0.6.2
 */