package com.aiyi.core.util.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project : Xunhengda
 * @Prackage Name : com.aiyi.core.util.thread
 * @Description :
 * @Author : 郭胜凯
 * @Creation Date : 2018/5/6 下午12:10
 * @ModificationHistory Who When What ---------- ------------- -----------------------------------
 * 郭胜凯 2018/5/6
 */
public class ThreadUtil {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private ThreadUtil() {
    }

    private static void set(String key, Object value) {
        if (null == threadLocal) {
            threadLocal = new ThreadLocal<>();
        }
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<>();
        }
        map.put(key, value);
        threadLocal.set(map);
    }

    private static <T> T get(String key) {
        if (null == threadLocal) {
            threadLocal = new ThreadLocal<>();
        }
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            return null;
        }
        return (T) threadLocal.get().get(key);
    }

    /**
     * @Description : 获取请求ID
     * @Creation Date : 2018/5/6 下午12:25
     * @Author : 郭胜凯
     */
    public static String getRequestId() {
        return get("requestId");
    }

    /**
     * @param requestId 请求ID
     * @Description : 设置请求ID
     * @Creation Date : 2018/5/6 下午12:25
     * @Author : 郭胜凯
     */
    public static void setRequestId(String requestId) {
        set("requestId", requestId);
    }

    /**
     * @Description : 获取通信令牌
     * @Creation Date : 2018/5/6 下午12:26
     * @Author : 郭胜凯
     */
    public static String getToken() {
        return get("token");
    }

    /**
     * @param token 通信令牌
     * @Description : 设置通信令牌
     * @Creation Date : 2018/5/6 下午12:26
     * @Author : 郭胜凯
     */
    public static void setToken(String token) {
        set("token", token);
    }

    /**
     * @Description : 获得当前线程中的登录用户ID
     * @Creation Date : 2018/5/6 下午12:56
     * @Author : 郭胜凯
     */
    public static Long getUserId() {
        return get("userId");
    }

    /**
     * @param userId 用户ID
     * @Description : 给当前线程设置用户ID
     * @Creation Date : 2018/5/6 下午12:56
     * @Author : 郭胜凯
     */
    public static void setUserId(Long userId) {
        set("userId", userId);
    }

    /**
     * @Description : 获得当前线程中登录的用户名
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static String getUserName() {
        if (null == threadLocal) {
            threadLocal = new ThreadLocal<>();
        }
        return (String) threadLocal.get().get("userName");
    }

    /**
     * @param userName 用户名
     * @Description : 给当前线程设置登录的用户名
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static void setUserName(String userName) {
        set("userName", userName);
    }

    /**
     * @Description : 获得当前线程设置登录的客户端类型
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static String getClientType() {
        return get("clientType");
    }

    /**
     * @param clientType 设备类型
     * @Description : 给当前线程设置登录的客户端类型
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static void setClientType(String clientType) {
        set("clientType", clientType);
    }

    /**
     * @Description : 获得当前线程设置登录的客户端设备
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static String getClientDriver() {
        return get("clientDriver");
    }

    /**
     * @param clientDriver 客户端设备
     * @Description : 给当前线程设置登录的设备类型
     * @Creation Date : 2018/5/6 下午12:58
     * @Author : 郭胜凯
     */
    public static void setClientDriver(String clientDriver) {
        set("clientDriver", clientDriver);
    }

    /**
     * @param key 键
     * @Description : 获得当前线程中缓存的某个键的值
     * @Creation Date : 2018/5/6 下午12:59
     * @Author : 郭胜凯
     */
    public static <T> T getCacheData(String key) {
        Map<String, Object> data = get("cacheData");
        if (null == data) {
            return null;
        }
        return (T) data.get(key);
    }

    /**
     * @Description : 清空当前线程中缓存的内容
     * @Creation Date : 2018/5/6 下午1:00
     * @Author : 郭胜凯
     */
    public static void clearCacheData() {
        Map<String, Object> data = get("cacheData");
        if (null != data) {
            data.clear();
        }
    }

    /**
     * @param key   键
     * @param value 值
     * @Description : 给当前线程添加一个Key, Value 的缓存数据, 若传入重复的key, value将会被新的值覆盖.
     * @Creation Date : 2018/5/6 下午1:00
     * @Author : 郭胜凯
     */
    public static void setCacheData(String key, Object value) {
        Map<String, Object> data = get("cacheData");
        if (null != data) {
            data.put(key, value);
        }
    }

    /**
     * @param user 用户实体类
     * @Description : 设置当前线程中登录用户的实体类
     * @Creation Date : 2018/5/6 下午1:52
     * @Author : 郭胜凯
     */
    public static <T> void setUserEntity(T user) {
        setCacheData("_IN_USER_ENEITY", user);
    }

    /**
     * @Description : 获得当前线程中登录用户的实体类(每次请求该实体类都会更新,
     * 因此不必考虑缓存问题, 但该实体类可以允许被外部修改, 因此不绝对安全.)
     * @Creation Date : 2018/5/6 下午1:54
     * @Author : 郭胜凯
     */
    public static <T> T getUserEntity() {
        return getCacheData("_IN_USER_ENEITY");
    }

    /**
     * @param key 键
     * @Description : 删除向前线程中缓存的某个Key(以及他的Value)
     * @Creation Date : 2018/5/6 下午1:01
     * @Author : 郭胜凯
     */
    public static <T> T removeCacheData(String key) {
        Map<String, Object> data = get("cacheData");
        if (null == data) {
            return null;
        }
        return (T) data.remove(key);
    }


}
