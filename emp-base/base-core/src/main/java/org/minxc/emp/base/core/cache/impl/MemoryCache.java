package org.minxc.emp.base.core.cache.impl;

import org.minxc.emp.base.core.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 内存的cache实现。
 * <pre>
 * </pre>
 */

@Component
public class MemoryCache<T> implements Cache<T> {

    private Map<String, T> map = new HashMap<String, T>();

    public void add(String key, T obj) {
        map.put(key, obj);

    }

    public void delByKey(String key) {
        map.remove(key);
    }

    public void clearAll() {
        map.clear();
    }

    public T getByKey(String key) {
        return map.get(key);
    }

    public boolean containKey(String key) {

        return map.containsKey(key);
    }

    public void add(String key, T obj, int timeout) {
        // TODO Auto-generated method stub

    }

}
