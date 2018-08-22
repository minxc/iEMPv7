package org.minxc.emp.syetem2.manager.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.syetem2.dao.SystemDataSourceDefDao;
import org.minxc.emp.syetem2.manager.SystemDataSourceDefManager;
import org.minxc.emp.syetem2.model.SystemDataSourceDef;
import org.minxc.emp.syetem2.model.def.SystemDataSourceDefAttribute;

/**
 * @author min.xianchang
 * @description 数据源模板 Manager处理实现类
 * @group dayDream
 * @email xianchangmin@126.com
 */
@Service
public class SystemDataSourceDefManagerImpl extends BaseManager<String, SystemDataSourceDef> implements SystemDataSourceDefManager {
    @Autowired
    SystemDataSourceDefDao sysDataSourceDefDao;

    @Override
    public List<SystemDataSourceDefAttribute> initAttributes(String classPath) {
        try {
            List<SystemDataSourceDefAttribute> attributes = new ArrayList<>();
            Class<?> cls = Class.forName(classPath);
            if (!DataSource.class.isAssignableFrom(cls)) {// 不是DataSource的子类就报错
                throw new Exception("classPath[" + classPath + "]不是javax.sql.DataSource的子类");
            }
            for (Method method : cls.getMethods()) {
                if (!method.getName().startsWith("set") || method.getParameters().length != 1) {
                    continue;
                }
                Parameter param = method.getParameters()[0];
                SystemDataSourceDefAttribute attribute = new SystemDataSourceDefAttribute();
                String fieldName = StringUtil.lowerFirst(method.getName().replace("set", ""));
                attribute.setComment(fieldName);
                attribute.setName(fieldName);
                attribute.setRequired(false);
                attribute.setType(param.getType().getName());
                attributes.add(attribute);
            }
            return attributes;
        } catch (Exception e) {
            throw new BusinessException("根据classPath[" + classPath + "]获取参数", e);
        }
    }

    public static void main(String[] args) {
        SystemDataSourceDefManagerImpl impl = new SystemDataSourceDefManagerImpl();
        //org.apache.commons.dbcp.BasicDataSource
        //com.alibaba.druid.pool.DruidDataSource
        System.out.println(JSON.toJSONString(impl.initAttributes("org.apache.commons.dbcp.BasicDataSource")));
    }
}
