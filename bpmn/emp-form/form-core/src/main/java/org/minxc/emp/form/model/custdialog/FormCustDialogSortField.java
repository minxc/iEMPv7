package org.minxc.emp.form.model.custdialog;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * <pre>
 * 描述：自定义对话框的排序字段
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年1月17日 下午8:26:42
 * 版权:summer
 * </pre>
 */
public class FormCustDialogSortField implements Serializable {
    /**
     * 字段名
     */
    @NotEmpty
    private String columnName;
    /**
     * 排序类型 com.dstz.base.api.constant.Direction.key
     */
    @NotEmpty
    private String sortType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
