package org.minxc.emp.system.redis;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.minxc.emp.base.core.cache.Cache;
import org.minxc.emp.system.api.redis.RedisService;

/**
 * Redis缓存实现
 */
public class RedisCache<T extends Object> implements Cache<T> {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Resource
    private RedisService redisService;

    @Override
    public synchronized void add(String key, T obj) {
        logger.info("key=" + key);
        redisService.set(key, obj);
    }

    @Override
    public synchronized void add(String key, T obj, int timeout) {
        logger.info("key=" + key);
        redisService.set(key, obj, timeout);
    }

    @Override
    public synchronized void delByKey(String key) {
        logger.info("key=" + key);
        redisService.del(key);
    }

    @Override
    public void clearAll() {
        redisService.flushDB();
    }

    @Override
    public synchronized T getByKey(String key) {
        Object obj = redisService.getObject(key);
        return (T) obj;
    }

    @Override
    public boolean containKey(String key) {
        return redisService.exists(key);
    }
}
