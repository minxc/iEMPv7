package org.minxc.emp.organization.api.model;

/*
 * 
* 项目名称：organization-intf   
* 类名称：GroupStructEnum   
* 类描述： 组结构枚举  
* 创建人：Xianchang.min   
* 创建时间：2018年8月22日 下午11:55:52   
* 修改人：Xianchang.min   
* 修改时间：2018年8月22日 下午11:55:52   
* 修改备注：   
* @version  1.0  
*
 */
public enum GroupStructEnum {

	PLAIN("plain", "平铺"),
    TREE("tree", "树形");

    private String key;
    private String label;

    GroupStructEnum(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String key() {
        return key;
    }

    public String label() {
        return label;
    }
}
