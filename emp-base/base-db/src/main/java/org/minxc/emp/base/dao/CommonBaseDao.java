
/**  

* @Title: CommonBaseDao.java 

* @Package org.minxc.emp.base.dao 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月22日 下午10:07:58 

* @version V1.0  

*/ 

package org.minxc.emp.base.dao;

import java.io.Serializable;
import java.util.List;

import org.minxc.emp.base.api.query.QueryFilter;


/**      
* 项目名称：base-db   
* 类名称：CommonBaseDao   
* 类描述：  系统基础公共dao
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:07:58   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:07:58   
* 修改备注：   
* @version  1.0  
**/

public interface CommonBaseDao<PK extends Serializable, T> {
		
	/**
	 * 
	
	* @Title: deleteByPrimaryKey 
	
	* @Description: 根据实体对象主键删除实体记录
	
	* @param @param id
	* @param @return    设定文件 
	
	* @return int    返回类型 
	
	* @throws
	 */
	  	int deleteByPrimaryKey(String id);
	  	
	  	/**
	  	 * 
	  	
	  	* @Title: insert 
	  	
	  	* @Description:新建实体对象
	  	
	  	* @param @param entity
	  	* @param @return    设定文件 
	  	
	  	* @return int    返回类型 
	  	
	  	* @throws
	  	 */

	    int insert(T entity);
	    
	    /**
	    
	    * @Title: insertSelective 
	    
	    * @Description: 根据实体对象创建实体
	    
	    * @param @param entity
	    * @param @return    设定文件 
	    
	    * @return int    返回类型 
	    
	    * @throws
	     */
	    int insertSelective(T entity);
	    
	    /**
	    
	    * @Title: selectByPrimaryKey 
	    
	    * @Description: 根据主键查询实体
	    
	    * @param @param id
	    * @param @return    设定文件 
	    
	    * @return T    返回类型 
	    
	    * @throws
	     */
	    T selectByPrimaryKey(String id);
	    
	    /**
	    
	    * @Title: updateByPrimaryKeySelective 
	    
	    * @Description: 根据主键更新实体
	    
	    * @param @param entity
	    * @param @return    设定文件 
	    
	    * @return int    返回类型 
	    
	    * @throws
	     */
	    int updateByPrimaryKeySelective(T entity);

	    /**
	     * 
	    
	    * @Title: updateByPrimaryKey 
	    
	    * @Description: 根据主键更新实体
	    
	    * @param @param entity
	    * @param @return    设定文件 
	    
	    * @return int    返回类型 
	    
	    * @throws
	     */
	    int updateByPrimaryKey(T entity);

	    
	    
	    /**
	     * 
	    
	    * @Title: query 
	    
	    * @Description: 根据查询条件查询实体对象
	    
	    * @param @param queryFilter
	    * @param @return    设定文件 
	    
	    * @return List<T>    返回类型 
	    
	    * @throws
	     */
	    public List<T> query(QueryFilter queryFilter);
	    /**
	     * 
	    
	    * @Title: query 
	    
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    
	    * @param @return    设定文件 
	    
	    * @return List<T>    返回类型 
	    
	    * @throws
	     */
	    public List<T> query();
}
