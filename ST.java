/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class ST<Key extends Comparable<Key>, Value>
/*     */   implements Iterable<Key>
/*     */ {
/*     */   private TreeMap<Key, Value> st;
/*     */ 
/*     */   public ST()
/*     */   {
/*  54 */     this.st = new TreeMap();
/*     */   }
/*     */ 
/*     */   public Value get(Key paramKey)
/*     */   {
/*  66 */     if (paramKey == null) throw new NullPointerException("called get() with null key");
/*  67 */     return this.st.get(paramKey);
/*     */   }
/*     */ 
/*     */   public void put(Key paramKey, Value paramValue)
/*     */   {
/*  79 */     if (paramKey == null) throw new NullPointerException("called put() with null key");
/*  80 */     if (paramValue == null) this.st.remove(paramKey); else
/*  81 */       this.st.put(paramKey, paramValue);
/*     */   }
/*     */ 
/*     */   public void delete(Key paramKey)
/*     */   {
/*  91 */     if (paramKey == null) throw new NullPointerException("called delete() with null key");
/*  92 */     this.st.remove(paramKey);
/*     */   }
/*     */ 
/*     */   public boolean contains(Key paramKey)
/*     */   {
/* 103 */     if (paramKey == null) throw new NullPointerException("called contains() with null key");
/* 104 */     return this.st.containsKey(paramKey);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 112 */     return this.st.size();
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 120 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public Iterable<Key> keys()
/*     */   {
/* 130 */     return this.st.keySet();
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public Iterator<Key> iterator()
/*     */   {
/* 143 */     return this.st.keySet().iterator();
/*     */   }
/*     */ 
/*     */   public Key min()
/*     */   {
/* 152 */     if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
/* 153 */     return (Comparable)this.st.firstKey();
/*     */   }
/*     */ 
/*     */   public Key max()
/*     */   {
/* 162 */     if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
/* 163 */     return (Comparable)this.st.lastKey();
/*     */   }
/*     */ 
/*     */   public Key ceiling(Key paramKey)
/*     */   {
/* 174 */     if (paramKey == null) throw new NullPointerException("called ceiling() with null key");
/* 175 */     Comparable localComparable = (Comparable)this.st.ceilingKey(paramKey);
/* 176 */     if (localComparable == null) throw new NoSuchElementException("all keys are less than " + paramKey);
/* 177 */     return localComparable;
/*     */   }
/*     */ 
/*     */   public Key floor(Key paramKey)
/*     */   {
/* 188 */     if (paramKey == null) throw new NullPointerException("called floor() with null key");
/* 189 */     Comparable localComparable = (Comparable)this.st.floorKey(paramKey);
/* 190 */     if (localComparable == null) throw new NoSuchElementException("all keys are greater than " + paramKey);
/* 191 */     return localComparable;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 198 */     ST localST = new ST();
/*     */     String str;
/* 199 */     for (int i = 0; !StdIn.isEmpty(); i++) {
/* 200 */       str = StdIn.readString();
/* 201 */       localST.put(str, Integer.valueOf(i));
/*     */     }
/* 203 */     for (Iterator localIterator = localST.keys().iterator(); localIterator.hasNext(); ) { str = (String)localIterator.next();
/* 204 */       StdOut.println(str + " " + localST.get(str));
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/leo/Dropbox/source_code/algorithm_interview/Algorithms_4th_princeton_java/algs4.jar
 * Qualified Name:     ST
 * JD-Core Version:    0.6.2
 */