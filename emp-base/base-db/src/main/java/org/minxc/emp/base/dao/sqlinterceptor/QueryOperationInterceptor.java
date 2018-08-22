
/**  

* @Title: QueryOperationInterceptor.java 

* @Package org.minxc.emp.base.dao.sqlinterceptor 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月22日 下午10:51:26 

* @version V1.0  

*/ 

package org.minxc.emp.base.dao.sqlinterceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.minxc.emp.base.api.Page;
import org.minxc.emp.base.api.query.FieldSort;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.core.util.BeanUtils;

import com.github.pagehelper.PageHelper;

/**      
* 项目名称：base-db   
* 类名称：QueryOperationInterceptor   
* 类描述：   查询操作切面逻辑
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:51:26   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:51:26   
* 修改备注：   
* @version  1.0  
**/
@Intercepts(
	    {
	    	@Signature(type = Executor.class, method = "selectByPrimaryKey", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	    }
	)
public class QueryOperationInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		if(BeanUtils.isEmpty(args) || args.length < 2) {
			 return invocation.proceed();
		}
		
		Object param = args[1];
		
		// 分页 ，参数转换
		if(param instanceof QueryFilter) {
			QueryFilter filter = (QueryFilter) param;
			
			//将queryFilter转为Map Param 
			Map<String, Object> params = getQueryParamsByFilter(filter);
			args[1] = params;
			
			Page page = filter.getPage();
			if(page != null) {
				PageHelper.startPage(page.getPageNo(), page.getPageSize(),true);
			}
		}
		
		// 其他事情
		
		
		 return invocation.proceed();
	}
	
	

	private Map<String, Object> getQueryParamsByFilter(QueryFilter filter) {
        //构建动态条件SQL
        String dynamicWhereSql = filter.getFieldLogic().getSql();
        Map<String, Object> params = filter.getParams();

        //默认条件过虑
        String defaultWhere = params.containsKey("defaultWhere") ? params.get("defaultWhere").toString() : "";
        if (StringUtils.isNotEmpty(defaultWhere)) {
            dynamicWhereSql = StringUtils.isNotEmpty(dynamicWhereSql) ? dynamicWhereSql + " and " + defaultWhere : defaultWhere;
        }

        if (StringUtils.isNotEmpty(dynamicWhereSql)) {
            params.put("whereSql", dynamicWhereSql);
        }
        //构建排序SQL
        if (filter.getFieldSortList().size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (FieldSort fieldSort : filter.getFieldSortList()) {
                sb.append(fieldSort.getField()).append(" ").append(fieldSort.getDirection()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            params.put("orderBySql", sb.toString());
        }
        return  params;
	}

	@Override
	public Object plugin(Object target) {
		return null;
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
