package org.minxc.emp.system.rest.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.FileUtil;
import org.minxc.emp.base.core.util.ZipUtil;
import org.minxc.emp.base.core.util.time.DateUtil;
import org.minxc.emp.base.db.model.page.PageJson;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.syetem2.manager.SystemFileManager;
import org.minxc.emp.syetem2.model.SystemFile;
import com.github.pagehelper.Page;

import net.lingala.zip4j.core.ZipFile;

/**
 * <pre>
 * 描述：上传附件的controller
 * 作者:aschs
 * 邮箱:aschs@qq.com
 * 日期:2018年6月4日
 * 版权:summer
 * </pre>
 */
@RestController
@RequestMapping("/sys/SystemFile/")
public class SystemFileController extends GenericController {
	@Autowired
	SystemFileManager SystemFileManager;

	/**
	 * <pre>
	 * </pre>
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@CatchError(value = "上传失败")
	public ResultMsg<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
		SystemFile SystemFile = SystemFileManager.upload(file.getInputStream(), file.getOriginalFilename());
		return getSuccessResult(SystemFile.getId(), "上传成功");
	}

	/**
	 * <pre>
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	@CatchError(value = "下载失败")
	public ResponseEntity<byte[]> download(@RequestParam("fileId") String fileId) throws Exception {
		SystemFile SystemFile = SystemFileManager.get(fileId);

		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(SystemFile.getName().getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<>(IOUtils.toByteArray(SystemFileManager.download(fileId)), headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "zip", method = RequestMethod.GET)
	@CatchError(value = "打包失败")
	public ResponseEntity<byte[]> zip(@RequestParam("fileIds") String fileIds) throws Exception {
		ArrayList<File> sourceFileList = new ArrayList<>();
		for (String id : fileIds.split(",")) {
			SystemFile SystemFile = SystemFileManager.get(id);
			sourceFileList.add(FileUtil.inputstream2file(SystemFileManager.download(id),new File(SystemFile.getName())));
		}

		String zipName = DateUtil.getCurrentTime("yyyyMMddHHmmss") + ".zip";
		File file = ZipUtil.zip(sourceFileList, new ZipFile(new File(zipName)));
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(zipName.getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		byte[] bs = FileUtils.readFileToByteArray(file);
		file.delete();// 删除临时zip文件
		return new ResponseEntity<>(bs, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "del")
	@CatchError(value = "删除失败")
	public ResultMsg<String> del(@RequestParam("fileId") String fileId) throws Exception {
		SystemFileManager.delete(fileId);
		return getSuccessResult("删除成功");
	}
	
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SystemFile> pageList = (Page<SystemFile>) SystemFileManager.query(queryFilter);
        return new PageJson(pageList);
    }
}
