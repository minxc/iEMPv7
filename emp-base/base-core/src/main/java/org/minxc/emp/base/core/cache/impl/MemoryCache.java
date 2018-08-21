package org.minxc.emp.base.core.cache.impl;

import org.minxc.emp.base.core.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 
* 项目名称：base-core   
* 类名称：MemoryCache   
* 类描述：   自定义内存实现的Cache
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:19:13   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:19:13   
* 修改备注：   
* @version  1.0  
*
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
