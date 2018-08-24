package org.minxc.emp.organization.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.base.dao.CommonBaseDao;
import org.minxc.emp.organization.core.model.GroupPositionLinkEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* 项目名称：organization-core   
* 类名称：GroupPositionLinkEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:10:26   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:10:26   
* 修改备注：   
* @version  1.0  
*
 */

@Mapper
public interface GroupPositionLinkEntityMapper extends CommonBaseDao<String, GroupPositionLinkEntity>{

}