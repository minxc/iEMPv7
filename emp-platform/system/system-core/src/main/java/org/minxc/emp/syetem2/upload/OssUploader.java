package org.minxc.emp.syetem2.upload;

/**
 * <pre>
 * 描述：oss上传器
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年6月1日
 * 版权:summer
 * TODO 
 * </pre>
 */
public abstract class OssUploader extends AbstractUploader {

	@Override
	public String type() {
		return "oss";
	}

}
