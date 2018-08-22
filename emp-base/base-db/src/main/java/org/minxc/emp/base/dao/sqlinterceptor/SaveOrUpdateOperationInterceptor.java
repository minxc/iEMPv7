
/**  

* @Title: SaveOrUpdateOperationInterceptor.java 

* @Package org.minxc.emp.base.dao.sqlinterceptor 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月22日 下午10:52:17 

* @version V1.0  

*/ 

package org.minxc.emp.base.dao.sqlinterceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**      
* 项目名称：base-db   
* 类名称：SaveOrUpdateOperationInterceptor   
* 类描述：   保存/更新操作Mybatis切面逻辑
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:52:17   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:52:17   
* 修改备注：   
* @version  1.0  
**/
@Intercepts({
    @Signature(type= Executor.class,method = "updateByPrimaryKey",args = {MappedStatement.class,Object.class}),
    @Signature(type= Executor.class,method = "updateByPrimaryKeySelective",args = {MappedStatement.class,Object.class})
})
public class SaveOrUpdateOperationInterceptor implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return null;
	}

	@Override
	public Object plugin(Object target) {
		return null;
	}

	@Override
	public void setProperties(Properties properties) {
	}

}
