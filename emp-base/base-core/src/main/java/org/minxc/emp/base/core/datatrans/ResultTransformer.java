package org.minxc.emp.base.core.datatrans;

import org.minxc.emp.base.core.util.BeanUtils;

import java.util.*;
import java.util.Map.Entry;

/**
 * <pre>
 *  描述：数据转换。
 *  使用方法:
 *
 *  TypeConvertConfig config=new TypeConvertConfig();
 *
 *  config.regeisterConvert(Date.class, new ITypeConvert() {
 *    @Override
 * 	public Object processValue(Object obj) {
 * 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
 * 			return format.format(obj);
 * 	}
 * });
 *  List list=;
 *  ResultTransform.transform(list,config);
 *
 *  2：
 *  ResultTransform.transform(pageList, new ITypeConvert() {
 *
 * 	@Override
 * 	public Object processValue(Object obj) {
 * 			Map<String, Object> map = (Map<String, Object>) obj;
 * 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
 * 			map.put("birthday", sdf.format(map.get("birthday")));
 * 			map.put("新增列", "new");//插入新数据..这里可以对map随意操作，任何增删查改
 * 			return map;
 * 		}
 * 	});
 *
 *  作者：lyj
 *  邮箱:liyj@jee-soft.cn
 *  日期:2014-7-22-上午9:39:34
 * </pre>
 */
public class ResultTransformer {
    /**
     * 从转化器注册器中匹配转换器
     *
     * @param list
     * @param config void
     */
    public static void transform(List list, TypeConverterConfig config) {

        Map<Class, TypeConverter> convertMap = config.getConverts();
        if (BeanUtils.isEmpty(list))
            return;

        Object obj = list.get(0);
        if (Map.class.isAssignableFrom(obj.getClass())) {
            handlerMap(list, convertMap);
        } else {
            handRowObject(list, convertMap);
        }
    }

    /**
     * 明确告诉使用哪个转换器
     *
     * @param list    ：数据
     * @param convert ：转换器
     *                void
     * @throws
     * @since 1.0.0
     */
    public static void transform(List list, TypeConverter convert) {
        if (convert == null)
            return;

        List rtnList = new ArrayList();
        for (Object tmp : list) {
            Object rtnObj = convert.processValue(tmp);
            rtnList.add(rtnObj);
        }
        list.clear();
        list.addAll(rtnList);
    }

    private static void handRowObject(List list, Map<Class, TypeConverter> convertMap) {
        Object obj = list.get(0);
        TypeConverter convert = getConvert(convertMap, obj);
        if (convert == null)
            return;

        List rtnList = new ArrayList();
        for (Object tmp : list) {
            Object rtnObj = convert.processValue(tmp);
            rtnList.add(rtnObj);
        }
        list.clear();
        list.addAll(rtnList);
    }

    private static void handlerMap(List list, Map<Class, TypeConverter> map) {
        Map<String, Object> rowMap = (Map<String, Object>) list.get(0);
        Map<String, TypeConverter> convertMap = getByRow(rowMap, map);

        List rtnList = new ArrayList();

        for (Object obj : list) {
            Map<String, Object> rtnRowMap = new HashMap<String, Object>();

            Map<String, Object> dataRow = (Map<String, Object>) obj;
            Set<Entry<String, Object>> set = dataRow.entrySet();
            for (Iterator<Entry<String, Object>> it = set.iterator(); it.hasNext(); ) {
                Entry<String, Object> ent = it.next();
                String key = ent.getKey();
                Object tmp = ent.getValue();
                TypeConverter convert = convertMap.get(key);
                if (convert == null) {
                    rtnRowMap.put(key, tmp);
                } else {
                    Object rtnObj = convert.processValue(tmp);
                    rtnRowMap.put(key, rtnObj);
                }
            }
            rtnList.add(rtnRowMap);
        }
        list.clear();
        list.addAll(rtnList);

    }

    private static Map<String, TypeConverter> getByRow(Map<String, Object> rowMap, Map<Class, TypeConverter> convertMap) {
        Map<String, TypeConverter> fieldConverMap = new HashMap<String, TypeConverter>();

        Set<Entry<String, Object>> set = rowMap.entrySet();
        for (Iterator<Entry<String, Object>> it = set.iterator(); it.hasNext(); ) {
            Entry<String, Object> ent = it.next();
            String key = ent.getKey();
            Object obj = ent.getValue();
            TypeConverter convert = getConvert(convertMap, obj);
            if (convert == null)
                continue;
            fieldConverMap.put(key, convert);
        }
        return fieldConverMap;
    }

    private static TypeConverter getConvert(Map<Class, TypeConverter> map, Object obj) {
        Class cls = obj.getClass();
        TypeConverter convert = map.get(cls);
        if (convert != null)
            return convert;

        Set<Entry<Class, TypeConverter>> set = map.entrySet();
        for (Iterator<Entry<Class, TypeConverter>> it = set.iterator(); it.hasNext(); ) {
            Entry<Class, TypeConverter> ent = it.next();
            if (ent.getKey().isAssignableFrom(cls)) {
                return ent.getValue();
            }
        }
        return null;
    }
}
