package org.minxc.emp.form.manager.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.core.util.AppUtil;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.dao.CommonDao;
import org.minxc.emp.base.db.dboper.DbOperator;
import org.minxc.emp.base.db.dboper.DbOperatorFactory;
import org.minxc.emp.base.db.model.query.DefaultQueryFilter;
import org.minxc.emp.base.db.model.table.Column;
import org.minxc.emp.base.db.model.table.Table;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.form.api.constant.FormCustDialogConditionFieldValueSource;
import org.minxc.emp.form.api.constant.FormCustDialogObjType;
import org.minxc.emp.form.api.constant.FormCustDialogStyle;
import org.minxc.emp.form.dao.FormCustDialogDao;
import org.minxc.emp.form.manager.FormCustDialogManager;
import org.minxc.emp.form.model.FormCustDialog;
import org.minxc.emp.form.model.custdialog.FormCustDialogConditionField;
import org.minxc.emp.form.model.custdialog.FormCustDialogDisplayField;
import org.minxc.emp.form.model.custdialog.FormCustDialogReturnField;
import org.minxc.emp.form.model.custdialog.FormCustDialogSortField;
import org.minxc.emp.system.api.groovy.GroovyScriptEngine;
import org.minxc.emp.system.api2.model.SystemDataSource;
import org.minxc.emp.system.api2.service.SystemDataSourceService;

/**
 * form_cust_dialog Manager处理实现类
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-01-18 19:30:51
 */
@Service("formCustDialogManager")
public class FormCustDialogManagerImpl extends BaseManager<String, FormCustDialog> implements FormCustDialogManager {
    @Resource
   private  FormCustDialogDao formCustDialogDao;
    @Autowired
    private SystemDataSourceService sysDataSourceService;
    @Autowired
    private CommonDao<?> commonDao;
    @Autowired
    private   GroovyScriptEngine groovyScriptEngine;

    @Override
    public FormCustDialog getByKey(String key) {
        QueryFilter filter = new DefaultQueryFilter();
        filter.addFilter("key_", key, QueryOperation.EQUAL);
        return this.queryOne(filter);
    }

    @Override
    public Map<String, String> searchObjName(FormCustDialog formCustDialog) {
        SystemDataSource sysDataSource = sysDataSourceService.getByKey(formCustDialog.getDsKey());
        JdbcTemplate jdbcTemplate = sysDataSourceService.getJdbcTemplateByKey(formCustDialog.getDsKey());
        Map<String, String> map = new HashMap<>();// Map<表/视图名,表/视图描述>
        DbOperator dbOperator = DbOperatorFactory.newOperator(sysDataSource.getDbType(), jdbcTemplate);
        // 表
        if (FormCustDialogObjType.TABLE.equalsWithKey(formCustDialog.getObjType())) {
            map = dbOperator.getTableNames(formCustDialog.getObjName());
        } else if (FormCustDialogObjType.VIEW.equalsWithKey(formCustDialog.getObjType())) {// 视图
            List<String> viewNames = dbOperator.getViewNames(formCustDialog.getObjName());
            for (String viewName : viewNames) {
                map.put(viewName, viewName);
            }
        }
        return map;
    }

