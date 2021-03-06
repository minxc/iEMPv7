package org.minxc.emp.business.api.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.business.api.model.BusinessObject;

/**
 * <pre>
 * 描述：Bo对其他模块提供出的service
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月26日 下午5:46:56
 * 版权:summer
 * </pre>
 */
public interface BusinessObjectService {
	/**
	 * <pre>
	 * 根据key获取BusinessObject
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	BusinessObject getByKey(String key);

	/**
	 * <pre>
	 * 生成bo数据树
	 * 是pid那种形式
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	List<JSONObject> boTreeData(String key);
	
	BusinessObject getFilledByKey(String key);
}
