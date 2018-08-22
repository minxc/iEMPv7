
/**  

* @Title: AbstractCommonModel.java 

* @Package org.minxc.emp.base.core.model 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月22日 下午11:46:33 

* @version V1.0  

*/ 

package org.minxc.emp.base.core.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.base.api.model.CommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**      
* 项目名称：base-core   
* 类名称：AbstractCommonModel   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:46:33   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:46:33   
* 修改备注：   
* @version  1.0  
**/

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractCommonModel implements CommonModel {

	
		private static final long serialVersionUID = -4468635720384821075L;
	
		@NotEmpty
	    protected String id;
	    // 创建时间
		protected Date createTime;
	    // 创建人ID
		protected String createBy;
	    // 更新时间
		protected Date updateTime;
	    // 更新人ID
		protected String updateBy;
	    // 版本号
		protected int version = 0;
	    // 逻辑删除标记
		protected boolean isDelete = false;
	
	
}
