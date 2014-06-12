/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class Transaction
/*     */   implements Comparable<Transaction>
/*     */ {
/*     */   private final String who;
/*     */   private final Date when;
/*     */   private final double amount;
/*     */ 
/*     */   public Transaction(String paramString, Date paramDate, double paramDouble)
/*     */   {
/*  40 */     if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble)))
/*  41 */       throw new IllegalArgumentException("Amount cannot be NaN or infinite");
/*  42 */     this.who = paramString;
/*  43 */     this.when = paramDate;
/*  44 */     if (paramDouble == 0.0D) this.amount = 0.0D; else
/*  45 */       this.amount = paramDouble;
/*     */   }
/*     */ 
/*     */   public Transaction(String paramString)
/*     */   {
/*  56 */     String[] arrayOfString = paramString.split("\\s+");
/*  57 */     this.who = arrayOfString[0];
/*  58 */     this.when = new Date(arrayOfString[1]);
/*  59 */     double d = Double.parseDouble(arrayOfString[2]);
/*  60 */     if (d == 0.0D) this.amount = 0.0D; else
/*  61 */       this.amount = d;
/*  62 */     if ((Double.isNaN(this.amount)) || (Double.isInfinite(this.amount)))
/*  63 */       throw new IllegalArgumentException("Amount cannot be NaN or infinite");
/*     */   }
/*     */ 
/*     */   public String who()
/*     */   {
/*  71 */     return this.who;
/*     */   }
/*     */ 
/*     */   public Date when()
/*     */   {
/*  79 */     return this.when;
/*     */   }
/*     */ 
/*     */   public double amount()
/*     */   {
/*  87 */     return this.amount;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  95 */     return String.format("%-10s %10s %8.2f", new Object[] { this.who, this.when, Double.valueOf(this.amount) });
/*     */   }
/*     */ 
/*     */   public int compareTo(Transaction paramTransaction)
/*     */   {
/* 105 */     if (this.amount < paramTransaction.amount) return -1;
/* 106 */     if (this.amount > paramTransaction.amount) return 1;
/* 107 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 116 */     if (paramObject == this) return true;
/* 117 */     if (paramObject == null) return false;
/* 118 */     if (paramObject.getClass() != getClass()) return false;
/* 119 */     Transaction localTransaction = (Transaction)paramObject;
/* 120 */     return (this.amount == localTransaction.amount) && (this.who.equals(localTransaction.who)) && (this.when.equals(localTransaction.when));
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 130 */     int i = 17;
/* 131 */     i = 31 * i + this.who.hashCode();
/* 132 */     i = 31 * i + this.when.hashCode();
/* 133 */     i = 31 * i + Double.valueOf(this.amount).hashCode();
/* 134 */     return i;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 171 */     Transaction[] arrayOfTransaction = new Transaction[4];
/* 172 */     arrayOfTransaction[0] = new Transaction("Turing   6/17/1990  644.08");
/* 173 */     arrayOfTransaction[1] = new Transaction("Tarjan   3/26/2002 4121.85");
/* 174 */     arrayOfTransaction[2] = new Transaction("Knuth    6/14/1999  288.34");
/* 175 */     arrayOfTransaction[3] = new Transaction("Dijkstra 8/22/2007 2678.40");
/*     */ 
/* 177 */     StdOut.println("Unsorted");
/* 178 */     for (int i = 0; i < arrayOfTransaction.length; i++)
/* 179 */       StdOut.println(arrayOfTransaction[i]);
/* 180 */     StdOut.println();
/*     */ 
/* 182 */     StdOut.println("Sort by date");
/* 183 */     Arrays.sort(arrayOfTransaction, new Transaction.WhenOrder());
/* 184 */     for (i = 0; i < arrayOfTransaction.length; i++)
/* 185 */       StdOut.println(arrayOfTransaction[i]);
/* 186 */     StdOut.println();
/*     */ 
/* 188 */     StdOut.println("Sort by customer");
/* 189 */     Arrays.sort(arrayOfTransaction, new Transaction.WhoOrder());
/* 190 */     for (i = 0; i < arrayOfTransaction.length; i++)
/* 191 */       StdOut.println(arrayOfTransaction[i]);
/* 192 */     StdOut.println();
/*     */ 
/* 194 */     StdOut.println("Sort by amount");
/* 195 */     Arrays.sort(arrayOfTransaction, new Transaction.HowMuchOrder());
/* 196 */     for (i = 0; i < arrayOfTransaction.length; i++)
/* 197 */       StdOut.println(arrayOfTransaction[i]);
/* 198 */     StdOut.println();
/*     */   }
/*     */ 
/*     */   public static class HowMuchOrder
/*     */     implements Comparator<Transaction>
/*     */   {
/*     */     public int compare(Transaction paramTransaction1, Transaction paramTransaction2)
/*     */     {
/* 160 */       if (paramTransaction1.amount < paramTransaction2.amount) return -1;
/* 161 */       if (paramTransaction1.amount > paramTransaction2.amount) return 1;
/* 162 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class WhenOrder
/*     */     implements Comparator<Transaction>
/*     */   {
/*     */     public int compare(Transaction paramTransaction1, Transaction paramTransaction2)
/*     */     {
/* 151 */       return paramTransaction1.when.compareTo(paramTransaction2.when);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class WhoOrder
/*     */     implements Comparator<Transaction>
/*     */   {
/*     */     public int compare(Transaction paramTransaction1, Transaction paramTransaction2)
/*     */     {
/* 142 */       return paramTransaction1.who.compareTo(paramTransaction2.who);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Transaction
 * JD-Core Version:    0.6.2
 */