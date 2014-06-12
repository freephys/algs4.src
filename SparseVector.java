/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class SparseVector
/*     */ {
/*     */   private int N;
/*     */   private ST<Integer, Double> st;
/*     */ 
/*     */   public SparseVector(int paramInt)
/*     */   {
/*  17 */     this.N = paramInt;
/*  18 */     this.st = new ST();
/*     */   }
/*     */ 
/*     */   public void put(int paramInt, double paramDouble)
/*     */   {
/*  23 */     if ((paramInt < 0) || (paramInt >= this.N)) throw new IndexOutOfBoundsException("Illegal index");
/*  24 */     if (paramDouble == 0.0D) this.st.delete(Integer.valueOf(paramInt)); else
/*  25 */       this.st.put(Integer.valueOf(paramInt), Double.valueOf(paramDouble));
/*     */   }
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/*  30 */     if ((paramInt < 0) || (paramInt >= this.N)) throw new IndexOutOfBoundsException("Illegal index");
/*  31 */     if (this.st.contains(Integer.valueOf(paramInt))) return ((Double)this.st.get(Integer.valueOf(paramInt))).doubleValue();
/*  32 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public int nnz()
/*     */   {
/*  37 */     return this.st.size();
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  42 */     return this.N;
/*     */   }
/*     */ 
/*     */   public double dot(SparseVector paramSparseVector)
/*     */   {
/*  47 */     if (this.N != paramSparseVector.N) throw new IllegalArgumentException("Vector lengths disagree");
/*  48 */     double d = 0.0D;
/*     */     Iterator localIterator;
/*     */     int i;
/*  51 */     if (this.st.size() <= paramSparseVector.st.size())
/*  52 */       for (localIterator = this.st.keys().iterator(); localIterator.hasNext(); ) { i = ((Integer)localIterator.next()).intValue();
/*  53 */         if (paramSparseVector.st.contains(Integer.valueOf(i))) d += get(i) * paramSparseVector.get(i);
/*     */       }
/*     */     else
/*  56 */       for (localIterator = paramSparseVector.st.keys().iterator(); localIterator.hasNext(); ) { i = ((Integer)localIterator.next()).intValue();
/*  57 */         if (this.st.contains(Integer.valueOf(i))) d += get(i) * paramSparseVector.get(i);
/*     */       }
/*  59 */     return d;
/*     */   }
/*     */ 
/*     */   public double dot(double[] paramArrayOfDouble)
/*     */   {
/*  65 */     double d = 0.0D;
/*  66 */     for (Iterator localIterator = this.st.keys().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  67 */       d += paramArrayOfDouble[i] * get(i); }
/*  68 */     return d;
/*     */   }
/*     */ 
/*     */   public double norm()
/*     */   {
/*  74 */     SparseVector localSparseVector = this;
/*  75 */     return Math.sqrt(localSparseVector.dot(localSparseVector));
/*     */   }
/*     */ 
/*     */   public SparseVector scale(double paramDouble)
/*     */   {
/*  80 */     SparseVector localSparseVector = new SparseVector(this.N);
/*     */     int i;
/*  81 */     for (Iterator localIterator = this.st.keys().iterator(); localIterator.hasNext(); localSparseVector.put(i, paramDouble * get(i))) i = ((Integer)localIterator.next()).intValue();
/*  82 */     return localSparseVector;
/*     */   }
/*     */ 
/*     */   public SparseVector plus(SparseVector paramSparseVector)
/*     */   {
/*  87 */     if (this.N != paramSparseVector.N) throw new IllegalArgumentException("Vector lengths disagree");
/*  88 */     SparseVector localSparseVector = new SparseVector(this.N);
/*     */     int i;
/*  89 */     for (Iterator localIterator = this.st.keys().iterator(); localIterator.hasNext(); localSparseVector.put(i, get(i))) i = ((Integer)localIterator.next()).intValue();
/*  90 */     for (localIterator = paramSparseVector.st.keys().iterator(); localIterator.hasNext(); localSparseVector.put(i, paramSparseVector.get(i) + localSparseVector.get(i))) i = ((Integer)localIterator.next()).intValue();
/*  91 */     return localSparseVector;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  96 */     String str = "";
/*  97 */     for (Iterator localIterator = this.st.keys().iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  98 */       str = str + "(" + i + ", " + this.st.get(Integer.valueOf(i)) + ") ";
/*     */     }
/* 100 */     return str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 106 */     SparseVector localSparseVector1 = new SparseVector(10);
/* 107 */     SparseVector localSparseVector2 = new SparseVector(10);
/* 108 */     localSparseVector1.put(3, 0.5D);
/* 109 */     localSparseVector1.put(9, 0.75D);
/* 110 */     localSparseVector1.put(6, 0.11D);
/* 111 */     localSparseVector1.put(6, 0.0D);
/* 112 */     localSparseVector2.put(3, 0.6D);
/* 113 */     localSparseVector2.put(4, 0.9D);
/* 114 */     StdOut.println("a = " + localSparseVector1);
/* 115 */     StdOut.println("b = " + localSparseVector2);
/* 116 */     StdOut.println("a dot b = " + localSparseVector1.dot(localSparseVector2));
/* 117 */     StdOut.println("a + b   = " + localSparseVector1.plus(localSparseVector2));
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SparseVector
 * JD-Core Version:    0.6.2
 */