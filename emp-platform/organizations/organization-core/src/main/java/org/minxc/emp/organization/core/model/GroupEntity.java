package org.minxc.emp.organization.core.model;

import org.minxc.emp.base.core.model.AbstractCommonModel;
import org.minxc.emp.organization.api.constant.GroupTypeConstant;
import org.minxc.emp.organization.api.model.GroupStructEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
* 项目名称：organization-core   
* 类名称：GroupEntity   
* 类描述： 系统组织实体类  
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:20:05   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:20:05   
* 修改备注：   
* @version  1.0  
*
 */


@Setter
@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GroupEntity extends AbstractCommonModel{

    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 6626050551592687284L;


    private String name;

    private String parentId;

    private Long seq;

    private String code;

    private String grade;

    private String description;
    
    private String parentGroupName;
    
    /**
     * 是否主组织。
     */
    @Builder.Default
    private int isMaster = 0;
    
    
    

    public String getGroupType() {
        return GroupTypeConstant.ORG.key();
    }

    public GroupStructEnum getStruct() {
        return GroupStructEnum.TREE;
    }

    public String getPath() {
        return null;
    }
}
