/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class DigraphGenerator
/*     */ {
/*     */   public static Digraph simple(int paramInt1, int paramInt2)
/*     */   {
/*  48 */     if (paramInt2 > paramInt1 * (paramInt1 - 1)) throw new IllegalArgumentException("Too many edges");
/*  49 */     if (paramInt2 < 0) throw new IllegalArgumentException("Too few edges");
/*  50 */     Digraph localDigraph = new Digraph(paramInt1);
/*  51 */     SET localSET = new SET();
/*  52 */     while (localDigraph.E() < paramInt2) {
/*  53 */       int i = StdRandom.uniform(paramInt1);
/*  54 */       int j = StdRandom.uniform(paramInt1);
/*  55 */       DigraphGenerator.Edge localEdge = new DigraphGenerator.Edge(i, j, null);
/*  56 */       if ((i != j) && (!localSET.contains(localEdge))) {
/*  57 */         localSET.add(localEdge);
/*  58 */         localDigraph.addEdge(i, j);
/*     */       }
/*     */     }
/*  61 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph simple(int paramInt, double paramDouble)
/*     */   {
/*  76 */     if ((paramDouble < 0.0D) || (paramDouble > 1.0D))
/*  77 */       throw new IllegalArgumentException("Probability must be between 0 and 1");
/*  78 */     Digraph localDigraph = new Digraph(paramInt);
/*  79 */     for (int i = 0; i < paramInt; i++)
/*  80 */       for (int j = 0; j < paramInt; j++)
/*  81 */         if ((i != j) && 
/*  82 */           (StdRandom.bernoulli(paramDouble)))
/*  83 */           localDigraph.addEdge(i, j);
/*  84 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph complete(int paramInt)
/*     */   {
/*  93 */     return simple(paramInt, paramInt * (paramInt - 1));
/*     */   }
/*     */ 
/*     */   public static Digraph dag(int paramInt1, int paramInt2)
/*     */   {
/* 106 */     if (paramInt2 > paramInt1 * (paramInt1 - 1) / 2L) throw new IllegalArgumentException("Too many edges");
/* 107 */     if (paramInt2 < 0) throw new IllegalArgumentException("Too few edges");
/* 108 */     Digraph localDigraph = new Digraph(paramInt1);
/* 109 */     SET localSET = new SET();
/* 110 */     int[] arrayOfInt = new int[paramInt1];
/* 111 */     for (int i = 0; i < paramInt1; i++) arrayOfInt[i] = i;
/* 112 */     StdRandom.shuffle(arrayOfInt);
/* 113 */     while (localDigraph.E() < paramInt2) {
/* 114 */       i = StdRandom.uniform(paramInt1);
/* 115 */       int j = StdRandom.uniform(paramInt1);
/* 116 */       DigraphGenerator.Edge localEdge = new DigraphGenerator.Edge(i, j, null);
/* 117 */       if ((i < j) && (!localSET.contains(localEdge))) {
/* 118 */         localSET.add(localEdge);
/* 119 */         localDigraph.addEdge(arrayOfInt[i], arrayOfInt[j]);
/*     */       }
/*     */     }
/* 122 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph tournament(int paramInt)
/*     */   {
/* 134 */     return dag(paramInt, paramInt * (paramInt - 1) / 2);
/*     */   }
/*     */ 
/*     */   public static Digraph rootedInDAG(int paramInt1, int paramInt2)
/*     */   {
/* 147 */     if (paramInt2 > paramInt1 * (paramInt1 - 1) / 2L) throw new IllegalArgumentException("Too many edges");
/* 148 */     if (paramInt2 < paramInt1 - 1) throw new IllegalArgumentException("Too few edges");
/* 149 */     Digraph localDigraph = new Digraph(paramInt1);
/* 150 */     SET localSET = new SET();
/*     */ 
/* 153 */     int[] arrayOfInt = new int[paramInt1];
/* 154 */     for (int i = 0; i < paramInt1; i++) arrayOfInt[i] = i;
/* 155 */     StdRandom.shuffle(arrayOfInt);
/*     */     int j;
/*     */     DigraphGenerator.Edge localEdge;
/* 158 */     for (i = 0; i < paramInt1 - 1; i++) {
/* 159 */       j = StdRandom.uniform(i + 1, paramInt1);
/* 160 */       localEdge = new DigraphGenerator.Edge(i, j, null);
/* 161 */       localSET.add(localEdge);
/* 162 */       localDigraph.addEdge(arrayOfInt[i], arrayOfInt[j]);
/*     */     }
/*     */ 
/* 165 */     while (localDigraph.E() < paramInt2) {
/* 166 */       i = StdRandom.uniform(paramInt1);
/* 167 */       j = StdRandom.uniform(paramInt1);
/* 168 */       localEdge = new DigraphGenerator.Edge(i, j, null);
/* 169 */       if ((i < j) && (!localSET.contains(localEdge))) {
/* 170 */         localSET.add(localEdge);
/* 171 */         localDigraph.addEdge(arrayOfInt[i], arrayOfInt[j]);
/*     */       }
/*     */     }
/* 174 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph rootedOutDAG(int paramInt1, int paramInt2)
/*     */   {
/* 187 */     if (paramInt2 > paramInt1 * (paramInt1 - 1) / 2L) throw new IllegalArgumentException("Too many edges");
/* 188 */     if (paramInt2 < paramInt1 - 1) throw new IllegalArgumentException("Too few edges");
/* 189 */     Digraph localDigraph = new Digraph(paramInt1);
/* 190 */     SET localSET = new SET();
/*     */ 
/* 193 */     int[] arrayOfInt = new int[paramInt1];
/* 194 */     for (int i = 0; i < paramInt1; i++) arrayOfInt[i] = i;
/* 195 */     StdRandom.shuffle(arrayOfInt);
/*     */     int j;
/*     */     DigraphGenerator.Edge localEdge;
/* 198 */     for (i = 0; i < paramInt1 - 1; i++) {
/* 199 */       j = StdRandom.uniform(i + 1, paramInt1);
/* 200 */       localEdge = new DigraphGenerator.Edge(j, i, null);
/* 201 */       localSET.add(localEdge);
/* 202 */       localDigraph.addEdge(arrayOfInt[j], arrayOfInt[i]);
/*     */     }
/*     */ 
/* 205 */     while (localDigraph.E() < paramInt2) {
/* 206 */       i = StdRandom.uniform(paramInt1);
/* 207 */       j = StdRandom.uniform(paramInt1);
/* 208 */       localEdge = new DigraphGenerator.Edge(j, i, null);
/* 209 */       if ((i < j) && (!localSET.contains(localEdge))) {
/* 210 */         localSET.add(localEdge);
/* 211 */         localDigraph.addEdge(arrayOfInt[j], arrayOfInt[i]);
/*     */       }
/*     */     }
/* 214 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph rootedInTree(int paramInt)
/*     */   {
/* 226 */     return rootedInDAG(paramInt, paramInt - 1);
/*     */   }
/*     */ 
/*     */   public static Digraph rootedOutTree(int paramInt)
/*     */   {
/* 238 */     return rootedOutDAG(paramInt, paramInt - 1);
/*     */   }
/*     */ 
/*     */   public static Digraph path(int paramInt)
/*     */   {
/* 247 */     Digraph localDigraph = new Digraph(paramInt);
/* 248 */     int[] arrayOfInt = new int[paramInt];
/* 249 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 250 */     StdRandom.shuffle(arrayOfInt);
/* 251 */     for (i = 0; i < paramInt - 1; i++) {
/* 252 */       localDigraph.addEdge(arrayOfInt[i], arrayOfInt[(i + 1)]);
/*     */     }
/* 254 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph binaryTree(int paramInt)
/*     */   {
/* 263 */     Digraph localDigraph = new Digraph(paramInt);
/* 264 */     int[] arrayOfInt = new int[paramInt];
/* 265 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 266 */     StdRandom.shuffle(arrayOfInt);
/* 267 */     for (i = 1; i < paramInt; i++) {
/* 268 */       localDigraph.addEdge(arrayOfInt[i], arrayOfInt[((i - 1) / 2)]);
/*     */     }
/* 270 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph cycle(int paramInt)
/*     */   {
/* 279 */     Digraph localDigraph = new Digraph(paramInt);
/* 280 */     int[] arrayOfInt = new int[paramInt];
/* 281 */     for (int i = 0; i < paramInt; i++) arrayOfInt[i] = i;
/* 282 */     StdRandom.shuffle(arrayOfInt);
/* 283 */     for (i = 0; i < paramInt - 1; i++) {
/* 284 */       localDigraph.addEdge(arrayOfInt[i], arrayOfInt[(i + 1)]);
/*     */     }
/* 286 */     localDigraph.addEdge(arrayOfInt[(paramInt - 1)], arrayOfInt[0]);
/* 287 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static Digraph strong(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 308 */     if ((paramInt3 >= paramInt1) || (paramInt3 <= 0))
/* 309 */       throw new IllegalArgumentException("Number of components must be between 1 and V");
/* 310 */     if (paramInt2 <= 2 * (paramInt1 - paramInt3))
/* 311 */       throw new IllegalArgumentException("Number of edges must be at least 2(V-c)");
/* 312 */     if (paramInt2 > paramInt1 * (paramInt1 - 1) / 2L) {
/* 313 */       throw new IllegalArgumentException("Too many edges");
/*     */     }
/*     */ 
/* 316 */     Digraph localDigraph = new Digraph(paramInt1);
/*     */ 
/* 319 */     SET localSET = new SET();
/*     */ 
/* 321 */     int[] arrayOfInt = new int[paramInt1];
/* 322 */     for (int i = 0; i < paramInt1; i++)
/* 323 */       arrayOfInt[i] = StdRandom.uniform(paramInt3);
/*     */     int j;
/*     */     Object localObject;
/* 327 */     for (i = 0; i < paramInt3; i++)
/*     */     {
/* 329 */       j = 0;
/* 330 */       for (int k = 0; k < localDigraph.V(); k++) {
/* 331 */         if (arrayOfInt[k] == i) j++;
/*     */ 
/*     */       }
/*     */ 
/* 336 */       localObject = new int[j];
/* 337 */       int m = 0;
/* 338 */       for (int n = 0; n < paramInt1; n++) {
/* 339 */         if (arrayOfInt[n] == i) localObject[(m++)] = n;
/*     */       }
/* 341 */       StdRandom.shuffle((int[])localObject);
/*     */       int i1;
/*     */       DigraphGenerator.Edge localEdge;
/* 344 */       for (n = 0; n < j - 1; n++) {
/* 345 */         i1 = StdRandom.uniform(n + 1, j);
/* 346 */         localEdge = new DigraphGenerator.Edge(i1, n, null);
/* 347 */         localSET.add(localEdge);
/* 348 */         localDigraph.addEdge(localObject[i1], localObject[n]);
/*     */       }
/*     */ 
/* 352 */       for (n = 0; n < j - 1; n++) {
/* 353 */         i1 = StdRandom.uniform(n + 1, j);
/* 354 */         localEdge = new DigraphGenerator.Edge(n, i1, null);
/* 355 */         localSET.add(localEdge);
/* 356 */         localDigraph.addEdge(localObject[n], localObject[i1]);
/*     */       }
/*     */     }
/*     */ 
/* 360 */     while (localDigraph.E() < paramInt2) {
/* 361 */       i = StdRandom.uniform(paramInt1);
/* 362 */       j = StdRandom.uniform(paramInt1);
/* 363 */       localObject = new DigraphGenerator.Edge(i, j, null);
/* 364 */       if ((!localSET.contains((Comparable)localObject)) && (i != j) && (arrayOfInt[i] <= arrayOfInt[j])) {
/* 365 */         localSET.add((Comparable)localObject);
/* 366 */         localDigraph.addEdge(i, j);
/*     */       }
/*     */     }
/*     */ 
/* 370 */     return localDigraph;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 377 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 378 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 379 */     System.out.println("complete graph");
/* 380 */     System.out.println(complete(i));
/* 381 */     System.out.println();
/*     */ 
/* 383 */     System.out.println("simple");
/* 384 */     System.out.println(simple(i, j));
/* 385 */     System.out.println();
/*     */ 
/* 387 */     System.out.println("path");
/* 388 */     System.out.println(path(i));
/* 389 */     System.out.println();
/*     */ 
/* 391 */     System.out.println("cycle");
/* 392 */     System.out.println(cycle(i));
/* 393 */     System.out.println();
/*     */ 
/* 395 */     System.out.println("binary tree");
/* 396 */     System.out.println(binaryTree(i));
/* 397 */     System.out.println();
/*     */ 
/* 399 */     System.out.println("tournament");
/* 400 */     System.out.println(tournament(i));
/* 401 */     System.out.println();
/*     */ 
/* 403 */     System.out.println("DAG");
/* 404 */     System.out.println(dag(i, j));
/* 405 */     System.out.println();
/*     */ 
/* 407 */     System.out.println("rooted-in DAG");
/* 408 */     System.out.println(rootedInDAG(i, j));
/* 409 */     System.out.println();
/*     */ 
/* 411 */     System.out.println("rooted-out DAG");
/* 412 */     System.out.println(rootedOutDAG(i, j));
/* 413 */     System.out.println();
/*     */ 
/* 415 */     System.out.println("rooted-in tree");
/* 416 */     System.out.println(rootedInTree(i));
/* 417 */     System.out.println();
/*     */ 
/* 419 */     System.out.println("rooted-out DAG");
/* 420 */     System.out.println(rootedOutTree(i));
/* 421 */     System.out.println();
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
/*  27 */       this.v = paramInt1;
/*  28 */       this.w = paramInt2;
/*     */     }
/*     */     public int compareTo(Edge paramEdge) {
/*  31 */       if (this.v < paramEdge.v) return -1;
/*  32 */       if (this.v > paramEdge.v) return 1;
/*  33 */       if (this.w < paramEdge.w) return -1;
/*  34 */       if (this.w > paramEdge.w) return 1;
/*  35 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     DigraphGenerator
 * JD-Core Version:    0.6.2
 */