    @Override
    public Table<Column> getTable(FormCustDialog formCustDialog) {
        try {
            SystemDataSource sysDataSource = sysDataSourceService.getByKey(formCustDialog.getDsKey());
            JdbcTemplate jdbcTemplate = sysDataSourceService.getJdbcTemplateByKey(formCustDialog.getDsKey());
            DbOperator dbOperator = DbOperatorFactory.newOperator(sysDataSource.getDbType(), jdbcTemplate);
            Table<Column> table = null;
            // 表
            if (FormCustDialogObjType.TABLE.equalsWithKey(formCustDialog.getObjType())) {
                table = dbOperator.getTable(formCustDialog.getObjName());
            } else if (FormCustDialogObjType.VIEW.equalsWithKey(formCustDialog.getObjType())) {// 视图
                table = dbOperator.getView(formCustDialog.getObjName());
            }
            return table;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<?> data(FormCustDialog formCustDialog, QueryFilter queryFilter) {
        String sql = analyseSql(formCustDialog);
        handleQueryFilter(formCustDialog, queryFilter);

        if(FormCustDialog.DATA_SOURCE_INTERFACE.equals(formCustDialog.getDataSource())) {
        	return getDataByInterface(formCustDialog,queryFilter);
        }
        
        List<?> list;
        if (formCustDialog.isPage()) {
            list = commonDao.queryForListPage(sql, queryFilter);
        } else {
        	queryFilter.setPage(null);
        	list = commonDao.queryForListPage(sql, queryFilter);
        }

        return list;
    }
    
	private List getDataByInterface(FormCustDialog customDialog, QueryFilter queryFilter) {
		String beanMethod = customDialog.getObjName();
		if(StringUtil.isEmpty(beanMethod)) throw new RuntimeException("自定义对话框数据服务接口不能为空！"); 
		
		String[] aryHandler = beanMethod.split("[.]");
		if(aryHandler==null || aryHandler.length!=2) throw new RuntimeException("自定义对话框数据服务接口格式不正确！"+beanMethod); ;
		
		String beanId = aryHandler[0];
		String method = aryHandler[1];
		// 触发该Bean下的业务方法
		Object serviceBean = AppUtil.getBean(beanId);
		if(serviceBean==null) return null;
		try {
			Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] {QueryFilter.class});
			return (List) invokeMethod.invoke(serviceBean,queryFilter);
		} catch (Exception e) {
			throw new RuntimeException("查询异常！"+e.getMessage(),e);
		}
	}

    /**
     * <pre>
     * 获取sql
     * 只获取到select XXXX from xxxx 这一段
     * where 和 order 由后面queryFilter来获取
     * </pre>
     *
     * @param formCustDialog
     * @return
     */
    private String analyseSql(FormCustDialog formCustDialog) {
        // 返回sql
        Set<String> columnNameSet = new HashSet<>();//select 的展示字段
        if (FormCustDialogStyle.LIST.equalsWithKey(formCustDialog.getStyle())) {
            for (FormCustDialogDisplayField field : formCustDialog.getDisplayFields()) {//显示字段要查出来
                columnNameSet.add(field.getColumnName());
            }
        }
        if (FormCustDialogStyle.TREE.equalsWithKey(formCustDialog.getStyle())) {
            columnNameSet.add(formCustDialog.getTreeConfig().getPid());
            columnNameSet.add(formCustDialog.getTreeConfig().getId());
            columnNameSet.add(formCustDialog.getTreeConfig().getShowColumn());
        }

        for (FormCustDialogReturnField field : formCustDialog.getReturnFields()) {//返回字段要查出来
            columnNameSet.add(field.getColumnName());
        }
        for (FormCustDialogSortField field : formCustDialog.getSortFields()) {//排序字段要查出来
            columnNameSet.add(field.getColumnName());
        }

        //拼装displaySql
        StringBuilder displaySql = new StringBuilder();
        for (String columnName : columnNameSet) {
            if (displaySql.length() > 0) {
                displaySql.append(",");
            }
            displaySql.append(columnName);
        }

        return "select " + displaySql.toString() + " from " + formCustDialog.getObjName();
    }

    /**
     * <pre>
     * 处理QueryFilter
     * </pre>
     *
     * @param formCustDialog
     * @param queryFilter
     * @return
     */
    private QueryFilter handleQueryFilter(FormCustDialog formCustDialog, QueryFilter queryFilter) {
        //处理条件 固定值，和脚本参数
        for (FormCustDialogConditionField field : formCustDialog.getConditionFields()) {
            if (FormCustDialogConditionFieldValueSource.FIXED_VALUE.equalsWithKey(field.getValueSource())) {
                Object value = BeanUtils.getValue(field.getDbType(), QueryOperation.getByVal(field.getCondition()), field.getValue().getText());
                queryFilter.addFilter(field.getColumnName(), value, QueryOperation.getByVal(field.getCondition()));
            }
            if (FormCustDialogConditionFieldValueSource.SCRIPT.equalsWithKey(field.getValueSource())) {
                Object value = groovyScriptEngine.executeObject(field.getValue().getText(), queryFilter.getParams());
                queryFilter.addFilter(field.getColumnName(), value, QueryOperation.getByVal(field.getCondition()));
            }
        }

        //处理默认排序
        for (FormCustDialogSortField field : formCustDialog.getSortFields()) {
            queryFilter.addFieldSort(field.getColumnName(), field.getSortType());
        }

        return queryFilter;
    }
}
