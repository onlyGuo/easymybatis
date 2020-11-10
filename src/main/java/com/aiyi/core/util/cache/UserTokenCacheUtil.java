package com.aiyi.core.util.cache;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 郭胜凯
 * @Date: 2020/10/9 17:22
 * @Email 719348277@qq.com
 * @Description: 用户Token缓存工具类
 */
public class UserTokenCacheUtil {

    public interface CACHE{
        String LOGIN_KEY = "TOKEN_LOGIN_KEY";
        String USER_ID_TOKEN = "TOKEN_LOGIN_ID_TOKEN";
    }

    /**
     * 缓存时间(单位: 小时)
     */
    private static final int expire = 2;

    /**
     * 缓存一个token和用户的对应关系
     * @param token
     *      登录令牌
     * @param user
     *      用户对象
     */
    public static <T extends GenerUser> void putUserCache(String token, T user){
        // 缓存2小时
        CacheUtil.put(Key.as(CACHE.LOGIN_KEY, token), user, TimeUnit.HOURS, expire);
        CacheUtil.put(Key.as(CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), token, TimeUnit.HOURS, expire);
    }

    /**
     * 清除用户的登录缓存
     * @param userId
     *      对应的用户ID
     */
    public static void clear(int userId) {
        String token = CacheUtil.get(Key.as(CACHE.USER_ID_TOKEN, String.valueOf(userId)), String.class);
        if (null != token){
            CacheUtil.expire(Key.as(CACHE.USER_ID_TOKEN, String.valueOf(userId)));
            CacheUtil.expire(Key.as(CACHE.LOGIN_KEY, token));
        }
    }

    /**
     * 更新缓存中的用户(token对应的用户对象)
     * @param user
     *      新的用户对象
     */
    public static <T extends GenerUser> void updateCacheUser(T user) {
        String token = CacheUtil.get(Key.as(CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), String.class);
        if (null != token){
            CacheUtil.put(Key.as(CACHE.LOGIN_KEY, token), user, TimeUnit.HOURS, expire);
            CacheUtil.put(Key.as(CACHE.USER_ID_TOKEN, String.valueOf(user.getId())), token, TimeUnit.HOURS, expire);
        }
    }
}