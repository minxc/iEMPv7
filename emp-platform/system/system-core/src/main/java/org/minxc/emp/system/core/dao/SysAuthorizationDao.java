package org.minxc.emp.system.core.dao;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.SystemAuthorization;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface SysAuthorizationDao extends BaseDao<String, SystemAuthorization> {
	
    public List<SystemAuthorization> getByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

    public void deleteByTarget(@Param("rightsObject")String rightsObject, @Param("rightsTarget")String rightsTarget);

}
