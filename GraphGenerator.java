/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class GraphGenerator
/*     */ {
/*     */   public static Graph simple(int paramInt1, int paramInt2)
/*     */   {
/*  56 */     if (paramInt2 > paramInt1 * (paramInt1 - 1) / 2L) throw new IllegalArgumentException("Too many edges");
/*  57 */     if (paramInt2 < 0) throw new IllegalArgumentException("Too few edges");
/*  58 */     Graph localGraph = new Graph(paramInt1);
/*  59 */     SET localSET = new SET();
/*  60 */     while (localGraph.E() < paramInt2) {
/*  61 */       int i = StdRandom.uniform(paramInt1);
/*  62 */       int j = StdRandom.uniform(paramInt1);
/*  63 */       GraphGenerator.Edge localEdge = new GraphGenerator.Edge(i, j, null);
/*  64 */       if ((i != j) && (!localSET.contains(localEdge))) {
/*  65 */         localSET.add(localEdge);
/*  66 */         localGraph.addEdge(i, j);
/*     */       }
/*     */     }
/*  69 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph simple(int paramInt, double paramDouble)
/*     */   {
/*  83 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D))
/*  84 */       throw new IllegalArgumentException("Probability must be between 0 and 1");
/*  85 */     Graph localGraph = new Graph(paramInt);
/*  86 */     for (int i = 0; i < paramInt; i++)
/*  87 */       for (int j = i + 1; j < paramInt; j++)
/*  88 */         if (StdRandom.bernoulli(paramDouble))
/*  89 */           localGraph.addEdge(i, j);
/*  90 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph complete(int paramInt)
/*     */   {
/*  99 */     return simple(paramInt, 1.0D);
/*     */   }
/*     */ 
/*     */   public static Graph completeBipartite(int paramInt1, int paramInt2)
/*     */   {
/* 110 */     return bipartite(paramInt1, paramInt2, paramInt1 * paramInt2);
/*     */   }
/*     */ 
/*     */   public static Graph bipartite(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 124 */     if (paramInt3 > paramInt1 * paramInt2) throw new IllegalArgumentException("Too many edges");
/* 125 */     if (paramInt3 < 0) throw new IllegalArgumentException("Too few edges");
/* 126 */     Graph localGraph = new Graph(paramInt1 + paramInt2);
/*     */ 
/* 128 */     int[] arrayOfInt = new int[paramInt1 + paramInt2];
/* 129 */     for (int i = 0; i < paramInt1 + paramInt2; i++) arrayOfInt[i] = i;
/* 130 */     StdRandom.shuffle(arrayOfInt);
/*     */ 
/* 132 */     SET localSET = new SET();
/* 133 */     while (localGraph.E() < paramInt3) {
/* 134 */       int j = StdRandom.uniform(paramInt1);
/* 135 */       int k = paramInt1 + StdRandom.uniform(paramInt2);
/* 136 */       GraphGenerator.Edge localEdge = new GraphGenerator.Edge(arrayOfInt[j], arrayOfInt[k], null);
/* 137 */       if (!localSET.contains(localEdge)) {
/* 138 */         localSET.add(localEdge);
/* 139 */         localGraph.addEdge(arrayOfInt[j], arrayOfInt[k]);
/*     */       }
/*     */     }
/* 142 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph bipartite(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 156 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D))
/* 157 */       throw new IllegalArgumentException("Probability must be between 0 and 1");
/* 158 */     int[] arrayOfInt = new int[paramInt1 + paramInt2];
/* 159 */     for (int i = 0; i < paramInt1 + paramInt2; i++) arrayOfInt[i] = i;
/* 160 */     StdRandom.shuffle(arrayOfInt);
/* 161 */     Graph localGraph = new Graph(paramInt1 + paramInt2);
/* 162 */     for (int j = 0; j < paramInt1; j++)
/* 163 */       for (int k = 0; k < paramInt2; k++)
/* 164 */         if (StdRandom.bernoulli(paramDouble))
/* 165 */           localGraph.addEdge(arrayOfInt[j], arrayOfInt[(paramInt1 + k)]);
/* 166 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph path(int paramInt)
/*     */   {
/* 175 */     Graph localGraph = new Graph(paramInt);
/* 176 */     int[] arrayOfInt = new int[paramInt];
/* 177 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 178 */     StdRandom.shuffle(arrayOfInt);
/* 179 */     for (i = 0; i < paramInt - 1; i++) {
/* 180 */       localGraph.addEdge(arrayOfInt[i], arrayOfInt[(i + 1)]);
/*     */     }
/* 182 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph binaryTree(int paramInt)
/*     */   {
/* 191 */     Graph localGraph = new Graph(paramInt);
/* 192 */     int[] arrayOfInt = new int[paramInt];
/* 193 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 194 */     StdRandom.shuffle(arrayOfInt);
/* 195 */     for (i = 1; i < paramInt; i++) {
/* 196 */       localGraph.addEdge(arrayOfInt[i], arrayOfInt[((i - 1) / 2)]);
/*     */     }
/* 198 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph cycle(int paramInt)
/*     */   {
/* 207 */     Graph localGraph = new Graph(paramInt);
/* 208 */     int[] arrayOfInt = new int[paramInt];
/* 209 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 210 */     StdRandom.shuffle(arrayOfInt);
/* 211 */     for (i = 0; i < paramInt - 1; i++) {
/* 212 */       localGraph.addEdge(arrayOfInt[i], arrayOfInt[(i + 1)]);
/*     */     }
/* 214 */     localGraph.addEdge(arrayOfInt[(paramInt - 1)], arrayOfInt[0]);
/* 215 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph wheel(int paramInt)
/*     */   {
/* 225 */     if (paramInt <= 1) throw new IllegalArgumentException("Number of vertices must be at least 2");
/* 226 */     Graph localGraph = new Graph(paramInt);
/* 227 */     int[] arrayOfInt = new int[paramInt];
/* 228 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 229 */     StdRandom.shuffle(arrayOfInt);
/*     */ 
/* 232 */     for (i = 1; i < paramInt - 1; i++) {
/* 233 */       localGraph.addEdge(arrayOfInt[i], arrayOfInt[(i + 1)]);
/*     */     }
/* 235 */     localGraph.addEdge(arrayOfInt[(paramInt - 1)], arrayOfInt[1]);
/*     */ 
/* 238 */     for (i = 1; i < paramInt; i++) {
/* 239 */       localGraph.addEdge(arrayOfInt[0], arrayOfInt[i]);
/*     */     }
/*     */ 
/* 242 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph star(int paramInt)
/*     */   {
/* 252 */     if (paramInt <= 0) throw new IllegalArgumentException("Number of vertices must be at least 1");
/* 253 */     Graph localGraph = new Graph(paramInt);
/* 254 */     int[] arrayOfInt = new int[paramInt];
/* 255 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 256 */     StdRandom.shuffle(arrayOfInt);
/*     */ 
/* 259 */     for (i = 1; i < paramInt; i++) {
/* 260 */       localGraph.addEdge(arrayOfInt[0], arrayOfInt[i]);
/*     */     }
/*     */ 
/* 263 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph regular(int paramInt1, int paramInt2)
/*     */   {
/* 274 */     if (paramInt1 * paramInt2 % 2 != 0) throw new IllegalArgumentException("Number of vertices * k must be even");
/* 275 */     Graph localGraph = new Graph(paramInt1);
/*     */ 
/* 278 */     int[] arrayOfInt = new int[paramInt1 * paramInt2];
/* 279 */     for (int i = 0; i < paramInt1; i++) {
/* 280 */       for (int j = 0; j < paramInt2; j++) {
/* 281 */         arrayOfInt[(i + paramInt1 * j)] = i;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 286 */     StdRandom.shuffle(arrayOfInt);
/* 287 */     for (i = 0; i < paramInt1 * paramInt2 / 2; i++) {
/* 288 */       localGraph.addEdge(arrayOfInt[(2 * i)], arrayOfInt[(2 * i + 1)]);
/*     */     }
/* 290 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static Graph tree(int paramInt)
/*     */   {
/* 302 */     Graph localGraph = new Graph(paramInt);
/*     */ 
/* 305 */     if (paramInt == 1) return localGraph;
/*     */ 
/* 311 */     int[] arrayOfInt1 = new int[paramInt - 2];
/* 312 */     for (int i = 0; i < paramInt - 2; i++) {
/* 313 */       arrayOfInt1[i] = StdRandom.uniform(paramInt);
/*     */     }
/*     */ 
/* 316 */     int[] arrayOfInt2 = new int[paramInt];
/* 317 */     for (int j = 0; j < paramInt; j++)
/* 318 */       arrayOfInt2[j] = 1;
/* 319 */     for (j = 0; j < paramInt - 2; j++) {
/* 320 */       arrayOfInt2[arrayOfInt1[j]] += 1;
/*     */     }
/*     */ 
/* 323 */     MinPQ localMinPQ = new MinPQ();
/* 324 */     for (int k = 0; k < paramInt; k++) {
/* 325 */       if (arrayOfInt2[k] == 1) localMinPQ.insert(Integer.valueOf(k));
/*     */     }
/*     */ 
/* 328 */     for (k = 0; k < paramInt - 2; k++) {
/* 329 */       int m = ((Integer)localMinPQ.delMin()).intValue();
/* 330 */       localGraph.addEdge(m, arrayOfInt1[k]);
/* 331 */       arrayOfInt2[m] -= 1;
/* 332 */       arrayOfInt2[arrayOfInt1[k]] -= 1;
/* 333 */       if (arrayOfInt2[arrayOfInt1[k]] == 1) localMinPQ.insert(Integer.valueOf(arrayOfInt1[k]));
/*     */     }
/* 335 */     localGraph.addEdge(((Integer)localMinPQ.delMin()).intValue(), ((Integer)localMinPQ.delMin()).intValue());
/* 336 */     return localGraph;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 343 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 344 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 345 */     int k = i / 2;
/* 346 */     int m = i - k;
/*     */ 
/* 348 */     System.out.println("complete graph");
/* 349 */     System.out.println(complete(i));
/* 350 */     System.out.println();
/*     */ 
/* 352 */     System.out.println("simple");
/* 353 */     System.out.println(simple(i, j));
/* 354 */     System.out.println();
/*     */ 
/* 356 */     System.out.println("Erdos-Renyi");
/* 357 */     double d1 = j / (i * (i - 1) / 2);
/* 358 */     System.out.println(simple(i, d1));
/* 359 */     System.out.println();
/*     */ 
/* 361 */     System.out.println("complete bipartite");
/* 362 */     System.out.println(completeBipartite(k, m));
/* 363 */     System.out.println();
/*     */ 
/* 365 */     System.out.println("bipartite");
/* 366 */     System.out.println(bipartite(k, m, j));
/* 367 */     System.out.println();
/*     */ 
/* 369 */     System.out.println("Erdos Renyi bipartite");
/* 370 */     double d2 = j / (k * m);
/* 371 */     System.out.println(bipartite(k, m, d2));
/* 372 */     System.out.println();
/*     */ 
/* 374 */     System.out.println("path");
/* 375 */     System.out.println(path(i));
/* 376 */     System.out.println();
/*     */ 
/* 378 */     System.out.println("cycle");
/* 379 */     System.out.println(cycle(i));
/* 380 */     System.out.println();
/*     */ 
/* 382 */     System.out.println("binary tree");
/* 383 */     System.out.println(binaryTree(i));
/* 384 */     System.out.println();
/*     */ 
/* 386 */     System.out.println("tree");
/* 387 */     System.out.println(tree(i));
/* 388 */     System.out.println();
/*     */ 
/* 390 */     System.out.println("4-regular");
/* 391 */     System.out.println(regular(i, 4));
/* 392 */     System.out.println();
/*     */ 
/* 394 */     System.out.println("star");
/* 395 */     System.out.println(star(i));
/* 396 */     System.out.println();
/*     */ 
/* 398 */     System.out.println("wheel");
/* 399 */     System.out.println(wheel(i));
/* 400 */     System.out.println();
/*     */   }
/*     */ 
/*     */   private static final class Edge
/*     */     implements Comparable<Edge>
/*     */   {
/*     */     private int v;
/*     */     private int w;
/*     */ 
/*     */     private Edge(int paramInt1, int paramInt2)
/*     */     {
/*  29 */       if (paramInt1 < paramInt2) {
/*  30 */         this.v = paramInt1;
/*  31 */         this.w = paramInt2;
/*     */       }
/*     */       else {
/*  34 */         this.v = paramInt2;
/*  35 */         this.w = paramInt1;
/*     */       }
/*     */     }
/*     */ 
/*  39 */     public int compareTo(Edge paramEdge) { if (this.v < paramEdge.v) return -1;
/*  40 */       if (this.v > paramEdge.v) return 1;
/*  41 */       if (this.w < paramEdge.w) return -1;
/*  42 */       if (this.w > paramEdge.w) return 1;
/*  43 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     GraphGenerator
 * JD-Core Version:    0.6.2
 */