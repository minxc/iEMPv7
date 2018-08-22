package org.minxc.emp.syetem2.model;

import org.minxc.emp.base.core.model.BaseModelImpl;
import org.minxc.emp.base.core.util.JsonUtil;
import org.minxc.emp.syetem2.model.def.SystemDataSourceDefAttribute;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <pre>
 * 描述：数据源模板
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:下午8:44:14
 * 版权:summer
 * </pre>
 */
public class SystemDataSourceDef extends BaseModelImpl {
    /**
     * 模板名字
     */
    @NotEmpty
    private String name;
    /**
     * 类路径
     */
    @NotEmpty
    private String classPath;
    /**
     * <pre>
     * 属性字段json，为了简单就以json格式入库就行
     * 因为这个对象也不常用，这样保存是可以的，对于常用对象这样就不建议用这个了
     * </pre>
     */
    @NotEmpty
    private String attributesJson;
    /**
     * 属性字段
     */
    @NotNull
    @Valid
    private List<SystemDataSourceDefAttribute> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getAttributesJson() {
        return attributesJson;
    }

    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson;
        this.attributes = JsonUtil.parseArray(attributesJson, SystemDataSourceDefAttribute.class);
    }

    public List<SystemDataSourceDefAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SystemDataSourceDefAttribute> attributes) {
        this.attributes = attributes;
        this.attributesJson = JsonUtil.toJSONString(attributes);
    }

}
