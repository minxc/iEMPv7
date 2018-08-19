package org.minxc.emp.syetem2.manager;

import org.minxc.emp.base.manager.Manager;
import org.minxc.emp.syetem2.model.SystemDataSourceDef;
import org.minxc.emp.syetem2.model.def.SystemDataSourceDefAttribute;

import java.util.List;

/**
 * <pre>
 * 描述：数据源模板 Manager处理接口
 * 构建组：白日梦团体
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018-01-03 18:04:15
 * 版权：summer
 * </pre>
 */
public interface SystemDataSourceDefManager extends Manager<String, SystemDataSourceDef> {

    /**
     * <pre>
     * 根据classPath类路径获取数据源的配置参数
     * </pre>
     *
     * @param classPath
     * @return
     */
    List<SystemDataSourceDefAttribute> initAttributes(String classPath);

}
