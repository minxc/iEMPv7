package org.minxc.emp.syetem2.dao;

import org.minxc.emp.syetem2.model.SystemTreeNodeImpl;
import org.mybatis.spring.annotation.MapperScan;

import org.minxc.emp.base.dao.BaseDao;

/**
 * 系统树节点 DAO接口
 *
 * @author min.xianchang
 * @email xianchangmin@126.com
 * @time 2018-03-13 20:02:33
 */
@MapperScan
public interface SystemTreeNodeDao extends BaseDao<String, SystemTreeNodeImpl> {

    /**
     * <pre>
     * 根据树id删除节点
     * </pre>
     *
     * @param treeId
     */
    void removeByTreeId(String treeId);
    
    /**
     * <pre>
     * 删除path下的全部节点
     * </pre>
     *
     * @param path
     */
    void removeByPath(String path);

}
