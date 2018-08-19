package org.minxc.emp.base.core.datatrans;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 描述：转换器类型注册容器。
 * </pre>
 */
public class TypeConverterConfig {

    private Map<Class, TypeConverter> map = new HashMap<Class, TypeConverter>();

    public void regeisterConvert(Class cls, TypeConverter convert) {
        map.put(cls, convert);
    }

    public Map<Class, TypeConverter> getConverts() {
        return map;
    }

}
