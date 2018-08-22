package org.minxc.emp.syetem2.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.api.exception.BusinessException;
import org.minxc.emp.base.core.util.FileUtil;
import org.minxc.emp.base.core.util.PropertyUtil;
import org.minxc.emp.base.core.util.time.DateUtil;

/**
 * <pre>
 * 描述：普通的上传器
 * 上传到服务器的某个文件夹中
 * 每次上传时会自动放在当前日期yyyyMMdd的目录下
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年6月1日
 * 版权:summer
 * </pre>
 */
@Service
public class OrdinaryUploader extends AbstractUploader {

	@Override
	public String type() {
		return "ordinary";
	}

	@Override
	public String upload(InputStream is, String name) {
		FileUtil.createFolderFile(getPath(name));
		FileUtil.writeFile(getPath(name), is);
		return getPath(name);
	}

	@Override
	public InputStream take(String path) {
		try {
			return new FileInputStream(new File(path));
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(String path) {
		FileUtil.deleteFile(path);
	}

	private String getPath(String name) {
		return PropertyUtil.getProperty("uploader.ordinary.path") + DateUtil.getCurrentTime("yyyyMMdd") + File.separator + name;
	}

}
