package org.minxc.emp.organization.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.base.dao.CommonBaseDao;
import org.minxc.emp.organization.core.model.GroupEntity;


/**
 * 
* 项目名称：organization-core   
* 类名称：GroupEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:31:12   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:31:12   
* 修改备注：   
* @version  1.0  
*
 */
@Mapper
public interface GroupEntityMapper extends CommonBaseDao<String, GroupEntity>{
	
	/**
	 * 
	
	* @Title: getGroupEntityByCode 
	
	* @Description: 根据组织编码查询组织信息
	
	* @param @param code
	* @param @return    设定文件 
	
	* @return GroupEntity    返回类型 
	
	* @throws
	 */
	public GroupEntity getGroupEntityByCode(String code);
	
	
	/**
	 * 
	
	* @Title: getGroupEntityByUserId 
	
	* @Description:  根据用户Id查询用户所属组织信息
	
	* @param @param userId
	* @param @return    设定文件 
	
	* @return GroupEntity    返回类型 
	
	* @throws
	 */
	public GroupEntity getGroupEntityByUserId(String userId);
	
	

	
}