package org.minxc.emp.system.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.ConstantUtil;
import org.minxc.emp.base.core.util.EnumUtil;
import org.minxc.emp.base.core.util.PinyinUtil;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;

/**
 * 系统工具类
 *
 * @author min.xianchang
 */
@RestController
@RequestMapping("/sys/tools/")
public class SystemToolsController extends GenericController {
	/**
	 * <pre>
	 * 根据一个枚举类的路径获取这个枚举的json形式，供前端使用
	 * 注意！！如果枚举在类中间，那么路径如下：org.minxc.emp.base.db.model.Column$TYPE
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getEnum")
	public Object getEnum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String enumClassPath = RequestUtil.getString(request, "path");
		boolean listMode = RequestUtil.getBoolean(request, "listMode");// 列表模式
		if (listMode) {
			return EnumUtil.toJSONArray(enumClassPath);
		}
		return EnumUtil.toJSON(enumClassPath);
	}

	/**
	 * <pre>
	 * 根据path(类路径)获取key（字段名）的常量
	 * ps:如果key为空，会把path类的全部static final的静态变量获取出来
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getConstant")
	public Object getConstant(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classPath = RequestUtil.getString(request, "path");
		String key = RequestUtil.getString(request, "key");
		if (StringUtil.isEmpty(key)) {
			return ConstantUtil.get(classPath);
		}
		return ConstantUtil.get(classPath, key);
	}

	@RequestMapping("getInterFaceImpls")
	@CatchError
	public Object getInterFaceImpls(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classPath = RequestUtil.getString(request, "classPath");
		Class<?> clazz = Class.forName(classPath);
		Map<String, ?> map = AppUtil.getImplInstance(clazz);
		return map.values();
	}

	@RequestMapping("pinyin")
	public void pinyin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String chinese = RequestUtil.getString(request, "chinese");
		int type = RequestUtil.getInt(request, "type");// 1：全拼 0：首字母拼音
		String result = "";
		if (type == 1) {
			result = PinyinUtil.getPinyin(chinese);
		} else {
			result = PinyinUtil.getPinYinHeadChar(chinese);
		}
		response.getWriter().write(result);
	}
}
