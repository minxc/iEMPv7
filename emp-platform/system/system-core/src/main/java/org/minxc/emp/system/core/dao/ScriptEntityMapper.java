package org.minxc.emp.system.core.dao;

import org.minxc.emp.system.core.model.ScriptEntity;

/**
 * 
* 项目名称：system-core   
* 类名称：ScriptEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:35:14   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:35:14   
* 修改备注：   
* @version  1.0  
*
 */
public interface ScriptEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScriptEntity record);

    int insertSelective(ScriptEntity record);

    ScriptEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScriptEntity record);

    int updateByPrimaryKeyWithBLOBs(ScriptEntity record);

    int updateByPrimaryKey(ScriptEntity record);
}