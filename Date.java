/*     */ public class Date
/*     */   implements Comparable<Date>
/*     */ {
/*  20 */   private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*     */   private final int month;
/*     */   private final int day;
/*     */   private final int year;
/*     */ 
/*     */   public Date(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  34 */     if (!isValid(paramInt1, paramInt2, paramInt3)) throw new IllegalArgumentException("Invalid date");
/*  35 */     this.month = paramInt1;
/*  36 */     this.day = paramInt2;
/*  37 */     this.year = paramInt3;
/*     */   }
/*     */ 
/*     */   public Date(String paramString)
/*     */   {
/*  46 */     String[] arrayOfString = paramString.split("/");
/*  47 */     if (arrayOfString.length != 3) {
/*  48 */       throw new IllegalArgumentException("Invalid date");
/*     */     }
/*  50 */     this.month = Integer.parseInt(arrayOfString[0]);
/*  51 */     this.day = Integer.parseInt(arrayOfString[1]);
/*  52 */     this.year = Integer.parseInt(arrayOfString[2]);
/*  53 */     if (!isValid(this.month, this.day, this.year)) throw new IllegalArgumentException("Invalid date");
/*     */   }
/*     */ 
/*     */   public int month()
/*     */   {
/*  61 */     return this.month;
/*     */   }
/*     */ 
/*     */   public int day()
/*     */   {
/*  69 */     return this.day;
/*     */   }
/*     */ 
/*     */   public int year()
/*     */   {
/*  77 */     return this.year;
/*     */   }
/*     */ 
/*     */   private static boolean isValid(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  83 */     if ((paramInt1 < 1) || (paramInt1 > 12)) return false;
/*  84 */     if ((paramInt2 < 1) || (paramInt2 > DAYS[paramInt1])) return false;
/*  85 */     if ((paramInt1 == 2) && (paramInt2 == 29) && (!isLeapYear(paramInt3))) return false;
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   private static boolean isLeapYear(int paramInt)
/*     */   {
/*  94 */     if (paramInt % 400 == 0) return true;
/*  95 */     if (paramInt % 100 == 0) return false;
/*  96 */     return paramInt % 4 == 0;
/*     */   }
/*     */ 
/*     */   public Date next()
/*     */   {
/* 104 */     if (isValid(this.month, this.day + 1, this.year)) return new Date(this.month, this.day + 1, this.year);
/* 105 */     if (isValid(this.month + 1, 1, this.year)) return new Date(this.month + 1, 1, this.year);
/* 106 */     return new Date(1, 1, this.year + 1);
/*     */   }
/*     */ 
/*     */   public boolean isAfter(Date paramDate)
/*     */   {
/* 114 */     return compareTo(paramDate) > 0;
/*     */   }
/*     */ 
/*     */   public boolean isBefore(Date paramDate)
/*     */   {
/* 122 */     return compareTo(paramDate) < 0;
/*     */   }
/*     */ 
/*     */   public int compareTo(Date paramDate)
/*     */   {
/* 131 */     if (this.year < paramDate.year) return -1;
/* 132 */     if (this.year > paramDate.year) return 1;
/* 133 */     if (this.month < paramDate.month) return -1;
/* 134 */     if (this.month > paramDate.month) return 1;
/* 135 */     if (this.day < paramDate.day) return -1;
/* 136 */     if (this.day > paramDate.day) return 1;
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 145 */     return this.month + "/" + this.day + "/" + this.year;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 153 */     if (paramObject == this) return true;
/* 154 */     if (paramObject == null) return false;
/* 155 */     if (paramObject.getClass() != getClass()) return false;
/* 156 */     Date localDate = (Date)paramObject;
/* 157 */     return (this.month == localDate.month) && (this.day == localDate.day) && (this.year == localDate.year);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 165 */     int i = 17;
/* 166 */     i = 31 * i + this.month;
/* 167 */     i = 31 * i + this.day;
/* 168 */     i = 31 * i + this.year;
/* 169 */     return i;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 176 */     Date localDate1 = new Date(2, 25, 2004);
/* 177 */     StdOut.println(localDate1);
/* 178 */     for (int i = 0; i < 10; i++) {
/* 179 */       localDate1 = localDate1.next();
/* 180 */       StdOut.println(localDate1);
/*     */     }
/*     */ 
/* 183 */     StdOut.println(localDate1.isAfter(localDate1.next()));
/* 184 */     StdOut.println(localDate1.isAfter(localDate1));
/* 185 */     StdOut.println(localDate1.next().isAfter(localDate1));
/*     */ 
/* 188 */     Date localDate2 = new Date(10, 16, 1971);
/* 189 */     StdOut.println(localDate2);
/* 190 */     for (int j = 0; j < 10; j++) {
/* 191 */       localDate2 = localDate2.next();
/* 192 */       StdOut.println(localDate2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     Date
 * JD-Core Version:    0.6.2
 */