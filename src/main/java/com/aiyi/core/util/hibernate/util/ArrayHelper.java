package com.aiyi.core.util.hibernate.util;


import com.aiyi.core.util.hibernate.LockMode;
import com.aiyi.core.util.hibernate.type.Type;

import java.lang.reflect.Array;
import java.util.*;


public final class ArrayHelper {

    public static final boolean[] TRUE = {true};
    public static final boolean[] FALSE = new boolean[1];

    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    @SuppressWarnings("rawtypes")
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    private static int SEED = 23;
    private static int PRIME_NUMER = 37;

    public static int indexOf(Object[] array, Object object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) return i;
        }
        return -1;
    }

    public static String[] toStringArray(Object[] objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = objects[i].toString();
        }
        return result;
    }

    public static String[] fillArray(String value, int length) {
        String[] result = new String[length];
        Arrays.fill(result, value);
        return result;
    }

    public static int[] fillArray(int value, int length) {
        int[] result = new int[length];
        Arrays.fill(result, value);
        return result;
    }

    public static LockMode[] fillArray(LockMode lockMode, int length) {
        LockMode[] array = new LockMode[length];
        Arrays.fill(array, lockMode);
        return array;
    }

    public static String[] toStringArray(Collection<?> coll) {
        return (String[]) coll.toArray(EMPTY_STRING_ARRAY);
    }

    public static String[][] to2DStringArray(Collection<?> coll) {
        return (String[][]) coll.toArray(new String[coll.size()][]);
    }

    public static int[][] to2DIntArray(Collection<?> coll) {
        return (int[][]) coll.toArray(new int[coll.size()][]);
    }

    public static Type[] toTypeArray(Collection<?> coll) {
        return (Type[]) coll.toArray(EMPTY_TYPE_ARRAY);
    }

    public static int[] toIntArray(Collection<?> coll) {
        Iterator<?> iter = coll.iterator();
        int[] arr = new int[coll.size()];
        int i = 0;
        while (iter.hasNext()) {
            arr[(i++)] = ((Integer) iter.next()).intValue();
        }
        return arr;
    }

    public static boolean[] toBooleanArray(Collection<?> coll) {
        Iterator<?> iter = coll.iterator();
        boolean[] arr = new boolean[coll.size()];
        int i = 0;
        while (iter.hasNext()) {
            arr[(i++)] = ((Boolean) iter.next()).booleanValue();
        }
        return arr;
    }

    public static Object[] typecast(Object[] array, Object[] to) {
        return Arrays.asList(array).toArray(to);
    }

    public static List<Object> toList(Object array) {
        if ((array instanceof Object[])) return Arrays.asList((Object[]) array);
        int size = Array.getLength(array);
        ArrayList<Object> list = new ArrayList<Object>(size);
        for (int i = 0; i < size; i++) {
            list.add(Array.get(array, i));
        }
        return list;
    }

    public static String[] slice(String[] strings, int begin, int length) {
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = strings[(begin + i)];
        }
        return result;
    }

    public static Object[] slice(Object[] objects, int begin, int length) {
        Object[] result = new Object[length];
        for (int i = 0; i < length; i++) {
            result[i] = objects[(begin + i)];
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<?> toList(Iterator<?> iter) {
        List list = new ArrayList<>();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }

    public static String[] join(String[] x, String[] y) {
        String[] result = new String[x.length + y.length];
        for (int i = 0; i < x.length; i++) result[i] = x[i];
        for (int i = 0; i < y.length; i++) result[(i + x.length)] = y[i];
        return result;
    }

    public static String[] join(String[] x, String[] y, boolean[] use) {
        String[] result = new String[x.length + countTrue(use)];
        for (int i = 0; i < x.length; i++) result[i] = x[i];
        int k = x.length;
        for (int i = 0; i < y.length; i++) {
            if (use[i]) {
                result[(k++)] = y[i];
            }
        }
        return result;
    }

    public static int[] join(int[] x, int[] y) {
        int[] result = new int[x.length + y.length];
        for (int i = 0; i < x.length; i++) result[i] = x[i];
        for (int i = 0; i < y.length; i++) result[(i + x.length)] = y[i];
        return result;
    }

    public static String toString(Object[] array) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static boolean isAllNegative(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllTrue(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return false;
            }
        }
        return true;
    }

    public static int countTrue(boolean[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                result++;
            }
        }
        return result;
    }

    public static boolean isAllFalse(boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                return false;
            }
        }
        return true;
    }

    public static void addAll(Collection<Object> collection, Object[] array) {
        for (int i = 0; i < array.length; i++) {
            collection.add(array[i]);
        }

    }

    public static int[] getBatchSizes(int maxBatchSize) {
        int batchSize = maxBatchSize;
        int n = 1;
        while (batchSize > 1) {
            batchSize = getNextBatchSize(batchSize);
            n++;
        }
        int[] result = new int[n];
        batchSize = maxBatchSize;
        for (int i = 0; i < n; i++) {
            result[i] = batchSize;
            batchSize = getNextBatchSize(batchSize);
        }
        return result;
    }

    private static int getNextBatchSize(int batchSize) {
        if (batchSize <= 10) {
            return batchSize - 1;
        }
        if (batchSize / 2 < 10) {
            return 10;
        }

        return batchSize / 2;
    }

    public static int hash(Object[] array) {
        int length = array.length;
        int seed = SEED;
        for (int index = 0; index < length; index++) {
            seed = hash(seed, array[index] == null ? 0 : array[index].hashCode());
        }
        return seed;
    }

    public static int hash(char[] array) {
        int length = array.length;
        int seed = SEED;
        for (int index = 0; index < length; index++) {
            seed = hash(seed, array[index]);
        }
        return seed;
    }

    public static int hash(byte[] bytes) {
        int length = bytes.length;
        int seed = SEED;
        for (int index = 0; index < length; index++) {
            seed = hash(seed, bytes[index]);
        }
        return seed;
    }

    private static int hash(int seed, int i) {
        return PRIME_NUMER * seed + i;
    }

    public static boolean isEquals(Object[] o1, Object[] o2) {
        if (o1 == o2) return true;
        if ((o1 == null) || (o2 == null)) {
            return false;
        }
        int length = o1.length;
        if (length != o2.length) {
            return false;
        }
        for (int index = 0; index < length; index++) {
            if (!o1[index].equals(o2[index])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEquals(char[] o1, char[] o2) {
        if (o1 == o2) return true;
        if ((o1 == null) || (o2 == null)) {
            return false;
        }
        int length = o1.length;
        if (length != o2.length) {
            return false;
        }
        for (int index = 0; index < length; index++) {
            if (o1[index] != o2[index]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEquals(byte[] b1, byte[] b2) {
        if (b1 == b2) return true;
        if ((b1 == null) || (b2 == null)) return false;
        int length = b1.length;
        if (length != b2.length) return false;
        for (int index = 0; index < length; index++) {
            if (b1[index] != b2[index]) return false;
        }
        return true;
    }
}