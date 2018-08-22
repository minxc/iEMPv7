
/**  

* @Title: CreateAndUpdateInfoModel.java 

* @Package org.minxc.emp.base.api.model 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月22日 下午11:41:49 

* @version V1.0  

*/ 

package org.minxc.emp.base.api.model;

import java.util.Date;

/**      
* 项目名称：base-intf   
* 类名称：CreateAndUpdateInfoModel   
* 类描述：   创建更新信息接口--若实现了该接口、则保存更新时会自动赋值
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:41:49   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:41:49   
* 修改备注：   
* @version  1.0  
**/

public interface CreateAndUpdateInfoModel {
	
	 /**
     * <pre>
     * 创建时间
     * </pre>
     *
     * @return
     */
    public Date getCreateTime();

    /**
     * <pre>
     * 设置创建时间
     * </pre>
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime);

    /**
     * <pre>
     * 创建人ID
     * </pre>
     *
     * @return
     */
    public String getCreateBy();

    /**
     * <pre>
     * 设置创建人ID
     * </pre>
     *
     * @param createBy
     */
    public void setCreateBy(String createBy);
    /**
     * <pre>
     * 更新时间
     * </pre>
     *
     * @return
     */
    public Date getUpdateTime();

    /**
     * <pre>
     * 设置 更新时间
     * </pre>
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime);

    /**
     * <pre>
     * 更新人ID
     * </pre>
     *
     * @return
     */
    public String getUpdateBy();

    /**
     * <pre>
     * 设置更新人ID
     * </pre>
     *
     * @param updateBy
     */
    public void setUpdateBy(String updateBy);
}
