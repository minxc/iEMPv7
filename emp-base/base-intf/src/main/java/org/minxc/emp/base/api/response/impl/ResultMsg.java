package org.minxc.emp.base.api.response.impl;

import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.constant.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* 项目名称：base-intf   
* 类名称：ResultMsg   
* 类描述：回结果   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:02:33   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:02:33   
* 修改备注：   
* @version  1.0  
*
 */
public class ResultMsg<E> extends BaseResult {

    /** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = 2299392167452381897L;
	
	
	@Deprecated
    public static final int SUCCESS = 1;
    @Deprecated
    public static final int FAIL = 0;
    @Deprecated
    public static final int ERROR = -1;
    @Deprecated
    public static final int TIMEOUT = 2;

    private E data = null; // 返回数据

    public ResultMsg() {

    }

    /**
     * 成功，有结果数据
     *
     * @param data
     */
    public ResultMsg(E result) {
        this.IsOk(true);
        this.setData(result);
    }

    public ResultMsg(StatusCode code, String msg) {
        if (BaseStatusCode.SUCCESS.getCode().equals(code.getCode())) {
            this.setIsOk(true);
        } else {
            this.IsOk(false);
        }
        this.setStatusCode(code);
        this.setMsg(msg);
    }

    @Deprecated
    public ResultMsg(int code, String msg) {
        this.IsOk(code == SUCCESS);
        this.setMsg(msg);
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void addMapParam(String key, Object val) {
        if (data == null) {
            Map map = new HashMap();
            this.data = (E) map;
        }
        if (!(this.data instanceof Map)) {
            throw new RuntimeException("设置参数异常！当前返回结果非map对象，无法使用 addMapParam方法获取数据");
        }

        Map map = (Map) data;
        map.put(key, val);
    }

    public Object getMapParam(String key) {
        if (!(this.data instanceof Map)) {
            throw new RuntimeException("获取参数异常！当前返回结果非map对象，无法使用 addMapParam方法获取数据");
        }

        Map map = (Map) data;
        return map.get(key);
    }
}

