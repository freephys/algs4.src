/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SymbolGraph
/*     */ {
/*     */   private ST<String, Integer> st;
/*     */   private String[] keys;
/*     */   private Graph G;
/*     */ 
/*     */   public SymbolGraph(String paramString1, String paramString2)
/*     */   {
/*  80 */     this.st = new ST();
/*     */ 
/*  84 */     In localIn = new In(paramString1);
/*     */ 
/*  86 */     while (!localIn.isEmpty()) {
/*  87 */       localObject = localIn.readLine().split(paramString2);
/*  88 */       for (int i = 0; i < localObject.length; i++) {
/*  89 */         if (!this.st.contains(localObject[i]))
/*  90 */           this.st.put(localObject[i], Integer.valueOf(this.st.size()));
/*     */       }
/*     */     }
/*  93 */     StdOut.println("Done reading " + paramString1);
/*     */ 
/*  96 */     this.keys = new String[this.st.size()];
/*  97 */     for (Object localObject = this.st.keys().iterator(); ((Iterator)localObject).hasNext(); ) { String str = (String)((Iterator)localObject).next();
/*  98 */       this.keys[((Integer)this.st.get(str)).intValue()] = str;
/*     */     }
/*     */ 
/* 103 */     this.G = new Graph(this.st.size());
/* 104 */     localIn = new In(paramString1);
/* 105 */     while (localIn.hasNextLine()) {
/* 106 */       localObject = localIn.readLine().split(paramString2);
/* 107 */       int j = ((Integer)this.st.get(localObject[0])).intValue();
/* 108 */       for (int k = 1; k < localObject.length; k++) {
/* 109 */         int m = ((Integer)this.st.get(localObject[k])).intValue();
/* 110 */         this.G.addEdge(j, m);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean contains(String paramString)
/*     */   {
/* 121 */     return this.st.contains(paramString);
/*     */   }
/*     */ 
/*     */   public int index(String paramString)
/*     */   {
/* 130 */     return ((Integer)this.st.get(paramString)).intValue();
/*     */   }
/*     */ 
/*     */   public String name(int paramInt)
/*     */   {
/* 139 */     return this.keys[paramInt];
/*     */   }
/*     */ 
/*     */   public Graph G()
/*     */   {
/* 148 */     return this.G;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 156 */     String str1 = paramArrayOfString[0];
/* 157 */     String str2 = paramArrayOfString[1];
/* 158 */     SymbolGraph localSymbolGraph = new SymbolGraph(str1, str2);
/* 159 */     Graph localGraph = localSymbolGraph.G();
/* 160 */     while (StdIn.hasNextLine()) {
/* 161 */       String str3 = StdIn.readLine();
/*     */       Iterator localIterator;
/* 162 */       if (localSymbolGraph.contains(str3)) {
/* 163 */         int i = localSymbolGraph.index(str3);
/* 164 */         for (localIterator = localGraph.adj(i).iterator(); localIterator.hasNext(); ) { int j = ((Integer)localIterator.next()).intValue();
/* 165 */           StdOut.println("   " + localSymbolGraph.name(j)); }
/*     */       }
/*     */       else
/*     */       {
/* 169 */         StdOut.println("input not contain '" + str3 + "'");
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SymbolGraph
 * JD-Core Version:    0.6.2
 */