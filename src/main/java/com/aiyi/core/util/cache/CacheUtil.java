package com.aiyi.core.util.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 */
public class CacheUtil {

    private static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    private static final Map<String, CacheItem> cacheItemMap = new HashMap<>();
    static {
        new Thread(() -> {
            while (true){
                Set<Map.Entry<String, CacheItem>> entries = CacheUtil.cacheItemMap.entrySet();
                try {
                    for (Map.Entry<String, CacheItem> entry : entries) {
                        String key = entry.getKey();
                        CacheItem item = entry.getValue();
                        if (item.getExpTime() <= System.currentTimeMillis()) {
                            CacheUtil.cacheItemMap.remove(key);
                            logger.debug("Cache expire by [{}]", key);
                            if (item.getCollback() != null) {
                                try {
                                    item.getCollback().run();
                                } catch (Exception e) {
                                    logger.error("Cache expire collback function execute error!", e);
                                }
                            }
                        }
                    }
                }catch (ConcurrentModificationException e){
                    continue;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 将一个对象放入缓存
     * @param key
     *      缓存键
     * @param value
     *      缓存值
     * @param timeUnit
     *      缓存时间单位
     * @param time
     *      缓存时间
     */
    public static void put(Key key, Object value, TimeUnit timeUnit, long time){
        CacheItem item = new CacheItem();
        item.setValue(value);
        switch (timeUnit){
            case DAYS: time = System.currentTimeMillis() + time * 24 * 60 * 60 * 1000;break;
            case HOURS: time = System.currentTimeMillis() + time * 60 * 60 * 1000;break;
            case MINUTES: time = System.currentTimeMillis() + time * 60 * 1000;break;
            case SECONDS: time = System.currentTimeMillis() + time * 1000;break;
            default: time = System.currentTimeMillis() + time;break;
        }
        item.setExpTime(time);
        CacheUtil.cacheItemMap.put(key.toString(), item);
    }

    /**
     * 将一个对象放入缓存
     * @param key
     *      缓存键
     * @param value
     *      缓存值
     * @param timeUnit
     *      缓存时间单位
     * @param time
     *      缓存时间
     */
    public static void put(Key key, Object value, TimeUnit timeUnit, long time, Runnable runnable){
        CacheItem item = new CacheItem();
        item.setValue(value);
        item.setCollback(runnable);
        switch (timeUnit){
            case DAYS: time = System.currentTimeMillis() + time * 24 * 60 * 60 * 1000;break;
            case HOURS: time = System.currentTimeMillis() + time * 60 * 60 * 1000;break;
            case MINUTES: time = System.currentTimeMillis() + time * 60 * 1000;break;
            case SECONDS: time = System.currentTimeMillis() + time * 1000;break;
            default: time = System.currentTimeMillis() + time;break;
        }
        item.setExpTime(time);
        CacheUtil.cacheItemMap.put(key.toString(), item);
    }

    /**
     * 从缓存中取出对象
     * @param key
     *      缓存Key
     * @param clazz
     *      对象类型
     * @param <T>
     * @return
     */
    public static<T> T get(Key key, Class<T> clazz){
        CacheItem cacheItem = cacheItemMap.get(key.toString());
        if (null == cacheItem){
            return null;
        }
        return cacheItem.getValue(clazz);
    }

    /**
     * 重新设置对象过期时间
     * @param key
     *      缓存的Key
     * @param timeUnit
     *      时间单位
     * @param time
     *      时间值
     */
    public static void expire(Key key, TimeUnit timeUnit, long time){
        Object o = get(key, Object.class);
        if (null != o){
            put(key, o, timeUnit, time);
        }
    }

    /**
     * 使对象立刻过期
     * @param key
     *      对象Key
     */
    public static void expire(Key key){
        put(key, null, TimeUnit.MILLISECONDS, 0);
    }

}
