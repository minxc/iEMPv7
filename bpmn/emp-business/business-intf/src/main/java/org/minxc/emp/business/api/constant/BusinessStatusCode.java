package org.minxc.emp.business.api.constant;

import org.minxc.emp.base.api.constant.StatusCode;

/**
 * <pre>
 * bus层的异常码
 * </pre>
 * 
 * @author aschs
 *
 */
public enum BusinessStatusCode implements StatusCode {

	PARAM_ILLEGAL("100", "参数校验不通过");

	private String code;
	private String desc;
	private String system;

	private BusinessStatusCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
		this.system = "BUS";
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public String getSystem() {
		return system;
	}
}
