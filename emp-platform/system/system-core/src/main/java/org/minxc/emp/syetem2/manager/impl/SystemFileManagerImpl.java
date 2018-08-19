package org.minxc.emp.syetem2.manager.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.minxc.emp.base.db.id.UniqueIdUtil;
import org.minxc.emp.base.manager.impl.BaseManager;
import org.minxc.emp.syetem2.dao.SystemFileDao;
import org.minxc.emp.syetem2.manager.SystemFileManager;
import org.minxc.emp.syetem2.model.SystemFile;
import org.minxc.emp.syetem2.upload.Uploader;
import org.minxc.emp.syetem2.upload.UploaderFactory;

/**
 * 系统附件 Manager处理实现类
 * 
 * @author aschs
 * @email aschs@qq.com
 * @time 2018-06-07 23:54:49
 */
@Service("sysFileManager")
public class SystemFileManagerImpl extends BaseManager<String, SystemFile> implements SystemFileManager {
	@Resource
    SystemFileDao sysFileDao;

	@Override
	public SystemFile upload(InputStream is, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		String id = UniqueIdUtil.getSuid();

		// 1 先上传文件
		Uploader uploader = UploaderFactory.getDefault();
		String path = uploader.upload(is, id + ext);//以id为文件名保证不重复

		// 2 建立SysFile数据
		SystemFile sysFile = new SystemFile();
		sysFile.setId(id);
		sysFile.setName(fileName);
		sysFile.setUploader(uploader.type());
		sysFile.setPath(path);
		create(sysFile);

		return sysFile;
	}

	@Override
	public InputStream download(String fileId) {
		SystemFile sysFile = get(fileId);
		Uploader uploader = UploaderFactory.getUploader(sysFile.getUploader());
		return uploader.take(sysFile.getPath());
	}
	
	@Override
	public void delete(String fileId) {
		SystemFile sysFile = get(fileId);
//		IUploader uploader = UploaderFactory.getUploader(sysFile.getUploader());
//		uploader.remove(sysFile.getPath());
//		remove(fileId);
		//做逻辑删除
		sysFile.setDelete(true);
		update(sysFile);
	}
}
