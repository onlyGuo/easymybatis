package com.aiyi.core.util.hibernate;

/*     */ 
/*     */ 
/*     */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*     */
/*     */

/*     */
/*     */ @SuppressWarnings({ "unchecked", "serial" })
public final class LockMode
/*     */   implements Serializable
/*     */ {
/*     */   private final int level;
/*     */   private final String name;
/*  45 */   @SuppressWarnings("rawtypes")
private static final Map INSTANCES = new HashMap();
/*     */ 
/*  80 */   public static final LockMode NONE = new LockMode(0, "NONE");
/*     */ 
/*  86 */   public static final LockMode READ = new LockMode(5, "READ");
/*     */ 
/*  91 */   public static final LockMode UPGRADE = new LockMode(10, "UPGRADE");
/*     */ 
/*  98 */   public static final LockMode UPGRADE_NOWAIT = new LockMode(10, "UPGRADE_NOWAIT");
/*     */ 
/* 105 */   public static final LockMode WRITE = new LockMode(10, "WRITE");
/*     */ 
/* 111 */   public static final LockMode FORCE = new LockMode(15, "FORCE");
/*     */ 
/*     */   static {
/* 114 */     INSTANCES.put(NONE.name, NONE);
/* 115 */     INSTANCES.put(READ.name, READ);
/* 116 */     INSTANCES.put(UPGRADE.name, UPGRADE);
/* 117 */     INSTANCES.put(UPGRADE_NOWAIT.name, UPGRADE_NOWAIT);
/* 118 */     INSTANCES.put(WRITE.name, WRITE);
/* 119 */     INSTANCES.put(FORCE.name, FORCE);
/*     */   }
/*     */ 
/*     */   private LockMode(int level, String name)
/*     */   {
/*  48 */     this.level = level;
/*  49 */     this.name = name;
/*     */   }
/*     */   public String toString() {
/*  52 */     return this.name;
/*     */   }
/*     */ 
/*     */   public boolean greaterThan(LockMode mode)
/*     */   {
/*  61 */     return this.level > mode.level;
/*     */   }
/*     */ 
/*     */   public boolean lessThan(LockMode mode)
/*     */   {
/*  70 */     return this.level < mode.level;
/*     */   }
/*     */ 
/*     */   private Object readResolve()
/*     */   {
/* 123 */     return parse(this.name);
/*     */   }
/*     */ 
/*     */   public static LockMode parse(String name) {
/* 127 */     return (LockMode)INSTANCES.get(name);
/*     */   }
/*     */ }

/* Location:           C:\Users\AnLuTong\Desktop\
 * Qualified Name:     org.hibernate.LockMode
 * JD-Core Version:    0.6.2
 */