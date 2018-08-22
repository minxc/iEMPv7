package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.minxc.emp.base.rest.BaseController;
import org.minxc.emp.base.rest.util.RequestUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.aop.annotion.CatchError;
import org.minxc.emp.base.api.query.QueryFilter;
import org.minxc.emp.base.api.query.QueryOperation;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.system.core.manager.DataDictManager;
import org.minxc.emp.system.core.model.DataDict;


/**
 * 数据字典 控制器类<br/>
 * @author  min.xianchang
 */
@RestController
@RequestMapping("/sys/dataDict")
public class DataDictionaryController extends BaseController<DataDict>{
	@Resource
	DataDictManager dataDictManager;
	
	
	@Override
	protected String getModelDesc(){
		return "数据字典";
	}
	
	@RequestMapping("getDictData")
	public ResultMsg<List<DataDict>> getByDictKey(@RequestParam String dictKey,@RequestParam(defaultValue="false") Boolean hasRoot) throws Exception{
		if(StringUtil.isEmpty(dictKey)) return null;
		
		List<DataDict> dict = dataDictManager.getDictNodeList(dictKey,hasRoot);
		return getSuccessResult(dict);
	}
	
	@RequestMapping("getDictList")
	public ResultMsg<List<DataDict>> getDictList(HttpServletRequest request) throws Exception{
		QueryFilter filter = getQueryFilter(request);
		filter.addFilter("dict_type_", DataDict.TYPE_DICT, QueryOperation.EQUAL);
		filter.setPage(null);
		
		List<DataDict> dict = dataDictManager.query(filter);
		return getSuccessResult(dict);
	}
	
	/**
	 * 获取所有数据字典，以tree的形式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDictTree")
	@CatchError("获取数据字典失败")
	public ResultMsg<JSONArray> getDictTree() throws Exception{
		JSONArray dict = dataDictManager.getDictTree();
		return getSuccessResult(dict);
	}
	
}
