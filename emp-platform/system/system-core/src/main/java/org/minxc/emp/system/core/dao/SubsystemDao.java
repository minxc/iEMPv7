package org.minxc.emp.system.core.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;
import org.minxc.emp.system.core.model.SubsystemImpl;

/**
 * <pre>
 * 描述：子系统定义 DAO接口
 * </pre>
 */
@MapperScan
public interface SubsystemDao extends BaseDao<String, SubsystemImpl> {

    /**
     * 判断别名是否存在
     *
     * @param subsystem
     * @return
     */
	Integer isExist(SubsystemImpl subsystem);

    /**
     * 获取子系统列表。
     *
     * @return
     */
    List<SubsystemImpl> getList();

    /**
     * 更新为默认。
     */
    void updNoDefault();


    /**
     * 根据用户获取子系统列表。
     *
     * @param userId
     * @return
     */
    List<SubsystemImpl> getSystemByUser(String userId);
}
