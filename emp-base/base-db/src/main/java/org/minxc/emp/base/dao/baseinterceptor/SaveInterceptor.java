package org.minxc.emp.base.dao.baseinterceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.minxc.emp.base.api.model.CreateInfoModel;
import org.minxc.emp.base.api.model.IDModel;
import org.minxc.emp.base.core.model.BaseModelImpl;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.db.id.UniqueIdUtil;

import java.util.Date;
import java.util.Properties;

/**
 * 更新设置更新人
 * @author Jeff
 *
 */
@Intercepts({
	        @Signature(type= Executor.class,method = "update",args = {MappedStatement.class,Object.class})
})
public class SaveInterceptor  implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		if(BeanUtils.isEmpty(args) || args.length < 2) {
			 return invocation.proceed();
		}
		
		Object param = args[1];
		MappedStatement statement = (MappedStatement) args[0];
		
		// 更新逻辑
		if(statement.getId().endsWith(".update")) {
			
			 if (param instanceof CreateInfoModel) {
				 CreateInfoModel model = (CreateInfoModel) param;
	            if (model.getUpdateTime() == null) {
	                model.setUpdateTime(new Date());
	            }
	            if(param instanceof BaseModelImpl) {
	            	BaseModelImpl baseModel = (BaseModelImpl) param;
	            	baseModel.setVersion(baseModel.getVersion() + 1);
	            }
	        }
		}

		//新增逻辑
		else if(StringUtils.endsWithAny(statement.getId(), ".create", ".insertSelective")) {
			//为ID赋值
			if (param instanceof IDModel) {
				IDModel model = (IDModel) param;
	            if (model.getId() == null) {
	                model.setId(UniqueIdUtil.getSuid());
	            }
	        }
			//创建信息赋值
			if(param instanceof CreateInfoModel) {
				CreateInfoModel model = (CreateInfoModel) param;
	            if (model.getCreateTime() == null) {
	                model.setCreateTime(new java.util.Date());
	            }
	            if(model.getUpdateTime() == null){
	            	model.setUpdateTime(new Date());
				}
			}
		}
		
		// 批量新增
		
		 return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
