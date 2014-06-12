/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class SET<Key extends Comparable<Key>>
/*     */   implements Iterable<Key>
/*     */ {
/*     */   private TreeSet<Key> set;
/*     */ 
/*     */   public SET()
/*     */   {
/*  52 */     this.set = new TreeSet();
/*     */   }
/*     */ 
/*     */   public void add(Key paramKey)
/*     */   {
/*  61 */     if (paramKey == null) throw new NullPointerException("called add() with a null key");
/*  62 */     this.set.add(paramKey);
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/*  74 */     if (paramKey == null) throw new NullPointerException("called contains() with a null key");
/*  75 */     return this.set.contains(paramKey);
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/*  84 */     if (paramKey == null) throw new NullPointerException("called delete() with a null key");
/*  85 */     this.set.remove(paramKey);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  93 */     return this.set.size();
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 101 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public Iterator<Key> iterator()
/*     */   {
/* 111 */     return this.set.iterator();
/*     */   }
/*     */ 
/*     */   public Key max()
/*     */   {
/* 120 */     if (isEmpty()) throw new NoSuchElementException("called max() with empty set");
/* 121 */     return (Comparable)this.set.last();
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 130 */     if (isEmpty()) throw new NoSuchElementException("called min() with empty set");
/* 131 */     return (Comparable)this.set.first();
/*     */   }
/*     */ 
/*     */   public Key ceiling(Key paramKey)
/*     */   {
/* 143 */     if (paramKey == null) throw new NullPointerException("called ceiling() with a null key");
/* 144 */     Comparable localComparable = (Comparable)this.set.ceiling(paramKey);
/* 145 */     if (localComparable == null) throw new NoSuchElementException("all keys are less than " + paramKey);
/* 146 */     return localComparable;
/*     */   }
/*     */ 
/*     */   public Key floor(Key paramKey)
/*     */   {
/* 157 */     if (paramKey == null) throw new NullPointerException("called floor() with a null key");
/* 158 */     Comparable localComparable = (Comparable)this.set.floor(paramKey);
/* 159 */     if (localComparable == null) throw new NoSuchElementException("all keys are greater than " + paramKey);
/* 160 */     return localComparable;
/*     */   }
/*     */ 
/*     */   public SET<Key> union(SET<Key> paramSET)
/*     */   {
/* 170 */     if (paramSET == null) throw new NullPointerException("called union() with a null argument");
/* 171 */     SET localSET = new SET();
/*     */     Comparable localComparable;
/* 172 */     for (Iterator localIterator = iterator(); localIterator.hasNext(); localSET.add(localComparable)) localComparable = (Comparable)localIterator.next();
/* 173 */     for (localIterator = paramSET.iterator(); localIterator.hasNext(); localSET.add(localComparable)) localComparable = (Comparable)localIterator.next();
/* 174 */     return localSET;
/*     */   }
/*     */ 
/*     */   public SET<Key> intersects(SET<Key> paramSET)
/*     */   {
/* 184 */     if (paramSET == null) throw new NullPointerException("called intersects() with a null argument");
/* 185 */     SET localSET = new SET();
/*     */     Iterator localIterator;
/*     */     Comparable localComparable;
/* 186 */     if (size() < paramSET.size()) {
/* 187 */       for (localIterator = iterator(); localIterator.hasNext(); ) { localComparable = (Comparable)localIterator.next();
/* 188 */         if (paramSET.contains(localComparable)) localSET.add(localComparable);
/*     */       }
/*     */     }
/*     */     else {
/* 192 */       for (localIterator = paramSET.iterator(); localIterator.hasNext(); ) { localComparable = (Comparable)localIterator.next();
/* 193 */         if (contains(localComparable)) localSET.add(localComparable);
/*     */       }
/*     */     }
/* 196 */     return localSET;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 209 */     if (paramObject == this) return true;
/* 210 */     if (paramObject == null) return false;
/* 211 */     if (paramObject.getClass() != getClass()) return false;
/* 212 */     SET localSET = (SET)paramObject;
/* 213 */     if (size() != localSET.size()) return false; try
/*     */     {
/* 215 */       for (Comparable localComparable : this)
/* 216 */         if (!localSET.contains(localComparable)) return false; 
/*     */     }
/*     */     catch (ClassCastException localClassCastException)
/*     */     {
/* 219 */       return false;
/*     */     }
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 230 */     StringBuilder localStringBuilder = new StringBuilder();
/* 231 */     for (Comparable localComparable : this)
/* 232 */       localStringBuilder.append(localComparable + " ");
/* 233 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 240 */     SET localSET = new SET();
/*     */ 
/* 244 */     localSET.add("www.cs.princeton.edu");
/* 245 */     localSET.add("www.cs.princeton.edu");
/* 246 */     localSET.add("www.princeton.edu");
/* 247 */     localSET.add("www.math.princeton.edu");
/* 248 */     localSET.add("www.yale.edu");
/* 249 */     localSET.add("www.amazon.com");
/* 250 */     localSET.add("www.simpsons.com");
/* 251 */     localSET.add("www.stanford.edu");
/* 252 */     localSET.add("www.google.com");
/* 253 */     localSET.add("www.ibm.com");
/* 254 */     localSET.add("www.apple.com");
/* 255 */     localSET.add("www.slashdot.com");
/* 256 */     localSET.add("www.whitehouse.gov");
/* 257 */     localSET.add("www.espn.com");
/* 258 */     localSET.add("www.snopes.com");
/* 259 */     localSET.add("www.movies.com");
/* 260 */     localSET.add("www.cnn.com");
/* 261 */     localSET.add("www.iitb.ac.in");
/*     */ 
/* 264 */     StdOut.println(localSET.contains("www.cs.princeton.edu"));
/* 265 */     StdOut.println(!localSET.contains("www.harvardsucks.com"));
/* 266 */     StdOut.println(localSET.contains("www.simpsons.com"));
/* 267 */     StdOut.println();
/*     */ 
/* 269 */     StdOut.println("ceiling(www.simpsonr.com) = " + (String)localSET.ceiling("www.simpsonr.com"));
/* 270 */     StdOut.println("ceiling(www.simpsons.com) = " + (String)localSET.ceiling("www.simpsons.com"));
/* 271 */     StdOut.println("ceiling(www.simpsont.com) = " + (String)localSET.ceiling("www.simpsont.com"));
/* 272 */     StdOut.println("floor(www.simpsonr.com)   = " + (String)localSET.floor("www.simpsonr.com"));
/* 273 */     StdOut.println("floor(www.simpsons.com)   = " + (String)localSET.floor("www.simpsons.com"));
/* 274 */     StdOut.println("floor(www.simpsont.com)   = " + (String)localSET.floor("www.simpsont.com"));
/* 275 */     StdOut.println();
/*     */ 
/* 279 */     for (String str : localSET)
/* 280 */       StdOut.println(str);
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     SET
 * JD-Core Version:    0.6.2
 */