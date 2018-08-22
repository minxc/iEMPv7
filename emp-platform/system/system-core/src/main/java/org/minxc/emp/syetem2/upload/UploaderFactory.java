package org.minxc.emp.syetem2.upload;

import java.util.Map;
import java.util.Map.Entry;

import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.PropertyUtil;

/**
 * <pre>
 * 描述：上传器工厂
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年6月6日
 * 版权:summer
 * </pre>
 */
public class UploaderFactory {
	private UploaderFactory() {

	}

	/**
	 * <pre>
	 * 获取上传器
	 * </pre>
	 * 
	 * @param type
	 * @return
	 */
	public static Uploader getUploader(String type) {
		Map<String, Uploader> map = AppUtil.getImplInstance(Uploader.class);
		for (Entry<String, Uploader> entry : map.entrySet()) {
			if (entry.getValue().type().equals(type)) {
				return entry.getValue();
			}
		}
		throw new BusinessException(String.format("找不到类型[%s]的上传器的实现类", type));
	}

	/**
	 * <pre>
	 * 返回默认的上传器
	 * </pre>
	 * 
	 * @return
	 */
	public static Uploader getDefault() {
		return getUploader(PropertyUtil.getProperty("uploader.default"));
	}
}
