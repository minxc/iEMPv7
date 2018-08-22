package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.UploadedFileEntity;


/**
 * 
* 项目名称：system-core   
* 类名称：UploadedFileEntityMapper   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午10:35:02   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午10:35:02   
* 修改备注：   
* @version  1.0  
*
 */
public interface UploadedFileEntityMapper {
    int deleteByPrimaryKey(String id);

    int insert(UploadedFileEntity record);

    int insertSelective(UploadedFileEntity record);

    UploadedFileEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UploadedFileEntity record);

    int updateByPrimaryKey(UploadedFileEntity record);
}